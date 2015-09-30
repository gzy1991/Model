package net.gslab.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.gslab.dao.AdminDao;
import net.gslab.entity.Admin;
import net.gslab.entity.Teacher;
import net.gslab.service.AdminService;
import net.gslab.service.TeacherService;

@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{
	@Resource(name="adminDaoImpl")
	private AdminDao adminDao;
	
}
