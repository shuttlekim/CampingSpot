package com.example.demo.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BoardReDao;
import com.example.demo.vo.BoardReVo;
import com.google.gson.Gson;

@RestController
public class BoardReController {

	@Autowired
	private BoardReDao dao;
	
	public BoardReDao getDao() {
		return dao;
	}

	@RequestMapping(value = "/boardReList.do", produces = "application/json;cahrset=UTF-8")
	public String selectReAll(int b_no, int br_del) {
		String str = "";
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("b_no", b_no);
		map.put("br_del", br_del);
		
		Gson gson = new Gson();
		str = gson.toJson(dao.selectReAll(map));
		return str;
	}
	
	@RequestMapping("/boardReInsert.do")
	public String insertRe(BoardReVo br) {
		String str = "댓글등록에 실패했습니다.";
		int re = dao.insertBoardRe(br);
		if(re > 0) {
			str = "댓글등록에 성공했습니다.";
		}
		return str;
	}
	
	@RequestMapping("/boardReDelete.do")
	public String deleteRe(int br_no, String mc_id) {
		String str = "댓글삭제에 실패했습니다.";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("br_no", br_no);
		map.put("mc_id", mc_id);
		int re = dao.deleteBoardRe(map);
		if(re > 0) {
			str = "댓글삭제에 성공했습니다.";
		}
		return str;
	}
}
