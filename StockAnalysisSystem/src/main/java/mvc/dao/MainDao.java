package mvc.dao;

import java.util.List;
import java.util.Optional;

import mvc.bean.BuyingList;

public interface MainDao {

	 int addBuyingList(BuyingList buyingList);
	 
	 List<BuyingList> findAllBuyingLists();
	 
	 List<BuyingList> findMyBuyingListByUserId(Integer userId);
	 
	 Optional<BuyingList> findStockByStockName(String stockName);

	 void updateBuyingList(BuyingList buyingList);
}
