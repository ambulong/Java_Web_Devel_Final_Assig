/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ambulong
 */
public class BSession {

    private HttpSession session;

    public BSession(HttpServletRequest request) {
        this.session = request.getSession();
    }

    public void init() {
        if (session.isNew()) {
            String sid = session.getId();
            session.setAttribute("token", BFunctions.getRandomString(50));
            session.setAttribute("uid", 0);
        }
    }

    public int getUid() {
        try {
            return (int) session.getAttribute("uid");
        } catch (Exception e) {
            return 0;
        }
    }

    public void setUid(int id) {
        session.setAttribute("uid", id);
    }

    public String getToken() {
        try {
            return (String) session.getAttribute("token");
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLogin() {
        if (this.getUid() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public void destroy(){
        session.setAttribute("uid", 0);
        session.invalidate();
    }
}
