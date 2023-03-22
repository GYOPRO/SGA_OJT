package com.sga.sol.vc.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.function.Supplier;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class ECBPasswordUtil {

	private static final int BLOCK_SIZE = 16; // 128비트

	/**
	 * 32바이트 데이터 16바이트 키로 암호화
	 *
	 * @param data 64바이트 평문 - hash된 패스워드
	 * @param key  32바이트 키 - 임의 지정
	 * @return 암호화 된 바이트 배열
	 * @throws 암호화 실패시
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 32바이트 데이터 블록 16바이트로 나누기
		byte[] leftBlock = Arrays.copyOfRange(data, 0, BLOCK_SIZE);
		byte[] rightBlock = Arrays.copyOfRange(data, BLOCK_SIZE, BLOCK_SIZE * 2);

		// 키 ScretKeySpec 사용해서 암호 키 만들기
		SecretKeySpec keySpec = new SecretKeySpec(key, 0, BLOCK_SIZE, "SEED");

		// SEED/ECB 알고리즘 생성
		Cipher cipher = Cipher.getInstance("SEED/ECB/NoPadding");
		// 암호화 , 키 초기화
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);

		// 나눈 16바이트 블록 따로따로 암호화
		byte[] encryptedLeft = cipher.doFinal(leftBlock);
		byte[] encryptedRight = cipher.doFinal(rightBlock);
		/*
		System.out.println("암호화 1번:     "+bytesToHex(encryptedLeft));
		System.out.println("암호화 2번:     "+bytesToHex(encryptedRight));
		*/
		// 나눈 암호화 블록 결합
		byte[] encryptedData = new byte[BLOCK_SIZE * 2];
		System.arraycopy(encryptedLeft, 0, encryptedData, 0, BLOCK_SIZE);
		System.arraycopy(encryptedRight, 0, encryptedData, BLOCK_SIZE, BLOCK_SIZE);

		return encryptedData;
	}

	/**
	 * SEED 암호 알고리즘을 사용하여 16바이트 키로 32바이트 데이터 블록을 복호화합니다.
	 *
	 * @param data 복호화할 32바이트 데이터 블록 - 암호화 된 데이터
	 * @param key  16바이트 복호화 키 -
	 * @return 바이트 배열로 표현된 복호화된 데이터 블록
	 * @throws Exception 복호화 실패 시
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 32바이트 데이터 블록을 두 개의 16바이트 블록으로 분할합니다.
		byte[] leftBlock = Arrays.copyOfRange(data, 0, BLOCK_SIZE);
	    byte[] rightBlock = Arrays.copyOfRange(data, BLOCK_SIZE, BLOCK_SIZE * 2);

		// 키 ScretKeySpec 사용해서 암호 키 만들기
		SecretKeySpec keySpec = new SecretKeySpec(key, 0, BLOCK_SIZE, "SEED");

		// SEED/ECB 알고리즘 생성
		Cipher cipher = Cipher.getInstance("SEED/ECB/NoPadding");
		// 복호화 , 키로 초기화
		cipher.init(Cipher.DECRYPT_MODE, keySpec);

		// 나눈 16바이트 블록 따로따로 복호화
		byte[] decryptedLeft = cipher.doFinal(leftBlock);
		byte[] decryptedRight = cipher.doFinal(rightBlock);
		
		/*
		System.out.println("복호화 1번:     "+bytesToHex(decryptedLeft));
		System.out.println("복호화 2번:     "+bytesToHex(decryptedRight));
		 */
		
		// 나눈 암호화 블록 결합
		byte[] decryptedData = new byte[BLOCK_SIZE * 2];
		System.arraycopy(decryptedLeft, 0, decryptedData, 0, BLOCK_SIZE);
		System.arraycopy(decryptedRight, 0, decryptedData, BLOCK_SIZE, BLOCK_SIZE);

		// 바이트 배열 반환
		return decryptedData;
	}
	
	 //바이트 배열 => 16진수로 변환
    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    
    //64자리 16진수 => 32바이트 배열로 변환
    //16진수 => 바이트 배열로
    public static byte[] hexToBytes(String hexString) {
	    int len = hexString.length();
	    byte[] byteArray = new byte[len / 2];

	    for (int i = 0; i < len; i += 2) {
	        byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
	                                     + Character.digit(hexString.charAt(i+1), 16));
	    }

	    return byteArray;
	}
    
    //16바이트 랜덤 배열 만들기
    public static byte[] getEcbKey(int length) {
        Supplier<byte[]> randomByteArraySupplier = () -> {
            SecureRandom random = new SecureRandom();
            byte[] byteArray = new byte[length];
            random.nextBytes(byteArray);
            return byteArray;
        };
        return randomByteArraySupplier.get();
    }

}


