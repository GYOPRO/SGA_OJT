package com.sga.sol.test;

import java.security.Security;

import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

public class HashDRBGTest {
	private static final int BLOCK_SIZE = 256;
	
	public static void main(String[] args) throws Exception {
    
		Security.addProvider(new BouncyCastleProvider());
    

        byte[] entropy = "This is an entropy string".getBytes();
        byte[] personalizationString = "This is a personalization string".getBytes();
        byte[] nonce = "This is a nonce".getBytes();

        HashDRBG hashDRBG = new HashDRBG();

        byte[] randomBits = hashDRBG.generateRandomBits(128);
        System.out.println("Random bits: " + DatatypeConverter.printHexBinary(randomBits));
    
}
}