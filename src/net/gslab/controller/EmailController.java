package net.gslab.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import net.gslab.entity.Admin;
import net.gslab.entity.Member;
import net.gslab.entity.Teacher;


import net.gslab.service.AdminService;
import net.gslab.dao.AdminDao;
import net.gslab.dao.TeacherDao;
import net.gslab.dao.MemberDao;
import net.gslab.service.TeacherService;
import net.gslab.service.UserService;
import net.gslab.service.impl.AdminServiceImpl;
import net.gslab.setting.PageBean;
import net.gslab.tools.Email;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;

@Controller(value="emailController")
@RequestMapping("/email")
public class EmailController extends BaseController{

	@Resource(name = "adminDaoImpl")
	private AdminDao adminDao;

	@Resource(name = "teacherDaoImpl")
	private TeacherDao teacherDao;
	
	@Resource(name = "memberDaoImpl")
	private MemberDao memberDao;
	
	//激活邮箱->接收邮件，并且设置邮箱状态为"已激活" .激活链接有3个参数，一个是账户类型（admin,teacher,member），一个是账户Id，一个是验证码
	@RequestMapping(value="/activeEmail_receive",method=RequestMethod.GET)
	public ModelAndView receiveEmail(HttpServletRequest request,String type,String ln, String ca){
		ModelAndView mav = new ModelAndView();
	    String toUrl="/view_all/emailActiveResult.jsp";     //设置重定向
	    mav.setViewName("redirect:"+toUrl);
		if(type.equals("member")){
			Member dbMember = memberDao.getUserByLoadName(ln);  //从数据库中读取用户
            if(dbMember!=null){                                       //如果学生存在
           	 if(dbMember.getEmail_active().equals("1")){           //如果邮件已经激活了
   				 //this.setSessionMember(request, dbMember);          //修改session
           		 mav.addObject("ERROR_MSG_KEY", "email "+dbMember.getEmail()+" have been actived before now!");
   			 }
           	 else if(dbMember.getEmail_captcha().equals(ca)){           //如果验证码匹配，则成功激活
           		 dbMember.setEmail_active("1");                    //修改激活状态
           		memberDao.update(dbMember);                    //修改数据库
           		 this.setSessionMember(request, dbMember);          //修改session
           		 mav.addObject("ERROR_MSG_KEY", "email "+dbMember.getEmail()+" active success!");
           	 }
           	 else{
           		 mav.addObject("ERROR_MSG_KEY", "the user "+dbMember.getMemberName()+" exists,but the captcha is wrong, please try to active again.");
           	 }
            }
            else{
       		 mav.addObject("ERROR_MSG_KEY", "the user "+ln+" do not exist, please contact the admin.");
            }
		}
		else if(type.equals("admin")){
			int id=Integer.parseInt(ln);      //adminId转为int类型
			Admin dbAdmin = adminDao.getAdminById(id);  //从数据库中读取用户
            if(dbAdmin!=null){                                       //如果学生存在
           	 if(dbAdmin.getEmail_active().equals("1")){           //如果邮件已经激活了
   				 //this.setSessionMember(request, dbMember);          //修改session
           		 mav.addObject("ERROR_MSG_KEY", "email "+dbAdmin.getEmail()+"have been actived before now!");
   			 }
           	 else if(dbAdmin.getEmail_captcha().equals(ca)){           //如果验证码匹配，则成功激活
           		dbAdmin.setEmail_active("1");                    //修改激活状态
           		adminDao.update(dbAdmin);                    //修改数据库
           		 this.setSessionAdmin(request, dbAdmin);          //修改session
           		 mav.addObject("ERROR_MSG_KEY", "email "+dbAdmin.getEmail()+" active success!");
           	 }
           	 else{
           		 mav.addObject("ERROR_MSG_KEY", "the user "+dbAdmin.getAdminName()+" exists,but the captcha is wrong, please try to active again.");
           	 }
            }
            else{
       		 mav.addObject("ERROR_MSG_KEY", "the user "+ln+" do not exist, please contact the admin.");
            }
		}
		else if(type.equals("teacher")){
			int id=Integer.parseInt(ln);      //adminId转为int类型
			Teacher dbTeacher = teacherDao.getTeacherById(id);  //从数据库中读取用户
            if(dbTeacher!=null){                                       //如果学生存在
           	 if(dbTeacher.getEmail_active().equals("1")){           //如果邮件已经激活了
   				 //this.setSessionMember(request, dbMember);          //修改session
           		 mav.addObject("ERROR_MSG_KEY", "email "+dbTeacher.getEmail()+"have been actived before now!");
   			 }
           	 else if(dbTeacher.getEmail_captcha().equals(ca)){           //如果验证码匹配，则成功激活
           		dbTeacher.setEmail_active("1");                    //修改激活状态
           		teacherDao.update(dbTeacher);                    //修改数据库
           		 this.setSessionTeacher(request, dbTeacher);          //修改session
           		 mav.addObject("ERROR_MSG_KEY", "email "+dbTeacher.getEmail()+" active success!");
           	 }
           	 else{
           		 mav.addObject("ERROR_MSG_KEY", "the user "+dbTeacher.getTeacherName()+" exists,but the captcha is wrong, please try to active again.");
           	 }
            }
            else{
       		 mav.addObject("ERROR_MSG_KEY", "the user "+ln+" do not exist, please contact the admin.");
            }
		}
		
		else{// type类型不存在，链接有问题
      		 mav.addObject("ERROR_MSG_KEY", "the user type is not right, the link is wrong");
		}
	    
	    return mav;
		
	}
	
