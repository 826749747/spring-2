package com.harry.spring.tx.xml;



import org.springframework.jdbc.core.JdbcTemplate;
 


public class BookShopDaoImpl implements BookShopDao{
	
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//根据书号获取书的单价
	public int findBookPriceByIsbn(String Isbn) {
		
		String sql = "Select price from book where Isbn = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, Isbn);
	}
	//mysql不支持检查约束
	//更新书的库存，使书号对应的库存-1
	public void updateBookStock(String Isbn) {
		//检查书的库存是否足够，若不够，则抛出异常
		
		String sql2 = "Select stock from book_stock where isbn = ?";
		int stock = jdbcTemplate.queryForObject(sql2, Integer.class, Isbn);
		if (stock == 0) {
			throw new BookStockException("库存不足！！");
		}
		
		String sql = "Update book_stock set stock = stock - 1 where isbn = ?";
		jdbcTemplate.update(sql,Isbn);
	}
	
	//更新用户的账户余额：是用户的blance-price
	public void updateUserAccount(String userName , int price) {
		//验证余额是否足够，若不够，抛出异常
		String sql2 = "Select blance from account where userName = ?";
		int blance = jdbcTemplate.queryForObject(sql2, Integer.class, userName);
		if (blance < price) {
			throw new UserAccountException("余额不足");
		}
		String sql = "Update account set blance = blance - ? where userName = ?";
		jdbcTemplate.update(sql, price,userName);
	} 
}
