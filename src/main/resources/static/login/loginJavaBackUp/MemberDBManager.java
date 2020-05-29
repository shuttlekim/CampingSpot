package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.MemberVo;

public class MemberDBManager {
	
	private static SqlSessionFactory factory;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch (Exception e) {			
			System.out.println(e.getMessage());
		}
	}
	 
	//회원세션
	public static MemberVo isMember(MemberVo m) {
		MemberVo r = null;
		SqlSession session = factory.openSession();
		r = session.selectOne("member.isMember", m);
		session.close();
		System.out.println("MemberDBManager isMember 메시지 : " + r);
		return r;		
	}
	
	//회원목록
	public static List<MemberVo> listAll(){
		List<MemberVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("member.listAll");
		session.close();
		return list;
	}
	
	//회원가입
	public static int insertMember(MemberVo m) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("member.insert", m);
		System.out.println("MemberDBManager insertMember 메시지 : " + re);
		return re;
	}
	
	//맴버 암호화 및 로그인처리
	public static MemberVo getMember(String mc_id) {
		SqlSession session = factory.openSession();
		MemberVo m = session.selectOne("member.getMember", mc_id);
		session.close();
		//System.out.println("DBManaer 메시지 : " + m);
		return m;
	}
	
	//아이디 중복
	public static int checkId(String mc_id) {
		SqlSession session = factory.openSession();
		MemberVo c = session.selectOne("member.checkId", mc_id);
		int r = 0;
		if( c != null ) {
			r = 1;
		}
		//System.out.println("DBManaer 메시지 : " + r);
		return r;
	}
	
	//최근접속일 업데이트
	public static int updateRecdate(String mc_id) {
		SqlSession session = factory.openSession(true);
		System.out.println("mc_id:" + mc_id);
		int re = 0;
		re = session.update("member.updateRecdate", mc_id);
		//update("member.updateRecdate", m);
		System.out.println("MemberDBManager 접속일 업데이트 re : " + re);
		session.close();
		return re;
	}
	
	//비밀번호 찾기 메일발송
	public static int findPwd(HashMap<String, String> map) {
		SqlSession session = factory.openSession();
		System.out.println("DBManaer 메시지 : " + map);
		MemberVo c = session.selectOne("member.findPwd", map);
		int r = 0;
		if( c != null ) {
			r = 1;
		}
		System.out.println("MemberDBManager findPwd 메시지 : " + r);
		return r;
	}
	
	//임시비밀번호 발송
	public static int updatePwd(String mc_id, String mc_email) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		//System.out.println("mc_id:" + mc_id);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mc_id", mc_id);
		map.put("mc_email", mc_email);
		
		re = session.update("member.updatePwd", map);
		System.out.println("MemberDBManager 임시비밀번호 re : " + re);
		return re;
	}	
	
	//회원탈퇴
	public static int deleteMember(String mc_id) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.delete("member.deleteMember", mc_id);
		session.close();
		System.out.println("MemberDBManager 회원탈퇴 : " + re);
		return re;
	}
	
	//세션맴버(수정을 위한 mc_id 로 모든회원정보 vo에 담아 가져와서)
	//select * from member_customer where mc_id=#{mc_id} ---> 이걸 
	public static MemberVo sessionMember(String mc_id) {	//in (mc_id)
		SqlSession session = factory.openSession();
		MemberVo smout = session.selectOne("member.sessionMember", mc_id);	//out 
		session.close();
		System.out.println("MemberDBManager 메시지 sessionMember : " + smout);
		return smout;
	}
	
	public static MemberVo sessionMember2(String mc_id) {	//in (mc_id)
		SqlSession session = factory.openSession();
		MemberVo smout = session.selectOne("member.sessionMember", mc_id);	//out 
		session.close();
		System.out.println("MemberDBManager 메시지 sessionMember2 : " + smout);
		return smout;
	}
	
	//맴버수정
	public static int updateMember(MemberVo um) {
		int re = -1;
		SqlSession session = factory.openSession(true);
	    re = session.update("member.updateMember", um);
	    session.close();
	    System.out.println("MemberDBManager 메시지 updateMember updateMember : " + re);
	    return re;		
	}
	 
	//맴버수정2
	public static int updateMember2(MemberVo um) {
		int re = -1;
		SqlSession session = factory.openSession(true);
	    re = session.update("member.updateMember2", um);
	    session.close();
	    System.out.println("MemberDBManager 메시지 updateMember2 : " + re);
	    return re;		
	}
	
}