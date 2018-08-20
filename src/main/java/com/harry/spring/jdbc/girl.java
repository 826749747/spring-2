package com.harry.spring.jdbc;

public class girl {
	private Integer id;
	private int age;
	private String cup_size;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCup_size() {
		return cup_size;
	}

	public void setCup_size(String cup_size) {
		this.cup_size = cup_size;
	}
	
	/*
	 * 无参的构造器是必须的，不然在对象转换的时候回报错
	 * */
	
	public girl() {
		super();
	}

	public girl(Integer id, int age, String cup_size) {
		super();
		this.id = id;
		this.age = age;
		this.cup_size = cup_size;
	}

	@Override
	public String toString() {
		return "girl [id=" + id + ", age=" + age + ", cup_size=" + cup_size + "]";
	}
	
	
}
