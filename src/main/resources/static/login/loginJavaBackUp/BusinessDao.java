package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.BusinessDBManager;

import com.example.demo.vo.BusinessVo;

@Repository
public class BusinessDao {

	public List<BusinessVo> listAll(){
		return BusinessDBManager.listAll();
	}
	
	public int insertBusiness(BusinessVo m) {
		return BusinessDBManager.insertBusiness(m);
	}
	
	public BusinessVo getBusiness(String mb_id) {
		return BusinessDBManager.getBusiness(mb_id);
	}
	
	public int checkId(String mb_id) {
		return BusinessDBManager.checkId(mb_id);
	}
	
	public int updateRecdate(String mb_id) {
		return BusinessDBManager.updateRecdate(mb_id);
	}
	
	public int findPwd(HashMap<String, String> map) {
		return BusinessDBManager.findPwd(map);
	}
 
	public int updatePwd(String mb_id, String mb_email) {
		return BusinessDBManager.updatePwd(mb_id, mb_email);
	}
	
	public int deleteBusiness(String mb_id) {
		return BusinessDBManager.deleteBusiness(mb_id);
	}

	public BusinessVo sessionBusiness(String mb_id) {
		return BusinessDBManager.sessionMember(mb_id);
	}
	
	public int updateBusiness(BusinessVo um) {
		return BusinessDBManager.updateBusiness(um);
	}
	
	public int updateBusiness2(BusinessVo um) {
		return BusinessDBManager.updateBusiness2(um);
	}	
	
}