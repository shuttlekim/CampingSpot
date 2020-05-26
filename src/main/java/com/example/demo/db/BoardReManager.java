package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardReVo;


public class BoardReManager {

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
	
	// b_del과 b_no의 결과에따라 각 게시판의 목록을 불러온다
	public static List<BoardReVo> selectAll(HashMap<String, Integer> map) {
		SqlSession session = factory.openSession();
		List<BoardReVo> list = session.selectList("boardRe.selectAll", map);
		session.close();
		return list;
	}
	
	//댓글등록
	public static int insert(BoardReVo br) {
		SqlSession session = factory.openSession();
		int re = session.insert("boardRe.insert", br);
		session.commit();
		session.close();
		return re;
	}
	
	//댓글번호와 아이디를 받아서 댓글삭제
	public static int delete(HashMap<String, Object> map) {
		SqlSession session = factory.openSession();
		int re = session.delete("boardRe.delete", map);
		session.commit();
		session.close();
		return re;
	}

}
