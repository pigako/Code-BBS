package com.inhatc.board.free.domain;

import java.util.Date;

public class FreeBoardCommentVO {
	private int fbc_no;
	private int fb_no;
	private int mem_no;
	private String mem_id;
	private String fbc_text;
	private Date fbc_reg;
	
	public int getFbc_no() {
		return fbc_no;
	}
	public void setFbc_no(int fbc_no) {
		this.fbc_no = fbc_no;
	}
	public int getFb_no() {
		return fb_no;
	}
	public void setFb_no(int fb_no) {
		this.fb_no = fb_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getFbc_text() {
		return fbc_text;
	}
	public void setFbc_text(String fbc_text) {
		this.fbc_text = fbc_text;
	}
	public Date getFbc_reg() {
		return fbc_reg;
	}
	public void setFbc_reg(Date fbc_reg) {
		this.fbc_reg = fbc_reg;
	}
	
	
}
