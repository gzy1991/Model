package net.gslab.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import net.gslab.dao.TeacherDao;
import net.gslab.entity.Member;
import net.gslab.entity.News;
import net.gslab.entity.Teacher;
@Repository("teacherDaoImpl")
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao{

	@Override
	public Teacher getTeacherById(int id) {
		// TODO Auto-generated method stub
		
          System.out.println("in the dao_getTeacherById(id)");
          Session session = getSession();//这里用了openSession()
  		   //hql采取面向对象的思想，比如User类，userName是类中变量
          String hql = "from Teacher m where m.teacherId=?";
          Query query = session.createQuery(hql);
  		  query.setParameter(0, id);
		  
          return  (Teacher) query.uniqueResult();
	}

}
