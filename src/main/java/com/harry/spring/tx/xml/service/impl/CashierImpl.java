package com.harry.spring.tx.xml.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harry.spring.tx.xml.service.BookShopService;
import com.harry.spring.tx.xml.service.Cashier;


public class CashierImpl implements Cashier{
	
	
	private BookShopService bookShopService;
	
	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}
	
	
	public void checkout(String userName,List<String> isbns) {
		for(String isbn:isbns) {
			bookShopService.purchar(userName, isbn);
		}
	}
}
