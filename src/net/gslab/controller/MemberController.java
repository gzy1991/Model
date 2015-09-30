package net.gslab.controller;

import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.gslab.entity.Member;
import net.gslab.entity.Member.Category;
import net.gslab.service.MemberService;
import net.gslab.setting.Page;
import net.gslab.tools.email;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller(value="memberController")
@RequestMapping("/member")
public class MemberController extends BaseController{
	@Resource(name="memberServiceImpl")
	MemberService memberService;
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	//添加学生
	@RequestMapping(value="/adduser",method=RequestMethod.POST)
	public boolean adduser(String loadname,String password,String password2,String category,String memberName){
		Member member=new Member();
		if(password.equals(password2)){
			member.setLoadname(loadname);
			member.setPassword(password);
			member.setMemberName(memberName);
		if(member!=null){
			int id=Integer.parseInt(loadname);
			member.setMemberId(id);
			memberService.save(member);
		}
		return true;
		}
		return false;
	}
	
	//删除单个学生，参数是学生Id，
	@RequestMapping(value="/deleteMember",method=RequestMethod.POST)
	public void deleteMember(String studentId){
		String stat;
		if(true==memberService.delete(Integer.parseInt(studentId))){
		}
	}
	
	//查找单个学生，
	@RequestMapping(value="/findOne",method=RequestMethod.POST)
	public @ResponseBody Member findOne(String id){
		int i=Integer.parseInt(id);
		Member member=memberService.get(i);
		return member;
	}
	
	//查找全部学生，
		@RequestMapping(value="/findmember",method=RequestMethod.GET)
		public @ResponseBody List<Member> findmember(int i){
			Page page= memberService.getPage(i);
			int max=page.getPageSize();
			List<Member> members=page.getData();
			return members;
		}
		
	//设置邮箱   ,并设置邮箱状态为“未激活”
		@RequestMapping(value="/setEmail",method=RequestMethod.POST)
		public ModelAndView setEmail(HttpServletRequest request,String user_email)
		{
			System.out.println("in the action :setEmail  1");
			ModelAndView mav = new ModelAndView();
		    String toUrl="/jsp/t_setEmailResult.jsp";     //设置重定向
		    mav.setViewName("redirect:"+toUrl);
			
		   // System.out.println("in the action :setEmail  2");
		    Member login_member=this.getSessionMember(request);//从session中获取已登陆用户的信息
		   // System.out.println(login_member.getLoadname());
		    Member dbMember = memberService.getMemberByLoadName(login_member.getLoadname());  //从数据库中读取用户
		   // System.out.println("dbMember.ID:"+dbMember.getMemberId());
		    //System.out.println(login_member.getEmail());
		   // System.out.println(user_email);
		    if(login_member==null){
		    	mav.addObject("ERROR_MSG_KEY", "the student doesn't exist or doesn't login in! failed");
		    	return mav;
		    }
		    else {
		    	dbMember.setEmail(user_email);   
		    	dbMember.setEmail_active("0");   //设置邮箱状态为"未激活"
		    	memberService.update(dbMember);  //把修改后的信息，写入数据库
		    	this.setSessionMember(request, dbMember);  //因为对登陆用户的信息进行了修改，所以要对session里面的对象也进行修改
		    	mav.addObject("ERROR_MSG_KEY", "email:"+user_email+"  , set success! Please active!");
		    	//System.out.println(user_email);
		    	return mav;
		    }
		}
		
		//激活邮箱->发送邮件  ,统计把验证码写入数据库
		@RequestMapping(value="/activeEmail_send",method=RequestMethod.POST)
		public ModelAndView setEmail(HttpServletRequest request)
		{			
			//System.out.println("in the action :activeEmail_send  1");
		    ModelAndView mav = new ModelAndView();
	        String toUrl="/jsp/test_activeEmailResult.jsp";     //设置重定向
	        mav.setViewName("redirect:"+toUrl);
	        
		    Member login_member=this.getSessionMember(request);//从session中获取已登陆用户的信息
            String emailAddress=login_member.getEmail();
		    Member dbMember = memberService.getMemberByLoadName(login_member.getLoadname());  //从数据库中读取用户
	        if(emailAddress.equals("")){
	        	mav.addObject("ERROR_MSG_KEY", "email does not exist; failed to send email!");
	        	return mav;
	        }
	        else{
	        	email t_email=new email();
	        	String captch=email.verification_code();  //生成验证码
	        	String msg=email.generate_msg(dbMember.getLoadname(),captch);//生成邮件正文
	        	email.sendMessage(emailAddress, "激活邮件", msg); //发送邮件
	        	dbMember.setEmail_captcha(captch);         //设置验证码
	        	memberService.update(dbMember);            //验证码写入数据库
		    	this.setSessionMember(request, dbMember);  //因为对登陆用户的信息进行了修改，所以要对session里面的对象也进行修改
	        	mav.addObject("ERROR_MSG_KEY", "email has been sent ,please check! ");
	        }
			return mav;
		}

