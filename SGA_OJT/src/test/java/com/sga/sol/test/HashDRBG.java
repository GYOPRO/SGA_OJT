package com.sga.sol.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashDRBG {
    private static final int SECURITY_STRENGTH = 256; // ���� ����
    private static final int BLOCK_SIZE = 136; // ��� ũ��
    private static final int OUT_LEN = 256; // ��� ����
    private static final int MAX_BITS_REQUESTED = 7500; // �ִ� ��û ��Ʈ ��

    private byte[] seedKey;
    private byte[] V;
    private byte[] C;
    private int reseedCounter;
    private MessageDigest md;

    public HashDRBG() {
        try {
            // SHA3-256 �˰����� ����ϱ� ���� MessageDigest�� �ʱ�ȭ�մϴ�.
            md = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Seed Key�� �����մϴ�.
        SecureRandom secureRandom = new SecureRandom();
        seedKey = new byte[BLOCK_SIZE / 8];
        secureRandom.nextBytes(seedKey);

        // V�� C ���� �ʱ�ȭ�մϴ�.
        V = new byte[BLOCK_SIZE / 8];
        C = new byte[BLOCK_SIZE / 8];
        reseedCounter = 1;
    }

    public byte[] generateRandomBits(int bitsRequested) {
        // �ִ� ��û ��Ʈ ���� �ʰ��ϴ� ��� ���ܸ� �߻���ŵ�ϴ�.
        if (bitsRequested > MAX_BITS_REQUESTED) {
            throw new IllegalArgumentException("Requested bit length is greater than the maximum value allowed.");
        }

        // ��û�� ��Ʈ ���� ����Ʈ ���� ��ȯ�մϴ�.
        int bytesRequested = (bitsRequested + 7) / 8;

        // ���� �Ұ����� �� ���� �ܰ�
        byte[] data = generateSeedMaterial(bytesRequested);
        byte[] randomBits = hashgen(OUT_LEN, data);

        // ���� �õ� �� ���� �ܰ�
        data = generateSeedMaterial(0);
        update(data);

        return randomBits;
    }

    //���� �õ� ���� ������ ������ ��Ʈ���� ����Ͽ� ���� �õ尪 ����
    private byte[] generateSeedMaterial(int bytesRequested) {
        // ���� �õ� ���� ������ ������ ��Ʈ���� ����Ͽ� ���� �õ� ���� �����մϴ�.
        byte[] nextSeed = getNextSeed();

        // ���� �õ� ���� ����Ͽ� SHA3-256 �˰����� �����Ͽ� ������ ��Ʈ���� �����մϴ�.
        byte[] data = new byte[nextSeed.length + 4];
        System.arraycopy(nextSeed, 0, data, 0, nextSeed.length);
        data[nextSeed.length] = (byte) ((bytesRequested >> 24) & 0xff);
        data[nextSeed.length + 1] = (byte) ((bytesRequested >> 16) & 0xff);
        data[nextSeed.length + 2] = (byte) ((bytesRequested >> 8) & 0xff);
        data[nextSeed.length + 3] = (byte) (bytesRequested & 0xff);

        return hashgen(BLOCK_SIZE, data);
    }

    //���� �õ� ���� V���� ����Ͽ� ���� �õ尪 ����
    private byte[] getNextSeed() {
        // ���� �õ� ���� V ���� ���
    	 //�Ͽ� ���� �õ� ���� �����մϴ�.
        byte[] seed = new byte[V.length + seedKey.length];
        System.arraycopy(V, 0, seed, 0, V.length);
        System.arraycopy(seedKey, 0, seed, V.length, seedKey.length);
        byte[] nextSeed = hashgen(BLOCK_SIZE, seed);
        return nextSeed;
    }

    //V�� C�� ����Ͽ� V�� ����, C�� ������Ʈ
    private void update(byte[] data) {
        // ���� V ���� C ���� ����Ͽ� ���� V ���� �����մϴ�.
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

    //�־��� �����Ϳ� C���� ����Ͽ� ������ ��Ʈ�� ����
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

    //byte�迭 �ϳ��� ����
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