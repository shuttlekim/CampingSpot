package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BusinessDao;
import com.example.demo.db.BusinessDBManager;
import com.example.demo.service.SHA256Util;
import com.example.demo.vo.BusinessVo;
import com.google.gson.Gson;

@RestController
public class BusinessController {

	@Autowired
	private BusinessDao dao;
	
	public void setDao(BusinessDao dao) {
		this.dao = dao;
	}
	

	//사업자 로그인
	@RequestMapping("/login2.do")
	public String login(String mb_id, String mb_pwd) {
		
		BusinessVo m = dao.getBusiness(mb_id);	
		BusinessVo r = null;
		
		String newPassword = mb_pwd;			
		String str = "no";
		System.out.println(newPassword);
		if(m != null) {				
			newPassword = SHA256Util.getEncrypt(mb_pwd, m.getSalt());		
			m.setMb_pwd(newPassword); 
			System.out.println("salt:"+m.getSalt());
			System.out.println("아이디:"+m.getMb_id()+", 비밀번호:"+m.getMb_pwd());
			r = BusinessDBManager.isBusiness(m);		
		}else {
			System.out.println("m 없음, 로그인 실패");
		}		
		
		if(r != null) {
			System.out.println("로그인 성공");
			str = m.getMb_id();
			System.out.println("/login2.do 컨트롤러 str : " + str);			
			int re = dao.updateRecdate(mb_id);
			if(re == 1) {
				System.out.println("접속일 업데이트");
			}else {
				System.out.println("접속일 업데이트 실패");
			}
			
		}else {
			System.out.println("r 없음, 로그인 실패");		
		}
		
		System.out.println("BusinessController 메시지 : 아이디 " + str);			
		System.out.println("BusinessController 메시지 : 암호화된 비밀번호 : " + newPassword);
		
		return str;
	}
	
	
	//사업자목록
	@RequestMapping(value = "/listBusiness.do", produces = "application/json;charset=UTF-8")
	public String listBusiness() {
		String str = "";
		List<BusinessVo> list = dao.listAll();
		Gson gson = new Gson();
		str = gson.toJson(list);
		System.out.println("BusinessController 메시지 : 사업자목록" + str);
		return str;
	}
	
	
	//사업자 등록
	@RequestMapping("/signIn2.do")
	public String insertBusiness(BusinessVo m) {
		String str = "";
		String salt = SHA256Util.generateSalt();
        String newPassword = SHA256Util.getEncrypt(m.getMb_pwd(), salt);
        m.setMb_pwd(newPassword);
        m.setSalt(salt);
        
        
        String path = "C:\\study\\STS_Study\\campingspot_0514\\src\\main\\resources\\static\\profile";
		MultipartFile uploadFile = m.getUploadFile();
		String fname = "";
		 if(uploadFile != null) {
			fname = uploadFile.getOriginalFilename();
			try {
				byte []data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path +"\\"+fname);
				fos.write(data);
				fos.close();
			}catch (Exception e) {				
				System.out.println(e.getMessage());
			}
		 }
		m.setMb_fname(fname);
		System.out.println("사진 : " + fname);
       
        dao.insertBusiness(m);
        System.out.println("BusinessController 메시지 : 사업자등록" + str);
		return str;
	}	
	
	//아이디 중복체크
	@RequestMapping("/checkId2.do")
	public int checkId(String mb_id) {
		System.out.println("입력아이디: " + mb_id);
		int re = dao.checkId(mb_id);
		System.out.println("BusinessController 메시지 : 중복체크" + re);
		return re;
	}
	
	//사업자 삭제
	@RequestMapping("/deleteBusiness.do")
	public int deleteBusiness(String mb_id) {		
		System.out.println("사업자회원삭제 " + mb_id);		
		//MemberVo m = dao.getMember(mc_id);
		int re = dao.deleteBusiness(mb_id);
		return re;
	}
	
	//사업자 수정 - 세션정보들 불러오기 mc_id에 vo 담아서
	@RequestMapping("/sessionBusiness.do")
	public BusinessVo sessionBusiness(String mb_id) {		//dao에서 받아옴
		BusinessVo smout = dao.sessionBusiness(mb_id);
		System.out.println("BusinessController 메시지 : sessionBusiness 동작 " + smout);
		return smout;
	}	
	
	//회원수정
	@RequestMapping("/updateBusiness.do")
	public String updateBusiness(BusinessVo mv) {
		String str = "";
		
		BusinessVo check = dao.getBusiness(mv.getMb_id());		
        String newPassword = SHA256Util.getEncrypt(mv.getMb_pwd(), check.getSalt());
        mv.setMb_pwd(newPassword);
        mv.setSalt(check.getSalt());  
        
        String path = "C:\\study\\STS_Study\\campingspot_0514\\src\\main\\resources\\static\\profile";
		String oldFname = mv.getMb_fname();
        MultipartFile uploadFile = mv.getUploadFile();
		String fname = null;
		if(uploadFile != null) {
		//수정할 파일이 있다면
			
			fname = uploadFile.getOriginalFilename();
			System.out.println(fname);
			if(fname != null && !fname.equals("")) {	
				//수정할 파일이 올라왔다면
				
				mv.setMb_fname(fname);
				try {
					byte []data = uploadFile.getBytes();
					FileOutputStream fos = new FileOutputStream(path +"/"+fname);
					fos.write(data);
					fos.close();
				}catch (Exception e) {				
					System.out.println(e.getMessage());
				}
			}
			
		 }
		 int re = dao.updateBusiness(mv);
		 
		 //수정 성공, 파일도 수정, 원래 게시물에 파일이 있었다면 원래 있던 파일 지워줘라 
		 if(re > 0 	&& fname != null && !fname.equals("") && oldFname != null && !oldFname.equals("")){
				File file = new File(path + "/" + oldFname);
				file.delete();
			}
			if(re >=1 ) {
				str = "게시물 수정 성공했습니다.";
			}
		return str;		
	}
	
	
	
	
}