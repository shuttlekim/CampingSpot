package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//캠핑리뷰 insert
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrVo {

	private int cre_no;
	private String cre_content;
	private int cre_rate;
	private Date cre_date;
	private String mc_id;
	private int cs_no;
	
	
}
