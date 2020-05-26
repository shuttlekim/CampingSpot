package com.example.demo.vo;

import java.util.Date;

public class BoardReVo {

	private int br_no;
	private int b_no;
	private String mc_id;
	private String br_regdate;
	private String br_contents;
	private int br_del;
	
	public BoardReVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardReVo(int br_no, int b_no, String mc_id, String br_regdate, String br_contents, int br_del) {
		super();
		this.br_no = br_no;
		this.b_no = b_no;
		this.mc_id = mc_id;
		this.br_regdate = br_regdate;
		this.br_contents = br_contents;
		this.br_del = br_del;
	}

	public int getBr_no() {
		return br_no;
	}

	public void setBr_no(int br_no) {
		this.br_no = br_no;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getMc_id() {
		return mc_id;
	}

	public void setMc_id(String mc_id) {
		this.mc_id = mc_id;
	}

	public String getBr_regdate() {
		return br_regdate;
	}

	public void setBr_regdate(String br_regdate) {
		this.br_regdate = br_regdate;
	}

	public String getBr_contents() {
		return br_contents;
	}

	public void setBr_contents(String br_contents) {
		this.br_contents = br_contents;
	}

	public int getBr_del() {
		return br_del;
	}

	public void setBr_del(int br_del) {
		this.br_del = br_del;
	}
	
	
}
