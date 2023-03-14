package com.sga.sol.vc.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sga.sol.vc.vo.MemberVo;

public interface MemberService {
	
	//����� ��� �ҷ�����
	List<MemberVo> selectAllMember() throws Exception;
	
	//���̵� �ߺ� Ȯ��
	int checkId(String user_id) throws Exception;
	
	//����� ���
	void insertMember(MemberVo vo) throws Exception;
	
	//����� ����
	void updateMemberByAdmin(MemberVo vo) throws Exception;
	
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
}
