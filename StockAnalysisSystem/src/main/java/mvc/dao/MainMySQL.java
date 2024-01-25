package mvc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mvc.bean.BuyingList;

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
		String sql = "select stockName, quantity, price from stockanalysissystem.BuyingList";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BuyingList.class));
	}

	@Override
	public List<BuyingList> findMyBuyingListByUserId(Integer userId) {
		String sql = "select stockName, quantity, price from stockanalysissystem.BuyingList where user_Id = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BuyingList.class), userId);
	}


	@Override
	public Optional<BuyingList> findStockByStockName(String stockName) {
		String sql = "SELECT stockName, SUM(quantity), SUM(price) FROM stockanalysissystem.BuyingList WHERE user_id = 101 AND stockName = ? GROUP BY stockName";
		try {
			BuyingList buyingList = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BuyingList.class), stockName);
			return Optional.ofNullable(buyingList);
		}catch (Exception e) {
			return Optional.empty();
		}
	}


	@Override
	public void updateBuyingList(BuyingList buyingList) {
		String sql = "update stockanalysissystem.BuyingList set quantity = ? where stockName = ?";
        jdbcTemplate.update(sql, buyingList.getQuantity(), buyingList.getStockName());
		
	}
	
	
	

	
}