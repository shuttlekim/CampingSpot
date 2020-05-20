package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveSearchVo {

	private int rs_no;
	private int r_no;
	private String rs_date;
	private int cr_no;
	private int  cs_no;
	private String cr_type;

}
