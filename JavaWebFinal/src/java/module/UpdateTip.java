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
import lib.BTip;
import lib.BTipBean;
import util.BFunctions;
import util.BRespJson;
import util.BSession;

/**
 *
 * @author Ambulong
 */
public class UpdateTip {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public UpdateTip(HttpServletRequest request, HttpServletResponse response) {
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

        BBoard bb = new BBoard();
        BTip bt = new BTip();
        
        int id = this.request.getParameter("id") != null ? Integer.parseInt(this.request.getParameter("id").trim()) : 0;
        int bid = this.request.getParameter("bid") != null ? Integer.parseInt(this.request.getParameter("bid").trim()) : 0;
        String title = this.request.getParameter("title") != null ? this.request.getParameter("title").trim() : "";
        String content = this.request.getParameter("content") != null ? this.request.getParameter("content").trim() : "";
        String realfile = this.request.getParameter("realfile") != null ? this.request.getParameter("realfile").trim() : "";
        String makefile = this.request.getParameter("makefile") != null ? this.request.getParameter("makefile").trim() : "";
        
        if(bt.getDetail(id).getUid() != bs.getUid() && !bs.isAdmin()){
            brj.resp(-1, "没有权限", null);
            return;
        }
        if(!bt.isExistID(id)){
            brj.resp(-1, "帖子不存在", null);
            return;
        }
        if (!bb.isExistID(bid)) {
            brj.resp(-1, "板块不存在", null);
            return;
        }

        BTipBean btb = new BTipBean();
        btb.setId(id);
        btb.setBid(bid);
        btb.setContent(content);
        btb.setMakefile(makefile);
        btb.setRealfile(realfile);
        btb.setTitle(title);
        btb.setUid(bs.getUid());
        
        if(!btb.validate()){
            brj.resp(-1, "数据错误", null);
            return;
        }

        if (bt.update(btb)) {
            brj.resp(1, "修改成功", null);
        } else {
            brj.resp(-1, "修改失败", null);
        }
        return;
    }
}
