package com.dt.mybatis.bean;

public class Employee {
	private Integer id;
	private String lastName;//这里设置一个不同于数据库中的名字，用于在后面的数据库查询中修改属性名
	private String email;
	private String gender;//为这四个变量添加get,set以及toString函数
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + "]";
	}
	
}

