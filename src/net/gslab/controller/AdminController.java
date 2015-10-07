package net.gslab.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.swing.text.View;

import net.gslab.entity.Admin;
import net.gslab.service.AdminService;
import net.gslab.dao.AdminDao;
import net.gslab.service.TeacherService;
import net.gslab.service.UserService;
import net.gslab.service.impl.AdminServiceImpl;
import net.gslab.setting.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller(value="adminController")
@RequestMapping("/admin")
public class AdminController extends BaseController{
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	
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

	
	//查找admin，根据id查找
	
	
	//修改admin信息
	
	//
	
	
	//
	
	
	//
	
	
	//
	
	
}
