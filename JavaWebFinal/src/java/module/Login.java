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
public class Login {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public Login(HttpServletRequest request, HttpServletResponse response) {
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

        if (bs.isLogin()) {
            brj.resp(-1, "您已登录", null);
            return;
        }

        String username = this.request.getParameter("username") != null ? this.request.getParameter("username").trim() : "";
        String password = this.request.getParameter("password") != null ? this.request.getParameter("password") : "";
        
        if(username.equals("") || password.equals("")){
            brj.resp(-1, "用户名和密码不许为空", null);
            return;
        }
        
        BUser buser = new BUser();
        if (!buser.isExistName(username) || !buser.chkLogin(username, password)) {
            brj.resp(-1, "用户名或密码错误", null);
            return;
        }else{
            int id = buser.getID(username);
            if(id <= 0){
                brj.resp(-1, "登录失败", null);
                return;
            }else{
                bs.setUid(id);
                bs.setFlag(buser.getFlag(id));
                brj.resp(1, "登录成功", null);
                return;
            }
        }
    }
}
