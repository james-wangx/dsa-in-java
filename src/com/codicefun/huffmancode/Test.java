package com.codicefun.huffmancode;

import java.util.Arrays;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        String content = "public class Test {\n" +
                "    public static void main(String[] args) {\n" +
                "        String content = \"\";\n" +
                "\n" +
                "        byte[] codes = HuffmanCode.code(content);\n" +
                "        System.out.println(Arrays.toString(codes));\n" +
                "\n" +
                "        String decode = HuffmanCode.decode(codes);\n" +
                "        System.out.println(decode);\n" +
                "    }\n" +
                "}";

        byte[] codes = HuffmanCode.code(content);
        System.out.println(Arrays.toString(codes));

        HashMap<Byte, String> codeMap = HuffmanCode.getCodeMap();
        HuffmanCode.setCodeMap(codeMap);
        String decode = HuffmanCode.decode(codes);
        System.out.println(decode);
    }
}
