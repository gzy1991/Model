package net.gslab.service;

import java.util.List;

import net.gslab.entity.Admin;
import net.gslab.entity.News;
import net.gslab.entity.Teacher;
import net.gslab.setting.PageBean;

public interface AdminService extends BaseService<Admin> {
	public Admin getByID(int id);//获取管理员
	PageBean<Admin> getPage(int pageIndex);
}
