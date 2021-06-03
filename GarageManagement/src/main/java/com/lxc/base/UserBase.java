package com.lxc.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lxc.entity.User;

public class UserBase {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public User getUserById(String id){
		String sql = "select * from user where id=?";
		
		try {
			conn = DataBase.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				int uid= rs.getInt("id");
				String upwd = rs.getString("password");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				long birthday = rs.getLong("birthday");
				long phone = rs.getLong("phone");
				User u = new User(uid, upwd, firstname,lastname,birthday,phone);
				return u;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			DataBase.CloseAll(conn, stmt, rs);
		}
	}
}
