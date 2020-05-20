package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BusinessVo;

public class BusinessDBManager {
	
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
	
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	
	//사업자세션
	public static BusinessVo isBusiness(BusinessVo m) {
		BusinessVo r = null;
		SqlSession session = factory.openSession();
		r = session.selectOne("business.isBusiness", m);
		session.close();
		System.out.println("BusinessDBManager 메시지 : " + r);
		return r;		
	}
	
	//사업자목록
	public static List<BusinessVo> listAll(){
		List<BusinessVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("business.listAll");
		session.close();
		return list;
	}
	
	//사업자회원가입
	public static int insertBusiness(BusinessVo m) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("business.insert", m);
		System.out.println("BusinessDBManager 메시지 : " + re);
		return re;
	}
	
	//사업자회원 암호화 및 로그인처리
	public static BusinessVo getBusiness(String mb_id) {
		SqlSession session = factory.openSession();
		BusinessVo m = session.selectOne("business.getBusiness", mb_id);
		session.close();
		//System.out.println("DBManaer 메시지 : " + m);
		return m;
	}
	
	//아이디 중복
	public static int checkId(String mb_id) {
		SqlSession session = factory.openSession();
		BusinessVo c = session.selectOne("business.checkId", mb_id);
		int r = 0;
		if( c != null ) {
			r = 1;
		}
		//System.out.println("DBManaer 메시지 : " + r);
		return r;
	}
	
	//최근접속일 업데이트
	public static int updateRecdate(String mb_id) {
		SqlSession session = factory.openSession(true);
		System.out.println("mb_id:" + mb_id);
		int re = 0;
		re = session.update("business.updateRecdate", mb_id);
		//update("member.updateRecdate", m);
		System.out.println("BusinessDBManager 접속일 업데이트 re : " + re);
		session.close();
		return re;
	}
	
	//비밀번호 찾기 메일발송
	public static int findPwd(HashMap<String, String> map) {
		SqlSession session = factory.openSession();
		System.out.println("DBManaer 메시지 : " + map);
		BusinessVo c = session.selectOne("business.findPwd", map);
		int r = 0;
		if( c != null ) {
			r = 1;
		}
		System.out.println("BusinessDBManager 메시지 : " + r);
		return r;
	}
	
	//임시비밀번호 발송
	public static int updatePwd(String mb_id, String mb_email) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		//System.out.println("mc_id:" + mc_id);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mb_id", mb_id);
		map.put("mb_email", mb_email);
		
		re = session.update("business.updatePwd", map);
		System.out.println("BusinessDBManager 임시비밀번호 re : " + re);
		return re;
	}
	
	//사업자 탈퇴
	public static int deleteBusiness(String mb_id) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.delete("business.deleteBusiness", mb_id);
		session.close();
		System.out.println("BusinessDBManager 회원탈퇴 : " + re);
		return re;
	}
	
	//세션 사업자(수정을 위한 mc_id 로 모든회원정보 vo에 담아 가져와서)
	//select * from member_customer where mc_id=#{mc_id} ---> 이걸 
	public static BusinessVo sessionMember(String mb_id) {	//in (mc_id)
		SqlSession session = factory.openSession();
		BusinessVo smout = session.selectOne("business.sessionBusiness", mb_id);	//out 
		session.close();
		System.out.println("BusinessDBManaer 메시지 sessionBusiness : " + smout);
		return smout;
	}
	
	//사업자 수정
	public static int updateBusiness(BusinessVo um) {
		int re = -1;
		SqlSession session = factory.openSession(true);
	    re = session.update("business.updateBusiness", um);
	    session.close();
	    System.out.println("BusinessDBManaer 메시지 updateBusiness : " + re);
	    return re;		
	}
	
	//맴버수정2
	public static int updateBusiness2(BusinessVo um) {
		int re = -1;
		SqlSession session = factory.openSession(true);
	    re = session.update("business.updateBusiness2", um);
	    session.close();
	    System.out.println("DBManaer 메시지 updateBusiness2 : " + re);
	    return re;		
	}

	
	
	
	
}