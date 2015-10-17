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
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import jxl.Sheet;
import jxl.Workbook;
import net.gslab.dao.TeacherDao;
import net.gslab.entity.Member;
import net.gslab.entity.Teacher;
import net.gslab.service.TeacherService;
import net.gslab.service.UserService;
import net.gslab.setting.PageBean;
import net.gslab.tools.Email;

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
	public void delete(int []id,HttpServletResponse response) throws IOException
	{
		if(id!=null)
		{
			for(int i=0;i<id.length;i++)
				teacherService.delete(id[i]);
		}
		response.getWriter().print("删除成功");
	}
	
	//查找单个老师 测试成功
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
	//查找一页老师 ,测试成功，例：http://localhost:8080/Model/teacher/findPage?pageIndex=1&pageSize=4
	@RequestMapping(value="/findPage")
	public @ResponseBody PageBean<Teacher> findPage(int pageIndex,  int pageSize)
	{
		/**
		 * @param pageIndex   请求的页码
         * @param pageSize   每页的记录条数
         * @param 
		 */
		PageBean page=teacherDao.getPage(pageIndex,pageSize);
		return page;
	}
	//查找一页老师 ,测试成功，例：http://localhost:8080/Model/teacher/findPage?pageIndex=1&pageSize=4
		@RequestMapping(value="/getPage")
		public ModelAndView getPage(String pg)
		{
			/**
			 * @param pageIndex   请求的页码
	         * @param pageSize   每页的记录条数
	         * @param 
			 */
			int pageIndex;
			if(pg==null) pageIndex=0;
			else pageIndex=Integer.parseInt(pg);
			ModelAndView mav=new ModelAndView("/view_admin/m_teacherList.jsp");
			PageBean pageBean=teacherService.getPage(pageIndex);
			mav.addObject("pageBean",pageBean);
			return mav;
		}
	//修改老师信息 ，修改除密码以外的其他信息，账户名和id是不可以修改的。）,修改：地址、年级、班级、生日、性别、专业、电话、QQ
	@RequestMapping(value="/changeInf")
	public ModelAndView changeInf(HttpServletRequest request,Teacher teacher)
	{
		ModelAndView mav = new ModelAndView("redirect:"+"/view_tea/test.jsp");  //设置重定向
		
		String login_type=this.getSessionType(request);
		Teacher login_teacher=this.getSessionTeacher(request);
		if(!login_type.equals("teacher")||login_teacher==null){
			mav.addObject("ERROR_MSG_KEY", "sorry, user do not login , or the login type is not 'teacher'.");
		    return mav;
		}
		Teacher dbTeacher=teacherService.getByID(login_teacher.getTeacherId());
		if(dbTeacher==null){
			mav.addObject("ERROR_MSG_KEY", "sorry, the datebase error , the teacher does not exist.");
		    return mav;
		}
		else{
			if(!teacher.getAddress().equals(""))
				dbTeacher.setAddress(teacher.getAddress());
			if(teacher.getBirthDate()!=null)
				dbTeacher.setBirthDate(teacher.getBirthDate());
			if(!teacher.getGender().equals(""))
				dbTeacher.setGender(teacher.getGender());;
			if(!teacher.getMobilePhone().equals(""))
				dbTeacher.setMobilePhone(teacher.getMobilePhone());;
			if(!teacher.getQQ().equals(""))
				dbTeacher.setQQ(teacher.getQQ());;
			if(!teacher.getSelfEvaluation().equals(""))
				dbTeacher.setSelfEvaluation(teacher.getSelfEvaluation());;
		}
		teacherDao.update(dbTeacher); //把修改后的信息，写入数据库
		this.setSessionTeacher(request, dbTeacher); //因为对登陆用户的信息进行了修改，所以要对session里面的对象也进行修改
		mav.addObject("ERROR_MSG_KEY", "modify teacher success!");
		return mav;
	}
	
	//直接修改密码：老师登陆后台，直接修改密码
	@RequestMapping(value="changePsw",method=RequestMethod.POST)
	public ModelAndView changePsw(HttpServletRequest request,String password,String passwordNew,String passwordNew2)
	{
		ModelAndView mav = new ModelAndView("redirect:"+"/view_tea/test.jsp");  //设置重定向
		String login_type=this.getSessionType(request);
		Teacher login_teacher=this.getSessionTeacher(request);
		if(!login_type.equals("teacher")||login_teacher==null){
			mav.addObject("ERROR_MSG_KEY", "sorry, user do not login , or the login type is not 'teacher'.");
		    return mav;
		}
		Teacher dbTeacher=teacherService.getByID(login_teacher.getTeacherId());
		if(dbTeacher==null){
			mav.addObject("ERROR_MSG_KEY", "sorry, the datebase error , the teacher does not exist.");
		    return mav;
		}
		else{
			if(dbTeacher.getPassword().equals(password)){
				if(passwordNew.equals(passwordNew2)){
					dbTeacher.setPassword(passwordNew);
					teacherDao.update(dbTeacher);
					this.setSessionTeacher(request, dbTeacher);
					mav.addObject("ERROR_MSG_KEY", "successed to modify  password !");
					return mav;
				}
				else{
					mav.addObject("ERROR_MSG_KEY", "new passwords are not same,failed");
					return mav;
				}
			}
			else{
				mav.addObject("ERROR_MSG_KEY", "old password is wrong, failed to modify!");
				return mav;
			}
		}
	}
	
	//设置邮箱，老师登陆后设置邮箱，通知自动设置邮箱状态为“未激活”
	@RequestMapping(value="/setEmail",method=RequestMethod.POST)
	public ModelAndView setEmail(HttpServletRequest request,String teacherEmail)
	{
		ModelAndView mav = new ModelAndView("redirect:"+"/view_tea/test.jsp");  //设置重定向
		
		String login_type=this.getSessionType(request);
		Teacher login_teacher=this.getSessionTeacher(request);
		if(!login_type.equals("teacher")||login_teacher==null){
			mav.addObject("ERROR_MSG_KEY", "sorry, user do not login , or the login type is not 'teacher'.");
		    return mav;
		}
		Teacher dbTeacher=teacherService.getByID(login_teacher.getTeacherId());
		if(dbTeacher==null){
			mav.addObject("ERROR_MSG_KEY", "sorry, the datebase error , the teacher does not exist.");
		    return mav;
		}
	    else {
	    	dbTeacher.setEmail(teacherEmail);   
	    	dbTeacher.setEmail_active("0");   //设置邮箱状态为"未激活"
	    	teacherDao.update(dbTeacher);  //把修改后的信息，写入数据库
	    	this.setSessionTeacher(request, dbTeacher);  //因为对登陆用户的信息进行了修改，所以要对session里面的对象也进行修改
	    	mav.addObject("ERROR_MSG_KEY", "email:"+teacherEmail+"  , set success!  Please active!");
	    	return mav;
	    }
	}
	
	
	
	//绑定邮箱 :激活邮箱->发送邮件  ,把验证码写入数据库
	@RequestMapping(value="/activeEmail_send",method=RequestMethod.POST)
	public ModelAndView activeEmail(HttpServletRequest request)
	{			
		ModelAndView mav = new ModelAndView("redirect:"+"/view_tea/test.jsp");  //设置重定向
        
		String login_type=this.getSessionType(request);
		Teacher login_teacher=this.getSessionTeacher(request);
		if(!login_type.equals("teacher")||login_teacher==null){
			mav.addObject("ERROR_MSG_KEY", "sorry, user do not login , or the login type is not 'teacher'.");
		    return mav;
		}
		Teacher dbTeacher=teacherService.getByID(login_teacher.getTeacherId());
		if(dbTeacher==null){
			mav.addObject("ERROR_MSG_KEY", "sorry, the datebase error , the teacher does not exist.");
		    return mav;
		}
		String emailAddress=dbTeacher.getEmail();
        if(emailAddress.equals("")){  //邮箱不存在
        	mav.addObject("ERROR_MSG_KEY", "email does not exist; failed to send email!");
        	return mav;
        }else if(dbTeacher.getEmail_active().equals("1")){  //邮箱已经激活
        	mav.addObject("ERROR_MSG_KEY", "email has been actived, failed to send email again!");
        	return mav;
        }
        else{
        	Email t_email=new Email();
        	String captch=Email.verification_code();  //生成验证码
        	String msg=Email.generate_msg("teacher",Integer.toString(dbTeacher.getTeacherId()),captch);//生成邮件正文
        	Email.sendMessage(emailAddress, "激活邮件", msg); //发送邮件
        	dbTeacher.setEmail_captcha(captch);         //设置验证码
        	teacherDao.update(dbTeacher);            //验证码写入数据库
	    	this.setSessionTeacher(request, dbTeacher);  //因为对登陆用户的信息进行了修改，所以要对session里面的对象也进行修改
        	mav.addObject("ERROR_MSG_KEY", "email has been sent ,please check! ");
        }
		return mav;
	}
}
