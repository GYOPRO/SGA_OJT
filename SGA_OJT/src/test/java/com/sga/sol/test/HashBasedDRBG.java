package com.sga.sol.test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class HashBasedDRBG {
    private static final int SALT_LENGTH = 16;
    private static final int OUTPUT_BYTE_LENGTH = 32;
    private static final int SECURITY_STRENGTH = 256;

    private final MessageDigest sha3;
    private byte[] state;
    private byte[] v;

    public HashBasedDRBG(byte[] seed) throws NoSuchAlgorithmException {
        sha3 = MessageDigest.getInstance("SHA3-" + SECURITY_STRENGTH);
        byte[] entropyInput = Arrays.copyOf(seed, SALT_LENGTH);
        state = sha3.digest(entropyInput);
        v = new byte[OUTPUT_BYTE_LENGTH];
    }

    public byte[] generate() {
        if (v == null) {
            throw new IllegalStateException("Reseed required");
        }

        byte[] output = new byte[OUTPUT_BYTE_LENGTH];
        byte[] seedMaterial = v;

        sha3.update(state);
        sha3.update((byte) 0x00);
        sha3.update(seedMaterial);
        v = sha3.digest();

        sha3.update(state);
        sha3.update((byte) 0x01);
        sha3.update(v);
        v = sha3.digest();

        System.arraycopy(v, 0, output, 0, OUTPUT_BYTE_LENGTH);

        sha3.update(state);
        sha3.update((byte) 0x02);
        v = sha3.digest();

        state = sha3.digest();

        return output;
    }

    public void reseed(byte[] seed) throws NoSuchAlgorithmException {
        byte[] entropyInput = Arrays.copyOf(seed, SALT_LENGTH);

        sha3.update(state);
        sha3.update((byte) 0x01);
        sha3.update(v);
        sha3.update(entropyInput);
        state = sha3.digest();

        v = null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
    	Security.addProvider(new BouncyCastleProvider());
    	
        byte[] seed = "entropy1".getBytes(StandardCharsets.UTF_8);
        HashBasedDRBG drbg = new HashBasedDRBG(seed);

        byte[] output1 = drbg.generate();
        System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(output1));

        byte[] output2 = drbg.generate();
        System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(output2));

        drbg.reseed("entropy2".getBytes(StandardCharsets.UTF_8));

        byte[] output3 = drbg.generate();
        System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(output3));
    }
}






