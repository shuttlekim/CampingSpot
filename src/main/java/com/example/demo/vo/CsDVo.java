package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//예약정보확인
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsDVo {

	private String cs_camp_fname;
	private double cs_lati;
	private double cs_long;
	private String cs_name;
	private String r_checkin;
	private String r_checkout;
	private String cr_type;
	private String cs_addr_remain;
	private String mc_name;
	private String mc_tel;
	private String mc_email;
	private String r_date;
	private int r_price;
	private String mc_id;
	private int r_no;
}
