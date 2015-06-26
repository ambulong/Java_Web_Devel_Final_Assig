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
import org.apache.commons.lang.StringEscapeUtils;
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
        List<BBoardBean> bbbs = bb.getBoardList();
        //System.out.println("BoardList length: "+bbbs.size());
        int escape = this.request.getParameter("html") != null ? Integer.parseInt(this.request.getParameter("html").trim()) : 0;

        List<Map> dataList = new ArrayList<Map>();
        for (int i = 0; i < bbbs.size(); i++) {
            Map<String, String> map = new <String, String> HashMap();
            //System.out.println("BoardList test: "+bbbs.get(i).getName());
            if (escape == 1) {
                map.put("name", StringEscapeUtils.escapeHtml(bbbs.get(i).getName()));
                map.put("id", StringEscapeUtils.escapeHtml(bbbs.get(i).getId() + ""));
                map.put("pid", StringEscapeUtils.escapeHtml(bbbs.get(i).getPid() + ""));
            } else {
                map.put("name", bbbs.get(i).getName());
                map.put("id", bbbs.get(i).getId() + "");
                map.put("pid", bbbs.get(i).getPid() + "");
            }
            dataList.add(map);
        }

        //System.out.println("getUid: "+map.toString());
        brj.resp(1, "", dataList);
        return;
    }
}
