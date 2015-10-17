/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.gslab.setting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.gslab.entity.User;

public class PageBean<T> implements Serializable {
	private static final long serialVersionUID = -3930180379790344299L;

	private List<T> data;              // List数据
	private int totalCount;      		//总记录数
    private int pageSize;             //每页的记录数
    private int pageIndex;
    private int mxIndex;
	public PageBean(int totalCount,List data,int pageSize,int pageIndex){
		this.totalCount = totalCount;
		this.data = data;
		this.pageSize=pageSize;
		this.pageIndex=pageIndex;
		mxIndex=(totalCount-1)/pageSize+1;
	}
	
	

	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
	/**
	 * @return the data
	 */
	public List getData() {
		return data;
	}

	


	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
	
	public int getTotalCount() {
		return totalCount;
	}



	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	public int getPageIndex() {
		
		return pageIndex;
	}
	public int getReasonableIndex(int index)
	{
		if(index>mxIndex)
			index=mxIndex;
		if(index<1) index=1;
		return index;
	}



	public int getMxIndex() {
		return mxIndex;
	}



	public void setMxIndex(int mxIndex) {
		this.mxIndex = mxIndex;
	}


	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getFirstPage(String uri,String []names,String []params)
	{
		uri+="?";
		for(int i=0;i<names.length;i++)
			if(params[i]!=null)
				uri+=names[i]+"="+params[i]+"&";
		return uri;
	}
	public String getPrePage(String uri,String []names,String []params)
	{
		uri+="?";
		for(int i=0;i<names.length;i++)
			if(params[i]!=null)
				uri+=names[i]+"="+params[i]+"&";
		return uri+"pg="+getReasonableIndex(pageIndex-1);
	}
	public String getNextPage(String uri,String []names,String []params)
	{
		uri+="?";
		for(int i=0;i<names.length;i++)
			if(params[i]!=null)
				uri+=names[i]+"="+params[i]+"&";
		return uri+"pg="+getReasonableIndex(pageIndex+1);
	}
	public String getLastPage(String uri,String []names,String []params)
	{
		uri+="?";
		for(int i=0;i<names.length;i++)
			if(params[i]!=null)
				uri+=names[i]+"="+params[i]+"&";
		return uri+"pg="+mxIndex;
	}
	

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}