package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dao.CampingReviewReDao;
import com.example.demo.vo.CampingReviewReVo;
import com.example.demo.vo.CampingReviewVo;
import com.google.gson.Gson;

@RestController
public class CampingReviewReController {
	
	@Autowired
	private CampingReviewReDao crrDao;

	public void setCrrDao(CampingReviewReDao crrDao) {
		this.crrDao = crrDao;
	}

	// 설아) 댓글 상세보기
	@RequestMapping(value = "/getCampingReviewRe.do",produces = "application/json;charset=UTF-8")
	public String getCampingReviewRe(int cre_no) {
		String str = "";
		CampingReviewReVo crrVo = crrDao.getCampingReviewRe(cre_no);
		Gson gson = new Gson();
		str = gson.toJson(crrVo);
		return str;
	}
	
	// 설아) (사업자) 캠핑 리뷰 댓글 삭제
	@RequestMapping("/bossDeleteCampingReviewRe.do")
	public String bossDeleteCampingReviewRe(int cre_re_no) {
		String str = "댓글 삭제를 실패하였습니다.";
		int re = crrDao.bossDeleteCampingReviewRe(cre_re_no);
		if ( re > 0) {
			str = "댓글 삭제를 성공하였습니다.";
			System.out.println("댓글삭제:"+re);
		}
		return str; 
	}
	
	// 설아) (사업자) 캠핑 리뷰 댓글 등록
	@RequestMapping("/bossInsertCampinReviewRe.do")
	public String bossInsertCampingReviewRe(CampingReviewReVo crrVo) {
		String str = "댓글 등록이 완료되었습니다.";
		int cre_re_no = crrDao.nextNo();
		crrVo.setCre_re_no(cre_re_no);
		
		int re = crrDao.bossInsertCampingReviewRe(crrVo);
		if(re < 0) {
			str = "댓글 등록이 실패하였습니다.";
		}
		System.out.println("댓글 등록:" + re);
		return str;
	}

	// 설아) (사업자) 캠핑리뷰 관리 목록
	@RequestMapping(value = "/bossCampingReviewList.do", produces = "application/json;charset=UTF-8")
	public String bossCampingReviewList(int cs_no) {
		String str = "";
		List<CampingReviewVo> creList = crrDao.bossCampingReviewList(cs_no);
		
		// 댓글 확인 
		for(int i=0; i<creList.size(); i++) {
			//System.out.println(creList.get(i).getRe_check());
			CampingReviewVo creVo = creList.get(i);
			int cre_no = creVo.getCre_no();
			CampingReviewReVo crrVo = crrDao.checkReviewRe(cre_no);
			if(crrVo != null) {
				creVo.setRe_check(1);
				creList.set(i, creVo);
			}
		}
		
		Gson gson = new Gson();
		str = gson.toJson(creList);
		//System.out.println(creList);
		return str;
	}
}
