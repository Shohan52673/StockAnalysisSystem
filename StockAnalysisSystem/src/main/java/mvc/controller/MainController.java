package mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.dao.MainDao;
import mvc.entity.User;

@Controller
@RequestMapping("group_buy/fronted")
public class MainController {

	
	@Autowired
	private MainDao mainDao;
	
	@GetMapping("/main")
	public String main(HttpSession session, Model model) {
		
		// 1. 先找到 user 登入者
			User user = (User)session.getAttribute("user");
				System.out.println("首頁");
		return "group_buy/main";	
	}
	
	@RequestMapping("/stcok")
	public String stock(HttpSession session) {
		// 1. 先找到 user 登入者
					User user = (User)session.getAttribute("user");
						System.out.println("stock頁面");
				return "group_buy/frontend/stockProduct";
	}
	
	@GetMapping("/search")
	public String searchStock(HttpSession session) {
		// 1. 先找到 user 登入者
		User user = (User)session.getAttribute("user");
		System.out.println("searchStock");
		
		return null;
	}
	
}
