package com.sga.sol.test;

import com.sga.sol.vc.util.ECBPasswordUtil;
import com.sga.sol.vc.util.sha3;

public class sha3_test {

    private static final int MSG_0_BUF_SIZE = 200;
    private static final int MSG_1600_BUF_SIZE = 200;
    private static final int OUTPUT_BUF_SIZE = 512;

    public static void main(String[] args) {
        sha3_0msg_test();

    }

    private static void sha3_0msg_test() {
        byte msg[] = new byte[MSG_0_BUF_SIZE];
        byte buf_sha3_224[] = new byte[OUTPUT_BUF_SIZE];
        byte buf_sha3_256[] = new byte[OUTPUT_BUF_SIZE];
        byte buf_sha3_384[] = new byte[OUTPUT_BUF_SIZE];
        byte buf_sha3_512[] = new byte[OUTPUT_BUF_SIZE];
        byte buf_shake128[] = new byte[OUTPUT_BUF_SIZE];
        byte buf_shake256[] = new byte[OUTPUT_BUF_SIZE];
        int len = 0, i = 0;

        sha3 sha3_test = new sha3();

        for (i = 0; i < MSG_0_BUF_SIZE; i++) {
            msg[i] = (byte) 0xA3;
        }	
       String message = "entrosadpy1";
       byte[] ms = message.getBytes();

       int inputLen = ms.length;
                
        System.out.println(message.toString());

        
   
        // SHA3-256 0_Msg test
        len = 256 / 8;
        sha3_test.sha3_hash(buf_sha3_256, len, ms,ms.length ,256, 0);
        System.out.println(buf_sha3_256);
        print_hex("sha3-256 이거", buf_sha3_256, len);
        System.out.println(inputLen);
       
    }

  

    private static void print_hex(String valName, byte[] data, int dataLen)
    {
        int i = 0;

        System.out.printf("%s :", valName);
        for (i = 0; i < dataLen; i++)
        {
            if ((i & 0x0F) == 0)
                System.out.println("");

            System.out.printf(" %02X", data[i]);
        }
        System.out.println("");
    }
}