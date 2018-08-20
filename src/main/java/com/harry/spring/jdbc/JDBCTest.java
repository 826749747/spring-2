package com.harry.spring.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;



public class JDBCTest {
	
	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate =(JdbcTemplate)ctx.getBean("jdbcTemplate");
	}
	
	/**
	 * 批量操作
	 * */
//	@Test
//	public void testBatchUpdate() {
//		String sql = "Insert into girl(id,age,cup_size) values(?,?,?)";
//		
//		List<Object[]> batchArgs = new ArrayList<Object[]>();
//		batchArgs.add(new Object[] {"4","18","A"});
//		batchArgs.add(new Object[] {"5","18","B"});
//		batchArgs.add(new Object[] {"6","18","C"});
//		batchArgs.add(new Object[] {"7","18","D"});
//		batchArgs.add(new Object[] {"8","18","E"});
//		jdbcTemplate.batchUpdate(sql, batchArgs);
//	}
	
	/**
	 * 获取单个列的值，或做统计查询
	 * */
	@Test
	public void testQueryForObject2() {
		String sql = "Select count(id) from girl ";
		long count = jdbcTemplate.queryForObject(sql,Long.class);
		System.out.println(count);
	}
	
	/**
	 * 查到实体类的集合
	 * */
	@Test
	public void testQueryForList() {
		
		String sql = "SELECT * from girl where age >= ?";
		RowMapper<girl> rowMapper = new BeanPropertyRowMapper<girl>(girl.class);
		List<girl> girls = jdbcTemplate.query(sql, rowMapper,18);
		System.out.println(girls);
	}
	/*
	 * 从数据库获取数据库获取记录，实际得到一个对象
	 * 
	 * 1.其中的RowMapper指定如何去映射结果集的行，常用的实现类为BeanPropertyRowMapper
	 * 2.使用SQL中的列名完成列名和类的属性名的映射。 例如last_name lastName
	 * 3.不支持级联属性。jdbcTemplate 到底是一个JDBC的小工具，而不是ORM框架
	 * */
	@Test
	public void testQueryForObject() {
		String sql = "Select * from girl Where id = ?";
		
		RowMapper<girl> rowMapper = new BeanPropertyRowMapper<girl>(girl.class);		//ROWMAPPER用于查询结果和实际对象的转换
		girl gg = jdbcTemplate.queryForObject(sql, rowMapper,1);
		
		System.out.println(gg);
	}
	@Test
	public void testUpdate() {
		String sql = "UPDATE girl SET cup_size = ? where id = ?";
		jdbcTemplate.update(sql,"Z",3);
	}
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource =ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
}
