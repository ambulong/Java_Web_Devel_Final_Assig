/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.BBoard;
import lib.BTip;
import lib.BTipBean;
import lib.BUser;
import util.BFunctions;
import util.BRespJson;
import util.BSession;

/**
 *
 * @author Ambulong
 */
public class GetTips {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public GetTips(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    
    public void init() throws IOException, Exception {
        BRespJson brj = new BRespJson(request, response);
        BSession bs = new BSession(request);

        if (!BFunctions.chkToken(this.request)) {
            brj.resp(-1, "Invalid Token", null);
            return;
        }

        if (!bs.isLogin()) {
            brj.resp(0, "未登录", null);
            return;
        }
        
        BBoard bb = new BBoard();
        int bid = this.request.getParameter("bid") != null ? Integer.parseInt(this.request.getParameter("bid").trim()) : -1;
        if(bid != 0 && !bb.isExistID(bid)){
            brj.resp(-1, "板块不存在", null);
            return;
        }
        
        BTip bt = new BTip();
        BTipBean[] btbs = bt.getTipList(bid);
        BUser bu = new BUser();
        
        List<Map> dataList = new ArrayList<Map>();
        for(int i=0; i<btbs.length; i++){
            Map<String, String> map = new<String, String> HashMap();  
            //map.put( "content", btbs[i].getContent() );
            //map.put( "makefile", btbs[i].getMakefile() );
            map.put( "pubtime", btbs[i].getPubtime() );
            //map.put( "realfile", btbs[i].getRealfile() );
            map.put( "title", btbs[i].getTitle() );
            map.put( "bid", btbs[i].getBid()+"" );
            map.put( "id", btbs[i].getId()+"" );
            map.put( "uid", btbs[i].getUid()+"" );
            map.put( "author", bu.getUsername(btbs[i].getUid()) );
            dataList.add(map);
        }

        //System.out.println("getUid: "+map.toString());
        brj.resp(1, "", dataList);
        return;
    }
}
