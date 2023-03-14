package com.sga.sol.vc.util;

import org.bouncycastle.jcajce.provider.digest.SHA3;

//java8������ MessageDigest sha3 ���̺귯�� ���� x
//bouncycastle �ܺ� ���̺귯���� ���
public class UserSha3_256 {
	
	public static String sha3(String planText) {
		try {
			byte[] messageBytes = planText.getBytes();
			
			//SHA3-256 �ؽ� ��ü ����
			SHA3.Digest256 md= new SHA3.Digest256();
			
			//����Ʈ ��й�ȣ �ؽ� ��ü�� ������Ʈ
			md.update(messageBytes);
			
			//�ؽ� ���� ����Ʈ �迭�� ���
			byte[] hashBytes = md.digest();
			
			//�ؽ� ���� 16���� ���ڿ��� ��ȯ�Ͽ� ���
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