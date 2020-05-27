package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationVo {
	
	
	private int r_no;
	private String r_checkin;
	private String r_checkout;
	private String r_date;
	private int r_price;
	private String r_payment;
	private int revstatus;
	private int r_cancel;
	private int cr_no;
	private String mc_id;
}
