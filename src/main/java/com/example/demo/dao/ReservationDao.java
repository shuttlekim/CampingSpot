package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSearchResultVo;
import com.example.demo.vo.DetailCampingSearchResultVo;

@Repository
public class ReservationDao {

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
}
