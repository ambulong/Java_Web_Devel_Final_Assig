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
public class GetTipDetail {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public GetTipDetail(HttpServletRequest request, HttpServletResponse response) {
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

        int id = this.request.getParameter("id") != null ? Integer.parseInt(this.request.getParameter("id").trim()) : -1;
        if (!bt.isExistID(id)) {
            brj.resp(-1, "帖子不存在", null);
            return;
        }

        BTipBean btb = bt.getDetail(id);
        BUser bu = new BUser();

        List<Map> dataList = new ArrayList<Map>();
        Map<String, String> map = new <String, String> HashMap();
        map.put("content", btb.getContent());
        map.put("makefile", btb.getMakefile());
        map.put("pubtime", btb.getPubtime());
        map.put("realfile", btb.getRealfile());
        map.put("title", btb.getTitle());
        map.put("bid", btb.getBid() + "");
        map.put("id", btb.getId() + "");
        map.put("uid", btb.getUid() + "");
        map.put("author", bu.getUsername(btb.getUid()));
        dataList.add(map);

        //System.out.println("getUid: "+map.toString());
        brj.resp(1, "", dataList);
        return;
    }
}
