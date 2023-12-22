package mvc.test;


import java.net.URISyntaxException;
import java.nio.file.Path;

import aweit.mail.GMail;

/**
 * 應用程式密碼：Google 帳戶 -> 安全性 -> 兩步驟驗證 -> 應用程式密碼 -> 輸入「send gmail」取得 Token (例如：rkrb vuai biti ssyf)
 */
public class GmailDemo2 {

	public static void main(String[] args) throws URISyntaxException {
		
		GMail mail = new GMail("jjdk@gmail.com", "wodk wfov fkfl dnfk");
		
		mail.from("jjdk@gmail.com")
		    .to("goodjc@gmail.com")
		    .cc("")
		    .personal("林")
		    .subject("測試信件")
		    .context("223")
//		    .attachement(Path.of(EmailSender.class.getClassLoader().getResource("123.txt").toURI()))
		    .send();

	}

}
