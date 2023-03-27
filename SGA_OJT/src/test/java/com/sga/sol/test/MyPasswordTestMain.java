package com.sga.sol.test;

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sga.sol.vc.util.ECBPasswordUtil;
import com.sga.sol.vc.util.HashPasswordUtil;

public class PasswordTestMain {

    public static void main(String[] args) throws NoSuchAlgorithmException {

    	Security.addProvider(new BouncyCastleProvider());
    	
        String seed = "testSeed1sdad1";
        HashPasswordUtil passwordUtil = new HashPasswordUtil(seed);

        // generateSalt() 테스트
        byte[] salt1 = passwordUtil.generateSalt();
        byte[] salt2 = passwordUtil.generateSalt();
        System.out.println("salt1: " + Arrays.toString(salt1));
        System.out.println("salt2: " + Arrays.toString(salt2));

        // bytesToHex() 테스트
        byte[] testBytes = {(byte) 0x12, (byte) 0x34, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};
        String hexString = ECBPasswordUtil.bytesToHex(testBytes);
        System.out.println("hexString: " + hexString);

        // generateHash() 테스트
        String password = "testPassword";
        byte[] salt = passwordUtil.generateSalt();
        byte[] hash1 = passwordUtil.generateHash(password, salt);
        byte[] hash2 = passwordUtil.generateHash(password, salt);
        System.out.println(Arrays.toString(hash1));
        System.out.println("hash1: " + ECBPasswordUtil.bytesToHex(hash1));
        System.out.println("hash2: " + ECBPasswordUtil.bytesToHex(hash2));
    }
    

}

