package com.taotaoke.search.pojo;

import java.util.List;

public class SearchResult {

	// 商品列表
	List<Item> list;

	// 总条数
	private Long count;

	// 总页数
	private long pageCount;
	// 当前页
	private long curPage;
	public List<Item> getList() {
		return list;
	}
	public void setList(List<Item> list) {
		this.list = list;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	
}
