/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.BConnectDB;
import util.BFunctions;

/**
 *
 * @author Ambulong
 */
public class BReply {
public BReply() throws Exception {
        
    }

    public boolean isExistID(int id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
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
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean add(BReplyBean brb) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!brb.validate()) {
                return false;
            }

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
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean update(BReplyBean brb) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(brb.getId())) {
                return false;
            }
            if (!brb.validate()) {
                return false;
            }

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
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean delete(int id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(id)) {
                return false;
            }

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
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String getTitle(int id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(id)) {
                return "";
            }
            ResultSet rs = null;
            String sql = "select * from reply where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("title");
        } catch (Exception e) {
            throw e;
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String getContent(int id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(id)) {
                return "";
            }
            ResultSet rs = null;
            String sql = "select * from reply where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("content");
        } catch (Exception e) {
            throw e;
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public BReplyBean getDetail(int id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(id)) {
                return null;
            }
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
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<BReplyBean> getReplyList(int tid) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            ResultSet rs = null;

            String sql = "select * from reply where `tid` = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tid);
            
            rs = ps.executeQuery();
            
            List<BReplyBean> list = new ArrayList<BReplyBean>();
            
            while (rs.next()) {
                BReplyBean brb = new BReplyBean();
                brb.setTid(rs.getInt("tid"));
                brb.setContent(rs.getString("content"));
                brb.setId(rs.getInt("id"));
                brb.setMakefile(rs.getString("makefile"));
                brb.setPubtime(rs.getString("pubtime"));
                brb.setRealfile(rs.getString("realfile"));
                brb.setTitle(rs.getString("title"));
                brb.setUid(rs.getInt("uid"));
                list.add(brb);
            }

            return list;
        } catch (Exception e) {
            throw e;
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
