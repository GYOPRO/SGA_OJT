package com.sga.sol.vc.util;

import org.bouncycastle.jcajce.provider.digest.SHA3;

//java8에서는 MessageDigest sha3 라이브러리 지원 x
//bouncycastle 외부 라이브러리를 사용
public class UserSha3_256 {
	
	public static String sha3(String planText) {
		try {
			byte[] messageBytes = planText.getBytes();
			
			//SHA3-256 해시 객체 생성
			SHA3.Digest256 md= new SHA3.Digest256();
			
			//바이트 비밀번호 해시 객체에 업데이트
			md.update(messageBytes);
			
			//해시 값을 바이트 배열로 출력
			byte[] hashBytes = md.digest();
			
			//해시 값을 16진수 문자열로 변환하여 출력
			StringBuilder sb =  new StringBuilder();
			for(byte b : hashBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}