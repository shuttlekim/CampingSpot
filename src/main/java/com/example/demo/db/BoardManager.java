package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVo;

public class BoardManager {

	private static SqlSessionFactory factory;
	static {
		try {
			Reader reader= Resources.getResourceAsReader("com/example/demo/db/SqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	// b_del의 결과에따라 각 게시판의 목록을 불러온다(하지연)
	public static List<BoardVo> listBoard(HashMap<String, Object> map) {
		SqlSession session = factory.openSession();
		List<BoardVo> list = session.selectList("board.selectAll", map);
		session.close();
		//System.out.println("매니저동작함");
		return list;
	}
	// 캠핑장후기 작성시 캠핑장 명 불러오기(하지연)
	public static List<String> findCampingSpotName(String mc_id){
		SqlSession session = factory.openSession();
		List<String> list = session.selectList("other.findCSName", mc_id);
		session.close();
		return list;
	}
	
	// 게시물 등록하기(하지연)
	public static int insertBoard(BoardVo b) {
		SqlSession session = factory.openSession();
		int re = session.insert("board.insert", b);
		session.commit();
		session.close();
		return re;
	}
	// 글번호를 받아 디테일 보여주기(하지연)
	public static BoardVo detailBoard(int b_no) {
		SqlSession session = factory.openSession();
		BoardVo b = session.selectOne("board.detail", b_no);
		session.close();
		return b;
	}
	// 상세보기를 누르면 조회수가 1씩 증가한다(하지연)
	public static int updateHit(int b_no) {
		SqlSession session = factory.openSession();
		int re = session.update("board.updateHit", b_no);
		session.commit();
		session.close();
		return re;
	}
	// 글번호를 받아 게시물 삭제(하지연)
	public static int deleteBoard(HashMap<String, Object> map) {
		SqlSession session = factory.openSession();
		int re = session.delete("board.delete", map);
		session.commit();
		session.close();
		return re;
	}
	// 글번호를 받아 게시물 수정(하지연)
	public static int updateBoard(BoardVo b) {
		SqlSession session = factory.openSession();
		int re = session.update("board.update", b);
		session.commit();
		session.close();
		return re;
	}
	// 검색 키워드 받아와서 목록불러오기(하지연)
	public static List<BoardVo> search(HashMap map){
		SqlSession session = factory.openSession();
		List<BoardVo> list = session.selectList("board.search", map);
		session.close();
		return list;
	}
}
