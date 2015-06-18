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
    
    public Register(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
    
    public void init() throws IOException{
        BRespJson brj = new BRespJson(request, response);
        BUserBean bub = new BUserBean();
        
        String username = this.request.getParameter("username") != null?this.request.getParameter("username").trim():"";
        String password = this.request.getParameter("password") != null?this.request.getParameter("password"):"";
        String age = this.request.getParameter("age") != null?this.request.getParameter("age").trim():"-1";
        String head = this.request.getParameter("head") != null?this.request.getParameter("head").trim():"";
        String gender = this.request.getParameter("gender") != null?this.request.getParameter("gender").trim():"-1";
        
        bub.setUsername(username);
        bub.setPassword(password);
        bub.setAge(Integer.parseInt(age));
        bub.setHead(head);
        bub.setGender(Integer.parseInt(gender));
        if(!bub.validate()){
            brj.resp(0, "参数不完整或格式错误", null);
        }else{
            
        }
    }
}
