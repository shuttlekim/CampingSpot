package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//예약취소확인
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsCVo {

	private String cs_name;
	private String cs_addr_remain;
	private String mc_tel;
	private String cr_type;
	private Date r_checkin;
	private Date r_checkout;
	private String mc_name;
	private Date r_date;
	private int mc_point;
	private int r_price;
	private String mc_id;
	private int r_no;
}
