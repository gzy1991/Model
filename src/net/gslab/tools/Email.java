package net.gslab.tools;

import java.util.Random;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;


public class Email {
	
	
	public static void sendMessage(String to,String title,String msg) {    
		//发送邮件
		//三个参数分别是收件人，主题，正文
		MultiPartEmail email = new MultiPartEmail();  
		// 这里是发送服务器的名字：  
		email.setHostName("smtp.qq.com");  
		// 编码集的设置 
		// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
		
		email.setCharset("utf-8");  
		               
		try {
			// 收件人的邮箱  
			email.addTo(to);
			email.setFrom("2424493572@qq.com");  
		
			email.setAuthentication("2424493572@qq.com", "I131201053");  
			//设置主题
			email.setSubject(title);  
			// 要发送的信息 
			email.setContent(msg, "text/html;charset=UTF-8");
			email.setMsg(msg);  

			// 发送  
			email.send();  
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		// 发送人的邮箱  

        //测试
		//sendMessage("1522780701@qq.com","test", "<html><body><a href='www.baidu.com'>asdf</a></body></html>");
	} 
	
	//生成6位不重复String类验证码，
	public static String verification_code ()
	{
		//生成6位 不重复随机数字
		char[] array = {'0','1','2','3','4','5','6','7','8','9'};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
		    int index = rand.nextInt(i);
		    char tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		String result="" ;
		for(int i = 0; i < 6; i++)
		    result = result + array[i];
		System.out.println("生成验证码："+result);
		
		return result;
		//三个参数分别是收件人，主题，正文
		//sendMessage("1522780701@qq.com","test", "<html><body><a href='www.baidu.com'>asdf</a></body></html>");
	}
	
	//生成绑定邮箱 邮件正文
	public static String generate_msg (String type,String loadname,String captch){
    	String msg="<html><body>";
    	
    	msg=msg+ "<a href='http://localhost:8080/Model/email/activeEmail_receive?type="+type+"&ln="+loadname+"&ca="+captch+"'>激活链接</a></br>";
    	msg=msg+"如果无法打开页面，请复制下面的链接，然后用浏览器打开：";
    	msg=msg+" http://localhost:8080/Model/email/activeEmail_receive?type=";
      	msg=msg+type;
    	msg=msg+"&ln="  ;//设置用户名"
    	msg=msg+loadname;
    	msg=msg+"&ca=";           //设置验证码
    	msg=msg+captch;
    	msg=msg+"</body></html>";
    	
		return msg;
	}
	
	//生成重置密码邮件的正文
	public static String generate_resetPass_msg (String type,String loadname,String captch){
		String msg="<html><body>";
		msg=msg+ "亲爱的："+type+":"+loadname+"</br>";
		msg=msg+ "你的新密码是："+captch+"</br>";
		msg=msg+"请登录网站，然后立即修改密码。</br>请不要向任何人泄露本邮件的内容。</br>如果您并没有申请重置密码，则你的账户信息可能已经泄露，请立刻登陆网站，绑定新的邮箱，并修改密码。";
		//msg=msg+"如果无法打开页面，请复制下面的链接，然后用浏览器打开：";
    	//msg=msg+" http://localhost:8080/Model/email/resetPasseord_receive?type="+type+"&ln="+loadname+"&ca="+captch;
    	
        msg=msg+"</body></html>";
		return msg;
	}
}
