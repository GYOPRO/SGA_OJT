package com.sga.sol.vc.util;

import org.bouncycastle.crypto.digests.SHA3Digest;

public class SHA3_DRBG {
	public static byte[] sha3_drbg(byte[] seed, int n){
		byte[] state = seed;
		byte[] result = new byte[n];
		int result_offset = 0;
		while(n>0) {
			byte[] digest = new byte[32];
			SHA3Digest sha3 = new SHA3Digest(256);
			sha3.update(state, 0 , state.length);
			sha3.doFinal(digest, 0);
			for(int i = 0 ; i < 32 && n > 0 ; i++) {
				result[result_offset++] = digest[i];
				n--;
			}
			state = digest;
		}
		return result;
	}
}
