package com.seoul.home;

public class PageVO {
	private int pageNum=1; // 현재 페이지
	private int onePageNum=5;// 한 페이지당 번호 수
	private int onePageRecord=5;// 한 페이지당 레코드 수
	   
	private int totalRecord;// 총 레코드 수
	private int totalPage;// 총 페이지 수 (마지막 페이지)
	private int startPageNum=1;// 시작 페이지
	   
	private int lastPageRecord=5;// 마지막 페이지에 남은 레코드 수

	public PageVO() {}
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		// 시작페이지 번호를 계산
	    startPageNum = ((pageNum-1)/onePageNum)*onePageNum+1;
	}

	public int getOnePageNum() {
		return onePageNum;
	}

	public void setOnePageNum(int onePageNum) {
		this.onePageNum = onePageNum;
	}

	public int getOnePageRecord() {
		return onePageRecord;
	}

	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int)Math.ceil(totalRecord/(double)onePageRecord);
		
		//마지막 페이지에 남은 레코드 수
		if(totalRecord%onePageRecord==0) {
			lastPageRecord = onePageRecord;
		}else {
			lastPageRecord = totalRecord%onePageRecord;
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}

	public int getLastPageRecord() {
		return lastPageRecord;
	}

	public void setLastPageRecord(int lastPageRecord) {
		this.lastPageRecord = lastPageRecord;
	}
	
	
}
