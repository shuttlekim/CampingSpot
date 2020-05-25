package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.CampingReviewVo;
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
	// 영현
	public static DetailCampingSearchResultVo detailCampingSearchResult(HashMap map) {
		SqlSession session = factory.openSession();
		DetailCampingSearchResultVo dcsrv = session.selectOne("campingSpot.detailCampingSearchResult", map);
		session.close();
		return dcsrv;
	}
	
	// 영현
	public static int updateCampingSpot(int cs_no) {
		SqlSession session = factory.openSession(true);
		int re = session.update("campingSpot.updateCampingSpot", cs_no);
		session.close();
		return re;
	}
	
	// 영현
	public static CampingSearchResultVo getCampingSpot(HashMap map) {
		SqlSession session = factory.openSession();
		CampingSearchResultVo csrv = session.selectOne("campingSpot.getCampingSpot", map);
		session.close();
		return csrv;
	}
	
	// 영현
	public static List<CampingSearchResultVo> search_list(HashMap map){
		SqlSession session = factory.openSession();
		List<CampingSearchResultVo> list = session.selectList("campingSpot.campingList", map);
		System.out.println("search_List 매니저 메소드 동작:"+list);
		session.close();
		return list;
	}
	
	// 영현) 캠핑장번호로 캠핑스팟 테이블 정보 불러오기 
	/*
	public static CampingSpotVo detailCampingSpot(int cs_no){
		SqlSession session = factory.openSession();
		CampingSpotVo c = session.selectOne("campingSpot.detailCampingSpot", cs_no);
		session.close();
		return c;
	}
	위에 detailCampingSearchResult 메소드로 대체됨, 혹시 추후 필요성여부에 의해 남김
	*/
	// 근희) 리뷰리스트 호출
	public static List<CampingReviewVo> campingReviewList(int cs_no){
	      SqlSession session = factory.openSession();
	      List<CampingReviewVo> list = session.selectList("campingReview.campingReviewList", cs_no);
	      session.close();
	      return list;
	}
	
	// 근희) 위시리스트 추가
	public static int insertWish(CampingWishVo w) {
	      SqlSession session = factory.openSession();
	      int re = session.insert("myPage.insertWish", w);
	      session.commit();
	      session.close();
	      return re;
	}
	
}

















