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

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.Module;

import aweit.mail.GMail;
import mvc.bean.LoginUser;
import mvc.bean.ResetPassword;
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
		System.out.println("refresh圖形");
	    getCodeImage(session, response);
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
		
		System.out.println("驗證碼生成");
	}
	
	// 呈現 login 頁面
	@GetMapping("/login")
	public String index(@ModelAttribute("loginuser") LoginUser loginUser,
						@ModelAttribute("signupuser") SignupUser signupUser,
						@ModelAttribute("resetPassword") ResetPassword resetPassword) {
		System.out.println("登入介面");
		return "group_buy/login";
		
	}
	
	//登入
	@PostMapping("/login")
	public String login(@ModelAttribute("loginuser") @Valid LoginUser loginUser, BindingResult result,
						@ModelAttribute("signupuser") SignupUser signupUser,
						@ModelAttribute("resetPassword") ResetPassword resetPassword,
						HttpSession session, Model model) {
		//表單驗證＠Valid
		if(result.hasErrors()) {
			session.invalidate(); // session 過期失效
			System.out.println("表單驗證＠Valid失敗！");
			return "group_buy/login";
		}
		 //比對驗證碼
		if(!loginUser.getCode().equals(session.getAttribute("code")+"")) {
			session.invalidate(); // session 過期失效
			model.addAttribute("wrongCAPTCHA", "驗證碼錯誤");
			System.out.println("驗證碼錯誤！");
			return "group_buy/login";
		}
		//根據 username 查找 user 物件
		Optional<User> userOptional = userdao.findUserByUsername(loginUser.getUsername());
		if(userOptional.isPresent()) {
			User dbuser = userOptional.get();
			// 比對&驗證 password
			if(BCrypt.checkpw(loginUser.getPassword(),dbuser.getPassword())) {
				session.setAttribute("user", dbuser);// 將 user 物件放入到 session 變數中
				System.out.println("登入成功");
				return "redirect:/mvc/group_buy/fronted/main";
			}else {
				session.invalidate();// session 過期失效
				model.addAttribute("wrongPassword", "密碼錯誤！");
				System.out.println("密碼錯誤！");
				return"group_buy/login";
			}
					
					
//			if(dbuser.getPassword().equals(loginUser.getPassword())) {
//				session.setAttribute("user", dbuser);// 將 user 物件放入到 session 變數中
//				System.out.println("登入成功");
////				return "redirect:/mvc/group_buy/main";// OK, 導向前台首頁
//				return "redirect:/mvc/group_buy/fronted/main";
//			}else {
//				session.invalidate();// session 過期失效
//				model.addAttribute("wrongPassword", "密碼錯誤！");
//				System.out.println("密碼錯誤！");
//				return"group_buy/login";
//			}
		}else {
			session.invalidate();// session 過期失效
			model.addAttribute("wrongUsername", "帳號錯誤！");
			System.out.println("帳號錯誤！");
			return "group_buy/login";
		}
	}
	
	//登出
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/mvc/group_buy/login";
	}
	
	//註冊
	@GetMapping("/signup")
	public String signup(@ModelAttribute("signupuser") SignupUser signupUser,
						 @ModelAttribute("loginuser") LoginUser loginUser,
						 @ModelAttribute("resetPassword") ResetPassword resetPassword,
						 HttpSession session){
		session.invalidate();
		System.out.println("註冊介面");
		return "group_buy/include/signup";
	}
	
	//註冊
	@PostMapping("/signup")
	public String signup(@ModelAttribute("signupuser") @Valid SignupUser signupuser, BindingResult result,
						 @ModelAttribute("loginuser") LoginUser loginUser,
						 @ModelAttribute("resetPassword") ResetPassword resetPassword,
						 @RequestParam("comfirm_password") String comfirm_password,
						 HttpSession session, User user,Model model) {
		// Signup 表單數據驗證
		if(result.hasErrors()) {
			session.invalidate(); // session 過期失效
			return "group_buy/include/signup";
		}
		Optional<User> userOptEmail = userdao.findUserByEmail(signupuser.getEmail());
		if(userOptEmail.isPresent()) {
			// 出現錯誤訊息
			model.addAttribute("signupemail", "信箱已存在");
			session.invalidate(); // session 過期失效
			System.out.println("錯誤，信箱已存在！");
			return "group_buy/include/signup";
		}
		  	Optional<User> userOptName = userdao.findUserByUsername(signupuser.getUsername());
		  	if(userOptName.isPresent()) {
			  model.addAttribute("signupname", "帳號已存在");
			  session.invalidate(); // session 過期失效
			  System.out.println("錯誤，帳號已存在！");
			  return "group_buy/include/signup";
		  	}
		  	if(signupuser.getPassword().equals(comfirm_password)) {
		  		//加密密碼
		  		String hashedPassword = BCrypt.hashpw(signupuser.getPassword(), BCrypt.gensalt());
		  		
		  		
		  				user.setFullname(signupuser.getFullname());
		  				user.setEmail(signupuser.getEmail());	
		  				user.setUsername(signupuser.getUsername());
		  				user.setPassword(hashedPassword);
		  			
		  				int rowcount = userdao.addUser(user);
		  				if(rowcount > 0) {
		  					session.invalidate(); // session 過期失效
		  					System.out.println("註冊成功！");
		  					return "redirect:/mvc/group_buy/success";
		  				}
		  				
		  		}
		  		session.invalidate(); // session 過期失效
		  		model.addAttribute("comfirm_password", "密碼不一致！");
		  		System.out.println("密碼不一致！");
		  		return "group_buy/include/signup";	
		
	}
	
	@GetMapping("/success")
	public String success(HttpSession session) {
		session.invalidate(); // session 過期失效
		System.out.println("註冊成功介面");
		return "group_buy/include/success";
	}
	
	@GetMapping("/resetPassword")
	public String resetPassword(@ModelAttribute("resetPassword") ResetPassword resetPassword,
								@ModelAttribute("loginuser") LoginUser loginUser,
								@ModelAttribute("signupuser") SignupUser signupUser,
								HttpSession session) {
		session.invalidate(); // session 過期失效
		System.out.println("忘記密碼介面");
		return "group_buy/include/resetPassword";
		
	}
	
	@PostMapping("/resetPassword")
	public String resetPassword(@ModelAttribute("resetPassword") @Valid ResetPassword resetPassword, BindingResult result,
								@ModelAttribute("loginuser") LoginUser loginUser,
								@ModelAttribute("signupuser") SignupUser signupUser,
								HttpSession session, Model model) {
		
	// resetPassword 表單數據驗證
		if(result.hasErrors()) {
			session.invalidate(); // session 過期失效
			return "group_buy/include/resetPassword";
		}
			Optional<User> userOptional = userdao.findUserByEmail(resetPassword.getEmail());
			if(userOptional.isPresent()) {
				User dbuser = userOptional.get();
				//比對username
				if(dbuser.getUsername().equals(resetPassword.getUsername())) {
					
					try {
	                    // 使用 Gmail 來發送郵件
	                    GMail mail = new GMail("a832k7025079@gmail.com", "hbdx zmll wbez rgcs");

	                    mail.from("a832k7025079@gmail.com")
	        		    .to(resetPassword.getEmail())
//	                    .to("a832k7025079@gmail.com")
	        		    .cc("a832k7025079@gmail.com")
	        		    .personal("系統")
	        		    .subject("測試信件")
	        		    .context("暫時密碼："+ generateRandomString(8))
//	        		    .attachement(Path.of(GmailDemo.class.getClassLoader().getResource("123.txt").toURI()))
	        		    .send();
	                   
	                    session.invalidate();
	                    System.out.println("信箱已發送");
	                    return"group_buy/login";
	                    
	                } catch (MailException e) {
	                    // 處理郵件發送失敗的情況
	                    e.printStackTrace();
	                }
				}
				
			}
				session.invalidate();
				model.addAttribute("email", "信箱有誤");
				System.out.println("信箱錯誤！");
				return"group_buy/resetPassword";
	}


	// 生成指定長度的亂數字串
	private String generateRandomString(int length) {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    Random random = new Random();
	    StringBuilder sb = new StringBuilder(length);
	    
	    for (int i = 0; i < length; i++) {
	        int index = random.nextInt(characters.length());
	        sb.append(characters.charAt(index));
	    }

	    return sb.toString();
	}

}
