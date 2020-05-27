package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberBusinessVo {
	
	private String mb_id;
	private String mb_pwd;
	private String salt;
	private String mb_email;
	private String mb_name;
	private String mb_nickname;
	private String mb_tel;
	private String mb_fname;
	private String mb_licence;
	private String mb_regdate;
	private String mb_recdate;
	private int mb_auth;
	
}
