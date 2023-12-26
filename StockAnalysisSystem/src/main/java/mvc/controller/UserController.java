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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.bean.LoginUser;
import mvc.bean.SignupUser;
import mvc.dao.UserDao;
import mvc.entity.User;

@Controller
@RequestMapping("group_buy")
public class UserController {
	
	
	@Autowired
	private UserDao userdao;
	
	@GetMapping("/refreshCode")
	private void refreshCode(HttpSession session, HttpServletResponse response) throws IOException {
	    // Call the existing code to generate a new code and image
	    getCodeImage(session, response);
	    System.out.println("refresh圖形");
	}
	
	
	@GetMapping("/getcode")
	private void getCodeImage(HttpSession session, HttpServletResponse response) throws IOException {
		// 產生一個驗證碼 code
		Random random = new Random();
		String code1 = String.format("%c", (char)(random.nextInt(90-65+1) + 65));
		String code2 = String.format("%c", (char)(random.nextInt(90-65+1) + 65));
		String code3 = String.format("%c", (char)(random.nextInt(90-65+1) + 65));
		String code4 = String.format("%c", (char)(random.nextInt(90-65+1) + 65));
		
		String code  = code1+code2+code3+code4;
		// 將驗證碼轉換為小寫
	    code = code.toLowerCase();
	    
		session.setAttribute("code", code);
		
		// Java 2D 產生圖檔
		// 1. 建立圖像暫存區
		BufferedImage img = new BufferedImage(90, 40, BufferedImage.TYPE_INT_BGR);
		// 2. 建立畫布
		Graphics g = img.getGraphics();
		// 3. 設定顏色
		g.setColor(Color.YELLOW);
		// 4. 塗滿背景
		g.fillRect(0, 0, 90, 40);
		// 5. 設定顏色
		g.setColor(Color.BLACK);
		// 6. 設定自型
		g.setFont(new Font("新細明體", Font.PLAIN, 33));
		// 7. 繪字串
		g.drawString(code, 6, 33); // code, x, y
		// 8. 干擾線
		g.setColor(Color.RED);
		for(int i=0;i<10;i++) {
			int x1 = random.nextInt(90);
			int y1 = random.nextInt(40);
			int x2 = random.nextInt(90);
			int y2 = random.nextInt(40);
			g.drawLine(x1, y1, x2, y2);
		}
		
		// 設定回應類型
		response.setContentType("image/png");
		
		// 將影像串流回寫給 client
		ImageIO.write(img, "PNG", response.getOutputStream());
		
		System.out.println("圖形生成");
	}
	
	// 呈現 login 頁面
	@GetMapping("/login")
	public String index(@ModelAttribute("signupuser") SignupUser signupUser,
						@ModelAttribute("loginuser") LoginUser loginUser) {
		System.out.println("登入介面");
		return "group_buy/login";
		
	}
	
	//登入
	@PostMapping("/login")
	public String login(@ModelAttribute("signupuser") SignupUser signupUser,
						@ModelAttribute("loginuser") @Valid LoginUser loginUser, BindingResult result,
						HttpSession session, Model model) {
		//表單驗證＠Valid
		if(result.hasErrors()) {
			System.out.println("表單驗證＠Valid失敗！");
			return "group_buy/login";
		}
		// 比對驗證碼
//		if(!code.equals(session.getAttribute("code")+"")) {
//			session.invalidate(); // session 過期失效
//			model.addAttribute("wrongCAPTCHA", "驗證碼錯誤");
//			System.out.println("驗證碼錯誤！");
//			return "group_buy/login";
//		}
		//根據 username 查找 user 物件
		Optional<User> userOptional = userdao.findUserByUsername(signupUser.getPassword());
		if(userOptional.isPresent()) {
			User dbuser = userOptional.get();
			// 比對 password
			if(dbuser.getPassword().equals(loginUser.getPassword())) {
				session.setAttribute("user", dbuser);// 將 user 物件放入到 session 變數中
				System.out.println("登入成功");
//				return "redirect:/mvc/group_buy/main";// OK, 導向前台首頁
				return "group_buy/main";
			}else {
				session.invalidate();// session 過期失效
				model.addAttribute("wrongPassword", "密碼錯誤！");
				System.out.println("密碼錯誤！");
				return"group_buy/login";
			}
		}else {
			session.invalidate();// session 過期失效
			model.addAttribute("wrongUsername", "帳號錯誤！");
			System.out.println("帳號錯誤！");
			return "group_buy/login";
		}
	}
	
	//登出
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/mvc/group_buy/login";
	}
	
	//註冊
//	@PostMapping("/signup")
//	public String signup( 
//						 @Valid SignupUser user, BindingResult result,
//						 @RequestParam("comfirm_password") String comfirm_password,
//						 HttpSession session,
//						 Model model) {
//		
//		if(result.hasErrors()) {
//			return "group_buy/login";
//		}
//		Optional<SignupUser> userOpt = userdao.findUserByUsername(username);
//		
//		userdao.addUser(user);
//		System.out.println("User added: ");
//		return "redirect:/mvc/group_buy/login";
//	}
	

		
		
		
		
		

		
	

}
