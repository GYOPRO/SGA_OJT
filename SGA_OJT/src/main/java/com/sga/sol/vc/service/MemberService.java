package com.sga.sol.vc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sga.sol.vc.util.ECBPasswordUtil;
import com.sga.sol.vc.util.sha3;
import com.sga.sol.vc.vo.MemberVo;

public interface MemberService {
	
	//����� ��� �ҷ�����
	List<MemberVo> selectAllMember() throws Exception;
	
	//���̵� �ߺ� Ȯ��
	int checkId(String userId) throws Exception;
	
	//����� ���
	void insertMember(MemberVo vo) throws Exception;
	
	//���� ���� ��ȸ(1��)
	MemberVo selectOneUser(String userId) throws Exception;
	
	//����� ����
	void updateUser(Map<String, Object> paramMap) throws Exception;
	
	//�α��� Ȯ��(���̵�, ��й�ȣ)
	int loginChk(MemberVo vo) throws Exception;
	
	//�α��� ���� �� ī��Ʈ ����
	void updateFailLogin(MemberVo vo) throws Exception;
	
	//�α��� ���� Ƚ�� ��ȸ
	int selectFailLogin(MemberVo vo) throws Exception;
	
	//�α��� �� �ֱ� �α��� �ð� ����
	void updateLoginDtm(MemberVo vo) throws Exception;
	
	//�α��� ��� �ð� ����
	void updateLockDtm(MemberVo vo) throws Exception;
	
	//����� ���2
	void insertMember2(MemberVo vo) throws Exception;
	
	//�α��� Ȯ��
	MemberVo loginChk2(MemberVo vo) throws Exception;
	
	//ȸ����� ��ȣȭ
	MemberVo encryptUser(MemberVo vo) throws Exception;
	
	//ȸ�� �α��� Ȯ��
	String loginUser(MemberVo vo) throws Exception;
	
	//����� ����
	void deleteUser(MemberVo vo) throws Exception;
		
	
}
