package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampingReviewVo {

	
   private int cre_no;
   private String cre_content;
   private int cre_rate;
   private Date cre_date;
   private String mc_id;
   private String mb_id;
   private int cs_no;
   private String cre_re_contents;
   
	// 댓글 확인을 위해 추가한 변수
	private int re_check;
   
}
