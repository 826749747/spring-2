package com.harry.spring.tx.xml.service.impl;




import com.harry.spring.tx.xml.BookShopDao;
import com.harry.spring.tx.xml.service.BookShopService;


public class BookShopServiceImpl implements BookShopService{
	
	/**
	 * 自动装载，就自己实例化了，ioc容器帮忙实例化
	 * 
	 * */
	
	private BookShopDao bookShopDao;
	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	//添加事务注解
	//1.使用propagation 指定事务的传播行为，即当前的事务方法被另外一个事务方法调用时     -->传播行为
	//如何使用事务，默认取值为REQUIRED 即使用调用方法的事务
	//REQUIRES_NEW:使用自己的事务，调用的事务方法的事务被挂起
	//2.使用isolation 指定事务的隔离级别，最常用的取值为READ_COMMITTED		-->隔离级别
	//3.默认情况下spring的声明式事务对所有的运行时异常进行回滚，也可以通过对应的属性进行设置,noRollbackFor= {UserAccountException.class}   -->回滚属性
	//4.使用readOnly 指定事务是否为只读。表示这个事务只读取数据但不更新数据，这样可以帮助数据库引擎优化事务。若真的是一个只读取数据库值的方法，应设置readOnly =true   --》只读属性
	//5.使用timeout指定强制回滚之前事务可以占用的事件	-->timeout 属性
	//@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED,readOnly=false,timeout = 3)
	public void purchar(String userName,String Isbn) {
		
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//1.根据书号获得单价
		int price = bookShopDao.findBookPriceByIsbn(Isbn);
		
		//2.更新库存
		bookShopDao.updateBookStock(Isbn);
		
		//3.更新用户
		bookShopDao.updateUserAccount(userName, price);
	}
}
