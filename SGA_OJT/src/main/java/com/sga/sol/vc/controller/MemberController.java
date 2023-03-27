package com.sga.sol.vc.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.sga.sol.vc.vo.MemberVo;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberservice;

	// 로그인 페이지 이동
	@RequestMapping("/")
	public String LoginPage() {

		return "member/login";
	}

	// 로그인 정보 확인
	@RequestMapping(value = "/loginChk", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> loginChk(MemberVo vo) throws Exception {

		// return 객체 생성
		HashMap<String, String> result = new HashMap<String, String>();
		String Code = memberservice.loginUser(vo);

		result.put("Code", Code);
		return result;
	}

	// 사용자 목록 페이지 이동
	@RequestMapping("/list")
	public String MemberListPage() throws Exception {
		return "member/list";
	}

	// 사용자 목록 불러오기
	@ResponseBody
	@RequestMapping(value = "/getMemberList", method = { RequestMethod.GET, RequestMethod.POST }, produces = {"application/json;charset=utf-8" })
	public Object getMemberList() throws Exception {
		List<MemberVo> memberList = memberservice.selectAllMember();
		HashMap<String, Object> list = new HashMap<String, Object>();
		list.put("memberList", memberList);
		return list;
	}

	// 회원가입 페이지 이동
	@RequestMapping("/insertMember")
	public String InsertPage() {
		return "member/insert";
	}

	// 아이디 중복 확인
	@RequestMapping(value = "/checkId")
	@ResponseBody
	public int checkId(@RequestParam("userId") String userId) throws Exception {
		int count = memberservice.checkId(userId);
		return count;
	}

	// 회원가입
	@PostMapping(value = "/insertMember")
	public String insertMember(MemberVo vo, Model model, HttpServletRequest request) throws Exception {
		// encryUser로 user 정보 암호화 후 MemberVo타입으로 리턴
		memberservice.insertMember2(memberservice.encryptUser(vo));
		return "redirect:/list";
	}

	// 사용자 정보 수정 폼 이동
	@RequestMapping("/updateMember")
	public String updateMemberPage() throws Exception {
		return "member/update";
	}

	// 사용자 정보 수정 폼
	@RequestMapping("/userinfo")
	public String userinfo() throws Exception {
		return "member/userinfo";
	}

	// 사용자 수정 정보 불러오기
	@RequestMapping(value = "/getMember", method = { RequestMethod.GET, RequestMethod.POST }, produces = {"application/json;charset=utf-8" })
	@ResponseBody
	public MemberVo getMember(@RequestParam("userId") String userId) throws Exception {

		MemberVo vo = memberservice.selectOneUser(userId);

		return vo;
	}

	// 사용자 정보 수정
	@RequestMapping("/updateMemberByAdmin")
	public String updateMemberBYAdmin(@RequestParam("userId") String userId) throws Exception {
		memberservice.updateMemberByAdmin(userId);
		return "redirect:/list";
	}
	
	//사용자 삭제
	@RequestMapping("/deleteUser")
	public String deleteUser(MemberVo vo) throws Exception{
		memberservice.deleteUser(vo);
		return "redirect:/list";
	}

}
