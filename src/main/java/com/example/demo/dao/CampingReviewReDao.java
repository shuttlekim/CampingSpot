package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.bossDBManager;
import com.example.demo.vo.CampingReviewReVo;
import com.example.demo.vo.CampingReviewVo;

@Repository
public class CampingReviewReDao {
	
	// 설아) (사업자) 댓글 상세보기
	public CampingReviewReVo getCampingReviewRe(int cre_no) {
		return bossDBManager.getCampingReviewRe(cre_no);
	}
	
	// 설아) 캠핑 리뷰 댓글 확인
	public CampingReviewReVo checkReviewRe(int cre_no) {
		return bossDBManager.checkReviewRe(cre_no);
	}
	
	// 설아) 캠핑 리뷰 댓글 삭제
	public int bossDeleteCampingReviewRe(int cre_re_no) {
		return bossDBManager.bossDeleteCampingReviewRe(cre_re_no);
	}
	
	// 설아) 캠핑 리뷰 댓글 번호
	public int nextNo() {
		return bossDBManager.nextNo();
	}

	// 설아) (사업자) 리뷰 댓글 등록
	public int bossInsertCampingReviewRe(CampingReviewReVo crrVo) {
		return bossDBManager.bossInsertCampingReviewRe(crrVo);
	}
	
	// 설아) (사업자) 리뷰 관리 목록
	public List<CampingReviewVo> bossCampingReviewList(int cs_no){
		return bossDBManager.bossCampingReviewList(cs_no);
	}

}
