package net.gslab.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import net.gslab.dao.MemberDao;
import net.gslab.dao.TeacherDao;
import net.gslab.entity.Member;

import org.junit.Test;

public class MemberDaoTest extends JUnitDaoBase {
	@Resource(name="memberDaoImpl")
	private MemberDao memberDaoImpl;
	@Test
	public void test() {
		Member m=new Member();
		m.setMemberId(123);
		m.setLoadname("123");
		m.setPassword("ad");
		memberDaoImpl.save(m);
	}

}
