package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FestivalDetailVo {

	private String title;
	private String period;
	private String location;
	private String tel;
	private String host;
	private String addr;
	private String detail;
	private String fname;
}
