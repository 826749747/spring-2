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

	public girl(Integer id, int age, String cup_size) {
		super();
		this.id = id;
		this.age = age;
		this.cup_size = cup_size;
	}
	
	
}
