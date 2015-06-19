/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.BBoard;
import lib.BReply;
import lib.BReplyBean;
import lib.BTip;
import lib.BTipBean;
import util.BFunctions;
import util.BRespJson;
import util.BSession;

/**
 *
 * @author Ambulong
 */
public class AddReply {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public AddReply(HttpServletRequest request, HttpServletResponse response) {
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

        BTip bt = new BTip();
        
        int tid = this.request.getParameter("tid") != null ? Integer.parseInt(this.request.getParameter("tid").trim()) : 0;
        String title = this.request.getParameter("title") != null ? this.request.getParameter("title").trim() : "";
        String content = this.request.getParameter("content") != null ? this.request.getParameter("content").trim() : "";
        String realfile = this.request.getParameter("realfile") != null ? this.request.getParameter("realfile").trim() : "";
        String makefile = this.request.getParameter("makefile") != null ? this.request.getParameter("makefile").trim() : "";
        
        if (bt.isExistID(tid)) {
            brj.resp(-1, "帖子不存在", null);
            return;
        }

        BReplyBean brb = new BReplyBean();
        brb.setContent(content);
        brb.setMakefile(makefile);
        brb.setRealfile(realfile);
        brb.setTid(tid);
        brb.setTitle(title);
        brb.setUid(bs.getUid());
        
        if(brb.validate()){
            brj.resp(-1, "数据错误", null);
            return;
        }
        
        BReply br = new BReply();
        
        if (br.add(brb)) {
            brj.resp(1, "添加成功", null);
        } else {
            brj.resp(-1, "添加失败", null);
        }
        return;
    }
}
