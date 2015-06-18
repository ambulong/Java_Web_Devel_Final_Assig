/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Ambulong
 */
public class BRespJson {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public BRespJson(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void resp(int status, String msg, List<Map> list) throws IOException {
            JSONObject jo = new JSONObject();
            JSONArray ja = JSONArray.fromObject(list);
            jo.put("status", status);
            jo.put("msg", msg);
            jo.put("data", ja);
            String json = jo.toString();
            this.response.setContentType("text/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
    }

}
