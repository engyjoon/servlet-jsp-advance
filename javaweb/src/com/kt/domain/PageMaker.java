package com.kt.domain;

public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private PageRange pageRange;
	
	public int getTotalCount() {
		return this.totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	public int getStartPage() {
		return this.startPage;
	}
	
	public int getEndPage() {
		return this.endPage;
	}
	
	public boolean isPrev() {
		return this.prev;
	}
	
	public boolean isNext() {
		return this.next;
	}
	
	public int getDisplayPageNum() {
		return this.displayPageNum;
	}
	
	public PageRange getPageRange() {
		return this.pageRange;
	}
	public void setPageRange(PageRange pageRange) {
		this.pageRange = pageRange;
	}
	
	private void calcData() {
		endPage = (int) (Math.ceil(pageRange.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int) (Math.ceil(totalCount / (double) pageRange.getPerPageNum()));
		
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * pageRange.getPerPageNum() >= totalCount ? false : true;
	}
}
