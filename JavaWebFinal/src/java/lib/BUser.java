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
public class BUser {

    public BUser() throws Exception {
    }

    public boolean isExistID(int id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            ResultSet rs = null;
            String sql = "select * from user where `id` = ?";
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

    public boolean isExistName(String str) throws Exception {
        str = str.trim();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            ResultSet rs = null;
            String sql = "select * from user where `username` = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, str);
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

    public boolean add(BUserBean bub) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!bub.validate()) {
                return false;
            }
            if (this.isExistName(bub.getUsername())) {
                return false;
            }
            String sql = "insert into user(`username`, `password`, `age`, `regtime`, `head`, `gender`, `flag`) values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, bub.getUsername());
            String hash = BPasswordHash.createHash(bub.getPassword());
            ps.setString(2, hash);
            ps.setInt(3, bub.getAge());
            ps.setString(4, BFunctions.getDatetime());
            ps.setString(5, bub.getHead());
            ps.setInt(6, bub.getGender());
            ps.setInt(7, bub.getFlag());

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

    public boolean chkLogin(String usr, String pwd) throws Exception {
        usr = usr.trim();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistName(usr)) {
                return false;
            }
            int id = this.getID(usr);
            String correctHash = this.getHash(id);
            BPasswordHash ph = new BPasswordHash();
            return ph.validatePassword(pwd, correctHash);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean chkLogin(int uid, String pwd) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(uid)) {
                return false;
            }
            String correctHash = this.getHash(uid);
            BPasswordHash ph = new BPasswordHash();
            return ph.validatePassword(pwd, correctHash);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean updateProfile(BUserBean bub) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(bub.getId())) {
                return false;
            }
            if (!bub.validateProfile()) {
                return false;
            }
            String sql = "update user set `age` = ?, `head` = ?, `gender` = ? where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, bub.getAge());
            ps.setString(2, bub.getHead());
            ps.setInt(3, bub.getGender());
            ps.setInt(4, bub.getId());

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

    public boolean updatePassword(int uid, String pwd) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(uid)) {
                return false;
            }
            if (pwd.length() <= 6) {
                return false;
            }
            String sql = "update user set `password` = ? where `id` = ?";
            ps = conn.prepareStatement(sql);

            String hash = BPasswordHash.createHash(pwd);
            ps.setString(1, hash);
            ps.setInt(2, uid);

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

    public int getID(String usr) throws Exception {
        usr = usr.trim();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistName(usr)) {
                return 0;
            }
            ResultSet rs = null;
            String sql = "select * from user where `username` = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, usr);
            rs = ps.executeQuery();
            rs.first();
            return rs.getInt("id");
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

    public int getFlag(int uid) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(uid)) {
                return 0;
            }
            ResultSet rs = null;
            String sql = "select * from user where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, uid);
            rs = ps.executeQuery();
            rs.first();
            return rs.getInt("flag");
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

    public boolean isAdmin(int uid) throws Exception {
        if (this.getFlag(uid) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String getHash(int uid) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(uid)) {
                return "";
            }
            ResultSet rs = null;
            String sql = "select * from user where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, uid);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("password");
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

    public String getHead(int uid) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(uid)) {
                return "";
            }
            ResultSet rs = null;
            String sql = "select * from user where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, uid);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("head");
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

    public String getUsername(int uid) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(uid)) {
                return "";
            }
            ResultSet rs = null;
            String sql = "select * from user where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, uid);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("username");
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

    public BUserBean getDetail(int uid) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            BConnectDB cdb = new BConnectDB();
            conn = cdb.getConnectDB();
            if (!this.isExistID(uid)) {
                return null;
            }
            BUserBean bub = new BUserBean();
            ResultSet rs = null;
            String sql = "select * from user where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, uid);
            rs = ps.executeQuery();
            rs.first();
            bub.setAge(rs.getInt("age"));
            bub.setFlag(rs.getInt("flag"));
            bub.setGender(rs.getInt("gender"));
            bub.setHead(rs.getString("head"));
            bub.setId(rs.getInt("id"));
            bub.setRegtime(rs.getString("regtime"));
            bub.setUsername(rs.getString("username"));
            return bub;
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
