package com.sga.sol.test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class DeterministicRandomNumberGenerator {
    private static final int SALT_LENGTH = 16;
    private static final int OUTPUT_BYTE_LENGTH = 64;
    private static final int SHA3_256_OUTPUT_BYTE_LENGTH = 32;

    private final MessageDigest sha3;
    private byte[] state;

    public DeterministicRandomNumberGenerator(byte[] seed) throws NoSuchAlgorithmException {
        this.sha3 = MessageDigest.getInstance("SHA3-256");
        byte[] salt = generateSalt();
        byte[] concatenatedSeed = concatenate(seed, salt);
        this.state = sha3.digest(concatenatedSeed);
    }

    private byte[] generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private byte[] concatenate(byte[] a, byte[] b) {
        byte[] result = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public byte[] generate() {
        byte[] output = new byte[OUTPUT_BYTE_LENGTH];
        while (output.length > 0) {
            byte[] digest = sha3.digest(state);
            if (output.length >= SHA3_256_OUTPUT_BYTE_LENGTH) {
                System.arraycopy(digest, 0, output, 0, SHA3_256_OUTPUT_BYTE_LENGTH);
                output = Arrays.copyOfRange(output, SHA3_256_OUTPUT_BYTE_LENGTH, output.length);
            } else {
                System.arraycopy(digest, 0, output, 0, output.length);
                output = new byte[0];
            }
            state = sha3.digest(state);
        }
        return output;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
    	Security.addProvider(new BouncyCastleProvider());
    	
        String seed = "entropy1";
        DeterministicRandomNumberGenerator drbg = new DeterministicRandomNumberGenerator(seed.getBytes(StandardCharsets.UTF_8));
        byte[] result = drbg.generate();
        System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(result));
        System.out.println("asd");
    }
}

