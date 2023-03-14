package com.sga.sol.vc.util;


import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.util.encoders.Hex;

import com.sun.xml.internal.ws.handler.HandlerException;

public class SHA3{
	public static String sha3_256(String password) {
		SHA3Digest digest = new SHA3Digest(256); //Bouncy Castle ���̺귯�� �̿��Ͽ� SHA-3-256 ��ü ����
		byte[] data = password.getBytes(); //password byte�� ��ȯ
		digest.update(data, 0 , data.length); //password �ؽð��� ����
		byte[] hash = new byte[digest.getDigestSize()];
		digest.doFinal(hash, 0);
		return Hex.toHexString(hash);
	}

}

