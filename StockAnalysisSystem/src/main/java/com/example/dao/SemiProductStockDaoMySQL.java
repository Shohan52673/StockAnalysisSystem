package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.bean.SemiProductStock;

@Component("semiProductStockDaoImpl")
public class SemiProductStockDaoMySQL implements SemiProductStockDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public int[] save(List<SemiProductStock> semiProductStocks) {

		int trxId = getNextSeq();
		
		String sql = "INSERT INTO SemiProductStock(trx_id, stockName,stockNumber,price,priceChange,trading) VALUES (?, ?, ?, ?, ?, ?)";
		
		BatchPreparedStatementSetter bps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				SemiProductStock semiProductStock = semiProductStocks.get(i);
			    ps.setInt(1, trxId);
			    ps.setString(2, semiProductStock.getStockName());
			    ps.setString(3, semiProductStock.getStockNumber());
			    ps.setString(4, semiProductStock.getPrice());
			    ps.setString(5, semiProductStock.getPriceChange());
			    ps.setString(6, semiProductStock.getTrading());
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
		String sql = "select v.trx_id, v.stockName, v.stockNumber, v.price, v.priceChange, v.trading, v.createDate from ( "
				+ "	SELECT trx_id, stockName, stockNumber, price, priceChange, trading, createDate, "
				+ "	RANK() OVER ( ORDER BY trx_id desc) as rk  "
				+ "	FROM StockAnalysisSystem.SemiProductStock "
				+ ") v where v.rk = 1 order by v.price desc";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(SemiProductStock.class));
	}

}