package com.sga.sol.vc.util;

import java.io.IOException;

import org.bouncycastle.util.encoders.Hex;

public class sha3test {

	public static void main(String[] args) throws IOException{
		String password = "entropy1";
		byte[] seed = "B94100EC205146FF76D65C1091E457ECCFED32B69F3571F2438705DCD42770FE".getBytes();
		byte[] random = SHA3_DRBG.sha3_drbg(seed, 512);
		String sha3 = SHA3.sha3_256(password);

		System.out.println("Random: " +Hex.toHexString(random));
		System.out.println("sha3:" +sha3);

	}
	


}
