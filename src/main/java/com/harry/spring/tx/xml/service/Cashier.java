package com.harry.spring.tx.xml.service;

import java.util.List;

public interface Cashier {
	
	public void checkout(String userName,List<String> isbns);
}
