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

	// 로그인 실패 횟수 조회
	@Override
	public int selectFailLogin(MemberVo vo) throws Exception {
		return membermapper.selectFailLogin(vo);
	}

	// 로그인 시 최근 로그인 시간 변경
	@Override
	public void updateLoginDtm(MemberVo vo) throws Exception {
		membermapper.updateLoginDtm(vo);
	}

	// 로그인 시 최근 로그인 시간 변경
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
	 * 회원가입 시 사용자 패스워드 암호화 salt(32) + inputPassword = dek(32) dek(32) + key(32) =
	 * kek(64)
	 */
	@Override
	public MemberVo encryptUser(MemberVo vo) throws Exception {
		//sha3 해시함수 객체 생성
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
		// 입력 아이디
		String inputId = vo.getUserId();
		// 입력 패스워드
		String inputPassword = vo.getUserPassword();

		sha3 sha3 = new sha3();

		String Code = "";

		// 아이디 존재하는지
		int idChk = checkId(inputId);

		// db저장 패스워드 정보 불러오기
		MemberVo dbVo = loginChk2(vo);

		if (idChk == 0) {// 아이디 존재하지 않을 때
			Code = "0"; // "아이디 비밀번호 재확인"
		} else { // 아이디 존재할 때
			// db정보 salt + inputPassword
			String salt = dbVo.getSalt();
			byte[] inputPasswordHash = sha3.generateHash(inputPassword, ECBPasswordUtil.hexToBytes(salt));
			// db저장 kek => 복호화
			byte[] decry = ECBPasswordUtil.decrypt(ECBPasswordUtil.hexToBytes(dbVo.getKek()),
					ECBPasswordUtil.hexToBytes(dbVo.getKey()));

			String decryS = ECBPasswordUtil.bytesToHex(decry);

			String hashedPassword = ECBPasswordUtil.bytesToHex(inputPasswordHash);
			// 아이디 로그인 실패 횟수 불러오기
			int failCount = selectFailLogin(vo);
			if (failCount < 5) { // 로그인 실패 횟수가 5보다 적을 때(로그인 가능할 때)
				if (decryS.equals(hashedPassword)) {
					// 입력받은 패스워드 + 저장된 salt 암호 = kek복화화 암호 일 때(같을 떄)
					Code = "1";
					updateLoginDtm(vo);// 최근 로그인 시간 없데이트
				} else { // 암호가 다를 때
					Code = "0";
					updateFailLogin(vo); // 로그인 실패 횟수 증가
					int failCount2 = selectFailLogin(vo);
					if (failCount2 == 5) { // 로그인 실패 횟수가 가 5가 되면
						updateLockDtm(vo); // 로그인 잠금 시간 업데이트
					}
				}
			} else { // 로그인 실패 횟수가 초과 되었을 때
				Code = "3";
			}
		}
		return Code;
	}

	//사용자 삭제
	public void deleteUser(MemberVo vo) throws Exception{
		membermapper.deleteUser(vo);
	}
	
}
