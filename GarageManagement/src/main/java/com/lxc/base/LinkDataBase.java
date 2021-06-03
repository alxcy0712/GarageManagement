package com.lxc.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lxc.entity.Garage;

public class LinkDataBase {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public List<Garage> getList(){
		String sql = "select *from garage";
		List <Garage> list = new ArrayList<>();
		try {
			conn = DataBase.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String carId = rs.getString("car_id");
				int style = rs.getInt("style");
				long timeStart = rs.getLong("time_start");
				long timeEnd = rs.getLong("time_end");
				int location = rs.getInt("location");
				list.add(new Garage(id,carId,style,timeStart,timeEnd,location));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DataBase.CloseAll(conn, stmt, rs);
		}
	}
	
	
	/**
	 * 通过carId车牌号查询所有信息
	 * @param carId
	 * @return
	 */
	public List<Garage> getListByCarId(String carId,int pn,int rn){
		String sql = "select *from garage where car_id like ? limit ?,?";
		List <Garage> list = new ArrayList<>();
		try {
			conn = DataBase.getConn();
			stmt = conn.prepareStatement(sql);
			if(carId.trim().equals("")) {
				carId = "%";
			}else {
				// 分割字符串carId为单一字符
				String newCarId = "%";
				for(char i=0;i<carId.length();i++) {
		            newCarId = newCarId+carId.charAt(i)+"%";
		        }
				carId = newCarId;
			}
			stmt.setString(1,carId);
			stmt.setInt(2, (pn-1)*rn);
			stmt.setInt(3, rn);
			rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String carId1 = rs.getString("car_id");
				int style = rs.getInt("style");
				long timeStart = rs.getLong("time_start");
				long timeEnd = rs.getLong("time_end");
				int location =rs.getInt("location");
				list.add(new Garage(id,carId1,style,timeStart,timeEnd,location));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DataBase.CloseAll(conn, stmt, rs);
		}
	}
	
	
	public int addCarInfoIntoGarage(String [] carInfo){
		String sql = "insert into garage (car_id,style,time_start,location) values (? , ? , ? ,?)";
		String sqlLocation = "select *from garage where location = ? and time_end is null";
		try {
			conn = DataBase.getConn();
			
			if(carInfo.length == 0)
				return 300;
			//信息输入不全 返回错误信息
			for(int i=0;i<carInfo.length;i++) {
				if(carInfo[i]==null)
					return i+300;
			}
			
			// 车位已被占用 返回2
			stmt = conn.prepareStatement(sqlLocation);
			int location = Integer.parseInt(carInfo[2]);
			stmt.setInt(1, location);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return 2;
			}
			
			
			
			// 一切正常 向数据库中添加信息
			stmt = conn.prepareStatement(sql);
			String carId = carInfo[0];
			int style = Integer.parseInt(carInfo[1]);
			long timeStart = System.currentTimeMillis();
			stmt.setString(1, carId);
			stmt.setInt(2, style);
			stmt.setLong(3,timeStart);
			stmt.setInt(4, location);
			stmt.execute();
			return 1;		//成功
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
			
}
	public int count() {
		String sql = "select count(*) num from garage";
		try {
			conn = DataBase.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("num");
			}else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			DataBase.CloseAll(conn, stmt, rs);
		}
	}
	
	
	public int delete(int mid) {
		String sql = "delete from garage where id = ?";
		
		try {
			conn = DataBase.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mid);
			int num = stmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			DataBase.CloseAll(conn, stmt, rs);
		}
	}
	
	public List<Garage> find(int id){
		String sql = "select *from garage where id = ?";
		try {
			conn = DataBase.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			List<Garage> list  = new ArrayList<>();
			while(rs.next()) {
				String carId = rs.getString("car_id");
				int style = rs.getInt("style");
				long timeStart = rs.getLong("time_start");
				long timeEnd = rs.getLong("time_end");
				int location = rs.getInt("location");
				list.add(new Garage(id,carId,style,timeStart,timeEnd,location));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DataBase.CloseAll(conn, stmt, rs);
		}
	}
	
	
	public int pay(int id,long timeEnd) {
		String sql = "UPDATE garage SET time_end = ? WHERE id = ?;";
		try {
			conn = DataBase.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, timeEnd);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			DataBase.CloseAll(conn, stmt, rs);
		}
		
	}
		
}

	

		
