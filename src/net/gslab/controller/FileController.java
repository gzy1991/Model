package net.gslab.controller;

import java.io.File;

import net.gslab.entity.Teacher;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
	@RequestMapping(value="/show")
	public ModelAndView show(String filePath)
	{
		ModelAndView mav=new ModelAndView("../view_all/show");
		
		File file=new File(filePath);
		if(file.isDirectory())
		{
			mav.addObject("filePath", filePath);
			File []files=file.listFiles();
			mav.addObject("files", files);
			
		}
		return mav;
	}
	@RequestMapping(value="download")
	public ModelAndView download(String filePath)
	{
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	@RequestMapping(value="/fileOfTeacher")
	public ModelAndView listFiles(Teacher t)
	{
		return show(t.getFileDirectory());
	}
	private boolean validate(String filePath)
	{
		return true;
		/*Teacher t=request.gets
		if()*/
	}
}
