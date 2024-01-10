package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.bean.SemiProductStock;
import com.example.bean.StockSymbol;

public interface SemiProductStockDao {

	public final String NEXT_SEQ_SQL = "SELECT NEXTVAL('semi_product_stock_trx_seq')";
	
	int getNextSeq();
	
//	int[] save(List<SemiProductStock> semiProductStocks);
	
	List<SemiProductStock> findLatestSemiProductStock();

	int[] save(List<SemiProductStock> semiProductStocks);
	
//	List<StockQueryUtil> findLatestStockQueryUtil();
	
	List<StockSymbol> findAllStockSymbolFromDatabase();
}