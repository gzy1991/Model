package net.gslab.dao.impl;

import org.springframework.stereotype.Repository;

import net.gslab.dao.TeacherDao;
import net.gslab.entity.Teacher;
@Repository("teacherDaoImpl")
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao{

}
