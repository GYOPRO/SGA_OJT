package com.sga.sol.vc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sga.sol.vc.mapper.MemberMapper;
import com.sga.sol.vc.service.MemberService;
import com.sga.sol.vc.util.ECBPasswordUtil;
import com.sga.sol.vc.util.sha3;
import com.sga.sol.vc.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper membermapper;

	@Override
	public List<MemberVo> selectAllMember() throws Exception {
		return membermapper.selectAllMember();
	}

	@Override
	public int checkId(String userId) throws Exception {
		int cnt = membermapper.checkId(userId);
		return cnt;
	}

	@Override
	public void insertMember(MemberVo vo) throws Exception {
		membermapper.insertMember(vo);
	}

	@Override
	public MemberVo selectOneUser(String userId) throws Exception {
		return membermapper.selectOneUser(userId);
	}

	@Override
	public void updateMemberByAdmin(String userId) throws Exception {
		membermapper.updateMemberByAdmin(userId);
	}

	@Override
	public int loginChk(MemberVo vo) throws Exception {
		return membermapper.loginChk(vo);
	}

	@Override
	public void updateFailLogin(MemberVo vo) throws Exception {
		membermapper.updateFailLogin(vo);
	}

	// �α��� ���� Ƚ�� ��ȸ
	@Override
	public int selectFailLogin(MemberVo vo) throws Exception {
		return membermapper.selectFailLogin(vo);
	}

	// �α��� �� �ֱ� �α��� �ð� ����
	@Override
	public void updateLoginDtm(MemberVo vo) throws Exception {
		membermapper.updateLoginDtm(vo);
	}

	// �α��� �� �ֱ� �α��� �ð� ����
	@Override
	public void updateLockDtm(MemberVo vo) throws Exception {
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

	/*
	 * ȸ������ �� ����� �н����� ��ȣȭ salt(32) + inputPassword = dek(32) dek(32) + key(32) =
	 * kek(64)
	 */
	@Override
	public MemberVo encryptUser(MemberVo vo) throws Exception {
		//sha3 �ؽ��Լ� ��ü ����
		sha3 sha3 = new sha3();
		
		byte[] salt = sha3.generateSalt();
		String inputPassword = vo.getUserPassword();

		byte[] hashedPassword = sha3.generateHash(inputPassword, salt);
		
		byte[] key = ECBPasswordUtil.getEcbKey(16);
		byte[] kek = ECBPasswordUtil.encrypt(hashedPassword, key);


		vo.setUserPassword(inputPassword);
		vo.setSalt(ECBPasswordUtil.bytesToHex(salt));
		vo.setDek(ECBPasswordUtil.bytesToHex(hashedPassword));
		vo.setKey(ECBPasswordUtil.bytesToHex(key));
		vo.setKek(ECBPasswordUtil.bytesToHex(kek));

		return vo;
	}

	// Login Process
	@Override
	public String loginUser(MemberVo vo) throws Exception {
		// �Է� ���̵�
		String inputId = vo.getUserId();
		// �Է� �н�����
		String inputPassword = vo.getUserPassword();

		sha3 sha3 = new sha3();

		String Code = "";

		// ���̵� �����ϴ���
		int idChk = checkId(inputId);

		// db���� �н����� ���� �ҷ�����
		MemberVo dbVo = loginChk2(vo);

		if (idChk == 0) {// ���̵� �������� ���� ��
			Code = "0"; // "���̵� ��й�ȣ ��Ȯ��"
		} else { // ���̵� ������ ��
			// db���� salt + inputPassword
			String salt = dbVo.getSalt();
			byte[] inputPasswordHash = sha3.generateHash(inputPassword, ECBPasswordUtil.hexToBytes(salt));
			// db���� kek => ��ȣȭ
			byte[] decry = ECBPasswordUtil.decrypt(ECBPasswordUtil.hexToBytes(dbVo.getKek()),
					ECBPasswordUtil.hexToBytes(dbVo.getKey()));

			String decryS = ECBPasswordUtil.bytesToHex(decry);

			String hashedPassword = ECBPasswordUtil.bytesToHex(inputPasswordHash);
			// ���̵� �α��� ���� Ƚ�� �ҷ�����
			int failCount = selectFailLogin(vo);
			if (failCount < 5) { // �α��� ���� Ƚ���� 5���� ���� ��(�α��� ������ ��)
				if (decryS.equals(hashedPassword)) {
					// �Է¹��� �н����� + ����� salt ��ȣ = kek��ȭȭ ��ȣ �� ��(���� ��)
					Code = "1";
					updateLoginDtm(vo);// �ֱ� �α��� �ð� ������Ʈ
				} else { // ��ȣ�� �ٸ� ��
					Code = "0";
					updateFailLogin(vo); // �α��� ���� Ƚ�� ����
					int failCount2 = selectFailLogin(vo);
					if (failCount2 == 5) { // �α��� ���� Ƚ���� �� 5�� �Ǹ�
						updateLockDtm(vo); // �α��� ��� �ð� ������Ʈ
					}
				}
			} else { // �α��� ���� Ƚ���� �ʰ� �Ǿ��� ��
				Code = "3";
			}
		}
		return Code;
	}

	//����� ����
	public void deleteUser(MemberVo vo) throws Exception{
		membermapper.deleteUser(vo);
	}
	
}
