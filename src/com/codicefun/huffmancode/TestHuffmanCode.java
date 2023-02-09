package com.codicefun.huffmancode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class TestHuffmanCode {

    @Test
    public void testCode() {
        String content = "i like like like java";

        byte[] codes = HuffmanCode.code(content);
        System.out.println(Arrays.toString(codes));

        HashMap<Byte, String> codeMap = HuffmanCode.getCodeMap();
        HuffmanCode.setCodeMap(codeMap);
        String decode = HuffmanCode.decode(codes);
        System.out.println(decode);
    }

}
