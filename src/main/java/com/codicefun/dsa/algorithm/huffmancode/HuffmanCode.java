package com.codicefun.dsa.algorithm.huffmancode;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 哈夫曼编码
 */
public class HuffmanCode {

    // 编码本
    private static HashMap<Byte, String> codeMap;

    public static HashMap<Byte, String> getCodeMap() {
        return codeMap;
    }

    public static void setCodeMap(HashMap<Byte, String> codeMap) {
        HuffmanCode.codeMap = codeMap;
    }

    /**
     * 获取字节出现的频率
     *
     * @param bytes 原始字节数组
     * @return HashMap<Byte, Integer> key 为字节，value 为频率
     */
    private static HashMap<Byte, Integer> getByteFrequency(byte[] bytes) {
        HashMap<Byte, Integer> byteFrequency = new HashMap<>();

        for (byte b : bytes) {
            byteFrequency.merge(b, 1, Integer::sum);
        }

        return byteFrequency;
    }

    /**
     * 获取节点列表
     *
     * @param frequency 字节出现频率
     * @return 节点列表
     */
    private static List<Node> getNodeList(HashMap<Byte, Integer> frequency) {
        ArrayList<Node> nodeList = new ArrayList<>();
        frequency.forEach((k, v) -> nodeList.add(new Node(k, v)));

        return nodeList;
    }

    /**
     * 创建编码本
     *
     * @param node 节点
     * @param str  路径，左节点为 0，右节点为 1
     * @param code 当前节点的编码
     */
    private static void createCodeMap(Node node, String str, StringBuilder code) {
        // 当前节点的编码
        StringBuilder sb = new StringBuilder();
        sb.append(code).append(str);

        if (node.data == null) {
            // 当前节点是非叶子节点
            if (node.left != null) {
                createCodeMap(node.left, "0", sb);
            }
            if (node.right != null) {
                createCodeMap(node.right, "1", sb);
            }
        } else {
            // 当前节点是叶子节点
            codeMap.put(node.data, sb.toString());
        }
    }

    /**
     * 编码的最后一步，按照编码本和原始文本生成编码后的二进制字符串，再将其转为字节数组
     *
     * @param content 原始字节数组
     * @return 编码后的字节数组
     */
    private static byte[] getCode(byte[] content) {
        StringBuilder sb = new StringBuilder();

        for (byte b : content) {
            sb.append(codeMap.get(b));
        }

        int len = (sb.length() + 7) / 8; // 最小长度
        byte[] code = new byte[len]; // 编码数组
        // i 为字符串索引，j 为编码数组索引
        for (int i = 0, j = 0; j < len; i += 8, j++) {
            if (i + 8 > sb.length()) {
                // 最后一个字节不做转换处理，直接放入编码本
                codeMap.put(null, sb.substring(i));
            } else {
                code[j] = (byte) Integer.parseInt(sb.substring(i, i + 8), 2);
            }
        }

        return code;
    }

    /**
     * 编码
     *
     * @param bytes 原始字节数组
     * @return 编码后的字节数组
     */
    private static byte[] code(byte[] bytes) {
        // 2、计算字节出现次数（频率）
        HashMap<Byte, Integer> frequency = getByteFrequency(bytes);
        // 3、根据频率生成节点（字节为 data，频率为 weights 即权重）
        List<Node> nodeList = getNodeList(frequency);
        // 4、生成哈夫曼树，得到根节点
        Node rootNode = HuffmanTree.createTree(nodeList);
        // 5、生成编码本
        codeMap = new HashMap<>();
        createCodeMap(rootNode, "", new StringBuilder());

        // 6、获得编码，并返回
        return getCode(bytes);
    }

    /**
     * 编码
     *
     * @param content 原始文本内容
     * @return 编码后的字节数组
     */
    public static byte[] code(String content) {
        // 1、将文本转为字节数组
        byte[] bytes = content.getBytes();

        return code(bytes);
    }

    /**
     * 将字节数组转为二进制字符串
     *
     * @param codes 编码后的字节数组
     * @return 二进制字符串
     */
    private static String getBinaryString(byte[] codes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < codes.length; i++) {
            String str = Integer.toBinaryString(codes[i] | 0x100);
            String format;
            if (i == codes.length - 1) {
                // 如果是最后一个字节，不需要转换，直接从编码本中取出
                format = codeMap.get(null);
            } else {
                format = str.substring(str.length() - 8);
            }
            sb.append(format);
        }

        return sb.toString();
    }

    /**
     * 将编码的二进制字符串转为原始文本
     *
     * @param codes 编码后的二进制字符串
     * @return 原始文本
     */
    private static String getContent(String codes) {
        StringBuilder sb = new StringBuilder();
        StringBuilder content = new StringBuilder();
        HashMap<String, Byte> decodeMap = new HashMap<>();

        // 生成解码本
        codeMap.forEach((key, value) -> decodeMap.put(value, key));

        int index = 0;
        while (index < codes.length()) {
            sb.append(codes.charAt(index++));
            Byte b = decodeMap.get(sb.toString());
            if (b != null) {
                content.append((char) (byte) b);
                sb = new StringBuilder();
            }
        }

        return content.toString();
    }

    /**
     * 解码
     *
     * @param codes 编码后的字节数组
     * @return 原始文本内容
     */
    public static String decode(byte[] codes) {
        String binaryString = getBinaryString(codes);

        return getContent(binaryString);
    }

    /**
     * 压缩文件
     *
     * @param srcPath 源文件路径
     * @param desPath 压缩路径
     */
    public static void compress(String srcPath, String desPath) {
        try (FileInputStream fis = new FileInputStream(srcPath);
             FileOutputStream fos = new FileOutputStream(desPath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            byte[] buf = new byte[fis.available()];
            if (fis.read(buf) != -1) {
                byte[] codes = code(buf);
                oos.writeObject(codes);
                oos.writeObject(codeMap);
                System.out.println("compress success");
            } else {
                System.out.println("compress fail");
            }
        } catch (IOException e) {
            System.out.println("compress fail");
            e.printStackTrace();
        }
    }

    /**
     * 解压文件
     *
     * @param srcPath 源文件路径
     * @param desPath 解压文件路径
     */
    @SuppressWarnings("unchecked")
    public static void decompress(String srcPath, String desPath) {
        try (FileInputStream fis = new FileInputStream(srcPath);
             ObjectInputStream ois = new ObjectInputStream(fis);
             FileWriter fw = new FileWriter(desPath);
             BufferedWriter br = new BufferedWriter(fw)) {
            byte[] codes = (byte[]) ois.readObject();
            codeMap = (HashMap<Byte, String>) ois.readObject();
            String content = decode(codes);
            br.write(content);
            System.out.println("decompress success");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("decompress fail");
            e.printStackTrace();
        }
    }

}
