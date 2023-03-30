package com.sga.sol.vc.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.function.Supplier;



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

		// KISA�ҽ��ڵ� ��� ��ȣȭ => 16����Ʈ ������ + 16����Ʈ Ű
		byte[] pbCipher1 = KISA_SEED_ECB.SEED_ECB_Encrypt(key, leftBlock, 0, 16);
		byte[] pbCipher2 = KISA_SEED_ECB.SEED_ECB_Encrypt(key, rightBlock, 0, 16);

		
		// ���� ��ȣȭ ��� ���� 64����Ʈ �迭
		byte[] encryptedData = new byte[BLOCK_SIZE * 4];
		System.arraycopy(pbCipher1, 0, encryptedData, 0, BLOCK_SIZE*2);
		System.arraycopy(pbCipher2, 0, encryptedData, BLOCK_SIZE*2, BLOCK_SIZE*2);
		
		//64����Ʈ ��ȣȭ ������ ����
		return encryptedData;
	}

	/**
	 * SEED ��ȣ �˰����� ����Ͽ�16����Ʈ Ű�� 32����Ʈ ������ ����� ��ȣȭ�մϴ�.
	 *
	 * @param data ��ȣȭ�� 32����Ʈ ������ ��� - ��ȣȭ �� ������
	 * @param key  16����Ʈ ��ȣȭ Ű -
	 * @return ����Ʈ �迭�� ǥ���� ��ȣȭ�� ������ ���
	 * @throws Exception ��ȣȭ ���� ��
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {

		
		// 64����Ʈ ������ ����� �� ���� 32����Ʈ ������� �����մϴ�.
		byte[] leftBlock = Arrays.copyOfRange(data, 0, BLOCK_SIZE*2);
	    byte[] rightBlock = Arrays.copyOfRange(data, BLOCK_SIZE*2, BLOCK_SIZE * 4);
	    
	    //32����Ʈ ��ȣȭ �迭 + 16����Ʈ Ű  ��ȣȭ
	    byte[] pbPlain1 = KISA_SEED_ECB.SEED_ECB_Decrypt(key,leftBlock, 0, 32);
		byte[] pbPlain2 = KISA_SEED_ECB.SEED_ECB_Decrypt(key,rightBlock, 0, 32);
	    
		
		// ���� ��ȣȭ ��� ����
		byte[] decryptedData = new byte[BLOCK_SIZE * 2];
		System.arraycopy(pbPlain1, 0, decryptedData, 0, BLOCK_SIZE);
		System.arraycopy(pbPlain2, 0, decryptedData, BLOCK_SIZE, BLOCK_SIZE);
;
		// 32����Ʈ �迭 ��ȯ
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


