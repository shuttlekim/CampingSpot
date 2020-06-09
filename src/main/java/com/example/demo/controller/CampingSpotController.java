package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dao.CampingSpotDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.db.DBManager;
import com.example.demo.vo.BossListSalesVo;
import com.example.demo.vo.CampingReviewVo;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSpotVo;
import com.example.demo.vo.CampingWishVo;
import com.example.demo.vo.DetailCampingSearchResultVo;
import com.example.demo.vo.MemberVo;
import com.google.gson.Gson;

@RestController
public class CampingSpotController {
   
   @Autowired
   private CampingSpotDao dao;
   
   @Autowired
   private MemberDao mdao;
   
   public void setMdao(MemberDao mdao) {
	   this.mdao = mdao;
   }

   public void setDao(CampingSpotDao dao) {
	   this.dao = dao;
   }
   
	// 설아) (사업자) 캠핑장 상세보기
	@RequestMapping(value = "/bossGetCampingSpot.do", produces = "application/json;charset=UTF-8")
	public String bossGetCampingSpot(int cs_no) {
		String str = "";
		CampingSpotVo csVo = dao.bossGetCampingSpot(cs_no);
		Gson gson = new Gson();
		str = gson.toJson(csVo);
		return str;
	}
  
  // 설아) (사업자) cs_no 번호 가져오기
  @RequestMapping(value = "/getCsNo.do", produces = "application/json;charset=UTF-8")
  public String getCsNo(String mb_id) {
	   String str = "";
	   CampingSpotVo csvo = dao.getCsNo(mb_id);
	   Gson gson = new Gson();
	   str = gson.toJson(csvo);
	   return str;
  }
   
	// 설아) (사업자) 매출 현황 챠트
	@RequestMapping(value = "/bossChart.do", produces = "application/json;charset=UTF-8")
	public String bossChart(int cs_no) {
		String str = "";
		List<BossListSalesVo> chartList = dao.bossChart(cs_no);
		Gson gson = new Gson();
		str = gson.toJson(chartList);
		return str;
	}
   
	// 설아) (사업자) 월별 매출 총합
	@RequestMapping(value = "/bossListTotalMonth.do", produces = "application/json;charset=UTF-8")
	public String bossListTotalMonth(int cs_no, String month) {
		String str = "";
		HashMap map = new HashMap();
		map.put("cs_no", cs_no);
		map.put("month", month);
		BossListSalesVo svo = dao.bossListTotalMonth(map);
		Gson gson = new Gson();
		str = gson.toJson(svo);
		return str;
	}
   
	// 설아) (사업자) 캠핑물별 매출 총합 list
	@RequestMapping(value = "/bossListTotalCampingRoom.do", produces = "application/json;charset=UTF-8")
	public String bossListTotalCampingRoom(int cs_no, String month) {
		String str = "";
		HashMap map = new HashMap();
		map.put("cs_no", cs_no);
		map.put("month", month);
		List<BossListSalesVo> totalList = dao.bossListTotalCampingRoom(map);
		Gson gson = new Gson();
		str = gson.toJson(totalList);
		return str;
	}
   
	// 설아) (사업자) 매출 현황 목록보기
	@RequestMapping(value = "/bossListSales.do", produces = "application/json;charset=UTF-8")
	public String bossListSales(int cs_no, String month) {
		String str ="";
		HashMap map = new HashMap();
		map.put("cs_no", cs_no);
		map.put("month", month);
		List<BossListSalesVo> salesList = dao.bossListSales(map);
		
		for(int i =0; i <salesList.size(); i++ ) {
			BossListSalesVo svo =  salesList.get(i);
			int r_cancel = svo.getR_cancel();
			int r_price = svo.getR_price();
			if(r_cancel > 0) {
				svo.setR_cancel(r_price);
				svo.setR_price(0);
				svo.setComm(0);
				svo.setMargin(0);
				salesList.set(i, svo);
			}else {
				svo.setR_cancel(0);
				salesList.set(i, svo);
			}
		}

		Gson gson = new Gson();
		str = gson.toJson(salesList);
		return str;
	}
   
	// 설아) (사업자) 캠핑룸 삭제하기
	@RequestMapping("/deleteCampingRoom.do")
	public String deleteCampingRoom(int cr_no) {

		String str = "캠핑룸 삭제를 실패하였습니다.";
		int re = dao.deleteCampingRoom(cr_no);
		if(re > 0) {
			str = "캠핑룸 삭제를 성공하였습니다.";
		}
		//System.out.println("캠핑룸 삭제 re:" + re);
		return str;
	}
   
	// 설아) (사업자) 캠핑룸 수정하기
	@RequestMapping("/updateCampingRoom.do")
	public String updateCampingRoom(CampingRoomVo crvo) {
		String str = "캠핑룸 수정을 실패하였습니다.";
		int re = dao.updateCampingRoom(crvo);
		if(re > 0) {
			str = "캠핑룸 수정을 성공하였습니다.";
		}
		System.out.println("캠핑룸 수정 re:" + re);
		return str;
	}
   
