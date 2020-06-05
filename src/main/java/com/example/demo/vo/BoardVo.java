package com.example.demo.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
}
