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
import com.example.demo.vo.CrLVo;
import com.example.demo.vo.CrVo;
import com.example.demo.vo.CsCVo;
import com.example.demo.vo.CsDVo;
import com.example.demo.vo.CsRVo;
import com.example.demo.vo.DetailCampingSearchResultVo;
import com.example.demo.vo.ProfileVo;
import com.example.demo.vo.WishVo;


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
	
//	// 영현) 캠핑장 데이터 한글화
//	public static int updateCampingSpot(int cs_no) {
//		SqlSession session = factory.openSession(true);
//		int re = session.update("updateTable.updateCampingSpotKor", cs_no);
//		session.close();
//		return re;
//	}
	
	// 영현) 캠핑장 파일이름 업데이트
	public static int updateCampingSpotFname(int cs_no) {
		SqlSession session = factory.openSession(true);
		int re = session.update("updateTable.updateCampingSpotFname", cs_no);
		session.close();
		return re;
	}
	
	// 영현) 캠핑장 검색 리스트 호출
	public static CampingSearchResultVo getCampingSpot(HashMap map) {
		SqlSession session = factory.openSession();
		System.out.println("getCampingSpot 조건:"+map);
		CampingSearchResultVo csrv = session.selectOne("reservation.getCampingSpot", map);
		System.out.println("getCampingSpot Manager:"+csrv);
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
	      SqlSession session = factory.openSession(true);
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
	
	// 현성 : 예약내역 출력
	public static List<CsRVo> myReservationList(String mc_id) {
		List<CsRVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("myPage.myReservationList",  mc_id);
		session.close();
		return list;
	}
	

	// 현성 : 예약내역 출력 - 사용후
	public static List<CsRVo> resrvation_after() {
		List<CsRVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("myPage.reservation_after");
		session.close();
		return list;
	}
	// 현성 : 프로필 출력
	public static List<ProfileVo> profile(String mc_id) {
		List<ProfileVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("myPage.profile", mc_id);
		session.close();
		return list;
	}
	// 현성 : 예약정보확인 출력
	public static List<CsDVo> myReservationDetail(CsDVo cd) {
		List<CsDVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("myPage.myReservationDetail", cd);
		session.close();
		return list;
	}
	
	// 현성 : 예약취소정보 출력
	public static List<CsCVo> requestCancel(CsCVo csc) {
		List<CsCVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("myPage.requestCancel", csc);
		session.close();
		return list;
	}
	
	// 현성 : 위시리스트 출력
	public static List<WishVo> myWishList(WishVo wish){
		List<WishVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("myPage.myWishList" , wish);
		session.close();
		return list;
	}
	
	// 현성 : 캠핑 후기 출력
	public static List<CrLVo> myReviewList(String mc_id){
		List<CrLVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("myPage.myReviewList", mc_id);
		session.close();
		return list;
	}
	
	// 현성 : 캠핑후기 삭제 delete
	public static int deleteMyReview(int cre_no) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.delete("myPage.deleteMyReview", cre_no);
		session.close();
		return re;
	}
	
	// 현성 : 위시리스트 삭제 delete
	public static int deleteMyWish(int cw_no) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.delete("myPage.deleteMyWish", cw_no);
		session.close();
		return re;
	}
	
	// 현성 : 예약취소요청 update
	public static int cancelReservation(int r_no) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.update("myPage.cancelReservation", r_no);
		session.close();
		return re;
	}
	
	// 현성 : 리뷰입력 insert
	public static int camping_review_insert(CrVo c) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.insert("myPage.camping_review_insert", c);
		session.close();
		return re;
	}
	
}

















