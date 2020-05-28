package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDao;
import com.example.demo.vo.BoardVo;
import com.google.gson.Gson;
import com.sun.mail.handlers.multipart_mixed;

@RestController
public class BoardController { 

	@Autowired 
	private BoardDao dao;

	public BoardDao getDao() {
		return dao;
	}
	
	//각 보드에 모든 list
	@RequestMapping(value = "/listBoard.do", produces = "application/json;cahrset=UTF-8")
	public String listBoard(int b_del, String keyword, String searchColumn, HttpSession session) {
		// b_del 이 0,1,2가 각각 올것인디... 
		// html에서 각 게시판의 b_del을 전달해주면 mapper에 들어가서 각 게시판을 select해서
		// 받아온 값을 json으로 목록을주어서 다시 각 게시판html에다가 전달해준다
		// ajax("/board",{data:{b_del:b_del},success:function(r){ajax성공시!}})
		String str = "";
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//System.out.println("b_del:" + b_del);
		//System.out.println("keyword:" + keyword);
		//System.out.println("searchColumn:" + searchColumn);
		
		map.put("b_del", b_del);
		map.put("keyword", keyword);
		map.put("searchColumn", searchColumn);
		
		str = gson.toJson(dao.selectAll(map));
		//System.out.println(str);
		//System.out.println("보드목록컨트롤러" + str);
		//System.out.println("컨트롤러 동작함");
		return str;
	}
	//회원이 숙박완료한 리스트
	@RequestMapping(value = "/findCampingSpotName.do", produces ="application/json;cahrset=UTF-8")
	public String findCampingSpotName(String mc_id) {
		String str = "";
		Gson gson = new Gson();
		str = gson.toJson(dao.findCSName(mc_id));
		//System.out.println(str);
		return str;
	}
	//각 보드 글쓰기
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVo b, MultipartHttpServletRequest request) {
		String str = "게시물 등록에 실패하였습니다.";
		
		// 사진파일 올리기
		//String path = "C:/Users/haji/git/CampingSpot/src/main/resources/static/resources/board_img";

		MultipartFile uploadFile = b.getUploadFile();
		String fnames = "";
		String fname = "";
		
		String path = request.getRealPath("/resources/board_img");
		//System.out.println("업로드경로:" + path);
		List<MultipartFile> list = request.getFiles("uploadFile");
		 
		for(int i = 0 ; i < list.size() ; i++) {
			MultipartFile mul = list.get(i);
			fname = mul.getOriginalFilename();
			fnames += fname;
				
			File outFile = new File(path + "/" + fname);
			try {
				mul.transferTo(outFile);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			if(i < list.size()-1) {
				fnames += ",";
			}
		}
		b.setB_fname(fnames);
		
		String arr[] = b.getB_fname().split(",");
		//System.out.println(arr);
		
		int re = dao.insert(b);
		if(re >= 1) {
			str = "게시물 등록에 성공하였습니다.";
		}
		return str;
	}
	//각 보드 wh
	@RequestMapping("/detailBoard.do")
	public BoardVo detailBoard(int b_no) {
		dao.updateHit(b_no);
		BoardVo b = dao.detail(b_no);
		//System.out.println("컨트롤러동작함");
		return b;
	}
	//각 보드 삭제하기
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(int b_no, String mc_id, HttpServletRequest request) {
		String str = "게시물 삭제에 실패했습니다.";
		String fname = dao.detail(b_no).getB_fname();
		//System.out.println(fname); cloth1.jpg,cloth2.jpg 이런식으로 옴
		
		String fnames[] = fname.split(",");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_no", b_no);
		map.put("mc_id", mc_id);
		int re = dao.delete(map);
		//String path = "C:/Users/haji/git/CampingSpot/src/main/resources/static/resources/board_img";
		String path = request.getRealPath("/resources/board_img");
		
		for(int i = 0 ; i < fnames.length ; i++) {
			File file = new File(path + "/" + fnames[i]);
			file.delete();
		}
		
		if(re >= 1) {
			str = "게시물 삭제 성공했습니다.";
		}
		return str;
	}
	//각 보드 수정하기
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVo b, MultipartHttpServletRequest request) {
		String str = "게시물 수정에 실패했습니다.";
		//String path = "C:/Users/haji/git/CampingSpot/src/main/resources/static/resources/board_img";
		String path = request.getRealPath("/resources/board_img");
		String oldFname = b.getB_fname();
		MultipartFile uploadFile = b.getUploadFile();
		
		String fnames = "";
		String fname = "";
		List<MultipartFile> list = request.getFiles("uploadFile");
		
		if(uploadFile != null) {
		//수정할 파일이 있다면
			fname = uploadFile.getOriginalFilename();
			if(fname != null && !fname.equals("")) {
			//수정할 파일이 올라왔다면
				
				for(int i = 0 ; i < list.size() ; i++) {
					MultipartFile mul = list.get(i);
					fname = mul.getOriginalFilename();
					fnames += fname;
						
					File outFile = new File(path + "/" + fname);
					try {
						mul.transferTo(outFile);
					}catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage());
					}
					if(i < list.size()-1) {
						fnames += ",";
					}
				}
				b.setB_fname(fnames);
			}
		}
		int re = dao.update(b);
		
		//수정에 성공했고  //파일도 수정했고  //원래게시물이 파일이 있었다면 원래있던거 지워라
		if(re > 0 
				&& fname != null && !fname.equals("")
				&& oldFname != null && !oldFname.equals("")) {
			
			String oldFnames[] = oldFname.split(",");
			for(int i = 0 ; i < oldFnames.length ; i++) {
				File file = new File(path + "/" + oldFnames[i]);
				file.delete();
			}
		}
		if(re >=1) {
			str = "게시물 수정 성공했습니다.";
		}
		
		return str;
	}
	
}
