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
import lib.BBoardBean;
import lib.BReply;
import lib.BReplyBean;
import lib.BTip;
import lib.BUser;
import util.BFunctions;
import util.BRespJson;
import util.BSession;

/**
 *
 * @author Ambulong
 */
public class GetBoards {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public GetBoards(HttpServletRequest request, HttpServletResponse response) {
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
        
        BBoard bb = new BBoard();
        BBoardBean[] bbb = bb.getBoardList();

        List<Map> dataList = new ArrayList<Map>();
        for(int i=0; i<bbb.length; i++){
            Map<String, String> map = new<String, String> HashMap();  
            map.put( "name", bbb[i].getName());
            map.put( "id", bbb[i].getId()+"");
            map.put( "pid", bbb[i].getPid()+"");
            
            dataList.add(map);
        }

        //System.out.println("getUid: "+map.toString());
        brj.resp(1, "", dataList);
        return;
    }
}
