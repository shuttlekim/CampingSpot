package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.DistrictChartVo;
import com.example.demo.vo.AdminLogVo;
import com.example.demo.vo.AdminNoticeVo;
import com.example.demo.vo.CampTypeChartVo;
import com.example.demo.vo.CampingRankChartVo;
import com.example.demo.vo.MemberBusinessVo;
import com.example.demo.vo.MemberCustomerVo;

public class AdminDBManager {
	
	private static SqlSessionFactory factory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static List<AdminNoticeVo> adminNotice(){
		SqlSession session = factory.openSession();
		List<AdminNoticeVo> list = session.selectList("adminnotice.select_an");
		System.out.println("매니저동작함");
		session.close();
		return list;
	}
	
	public static AdminNoticeVo detailAdminNotice(int an_no){
		SqlSession session = factory.openSession();
		AdminNoticeVo list = session.selectOne("adminnotice.get_an", an_no);
		System.out.println("DBManager.detail_an : "  + list);
		System.out.println("매니저동작함");
		session.close();
		return list;
	}
	
	public static int insertAdminNotice(AdminNoticeVo an) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("adminnotice.insert_an", an);
		session.close();
		return re;
	}
	
	public static int delete_an(AdminNoticeVo an) {
		int re =-1;
		SqlSession session = factory.openSession(true);
		re = session.delete("adminnotice.delete_an", an);
		session.close();
		return re;
	}
	
	public static int updateAdminNotice(AdminNoticeVo an) {
		int re =-1;
		System.out.println("AdminDBManager.start");
		SqlSession session = factory.openSession(true);
		System.out.println("an : " + an);
		re = session.update("adminnotice.update_an", an);
		System.out.println(re);
		System.out.println("AdminDBManager.end");
		session.close();
		return re;
	}
	
	
	
	public static AdminNoticeVo popup(AdminNoticeVo an) {
		SqlSession session = factory.openSession();
		AdminNoticeVo popup = session.selectOne("adminnotic.getpop", an);
		session.close();
		return popup;
		
	}
	
	//////////////////////////////////////////////////////////mc
	
	
	
	public static List<MemberCustomerVo> adminMemberCustomerAll(){
		SqlSession session = factory.openSession();
		List<MemberCustomerVo> list = session.selectList("adminmember.selectAllAdminMember");
		session.close();
		return list;
	}
	
	
	public static MemberCustomerVo detailAdminMember(String mc_id){
		SqlSession session = factory.openSession();
		MemberCustomerVo list = session.selectOne("adminmember.getMember", mc_id);
		System.out.println("DBManager.getMember : "  + list);
		System.out.println("getMember매니저동작함");
		session.close();
		return list;
	}
	
	public static List<MemberCustomerVo> adminMemberSearchList(HashMap map){
		SqlSession session = factory.openSession();
		List<MemberCustomerVo> list = session.selectList("adminmember.selectAdminMemberSearch", map);
		session.close();
		return list;
	}
	
	public static int insertAdminMember(MemberCustomerVo mc) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("adminmember.insertAdminMember", mc);
		session.close();
		return re;
	}
	
	public static int deleteAdminMember(MemberCustomerVo mc) {
		int re =-1;
		SqlSession session = factory.openSession(true);
		re = session.delete("adminmember.deleteAdminMember", mc);
		session.close();
		return re;
	}
	
	public static int updateAdminMember(MemberCustomerVo mc) {
		int re =-1;
		SqlSession session = factory.openSession(true);
		re = session.update("adminmember.updateAdminMember", mc);
		session.close();
		return re;
	}
	
	
	///////////////////////////////////////////////////////////////////mb
	
	public static List<MemberBusinessVo> adminMemberBusinessAll(){
		SqlSession session = factory.openSession();
		List<MemberBusinessVo> list = session.selectList("adminmember.selectAllBusiness");
		session.close();
		return list;
	}
	
	public static List<MemberBusinessVo> adminMemberBusinessSearch(HashMap map){
		SqlSession session = factory.openSession();
		List<MemberBusinessVo> list = session.selectList("adminmember.selectAdminBusinessSearch", map);
		session.close();
		return list;
	}
	
	public static MemberBusinessVo detailAdminBusiness(String mb_id){
		SqlSession session = factory.openSession();
		MemberBusinessVo list = session.selectOne("adminmember.getBusiness", mb_id);
		System.out.println("DBManager.getBusiness : "  + list);
		System.out.println("getBusiness매니저동작함");
		session.close();
		return list;
	}
	
	public static int insertAdminBusiness(MemberBusinessVo mb) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("adminmember.insertAdminBusiness", mb);
		session.close();
		return re;
	}
	
	public static int deleteAdminBusiness(MemberBusinessVo mb) {
		int re =-1;
		SqlSession session = factory.openSession(true);
		re = session.delete("adminmember.deleteAdminBusiness", mb);
		session.close();
		return re;
	}
	
	public static int updateAdminBusiness(MemberBusinessVo mb) {
		int re =-1;
		SqlSession session = factory.openSession(true);
		re = session.update("adminmember.updateAdminBusiness", mb);
		session.close();
		return re;
	}
	
	
	//////////////////////////////////////////////////////////////////////////auth
	
	public static List<MemberCustomerVo> adminAuthList(){
		SqlSession session = factory.openSession();
		List<MemberCustomerVo> list = session.selectList("adminmember.selectAdminAuth");
		session.close();
		return list;
	}
	
	public static List<MemberCustomerVo> adminAuthSearchList(HashMap map){
		SqlSession session = factory.openSession();
		List<MemberCustomerVo> list = session.selectList("adminmember.selectAdminAuthSearch", map);
		session.close();
		return list;
	}
	
	
	public static MemberCustomerVo detailAdminAuth(String mc_id){
		SqlSession session = factory.openSession();
		MemberCustomerVo list = session.selectOne("adminmember.getAdminAuth", mc_id);
		session.close();
		return list;
	}
	
	public static int updateAdminAuth(MemberCustomerVo mc) {
		int re =-1;
		SqlSession session = factory.openSession(true);
		re = session.update("adminmember.updateAdminAuth", mc);
		session.close();
		return re;
	}
	
	public static int deleteAdminAuth(MemberCustomerVo mc) {
		int re =-1;
		SqlSession session = factory.openSession(true);
		re = session.update("adminmember.deleteAdminAuth", mc);
		session.close();
		return re;
	}
	
	//////////////////////////////////////////////////////////log
	
	public static List<AdminLogVo> adminLogList(){
		SqlSession session = factory.openSession();
		List<AdminLogVo> list = session.selectList("adminnotice.selectAdminLog");
		session.close();
		return list;
	}
	
	public static int insertAdminLog(AdminLogVo al) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("adminnotice.insertAdminLog", al);
		session.close();
		return re;
	}
	
	
	/////////////////////////////////////////////////////////chart
	
	public static List<DistrictChartVo> districtChart(){
		SqlSession session = factory.openSession();
		List<DistrictChartVo> list = session.selectList("adminnotice.district");
		session.close();
		return list;
	}
	
	public static List<CampTypeChartVo> campTypeChart(){
		SqlSession session = factory.openSession();
		List<CampTypeChartVo> list = session.selectList("adminnotice.camptype");
		session.close();
		return list;
	}
	
	public static List<CampingRankChartVo> campingrank(){
		SqlSession session = factory.openSession();
		List<CampingRankChartVo> list = session.selectList("adminnotice.campingrank");
		session.close();
		return list;
	}
	
	public static List<AdminNoticeVo> getAnNo(String userId) {
		SqlSession session = factory.openSession();
		List<AdminNoticeVo> anNo = session.selectList("adminnotice.getAnNo", userId);
		session.close();
		return anNo;
	}

}
