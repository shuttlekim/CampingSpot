package com.example.demo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//위시리스트
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishVo {
	
	private int cw_no;
	private String cs_name;
	private String cs_camp_fname;
	private String cs_addr_remain;
	private String mc_id;
	//private int cre_no;
	private int cs_no;
	
}
