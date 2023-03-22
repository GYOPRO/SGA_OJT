package com.sga.sol.vc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class HashPasswordUtil {
	   	public static final int SALT_LENGTH = 16; // ��Ʈ ����
	    public static final int NUM_ITERATIONS = 1; // �ؽ� �Լ� �ݺ� Ƚ��

	    public MessageDigest sha3;

	    public HashPasswordUtil(String seed) throws NoSuchAlgorithmException {
	        this.sha3 = MessageDigest.getInstance("SHA3-256");
	        this.sha3.update(seed.getBytes());
	    }

	    //sha3 ��� ���� �߻���(��Ʈ����)
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
	    

	    //password + salt �� �ؽ� ��ȣȭ
	    public byte[] generateHash(String password, String salts) throws NoSuchAlgorithmException {
	    	byte[] salt = ECBPasswordUtil.hexToBytes(salts);
	        byte[] inputBytes = new byte[salt.length + password.getBytes().length];// �Է¹��� ��ȣ byte
	        System.arraycopy(salt, 0, inputBytes, 0, salt.length); //�нÿ��� ����
	        System.arraycopy(password.getBytes(), 0, inputBytes, salt.length, password.getBytes().length); //salt����
	        
	        MessageDigest digest = MessageDigest.getInstance("SHA3-256");
	        byte[] outputBytes = inputBytes; //�迭 ����
	        for (int i = 0; i < NUM_ITERATIONS; i++) {
	            outputBytes = digest.digest(outputBytes); //�ؽ� ���
	            //outputBytes�� ���� SHA3-256�ؽ� ��� -> �� �ؽð��� �ٽ� outputBytes ������ �Ҵ�Ǿ� ���� �ݺ��� �Է�
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



