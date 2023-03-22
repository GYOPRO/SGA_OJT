package com.sga.sol.vc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class HashPasswordUtil {
	   	public static final int SALT_LENGTH = 16; // 솔트 길이
	    public static final int NUM_ITERATIONS = 1; // 해시 함수 반복 횟수

	    public MessageDigest sha3;

	    public HashPasswordUtil(String seed) throws NoSuchAlgorithmException {
	        this.sha3 = MessageDigest.getInstance("SHA3-256");
	        this.sha3.update(seed.getBytes());
	    }

	    //sha3 사용 난수 발생기(솔트생성)
	    public byte[] generateSalt() {
	        byte[] salt = new byte[SALT_LENGTH];
	        byte[] seed = sha3.digest();
	        
	        
	        for (int i = 0; i < SALT_LENGTH; i++ ) {
	            seed = sha3.digest(seed);
	            for (int j = 0; j < seed.length && i < SALT_LENGTH; j++, i++) {
	                salt[i] = seed[j];
	            }
	        }

	        return salt;
	    }
	    

	    //password + salt 후 해시 암호화
	    public byte[] generateHash(String password, String salts) throws NoSuchAlgorithmException {
	    	byte[] salt = ECBPasswordUtil.hexToBytes(salts);
	        byte[] inputBytes = new byte[salt.length + password.getBytes().length];// 입력받은 암호 byte
	        System.arraycopy(salt, 0, inputBytes, 0, salt.length); //패시워드 복사
	        System.arraycopy(password.getBytes(), 0, inputBytes, salt.length, password.getBytes().length); //salt복사
	        
	        MessageDigest digest = MessageDigest.getInstance("SHA3-256");
	        byte[] outputBytes = inputBytes; //배열 복사
	        for (int i = 0; i < NUM_ITERATIONS; i++) {
	            outputBytes = digest.digest(outputBytes); //해시 계산
	            //outputBytes에 대한 SHA3-256해시 계산 -> 이 해시값이 다시 outputBytes 변수에 할당되어 다음 반복에 입력
	        }

	        return outputBytes;
	    }
	    
	    //login hash
	    public String loginHash(String password, String salt) throws NoSuchAlgorithmException{
	    	byte[] hash = generateHash(password, salt);
	    	String result = ECBPasswordUtil.bytesToHex(hash);
	    	return result;
	    }
	    
	    //login salt
	    public String loginsalt() {
	    	byte[] salt = generateSalt();
	    	String result = ECBPasswordUtil.bytesToHex(salt);
	    	return result;
	    }


	    
}



