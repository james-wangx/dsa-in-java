package com.codicefun.dsa.huffmancode;

import java.util.Collections;
import java.util.List;

/**
 * 哈夫曼树
 */
public class HuffmanTree {

    /**
     * 生成哈夫曼树
     *
     * @param nodeList 节点列表
     * @return 根节点
     */
    public static Node createTree(List<Node> nodeList) {
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            Node parentNode = new Node(null, leftNode.weights + rightNode.weights);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parentNode);
        }

        return nodeList.get(0);
    }
}