	// 重置密码： 发送邮件 ，并把生成的新密码发送给用户邮箱  ， resetPasseord  
	@RequestMapping(value="/resetPasseord_sent",method=RequestMethod.POST)
	 public ModelAndView resetPasseord(HttpServletRequest request,String logintype,String loadname,String email){
		ModelAndView mav = new ModelAndView();
	    String toUrl="/view_all/test.jsp";     //设置重定向
	    mav.setViewName("redirect:"+toUrl);
	    
	    if(logintype.equals("student")){
	    	Member dbMember=memberDao.getUserByLoadName(loadname);
	    	if(dbMember==null){
	       		 mav.addObject("ERROR_MSG_KEY", "the student "+loadname+" do not exist.");
	       		 return mav;
	    	}else if(dbMember.getEmail()==null||dbMember.getEmail().equals("")){
	       		 mav.addObject("ERROR_MSG_KEY", "the student "+loadname+" do not bind email  ,faild to reset password.");
	       		 return mav;
	    	}else if(dbMember.getEmail().equals(email)){
	    		 Email m_email=new Email();
	    		 String captch=Email.verification_code();  //生成6位随机密码
	    		 String msg=Email.generate_resetPass_msg("student", loadname, captch)  ;//生成邮件正文
	    		 Email.sendMessage(email, "重置密码", msg);      //发送邮件
	    		 dbMember.setPassword(captch);		  //设置密码
	    		 memberDao.update(dbMember);                  //密码写入数据库
	       		 mav.addObject("ERROR_MSG_KEY", "the  email has been sent, please check!.");
	       		 return mav;
	    	}else{
	    		 mav.addObject("ERROR_MSG_KEY", "email address is wrong   ,faild to reset password.");
	       		 return mav;
	    	}
	    }else if(logintype.equals("teacher")){
	    	int id=Integer.parseInt(loadname);
	    	Teacher dbTeacher=teacherDao.getTeacherById(id);
	    	if(dbTeacher==null){
	       		 mav.addObject("ERROR_MSG_KEY", "the teacher "+loadname+" do not exist.");
	       		 return mav;
	    	}else if(dbTeacher.getEmail()==null||dbTeacher.getEmail().equals("")){
	       		 mav.addObject("ERROR_MSG_KEY", "the teacher "+loadname+" do not bind email  ,faild to reset password.");
	       		 return mav;
	    	}else if(dbTeacher.getEmail().equals(email)){
	    		 Email m_email=new Email();
	    		 String captch=Email.verification_code();  //生成验证码
	    		 String msg=Email.generate_resetPass_msg("teacher", loadname, captch)  ;//生成邮件正文
	    		 Email.sendMessage(email, "重置密码", msg);      //发送邮件
	    		 dbTeacher.setPassword(captch);		  //设置密码
	    		 teacherDao.update(dbTeacher);                //新密码写入数据库
	       		 mav.addObject("ERROR_MSG_KEY", "the  email has been sent, please check!.");
	       		 return mav;
	    	}else{
	    		 mav.addObject("ERROR_MSG_KEY", "email address is wrong   ,faild to reset password.");
	       		 return mav;
	    	}
	    }else if(logintype.equals("admin")){
	    	int id=Integer.parseInt(loadname);
	    	Admin dbAdmin=adminDao.getAdminById(id);
	    	if(dbAdmin==null){
	       		 mav.addObject("ERROR_MSG_KEY", "the admin "+loadname+" do not exist.");
	       		 return mav;
	    	}else if(dbAdmin.getEmail()==null||dbAdmin.getEmail().equals("")){
	       		 mav.addObject("ERROR_MSG_KEY", "the admin "+loadname+" do not bind email  ,faild to reset password.");
	       		 return mav;
	    	}else if(dbAdmin.getEmail().equals(email)){
	    		 Email m_email=new Email();
	    		 String captch=Email.verification_code();  //生成密码
	    		 String msg=Email.generate_resetPass_msg("admin", loadname, captch)  ;//生成邮件正文
	    		 Email.sendMessage(email, "重置密码", msg);      //发送邮件
	    		 dbAdmin.setPassword(captch);		 //设置密码
	    		 adminDao.update(dbAdmin);                  //密码写入数据库
	       		 mav.addObject("ERROR_MSG_KEY", "the  email has been sent, please check!.");
	       		 return mav;
	    	}else{
	    		 mav.addObject("ERROR_MSG_KEY", "email address is wrong   ,faild to reset password.");
	       		 return mav;
	    	}
	    }else{// type类型不存在，链接有问题
     		 mav.addObject("ERROR_MSG_KEY", "the user type is wrong！");
     		return mav;
	    }
	   
	}
	
