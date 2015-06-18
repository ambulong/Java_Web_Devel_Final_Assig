/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ambulong
 */
public class BFunctions {

    public static String getDatetime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static boolean chkToken(HttpServletRequest request) {
        String token = request.getParameter("token") != null ? request.getParameter("token").trim() : "";
        if (token.equals("")) {
            return false;
        }
        BSession bsession = new BSession(request);
        if (token.equals(bsession.getToken())) {
            return true;
        } else {
            return false;
        }
    }
}
