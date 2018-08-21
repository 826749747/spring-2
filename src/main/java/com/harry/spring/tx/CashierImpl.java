package com.harry.spring.tx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cashier")
public class CashierImpl implements Cashier{
	
	@Autowired
	private BookShopService bookShopService;
	
	@Transactional
	public void checkout(String userName,List<String> isbns) {
		for(String isbn:isbns) {
			bookShopService.purchar(userName, isbn);
		}
	}
}
