package com.sga.sol.vc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sga.sol.vc.util.ECBPasswordUtil;
import com.sga.sol.vc.util.sha3;
import com.sga.sol.vc.vo.MemberVo;

public interface MemberService {
	
	//사용자 목록 불러오기
	List<MemberVo> selectAllMember() throws Exception;
	
	//아이디 중복 확인
	int checkId(String userId) throws Exception;
	
	//사용자 등록
	void insertMember(MemberVo vo) throws Exception;
	
	//유저 정보 조회(1명)
	MemberVo selectOneUser(String userId) throws Exception;
	
	//사용자 수정
	void updateUser(Map<String, Object> paramMap) throws Exception;
	
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
	
	//사용자 등록2
	void insertMember2(MemberVo vo) throws Exception;
	
	//로그인 확인
	MemberVo loginChk2(MemberVo vo) throws Exception;
	
	//회원등록 암호화
	MemberVo encryptUser(MemberVo vo) throws Exception;
	
	//회원 로그인 확인
	String loginUser(MemberVo vo) throws Exception;
	
	//사용자 삭제
	void deleteUser(MemberVo vo) throws Exception;
		
	
}
