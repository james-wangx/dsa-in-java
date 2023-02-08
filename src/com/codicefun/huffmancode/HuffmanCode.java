package com.codicefun.huffmancode;

import java.util.*;

/**
 * 哈夫曼编码
 */
public class HuffmanCode {
    // 生成哈夫曼编码
    // 思路：
    // 1、将哈夫曼编码存放在 Map<Bytes, String> 形式
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 2、在生成哈夫曼编码时，需要拼接路径，定义一个 StringBuilder 存储某个叶子节点的路径
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        List<Node> nodes = getNodes(contentBytes);

        Node root = createHuffmanTree(nodes);
        root.preOrder();

        // 测试是否生成了对应的哈夫曼编码
        // getCodes(root, "", sb);
        Map<Byte, String> codes = getCodes(root);
        System.out.println("哈夫曼编码表：" + codes);
    }

    /**
     * 将 bytes 数组转为 Node 列表
     *
     * @param bytes 字节数组
     * @return Node 列表
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 1、创建 ArrayList
        List<Node> nodes = new ArrayList<>();

        // 存储每一个 byte 出现的次数
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            counts.merge(b, 1, Integer::sum); // 方法引用
        }

        // 把一个键值对转成一个 Node 对象，并加入 nodes 集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    // 创建哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 父节点没有 data 编码
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }

        getCodes(root.left, "0", sb);
        getCodes(root.right, "1", sb);

        return huffmanCodes;
    }

    /**
     * 得到哈夫曼编码并放入 huffmanCodes
     *
     * @param node 节点
     * @param code 路径：左子节点是 0，右子节点是 1
     * @param sb   用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);

        if (node != null) {
            // 判断当前 node 是叶子节点还是非叶子节点
            if (node.data == null) {
                // 向左递归
                getCodes(node.left, "0", sb2);
                // 向右递归
                getCodes(node.right, "1", sb2);
            } else {
                // 叶子节点，停止遍历
                huffmanCodes.put(node.data, sb2.toString());
            }
        }
    }
}

class Node implements Comparable<Node> {
    Byte data; // 存放数据本身
    int weight; // 权值
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);

        if (left != null) {
            left.preOrder();
        }

        if (right != null) {
            right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
