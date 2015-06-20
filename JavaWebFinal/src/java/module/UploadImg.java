/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lib.BBoard;
import lib.BTip;
import lib.BTipBean;
import util.BConfig;
import util.BFunctions;
import util.BRespJson;
import util.BSession;

/**
 *
 * @author Ambulong
 */
public class UploadImg {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final String path;

    public UploadImg(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        this.request = request;
        this.response = response;
        BConfig bc = new BConfig();
        this.path = path+bc.getImgUri();
    }

    public void init() throws IOException, Exception {
        BRespJson brj = new BRespJson(request, response);
        BSession bs = new BSession(request);

        if (!BFunctions.chkToken(this.request)) {
            brj.resp(-1, "Invalid Token", null);
            return;
        }
        
        
        Part part = request.getPart("image");
        String filename = getFileName(part);
        String filetype = filename.substring(filename.lastIndexOf("."));
        String makename = BFunctions.getRandomString(44)+filetype;

        if(!filetype.equals(".jpg") && !filetype.equals(".png")){
            brj.resp(-1, "图片格式错误。(只支持.jpg和.png格式图片)", null);
            return;
        }
        this.writeTo(path+"/"+makename, part);

        System.out.println(path+"/"+filename);
        System.out.println(path+"/"+makename);
        
        List<Map> dataList = new ArrayList<Map>();
        Map<String, String> map = new<String, String> HashMap();  
        map.put( "filename", makename);
        dataList.add(map);
        brj.resp(1, "", dataList);
        
        return;
    }
    
    private void writeTo(String fileName, Part part) throws IOException, FileNotFoundException {
        InputStream in = part.getInputStream();
        OutputStream out = new FileOutputStream(fileName);
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
 
        in.close();
        out.close();
    }
    
    private String getFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        String fileName = header.substring(header.indexOf("filename=\"") + 10,
                header.lastIndexOf("\""));
        return fileName;
    }
}
