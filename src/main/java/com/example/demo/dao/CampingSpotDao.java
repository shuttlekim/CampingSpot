package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.CampingReviewVo;
import com.example.demo.vo.CampingSearchResultVo;
import com.example.demo.vo.CampingWishVo;
import com.example.demo.vo.DetailCampingSearchResultVo;

@Repository
public class CampingSpotDao {
   
//   public List<CampingListVo> campingList(){
//      return DBManager.campingList();
//   }

	// 영현
	public CampingSearchResultVo getCampingSpot(HashMap map) {
		return DBManager.getCampingSpot(map);
	}
	
	// 영현 
   public DetailCampingSearchResultVo detailCampingSearchResult(HashMap map) {
      return DBManager.detailCampingSearchResult(map);
   }
   
   public List<CampingReviewVo> campingReviewList(int cs_no) {
      return DBManager.campingReviewList(cs_no);
   }
   
   public int insertWish(CampingWishVo w) {
      return DBManager.insertWish(w);
   }

}