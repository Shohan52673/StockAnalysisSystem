package mvc.controller;

import java.util.List;
import java.util.Optional;

import javax.lang.model.element.ModuleElement;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mvc.bean.BuyingList;
import mvc.dao.MainDao;
import mvc.dao.UserDao;
import mvc.entity.User;
import netscape.javascript.JSObject;

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

	
	
}
