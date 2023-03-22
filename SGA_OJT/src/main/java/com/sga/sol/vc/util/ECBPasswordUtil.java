package com.sga.sol.vc.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.function.Supplier;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class ECBPasswordUtil {

	private static final int BLOCK_SIZE = 16; // 128��Ʈ

	/**
	 * 32����Ʈ ������ 16����Ʈ Ű�� ��ȣȭ
	 *
	 * @param data 64����Ʈ �� - hash�� �н�����
	 * @param key  32����Ʈ Ű - ���� ����
	 * @return ��ȣȭ �� ����Ʈ �迭
	 * @throws ��ȣȭ ���н�
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 32����Ʈ ������ ��� 16����Ʈ�� ������
		byte[] leftBlock = Arrays.copyOfRange(data, 0, BLOCK_SIZE);
		byte[] rightBlock = Arrays.copyOfRange(data, BLOCK_SIZE, BLOCK_SIZE * 2);

		// Ű ScretKeySpec ����ؼ� ��ȣ Ű �����
		SecretKeySpec keySpec = new SecretKeySpec(key, 0, BLOCK_SIZE, "SEED");

		// SEED/ECB �˰��� ����
		Cipher cipher = Cipher.getInstance("SEED/ECB/NoPadding");
		// ��ȣȭ , Ű �ʱ�ȭ
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);

		// ���� 16����Ʈ ��� ���ε��� ��ȣȭ
		byte[] encryptedLeft = cipher.doFinal(leftBlock);
		byte[] encryptedRight = cipher.doFinal(rightBlock);
		/*
		System.out.println("��ȣȭ 1��:     "+bytesToHex(encryptedLeft));
		System.out.println("��ȣȭ 2��:     "+bytesToHex(encryptedRight));
		*/
		// ���� ��ȣȭ ��� ����
		byte[] encryptedData = new byte[BLOCK_SIZE * 2];
		System.arraycopy(encryptedLeft, 0, encryptedData, 0, BLOCK_SIZE);
		System.arraycopy(encryptedRight, 0, encryptedData, BLOCK_SIZE, BLOCK_SIZE);

		return encryptedData;
	}

	/**
	 * SEED ��ȣ �˰����� ����Ͽ� 16����Ʈ Ű�� 32����Ʈ ������ ����� ��ȣȭ�մϴ�.
	 *
	 * @param data ��ȣȭ�� 32����Ʈ ������ ��� - ��ȣȭ �� ������
	 * @param key  16����Ʈ ��ȣȭ Ű -
	 * @return ����Ʈ �迭�� ǥ���� ��ȣȭ�� ������ ���
	 * @throws Exception ��ȣȭ ���� ��
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 32����Ʈ ������ ����� �� ���� 16����Ʈ ������� �����մϴ�.
		byte[] leftBlock = Arrays.copyOfRange(data, 0, BLOCK_SIZE);
	    byte[] rightBlock = Arrays.copyOfRange(data, BLOCK_SIZE, BLOCK_SIZE * 2);

		// Ű ScretKeySpec ����ؼ� ��ȣ Ű �����
		SecretKeySpec keySpec = new SecretKeySpec(key, 0, BLOCK_SIZE, "SEED");

		// SEED/ECB �˰��� ����
		Cipher cipher = Cipher.getInstance("SEED/ECB/NoPadding");
		// ��ȣȭ , Ű�� �ʱ�ȭ
		cipher.init(Cipher.DECRYPT_MODE, keySpec);

		// ���� 16����Ʈ ��� ���ε��� ��ȣȭ
		byte[] decryptedLeft = cipher.doFinal(leftBlock);
		byte[] decryptedRight = cipher.doFinal(rightBlock);
		
		/*
		System.out.println("��ȣȭ 1��:     "+bytesToHex(decryptedLeft));
		System.out.println("��ȣȭ 2��:     "+bytesToHex(decryptedRight));
		 */
		
		// ���� ��ȣȭ ��� ����
		byte[] decryptedData = new byte[BLOCK_SIZE * 2];
		System.arraycopy(decryptedLeft, 0, decryptedData, 0, BLOCK_SIZE);
		System.arraycopy(decryptedRight, 0, decryptedData, BLOCK_SIZE, BLOCK_SIZE);

		// ����Ʈ �迭 ��ȯ
		return decryptedData;
	}
	
	 //����Ʈ �迭 => 16������ ��ȯ
    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    
    //64�ڸ� 16���� => 32����Ʈ �迭�� ��ȯ
    //16���� => ����Ʈ �迭��
    public static byte[] hexToBytes(String hexString) {
	    int len = hexString.length();
	    byte[] byteArray = new byte[len / 2];

	    for (int i = 0; i < len; i += 2) {
	        byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
	                                     + Character.digit(hexString.charAt(i+1), 16));
	    }

	    return byteArray;
	}
    
    //16����Ʈ ���� �迭 �����
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


