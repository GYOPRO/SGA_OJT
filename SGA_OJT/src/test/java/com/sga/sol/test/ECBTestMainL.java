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
		// bouncyCastleprovide��ü ����
		Security.addProvider(new BouncyCastleProvider());

		// seed ����
		HashPasswordUtil hashPasswordUtil = new HashPasswordUtil("entropy1");
		// ���� �Է� �н����� ����
		String password = "perStr2ing";
		// ��Ʈ ����
		byte[] salt = hashPasswordUtil.generateSalt();
		// �ؽ� ��ȣȭ ��й�ȣ byte�迭 ����
		byte[] data = hashPasswordUtil.generateHash(password, salt);
		
		// ������ Ű��(����) 16����Ʈ
		byte[] key = ECBPasswordUtil.getEcbKey(16);

		byte[] encryptedData = ECBPasswordUtil.encrypt(data, key);
		byte[] decryptedData = ECBPasswordUtil.decrypt(encryptedData, key);
		System.out.println("16���� key:  " +ECBPasswordUtil.bytesToHex(key));
		System.out.println("�ؽþ�ȣ:    " + ECBPasswordUtil.bytesToHex(data));
		System.out.println("ecb��ȣȭ:   " + ECBPasswordUtil.bytesToHex(encryptedData));
		System.out.println("ecb��ȣȭ:   " + ECBPasswordUtil.bytesToHex(decryptedData));
		// ������ Ȯ��
		System.out.println("Data is equal:    " + Arrays.equals(data, decryptedData));

	}

}
