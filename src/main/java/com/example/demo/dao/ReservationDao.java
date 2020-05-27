package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.db.bossDBManager;
import com.example.demo.vo.BossReservationVo;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSearchResultVo;
import com.example.demo.vo.DetailCampingSearchResultVo;

@Repository
public class ReservationDao {
	
	
	// 설아) (사업자) 사업자페이지 메인 예약목록
	public List<BossReservationVo> businessMyPageReservationList(int cs_no){
		return bossDBManager.businessMyPageReservationList(cs_no);
	}
				
	// 설아) (사업자) 취소승인 업데이트
	public int updateCancelStatus(int r_no) {
		return bossDBManager.updateCancelStatus(r_no);
	}

	// 설아) (사업자) 예약승인 업데이트
	public int updateReserveStatus(int r_no) {
		return bossDBManager.updateReserveStatus(r_no);
	}
		
	// 설아) (사업자) 예약 관리 현황 목록보기
	public List<BossReservationVo> bossReservationList(int cs_no){
		return bossDBManager.bossReservationList(cs_no);
	}
	
	// 영현
	public int callReservationKey() {
		return DBManager.callReservationKey();
	}
	
	// 영현
	public int insertReserveSearch(HashMap map) {
		return DBManager.insertReserveSearch(map);
	}
	
	// 영현 
	public int insertReservation(HashMap map) {
		return DBManager.insertReservation(map);
	}
	
	// 영현
	public CampingRoomVo getRoomInfo(int cr_no) {
		return DBManager.getRoomInfo(cr_no);
	}
	
	// 영현
	public List<CampingRoomVo> ingRoom(HashMap map){
		return DBManager.ingRoom(map);
	}

	// 영현
	public List<CampingRoomVo> selectorRoom(HashMap map){
		return DBManager.selectorRoom(map);
	}
	
	// 영현
	public CampingSearchResultVo getCampingSpot(HashMap map) {
		return DBManager.getCampingSpot(map);
	}
		
	// 영현 
	public DetailCampingSearchResultVo detailCampingSearchResult(HashMap map) {
		return DBManager.detailCampingSearchResult(map);
	}
	
	// 지연 :로그인한 회원의 포인트 가져오기
	public int selPoint(String mc_id) {
		return DBManager.selPoint(mc_id);
	}
}
