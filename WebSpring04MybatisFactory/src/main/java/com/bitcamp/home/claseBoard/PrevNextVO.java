package com.bitcamp.home.claseBoard;

/**
 * @author lssgt
 *
 */
public class PrevNextVO {
	private int prevNo; //이전글번호
	private String prevSubject;//이전글제목
	private int nextNo;//다음글 번호
	private String nextSubject;//다음글 제목
	
	public int getPrevNo() {
		return prevNo;
	}
	public void setPrevNo(int prevNo) {
		this.prevNo = prevNo;
	}
	public String getPrevSubject() {
		return prevSubject;
	}
	public void setPrevSubject(String prevSubject) {
		this.prevSubject = prevSubject;
	}
	public int getNextNo() {
		return nextNo;
	}
	public void setNextNo(int nextNo) {
		this.nextNo = nextNo;
	}
	public String getNextSubject() {
		return nextSubject;
	}
	public void setNextSubject(String nextSubject) {
		this.nextSubject = nextSubject;
	}
	
	
}
