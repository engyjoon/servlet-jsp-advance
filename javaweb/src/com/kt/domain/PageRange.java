package com.kt.domain;

public class PageRange {

	private int page;
	private int perPageNum;
	
	public PageRange() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public int getPage() {
		return this.page;
	}
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public int getPerPageNum() {
		return this.perPageNum;
	}
	
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPageStart() {
		return (page - 1) * perPageNum;
	}
}
