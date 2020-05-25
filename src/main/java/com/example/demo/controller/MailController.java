package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.BusinessDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.service.SHA256Util;
import com.example.demo.vo.BusinessVo;
import com.example.demo.vo.MemberVo;

@Controller
public class MailController {

	@Autowired
	private MemberDao maildao;
	
	public void setMaildao(MemberDao maildao) {
		this.maildao = maildao;
	}

	@Autowired
	private BusinessDao maildao2;
	
	public void setMaildao(BusinessDao maildao2) {
		this.maildao2 = maildao2;
	}	
	
	@Autowired
	private MailSender javaMailSender;

	public void setJavaMailSender(MailSender mailSender) {
		this.javaMailSender = mailSender;
	}

	
	
	//회원 비밀번호 찾기
	@RequestMapping("/email.do")
	@ResponseBody
	public String mailMember(String mc_id, String mc_email) {
		String r = "";
	
		try {		
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setSubject("[캠핑스팟]임시 비밀번호 입니다.");
			mailMessage.setFrom("campingspot12345@gmail.com");
			mailMessage.setText("비밀번호 안내 메일입니다.\n임시 비밀번호는 [12345678] 입니다.\n비밀번호를 변경하여 주세요");
			System.out.println(mc_email);
			mailMessage.setTo(mc_email);
			
			javaMailSender.send(mailMessage);			
			
			int re = maildao.updatePwd(mc_id, mc_email);
			
			if(re != 0) {
			
			MemberVo m = maildao.getMember(mc_id);
			
			System.out.println("메일 컨트롤러 동작 re: " + re);
			System.out.println("메일 컨트롤러 동작 id: " + mc_id);
			System.out.println("메일 컨트롤러 동작 pw: " + m.getMc_pwd());
			System.out.println("메일 컨트롤러 동작 id: " + m.getMc_id());
	        String newPassword = SHA256Util.getEncrypt(m.getMc_pwd(), m.getSalt());
	        System.out.println("이전 비밀번호" + m.getMc_pwd());
	        System.out.println("불러온 salt" + m.getSalt());
	        m.setMc_pwd(newPassword);
	        System.out.println("새로운 비밀번호: " + newPassword);
	        
	        int re2 = 0;
	        
	        re2 = maildao.updateMember2(m);
	        
		        if(re2 >= 1) {
		        	r = "success";
		        	System.out.println("비밀번호 업데이트 성공: " + r);
		        }else {
		        	r = "fail";
		        	System.out.println("비밀번호 업데이트 실패: " + r);
		        }
			}else {
				System.out.println("아이디, 메일이 존재하지 않습니다.");
			}	        

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}			
		
		return r;
	}
	
	//사업자 비밀번호 찾기
	@RequestMapping("/email2.do")
	@ResponseBody
	public String mailBusiness(String mb_id, String mb_email) {
		String r = "";
	
		try {		
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setSubject("[캠핑스팟]임시 비밀번호 입니다.");
			mailMessage.setFrom("campingspot12345@gmail.com");
			mailMessage.setText("비밀번호 안내 메일입니다.\n임시 비밀번호는 [12345678] 입니다.\n비밀번호를 변경하여 주세요");
			System.out.println(mb_email);
			mailMessage.setTo(mb_email);
			
			javaMailSender.send(mailMessage);			
			
			int re = maildao2.updatePwd(mb_id, mb_email);
			
			if(re != 0) {
			
			BusinessVo m2 = maildao2.getBusiness(mb_id);
			
			System.out.println("메일 컨트롤러 동작 re: " + re);
			System.out.println("메일 컨트롤러 동작 id: " + mb_id);
			System.out.println("메일 컨트롤러 동작 pw: " + m2.getMb_pwd());			
			System.out.println("메일 컨트롤러 동작 id: " + m2.getMb_id());
	        String newPassword = SHA256Util.getEncrypt(m2.getMb_pwd(), m2.getSalt());
	        System.out.println("이전 비밀번호" + m2.getMb_pwd());
	        System.out.println("불러온 salt" + m2.getSalt());
	        m2.setMb_pwd(newPassword);
	        System.out.println("새로운 비밀번호: " + newPassword);
	        
	        int re2 = 0;
	        
	        re2 = maildao2.updateBusiness2(m2);
	        
		        if(re2 >= 1) {
		        	r = "success";
		        	System.out.println("비밀번호 업데이트 성공: " + r);
		        }else {
		        	r = "fail";
		        	System.out.println("비밀번호 업데이트 실패: " + r);
		        }
			}else {
				System.out.println("아이디, 메일이 존재하지 않습니다.");
			}	        

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}			
		
		return r;
	}	
	
}