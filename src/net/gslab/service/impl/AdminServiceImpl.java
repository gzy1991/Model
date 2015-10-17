package net.gslab.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.gslab.dao.AdminDao;
import net.gslab.dao.BaseDao;
import net.gslab.dao.MemberDao;
import net.gslab.entity.Admin;
import net.gslab.entity.Member;
import net.gslab.entity.Teacher;
import net.gslab.service.AdminService;
import net.gslab.service.TeacherService;
import net.gslab.setting.CommonConstant;
import net.gslab.setting.PageBean;

@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{
	private AdminDao adminDao;

	@Resource(name = "adminDaoImpl")
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao=adminDao;
		super.setBaseDao(adminDao);
		
	}
	
	
	@Override
	public Admin getByID(int id) {
		// TODO Auto-generated method stub
		return adminDao.get(id);	

	}
	
	public void save(Admin admin){
		adminDao.save(admin);
	}
	
	public boolean delete(int id){
		Admin entity = adminDao.load(id);
		return adminDao.remove(entity);
	}
	public PageBean<Admin> getPage(int pageIndex){
		String className=adminDao.getEntityClass().getSimpleName();
		int pageSize=CommonConstant.PAGE_SIZE[CommonConstant.getTable(className)];
		return adminDao.getPage(pageIndex,pageSize);
	}
}
