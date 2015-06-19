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
import lib.BReply;
import lib.BReplyBean;
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
public class GetReplies {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public GetReplies(HttpServletRequest request, HttpServletResponse response) {
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
        
        BTip bt = new BTip();
        int tid = this.request.getParameter("tid") != null ? Integer.parseInt(this.request.getParameter("tid").trim()) : -1;
        if(!bt.isExistID(tid)){
            brj.resp(-1, "帖子不存在", null);
            return;
        }
        
        BReply br = new BReply();
        BReplyBean[] brbs = br.getReplyList(tid);
        BUser bu = new BUser();
        
        List<Map> dataList = new ArrayList<Map>();
        for(int i=0; i<brbs.length; i++){
            Map<String, String> map = new<String, String> HashMap();  
            map.put( "content", brbs[i].getContent());
            map.put( "makefile", brbs[i].getMakefile());
            map.put( "pubtime", brbs[i].getPubtime());
            map.put( "realfile", brbs[i].getRealfile());
            map.put( "title", brbs[i].getTitle());
            map.put( "id", brbs[i].getId()+"");
            map.put( "tid", brbs[i].getTid()+"");
            map.put( "uid", brbs[i].getUid()+"");
            map.put( "author", bu.getUsername(brbs[i].getUid()) );
            dataList.add(map);
        }

        //System.out.println("getUid: "+map.toString());
        brj.resp(1, "", dataList);
        return;
    }
}
