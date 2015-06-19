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
import lib.BUser;
import lib.BUserBean;
import util.BFunctions;
import util.BRespJson;
import util.BSession;

/**
 *
 * @author Ambulong
 */
public class GetUserInfo {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public GetUserInfo(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    
    public void init() throws IOException, Exception {
        BRespJson brj = new BRespJson(request, response);
        BSession bs = new BSession(request);
        BUser buser = new BUser();

        if (!BFunctions.chkToken(this.request)) {
            brj.resp(-1, "Invalid Token", null);
            return;
        }

        if (!bs.isLogin()) {
            brj.resp(0, "未登录", null);
            return;
        }
        BUserBean bui = buser.getDetail(bs.getUid());
        List<Map> dataList = new ArrayList<Map>();
        Map<String, String> map = new<String, String> HashMap();  
        map.put( "id", bui.getId()+"" );
        map.put("gender", bui.getGendertoString());
        map.put("head", bui.getHead());
        map.put("regtime", bui.getRegtime());
        map.put("username", bui.getUsername());
        map.put("age", bui.getAge()+"");
        map.put("flag", bui.getFlag()+"");
        dataList.add(map);
        //System.out.println("getUid: "+map.toString());
        brj.resp(1, "", dataList);
        return;
    }
}
