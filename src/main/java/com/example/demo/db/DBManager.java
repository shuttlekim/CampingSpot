package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.CampingReviewVo;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSearchResultVo;
import com.example.demo.vo.CampingSpotVo;
import com.example.demo.vo.CampingWishVo;
import com.example.demo.vo.DetailCampingSearchResultVo;


public class DBManager {

	private static SqlSessionFactory factory = null;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/sqlMapConfig.xml");
			System.out.println("reader:"+reader);
			factory = new SqlSessionFactoryBuilder().build(reader);
			System.out.println("factory:"+factory);
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 영현) 예약번호 불러오기
	public static int callReservationKey(HashMap map) {
		SqlSession session = factory.openSession();
		int re = session.selectOne("reservation.callReservationKey", map);
		session.close();
		return re;
	}
	
	// 영현) 예약 검색 등록
	public static int insertReserveSearch(HashMap map) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("reservation.insertReserveSearch", map);
		session.close();
		return re;
	}
	
	// 영현) 예약 등록
	public static int insertReservation(HashMap map) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("reservation.insertReservation", map);
		session.close();
		return re;
	}
	
	// 영현) 캠핑룸 번호로 캠핑룸정보 요청
	public static CampingRoomVo getRoomInfo(int cr_no) {
		SqlSession session = factory.openSession();
		CampingRoomVo c = session.selectOne("reservation.getRoomInfo", cr_no);
		session.close();
		return c;
	}
	// 영현) 체크인 체크아웃 사이에 사용중인 룸을 조회
	public static List<CampingRoomVo> ingRoom(HashMap map){
		SqlSession session = factory.openSession();
		List<CampingRoomVo> list = session.selectList("reservation.ingRoom", map);
		session.close();
		return list;
	}
	
	// 영현) 캠핑장별 캠핑룸 현황 조회
	public static List<CampingRoomVo> selectorRoom(HashMap map) {
		SqlSession session = factory.openSession();
		List<CampingRoomVo> list = session.selectList("reservation.selectorRoom", map);
		session.close();
		return list;
	}
	
	// 영현) 캠핑장 예약 상세페이지
	public static DetailCampingSearchResultVo detailCampingSearchResult(HashMap map) {
		SqlSession session = factory.openSession();
		DetailCampingSearchResultVo dcsrv = session.selectOne("reservation.detailCampingSearchResult", map);
		session.close();
		return dcsrv;
	}
	
	// 영현) 캠핑장 데이터 업데이트
	public static int updateCampingSpot(int cs_no) {
		SqlSession session = factory.openSession(true);
		int re = session.update("campingSpot.updateCampingSpot", cs_no);
		session.close();
		return re;
	}
	
	// 영현) 캠핑장 검색 리스트 호출
	public static CampingSearchResultVo getCampingSpot(HashMap map) {
		SqlSession session = factory.openSession();
		CampingSearchResultVo csrv = session.selectOne("reservation.getCampingSpot", map);
		session.close();
		return csrv;
	}
	
	// 영현) 캠핑장 날짜외 정보에 따른 리스트 호출
	public static List<CampingSearchResultVo> search_list(HashMap map){
		SqlSession session = factory.openSession();
		System.out.println("========================================");
		System.out.println("조건식의값: "+map);
		List<CampingSearchResultVo> list = session.selectList("reservation.campingList", map);
		System.out.println("search_List 매니저 메소드 동작:"+list);
		session.close();
		return list;
	}
	
	// 영현) 캠핑장번호로 캠핑장정보 호출
	public static CampingSpotVo getCampingInfo(int cs_no) {
		SqlSession session = factory.openSession();
		CampingSpotVo c = session.selectOne("campingSpot.getCampingInfo", cs_no);
		session.close();
		return c;
	}
	// 근희) 리뷰리스트 호출
	public static List<CampingReviewVo> campingReviewList(int cs_no){
	      SqlSession session = factory.openSession();
	      List<CampingReviewVo> list = session.selectList("campingReview.campingReviewList", cs_no);
	      session.close();
	      return list;
	}
	
	// 영현) 위시리스트 중복체크를 위한 요청
	public static CampingWishVo checkDouble(HashMap map) {
		SqlSession session = factory.openSession();
		CampingWishVo c = session.selectOne("myPage.checkDouble", map);
		session.close();
		return c;
	}
	
	// 근희) 위시리스트 추가 - 수정(영현)
	public static int insertWish(HashMap map) {
	      SqlSession session = factory.openSession();
	      int re = session.insert("myPage.insertWish", map);
	      session.commit();
	      session.close();
	      return re;
	}
	
	// 지연 :로그인한 회원의 포인트 가져오기
	public static int selPoint(String mc_id) {
		SqlSession session = factory.openSession();
		int re = session.selectOne("reservation.selPoint", mc_id);
		session.close();
		//System.out.println("매니저동작함: 포인트는?" + re);
		return re;
	}
	
}

















