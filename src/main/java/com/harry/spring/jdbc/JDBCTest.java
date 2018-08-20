package com.harry.spring.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;



public class JDBCTest {
	
	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate =(JdbcTemplate)ctx.getBean("jdbcTemplate");
		namedParameterJdbcTemplate =ctx.getBean(NamedParameterJdbcTemplate.class);
	}
	
	/**
	 * 使用具名参数时。可以使用BeanPropertySqlParameterSource(Object object)方法进行更新操作
	 * 1.sql语句中的参数名和类的属性一致
	 * 2.使用SqlParameterSource的BeanPropertySqlParameterSource实现作为参数
	 * */
	@Test
	public void testNameParamerJdbcTemplate2() {
		String sql = "Insert into girl values(:id,:age,:cup_size)";
		
		girl ggGirl = new girl();
		ggGirl.setId(11);
		ggGirl.setAge(22);
		ggGirl.setCup_size("F");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(ggGirl);
		namedParameterJdbcTemplate.update(sql, paramSource);	
	}

	/**
	 * NamedParameterJdbcTemplate
	 * 可以为参数起名字
	 * 1.好处：若有多个参数，则不用再去对应位置，直接对应参数名字，便于维护
	 * 2.缺点：较为麻烦。
	 * */
//	@Test
//	public void testNameParamerJdbcTemplate() {
//		String sql = "Insert into girl values(:id,:age,:cup_size)";
//		
//		Map<String , Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", 9);
//		paramMap.put("age", 30);
//		paramMap.put("cup_size","G");
//		namedParameterJdbcTemplate.update(sql, paramMap);
//	}
//	
	
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
	 * 使用queryForObject(sql,Class)
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
