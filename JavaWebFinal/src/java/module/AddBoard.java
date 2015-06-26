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
public class AddBoard {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public AddBoard(HttpServletRequest request, HttpServletResponse response) {
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

        if (!bs.isAdmin()) {
            brj.resp(0, "权限不够", null);
            return;
        }

        BBoard bb = new BBoard();

        String name = this.request.getParameter("name") != null ? this.request.getParameter("name").trim() : "";
        int pid = this.request.getParameter("pid") != null ? Integer.parseInt(this.request.getParameter("pid").trim()) : 0;
        System.out.println("AddBoard: "+ name);
        if (name.equals("")) {
            brj.resp(-1, "板块名不能为空不存在", null);
            return;
        }

        if (pid != 0 && !bb.isExistID(pid)) {
            brj.resp(-1, "父板块不存在", null);
            return;
        }

        BBoardBean bbb = new BBoardBean();
        bbb.setName(name);
        bbb.setPid(pid);

        if (bb.add(bbb)) {
            brj.resp(1, "添加成功", null);
        } else {
            brj.resp(-1, "添加失败", null);
        }
        return;
    }
}
