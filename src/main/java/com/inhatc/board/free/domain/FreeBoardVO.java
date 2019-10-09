package com.inhatc.board.free.domain;

import java.util.Date;

public class FreeBoardVO {
	private int fb_no;
	private int mem_no;
	private String mem_id;
	private String fb_title;
	private String fb_text;
	private Date fb_reg;
	private int fb_view;
	private String fb_type;
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
	public String getFb_title() {
		return fb_title;
	}
	public void setFb_title(String fb_title) {
		this.fb_title = fb_title;
	}
	public String getFb_text() {
		return fb_text;
	}
	public void setFb_text(String fb_text) {
		this.fb_text = fb_text;
	}
	public Date getFb_reg() {
		return fb_reg;
	}
	public void setFb_reg(Date fb_reg) {
		this.fb_reg = fb_reg;
	}
	public int getFb_view() {
		return fb_view;
	}
	public void setFb_view(int fb_view) {
		this.fb_view = fb_view;
	}
	public String getFb_type() {
		return fb_type;
	}
	public void setFb_type(String fb_type) {
		this.fb_type = fb_type;
	}
	
	
}
