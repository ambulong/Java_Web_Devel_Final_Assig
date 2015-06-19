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
public class BBoard {
    private Connection conn;
    
    public BBoard() throws Exception {
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
            String sql = "select * from board where `id` = ?";
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
    
    public boolean add(BBoardBean bbb) throws Exception {
        try {
            if (!bbb.validate()) {
                return false;
            }
            
            PreparedStatement ps = null;
            String sql = "insert into board(`name`, `pid`) values(?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, bbb.getName());
            ps.setInt(2, bbb.getPid());

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
    
    public boolean update(BBoardBean bbb) throws Exception {
        try {
            if (!bbb.validate()) {
                return false;
            }
            
            PreparedStatement ps = null;
            String sql = "update board set `name` = ?, `pid` = ? where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, bbb.getName());
            ps.setInt(2, bbb.getPid());
            ps.setInt(3, bbb.getId());

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
            String sql = "delete from board where `id` = ?";
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
    
    public boolean hasChildren(int id) throws Exception {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select * from board where `pid` = ?";
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
    
    public String getName(int id) throws Exception {
        try {
            if (!this.isExistID(id)) {
                return "";
            }
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select * from board where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("name");
        } catch (Exception e) {
            throw e;
        }
    }
    
    public BBoardBean getDetail(int id) throws Exception {
        try {
            if (!this.isExistID(id)) {
                return null;
            }
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select * from board where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            
            BBoardBean bbb = new BBoardBean();
            bbb.setId(rs.getInt("id"));
            bbb.setName(rs.getString("name"));
            bbb.setPid(rs.getInt("pid"));
            ps.close();
            
            return bbb;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public BBoardBean[] getChildren(int id) throws Exception {
        try {
            if (!this.isExistID(id)) {
                return null;
            }
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select * from board where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            BBoardBean[] bbbs = null;
            
            int i=0;
            if(rs.next()){
                bbbs[i].setId(rs.getInt("id"));
                bbbs[i].setName(rs.getString("name"));
                bbbs[i].setPid(rs.getInt("pid"));
                i++;
            }
            
            return bbbs;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public BBoardBean[] getBoardList() throws Exception {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select * from board";
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            BBoardBean[] bbbs = null;
            
            int i=0;
            if(rs.next()){
                bbbs[i].setId(rs.getInt("id"));
                bbbs[i].setName(rs.getString("name"));
                bbbs[i].setPid(rs.getInt("pid"));
                i++;
            }
            
            return bbbs;
        } catch (Exception e) {
            throw e;
        }
    }
}
