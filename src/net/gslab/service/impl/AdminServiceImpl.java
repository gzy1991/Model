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

@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{
	@Resource(name="adminDaoImpl")
	private AdminDao adminDao;

	/*public BaseDao getBaseDao() {
		return baseDao;
	}
	@Resource(name = "baseDaoImpl")
	public void setAdminDao(BaseDao baseDao) {
		super.setBaseDao(baseDao);
		this.baseDao = baseDao;
	}*/
	
	
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
}
