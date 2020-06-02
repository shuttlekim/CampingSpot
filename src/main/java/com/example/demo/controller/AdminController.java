package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AdminDao;
import com.example.demo.vo.DistrictChartVo;
import com.example.demo.vo.AdminLogVo;
import com.example.demo.vo.AdminNoticeVo;
import com.example.demo.vo.CampTypeChartVo;
import com.example.demo.vo.CampingRankChartVo;
import com.example.demo.vo.MemberBusinessVo;
import com.example.demo.vo.MemberCustomerVo;
import com.google.gson.Gson;

@RestController
public class AdminController {
	@Autowired
	private AdminDao an_dao;

	public void setAn_dao(AdminDao an_dao) {
		this.an_dao = an_dao;
	}
	
	@RequestMapping(value="/adminNotice.do", produces = "application/json;charset=utf-8")
	public String adminNotice() {
		String str = "";
		List<AdminNoticeVo> list = an_dao.adminNotice();
		Gson gson = new Gson();
		str = gson.toJson(list);
		System.out.println("컨트롤러동작함");
		return str;
	}
	@RequestMapping("/detailAdminNotice.do")
	public AdminNoticeVo detailAdminNotice(int an_no) {
		AdminNoticeVo list = an_dao.detailAdminNotice(an_no);
		System.out.println("컨트롤러동작함");
		System.out.println(list);
		return list;
	}
	
	@RequestMapping("/insertAdminNotice.do")
	public String insertAdminNotice(@DateTimeFormat(pattern="yyyy-MM-dd") AdminNoticeVo an) {
		String str = "";
		int i = an_dao.insertAdminNotice(an);
		System.out.println("i : " + i);
		return str;
	}
	@RequestMapping("/deleteAdminNotice.do")
	public String an_delete(AdminNoticeVo an) {
		String str = "";
		an_dao.an_delete(an);
		return str;
	}
	@RequestMapping("/updateAdminNotice.do")
	public String an_update(AdminNoticeVo an) {
		String str = "";
		System.out.println("AdminController.start");
		an_dao.updateAdminNotice(an);
		System.out.println("AdminController.end");
		return str;
	}
	@RequestMapping("/getpop.do")
	public AdminNoticeVo an_pop(AdminNoticeVo an) {
		AdminNoticeVo list = an_dao.popup(an);
		String ispost= an.getIspost();
		if(ispost.equals("y")) {
			System.out.println("팝업여부 : Y");
		}else {
			System.out.println("팝업여부 : N");
		}
		return list;
	}
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////mc
	
	
	@RequestMapping(value="/adminMember.do", produces = "application/json;charset=utf-8")
	public String adminMemberAll() {
		String str = "";
		List<MemberCustomerVo> list = an_dao.adminMemberAll();
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}
	
	@RequestMapping(value="/adminMemberSearch.do", produces = "application/json;charset=utf-8")
	public String adminMemberSearch(String keyword, String searchColumn) {
		System.out.println("keyword : "+keyword);
		System.out.println("searchColumn : "+searchColumn);
		String str = "";
		HashMap map = new HashMap();
		map.put("keyword", keyword);
		map.put("searchColumn", searchColumn);
		System.out.println("map : " + map);
		List<MemberCustomerVo> list = an_dao.adminMemberSerch(map);
		System.out.println("list : " + list);
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}
	
	
	@RequestMapping("/detailAdminMember.do")
	public MemberCustomerVo detailAdminMember(String mc_id) {
		MemberCustomerVo list = an_dao.detailadminMember(mc_id);
		System.out.println("detailAdminMember컨트롤러동작함");
		System.out.println(list);
		return list;
	}
	
	@RequestMapping("/insertAdminMember.do")
	public String insertAdminMember(@DateTimeFormat(pattern="yyyy-MM-dd") MemberCustomerVo mc) {
		String str = "";
		int i = an_dao.insertAdminMember(mc);
		System.out.println("i : " + i);
		return str;
	}
	
	@RequestMapping("/deleteAdminMember.do")
	public String deleteAdminMember(MemberCustomerVo mc) {
		String str = "";
		an_dao.deleteAdminMember(mc);
		return str;
	}
	@RequestMapping("/updateAdminMember.do")
	public String updateAdminMember(MemberCustomerVo mc) {
		String str = "";
		an_dao.updateAdminMember(mc);
		return str;
	}
	
	
	///////////////////////////////////////////////////////////////mb
	
	
	@RequestMapping(value="/adminBusiness.do", produces = "application/json;charset=utf-8")
	public String adminBusinessAll() {
		String str = "";
		List<MemberBusinessVo> list = an_dao.adminBusinessAll();
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}
	
