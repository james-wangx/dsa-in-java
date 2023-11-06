package com.codicefun.dsa.huffmancode;

/**
 * 节点
 */
public class Node implements Comparable<Node> {

    public Byte data; // 字节数据
    public Integer weights; // 权重
    public Node left; // 左节点
    public Node right; // 右节点

    public Node(Byte data, Integer weights) {
        this.data = data;
        this.weights = weights;
    }

    @Override
    public String toString() {
        return String.format("{data=%d, weights=%d}", data, weights);
    }

    @Override
    public int compareTo(Node o) {
        // 升序排序
        return this.weights - o.weights;
    }

}
