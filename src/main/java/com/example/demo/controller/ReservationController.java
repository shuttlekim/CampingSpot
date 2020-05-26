package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ReservationDao;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.DetailCampingSearchResultVo;
import com.google.gson.Gson;

@RestController
public class ReservationController {

	@Autowired
	private ReservationDao dao;
	
	public void setDao(ReservationDao dao) {
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
	      System.out.println(str);
	      
	      return str;
	   }
	   
	   // 캠핑장 번호와 캠핑타입으로 해당 캠핑룸들을 불러오도록 요청
	   @RequestMapping(value="/callCampingRoomForReserve.do", produces = "application/json;charset=utf8")
	   public String callCampingRoomForReserve(int cs_no, String campingType, String checkin, String checkout) {
		   String str = "";
		   System.out.println("======= callCampingRoomForReserve 컨트롤러 작동! ========");
		   System.out.println("캠핑장 번호: "+cs_no);
		   System.out.println("캠핑타입: "+campingType);
		   System.out.println("체크인날짜: "+checkin);
		   System.out.println("체크아웃날짜: "+checkout);
		   
		   HashMap map = new HashMap();
		   map.put("cs_no", cs_no);
		   map.put("campingType", campingType);
		   map.put("checkout", checkout);
		   map.put("checkin", checkin);
		   
		   //캠핑장번호와 캠핑타입으로 해당하는 전체룸을 불러온다.
		   List<CampingRoomVo> list = dao.selectorRoom(map);
		   
		   //체크인 체크아웃 사이에 이미 이용중인 룸을 불러온다.
		   List<CampingRoomVo> ingList = dao.ingRoom(map);
		   
		   for(int i=0; i<list.size(); i++) {
			   for(int j=0; j<ingList.size(); j++) {
				  if(list.get(i).getCr_no() == ingList.get(j).getCr_no()) {
					  list.get(i).setEmpty(false);
					  break;
				  }else {
					  list.get(i).setEmpty(true);
				  }
			   }
		   }
		   
		   Gson gson = new Gson();
		   str = gson.toJson(list);
		   System.out.println(str);
		   
		   return str;
	   }
	   
	   // 지연 :로그인한 회원의 포인트 가져오기
	   @RequestMapping("/selPoint")
		public int selPoint(String mc_id) {
			int re = dao.selPoint(mc_id);
			return re;
		}
	   
}
