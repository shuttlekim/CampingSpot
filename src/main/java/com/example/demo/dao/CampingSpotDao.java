package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.CampingReviewVo;
import com.example.demo.vo.CampingSpotVo;
import com.example.demo.vo.CampingWishVo;

@Repository
public class CampingSpotDao {
   
//   public List<CampingListVo> campingList(){
//      return DBManager.campingList();
//   }

   public CampingSpotVo detailCampingSpot(int cs_no) {
      return DBManager.detailCampingSpot(cs_no);
   }
   
   public List<CampingReviewVo> campingReviewList(int cs_no) {
      return DBManager.campingReviewList(cs_no);
   }
   
   public int insertWish(CampingWishVo w) {
      return DBManager.insertWish(w);
   }

}