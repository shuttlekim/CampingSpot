package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ReservationDao;
import com.example.demo.vo.BossReservationVo;
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

	
	// 설아) (사업자) 사업자페이지 메인 예약목록
	@RequestMapping(value ="/businessMyPageReservationList.do", produces = "application/json;charset=UTF-8")
	public String businessMyPageReservationList(int cs_no) {
		String str = "";
		List<BossReservationVo> mpList = dao.businessMyPageReservationList(cs_no);
		Gson gson = new Gson();
		str = gson.toJson(mpList);
		return str;
	}
	
	// 설아) (사업자) 취소 승인 업데이트
	@RequestMapping("/updateCancelStatus.do")
	public String updateCancelStatus(int r_no) {
		String str ="취소 승인되었습니다.";
		int re = dao.updateCancelStatus(r_no);
		System.out.println("취소승인:"+re);
		return str;
	}
	
	// 설아) (사업자) 예약 승인 업데이트
	@RequestMapping("/updateReserveStatus.do")
	public String updateReserveStatus(int r_no) {
		String str ="예약 승인되었습니다.";
		int re = dao.updateReserveStatus(r_no);
		System.out.println("예약승인:"+re);
		return str;
	}
	
	// 설아) (사업자) 예약 관리 현황 목록보기
	@RequestMapping(value ="/bossReservationList.do", produces = "application/json;charset=UTF-8" )
	public String bossReservationList(int cs_no) {
		String str = "";
		List<BossReservationVo> bossRList = dao.bossReservationList(cs_no);
		Gson gson = new Gson();
		str = gson.toJson(bossRList);
		return str;
	}
	
	  //캠핑예약 등록
	  @RequestMapping(value="/insertReservation.do", produces="application/json;charset=utf8")
	  public String insertReservation(String mc_id, int cr_no, String checkout, String checkin, int price, String payment) {
		  String str = "";
		  System.out.println("======== 예약추가 컨트롤러 작동 ! ===========");
		  System.out.println("회원정보: "+mc_id);
		  System.out.println("캠핑룸번호: "+cr_no);
		  System.out.println("체크인: "+checkin);
		  System.out.println("체크아웃: "+checkout);
		  System.out.println("가격: "+price);
		  System.out.println("지불방법: "+payment);
		  
		  // 예약테이블에 넣기위한 map
		  HashMap map = new HashMap();
		  map.put("mc_id", mc_id);
		  map.put("cr_no", cr_no);
		  map.put("checkin", checkin);
		  map.put("checkout", checkout);
		  map.put("price", price);
		  map.put("payment", payment);
		  
		  // 예약번호를 찾기위한 r_map
		  HashMap r_map = new HashMap();
		  r_map.put("cr_no", cr_no);
		  r_map.put("checkin", checkin);
		  
		  int result1 = dao.insertReservation(map);
		  if(result1 > 0) {
			  System.out.println("예약테이블에 등록:"+map);
			  
			  //int r_no = dao.callReservationKey() - 1; // 시퀀스의 nextval 값이므로 무조건 -1 해준다. //에러발생가능!
			  //+++++++++++++++++++++++++++++++++++++++++++
			  int r_no = dao.callReservationKey(r_map); // 체크인날짜와 해당 룸번호로 예약번호를 구한다.
			  int cs_no = dao.getRoomInfo(cr_no).getCs_no();
			  String cr_type = dao.getRoomInfo(cr_no).getCr_type();
			  
			  	// 예약검색테이블에 넣기위한 map
			  	HashMap map_search = new HashMap();
			  	map_search.put("r_no", r_no);
			  	map_search.put("cr_no", cr_no);
			  	map_search.put("cs_no", cs_no);
			  	map_search.put("cr_type", cr_type);
			  	
			  	
			  	// 유효일자를 구하기위한 코드
			  	Date checkin_date = new Date();
				Date checkout_date = new Date();
				//체크인, 체크아웃 차이일수 : 숙박일수
				long calDateDays = 0;
				
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
				
					checkin_date = transFormat.parse(checkin);
					checkout_date = transFormat.parse(checkout);
					
					long calDate = checkout_date.getTime() - checkin_date.getTime();
					calDateDays = calDate / (24*60*60*1000);
		
					System.out.println("차이 일수 :" +calDateDays);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				//유효날짜를 구한다.
				System.out.println("-------유효 일수-------");
				Calendar cal = Calendar.getInstance();
				cal.setTime(checkin_date);
				
				String validDate1 = null;
				String validDate2 = null;
				String validDate3 = null;
				String validDate4 = null;
				
				//체크인정보가 null 이 아니고, ""(공백) 이 아니라면 유효일1에 정보를 넣는다.
				if((checkin!=null)&&(!checkin.equals(""))) {
					validDate1 = checkin;
					map_search.remove("validDate");
					map_search.put("validDate", validDate1);
					System.out.println(" 등록하기전에 마지막 map_search확인!:"+map_search);
					int re1 = dao.insertReserveSearch(map_search);
					if(re1 > 0) {
						System.out.println("유효일 ("+validDate1+")등록완료");
					}
				}
				if(calDateDays >= 2) {
					cal.add(Calendar.DATE,	1);
					validDate2 = transFormat.format(cal.getTime());
					map_search.remove("validDate");
					map_search.put("validDate", validDate2);
					int re2 = dao.insertReserveSearch(map_search);
					if(re2 > 0) {
						System.out.println("유효일 ("+validDate2+")등록완료");
					}
				}
				if(calDateDays >= 3) {
					cal.add(Calendar.DATE,	1);
					validDate3 = transFormat.format(cal.getTime());
					map_search.remove("validDate");
					map_search.put("validDate", validDate3);
					int re3 = dao.insertReserveSearch(map_search);
					if(re3 > 0) {
						System.out.println("유효일 ("+validDate3+")등록완료");
					}
				}
				if(calDateDays == 4) {
					cal.add(Calendar.DATE,	1);
					validDate4 = transFormat.format(cal.getTime());
					map_search.remove("validDate");
					map_search.put("validDate", validDate4);
					int re4 = dao.insertReserveSearch(map_search);
					if(re4 > 0) {
						System.out.println("유효일 ("+validDate4+")등록완료");
					}
				}
				
				
				System.out.println("유효일수 1:"+validDate1);
				System.out.println("유효일수 2:"+validDate2);
				System.out.println("유효일수 3:"+validDate3);
				System.out.println("유효일수 4:"+validDate4);
		  }else {
			  System.out.println("예약테이블 등록실패");
		  }
		  
		  return str;
	  }
	
	  //캠핑룸 번호로 캠핑룸정보 요청
	  @RequestMapping(value="/getRoomInfo.do", produces="application/json;charset=utf8")
	  public String getRoomInfo(int cr_no) {
		  String str = "";
		  System.out.println("======= getRoomInfo 컨트롤러 작동중 =========");
		  
		  str = new Gson().toJson(dao.getRoomInfo(cr_no));
		  
		  return str;
	  }

	  //캠핑장 예약 상세페이지 요청
	  @RequestMapping(value="/detailCampingSpot.do", produces = "application/json;charset=utf8")
	  public String detailCampingSpot(int cs_no, String campingType, String checkout, String checkin) {
		  String str = "";
		  System.out.println("=========detailCampingSpot 컨트롤러 실행!===========");
		  if(campingType.equals("undefined")) {
			  campingType = null;
		  }
		  if(checkin.equals("undefined")) {
			  checkin = null;
		  }
		  if(checkout.equals("undefined")) {
			  checkout = null;
		  }
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
			   list.get(i).setEmpty(true);
			   for(int j=0; j<ingList.size(); j++) {
				   if(list.get(i).getCr_no() == ingList.get(j).getCr_no()) {
						  list.get(i).setEmpty(false);
						  break;
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