	/*// 重置密码->接收邮件 ，  resetPasseord_receive  
	@RequestMapping(value="/resetPasseord_receive",method=RequestMethod.GET)
    public ModelAndView resetPasseord_receive(HttpServletRequest request,String type,String ln, String ca){
		ModelAndView mav = new ModelAndView();
	    String toUrl="/view_all/emailResetPasswordResult2.jsp";     //设置重定向
	    mav.setViewName("redirect:"+toUrl);
	    
	    if(type.equals("student")){
	    	Member dbMember = memberDao.getUserByLoadName(ln);  //从数据库中读取用户
            if(dbMember!=null){                                       //如果学生存在
           	   if(dbMember.getPassword_captcha().equals(ca)){           //如果验证码匹配，
           		 dbMember.setPassword("123456");                    //重置密码
           		 memberDao.update(dbMember);                    //修改数据库
           		 // this.setSessionMember(request, dbMember);          //修改session
           		 mav.addObject("ERROR_MSG_KEY", "the password of user "+dbMember.getMemberId()+" reset success!");
           	   }
           	   else{
           		  mav.addObject("ERROR_MSG_KEY", "the user "+dbMember.getMemberId()+" exists,but the captcha is wrong, please try to active again.");
           	   }
            }
            else{
       		 mav.addObject("ERROR_MSG_KEY", "the user "+ln+" do not exist, please contact the admin.");
            }
	    	
	    }else if(type.equals("teacher")){
	    	
	    	
	    }else if(type.equals("admin")){
	    	
	    	
	    }else{
	    	
	    	
	    }
	    
	    
	    return mav;
	}*/
}
