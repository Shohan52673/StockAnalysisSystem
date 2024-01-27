package mvc.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bean.SemiProductStock;
import com.example.bean.Watchlist;

import mvc.bean.BuyingList;
import mvc.dao.MainDao;
import mvc.dao.UserDao;
import mvc.entity.User;

@Controller
@RequestMapping("group_buy/fronted")
public class MainController {

	
	@Autowired
	private MainDao mainDao;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/main")
	public String main(HttpSession session, Model model) {
		
		// 1. 先找到 user 登入者
			User user = (User)session.getAttribute("user");
			model.addAttribute("users", user);
			System.out.println("首頁");
		return "group_buy/main";	
	}
	
	@RequestMapping("/stcok")
	public String stock(HttpSession session) {
		// 1. 先找到 user 登入者
					User user = (User)session.getAttribute("user");
						System.out.println("stock頁面");
				return "group_buy/frontend/stockTrading";
	}
	
	@GetMapping("/search")
	public String searchStock(HttpSession session) {
		// 1. 先找到 user 登入者
		User user = (User)session.getAttribute("user");
		System.out.println("searchStock");
		
		return null;
	}
	
	@PostMapping("/stockData")
	@ResponseBody
	public String stockData(@RequestBody BuyingList buyingList, HttpSession session) {
	    try {
	        // 處理資料庫儲存的邏輯
	    	User user = (User)session.getAttribute("user");
	    	buyingList.setUserId(user.getUserId());
	    	System.out.println(buyingList.toString());
	        mainDao.addBuyingList(buyingList);
	        System.out.println("stockData success" + buyingList);
	        return "success"; // 或者返回其他你想要的成功信息
	    } catch (Exception e) {
	        System.out.println("stockData error: " + e.getMessage());
	        e.printStackTrace(); // 輸出例外的詳細信息
	        return "error: " + e.getMessage(); // 返回錯誤信息
	    }
	}
	
	@GetMapping("/portfolio")
	public String portfolio(BuyingList buyingList, Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		model.addAttribute("buyingLists", mainDao.findMyBuyingListByUserId(user.getUserId()));
		return "group_buy/frontend/portfolio";
	}
	
	
	@GetMapping("/watchlist")
	public String watchlist(Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		
		model.addAttribute("watchlist", mainDao.findAllWatchListByUserId(user.getUserId()));
		return "group_buy/frontend/watchlist";
		
	}
	
	@PostMapping("/watchlist")
	@ResponseBody
	public String watchlist(@RequestBody SemiProductStock semiProductStock, Model model, HttpSession session) {
//		System.out.println(semiProductStock.toString());
		User user = (User)session.getAttribute("user");
		try {
			Optional<SemiProductStock> semiOptional = mainDao.findSemiProductStockByStockName(semiProductStock.getStockName());
			if(semiOptional.isPresent()) {
				SemiProductStock semiProductStock2 = semiOptional.get();
				Optional<Watchlist> watchlistOptional = mainDao.findWatchlistByStockName(semiProductStock.getStockName());
				if(watchlistOptional.isPresent()){
					return "加入失敗！！！"+ " " + semiProductStock.getStockName() + "已在自選清單";
				}
				mainDao.addWatchlist(semiProductStock2, user.getUserId());
				System.out.println(semiProductStock2.toString());
			}
//					return "group_buy/main";
		return "成功將" + semiProductStock.getStockName() + "加入自選清單"; // 或者返回其他你想要的成功信息
	    } catch (Exception e) {
	        System.out.println("stockData error: " + e.getMessage());
	        e.printStackTrace(); // 輸出例外的詳細信息
	        return "error: " + e.getMessage(); // 返回錯誤信息
	    }
	}
	
	@GetMapping("/removeOneWatchlist")
	public String removeOneWatchlist(@RequestParam("stockName") String stockName, 
									 HttpSession session) {
		User user = (User) session.getAttribute("user");
	    if (user != null) {
	        boolean success = mainDao.removeOneWatchlistByStockName(stockName, user.getUserId());
	        if (success) {
	            // 操作成功，可以進行相應的處理
	        } else {
	            // 操作失敗，可以進行相應的處理
	        }
	    }
		return "redirect:/mvc/group_buy/fronted/watchlist";
	}

	
	
}
