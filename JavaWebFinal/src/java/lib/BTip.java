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
public class BTip {

    public BTip() throws Exception {

    }

    public boolean isExistID(int id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            ResultSet rs = null;
            String sql = "select * from tip where `id` = ?";
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
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean add(BTipBean btb) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!btb.validate()) {
                return false;
            }

            String sql = "insert into tip(`title`, `content`, `pubtime`, `uid`, `bid`, `realfile`, `makefile`) values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, btb.getTitle());
            ps.setString(2, btb.getContent());
            ps.setString(3, BFunctions.getDatetime());
            ps.setInt(4, btb.getUid());
            ps.setInt(5, btb.getBid());
            ps.setString(6, btb.getRealfile());
            ps.setString(7, btb.getMakefile());

            int result = ps.executeUpdate();
            ps.close();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean update(BTipBean btb) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(btb.getId())) {
                return false;
            }
            if (!btb.validate()) {
                return false;
            }

            String sql = "update tip set `title` = ?, `content` = ?, `pubtime` = ?, `uid` = ?, `bid` = ?, `realfile` = ?, `makefile` = ? where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, btb.getTitle());
            ps.setString(2, btb.getContent());
            ps.setString(3, BFunctions.getDatetime());
            ps.setInt(4, btb.getUid());
            ps.setInt(5, btb.getBid());
            ps.setString(6, btb.getRealfile());
            ps.setString(7, btb.getMakefile());
            ps.setInt(8, btb.getId());

            int result = ps.executeUpdate();
            ps.close();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        } finally {
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

            String sql = "delete from tip where `id` = ?";
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
        } finally {
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
            String sql = "select * from tip where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("title");
        } catch (Exception e) {
            throw e;
        } finally {
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
            String sql = "select * from tip where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("content");
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public BTipBean getDetail(int id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(id)) {
                return null;
            }
            ResultSet rs = null;
            String sql = "select * from tip where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();

            BTipBean btb = new BTipBean();
            btb.setBid(rs.getInt("bid"));
            btb.setContent(rs.getString("content"));
            btb.setId(rs.getInt("id"));
            btb.setMakefile(rs.getString("makefile"));
            btb.setPubtime(rs.getString("pubtime"));
            btb.setRealfile(rs.getString("realfile"));
            btb.setTitle(rs.getString("title"));
            btb.setUid(rs.getInt("uid"));

            return btb;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<BTipBean> getTipList(int bid) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            ResultSet rs = null;
            if (bid == 0) {
                String sql = "select * from tip order by `id` desc";
                ps = conn.prepareStatement(sql);
            } else {
                String sql = "select * from tip where `bid` = ? order by `id` desc";

                ps = conn.prepareStatement(sql);

                ps.setInt(1, bid);
            }
            rs = ps.executeQuery();

            List<BTipBean> list = new ArrayList<BTipBean>();

            while (rs.next()) {
                BTipBean btb = new BTipBean();
                btb.setBid(rs.getInt("bid"));
                btb.setContent(rs.getString("content"));
                btb.setId(rs.getInt("id"));
                btb.setMakefile(rs.getString("makefile"));
                btb.setPubtime(rs.getString("pubtime"));
                btb.setRealfile(rs.getString("realfile"));
                btb.setTitle(rs.getString("title"));
                btb.setUid(rs.getInt("uid"));

                list.add(btb);
            }

            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
