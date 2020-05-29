package com.example.demo.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberVo {

	private String mc_id;
	private String mc_pwd;
		private String salt;
	private String mc_email;
	private String mc_name;
	private String mc_nickname;
	private String mc_tel;
	private String mc_taste;
	private String mc_fname;
		private MultipartFile uploadFile;	
	private Date mc_regdate;
	private Date mc_recdate;
	private int mc_point;
	private int mc_auth;	
	
	/*

	아이디
	패스워드
	솔트
	이메일
	이름
	별명
	전화번호
	취향
	프로필
	가입일
	최근접속일
	포인트
	권한

	 */
	
}