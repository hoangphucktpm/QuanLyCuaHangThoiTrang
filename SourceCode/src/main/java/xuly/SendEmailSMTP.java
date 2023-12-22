package xuly;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import GUI.FrmQuenMatKhau;

public class SendEmailSMTP {
//	pass rogz dvjl asuy saic
//	email: smileshopptit@gmail.com
	static final String fromMail = "smileshopptit@gmail.com";
	static final String passMail = "rogzdvjlasuysaic";

	
//	TLS
//	Đăng nhập vào hệ thống
	public static void main(String[] args) {
		
		
		
	}
	public static boolean sendMail(String mailTo, String userName, String newPW)
	{
			
	//		Properties: các thuộc tính
			
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP 
			props.put("mail.smtp.port", "587");	// TLS 587
			props.put("mail.smtp.auth", "true");//Phải đăng nhập vào host??
			props.put("mail.smtp.starttls.enable", "true");
			
	//		tạo Authenticator
			Authenticator auth = new Authenticator() {
	
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(fromMail, passMail);
				}
				
			};
			
	//		Phiên làm việc
			Session session = Session.getInstance(props, auth);
			
			
	//		Gửi email
	//		Tạo tin nhắn mới
			MimeMessage mess = new MimeMessage(session);
			try {
				mess.addHeader("Cấp mật khẩu mới", "text/HTML; charset = UTF-8");
				mess.setFrom(fromMail); //Người gửi
				mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo, false)); //Người nhận
				
	//			Tiêu đề email
				mess.setSubject("Cấp mật khẩu mới");
	//			Quy định ngày gửi
				mess.setSentDate(new Date());
	//			Nội dung mail
				 mess.setContent(" <div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\r\n"
				 		+ "                      <div style=\"margin:50px auto;width:70%;padding:20px 0\">\r\n"
				 		+ "                      <div style=\"border-bottom:1px solid #eee\">\r\n"
				 		+ "                         <a href=\"#\" style=\"font-size:1.4em;color: #fc9055;text-decoration:none;font-weight:600\">SmileShop</a>\r\n"
				 		+ "                     </div>\r\n"
				 		+ "                     <p style=\"font-size:1.1em\">Đã thay đổi mật khẩu cho ứng dụng quản lý cửa hàng thời trang,</p>\r\n"
				 		+ "                     <p>Tài khoản: "+ userName +"</p>\r\n"
				 		+ "                     <p>Mật khẩu mới: " + newPW +"</p>\r\n"
				 		+ "                     <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">\r\n"
				 		+ "                         <p>SmileShop</p>\r\n"
				 		+ "                         <p>12 Nguyễn Văn Bảo - Phường 4 - Gò Vấp - Thành phố Hồ Chí Minh</p>\r\n"
				 		+ "                         <p>Việt Nam</p>\r\n"
				 		+ "                     </div>\r\n"
				 		+ "                     </div>\r\n"
				 		+ "                     </div>", "text/html; charset=utf-8");
				
				Transport.send(mess);
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
	}
}