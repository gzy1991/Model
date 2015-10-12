package net.gslab.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;

import jxl.Sheet;
import jxl.Workbook;
import net.gslab.dao.TeacherDao;
import net.gslab.entity.Member;
import net.gslab.entity.Teacher;
import net.gslab.service.TeacherService;
import net.gslab.service.UserService;
import net.gslab.setting.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
	@Resource(name = "teacherServiceImpl")
	private TeacherService teacherService;
	
	@Resource(name = "teacherDaoImpl")
	private TeacherDao teacherDao;

	//添加一个老师
	@RequestMapping(value="/addOne",method=RequestMethod.POST)
	public ModelAndView addTeacher(String teacherId,String teacherName,String password,String password2)
	{
		ModelAndView mav = new ModelAndView("redirect:"+"/view_admin/test.jsp");  //设置重定向
	    int id=Integer.parseInt(teacherId);    //teacherId转为int类型
	    if(!password.equals(password2)){ //如果两次密码不一致
	    	mav.addObject("ERROR_MSG_KEY", "sorry , the password are not same,failed to add..");
	    	return mav;
	    }
	    if(teacherId!=null&&password!=null&&password2!=null&&teacherName!=null){
	    	if(teacherService.getByID(id)==null) //如果新添加的admin不与数据库中人员冲突，并且不为空
		    {
		    		Teacher teacher=new Teacher();
		    		teacher.setTeacherId(id);
		    		teacher.setPassword(password);
		    		teacher.setTeacherName(teacherName);
		    		teacher.setFileDirectory(teacherName);
		    		teacher.setVideoDirectory(teacherName);
		    		teacherDao.save(teacher);
		    		mav.addObject("ERROR_MSG_KEY", "add teacher successfully;.");
			    	return mav;
		    	}
	    	else{
	    		mav.addObject("ERROR_MSG_KEY", "sorry , the teacherId conflict ,the teacherId you input has existd, failed to add.");
		    	return mav;
	    	}
	    }
	    else{
	    	mav.addObject("ERROR_MSG_KEY", "sorry , please check the content your input, failed to add.");
	    	return mav;
	    }
	}
		
	//批量添加老师,接收excel表，，然后批量添加进数据库
	@RequestMapping(value="/uploadExcel",method=RequestMethod.POST)
	public   ModelAndView uploadExcel(MultipartFile file, HttpServletRequest request 
			 ) throws ServletException, IOException{
		ModelAndView mav = new ModelAndView();
		String toUrl="/view_admin/test.jsp";     //设置重定向
		mav.setViewName("redirect:"+toUrl);
		
		//获取 ServletContext.  注意:springMVC里面获取ServletContext和 之前在servlet里面获取的方法不一样
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
		ServletContext sc=webApplicationContext.getServletContext();  
		
		String dir = sc.getRealPath("/upload_excel");    //设定文件保存的目录
		String filename = file.getOriginalFilename();    //得到上传时的文件名
		File newfile=new File(dir,filename);             //建立本地同名文件
		String type=file.getContentType();               //获取文件类型，
		System.out.println("type:"+type);
        mav.addObject("file_type",type);                //把文件属性记到mav里面
		mav.addObject("file_name", filename);           //把文件名记到mav里面
        if(!type.equals("application/vnd.ms-excel"))   //验证，如果不是excel文件，则return，并报错
        {
        	mav.addObject("ERROR_MSG_KEY", "file "+filename+"upload success,but it is not a excel file.");
        	return mav;
        }
		try {
			file.transferTo(newfile);                 //复制，保存文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("upload over: "+ filename);
		mav.addObject("ERROR_MSG_KEY", "upload success ,processing...");//设置消息，内容为是否上传成功
            
		System.out.println("开始批量处理excel！");
		String savePath=newfile.getAbsolutePath();   //获得excel的绝对路径
		System.out.println("savePath:"+savePath);
		if(!savePath.isEmpty()) //检测，如果文件路径不是空
		{
			List<Teacher> list=new  ArrayList<Teacher>() ;
			try{
				Workbook rwb=Workbook.getWorkbook(new File(savePath));
				Sheet rs=rwb.getSheet(0);
	            int clos=rs.getColumns();//得到所有的列
	            int rows=rs.getRows();//得到所有的行
	            System.out.println(" clos:"+clos+" rows:"+rows);
	            
	            //应该对excel表的进行验证，遇到不符合格式的表格，就return
	            //可以暂定excel的表格式是  id		teacherName		password   
	            if(clos!=3||rows<=1)
	            {
					mav.addObject("ERROR_MSG_KEY","Sorry! excel path is valid, but the excel doesn't fit the format ,upload failed.");
					return mav;
	            }
	            for (int i = 1; i < rows; i++) {         //循环，对于每一行，进行处理  ，每一个是一个记录
	                    int j=0;
	                    String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
	                    String teacherName=rs.getCell(j++, i).getContents();
	                    String password=rs.getCell(j++, i).getContents();
	                    
	                    int teacherId=Integer.parseInt(id);  //id转为int类型，因为数据库里面teacherId是int类型
	                    Teacher as = new Teacher(); 
	                    if( teacherDao.getTeacherById(teacherId)==null&&teacherName!=null&&password!=null)  //如果teacher不与数据库中人员冲突，并且不为空
	                    {
	                    as.setTeacherName(teacherName);
	                    as.setPassword(password);
	                    as.setTeacherId(teacherId);
	                    as.setFileDirectory(teacherName);
	                    as.setVideoDirectory(teacherName);
	                    if(as!=null){   //如果teacher不与数据库中人员冲突，并且不为空
	            			teacherDao.save(as);
	            		    }
	                    }
	                }
			} catch(Exception e){
				 e.printStackTrace();
			}
			mav.addObject("ERROR_MSG_KEY","excel path is:"+savePath+",and process  succeed.");
		}
		else
		{
			mav.addObject("ERROR_MSG_KEY","Sorry! excel path is null, process failed.");
		}
		return mav;
	}
	
	//删除一个老师
	@RequestMapping(value="/delete")
	public ModelAndView deleteTeacher(String teacherId,Model m)
	{
		ModelAndView mav = new ModelAndView("redirect:"+"/view_admin/test.jsp");  //设置重定向
		int id=Integer.parseInt(teacherId);
		Teacher teacher=teacherDao.getTeacherById(id);
		if(teacher!=null){
			teacherService.delete(id);
			mav.addObject("ERROR_MSG_KEY","delete success!.");
			return mav;
		}
		else{
			mav.addObject("ERROR_MSG_KEY","the teacher do not exist, failed to delete!.");
			return mav;
		}
	}
	
	//查找单个老师
	@RequestMapping(value="/findOne")
	public @ResponseBody Teacher findOne(int teacherId,Model m)
	{
		Teacher t=teacherDao.getTeacherById(teacherId);
		if(t==null){
			return null;
		}else{
			return t;
		}
	}
	//查找一页老师
	@RequestMapping(value="/findPage")
	public @ResponseBody Page<Teacher> findPage(int pageIndex,  int pageSize,Model m)
	{
		/**
		 * @param pageIndex   请求的页码
         * @param pageSize   每页的记录条数
         * @param 
		 */
		Page page=teacherDao.getPage(pageIndex,pageSize);
		return page;
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
