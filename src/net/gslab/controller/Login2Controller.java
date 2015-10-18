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
	
	@RequestMapping(value="/doLogin2",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,String loadname,String password,String logintype){
		System.out.println("in the doLogin_2");
		System.out.println("loadname:"+loadname+" logintype:"+logintype);
		ModelAndView mav=new ModelAndView();
		
		int id=Integer.parseInt(loadname);   //登陆账户转为int类型
		
		if(logintype.equals("student")){      //学生登录
			Member dbMember = memberService.getByID(id);
			if(dbMember==null) {
				mav.addObject("ERROR_MSG_KEY", "the student Id don't exist1;");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				System.out.println("redirect2");
				return mav;
				}
			else if(!dbMember.getPassword().equals(password)){
				mav.addObject("ERROR_MSG_KEY","the student password is wrong;");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				return mav;
			}else{
				mav.addObject("ERROR_MSG_KEY", "Hello,student,login success");
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
					toUrl="/view_stu/s_student.jsp";  //设置重定向
				}
				mav.setViewName("redirect:"+toUrl);
			}
		}	
		else if(logintype.equals("teacher")){             //老师登录
			Teacher dbMember = teacherService.getByID(id);
			if(dbMember==null) {
				mav.addObject("ERROR_MSG_KEY", "the teacher Id don't exist;");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				System.out.println("redirect2");
				return mav;
				}
			else if(!dbMember.getPassword().equals(password)){
				mav.addObject("ERROR_MSG_KEY","the teacher password is wrong");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				return mav;
			}else{
				mav.addObject("ERROR_MSG_KEY", "Hello,teacher,login success!");
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
					toUrl="/view_tea/t_teacher.jsp";  //设置重定向
				}
				mav.setViewName("redirect:"+toUrl);
			}
			
		}
		else if(logintype.equals("admin")){                     //管理员登录
			Admin dbMember = adminService.getByID(id);
			if(dbMember==null) {
				mav.addObject("ERROR_MSG_KEY", "the admin Id don't exist1;");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				System.out.println("redirect2");
				return mav;
				}
			else if(!dbMember.getPassword().equals(password)){
				mav.addObject("ERROR_MSG_KEY","the admin password is wrong");
				mav.setViewName("redirect:/common/resource_not_found.jsp");
				return mav;
			}else{
				mav.addObject("ERROR_MSG_KEY", "Admin,login success.");
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
					toUrl="/view_admin/m_admin.jsp";  //设置重定向
				}
				mav.setViewName("redirect:"+toUrl);
			}
		}
		return mav;
	}
	
	//注销
	@RequestMapping(value="/doLoginOut")
	public ModelAndView loginout2(HttpServletRequest request)
	{
		System.out.println("in the doLoginOut");
		ModelAndView mav = new ModelAndView();
	    String toUrl="/loginOutResult.jsp";     //设置重定向
	    mav.setViewName("redirect:"+toUrl);
	        
	    String type=this.getSessionType(request);
	    //System.out.println("login_type:"+type);
	    if(type==null||type.equals("")){
			mav.addObject("ERROR_MSG_KEY", "you do not login in,can't login out.");
	    }
	    else if(type.equals("student")||type.equals("teacher")||type.equals("admin")){
	    	request.getSession().removeAttribute("LOGIN_TYPE");
	    	request.getSession().removeAttribute("MEMBER_CONTEXT");
	    	mav.addObject("ERROR_MSG_KEY", "login out successfully.");
	    }
		return mav;
	}

}
