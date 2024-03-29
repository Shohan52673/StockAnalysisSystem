package com.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.bean.SemiProductStock;
import com.example.dao.SemiProductStockDao;
import com.example.jsoup.SemiProductStockUtil;
import com.example.jsoup.StockQueryUtil;

public class ScheduleMain {

	public static void main(String[] args) throws SQLException, IOException, InterruptedException {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/resources/beans.xml");
		
//		SemiProductStockUtil semiProductStockUtil = ctx.getBean("semiProductStockUtil", SemiProductStockUtil.class);
//		
//		SemiProductStockDao semiProductStockDao = ctx.getBean("semiProductStockDaoMySQL",SemiProductStockDao.class);
//		
//		List<SemiProductStock> semiProductStocks = semiProductStockUtil.getSemiProductStocks();
//		
//		semiProductStockDao.save(semiProductStocks);
		
		
		StockQueryUtil stockQueryUtil = ctx.getBean("stockQueryUtil", StockQueryUtil.class);
	
		SemiProductStockDao stockQueryUtilDao = ctx.getBean("semiProductStockDaoMySQL", SemiProductStockDao.class);
			
		List<SemiProductStock> stockQueryUtils = stockQueryUtil.getStockQueryUtil();
					
		stockQueryUtilDao.save(stockQueryUtils);
		
//		 通知股票服務，目的為：讓股票服務知道有更新股票資訊，請他廣播給所有 WebSocket的用戶。
//		RestTemplate restTemplate = new RestTemplate();
//		String url = "http://localhost:8080/WebSocketDemo/mvc/stock";
//		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//		System.out.println(response);
	}

}