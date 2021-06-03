package com.lxc.entity;

import java.util.Date;
/**
 * 
 * 关于Garage类的补充说明
 * 	style:（及相关收费信息）
 * 		  1.小型车		5元/小时
 * 		  2.中型车		7元/小时
 * 		  3.大型车		9元/小时
 * 		  4.跑车			13元/小时
 * 		  5.其他类		7元/小时
 *  收费相关信息：
 *  	  停放时间小于等于30分钟 		   收费为该车型一小时收费的一半
 *  	  停放时间大于30分钟不满60分钟     收费按该车型一小时的收费
 *  	  停放时间大于60分钟			   收费按小时整数倍收取 剩余时间收费按前两条计算
 *  
 * 
 * @author liuxiaochen
 *
 */
public class Garage {
	private    int        id;						// 自增 车库记录的记录编号
	private    String	  carId;					// 车牌号
	private    int 		  style;					// 车型
	private    long 	  timeStart;			// 起始时间
	private    long   	  timeEnd;					// 结束时间
	private    int 		  location;					// 停放位置
	
	public Garage(){
		
	}
	public Garage(int id,String carId,int style,long timeStart,long timeEnd,int location) {
		this.setId(id);
		this.setCarId(carId);
		this.setStyle(style);
		this.setTimeStart(timeStart);
		this.setTimeEnd(timeEnd);
		this.setLocation(location);
	}
	
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public long getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}
	public long getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(long timeEnd) {
		this.timeEnd = timeEnd;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
