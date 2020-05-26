package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminNoticeVo {
	
	private int an_no;
	private String an_title;
	private String an_content;
	private String an_regdate;
	private String mc_id;
	private String ispost;
	private String startdate;
	private String enddate;
	private int mc_auth;
	

}
