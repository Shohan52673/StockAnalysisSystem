package mvc.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.bean.SemiProductStock;
import com.example.bean.Watchlist;

import mvc.bean.BuyingList;
import mvc.entity.User;

@Repository
public class MainMySQL implements MainDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public int addBuyingList(BuyingList buyingList) {
		String sql = "insert into StockAnalysisSystem.BuyingList(stockName, quantity, price, user_id) values(?,?,?,?)";
		return jdbcTemplate.update(sql, buyingList.getStockName(), buyingList.getQuantity(), buyingList.getPrice(), buyingList.getUserId());
		
	}


	@Override
	public List<BuyingList> findAllBuyingLists() {
		String sql = "select stockName, quantity, price from stockanalysissystem.BuyingList where user_id = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BuyingList.class));
	}

	@Override
	public List<BuyingList> findMyBuyingListByUserId(Integer userId) {
		String sql = "SELECT stockName, SUM(quantity) quantity, SUM(price) price FROM stockanalysissystem.BuyingList WHERE user_id = ? group by stockName";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BuyingList.class), userId);
	}


	@Override
	public Optional<SemiProductStock> findSemiProductStockByStockName(String stcokName) {
		String sql = "SELECT stockName, stockNumber, price, openingPrice, highPrice, lowPrice, averagePrice, transactionAmountBillion, "
				+ "previousClosingPrice, changePercentage, changeP, totalVolume, previousVolume, amplitude from StockAnalysisSystem.SemiProductStock WHERE stockName = ? order by createDate desc limit 1"; 
		try {
			SemiProductStock semiProductStock = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(SemiProductStock.class),stcokName);
			return Optional.ofNullable(semiProductStock);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}


	@Override
	public int addWatchlist(SemiProductStock semiProductStock, Integer userId) {
		String sql = "insert into StockAnalysisSystem.Watchlist(user_id, stockName, stockNumber, price, openingPrice, highPrice, lowPrice, averagePrice, transactionAmountBillion,"
				+ "previousClosingPrice, changePercentage, changeP, totalVolume, previousVolume, amplitude) values(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,userId, semiProductStock.getStockName(), semiProductStock.getStockNumber(), semiProductStock.getPrice(), semiProductStock.getOpeningPrice(), semiProductStock.getHighPrice(), semiProductStock.getLowPrice(), semiProductStock.getAveragePrice(), semiProductStock.getTransactionAmountBillion(), semiProductStock.getPreviousClosingPrice(), semiProductStock.getChangePercentage(), semiProductStock.getChangeP(), semiProductStock.getTotalVolume(), semiProductStock.getPreviousVolume(), semiProductStock.getAmplitude());
	}


	@Override
	public List<Watchlist> findAllWatchListByUserId(Integer userId) {
		String sql = "select stockName, stockNumber, price, openingPrice, highPrice, lowPrice, averagePrice, transactionAmountBillion, "
				+ "previousClosingPrice, changePercentage, changeP, totalVolume, previousVolume, amplitude from StockAnalysisSystem.watchlist where user_id = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Watchlist.class),userId);
	}

	//透過股票名稱從自選清單刪除一筆資料
	@Override
	public Boolean removeOneWatchlistByStockName(String stockName, Integer userId) {
	    String sql = "DELETE FROM StockAnalysisSystem.watchlist WHERE stockName = ? AND user_id = ?";
	    return jdbcTemplate.update(sql, stockName, userId) == 1;
	}



	@Override
	public Optional<Watchlist> findWatchlistByStockName(String stockName) {
	    String sql = "SELECT stockName, stockNumber, price, openingPrice, highPrice, lowPrice, averagePrice, transactionAmountBillion, "
	            + "previousClosingPrice, changePercentage, changeP, totalVolume, previousVolume, amplitude from StockAnalysisSystem.watchlist where stockName = ?";
	    try {
	        Watchlist watchlist = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Watchlist.class), stockName);
	        return Optional.ofNullable(watchlist);
	    } catch (EmptyResultDataAccessException e) {
	        return Optional.empty();
	    }
	}




	


//	@Override
//	public Optional<BuyingList> findStockByStockName(String stockName) {
//		String sql = "SELECT stockName, SUM(quantity), SUM(price) FROM stockanalysissystem.BuyingList WHERE user_id = ? AND stockName = ? GROUP BY stockName";
//		try {
//			BuyingList buyingList = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BuyingList.class), stockName);
//			return Optional.ofNullable(buyingList);
//		}catch (Exception e) {
//			return Optional.empty();
//		}
//	}
//
//
//	@Override
//	public void updateBuyingList(BuyingList buyingList) {
//		String sql = "update stockanalysissystem.BuyingList set quantity = ? where stockName = ?";
//        jdbcTemplate.update(sql, buyingList.getQuantity(), buyingList.getStockName());
//		
//	}
	
	
	

	
}