package net.gslab.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.swing.text.View;

import net.gslab.entity.Admin;
import net.gslab.service.AdminService;
import net.gslab.service.TeacherService;
import net.gslab.service.UserService;
import net.gslab.setting.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value="adminController")
@RequestMapping("/admin")

public class AdminController extends BaseController{
	@Resource(name = "adminServiceImpl")
	private TeacherService adminService;
	
	
	
	
}
