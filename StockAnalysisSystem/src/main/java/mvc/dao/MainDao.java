package mvc.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.bean.SemiProductStock;
import com.example.bean.Watchlist;

import mvc.bean.BuyingList;

public interface MainDao {

	 int addBuyingList(BuyingList buyingList);
	 
	 List<BuyingList> findAllBuyingLists();
	 
	 List<BuyingList> findMyBuyingListByUserId(Integer userId);
	 
	 Optional<SemiProductStock> findSemiProductStockByStockName(String stcokName);
	 
	 Optional<Watchlist> findWatchlistByStockName(String stcokName);
	 
//	 Optional<BuyingList> findStockByStockName(String stockName);
//
//	 void updateBuyingList(BuyingList buyingList);
	 
	 int addWatchlist(SemiProductStock semiProductStock, Integer userId);
	 
	 List<Watchlist> findAllWatchListByUserId(Integer userId);
	 
	 //透過股票名稱從自選清單刪除一筆資料
	 Boolean removeOneWatchlistByStockName(String stockName, Integer userId);
	 
	 
}
