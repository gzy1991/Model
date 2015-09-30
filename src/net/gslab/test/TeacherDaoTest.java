package net.gslab.test;

import javax.annotation.Resource;

import net.gslab.dao.TeacherDao;
import net.gslab.entity.Teacher;

import org.junit.Test;

public class TeacherDaoTest extends JUnitDaoBase{
	@Resource(name="teacherDaoImpl") 
    private TeacherDao teacherDao; 
	@Test
    public void updateTest()
    {
    	Teacher t=teacherDao.load(1);
    	System.out.println(t.getPassword());
    	t.setPassword("1");
    	teacherDao.update(t);
    }
}
