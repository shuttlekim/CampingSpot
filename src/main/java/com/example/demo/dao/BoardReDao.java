package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.BoardReManager;
import com.example.demo.vo.BoardReVo;

@Repository
public class BoardReDao {

	public List<BoardReVo> selectReAll(HashMap<String, Integer> map){
		return BoardReManager.selectAll(map);
	}
	
	public int insertBoardRe(BoardReVo br) {
		return BoardReManager.insert(br);
	}
	
	public int deleteBoardRe(HashMap<String, Object> map) {
		return BoardReManager.delete(map);
	}
}