	// 설아) (사업자) 캠핑룸 등록하기
	@RequestMapping("/insertCampingRoom.do")
	public String insertCampingRoom(CampingRoomVo crvo) {
		String str = "캠핑룸 등록을 성공하였습니다.";
		dao.insertCampingRoom(crvo);
		return str;
	}
   
	// 설아) (사업자) 캠핑룸 목록보기
	@RequestMapping(value = "/bossCampingRoomList.do", produces = "application/json;charset=UTF-8")
	public String bossCampingRoomList(int cs_no) {
		String str ="";
		List<CampingRoomVo> crList = dao.bossCampingRoomList(cs_no);
		Gson gson = new Gson();
		str = gson.toJson(crList);
		return str;
	}
   
    // 설아) (사업자) 캠핑장 등록하기
	@RequestMapping("/insertCampingSpot.do")
	public String insertCampingSpot(CampingSpotVo csvo, MultipartHttpServletRequest request) {		
		
		String str = "캠핑장 등록을 성공하였습니다.";
		
		//1.사업자등록증 업로드
		//String path = "C:\\Users\\User\\git\\CampingSpot\\src\\main\\resources\\static\\resources\\camping_spot_img";
		String path = request.getRealPath("/resources/camping_spot_img");
		
		System.out.println("사업자등록증 경로:" + path);
		MultipartFile uploadFile = csvo.getUploadFile();
		String cs_licence_fname =""; 	// 사업자등록증 파일이름
				
		if(uploadFile != null) {
			cs_licence_fname = uploadFile.getOriginalFilename();
			System.out.println("사업자등록증 fname:" + cs_licence_fname);

			try {
				byte []data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path + "/" + cs_licence_fname);
				fos.write(data);
				fos.close();
								
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		//2.캠핑장이미지 업로드 			
//		String Cpath = "C:\\Users\\User\\git\\CampingSpot\\src\\main\\resources\\static\\resources\\camping_spot_img";
		String Cpath = request.getRealPath("/resources/camping_spot_img");
		System.out.println("캠핑장이미지 경로: " +Cpath);

		MultipartFile CampingUploadFile = csvo.getCampingUploadFile();
		
		// 캠핑장 파일 이름들을 넣어 둘 변수
		String cs_camp_fnames =""; 	
		
		List<MultipartFile> campList = request.getFiles("CampingUploadFile");
		for (int i=0; i <campList.size(); i++) {
			MultipartFile multiF = campList.get(i);
			String cs_camp_fname = multiF.getOriginalFilename();
			cs_camp_fnames += cs_camp_fname;
			
			File outFile = new File(Cpath + "/" + cs_camp_fname);
			try {
				multiF.transferTo(outFile);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			// ,로 구분하기 위해
			if(i < campList.size()-1) {
				cs_camp_fnames += ",";
			}
		}
		
		csvo.setCs_camp_fname(cs_camp_fnames);
		System.out.println(csvo.getCs_camp_fname());
		
		String arr[] = csvo.getCs_camp_fname().split(",");
		System.out.println(arr);
		
		// 3.지도 이미지 업로드
//		String Mpath = "C:\\Users\\User\\git\\CampingSpot\\src\\main\\resources\\static\\resources\\camping_spot_img";
		String Mpath = request.getRealPath("/resources/camping_spot_img");
		System.out.println("지도이미지 경로: " + Mpath);
		
		MultipartFile MapUploadFile = csvo.getMapUploadFile();
		String cs_map_fname = "";
		
		if(MapUploadFile != null) {
			cs_map_fname = MapUploadFile.getOriginalFilename();
			System.out.println("약도 fname:" + cs_map_fname);
			try {
				byte []mdata = MapUploadFile.getBytes();
				FileOutputStream mfos = new FileOutputStream(Mpath + "/" + cs_map_fname);
				
				mfos.write(mdata);
				mfos.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		csvo.setCs_licence_fname(cs_licence_fname);
		csvo.setCs_map_fname(cs_map_fname);
		
		dao.insertCampingSpot(csvo);
		return str;
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
      System.out.println("======== campingReviewList 컨트롤러 실행 ! ===========");

//      SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//      
      List<CampingReviewVo> list = dao.campingReviewList(cs_no);
      System.out.println("불러온 list:"+list);
      if( list.size() != 0 ) {
    	  for (int i=0; i<list.size(); i++) {
        	  String id = list.get(i).getMc_id();
        	  MemberVo vo = mdao.getMember(id);
        	  System.out.println(vo);
        	  list.get(i).setMc_fname(vo.getMc_fname());
        	  list.get(i).setMc_name(vo.getMc_name());
          }
          
          Gson gson = new Gson();
          str = gson.toJson(list);
          System.out.println(list.get(0).getMc_fname());
          System.out.println(str);
      }else {
    	  str = new Gson().toJson(list);
      }
      
      System.out.println(str);
      
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