	@RequestMapping(value="/adminBusinessSearch.do", produces = "application/json;charset=utf-8")
	public String adminBusinessSearch(String keyword, String searchColumn) {
		System.out.println("keyword : "+keyword);
		System.out.println("searchColumn : "+searchColumn);
		String str = "";
		HashMap map = new HashMap();
		map.put("keyword", keyword);
		map.put("searchColumn", searchColumn);
		System.out.println("map : " + map);
		List<MemberBusinessVo> list = an_dao.adminBusinessSearch(map);
		System.out.println("list : " + list);
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}
	
	
	@RequestMapping("/detailAdminBusiness.do")
	public MemberBusinessVo detailAdminBusiness(String mb_id) {
		MemberBusinessVo list = an_dao.detailadminBusiness(mb_id);
		System.out.println("detailAdminBusiness컨트롤러동작함");
		System.out.println(list);
		return list;
	}
	
	@RequestMapping("/insertAdminBusiness.do")
	public String insertAdminBusiness(@DateTimeFormat(pattern="yyyy-MM-dd") MemberBusinessVo mb) {
		String str = "";
		int i = an_dao.insertAdminBusiness(mb);
		System.out.println("i : " + i);
		return str;
	}
	
	@RequestMapping("/deleteAdminBusiness.do")
	public String deleteAdminBusiness(MemberBusinessVo mb) {
		String str = "";
		an_dao.deleteAdminBusiness(mb);
		return str;
	}
	@RequestMapping("/updateAdminBusiness.do")
	public String updateAdminBusiness(MemberBusinessVo mb) {
		String str = "";
		an_dao.updateAdminBusiness(mb);
		return str;
	}
	
	////////////////////////////////////////////////////////////////////auth
	
	@RequestMapping(value="/adminList.do", produces = "application/json;charset=utf-8")
	public String adminList() {
		String str = "";
		List<MemberCustomerVo> list = an_dao.adminListAll();
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}
	
	@RequestMapping(value="/adminListSearch.do", produces = "application/json;charset=utf-8")
	public String adminListSearch(String keyword, String searchColumn) {
		System.out.println("keyword : "+keyword);
		System.out.println("searchColumn : "+searchColumn);
		String str = "";
		HashMap map = new HashMap();
		map.put("keyword", keyword);
		map.put("searchColumn", searchColumn);
		System.out.println("map : " + map);
		List<MemberCustomerVo> list = an_dao.adminListSearch(map);
		System.out.println("list : " + list);
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}
	
	
//	@RequestMapping(value="/adminMember.do", produces = "application/json;charset=utf-8")
//	public String adminMemberAll() {
//		String str = "";
//		List<MemberCustomerVo> list = an_dao.adminMemberAll();
//		Gson gson = new Gson();
//		str = gson.toJson(list);
//		return str;
//	}
//	
	
	
	@RequestMapping("/detailAdminList.do")
	public MemberCustomerVo detailAdminList(String mc_id) {
		MemberCustomerVo list = an_dao.detailadminList(mc_id);
		System.out.println("detailAdminList컨트롤러동작함");
		System.out.println(list);
		return list;
	}
	
	@RequestMapping("/deleteAdminList.do")
	public String deleteAdminList(MemberCustomerVo mc) {
		String str = "";
		an_dao.deleteAdminList(mc);
		return str;
	}
	@RequestMapping("/updateAdminList.do")
	public String updateAdminList(MemberCustomerVo mc) {
		String str = "";
		an_dao.updateAdminList(mc);
		return str;
	}
	
	///////////////////////////////////////////////////log
	
	@RequestMapping(value="/adminLogList.do", produces = "application/json;charset=utf-8")
	public String adminLogList() {
		String str = "";
		List<AdminLogVo> list = an_dao.adminLogListAll();
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}
	
	@RequestMapping("/insertAdminLog.do")
	public String insertAdminLog(@DateTimeFormat(pattern="yyyy-MM-dd") AdminLogVo al) {
		String str = "";
		int i = an_dao.insertAdminLog(al);
		System.out.println("i : " + i);
		return str;
	}
	
	
	
	//////////////////////////////////////chart
	
	@RequestMapping(value="/districtChart.do", produces = "application/json;charset=utf-8")
	public String districtChart() {
		String str = "";
		List<DistrictChartVo> list = an_dao.districtChart();
		System.out.println("list : " + list);
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}
	
	@RequestMapping(value="/campTypeChart.do", produces = "application/json;charset=utf-8")
	public String campTypeChart() {
		String str = "";
		List<CampTypeChartVo> list = an_dao.campTypeChart();
		System.out.println("list : " + list);
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}
	
	@RequestMapping(value="/campingRankChart.do", produces = "application/json;charset=utf-8")
	public String campingRankChart() {
		String str = "";
		List<CampingRankChartVo> list = an_dao.campingRankChart();
		System.out.println("list : " + list);
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}

	//////POP
	@RequestMapping(value="/getAnNo.do", produces = "application/json;charset=utf-8")
	public String getAnNo(String userId) {
		String str = "";
		System.out.println("userId : " + userId);
		List<AdminNoticeVo> list = an_dao.getAnNo(userId);
		Gson gson = new Gson();
		str = gson.toJson(list);
		return str;
	}
}
