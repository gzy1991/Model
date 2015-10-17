package net.gslab.dao;

import java.io.Serializable;
import java.util.List;

import net.gslab.entity.News;
import net.gslab.entity.Teacher;
import net.gslab.setting.PageBean;

public interface TeacherDao extends BaseDao<Teacher>{
	
	public Teacher getTeacherById(int id);//获取老师
}
