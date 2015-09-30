package net.gslab.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import net.gslab.dao.MemberDao;
import net.gslab.dao.UserDao;
import net.gslab.entity.Member;
import net.gslab.entity.User;

@SuppressWarnings("unchecked")
@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

	@Override
	public Member getUserByName(String username) {
		Session session = getSession();//这里用了openSession()
		//hql采取面向对象的思想，比如User类，userName是类中变量
		String hql = "select n from Member m where m.memberName=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		return (Member) query.uniqueResult();
	}

	@Override
	public Member getUserByLoadName(String loadname) {
		// TODO Auto-generated method stub
		Session session = getSession();//这里用了openSession()
		//hql采取面向对象的思想，比如User类，userName是类中变量
		String hql = "from Member m where m.loadname=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, loadname);
		return (Member) query.uniqueResult();
		
	}
	
}
