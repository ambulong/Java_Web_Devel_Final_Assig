/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.*;
import util.*;

/**
 *
 * @author Ambulong
 */
public class Register {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public Register(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void init() throws IOException, Exception {
        BRespJson brj = new BRespJson(request, response);
        BUserBean bub = new BUserBean();
        BSession bs = new BSession(request);
        
        if(!BFunctions.chkToken(this.request)){
            brj.resp(-1, "Invalid Token", null);
            return;
        }
        
        if(bs.isLogin()){
            brj.resp(-1, "您已登录", null);
            return;
        }

        String username = this.request.getParameter("username") != null ? this.request.getParameter("username").trim() : "";
        String password = this.request.getParameter("password") != null ? this.request.getParameter("password") : "";
        String age = this.request.getParameter("age") != null ? this.request.getParameter("age").trim() : "-1";
        String head = this.request.getParameter("head") != null ? this.request.getParameter("head").trim() : "";
        String gender = this.request.getParameter("gender") != null ? this.request.getParameter("gender").trim() : "-1";

        //System.out.println("Var Username: "+username);
        bub.setUsername(username);
        bub.setPassword(password);
        bub.setAge(Integer.parseInt(age));
        bub.setHead(head);
        bub.setGender(Integer.parseInt(gender));
        if (!bub.validate()) {
            brj.resp(-1, "参数不完整或格式错误", null);
            return;
        } else {
            BUser buser = new BUser();
            if(buser.isExistName(bub.getUsername())){
                brj.resp(-1, "用户名已存在", null);
                return;
            }else if (buser.add(bub)) {
                brj.resp(1, "注册成功", null);
                return;
            } else {
                brj.resp(-1, "注册失败", null);
                return;
            }
        }
    }
}
