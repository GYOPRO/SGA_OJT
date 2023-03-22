package com.sga.sol.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashDRBG {
    private static final int SECURITY_STRENGTH = 256; // 보안 강도
    private static final int BLOCK_SIZE = 136; // 블록 크기
    private static final int OUT_LEN = 256; // 출력 길이
    private static final int MAX_BITS_REQUESTED = 7500; // 최대 요청 비트 수

    private byte[] seedKey;
    private byte[] V;
    private byte[] C;
    private int reseedCounter;
    private MessageDigest md;

    public HashDRBG() {
        try {
            // SHA3-256 알고리즘을 사용하기 위해 MessageDigest를 초기화합니다.
            md = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Seed Key를 생성합니다.
        SecureRandom secureRandom = new SecureRandom();
        seedKey = new byte[BLOCK_SIZE / 8];
        secureRandom.nextBytes(seedKey);

        // V와 C 값을 초기화합니다.
        V = new byte[BLOCK_SIZE / 8];
        C = new byte[BLOCK_SIZE / 8];
        reseedCounter = 1;
    }

    public byte[] generateRandomBits(int bitsRequested) {
        // 최대 요청 비트 수를 초과하는 경우 예외를 발생시킵니다.
        if (bitsRequested > MAX_BITS_REQUESTED) {
            throw new IllegalArgumentException("Requested bit length is greater than the maximum value allowed.");
        }

        // 요청한 비트 수를 바이트 수로 변환합니다.
        int bytesRequested = (bitsRequested + 7) / 8;

        // 예측 불가능한 값 생성 단계
        byte[] data = generateSeedMaterial(bytesRequested);
        byte[] randomBits = hashgen(OUT_LEN, data);

        // 다음 시드 값 생성 단계
        data = generateSeedMaterial(0);
        update(data);

        return randomBits;
    }

    //이전 시드 값과 생성된 무작위 비트열을 사용하여 다음 시드값 생성
    private byte[] generateSeedMaterial(int bytesRequested) {
        // 이전 시드 값과 생성된 무작위 비트열을 사용하여 다음 시드 값을 생성합니다.
        byte[] nextSeed = getNextSeed();

        // 다음 시드 값을 사용하여 SHA3-256 알고리즘을 실행하여 무작위 비트열을 생성합니다.
        byte[] data = new byte[nextSeed.length + 4];
        System.arraycopy(nextSeed, 0, data, 0, nextSeed.length);
        data[nextSeed.length] = (byte) ((bytesRequested >> 24) & 0xff);
        data[nextSeed.length + 1] = (byte) ((bytesRequested >> 16) & 0xff);
        data[nextSeed.length + 2] = (byte) ((bytesRequested >> 8) & 0xff);
        data[nextSeed.length + 3] = (byte) (bytesRequested & 0xff);

        return hashgen(BLOCK_SIZE, data);
    }

    //이전 시드 값과 V값을 사용하여 다음 시드값 생성
    private byte[] getNextSeed() {
        // 이전 시드 값과 V 값을 사용
    	 //하여 다음 시드 값을 생성합니다.
        byte[] seed = new byte[V.length + seedKey.length];
        System.arraycopy(V, 0, seed, 0, V.length);
        System.arraycopy(seedKey, 0, seed, V.length, seedKey.length);
        byte[] nextSeed = hashgen(BLOCK_SIZE, seed);
        return nextSeed;
    }

    //V와 C를 사용하여 V값 생성, C값 업데이트
    private void update(byte[] data) {
        // 현재 V 값과 C 값을 사용하여 다음 V 값을 생성합니다.
        byte[] seedMaterial = new byte[data.length + V.length + 1];
        System.arraycopy(V, 0, seedMaterial, 0, V.length);
        System.arraycopy(data, 0, seedMaterial, V.length, data.length);
        seedMaterial[seedMaterial.length - 1] = (byte) reseedCounter;

        byte[] newV = hashgen(BLOCK_SIZE, seedMaterial);
        byte[] newC = hashgen(BLOCK_SIZE, concatenate(newV, V, seedKey, data));

        V = newV;
        C = newC;
        reseedCounter += 1;
    }

    //주어진 데이터와 C값을 사용하여 무작위 비트열 생성
    private byte[] hashgen(int outLen, byte[] data) {
        int iterations = (int) Math.ceil((double) outLen / (double) BLOCK_SIZE);
        byte[] result = new byte[iterations * BLOCK_SIZE];
        byte[] input = concatenate(data, C);

        for (int i = 0; i < iterations; i++) {
            byte[] output = md.digest(input);
            System.arraycopy(output, 0, result, i * BLOCK_SIZE, BLOCK_SIZE);
            input = concatenate(output, data);
        }

        byte[] truncatedResult = new byte[outLen / 8];
        System.arraycopy(result, 0, truncatedResult, 0, outLen / 8);
        return truncatedResult;
    }

    //byte배열 하나로 연결
    private byte[] concatenate(byte[]... arrays) {
        int totalLength = 0;
        for (byte[] array : arrays) {
            totalLength += array.length;
        }

        byte[] result = new byte[totalLength];
        int offset = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }
    
    
}