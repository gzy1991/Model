package net.gslab.controller;

import javax.servlet.http.HttpServletRequest;

import net.gslab.entity.Admin;
import net.gslab.entity.Member;
import net.gslab.entity.Teacher;
import net.gslab.entity.User;
import net.gslab.setting.CommonConstant;

public class BaseController {
	protected static final String ERROR_MSG_KEY="errorMsg";
	protected static  String login_type;
	
	protected User getSessionUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}
	
	//登陆类型
	protected void setSessionType(HttpServletRequest request,String type){
		request.getSession().setAttribute(CommonConstant.LOGIN_TYPE,type);
	}
	protected String getSessionType(HttpServletRequest request){
		return (String)request.getSession().getAttribute(CommonConstant.LOGIN_TYPE);
	}
	
	//Member  学生
	protected void setSessionMember(HttpServletRequest request,Member member){
		this.setSessionType(request,"student");
		request.getSession().setAttribute(CommonConstant.MEMBER_CONTEXT, member);
	}
	protected Member getSessionMember(HttpServletRequest request){
		return (Member)request.getSession().getAttribute(CommonConstant.MEMBER_CONTEXT);
	}
	
	//Teacher  老师
	protected void setSessionTeacher(HttpServletRequest request,Teacher teacher){
		this.setSessionType(request,"teacher");
		request.getSession().setAttribute(CommonConstant.MEMBER_CONTEXT, teacher);
	}
	protected Teacher getSessionTeacher(HttpServletRequest request){
		return (Teacher)request.getSession().getAttribute(CommonConstant.MEMBER_CONTEXT);
	}
	
	//Admin  管理员
	protected void setSessionAdmin(HttpServletRequest request,Admin admin){
		this.setSessionType(request,"admin");
		request.getSession().setAttribute(CommonConstant.MEMBER_CONTEXT, admin);
	}
	protected Admin getSessionAdmin(HttpServletRequest request){
		return (Admin)request.getSession().getAttribute(CommonConstant.MEMBER_CONTEXT);
	}
	
	//注销
	protected void loginOut(HttpServletRequest request){
		String type=this.getSessionType(request);
		if(type==null ||type.equals("")){           //如果不存在登陆用户
			return;
		}
		else if(type.equals("student")||type.equals("teacher")||type.equals("admin")){
		request.getSession().removeAttribute(CommonConstant.MEMBER_CONTEXT);
		request.getSession().removeAttribute(CommonConstant.LOGIN_TYPE);
		}
	}
	
}