package net.gslab.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import net.gslab.dao.TopicDao;
import net.gslab.entity.Topic;
import net.gslab.setting.PageBean;




  
public class TopicDaoTest extends JUnitDaoBase{
	@Resource(name="topicDaoImpl")
	private TopicDao topicDaoImpl;
	
	@Test
	public void test(){
	Topic t=new Topic();
	t.setTopicId(1);
	
	}

}
