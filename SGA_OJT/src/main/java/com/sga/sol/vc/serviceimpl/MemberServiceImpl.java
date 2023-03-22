package com.sga.sol.vc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sga.sol.vc.mapper.MemberMapper;
import com.sga.sol.vc.service.MemberService;
import com.sga.sol.vc.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper membermapper;
	
	@Override
	public List<MemberVo> selectAllMember() throws Exception{
		return membermapper.selectAllMember();
	}
	
	@Override
	public int checkId(String user_id) throws Exception{
		int cnt = membermapper.checkId(user_id);
		return cnt;
	}
	
	@Override
	public void insertMember(MemberVo vo) throws Exception{
		membermapper.insertMember(vo);
	}
	
	@Override
	public MemberVo selectOneUser(String user_id) throws Exception{
		return membermapper.selectOneUser(user_id);
	}
	
	@Override
	public void updateMemberByAdmin(MemberVo vo) throws Exception{
		membermapper.updateMemberByAdmin(vo);
	}
	
	@Override
	public int loginChk(MemberVo vo) throws Exception{
		return membermapper.loginChk(vo);
	}
	
	@Override
	public void updateFailLogin(MemberVo vo) throws Exception{
		membermapper.updateFailLogin(vo);
	}
	
	//로그인 실패 횟수 조회
	@Override
	public int selectFailLogin(MemberVo vo) throws Exception{
		return membermapper.selectFailLogin(vo);
	}
	
	//로그인 시 최근 로그인 시간 변경
	@Override
	public void updateLoginDtm(MemberVo vo) throws Exception{
		membermapper.updateLoginDtm(vo);
	}
	
	//로그인 시 최근 로그인 시간 변경
	@Override
	public void updateLockDtm(MemberVo vo) throws Exception{
		membermapper.updateLockDtm(vo);
	}

	@Override
	public void insertMember2(MemberVo vo) throws Exception {
		membermapper.insertMember2(vo);
	}

	@Override
	public MemberVo loginChk2(MemberVo vo) throws Exception {
		return membermapper.loginChk2(vo);
	}
	
	
	
}
