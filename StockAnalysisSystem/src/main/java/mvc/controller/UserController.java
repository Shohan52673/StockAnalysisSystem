package mvc.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
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
	
	@GetMapping("/refreshCode")
	private void refreshCode(HttpSession session, HttpServletResponse response) throws IOException {
	    // Call the existing code to generate a new code and image
	    getCodeImage(session, response);
	}
	
	
	@GetMapping("/getcode")
	private void getCodeImage(HttpSession session, HttpServletResponse response) throws IOException {
		// 產生一個驗證碼 code
		Random random = new Random();
		String code1 = String.format("%c", (char)(random.nextInt(122-65+1) + 65));
		String code2 = String.format("%c", (char)(random.nextInt(122-65+1) + 65));
		String code3 = String.format("%c", (char)(random.nextInt(122-65+1) + 65));
		String code4 = String.format("%c", (char)(random.nextInt(122-65+1) + 65));
		
		String code  = code1+code2+code3+code4;
		// 將驗證碼轉換為小寫
	    code = code.toLowerCase();
	    
		session.setAttribute("code", code);
		
		// Java 2D 產生圖檔
		// 1. 建立圖像暫存區
		BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_INT_BGR);
		// 2. 建立畫布
		Graphics g = img.getGraphics();
		// 3. 設定顏色
		g.setColor(Color.YELLOW);
		// 4. 塗滿背景
		g.fillRect(0, 0, 80, 30);
		// 5. 設定顏色
		g.setColor(Color.BLACK);
		// 6. 設定自型
		g.setFont(new Font("新細明體", Font.PLAIN, 30));
		// 7. 繪字串
		g.drawString(code, 10, 23); // code, x, y
		// 8. 干擾線
		g.setColor(Color.RED);
		for(int i=0;i<10;i++) {
			int x1 = random.nextInt(80);
			int y1 = random.nextInt(30);
			int x2 = random.nextInt(80);
			int y2 = random.nextInt(30);
			g.drawLine(x1, y1, x2, y2);
		}
		
		// 設定回應類型
		response.setContentType("image/png");
		
		// 將影像串流回寫給 client
		ImageIO.write(img, "PNG", response.getOutputStream());
	}
	
	
	@GetMapping("/login")
	public String index() {
		return "group_buy/login";
		
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password,
						@RequestParam("code") String code,
						HttpSession session, Model model) {
		// 比對驗證碼
		if(!code.equals(session.getAttribute("code")+"")) {
			session.invalidate(); // session 過期失效
			model.addAttribute("wrongCAPTCHA", "驗證碼錯誤");
			return "group_buy/login";
		}
		//根據 username 查找 user 物件
		Optional<User> userOptional = userdao.findUserByUsername(username);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			// 比對 password
			if(user.getPassword().equals(password)) {
				session.setAttribute("user", user);// 將 user 物件放入到 session 變數中
				return "group_buy/main";// OK, 導向前台首頁
			}else {
				session.invalidate();// session 過期失效
				model.addAttribute("wrongPassword", "密碼錯誤！");
				return"group_buy/login";
			}
		}else {
			session.invalidate();// session 過期失效
			model.addAttribute("wrongUsername", "帳號錯誤！");
			return "group_buy/login";
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
