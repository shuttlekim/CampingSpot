package com.example.demo.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BusinessVo {

	
		private String mb_id;
		private String mb_pwd;
			private String salt;
		private String mb_email;
		private String mb_name;
		private String mb_nickname;
		private String mb_tel;
		private String mb_licence;
		private String mb_fname;
			private MultipartFile uploadFile;	
		private Date mb_regdate;
		private Date mb_recdate;
		private int mb_auth;	
		
		/*

		아이디
		패스워드
		솔트
		이메일
		이름
		별명
		전화번호
		사업자등록번호
		프로필
		가입일
		최근접속일
		권한

		 */
}