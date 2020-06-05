package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardReVo {

	private int br_no;
	private int b_no;
	private String mc_id;
	private String br_regdate;
	private String br_contents;
	private int br_del;
	
}
