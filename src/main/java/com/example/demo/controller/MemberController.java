package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.MemberDao;
import com.example.demo.db.MemberDBManager;
import com.example.demo.service.SHA256Util;
import com.example.demo.vo.MemberVo;
import com.google.gson.Gson;

@RestController
public class MemberController {

	@Autowired
	private MemberDao dao;
	
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}


	//회원 로그인
	@RequestMapping("/login.do")
	public String login(String mc_id, String mc_pwd) {
		
		MemberVo m = dao.getMember(mc_id);
		MemberVo r = null;
		
		String newPassword = mc_pwd;			
		String str = "no";
		
		System.out.println(newPassword);
		if(m != null) {				
			newPassword = SHA256Util.getEncrypt(mc_pwd, m.getSalt());		
			m.setMc_pwd(newPassword); 
			System.out.println("salt:"+m.getSalt());
			System.out.println("아이디:"+m.getMc_id()+", 비밀번호:"+m.getMc_pwd());
			r = MemberDBManager.isMember(m);		
		}else {
			System.out.println("m 없음, 로그인 실패");
		}		
		
		if(r != null) {
			System.out.println("로그인 성공");
			str = m.getMc_id();
			System.out.println("/login.do 컨트롤러 str : " + str);			
			int re = dao.updateRecdate(mc_id);
			if(re == 1) {
				System.out.println("접속일 업데이트");
			}else {
				System.out.println("접속일 업데이트 실패");
			}
			
		}else {
			System.out.println("r 없음, 로그인 실패");		
		}
		
		System.out.println("MemberController 메시지 : 아이디 " + str);			
		System.out.println("MemberController 메시지 : 암호화된 비밀번호 : " + newPassword);
		
		return str;
	}
	
	//회원목록
	@RequestMapping(value = "/listMember.do", produces = "application/json;charset=UTF-8")
	public String listMember() {
		String str = "";
		List<MemberVo> list = dao.listAll();
		Gson gson = new Gson();
		str = gson.toJson(list);
		System.out.println("MemberController 메시지 : 회원목록" + str);
		return str;
	}
	
	//회원가입
	//@RequestMapping("/signIn.do")
	@RequestMapping(value = "/signIn.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String insertMember(MemberVo m) {
		System.out.println("================sign.do 작동 시작 =======================");
		System.out.println("MemberVo:"+m);
		String str = "";
		
		if(m.getMc_auth() == 3) {			
		
			str = "";
			String salt = SHA256Util.generateSalt();
	        String newPassword = SHA256Util.getEncrypt(m.getMc_pwd(), salt);
	        m.setMc_pwd(newPassword);
	        m.setSalt(salt);	        
	        
	        //String path = "/resources/static/resource/profile"; 
	        String path = "C:\\Users\\YOGO\\git\\CampingSpot\\src\\main\\resources\\static\\resources\\profile";
	        //String path = request.getSession().getServletContext().getRealPath("resources\\\\static\\\\resources\\\\profile");
	        //String path = System.getProperty("/resource/profile");
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
			m.setMc_fname(fname);
			System.out.println("사진 : " + fname);
		}
		
        dao.insertMember(m);
        System.out.println("insertMember 작동");
        String memberId = dao.getMember(m.getMc_id()).getMc_id();
        System.out.println("memberId : " + memberId);
        str = memberId;
        System.out.println("str : " + str);
        System.out.println("MemberController 메시지 : 회원등록" + str);
		return str;
	}	

	//회원 아이디 중복체크
	//@RequestMapping("/checkId.do")
	@RequestMapping(value = "/checkId.do", method = {RequestMethod.GET, RequestMethod.POST})
	//@ResponseStatus(value=HttpStatus.OK)
	//@ResponseBody
	public int checkId(String mc_id) {
		System.out.println("입력아이디: " + mc_id);
		int re = dao.checkId(mc_id);
		System.out.println("MemberController 메시지 : 중복체크" + re);
		return re;
	}
	
	//회원 탈퇴
	@RequestMapping("/deleteMember.do")
	public int deleteMember(String mc_id) {		
		System.out.println("회원삭제 " + mc_id);		
		//MemberVo m = dao.getMember(mc_id);
		int re = dao.deleteMember(mc_id);
		return re;
	}
	
	//회원수정 - 세션정보들 불러오기 mc_id에 vo 담아서
	@RequestMapping("/sessionMember.do")
	public String sessionMember(String mc_id) {		//dao에서 받아옴
		System.out.println("------sessionMember 작동------");
		String str = "";
		MemberVo smout = dao.sessionMember(mc_id);
		System.out.println("MemberController 메시지 : smout 의 상태 " + smout);
		if(smout != null) {
		str = smout.getMc_id();
		System.out.println("str : " + str);
		//System.out.println("smout: " + smout);
		}else {
			str = null;
			System.out.println("str : " + str);
		}
		return str;
	}	
	
	
	//위에꺼 네이버 로그인테스트 하느라 저거였고 원본은 지금 이거
	//회원정보 세션
    @RequestMapping("/sessionMember2.do")
    public MemberVo sessionMember2(String mc_id) {        //dao에서 받아옴
        MemberVo smout = dao.sessionMember(mc_id);
        System.out.println("MemberController 메시지 : sessionMember2 동작 " + smout);
        return smout;
    }    
	
	//회원수정
	@RequestMapping("/updateMember.do")
	public String updateMember(MemberVo mv) {
		String str = "";
		
		MemberVo check = dao.getMember(mv.getMc_id());		
        String newPassword = SHA256Util.getEncrypt(mv.getMc_pwd(), check.getSalt());
        mv.setMc_pwd(newPassword);
        mv.setSalt(check.getSalt());  
        
        String path = "C:\\Users\\YOGO\\git\\CampingSpot\\src\\main\\resources\\static\\resources\\profile";
		String oldFname = mv.getMc_fname();
        MultipartFile uploadFile = mv.getUploadFile();
		String fname = null;
		if(uploadFile != null) {
		//수정할 파일이 있다면
			
			fname = uploadFile.getOriginalFilename();
			System.out.println(fname);
			if(fname != null && !fname.equals("")) {	
				//수정할 파일이 올라왔다면
				
				mv.setMc_fname(fname);
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
		 int re = dao.updateMember(mv);
		 
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
	
	//네아로(네이버 아이디 로그인) 세션 및 가입 처리
	@RequestMapping("/naver.do")
	public String naver(MemberVo n) {
		
		System.out.println("------sessionMember 작동------");
		System.out.println("input_id:" + n.getMc_id() );
		String str = "";
		MemberVo smout = dao.sessionMember(n.getMc_id());
		//MemberVo smout = dao.sessionMember(n.getMc_name());
		System.out.println("MemberController 메시지 : 불러온 smout 의 상태 " + smout);
			
		if(smout != null) {
			//str = smout.getMc_id();
			str = smout.getMc_name();
			System.out.println("str : " + str);
		}else {
			str = null;
			System.out.println("str : " + str);
		}		
		
		System.out.println("================sign.do 작동 시작 =======================");
		System.out.println("MemberVo:"+smout);
		
		if(smout == null) {
	        dao.insertMember(n);
	        System.out.println("insertMember 작동");
	        String memberId = dao.sessionMember(n.getMc_id()).getMc_id();
	        memberId = n.getMc_name();
	        System.out.println("memberId : " + memberId);
	        str = memberId;	        
	        System.out.println("회원 가입 후 str : " + str);
	        System.out.println("MemberVo:"+smout);
	        smout = dao.sessionMember(n.getMc_id());
	        str = smout.getMc_name();
		}		
		return str;		
	}	

	
}