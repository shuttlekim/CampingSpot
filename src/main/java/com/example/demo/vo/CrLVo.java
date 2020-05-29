package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//캠핑후기 리스트
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrLVo {

	private int cre_no;
	private String cre_date;
	private int cre_rate;
	private String cs_name;
	private String cs_camp_fname;
	private String cre_content;
	private String mc_id;
	private String mb_id;
	private String cre_re_contents;
	
}
