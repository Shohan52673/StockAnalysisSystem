package mvc.controller;


import java.net.URISyntaxException;
import java.nio.file.Path;

import aweit.mail.GMail;

/**
 * 應用程式密碼：Google 帳戶 -> 安全性 -> 兩步驟驗證 -> 應用程式密碼 -> 輸入「send gmail」取得 Token (例如：rkrb vuai biti ssyf)
 */


public class SendEmailController {

	public static void main(String[] args) throws URISyntaxException {
		//gwxr jjuf snsi duev
		GMail mail = new GMail("a832k7025079@gmail.com", "gwxr jjuf snsi duev");
		
		mail.from("a832k7025079@gmail.com")
		    .to("a832k7025079@gmail.com")
		    .cc("")
		    .personal("柯林")
		    .subject("測試信件")
		    .context("223")
//		    .attachement(Path.of(EmailSender.class.getClassLoader().getResource("123.txt").toURI()))
		    .send();

	}

}
