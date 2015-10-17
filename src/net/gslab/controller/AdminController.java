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
import net.gslab.service.AdminService;
import net.gslab.dao.AdminDao;
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
@Controller(value="adminController")
@RequestMapping("/admin")
public class AdminController extends BaseController{
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	
	@Resource(name = "adminDaoImpl")
	private AdminDao adminDao;

	
	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminServiceImpl(AdminServiceImpl adminService) {
		this.adminService = adminService;
	}
	
	//添加单个admin，注意，添加admin除了需要id和密码外，还需要一个独一无二的标记。
	@RequestMapping(value="/addAdmin",method=RequestMethod.POST)
	public ModelAndView addAdmin(String adminId,String password,String password2,String adminName,String addAdminMark){
		System.out.println("in the action :addAdmin  ");
		ModelAndView mav = new ModelAndView();
	    String toUrl="/view_admin/m_addAdminResult.jsp";     //设置重定向
	    mav.setViewName("redirect:"+toUrl);
	    int id=Integer.parseInt(adminId); //adminId转为int类型
	    
	    if(!password.equals(password2)){ //密码不一致
	    	mav.addObject("ERROR_MSG_KEY", "sorry , the password are not same,failed to add..");
	    	return mav;
	    }
	    if(adminId!=null&&password!=null&&password2!=null&&adminName!=null&&addAdminMark!=null){
	    	if(adminService.getByID(id)==null) //如果新添加的admin不与数据库中人员冲突，并且不为空
		    {
		    	if(addAdminMark.equals("111111")){    //如果标记正确
		    		Admin admin=new Admin();
		    		admin.setAdminId(id);
		    		admin.setPassword(password);
		    		admin.setAdminName(adminName);
		    		adminService.save(admin);
		    		mav.addObject("ERROR_MSG_KEY", "add admin successfully;.");
			    	return mav;
		    	}
		    	else{
		    		mav.addObject("ERROR_MSG_KEY", "sorry , the addAdminMark is wrong, failed to add.");
			    	return mav;
		    	}
		    }
	    	else{
	    		mav.addObject("ERROR_MSG_KEY", "sorry , the adminId conflict ,the adminId you input has existd, failed to add.");
		    	return mav;
	    	}
	    }
	    else{
	    	mav.addObject("ERROR_MSG_KEY", "sorry , please check the content your input, failed to add.");
	    	return mav;
	    }
	}
	
	//删除单个admin   
	@RequestMapping(value="/deleteAdmin",method=RequestMethod.POST)
	public ModelAndView deleteAdmin(String adminId,String addAdminMark){
		System.out.println("in the action :addAdmin  ");
		ModelAndView mav = new ModelAndView();
	    String toUrl="/view_admin/m_deleteAdminResult.jsp";     //设置重定向
	    mav.setViewName("redirect:"+toUrl);
	    
	    if(!addAdminMark.equals("111111")){
	    	mav.addObject("ERROR_MSG_KEY", "sorry ,  the addAdminMark is wrong, failed .");
	    	return mav;
	    }
		int id=Integer.parseInt(adminId); //adminId转为int类型
		if(adminService.getByID(id)==null){
			mav.addObject("ERROR_MSG_KEY", "sorry ,  the adminid do not exists.");
	    	return mav;
		}
		else if(adminService.delete(id)){
			mav.addObject("ERROR_MSG_KEY", "delete admin successfully.");
	    	return mav;
		}
		else{
			mav.addObject("ERROR_MSG_KEY", "Unknown error,failed.");
			return mav;
		}
	}
	
