package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BossReservationVo {

	private int r_no;
	private String mc_name;
	private String mc_tel;
	private String cr_name;
	private Date r_date;
	private Date r_checkin;
	private int r_price;
	private int r_revstatus;
	private int r_cancel;
	private int cs_no;
	
	private Date rs_date;
}
