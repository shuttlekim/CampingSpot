package com.example.demo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCustomerVo {
	
	private String mc_id;
	private String mc_pwd;
	private String mc_salt;
	private String mc_email;
	private String mc_name;
	private String mc_nickname;
	private String mc_tel;
	private String mc_taste;
	private String mc_fname;
	private String mc_regdate;
	private String mc_recdate;
	private int mc_point;
	private int mc_auth;
	

}
