package com.sga.sol.vc.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sga.sol.vc.vo.MemberVo;

public interface MemberMapper {
	//사용자 목록 불러오기
	List<MemberVo> selectAllMember() throws Exception;
	
	//아이디 중복 확인
	int checkId(String user_id) throws Exception;
	
	//사용자 등록
	void insertMember(MemberVo vo) throws Exception;
	
	//사용자 수정
	void updateMemberByAdmin(MemberVo vo) throws Exception;
	
	//로그인 확인(아이디, 비밀번호)
	int loginChk(MemberVo vo) throws Exception;
	
	//로그인 실패 시 카운트 증가
	void updateFailLogin(MemberVo vo) throws Exception;
	
	//로그인 실패 횟수 조회
	int selectFailLogin(MemberVo vo) throws Exception;
	
	//로그인 시 최근 로그인 시간 변경
	void updateLoginDtm(MemberVo vo) throws Exception;
	
	//로그인 잠금 시간 변경
	void updateLockDtm(MemberVo vo) throws Exception;
}
