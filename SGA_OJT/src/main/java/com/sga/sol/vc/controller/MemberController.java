package com.sga.sol.vc.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.util.encoders.Hex;
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

import com.sga.sol.vc.serviceimpl.MemberServiceImpl;
import com.sga.sol.vc.util.SHA3_DRBG;
import com.sga.sol.vc.vo.MemberVo;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.jmx.snmp.Timestamp;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberServiceImpl memberservice;
	//로그인 페이지 이동
	@RequestMapping("/")
	public String LoginPage() {
		return "member/login";
	}
	
	//로그인 정보 확인
	@RequestMapping(value = "/loginChk", method = RequestMethod.POST)
	@ResponseBody
	public HashMap <String, String> loginChk(MemberVo vo) throws Exception{

		
		
		
		HashMap<String, String> result = new HashMap <String,String>();
		
		String Code ="";

		
			int loginRst = memberservice.loginChk(vo);
			
			if(loginRst > 0) { // 로그인 성공시
				int failCount = memberservice.selectFailLogin(vo);
				if(failCount < 5) {
					Code = "1";
					memberservice.updateLoginDtm(vo);//로그인 시간 업데이트
				}else {
					Code ="3";
				}
			}else { //로그인 실패시
				int idChk = memberservice.checkId(vo.getUser_id());
				if(idChk == 0) {
					Code = "0";
				}else {
					int failCount = memberservice.selectFailLogin(vo);
					if(failCount < 5) {
						Code = "0";
						memberservice.updateFailLogin(vo); // 실패 횟수 증가
						int failCount2 = memberservice.selectFailLogin(vo);
						if(failCount2 == 5) {
							memberservice.updateLockDtm(vo);
						}
					}else { 
						Code ="3";
					}
				}
				
			}
	
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
		
		//암호확인
		System.out.println("입력된 암호 : " + vo.getUser_password());
		
		byte[] seed = vo.getUser_password().getBytes();
		byte[] random = SHA3_DRBG.sha3_drbg(seed, 32);
		String password = Hex.toHexString(random);
		vo.setDek(password);
		System.out.println("암호화 암호 : " + vo.getDek());
		
		memberservice.insertMember(vo);
		return "redirect:/list";
	}
	
	//사용자 정보 수정 페이지 이동
	@RequestMapping("/updateMember")
	public String updateMember() throws Exception{
		return "member/update";
	}
	
	//사용자 정보 수정 페이지 이동test
	@RequestMapping("/updateMembertest")
	public String updateMembertest() throws Exception{
		return "member/updatetest";
	}

	//사용자 정보 수정
	@RequestMapping("/updateMemberByAdmin")
	public String updateMemberBYAdmin(MemberVo vo) throws Exception{
		memberservice.updateMemberByAdmin(vo);
		return "redirect:/list";
	}
	
	
	
}
