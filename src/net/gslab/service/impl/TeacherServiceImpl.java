package net.gslab.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.gslab.dao.TeacherDao;
import net.gslab.entity.Teacher;
import net.gslab.service.TeacherService;
@Service("teacherServiceImpl")
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService{

	
	

	@Resource(name = "teacherDaoImpl")
	private TeacherDao teacherDao;
	@Override
	public Teacher getByID(int id) {
		// TODO Auto-generated method stub
		
		return teacherDao.getTeacherById(id);	
		}

}
