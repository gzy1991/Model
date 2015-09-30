package net.gslab.dao;

import net.gslab.entity.Board;

public interface BoardDao extends BaseDao<Board>{

	//返回论坛板块总数
	public abstract long getBoardNum();

}