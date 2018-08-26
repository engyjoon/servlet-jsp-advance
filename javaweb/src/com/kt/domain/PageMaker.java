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
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}
	
	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public PageRange getPageRange() {
		return pageRange;
	}
	public void setPageRange(PageRange pageRange) {
		this.pageRange = pageRange;
	}

	private void calcData() {
		endPage = (int) (Math.ceil(pageRange.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		// 실제 마지막 페이지 번호
		int tempEndPage = (int) (Math.ceil(totalCount / (double) pageRange.getPerPageNum()));
		
		// displayPageNum 단위로 계산된 endPage 보정
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * pageRange.getPerPageNum() >= totalCount ? false : true;
	}
	
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + "]";
	}
	
}
