package net.gslab.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import net.gslab.dao.AdminDao;
import net.gslab.dao.MemberDao;
import net.gslab.dao.UserDao;
import net.gslab.entity.Admin;
import net.gslab.entity.Member;
import net.gslab.entity.Teacher;
import net.gslab.entity.User;

@SuppressWarnings("unchecked")
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{

	@Override
	public Admin getAdminById(int id) {

        System.out.println("in the dao_getAdminById(id)");
        Session session = getSession();//这里用了openSession()
		   //hql采取面向对象的思想，比如User类，userName是类中变量
        String hql = "from Admin m where m.adminId=?";
        Query query = session.createQuery(hql);
		  query.setParameter(0, id);
		  
        return  (Admin) query.uniqueResult();
	}

}
