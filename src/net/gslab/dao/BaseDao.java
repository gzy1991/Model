package net.gslab.dao;

import java.io.Serializable;
import java.util.List;

import net.gslab.setting.PageBean;

public interface BaseDao<T> {

	public abstract T load(Serializable id);

	public abstract List<T> loadAll();

	public abstract void save(T entity);

	public abstract boolean remove(T entity);

	public abstract void update(T entity);

	public abstract List<T> find(String hql);

	public abstract List<T> find(String hql, Object... params);
	public abstract Class<T> getEntityClass();
	public abstract int getCount(String hql);
	public abstract PageBean<T> getPage(int pageIndex,int pageSize);
    public abstract PageBean<T> getPage(final String hql,int pageIndex,int pageSize);

	public abstract T get(Serializable id);
	public void saveOrUpdate(T entity);
	
}