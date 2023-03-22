package com.sga.sol.test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sga.sol.vc.util.ECBPasswordUtil;
import com.sga.sol.vc.util.HashPasswordUtil;

public class EcbTestMain {

	public static void main(String[] args) throws Exception {
		// bouncyCastleprovide객체 생성
		Security.addProvider(new BouncyCastleProvider());

		// seed 설정
		HashPasswordUtil hashPasswordUtil = new HashPasswordUtil("entropy1");
		// 임의 입력 패스워드 설정
		String password = "perStr2ing";
		// 솔트 생성
		byte[] salt = hashPasswordUtil.generateSalt();
		// 해시 암호화 비밀번호 byte배열 생성
		byte[] data = hashPasswordUtil.generateHash(password, salt);
		
		// 임의의 키값(변경) 16바이트
		byte[] key = ECBPasswordUtil.getEcbKey(16);

		byte[] encryptedData = ECBPasswordUtil.encrypt(data, key);
		byte[] decryptedData = ECBPasswordUtil.decrypt(encryptedData, key);
		System.out.println("16진수 key:  " +ECBPasswordUtil.bytesToHex(key));
		System.out.println("해시암호:    " + ECBPasswordUtil.bytesToHex(data));
		System.out.println("ecb암호화:   " + ECBPasswordUtil.bytesToHex(encryptedData));
		System.out.println("ecb복호화:   " + ECBPasswordUtil.bytesToHex(decryptedData));
		// 같은지 확인
		System.out.println("Data is equal:    " + Arrays.equals(data, decryptedData));

	}

}
