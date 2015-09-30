package net.gslab.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.gslab.entity.Member;
import net.gslab.entity.User;
import net.gslab.service.MemberService;
import net.gslab.service.UserService;





import net.gslab.setting.CommonConstant;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	@Resource(name="memberServiceImpl")
	private MemberService memberService;

	@RequestMapping(value="/doLogin",method=RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,Member member){
		System.out.println("in the doLogin");
		System.out.println(member.getLoadname());
		Member dbMember = memberService.getMemberByLoadName(member.getLoadname());
		ModelAndView mav = new ModelAndView();
		if(dbMember==null) {
			mav.addObject(ERROR_MSG_KEY, "用户名不存在");
			mav.setViewName("redirect:/common/resource_not_found.jsp");
			System.out.println("redirect2");
			return mav;
			}
		else if(!dbMember.getPassword().equals(member.getPassword())){
			mav.addObject(ERROR_MSG_KEY,"用户密码不正确");
			mav.setViewName("redirect:/common/resource_not_found.jsp");
			return mav;
		}else{  
			//memberService.loginSuccess(dbMember);  //暂时未实现  memberService.loginSuccess()
			mav.addObject(ERROR_MSG_KEY, "您已成功登陆");
			System.out.println("您已成功登陆");
			
			//LoginFilter.java ,设置 FILTERED_REQUEST, "@@session_context_filtered_request"
			// 置位不再经过过滤器 ,这个功能还在测试
			request.setAttribute("@@session_context_filtered_request", Boolean.TRUE);  
			this.setSessionMember(request, dbMember);          //设置session，吧学生信息存入session
			String toUrl=(String)request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
			//toUrl要访问的页面，由于拦截器，如果不能通过拦截器不能访问到正确的页面
			request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
			mav.setViewName("home");//逻辑视图，与applicationContext-mvc下定义的viewResolver对应，不光前面，还有后面的Jsp也不能加
			System.out.println("用户申请跳转到页面："+toUrl);
			if(StringUtils.isEmpty(toUrl)){
				toUrl="/view/home.jsp";  //设置重定向
			}
			mav.setViewName("redirect:"+toUrl);
			
			return mav;
		}
	}
	
	//注销功能,,还未实现
	@RequestMapping(value="/doLoginOut",method=RequestMethod.GET)
	public ModelAndView loginout(HttpServletRequest request)
	{
		System.out.println("in the doLoginOut");
		ModelAndView mav = new ModelAndView();
		return mav;
	}

}
