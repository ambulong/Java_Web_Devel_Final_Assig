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
public class BTip {

    private Connection conn;

    public BTip() throws Exception {
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
        }
    }

    public boolean add(BTipBean btb) throws Exception {
        try {
            if (!btb.validate()) {
                return false;
            }

            PreparedStatement ps = null;
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
        }
    }

    public boolean update(BTipBean btb) throws Exception {
        try {
            if (!this.isExistID(btb.getId())) {
                return false;
            }
            if (!btb.validate()) {
                return false;
            }

            PreparedStatement ps = null;
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
        }
    }
    
    public boolean delete(int id) throws Exception {
        try {
            if (!this.isExistID(id)) {
                return false;
            }
            
            PreparedStatement ps = null;
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
        }
    }
    
    public String getTitle(int id) throws Exception {
        try {
            if (!this.isExistID(id)) {
                return "";
            }
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select * from tip where `id` = ?";
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
            String sql = "select * from tip where `id` = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            return rs.getString("content");
        } catch (Exception e) {
            throw e;
        }
    }
    
    public BTipBean getDetail(int id) throws Exception {
        try {
            if (!this.isExistID(id)) {
                return null;
            }
            PreparedStatement ps = null;
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
        }
    }
    
    public BTipBean[] getTipList(int bid) throws Exception {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            if(bid == 0){
                String sql = "select * from tip order by `id` desc";
                ps = conn.prepareStatement(sql);
            }else{
                String sql = "select * from tip where `bid` = ? order by `id` desc";
                ps.setInt(1, bid);
                ps = conn.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            
            BTipBean[] btbs = null;
            
            int i=0;
            if(rs.next()){
                btbs[i].setBid(rs.getInt("bid"));
                btbs[i].setContent(rs.getString("content"));
                btbs[i].setId(rs.getInt("id"));
                btbs[i].setMakefile(rs.getString("makefile"));
                btbs[i].setPubtime(rs.getString("pubtime"));
                btbs[i].setRealfile(rs.getString("realfile"));
                btbs[i].setTitle(rs.getString("title"));
                btbs[i].setUid(rs.getInt("uid"));
                i++;
            }
            
            return btbs;
        } catch (Exception e) {
            throw e;
        }
    }
}
