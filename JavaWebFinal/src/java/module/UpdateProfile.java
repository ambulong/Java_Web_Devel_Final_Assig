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
import lib.BUserBean;
import util.BFunctions;
import util.BRespJson;
import util.BSession;

/**
 *
 * @author Ambulong
 */
public class UpdateProfile {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public UpdateProfile(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void init() throws IOException, Exception {
        BRespJson brj = new BRespJson(request, response);
        BUserBean bub = new BUserBean();
        BSession bs = new BSession(request);

        if (!BFunctions.chkToken(this.request)) {
            brj.resp(-1, "Invalid Token", null);
            return;
        }

        if (!bs.isLogin()) {
            brj.resp(0, "未登录", null);
            return;
        }

        String age = this.request.getParameter("age") != null ? this.request.getParameter("age").trim() : "-1";
        if (age.equals("")) {
            age = "-1";
        }
        String head = this.request.getParameter("head") != null ? this.request.getParameter("head").trim() : "";
        String gender = this.request.getParameter("gender") != null ? this.request.getParameter("gender").trim() : "-1";
        if (gender.equals("")) {
            gender = "-1";
        }

        bub.setId(bs.getUid());
        bub.setAge(Integer.parseInt(age));
        bub.setHead(head);
        bub.setGender(Integer.parseInt(gender));

        if (!bub.validateProfile()) {
            brj.resp(-1, "参数不完整或格式错误", null);
            return;
        } else {
            BUser buser = new BUser();
            if (!buser.updateProfile(bub)) {
                brj.resp(-1, "更改失败", null);
            } else {
                brj.resp(1, "更改成功", null);
            }
        }
    }
}
