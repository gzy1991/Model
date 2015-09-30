package net.gslab.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import net.gslab.entity.Member;
import net.gslab.entity.User;
import net.gslab.setting.CommonConstant;

public class LoginFilter implements Filter {

	private static final String FILTERED_REQUEST = "@@session_context_filtered_request";
	private static final String[] INHERENT_ESCAPE_URIS = { "/view/home.jsp","/view/aplay.jsp"}; //?

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("进入登录验证过滤器：");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        String[] notFilter = new String[] { "/view/doLogin","/member/activeEmail_receive"  };// 不过滤的url,

		String servletPath=httpRequest.getServletPath();  //获取url路径 的后半部分
        String url = httpRequest.getRequestURI().toString(); //获取url路径 
        
        
		System.out.println("servletPath:"+servletPath);
		System.out.println("request.getServletContext："+request.getServletContext());
		
        for(int i=0;i<notFilter.length;i++)              //对url进行处理，若url不需要过滤，则之间通过
        {
        	if(servletPath.equals(notFilter[i]))        //如果访问了 不过滤的url
        	{
        		System.out.println("您访问了不需要过滤的页面或请求，直接通过！");
        		
        		// 把想要访问的界面存到LOGIN_TO_URL里，转到登陆界面，一旦登陆成功就去那个页面
        		if (!StringUtils.isEmpty(httpRequest.getQueryString()))
        			url += "?" + httpRequest.getQueryString();
        		
				// 把想要访问的界面存到LOGIN_TO_URL里，转到登陆界面，一旦登陆成功就去那个页面
				httpRequest.getSession().setAttribute(
						CommonConstant.LOGIN_TO_URL, url);
				System.out.println("url:"+url); 
        		chain.doFilter(request, response);
        	}
        }
		
		if (request != null && request.getAttribute(FILTERED_REQUEST)!=null) {  //?
			 //如果没发送请求  或者  不是第一次成功通过过滤器，则直接通过
			System.out.println("直接通过过滤器");
			chain.doFilter(request, response);
			
		} else {
			System.out.println("首次进入过滤器");
			Member memberContext = this.getSessionMember(httpRequest);         // 从session中获取登录者实体
			System.out.println("memberContext:"+memberContext);
			
			//if (memberContext == null && !isURILogin(httpRequest.getRequestURI(), httpRequest)) {
			if (	memberContext == null ) {              // 如果用户为空且未登录
				// 拿到url
				String toUrl = httpRequest.getRequestURL().toString();
				System.out.println("被过滤掉：用户未登录且访问了未授权页面");
				// 拿到请求
				if (!StringUtils.isEmpty(httpRequest.getQueryString()))
					toUrl += "?" + httpRequest.getQueryString();
				System.out.println("toUrl" + toUrl);
				// 把想要访问的界面存到LOGIN_TO_URL里，转到登陆界面，一旦登陆成功就去那个页面
				httpRequest.getSession().setAttribute(
						CommonConstant.LOGIN_TO_URL, toUrl);
				request.getRequestDispatcher("/login.jsp").forward(
						request, response);                //跳转
				System.out.println("要return了啊========");
				return;

			}
			else{
				chain.doFilter(request, response);
			}
			
		}
	}

	private Member getSessionMember(HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		Member user = (Member) httpRequest.getSession().getAttribute(
				CommonConstant.MEMBER_CONTEXT);
		if (user == null)
			System.out.println("null user in the loginFilter");
		return user;
	}

	private boolean isURILogin(String requestURI, HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		if (httpRequest.getContextPath().equalsIgnoreCase(requestURI)
				|| (httpRequest.getContextPath() + "/")
						.equalsIgnoreCase(requestURI))
			return true;
		for (String uri : INHERENT_ESCAPE_URIS) {
			if (requestURI != null && requestURI.indexOf(uri) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}