package mvc.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.Session;

import mvc.bean.User;
import mvc.dao.UserDao;

@Controller
@RequestMapping("group_buy")
public class UserController {
	
	
	@Autowired
	private UserDao userdao;
	
	
	@GetMapping("/login")
	public String index() {
		return "group_buy/mainUnlogin";
		
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password,
						HttpSession session, Model model) {
		//根據 username 查找 user 物件
		Optional<User> userOptional = userdao.findUserByUsername(username);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			// 比對 password
			if(user.getPassword().equals(password)) {
				session.setAttribute("user", user);// 將 user 物件放入到 session 變數中
				return "group_buy/mainLogin";// OK, 導向前台首頁
			}else {
				session.invalidate();// session 過期失效
				model.addAttribute("wrongPassword", "密碼錯誤！");
				return"group_buy/mainUnlogin";
			}
		}else {
			session.invalidate();// session 過期失效
			model.addAttribute("wrongUsername", "帳號錯誤！");
			return "group_buy/mainUnlogin";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/mvc/group_buy/login";
	}
	
	@GetMapping("/register")
	public String unRegister(HttpSession session) {
		session.invalidate();
		return"group_buy/include/register";
	}
	
//	@PostMapping("/register")
//	public String register()
		
		
		
		
		

		
	

}
