package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.demo.db.MemberDBManager;
import com.example.demo.vo.MemberVo;

@Repository
public class MemberDao {

	public List<MemberVo> listAll(){
		return MemberDBManager.listAll();
	}
	
	public int insertMember(MemberVo m) {
		return MemberDBManager.insertMember(m);
	}
	
	public MemberVo getMember(String mc_id) {
		return MemberDBManager.getMember(mc_id);
	}
	
	public int checkId(String mc_id) {
		return MemberDBManager.checkId(mc_id);
	}
	
	public int updateRecdate(String mc_id) {
		return MemberDBManager.updateRecdate(mc_id);
	}
	
	public int findPwd(HashMap<String, String> map) {
		return MemberDBManager.findPwd(map);
	}
	
	public int updatePwd(String mc_id, String mc_email) {
		return MemberDBManager.updatePwd(mc_id, mc_email);
	}
	
	public int deleteMember(String mc_id) {
		return MemberDBManager.deleteMember(mc_id);
	}

	public MemberVo sessionMember(String mc_id) {
		return MemberDBManager.sessionMember(mc_id);
	}
	
	public int updateMember(MemberVo um) {
		return MemberDBManager.updateMember(um);
	}
	
	public int updateMember2(MemberVo um) {
		return MemberDBManager.updateMember2(um);
	}
	
}