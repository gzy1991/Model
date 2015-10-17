package net.gslab.service;

import java.util.List;

import net.gslab.entity.Admin;
import net.gslab.entity.Teacher;
import net.gslab.setting.PageBean;

public interface TeacherService extends BaseService<Teacher> {
	 public Teacher getByID(int id);//获取管理员
}