	//查找单个admin，根据id查找，用request返回数据  例子： “http://localhost:8080/Model/view_admin/findOneAdmin.jsp”
	@RequestMapping(value="/findOneAdmin",method=RequestMethod.POST)
	public void fineOne(ServletRequest request,ServletResponse response,String adminId)
	throws ServletException, IOException
	{
		System.out.println("in the action :findOneAdmin  ");
		
	    int id=Integer.parseInt(adminId);
	    Admin admin=adminService.getByID(id);
		if(admin==null){
			System.out.println("findOneAdmin  failed ");
			request.setAttribute("ERROR_MSG_KEY", "sorry ,  the adminId do not exists.");
		}
		else{
			System.out.println("findOneAdmin  success ");
			request.setAttribute("ERROR_MSG_KEY", " find OneAdmin success.");
			request.setAttribute("admin", admin);
			try{
			request.getRequestDispatcher("../view_admin/m_findOneAdminResult.jsp").forward(request,response);
			}catch (IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//查找单个admin，根据id查找,返回json串     例子：“http://localhost:8080/Model/admin/findOneAdmin_2?adminId=555”
	@RequestMapping(value="/findOneAdmin_2",method=RequestMethod.GET)
	public @ResponseBody Admin fineOne_2(HttpServletRequest request,String adminId){
		int id=Integer.parseInt(adminId);
		Admin admin=adminService.getByID(id);
		return admin;
	}
	
	
	@RequestMapping("/getPage")
	public ModelAndView getPage(String pg)
	{
		int pageIndex;
		if(pg==null) pageIndex=1;
		else pageIndex=Integer.parseInt(pg);
		ModelAndView mav=new ModelAndView("/view_admin/m_adminList.jsp");
		PageBean pageBean=adminService.getPage(pageIndex);
		mav.addObject("pageBean",pageBean);
		return mav;
	}
	//修改admin信息
	
	
	//直接修改密码，admin本人直接修改密码
	@RequestMapping(value="/modifyPass_1",method=RequestMethod.POST)
	public ModelAndView modifyPass(HttpServletRequest request,String password,String passwordNew,String passwordNew2){
		ModelAndView mav = new ModelAndView();
		String toUrl="/view_admin/m_modifyPasswordResult.jsp";        //设置重定向
		mav.setViewName("redirect:"+toUrl);                  //设置重定向
		
		Admin admin=this.getSessionAdmin(request);//从session中获取已登陆admin的信息
		Admin dbAdmin=adminDao.getAdminById(admin.getAdminId());
		System.out.println(dbAdmin.getAdminName());
		if(dbAdmin!=null){
			if(dbAdmin.getPassword().equals(password))
			{
				if(passwordNew.equals(passwordNew2))
				{
					dbAdmin.setPassword(passwordNew);
					adminDao.update(dbAdmin);          //修改数据库内admin密码
					this.setSessionAdmin(request, dbAdmin);//因为对登陆用户的信息进行了修改，所以要对session里面的对象也进行修改
					mav.addObject("ERROR_MSG_KEY", "modify password success!");
				}
				else{
					mav.addObject("ERROR_MSG_KEY", "new password is not same,failed");
				}
			}
			else{
				mav.addObject("ERROR_MSG_KEY", "old password is wrong, failed!");
			}
		}
		System.out.println(request.getAttribute("ERROR_MSG_KEY"));
		return mav;
	}
	
	
	//设置邮箱   ,并设置邮箱状态为“未激活”
	@RequestMapping(value="/setEmail",method=RequestMethod.POST)
	public ModelAndView setEmail(HttpServletRequest request,String adminEmail)
	{
		System.out.println("in the action :setEmail  1");
		ModelAndView mav = new ModelAndView();
	    String toUrl="/view_admin/m_setEmailResult.jsp";     //设置重定向
	    mav.setViewName("redirect:"+toUrl);
		
	    Admin admin=this.getSessionAdmin(request);//从session中获取已登陆用户的信息
	    Admin dbAdmin = adminDao.getAdminById(admin.getAdminId());  //从数据库中读取用户
	    if(admin==null){
	    	mav.addObject("ERROR_MSG_KEY", "the admin doesn't exist or doesn't login in! failed");
	    	return mav;
	    }
	    else {
	    	dbAdmin.setEmail(adminEmail);   
	    	dbAdmin.setEmail_active("0");   //设置邮箱状态为"未激活"
	    	adminDao.update(dbAdmin);  //把修改后的信息，写入数据库
	    	this.setSessionAdmin(request, dbAdmin);  //因为对登陆用户的信息进行了修改，所以要对session里面的对象也进行修改
	    	mav.addObject("ERROR_MSG_KEY", "email:"+adminEmail+"  , set success!  Please active!");
	    	//System.out.println(user_email);
	    	return mav;
	    }
	}
	
	//激活邮箱->发送邮件  ,同时把验证码写入数据库
	@RequestMapping(value="/activeEmail_send",method=RequestMethod.POST)
	public ModelAndView setEmail(HttpServletRequest request)
	{			
		//System.out.println("in the action :activeEmail_send  1");
	    ModelAndView mav = new ModelAndView();
        String toUrl="/view_admin/m_activeEmailResult.jsp";     //设置重定向
        mav.setViewName("redirect:"+toUrl);
        
        Admin admin=this.getSessionAdmin(request);//从session中获取已登陆用户的信息
	    Admin dbAdmin = adminDao.getAdminById(admin.getAdminId());  //从数据库中读取用户
        String emailAddress=admin.getEmail();
        if(emailAddress.equals("")){
        	mav.addObject("ERROR_MSG_KEY", "email does not exist; failed to send email,please set email first!");
        	return mav;
        }
        else{
        	Email t_email=new Email();
        	String captch=Email.verification_code();  //生成验证码
        	String msg=Email.generate_msg("admin",Integer.toString(dbAdmin.getAdminId()),captch);//生成邮件正文
        	Email.sendMessage(emailAddress, "激活邮件", msg); //发送邮件
        	dbAdmin.setEmail_captcha(captch);         //设置验证码
        	adminDao.update(dbAdmin);            //验证码写入数据库
	    	this.setSessionAdmin(request, dbAdmin);  //因为对登陆用户的信息进行了修改，所以要对session里面的对象也进行修改
        	mav.addObject("ERROR_MSG_KEY", "email has been sent ,please check! ");
        }
		return mav;
	}	
	
	//
	
	
	//
	
	
}
