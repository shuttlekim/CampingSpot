package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLogVo {
	
	private int al_no;
	private String al_regdate;
	private int al_cancel;
	private String al_ip;
	private int r_no;
	

}
