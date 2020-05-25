package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
   
   //캠핑장 예약 상세페이지 요청
   @RequestMapping(value="/detailCampingSpot.do", produces = "application/json;charset=utf8")
   public String detailCampingSpot(int cs_no, String campingType, String checkout, String checkin) {
      String str = "";
      System.out.println("=========detailCampingSpot 컨트롤러 실행!===========");
      System.out.println("체크인날짜: "+checkin);
      System.out.println("체크아웃날짜: "+checkout);
      System.out.println("캠핑장번호: "+cs_no);
      System.out.println("캠핑타입: "+campingType);
      
      HashMap map = new HashMap();
      map.put("cs_no", cs_no);
      map.put("no", cs_no);
      map.put("campingType", campingType);
      map.put("checkout", checkout);
      map.put("checkin", checkin);
      
      //기본적인 정보들을 담는다.
      DetailCampingSearchResultVo detail = dao.detailCampingSearchResult(map);
      detail.setCampingType(campingType);
      detail.setCheckin(checkin);
      detail.setCheckout(checkout);
      
      //가격과 평점을 가져온다.
      int price = dao.getCampingSpot(map).getPrice();
      double rate = dao.getCampingSpot(map).getRate();
  
      System.out.println("가격: "+price);
      System.out.println("평점: "+rate);
     
      detail.setPrice(price);
      detail.setRate(rate);
      
      //해당 체크인날짜의 날씨정보를 기상청에서 크롤링해서 담아준다.
      /// 진행예정
      
      
      Gson gson = new Gson();
      str = gson.toJson(detail);
      
      return str;
   }
   
   // 캠핑장 번호와 캠핑타입으로 해당 캠핑룸들을 불러오도록 요청
   @RequestMapping(value="/callCampingRoomForReserve.do", produces = "application/json;charset=utf8")
   public String callCampingRoomForReserve(int cs_no, String CampingType) {
	   String str = "";
	   
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

}
