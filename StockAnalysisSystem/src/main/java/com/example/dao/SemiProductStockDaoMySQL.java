package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.bean.SemiProductStock;
import com.example.bean.StockSymbol;

import mvc.entity.User;

@Component("semiProductStockDaoMySQL")
public class SemiProductStockDaoMySQL implements SemiProductStockDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public int[] save(List<SemiProductStock> semiProductStocks) {

		int trxId = getNextSeq();
		
		String sql = "INSERT INTO SemiProductStock(trx_id, stockName, stockNumber, price, openingPrice, highPrice, lowPrice, averagePrice, transactionAmountBillion, previousClosingPrice, changePercentage, changeP, totalVolume, previousVolume, amplitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		BatchPreparedStatementSetter bps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				SemiProductStock semiProductStock = semiProductStocks.get(i);
			    ps.setInt(1, trxId);
			    ps.setString(2, semiProductStock.getStockName());
			    ps.setString(3, semiProductStock.getStockNumber());
			    ps.setString(4, semiProductStock.getPrice());
			    ps.setString(5, semiProductStock.getOpeningPrice());
			    ps.setString(6, semiProductStock.getHighPrice());
			    ps.setString(7, semiProductStock.getLowPrice());
			    ps.setString(8, semiProductStock.getAveragePrice());
			    ps.setString(9, semiProductStock.getTransactionAmountBillion());
			    ps.setString(10, semiProductStock.getPreviousClosingPrice());
			    ps.setString(11, semiProductStock.getChangePercentage());
			    ps.setString(12, semiProductStock.getChangeP());
			    ps.setString(13, semiProductStock.getTotalVolume());
			    ps.setString(14, semiProductStock.getPreviousVolume());
			    ps.setString(15, semiProductStock.getAmplitude());
			}

			@Override
			public int getBatchSize() {
				return semiProductStocks.size();
			}
			
		};
		
		return jdbcTemplate.batchUpdate(sql, bps);

	}

	@Override
	public int getNextSeq() {
		return jdbcTemplate.queryForObject(NEXT_SEQ_SQL, Integer.class);
	}

	@Override
	public List<SemiProductStock> findLatestSemiProductStock() {
		String sql = "select v.trx_id, v.stockName, v.stockNumber, v.price, v.openingPrice, v.highPrice, v.lowPrice, v.averagePrice, v.transactionAmountBillion, v.previousClosingPrice, v.changePercentage, v.changeP, v.totalVolume, v.previousVolume, v.amplitude, v.createDate from ( "
				+ "	SELECT trx_id, stockName, stockNumber, price, openingPrice, highPrice, lowPrice, averagePrice, transactionAmountBillion, previousClosingPrice, changePercentage, changeP, totalVolume, previousVolume, amplitude, createDate, "
				+ "	RANK() OVER ( ORDER BY trx_id desc) as rk  "
				+ "	FROM StockAnalysisSystem.SemiProductStock "
				+ ") v where v.rk = 1 order by v.price desc";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(SemiProductStock.class));
	}

	@Override
	public List<StockSymbol> findAllStockSymbolFromDatabase() {
		String sql ="select stockSymbol from StockAnalysisSystem.Symbol";
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(StockSymbol.class));
	}

//	@Override
//	public Optional<StockSymbol> getStockSymbolFromDatabase(String stockSymbol) {
//        String sql = "SELECT stockSymbol, stockName FROM stockanalysissystem.Symbol WHERE stockSymbol = ?";
//        try {
//        	StockSymbol StockCode = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(StockSymbol.class), stockSymbol);
//	
//	        return Optional.ofNullable(StockCode);
//        }catch (EmptyResultDataAccessException e) {
//			return Optional.empty();
//		}
//    }



}