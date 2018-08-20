package com.harry.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GirlDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public girl get(Integer id) {
		String sql = "Select * from girl Where id = ?";
		
		RowMapper<girl> rowMapper = new BeanPropertyRowMapper<girl>(girl.class);		//ROWMAPPER用于查询结果和实际对象的转换
		girl gg = jdbcTemplate.queryForObject(sql, rowMapper,id);
		return gg;
	}
}
 