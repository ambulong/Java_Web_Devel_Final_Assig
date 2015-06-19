/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.BConnectDB;
import util.BFunctions;

/**
 *
 * @author Ambulong
 */
public class BReply {

    private Connection conn;

    public BReply() throws Exception {
        try {
            BConnectDB cdb = new BConnectDB();
            this.conn = cdb.getConnectDB();
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean isExistID(int id) throws Exception {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select * from reply where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.last();
            if (rs.getRow() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean add(BReplyBean brb) throws Exception {
        try {
            if (!brb.validate()) {
                return false;
            }

            PreparedStatement ps = null;
            String sql = "insert into reply(`title`, `content`, `pubtime`, `uid`, `tid`, `realfile`, `makefile`) values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, brb.getTitle());
            ps.setString(2, brb.getContent());
            ps.setString(3, BFunctions.getDatetime());
            ps.setInt(4, brb.getUid());
            ps.setInt(5, brb.getTid());
            ps.setString(6, brb.getRealfile());
            ps.setString(7, brb.getMakefile());

            int result = ps.executeUpdate();
            ps.close();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean update(BReplyBean brb) throws Exception {
        try {
            if (!this.isExistID(brb.getId())) {
                return false;
            }
            if (!brb.validate()) {
                return false;
            }

            PreparedStatement ps = null;
            String sql = "update reply set `title` = ?, `content` = ?, `pubtime` = ?, `uid` = ?, `tid` = ?, `realfile` = ?, `makefile` = ? where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, brb.getTitle());
            ps.setString(2, brb.getContent());
            ps.setString(3, BFunctions.getDatetime());
            ps.setInt(4, brb.getUid());
            ps.setInt(5, brb.getTid());
            ps.setString(6, brb.getRealfile());
            ps.setString(7, brb.getMakefile());
            ps.setInt(8, brb.getId());

            int result = ps.executeUpdate();
            ps.close();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean delete(int id) throws Exception {
        try {
            if (!this.isExistID(id)) {
                return false;
            }

            PreparedStatement ps = null;
            String sql = "delete from reply where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            int result = ps.executeUpdate();
            ps.close();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String getTitle(int id) throws Exception {
        try {
            if (!this.isExistID(id)) {
                return "";
            }
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select * from reply where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("title");
        } catch (Exception e) {
            throw e;
        }
    }

    public String getContent(int id) throws Exception {
        try {
            if (!this.isExistID(id)) {
                return "";
            }
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select * from reply where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("content");
        } catch (Exception e) {
            throw e;
        }
    }

    public BReplyBean getDetail(int id) throws Exception {
        try {
            if (!this.isExistID(id)) {
                return null;
            }
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select * from reply where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();

            BReplyBean brb = new BReplyBean();
            brb.setTid(rs.getInt("tid"));
            brb.setContent(rs.getString("content"));
            brb.setId(rs.getInt("id"));
            brb.setMakefile(rs.getString("makefile"));
            brb.setPubtime(rs.getString("pubtime"));
            brb.setRealfile(rs.getString("realfile"));
            brb.setTitle(rs.getString("title"));
            brb.setUid(rs.getInt("uid"));

            return brb;
        } catch (Exception e) {
            throw e;
        }
    }

    public BReplyBean[] getReplyList(int tid) throws Exception {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            String sql = "select * from reply where `bid` = ? order by `id` desc";
            ps.setInt(1, tid);
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            BReplyBean[] brbs = null;

            int i = 0;
            if (rs.next()) {
                brbs[i].setTid(rs.getInt("tid"));
                brbs[i].setContent(rs.getString("content"));
                brbs[i].setId(rs.getInt("id"));
                brbs[i].setMakefile(rs.getString("makefile"));
                brbs[i].setPubtime(rs.getString("pubtime"));
                brbs[i].setRealfile(rs.getString("realfile"));
                brbs[i].setTitle(rs.getString("title"));
                brbs[i].setUid(rs.getInt("uid"));
                i++;
            }

            return brbs;
        } catch (Exception e) {
            throw e;
        }
    }
}
