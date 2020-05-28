package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//예약내역확인리스트
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsRVo {

	private int cs_no;
	private String cs_name;
	private String cs_camp_fname;
	private String r_checkin;
	private String r_checkout;
	private String mc_id;
	private int r_no;
	private int cre_no;
	private int r_cancel;
}
