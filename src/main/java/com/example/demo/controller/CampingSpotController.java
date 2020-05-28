package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.demo.dao.CampingSpotDao;
import com.example.demo.db.DBManager;
import com.example.demo.vo.CampingReviewVo;
import com.example.demo.vo.CampingSpotVo;
import com.example.demo.vo.CampingWishVo;
import com.example.demo.vo.DetailCampingSearchResultVo;
import com.google.gson.Gson;

@RestController
public class CampingSpotController {
   
   @Autowired
   private CampingSpotDao dao;
   

   public void setDao(CampingSpotDao dao) {
	   this.dao = dao;
   }
   
   // 캠핑장번호로 캠핑장정보 요청
   @RequestMapping(value="/getCampingInfo.do", produces = "application/json;charset=UTF-8")
   public String getCampingInfo(int cs_no) {
	   System.out.println("====== getCampingInfo 컨트롤러 작동중 =======");
	   String str = "";
	   
	   str = new Gson().toJson(dao.getCampingInfo(cs_no));
	   return str;
   }

   // 캠핑장 예약 상세페이지 리뷰목록 요청
   @RequestMapping(value="/campingReviewList.do", produces = "application/json;charset=utf8")
   public String campingReviewList(int cs_no) {
      String str = "";
      //System.out.println("현재 cs_no : "+cs_no);

      Gson gson = new Gson();
      str = gson.toJson(dao.campingReviewList(cs_no));
      
      //System.out.println(str);
      
      return str;
   }
   /* 밑에 다른 메소드로 수정합니다(영현)
   @RequestMapping("/campingInsertWish.do")
   public String insertWish(CampingWishVo w) {
      String str = "";
      
      System.out.println("추가하는 위시항목 CampingWishVo : " +  w);
      
      int re = dao.insertWish(w);
      
      if(re > 0) {
         str = "찜 목록에 추가하였음";
      }
      
      return str;
   }
   */
   
   // 영현) 위시리스트 추가
   @RequestMapping("/insertWishList.do")
   public String insertWishList(String mc_id, int cs_no) {
	   String str = "false";
	   System.out.println("======== insertWishList 컨트롤러 작동 ! ========");
	   System.out.println("사용자 id: "+ mc_id);
	   System.out.println("캠핑장번호: "+ cs_no);
	   
	   HashMap map = new HashMap();
	   map.put("mc_id", mc_id);
	   map.put("cs_no", cs_no);
	   
	   //중복체크를위해 이미 결과값이 있다면 c 에 저장
	   CampingWishVo c = dao.checkDouble(map);
	   
	   if (c == null) {
		   int re = dao.insertWish(map);
		   
		   if(re > 0) {
			   str = "success";
		   }
	   }else {
		   str = "double";
	   }
	   
	   return str;
   }
   

}