		//激活邮箱->接收邮件，并且设置邮箱状态为"已激活" .激活链接有两个参数，一个是学生账户，一个是验证码
		@RequestMapping(value="/activeEmail_receive",method=RequestMethod.GET)
		public ModelAndView receiveEmail(HttpServletRequest request,String ln, String ca){
			 ModelAndView mav = new ModelAndView();
		     String toUrl="/jsp/test_activeEmailResult_2.jsp";     //设置重定向
		     mav.setViewName("redirect:"+toUrl);
		     
			 Member dbMember = memberService.getMemberByLoadName(ln);  //从数据库中读取用户
             if(dbMember!=null){                                       //如果学生存在
            	 if(dbMember.getEmail_active().equals("1")){           //如果邮件已经激活了
    				 //this.setSessionMember(request, dbMember);          //修改session
            		 mav.addObject("ERROR_MSG_KEY", "email "+dbMember.getEmail()+"have been actived before now!");
    			 }
            	 else if(dbMember.getEmail_captcha().equals(ca)){           //如果验证码匹配，则成功激活
            		 dbMember.setEmail_active("1");                    //修改激活状态
            		 memberService.update(dbMember);                    //修改数据库
            		 this.setSessionMember(request, dbMember);          //修改session
            		 mav.addObject("ERROR_MSG_KEY", "email "+dbMember.getEmail()+" active success!");
            	 }
            	 else{
            		 mav.addObject("ERROR_MSG_KEY", "the user "+dbMember.getMemberName()+" exists,but the captcha is wrong, please try to active again.");
            	 }
             }
             else{
        		 mav.addObject("ERROR_MSG_KEY", "the user "+ln+" do not exist, please contact the admin.");
             }
		     
		     return mav;
		}
		
	//修改学生信息  （修改除密码以外的其他信息，账户名和id是不可以修改的。）,修改：地址、年级、班级、生日、性别、专业、电话、QQ
	@RequestMapping(value="/modifyMember",method=RequestMethod.POST)
	public  ModelAndView modifyMember(HttpServletRequest request,Member member)
	{
		System.out.println("in the modifyMember action!");

		ModelAndView mav = new ModelAndView();
		String toUrl="/jsp/t_modifyMemberResult.jsp";        //设置重定向
		mav.setViewName("redirect:"+toUrl);                  //设置重定向
		
        Member login_member=this.getSessionMember(request);//从session中获取已登陆用户的信息
		Member dbMember = memberService.get(login_member.getMemberId());  //从数据库中读取用户
		System.out.println("dbMember:"+dbMember.getMemberId());
		if(dbMember==null) {
			mav.addObject("ERROR_MSG_KEY", "sorry , the datebase error, the student does not exist.");
			System.out.println("dbMember不存在");
			return mav;
			}
		else{
			if(!member.getAddress().equals(""))
			  dbMember.setAddress(member.getAddress());
			if(member.getBirthDate()!=null)                 //注意，这个跟其他信息不一样
				dbMember.setBirthDate(member.getBirthDate());
			if(!member.getGrade().equals(""))
				dbMember.setGrade(member.getGrade());               //年级
			if(!member.getClassNo().equals(""))
				dbMember.setClassNo(member.getClassNo());              //班级
			if(!member.getGender().equals(""))
				dbMember.setGender(member.getGender());
			if(!member.getMajor().equals(""))
				dbMember.setMajor(member.getMajor());
			if(!member.getMobilePhone().equals(""))
				dbMember.setMobilePhone(member.getMobilePhone());
			if(!member.getQQ().equals(""))
				dbMember.setQQ(member.getQQ());
		}
	
		memberService.update(dbMember);  //把修改后的信息，写入数据库
		System.out.println(dbMember.getBirthDate());
		mav.addObject("ERROR_MSG_KEY", "modify success!");
		System.out.println("修改成功");
		this.setSessionMember(request, dbMember);  //因为对登陆用户的信息进行了修改，所以要对session里面的对象也进行修改
		return mav;
	}
		
