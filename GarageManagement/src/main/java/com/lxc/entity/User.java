package com.lxc.entity;

public class User {
	private	 	int 		id;			//ID
	private 	String 		password;	//密码
	private 	String 		firstname;	//名
	private 	String 		lastname;	//姓
	private 	long 		birthday;	//生日
	private 	long 		phone;		//手机号
	
	public User() {
		
	}
	public User(int id,String password,String firstname,String lastname,long birthday,long phone) {
		this.setId(id);
		this.setPassword(password);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setBirthday(birthday);
		this.setPhone(phone);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public long getBirthday() {
		return birthday;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
}
