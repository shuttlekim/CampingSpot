package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.BoardManager;
import com.example.demo.vo.BoardVo;

@Repository
public class BoardDao {

	public List<BoardVo> selectAll(HashMap<String, Object> map){
		return BoardManager.listBoard(map);
	}
	
	public int insert(BoardVo b) {
		return BoardManager.insertBoard(b);
	}
	
	public BoardVo detail(int b_no) {
		return BoardManager.detailBoard(b_no);
	}
	
	public int updateHit(int b_no) {
		return BoardManager.updateHit(b_no);
	}
	
	public int delete(HashMap<String, Object> map) {
		return BoardManager.deleteBoard(map);
	}
	
	public int update(BoardVo b) {
		return BoardManager.updateBoard(b);
	}
	
	public List<String> findCSName(String mc_id){
		return BoardManager.findCampingSpotName(mc_id);
	}
	
	public List<BoardVo> search(HashMap map){
		return BoardManager.search(map);
	}
}
