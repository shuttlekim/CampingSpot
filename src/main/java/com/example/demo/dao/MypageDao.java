package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.CrLVo;
import com.example.demo.vo.CrVo;
import com.example.demo.vo.CsCVo;
import com.example.demo.vo.CsDVo;
import com.example.demo.vo.CsRVo;
import com.example.demo.vo.ProfileVo;
import com.example.demo.vo.WishVo;

@Repository
public class MypageDao {

	public List<CsRVo> myReservationList(String mc_id) {
		return DBManager.myReservationList(mc_id);
	}

	public List<CsRVo> reservation_after() {
		return DBManager.resrvation_after();
	}

	public List<ProfileVo> profile() {
		return DBManager.profile();
	}

	public List<CsDVo> myReservationDetail(CsDVo cd) {
		return DBManager.myReservationDetail(cd);
	}

	public List<CsCVo> requestCancel(CsCVo csc) {
		return DBManager.requestCancel(csc);
	}

	public List<WishVo> myWishList(WishVo wish) {
		return DBManager.myWishList(wish);
	}

	public List<CrLVo> myReviewList(CrLVo crl) {
		return DBManager.myReviewList(crl);
	}
	
	public int deleteMyReview(int cre_no) {
		return DBManager.deleteMyReview(cre_no);
	}
	
	public int deleteMyWish(int cw_no) {
		return DBManager.deleteMyWish(cw_no);
	}
	
	public int cancelReservation(String mc_id) {
		return DBManager.cancelReservation(mc_id);
	}
	
	public int camping_review_insert(CrVo cr) {
		return DBManager.camping_review_insert(cr);
	}
}
