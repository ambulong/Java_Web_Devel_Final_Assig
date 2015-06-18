/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ambulong
 */
public class BRouter {
    private String module;
    private final ArrayList mods;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    public BRouter(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        mods = new ArrayList();
        mods.add("login");
        mods.add("logout");
        mods.add("getBoards");
        mods.add("getUid");
        mods.add("getUserInfo");
        mods.add("getTips");
        mods.add("getTipDetail");
        mods.add("getReplies");
        mods.add("register");
        mods.add("updateProfile");
        mods.add("updatePassword");
        mods.add("addBoard");
        mods.add("updateBoard");
        mods.add("deleteBoard");
        mods.add("addTip");
        mods.add("updateTip");
        mods.add("deleteTip");
        mods.add("addReply");
        mods.add("deleteReply");
    }
    
    public void init() throws IOException{
        this.module = this.request.getParameter("m") != null?this.request.getParameter("m"):"";
        if(mods.indexOf(this.module) != -1){
            //response.setContentType("text/html;charset=UTF-8");
            //PrintWriter out = response.getWriter();
            //out.println("Module "+ this.module);
        }else{
            this.response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("Module "+ this.module + " is not found");
        }
    }
}
