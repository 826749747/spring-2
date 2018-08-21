package com.harry.spring.tx.xml;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.harry.spring.tx.xml.service.BookShopService;
import com.harry.spring.tx.xml.service.Cashier;

public class SpringTransactionTest {
	
	private ApplicationContext ctx = null;
	BookShopDao bookShopDao = null;
	BookShopService bookShopService = null;
	private Cashier cashier = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext-tx-xml.xml");
		bookShopDao = ctx.getBean(BookShopDao.class);
		bookShopService = ctx.getBean(BookShopService.class);
		cashier = ctx.getBean(Cashier.class);
	}
	
	@Test
	public void testTransactionalPropagation() {
		
		cashier.checkout("AA",Arrays.asList("1001","1002"));
	}
	
	@Test
	public void testBookShopService() {
		bookShopService.purchar("AA", "1001");
	}
	
	
}
