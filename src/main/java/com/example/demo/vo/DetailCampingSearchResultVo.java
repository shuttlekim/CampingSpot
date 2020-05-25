package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailCampingSearchResultVo {

	private int cs_no;
	private String cs_name;
	private String cs_tel;
	private String cs_addr_head;
	private String cs_addr_remain;
	private String cs_url;
	private String cs_account;
	private String cs_camp_fname;
	private String cs_map_fname;
	private int cs_glamping_cnt;
	private int cs_caravan_cnt;
	private int cs_autocamping_cnt;
	private String cs_theme;
	private String cs_fac;
	private String cs_detail;
	private String mb_id;
	private String campingType;
	private String checkin;
	private String checkout;
	private int price;
	private double rate;
	private String weather;
	private double max_temp;
	private double min_temp;
}
