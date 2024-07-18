package com.board.www.dto;

import java.sql.Date;

public class BoardDTO {
	// Board 객체 처리 / setter & getter
	
	// 필드
	private  int bno;
	private  String btitle;
	private  String bcontent;
	private  String bwriter;
	private   Date bDate;

	public BoardDTO() {
		// 기본생성자
	}

	public BoardDTO(int bno, String btitle, String bcontent, String bwriter, Date bDate) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bDate = bDate;
	}

	public int getBno() {
		return bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public String getBwriter() {
		return bwriter;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	
	
	
}
