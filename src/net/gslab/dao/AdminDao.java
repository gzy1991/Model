package net.gslab.dao;
import net.gslab.entity.Admin;
import net.gslab.entity.Member;
import net.gslab.entity.Teacher;

public interface AdminDao extends BaseDao<Admin>{
	public Admin getAdminById(int id);//获取admin
}
