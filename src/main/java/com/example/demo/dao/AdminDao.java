package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.AdminDBManager;
import com.example.demo.vo.DistrictChartVo;
import com.example.demo.vo.AdminLogVo;
import com.example.demo.vo.AdminNoticeVo;
import com.example.demo.vo.CampTypeChartVo;
import com.example.demo.vo.CampingRankChartVo;
import com.example.demo.vo.MemberBusinessVo;
import com.example.demo.vo.MemberCustomerVo;

@Repository
public class AdminDao {
	
	public List<AdminNoticeVo> adminNotice(){
		System.out.println("Dao동작함");
		return AdminDBManager.adminNotice();
	}
	
	public AdminNoticeVo detailAdminNotice(int an_no){
		System.out.println("Dao동작함");
		return AdminDBManager.detailAdminNotice(an_no);
	}
	
	public int insertAdminNotice(AdminNoticeVo an){
		int i = AdminDBManager.insertAdminNotice(an);
		return i;
	}
	
	public int updateAdminNotice(AdminNoticeVo an) {
		System.out.println("AdminDao.start");
		int i = AdminDBManager.updateAdminNotice(an);
		System.out.println("AdminDao.end");
		return i;
	}
	
	public int an_delete(AdminNoticeVo an) {
		return AdminDBManager.delete_an(an);
	}
	
	public AdminNoticeVo popup(AdminNoticeVo an) {
		return AdminDBManager.popup(an);
	}
	
	
	///////////////////////////////////////////////mc
	
	
	public List<MemberCustomerVo> adminMemberAll(){
		return AdminDBManager.adminMemberCustomerAll();
	}
	
	public List<MemberCustomerVo> adminMemberSerch(HashMap map){
		return AdminDBManager.adminMemberSearchList(map);
	}
	
	public MemberCustomerVo detailadminMember(String mc_id){
		System.out.println("Dao동작함");
		return AdminDBManager.detailAdminMember(mc_id);
	}
	
	public int insertAdminMember(MemberCustomerVo mc){
		int i = AdminDBManager.insertAdminMember(mc);
		return i;
	}
	
	public int updateAdminMember(MemberCustomerVo mc) {
		int i = AdminDBManager.updateAdminMember(mc);
		return i;
	}
	
	public int deleteAdminMember(MemberCustomerVo mc) {
		return AdminDBManager.deleteAdminMember(mc);
	}
	
	
	
	/////////////////////////////////////////////////////mb
	
	
	public List<MemberBusinessVo> adminBusinessAll(){
		return AdminDBManager.adminMemberBusinessAll();
	}
	
	public List<MemberBusinessVo> adminBusinessSearch(HashMap map){
		return AdminDBManager.adminMemberBusinessSearch(map);
	}

	public MemberBusinessVo detailadminBusiness(String mb_id){
		System.out.println("Dao동작함");
		return AdminDBManager.detailAdminBusiness(mb_id);
	}
	public int insertAdminBusiness(MemberBusinessVo mb){
		int i = AdminDBManager.insertAdminBusiness(mb);
		return i;
	}
	
	public int updateAdminBusiness(MemberBusinessVo mb) {
		int i = AdminDBManager.updateAdminBusiness(mb);
		return i;
	}
	
	public int deleteAdminBusiness(MemberBusinessVo mb) {
		return AdminDBManager.deleteAdminBusiness(mb);
	}
	
	
	////////////////////////////////////////////////////auth
	
	public List<MemberCustomerVo> adminListAll(){
		return AdminDBManager.adminAuthList();
	}
	
	public List<MemberCustomerVo> adminListSearch(HashMap map){
		return AdminDBManager.adminAuthSearchList(map);
	}
	
	public MemberCustomerVo detailadminList(String mc_id){
		System.out.println("Dao동작함");
		return AdminDBManager.detailAdminAuth(mc_id);
	}
	
	public int updateAdminList(MemberCustomerVo mc) {
		int i = AdminDBManager.updateAdminAuth(mc);
		return i;
	}
	
	public int deleteAdminList(MemberCustomerVo mc) {
		return AdminDBManager.deleteAdminAuth(mc);
	}
	
	////////////////////////////////////////log
	
	public List<AdminLogVo> adminLogListAll(){
		return AdminDBManager.adminLogList();
	}
	
	public int insertAdminLog(AdminLogVo al) {
		return AdminDBManager.insertAdminLog(al);
	}
	
	/////////////////////////////////////////chart
	
	public List<DistrictChartVo> districtChart(){
		return AdminDBManager.districtChart();
	}
	
	public List<CampTypeChartVo> campTypeChart(){
		return AdminDBManager.campTypeChart();
	}
	
	public List<CampingRankChartVo> campingRankChart(){
		return AdminDBManager.campingrank();
	}
	
}
