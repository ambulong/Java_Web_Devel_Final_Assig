package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDAO {
	
	public void addUser(UserInfoBean ui) throws SQLException,Exception{
		Connection conn = null ;
		PreparedStatement ps = null ;
		
		try{
			ConnectDB cdb = new ConnectDB();
			conn = cdb.getConnectDB();
			
			String sql = "insert into userInfo(username,passwd,age) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, ui.getUsername());
			ps.setString(2, ui.getPasswd());
			ps.setInt(3, ui.getAge());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}catch(Exception e){
			throw e;
		}finally{
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}
		
	}
	
	public List<UserInfoBean> findAllUser() throws SQLException,Exception{
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		
		List<UserInfoBean> list = new ArrayList<UserInfoBean>();
		
		try{
			ConnectDB cdb = new ConnectDB();
			conn = cdb.getConnectDB();
			
			String sql = "select * from userInfo" ;
			st = conn.createStatement();
			
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				UserInfoBean uib = new UserInfoBean();
				
				uib.setId(rs.getInt("id"));
				uib.setUsername(rs.getString("username"));
				uib.setPasswd(rs.getString("passwd"));
				uib.setAge(rs.getInt("age"));
				
				list.add(uib);
				
			}
			
			
			
			rs.close();
			st.close();
			conn.close();
		}catch(Exception e){
			throw e;
		}finally{
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(conn != null) conn.close();
		}
		
		return list ;
		
	}
	
	public void updateUser(List<UserInfoBean> lt) throws SQLException,Exception{
		Connection conn = null ;
		PreparedStatement ps = null ;
		
		try{
			ConnectDB cdb = new ConnectDB();
			conn = cdb.getConnectDB();
			
			String sql = "update userInfo set username=?, passwd=?, age=? where id=?";
			
			if(lt != null){
				for(int i = 0 ; i < lt.size(); i++){
					ps = conn.prepareStatement(sql);
					
					ps.setString(1, lt.get(i).getUsername());
					ps.setString(2, lt.get(i).getPasswd());
					ps.setInt(3, lt.get(i).getAge());
					ps.setInt(4, lt.get(i).getId());
					
					ps.executeUpdate();
					
					ps.close();
				}
			}

			conn.close();
		}catch(Exception e){
			throw e;
		}finally{
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}
		
	}
	
	public UserInfoBean findUserById(int id) throws SQLException,Exception{
		Connection conn = null ;
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		
		UserInfoBean ui = new UserInfoBean();
		
		try{
			ConnectDB cdb = new ConnectDB();
			conn = cdb.getConnectDB();
			
			String sql = "select * from userInfo where id=?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				
				ui.setId(rs.getInt("id"));
				ui.setUsername(rs.getString("username"));
				ui.setPasswd(rs.getString("passwd"));
				ui.setAge(rs.getInt("age"));
			}
			
			
			ps.close();
			conn.close();
		}catch(Exception e){
			throw e;
		}finally{
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}
		
		return ui ;
	}
	
	public void deleteUserById(int id) throws SQLException,Exception{
		Connection conn = null ;
		PreparedStatement ps = null ;
		
		try{
			ConnectDB cdb = new ConnectDB();
			conn = cdb.getConnectDB();
			
			String sql = "delete from userInfo where id=?";
			
			
			ps = conn.prepareStatement(sql);
					
			ps.setInt(1, id);
					
			ps.executeUpdate();
					
			ps.close();

			conn.close();
		}catch(Exception e){
			throw e;
		}finally{
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			/*
			//--add user info
			UserInfoDAO uidao = new UserInfoDAO();
			UserInfoBean uib = new UserInfoBean();
			uib.setUsername("gao324");
			uib.setPasswd("gao123");
			uib.setAge(10);
			uidao.addUser(uib);
			*/
			
			/*
			//--find all user
			UserInfoDAO uidao = new UserInfoDAO();
			List<UserInfoBean> list = uidao.findAllUser();
			
			//System.out.println(list.size());
			if(list != null){
				for(int i = 0; i<list.size(); i++){
					UserInfoBean uib = list.get(i);
					System.out.println("id="+uib.getId());
				}
			}*/
			
			/*
			//--find an user info by id
			UserInfoDAO uidao = new UserInfoDAO();
			UserInfoBean uib = uidao.findUserById(3);
			
			System.out.println("id="+uib.getId()+
					"username="+uib.getUsername());
			*/
			
			/*
			UserInfoDAO uidao = new UserInfoDAO();
			List<UserInfoBean> list = new ArrayList<UserInfoBean>();
			UserInfoBean uib = uidao.findUserById(2);
			uib.setUsername("g1");
			list.add(uib);
			
			uib = uidao.findUserById(3);
			uib.setUsername("g2");
			list.add(uib);
			
			uidao.updateUser(list);
			*/
			
			UserInfoDAO uidao = new UserInfoDAO();
			uidao.deleteUserById(2);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
