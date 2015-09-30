package net.gslab.test;

import javax.annotation.Resource;
import net.gslab.dao.impl.BoardDaoImpl;
import net.gslab.entity.Board;
import org.junit.Test;

public class BoardDaoTest extends JUnitDaoBase {  
	@Resource(name="boardDaoImpl") 
    private BoardDaoImpl boardDao;  
	
	@Test  
    public void testBorderDao() {  
    	Board b= new Board();
    	b.setBoardId(5);
    	b.setBoardName("hello");
    	boardDao.save(b);
    }  
}  