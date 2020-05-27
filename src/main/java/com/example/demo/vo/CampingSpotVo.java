package com.example.demo.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampingSpotVo {

	private int cs_no;
	private String cs_name;
	private String cs_tel;
	private String cs_addr_head;
	private String cs_addr_remain;
	private String cs_url;
	private String cs_account;
	private String cs_licence_fname;
	private String cs_camp_fname;
	private String cs_map_fname;
	private double cs_lati;
	private double cs_long;
	private int cs_glamping_cnt;
	private int cs_caravan_cnt;
	private int cs_autocamping_cnt;
	private String cs_theme;
	private String cs_fac;
	private String cs_detail;
	private String mb_id;
	
	private MultipartFile uploadFile;
	private MultipartFile CampingUploadFile;
	private MultipartFile MapUploadFile;
	
}
