package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BossListSalesVo;
import com.example.demo.vo.BossReservationVo;
import com.example.demo.vo.CampingReviewReVo;
import com.example.demo.vo.CampingReviewVo;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSpotVo;

public class bossDBManager {
	
	
	public static SqlSessionFactory factory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/SqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 설아) (사업자) 캠핑장 정보 상세 
	public static CampingSpotVo bossGetCampingSpot(int cs_no) {
		SqlSession session = factory.openSession();
		CampingSpotVo csVo = session.selectOne("campingSpot.bossGetCampingSpot", cs_no);
		session.close();
		return csVo;
	}
	
	//설아) (사업자) cs_no번호만 가져오기
	public static CampingSpotVo getCsNO(String mb_id) {
		SqlSession session = factory.openSession();
		CampingSpotVo csvo = session.selectOne("campingSpot.getCsNo", mb_id);
		session.close();
		return csvo;
	}
	
	// 설아) (사업자) 사업자페이지 메인 예약목록
	public static List<BossReservationVo> businessMyPageReservationList(int cs_no) {
		List<BossReservationVo> mpList = null;
		SqlSession session = factory.openSession();
		mpList = session.selectList("reservation.businessMyPageReservationList", cs_no);
		session.close();
		return mpList;
	}
	
	// 설아) (사업자) 매출 현황 챠트
	public static List<BossListSalesVo> bossChart(int cs_no){
		List<BossListSalesVo> chartList = null;
		SqlSession session = factory.openSession();
		chartList = session.selectList("campingRoom.chart", cs_no);
		session.close();
		return chartList;
	}
	
	// 설아) (사업자) 월별 매출 총합
	public static BossListSalesVo bossListTotalMonth(HashMap map){
		BossListSalesVo svo = null;
		SqlSession session = factory.openSession();
		svo = session.selectOne("campingRoom.totalMonth", map);
		session.close();
		return svo;
	}
	
	// 설아) (사업자) 캠핑룸별 매출 총합 list
	public static List<BossListSalesVo> bossListTotalCampingRoom(HashMap map){
		List<BossListSalesVo> salesList = null;
		SqlSession session = factory.openSession();
		salesList = session.selectList("campingRoom.totalByCR", map);
		session.close();
		return salesList;
	}
	
	// 설아) (사업자) 매출 현황 목록
	public static List<BossListSalesVo> bossListSales(HashMap map){
		List<BossListSalesVo> salesList = null;
		SqlSession session = factory.openSession();
		salesList = session.selectList("campingRoom.bossListSales", map);
		session.close();
		return salesList;
	}
	
	// 설아) (사업자) 취소승인
	public static int updateCancelStatus(int r_no) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.update("reservation.updateCstatus", r_no);
		session.commit();
		session.close();
		return re;
	}
	
	// 설아) (사업자) 예약승인
	public static int updateReserveStatus(int r_no) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.update("reservation.updateRstatus", r_no);
		session.commit();
		session.close();
		return re;
	}
	
	// 설아) (사업자) 캠핑 리뷰 댓글 확인
	public static CampingReviewReVo checkReviewRe(int cre_no) {
		CampingReviewReVo crrVo = null;
		SqlSession session = factory.openSession();
		crrVo = session.selectOne("CampingReviewRe.countRepleAtReview", cre_no);
		session.close();
		return crrVo;
	}
	
	// 설아) 캠핑 리뷰 댓글 삭제
	public static int bossDeleteCampingReviewRe(int cre_re_no) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.insert("CampingReviewRe.deleteCreRe", cre_re_no);
		session.commit();
		session.close();
		return re;
	}
	
	// 설아) 캠핑 리뷰 답글 번호
	public static int nextNo() {
		int cre_re_no =0;
		SqlSession session = factory.openSession();
		cre_re_no = session.selectOne("CampingReviewRe.nextNo");
		session.close();
		return cre_re_no;
	}
	
	// 설아) (사업자) 리뷰 댓글 상세보기
	public static CampingReviewReVo getCampingReviewRe(int cre_no) {
		SqlSession session = factory.openSession();
		CampingReviewReVo crrVo = session.selectOne("CampingReviewRe.getCampingReveiwRe", cre_no);
		session.close();
		return crrVo;
	}
	
	// 설아) (사업자) 리뷰 댓글 등록 메소드
	public static int bossInsertCampingReviewRe(CampingReviewReVo crrVo) {
		int re = -1;			
		SqlSession session = factory.openSession();
		re = session.insert("CampingReviewRe.insertCreRe", crrVo);
		session.commit();
		session.close();
		return re;
	}
	
	// 설아) (사업자) 리뷰 관리 메소드
	public static List<CampingReviewVo> bossCampingReviewList(int cs_no){
		List<CampingReviewVo> creList = null;
		SqlSession session = factory.openSession();
		creList = session.selectList("CampingReviewRe.bossCampingReviewList",cs_no);
		System.out.println("리뷰관리목록:" + creList);
		session.close();
		return creList;
	}
	
	// 설아) (사업자) 예약 관리 현황 메소드
	public static List<BossReservationVo> bossReservationList(int cs_no){
		List<BossReservationVo> bossRList = null;
		SqlSession session = factory.openSession();
		bossRList = session.selectList("reservation.bossReservationList", cs_no);
		session.close();
		return bossRList;
 	}
	
	// 설아) (사업자) 캠핑룸 삭제 메소드
	public static int deleteCampingRoom(int cr_no) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.delete("campingRoom.deleteCampingRoom", cr_no);
		session.commit();
		session.close();
		return re;
	}
	
	// 설아) (사업자) 캠핑룸 업데이트 메소드
	public static int updateCampingRoom(CampingRoomVo crvo) {
		int re = -1;			
		SqlSession session = factory.openSession();
		re = session.update("campingRoom.updateCampingRoom", crvo);
		session.commit();
		session.close();
		return re;
	}
	
	// 설아) (사업자) 캠핑룸 등록 메소드
	public static int insertCampingRoom(CampingRoomVo crvo) {
		int re = -1;			
		SqlSession session = factory.openSession();
		re = session.insert("campingRoom.insertCampingRoom", crvo);
		session.commit();
		session.close();
		return re;
	}
	
	// 설아) (사업자) 캠핑룸 목록 메소드
	public static List<CampingRoomVo> bossCampingRoomList(int cs_no){
		List<CampingRoomVo> crList = null;
		SqlSession session = factory.openSession();
		crList = session.selectList("campingRoom.listCampingRoom", cs_no);
		session.close();
		return crList;
 	}
	
	// 설아) (사업자) 캠핑장 등록 메소드
	public static int insertCampingSpot(CampingSpotVo csvo) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.insert("campingSpot.insertCampingSpot", csvo);
		session.commit();
		session.close();
		return re;	
	}	

}
