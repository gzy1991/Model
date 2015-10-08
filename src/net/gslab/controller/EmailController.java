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
import net.gslab.setting.Page;
import net.gslab.tools.email;

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
	    String toUrl="/jsp/test_activeEmailResult_2.jsp";     //设置重定向
	    mav.setViewName("redirect:"+toUrl);
		if(type.equals("member")){
			
		}
		else if(type.equals("teacher")){
			
		}
		else if(type.equals("admin")){
			
		}
		else{
			
		}
	    
	    return mav;
		
	}
	
}
