package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampingSearchResultVo {

	private String name;
	private int no;
	private int price;
	private double rate;
	private String addr_head;
	private String addr_remain;
	private String detail;
	private String tel;
	private String theme;
	private String fac;
	private String camp_fname;
}
