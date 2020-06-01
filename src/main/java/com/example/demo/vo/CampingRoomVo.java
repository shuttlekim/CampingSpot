package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampingRoomVo {

	private int cr_no;
	private String cr_name;
	private int cr_max;
	private String cr_checkin;
	private String cr_checkout;
	private int cr_price;
	private int cr_peak_price;
	private String cr_type;
	private int cs_no;
	private boolean empty;
	
}
