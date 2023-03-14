package com.sga.sol.vc.serviceimpl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sga.sol.vc.mapper.MemberMapper;
import com.sga.sol.vc.vo.MemberVo;



@Service
public class MemberServiceImpl {
	
	@Autowired
	private MemberMapper membermapper;
	
	public List<MemberVo> selectAllMember() throws Exception{
		return membermapper.selectAllMember();
	}
	
	public int checkId(String user_id) throws Exception{
		int cnt = membermapper.checkId(user_id);
		return cnt;
	}
	
	public void insertMember(MemberVo vo) throws Exception{
		membermapper.insertMember(vo);
	}
	
	public void updateMemberByAdmin(MemberVo vo) throws Exception{
		membermapper.updateMemberByAdmin(vo);
	}
	
	public int loginChk(MemberVo vo) throws Exception{
		return membermapper.loginChk(vo);
	}
	
	public void updateFailLogin(MemberVo vo) throws Exception{
		membermapper.updateFailLogin(vo);
	}
	
	//�α��� ���� Ƚ�� ��ȸ
	public int selectFailLogin(MemberVo vo) throws Exception{
		return membermapper.selectFailLogin(vo);
	}
	
	//�α��� �� �ֱ� �α��� �ð� ����
	public void updateLoginDtm(MemberVo vo) throws Exception{
		membermapper.updateLoginDtm(vo);
	}
	
	//�α��� �� �ֱ� �α��� �ð� ����
	public void updateLockDtm(MemberVo vo) throws Exception{
		membermapper.updateLockDtm(vo);
	}

}
