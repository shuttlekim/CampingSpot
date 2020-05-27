package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.db.bossDBManager;
import com.example.demo.vo.BossListSalesVo;
import com.example.demo.vo.CampingReviewVo;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSearchResultVo;
import com.example.demo.vo.CampingSpotVo;
import com.example.demo.vo.CampingWishVo;
import com.example.demo.vo.DetailCampingSearchResultVo;

@Repository
public class CampingSpotDao {
	
	
	// 설아) (사업자) 매출 현황 챠트
	public List<BossListSalesVo> bossChart(int cs_no){
		return bossDBManager.bossChart(cs_no);
	}
	
	// 설아) (사업자) 월별 매출 총합 
	public BossListSalesVo bossListTotalMonth(HashMap map){
		return bossDBManager.bossListTotalMonth(map);
	}
	
	// 설아) (사업자) 캠핑룸별 매출 총합 목록
	public List<BossListSalesVo> bossListTotalCampingRoom(HashMap map){
		return bossDBManager.bossListTotalCampingRoom(map);
	}
	
	// 설아) (사업자) 매출 현황 목록 dao
	public List<BossListSalesVo> bossListSales(HashMap map){
		return bossDBManager.bossListSales(map);
	}
	
	// 설아) (사업자) 캠핑룸 삭제 dao
	public int deleteCampingRoom(int cr_no) {
		return bossDBManager.deleteCampingRoom(cr_no);
	}
	
	// 설아) (사업자) 캠핑룸 업데이트 dao
	public int updateCampingRoom(CampingRoomVo crvo) {
		return bossDBManager.updateCampingRoom(crvo);
	}
	
	// 설아) (사업자) 캠핑룸 등록 dao
	public int insertCampingRoom(CampingRoomVo crvo) {
		return bossDBManager.insertCampingRoom(crvo);
	}
	
	// 설아) (사업자) 캠핑룸 목록 dao
	public List<CampingRoomVo> bossCampingRoomList(int cs_no){
		return bossDBManager.bossCampingRoomList(cs_no);
	}
	
	// 설아) (사업자) 캠핑장 등록 dao
	public int insertCampingSpot(CampingSpotVo csvo) {
		return bossDBManager.insertCampingSpot(csvo);
	}
   
//   public List<CampingListVo> campingList(){
//      return DBManager.campingList();
//   }
   
   public List<CampingReviewVo> campingReviewList(int cs_no) {
      return DBManager.campingReviewList(cs_no);
   }
   
   // 영현) 위시리스트 중복체크 요청
   public CampingWishVo checkDouble(HashMap map) {
	   return DBManager.checkDouble(map);
   }
   
   // 근희) 위시리스트 추가 - 수정(영현)
   public int insertWish(HashMap map) {
      return DBManager.insertWish(map);
   }
   
   public CampingSpotVo getCampingInfo(int cs_no) {
	   return DBManager.getCampingInfo(cs_no);
   }

}