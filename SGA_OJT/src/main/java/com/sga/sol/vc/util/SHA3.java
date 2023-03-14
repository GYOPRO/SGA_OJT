package com.sga.sol.vc.util;


import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.util.encoders.Hex;

import com.sun.xml.internal.ws.handler.HandlerException;

public class SHA3{
	public static String sha3_256(String password) {
		SHA3Digest digest = new SHA3Digest(256); //Bouncy Castle 라이브러리 이용하여 SHA-3-256 객체 생성
		byte[] data = password.getBytes(); //password byte로 변환
		digest.update(data, 0 , data.length); //password 해시값을 갱신
		byte[] hash = new byte[digest.getDigestSize()];
		digest.doFinal(hash, 0);
		return Hex.toHexString(hash);
	}

}