	//修改密码,学生本人直接修改密码
	@RequestMapping(value="/modifyPass_1",method=RequestMethod.POST)
	public  ModelAndView changePass(HttpServletRequest request,String password,String passwordNew,String passwordNew2)
	{
		ModelAndView mav = new ModelAndView();
		String toUrl="/jsp/t_modifyPassword_1_result.jsp";        //设置重定向
		mav.setViewName("redirect:"+toUrl);                  //设置重定向

		Member member=this.getSessionMember(request);//从session中获取已登陆用户的信息
		Member dbMember=memberService.getMemberByLoadName(member.getLoadname());
		System.out.println(dbMember.getLoadname());
		if(dbMember!=null)
		{
			if(dbMember.getPassword().equals(password))
			{
				if(passwordNew.equals(passwordNew2))
				{
					dbMember.setPassword(passwordNew);
					memberService.update(dbMember);          //修改数据库内学生密码
					this.setSessionMember(request, dbMember);//因为对登陆用户的信息进行了修改，所以要对session里面的对象也进行修改
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
	    
	
	
	//批量添加： 接收excel表，，然后批量添加进数据库
    @RequestMapping(value="/uploadExcel",method=RequestMethod.POST)
		public   ModelAndView uploadExcel(MultipartFile file, HttpServletRequest request 
				 ) throws ServletException, IOException
		{
			ModelAndView mav = new ModelAndView();
			String toUrl="/jsp/excelUploadResult.jsp";     //设置重定向
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
            	mav.addObject("upload_result_message", "file "+filename+"upload success,but it is not a excel file.");
            	return mav;
            }
            
			try {
				file.transferTo(newfile);                 //复制，保存文件
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("upload over: "+ filename);
			request.setAttribute("message", "upload success:"+filename);
			
			mav.addObject("upload_result_message", "upload success ,processing...");//设置消息，内容为是否上传成功
	            
			System.out.println("开始批量处理excel！");
			String savePath=newfile.getAbsolutePath();   //获得excel的绝对路径
			System.out.println("savePath:"+savePath);
			
			if(!savePath.isEmpty()) //检测，如果文件路径不是空
			{
				List<Member> list=new  ArrayList<Member>() ;
				try{
					Workbook rwb=Workbook.getWorkbook(new File(savePath));
					Sheet rs=rwb.getSheet(0);
		            int clos=rs.getColumns();//得到所有的列
		            int rows=rs.getRows();//得到所有的行
		            System.out.println(" clos:"+clos+" rows:"+rows);
		            
		            //应该对excel表的进行验证，遇到不符合格式的表格，就return
		            //可以暂定excel的表格式是  id	loadname	memberName		password   
                    //其中id和loadname都是String类，而且相同
		            //TO-DO
		            if(clos!=4||rows<=1)
		            {
						mav.addObject("handle_result_message","Sorry! excel path is valid, but the excel doesn't fit the format ,upload failed.");
						return mav;
		            }
		            for (int i = 1; i < rows; i++) {         //循环，对于每一行，进行处理  ，每一个是一个记录
		                    int j=0;
		                    //注意：第一行的内容不是数据,不处理。
		                    String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
		                    String loadname=rs.getCell(j++, i).getContents();
		                    String memberName=rs.getCell(j++, i).getContents();
		                    String password=rs.getCell(j++, i).getContents();
		                     if(!id.equals(loadname))  //如果id和loadname不一样，则本条记录添加失败
		                     {
		                    	 System.out.println("id与loadname不同，添加失败！");
		                    	 continue;
		                     }
		                    
		                    int memberid=Integer.parseInt(id);  //id转为int类型，因为数据库里面memberId是int类型
		                    System.out.println("1 :memberId:"+memberid+" loadname:"+loadname+"memberName："+memberName+" password:"+password);
		                    
		                    Member as = new Member();
		                    if( memberService.get(memberid)==null&&loadname!=null&&password!=null)
		                    {
			                System.out.println("2 :memberId:"+memberid+" loadname:"+loadname+"memberName："+memberName+" password:"+password);
		                    as.setLoadname(loadname);
		                    as.setMemberName(memberName);
		                    as.setPassword(password);
		                    as.setMemberId(memberid);
		                    if(as!=null){   //如果member不与数据库中人员冲突，并且不为空
			                    System.out.println("4 :memberId:"+memberid+" loadname:"+loadname+"memberName："+memberName+" password:"+password);
		            			memberService.save(as);
		            		}
		                    }
		                }
		            
				} catch(Exception e){
					 e.printStackTrace();
				}
				mav.addObject("handle_result_message","excel path is:"+savePath+",and process  succeed.");
			}
			else
			{
				mav.addObject("handle_result_message","Sorry! excel path is null, process failed.");
			}
			return mav;
		}
}


 
 
 
 

 
 
 
 
 
   