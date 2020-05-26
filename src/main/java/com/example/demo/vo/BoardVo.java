package com.example.demo.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVo {
	private int b_no;
	private String b_title;
	private String mc_id;
	private String b_regdate;
	private int b_hit;
	private String b_contents;
	private String b_fname;
	private int b_del;
	
	private MultipartFile uploadFile;

	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardVo(int b_no, String b_title, String mc_id, String b_regdate, int b_hit, String b_contents,
			String b_fname, int b_del, MultipartFile uploadFile) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.mc_id = mc_id;
		this.b_regdate = b_regdate;
		this.b_hit = b_hit;
		this.b_contents = b_contents;
		this.b_fname = b_fname;
		this.b_del = b_del;
		this.uploadFile = uploadFile;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getMc_id() {
		return mc_id;
	}

	public void setMc_id(String mc_id) {
		this.mc_id = mc_id;
	}

	public String getB_regdate() {
		return b_regdate;
	}

	public void setB_regdate(String b_regdate) {
		this.b_regdate = b_regdate;
	}

	public int getB_hit() {
		return b_hit;
	}

	public void setB_hit(int b_hit) {
		this.b_hit = b_hit;
	}

	public String getB_contents() {
		return b_contents;
	}

	public void setB_contents(String b_contents) {
		this.b_contents = b_contents;
	}

	public String getB_fname() {
		return b_fname;
	}

	public void setB_fname(String b_fname) {
		this.b_fname = b_fname;
	}

	public int getB_del() {
		return b_del;
	}

	public void setB_del(int b_del) {
		this.b_del = b_del;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	
	
}
