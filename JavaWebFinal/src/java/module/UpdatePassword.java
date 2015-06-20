/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.BUser;
import util.BFunctions;
import util.BRespJson;
import util.BSession;

/**
 *
 * @author Ambulong
 */
public class UpdatePassword {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public UpdatePassword(HttpServletRequest request, HttpServletResponse response) {
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

        String oldpassword = this.request.getParameter("oldpassword") != null ? this.request.getParameter("oldpassword") : "";
        String password = this.request.getParameter("password") != null ? this.request.getParameter("password") : "";

        if (oldpassword.equals("") || password.equals("")) {
            brj.resp(-1, "密码不许为空", null);
            return;
        }

        BUser buser = new BUser();
        if (!buser.chkLogin(bs.getUid(), oldpassword)) {
            brj.resp(-1, "原密码错误", null);
            return;
        } else {
            if (buser.updatePassword(bs.getUid(), password)) {
                brj.resp(1, "修改成功", null);
            } else {
                brj.resp(-1, "修改失败", null);
            }
        }
    }
}
