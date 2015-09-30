package net.gslab.controller;


import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.gslab.entity.Member;
import net.gslab.entity.User;
import net.gslab.entity.Admin;
import net.gslab.entity.Teacher;

import net.gslab.service.AdminService;
import net.gslab.service.MemberService;
import net.gslab.service.TeacherService;
import net.gslab.service.UserService;
import net.gslab.setting.CommonConstant;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login2")
public class Login2Controller extends BaseController{


	@Resource(name="memberServiceImpl")
	private MemberService memberService;
	

	@Resource(name="teacherServiceImpl")
	private TeacherService teacherService;
	
	@Resource(name="adminServiceImpl")
	private AdminService adminService;
	
	@RequestMapping(value="/doLogin2",method=RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,String loadname,String password,String logintype){
		System.out.println("in the doLogin_2");
		System.out.println("loadname:"+loadname+" password:"+ password+" logintype:"+logintype);
		ModelAndView mav=new ModelAndView();
		
		int id=Integer.parseInt(loadname);   //登陆账户转为int类型
		
		if(logintype.equals("student")){      //学生登录
			Member dbMember = memberService.getMemberByLoadName(loadname);
			if(dbMember==null) {
				mav.addObject("ERROR_MSG_KEY", "用户名不存在");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				System.out.println("redirect2");
				return mav;
				}
			else if(!dbMember.getPassword().equals(password)){
				mav.addObject("ERROR_MSG_KEY","用户密码不正确");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				return mav;
			}else{
				mav.addObject("ERROR_MSG_KEY", "您已成功登陆");
				System.out.println("您已成功登陆");
				//LoginFilter.java ,设置 FILTERED_REQUEST, "@@session_context_filtered_request"
				// 置位不再经过过滤器 ,这个功能还在测试
				request.setAttribute("@@session_context_filtered_request", Boolean.TRUE);  
				this.setSessionMember(request, dbMember); //设置session，吧学生信息存入session
				String toUrl=(String)request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
				//toUrl要访问的页面，由于拦截器，如果不能通过拦截器不能访问到正确的页面
				request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
				mav.setViewName("home");//逻辑视图，与applicationContext-mvc下定义的viewResolver对应，不光前面，还有后面的Jsp也不能加
				System.out.println("用户申请跳转到页面："+toUrl);
				if(StringUtils.isEmpty(toUrl)){
					toUrl="/view/home.jsp";  //设置重定向
				}
				mav.setViewName("redirect:"+toUrl);
			}
		}	
		else if(logintype.equals("teacher")){             //老师登录
			Teacher dbMember = teacherService.getByID(id);
			if(dbMember==null) {
				mav.addObject("ERROR_MSG_KEY", "用户名不存在");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				System.out.println("redirect2");
				return mav;
				}
			else if(!dbMember.getPassword().equals(password)){
				mav.addObject("ERROR_MSG_KEY","用户密码不正确");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				return mav;
			}else{
				mav.addObject("ERROR_MSG_KEY", "您已成功登陆");
				System.out.println("老师，您已成功登陆");
				//LoginFilter.java ,设置 FILTERED_REQUEST, "@@session_context_filtered_request"
				// 置位不再经过过滤器 ,这个功能还在测试
				request.setAttribute("@@session_context_filtered_request", Boolean.TRUE);  
				this.setSessionTeacher(request, dbMember); //设置session，吧学生信息存入session
				String toUrl=(String)request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
				//toUrl要访问的页面，由于拦截器，如果不能通过拦截器不能访问到正确的页面
				request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
				mav.setViewName("home");//逻辑视图，与applicationContext-mvc下定义的viewResolver对应，不光前面，还有后面的Jsp也不能加
				System.out.println("用户申请跳转到页面："+toUrl);
				if(StringUtils.isEmpty(toUrl)){
					toUrl="/view/home.jsp";  //设置重定向
				}
				mav.setViewName("redirect:"+toUrl);
			}
			
		}
		else if(logintype.equals("admin")){                     //管理员登录
			Admin dbMember = adminService.getByID(id);
			if(dbMember==null) {
				mav.addObject("ERROR_MSG_KEY", "用户名不存在");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				System.out.println("redirect2");
				return mav;
				}
			else if(!dbMember.getPassword().equals(password)){
				mav.addObject("ERROR_MSG_KEY","用户密码不正确");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				return mav;
			}else{
				mav.addObject("ERROR_MSG_KEY", "您已成功登陆");
				System.out.println("管理员，您已成功登陆");
				//LoginFilter.java ,设置 FILTERED_REQUEST, "@@session_context_filtered_request"
				// 置位不再经过过滤器 ,这个功能还在测试
				request.setAttribute("@@session_context_filtered_request", Boolean.TRUE);  
				this.setSessionAdmin(request, dbMember); //设置session，吧学生信息存入session
				String toUrl=(String)request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
				//toUrl要访问的页面，由于拦截器，如果不能通过拦截器不能访问到正确的页面
				request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
				mav.setViewName("home");//逻辑视图，与applicationContext-mvc下定义的viewResolver对应，不光前面，还有后面的Jsp也不能加
				System.out.println("用户申请跳转到页面："+toUrl);
				if(StringUtils.isEmpty(toUrl)){
					toUrl="/view/home.jsp";  //设置重定向
				}
				mav.setViewName("redirect:"+toUrl);
			}
		}
		return mav;
	}
}
