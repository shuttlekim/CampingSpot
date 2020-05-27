package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BossListSalesVo {

	
	private int cs_no;
	private int cr_no;
	private String cr_name;
	private int r_no;
	private Date r_checkin;
	private Date r_checkout;
	private int cr_price;
	private int cr_peak_price;
	private int r_price;
	private int comm;
	private int margin;
	private int r_cancel;
	private int days;
	
	//캠핑룸별
	private int totPrice;
	private int totComm;
	private int totMargin;

	// 월별 총합
	private int totR;
	private int totCM;
	private int totM;
	private int totCC;
	
	// 구글 챠트
	private int cnt;
		
	private String month;
}
