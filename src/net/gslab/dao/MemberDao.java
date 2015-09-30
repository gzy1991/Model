package net.gslab.dao;




import net.gslab.entity.Member;


public interface MemberDao extends BaseDao<Member>{

	Member getUserByName(String username);

	Member getUserByLoadName(String loadname);


}
