package net.gslab.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.swing.text.View;

import net.gslab.entity.Teacher;
import net.gslab.service.TeacherService;
import net.gslab.service.UserService;
import net.gslab.setting.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
	@Resource(name = "teacherServiceImpl")
	private TeacherService teacherService;
	//添加老师
	@RequestMapping(value="/add")
	public boolean addTeacher(Teacher t)
	{
		teacherService.save(t);
		return true;
	}
	//删除老师
	@RequestMapping(value="/delete")
	public boolean deleteTeacher(Teacher t,Model m)
	{
		teacherService.delete(t);
		return true;
	}
	//查找单个老师
	@RequestMapping(value="/findOne")
	public @ResponseBody Teacher findOne(int teacherId,Model m)
	{
		Teacher t=teacherService.find(teacherId);
		return t;
	}
	//查找一页老师
	@RequestMapping(value="/findPage")
	public @ResponseBody List<Teacher> findPage(int pageIndex,Model m)
	{
		Page page=teacherService.getPage(pageIndex);
		return page.getData();
	}
	//修改老师信息
	@RequestMapping(value="/changeInf")
	public boolean changeInf(Teacher t)
	{
		Teacher pre=teacherService.find(t.getTeacherId());
		t.setPassword(pre.getPassword());
		teacherService.update(t);
		return true;
	}
	//修改密码
	@RequestMapping("changePsw")
	public String changePsw(Teacher t,String psw1,String psw2)
	{
		if(psw1==null||!psw1.equals(psw2)) return "两次输入密码不一致";
		Teacher pre=teacherService.find(t.getTeacherId());
		if(!pre.getPassword().equals(t.getPassword())) return "原密码错误";
		t.setPassword(psw1);
		return "修改密码成功";
	}
	//绑定邮箱
	
}
