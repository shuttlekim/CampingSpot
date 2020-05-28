package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.MypageDao;
import com.example.demo.vo.CrLVo;
import com.example.demo.vo.CrVo;
import com.example.demo.vo.CsCVo;
import com.example.demo.vo.CsDVo;
import com.example.demo.vo.CsRVo;
import com.example.demo.vo.ProfileVo;
import com.example.demo.vo.WishVo;
import com.google.gson.Gson;

@RestController
public class MypageController {

	@Autowired
	private MypageDao dao;

	public void setDao(MypageDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping(value = "/myReservationList.do", produces = "application/json;charset=UTF-8")
	public String myReservationList() {
		String str = "";
		List<CsRVo> list1 = dao.myReservationList();
		System.out.println("list의 첫번째 요소입니다. : "+ list1.get(0).getR_checkin());
		Gson gson = new Gson();
		str = gson.toJson(list1);
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "/reservation_after.do", produces = "application/json;charset=UTF-8")
	public String reservation_after() {
		String str = "";
		List<CsRVo> list2 = dao.reservation_after();
		Gson gson = new Gson();
		str = gson.toJson(list2);
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "/profile.do", produces = "application/json;charset=UTF-8")
	public String profile() {
		String str = "";
		List<ProfileVo> list3 = dao.profile();
		Gson gson = new Gson();
		str = gson.toJson(list3);
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "/myReservationDetail.do", produces = "application/json;charset=UTF-8")
	public String myReservationDetail(CsDVo cd) {
		String str = "";
		List<CsDVo> list4 = dao.myReservationDetail(cd);
		Gson gson = new Gson();
		str = gson.toJson(list4);
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "/requestCancel.do", produces = "application/json;charset=UTF-8")
	public String requestCancel(CsCVo csc) {
		String str = "";
		List<CsCVo> list5 = dao.requestCancel(csc);
		Gson gson = new Gson();
		str = gson.toJson(list5);
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "/myWishList.do", produces = "application/json;charset=UTF-8")
	public String WishList(WishVo wish) {
		String str = "";
		List<WishVo> list6 = dao.myWishList(wish);
		Gson gson = new Gson();
		str = gson.toJson(list6);
		System.out.println(str);
		return str;
	}

	@RequestMapping(value = "/myReviewList.do", produces = "application/json;charset=UTF-8")
	public String myReviewList(CrLVo crl) {
		String str = "";
		List<CrLVo> list7 = dao.myReviewList(crl);
		Gson gson = new Gson();
		str = gson.toJson(list7);
		System.out.println(str);
		return str;
	}

	@RequestMapping("/deleteMyReview")
	public String deleteMyReview(int cre_no) {
		String str = "ok";
		dao.deleteMyReview(cre_no);
		return str;
	}

	@RequestMapping("/deleteMyWish")
	public String deleteMyWish(int cw_no) {
		String str = "ok";
		dao.deleteMyWish(cw_no);
		return str;
	}
	
	@RequestMapping("/cancelReservation")
	public String cancelReservation(String mc_id) {
		System.out.println("컨트롤러 동작함");
		String str = "ok";
		dao.cancelReservation(mc_id);
		return str;
	}
	
	@RequestMapping("/camping_review_insert")
	public String camping_review_insert(CrVo cr) {
		String str ="ok";
		dao.camping_review_insert(cr);
		return str;
	}
	
}
