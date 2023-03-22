package com.sga.sol.vc.controller;

import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sga.sol.vc.service.MemberService;
import com.sga.sol.vc.util.ECBPasswordUtil;
import com.sga.sol.vc.util.HashPasswordUtil;
import com.sga.sol.vc.vo.MemberVo;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberservice;
	//로그인 페이지 이동
	@RequestMapping("/")
	public String LoginPage() {
		
		return "member/login";
	}
	

	
	//로그인 정보 확인
		@RequestMapping(value = "/loginChk", method = RequestMethod.POST)
		@ResponseBody
		public HashMap <String, String> loginChk(MemberVo vo) throws Exception{
			
			//return 객체 생성
			HashMap<String, String> result = new HashMap <String,String>();
			
			//BouncyCastleProvider 객체 생성
	        Security.addProvider(new BouncyCastleProvider());
			
	        //seed 설정
	        HashPasswordUtil passwordUtil = new HashPasswordUtil("random_seed");
	        
			String Code ="";
			
				//아이디 존재하는지
				int idChk = memberservice.checkId(vo.getUser_id());
				System.out.println("idCHk"+idChk);
				//입력 패스워드
				String inputPassword = vo.getUser_password(); 
				//db저장 패스워드 정보 불러오기
				MemberVo vvo = memberservice.loginChk2(vo);
				
				if(idChk == 0) {//아이디 존재하지 않을 때
					Code ="0"; //"아이디 비밀번호 재확인"
				}else { //아이디 존재할 때
					//입력 패스워드 => 해시
					String dek = passwordUtil.loginHash(inputPassword, vvo.getSalt());
					//db저장 kek => 복호화
					String decry = ECBPasswordUtil.bytesToHex(ECBPasswordUtil.decrypt(ECBPasswordUtil.hexToBytes(vvo.getKek()), ECBPasswordUtil.hexToBytes(vvo.getKey())));
					System.out.println("Data is equal:" + dek.equals(decry));
					int failCount = memberservice.selectFailLogin(vo);
					if(failCount < 5) { //로그인 실패 횟수가 5보다 적을 때(로그인 가능할 때)
						if(dek.equals(decry)) {
							//입력받은 패스워드 + 저장된 salt 암호 = kek복화화 암호 일 때(같을 떄)
							Code = "1"; 
							memberservice.updateLoginDtm(vo);//최근 로그인 시간 없데이트
						}else { //암호가 다를 때
							Code = "0";
							memberservice.updateFailLogin(vo); // 로그인 실패 횟수 증가
							int failCount2 = memberservice.selectFailLogin(vo);
							if(failCount2 == 5) { //로그인 실패 횟수가 가 5가 되면
								memberservice.updateLockDtm(vo); // 로그인 잠금 시간 업데이트
							}
						}
					}else { //로그인 실패 횟수가 초과 되었을 때
						Code ="3";
					}
				}
				
			System.out.println(Code);
			result.put("Code",Code);
			return result;
		}
	
	//사용자 목록 페이지 이동
	@RequestMapping("/list")
	public String MemberListPage() throws Exception {
		
		return "member/list";
	}
	
	//사용자 목록 불러오기
	@ResponseBody
	@RequestMapping(value = "/getMemberList", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8"})
	public Object getMemberList() throws Exception{
		
		List<MemberVo> memberList = memberservice.selectAllMember();
		
		HashMap<String, Object> list = new HashMap<String, Object>();
		list.put("memberList",memberList);
		return	list;
	}
	
	//회원가입 페이지 이동
	@RequestMapping("/insertMember")
	public String InsertPage() {
		return "member/insert";
	}
	
	//아이디 중복 확인
	@RequestMapping(value ="/checkId")
	@ResponseBody
	public int checkId(@RequestParam("user_id") String user_id) throws Exception{
		int count = memberservice.checkId(user_id);
		return count;
	}
	
	//회원가입
	@PostMapping(value ="/insertMember")
	public String insertMember(MemberVo vo, Model model, HttpServletRequest request) throws Exception{
		
		//BouncyCastleProvider 객체 생성
        Security.addProvider(new BouncyCastleProvider());
		
        //seed 설정
        HashPasswordUtil passwordUtil = new HashPasswordUtil("random_seed");
        
        byte[] saltb = passwordUtil.generateSalt();
        String salt = ECBPasswordUtil.bytesToHex(saltb);
        byte[] hash = passwordUtil.generateHash(vo.getUser_password(), salt);
        byte[] key =  ECBPasswordUtil.getEcbKey(16); //시리얼번호 조합으로 변경해야함
        String kek = ECBPasswordUtil.bytesToHex(ECBPasswordUtil.encrypt(hash, key));
        
        vo.setKek(kek);
        vo.setKey(ECBPasswordUtil.bytesToHex(key));
        vo.setDek(ECBPasswordUtil.bytesToHex(hash));
        vo.setSalt(salt);
        
		
		memberservice.insertMember2(vo);
		return "redirect:/list";
	}
	
	
	//사용자 정보 수정 폼 이동
	@RequestMapping("/updateMember")
	public String updateMemberPage() throws Exception{
		return "member/update";
	}
	
	
	//사용자 정보 수정 폼
	@RequestMapping("/userinfo")
	public String userinfo(String user_id) throws Exception{
		return "member/userinfo";
	}
	
	//사용자 수정 정보 불러오기
	@RequestMapping(value = "/getMember", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8"})
	public Object getMember(@RequestParam("user_id") String user_id) throws Exception{
		
		MemberVo vo = memberservice.selectOneUser(user_id);
		
		System.out.println(vo.getUser_id());
		HashMap<String, Object> user = new HashMap<String, Object>();
		user.put("user",vo);
		System.out.println(user);
		return user;
	}
	

	//사용자 정보 수정
	@RequestMapping("/updateMemberByAdmin")
	public String updateMemberBYAdmin(MemberVo vo) throws Exception{
		memberservice.updateMemberByAdmin(vo);
		return "redirect:/list";
	}
	
	
	
}
