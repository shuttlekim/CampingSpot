package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FestivalListVo {

	private String title;
	private String period;
	private String addr;
	private String detailHead;
	private String detailAll;
	private String ingStatus;
	private String url;
	private String fname;
}
