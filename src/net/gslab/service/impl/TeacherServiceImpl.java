package net.gslab.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.gslab.dao.TeacherDao;
import net.gslab.entity.Admin;
import net.gslab.entity.Teacher;
import net.gslab.service.TeacherService;
@Service("teacherServiceImpl")
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService{

	
	private TeacherDao teacherDao;
	@Resource(name = "teacherDaoImpl")
	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao=teacherDao;
		super.setBaseDao(teacherDao);
	}
	@Override
	public Teacher getByID(int id) {
		// TODO Auto-generated method stub
		
		return teacherDao.getTeacherById(id);	
		}
	public boolean delete(int id){
		Teacher entity = teacherDao.load(id);
		return teacherDao.remove(entity);
	}

}
