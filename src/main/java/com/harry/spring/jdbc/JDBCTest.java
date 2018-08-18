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
	@Test
	public void testBatchUpdate() {
		String sql = "Insert into girl(id,age,cup_size) values(?,?,?)";
		
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(new Object[] {"4","18","A"});
		batchArgs.add(new Object[] {"5","18","B"});
		batchArgs.add(new Object[] {"6","18","C"});
		batchArgs.add(new Object[] {"7","18","D"});
		batchArgs.add(new Object[] {"8","18","E"});
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	
	/*
	 * 从数据库获取数据库获取记录，实际得到一个对象
	 * */
	@Test
	public void testQueryForObject() {
		String sql = "Select * from girl Where id = ?";
		
		RowMapper<girl> rowMapper = new BeanPropertyRowMapper<girl>(girl.class);
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
