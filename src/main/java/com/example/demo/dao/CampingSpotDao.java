package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.CampingReviewVo;
import com.example.demo.vo.CampingSearchResultVo;
import com.example.demo.vo.CampingSpotVo;
import com.example.demo.vo.CampingWishVo;
import com.example.demo.vo.DetailCampingSearchResultVo;

@Repository
public class CampingSpotDao {
   
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