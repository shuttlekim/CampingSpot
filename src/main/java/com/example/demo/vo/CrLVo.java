package com.example.demo.vo;
  
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//캠핑후기 리스트
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrLVo {

	   private String cs_camp_fname;
	   private String cs_name;
	   private int cre_no;
	   private String cre_content;
	   private int cre_rate;
	   private String cre_date;
	   private String mc_id;
	   private String mb_id;
	   private String mc_nickname;
	   private String mb_nickname;
	   private int cs_no;
	   private String cre_re_contents;
	   
		// 댓글 확인을 위해 추가한 변수
		private int re_check;
		
}
