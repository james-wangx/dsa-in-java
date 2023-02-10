package com.codicefun.huffmancode;

import org.junit.Test;

import java.util.HashMap;

public class TestHuffmanCode {

    @Test
    public void testCode() {
        String content = "Using this coding, \"go go gophers\" is encoded (again, spaces would not appear in the bit-stream) as: 00 01 101 00 01 101 00 01 1110 1101 1100 1111 100. This is a total of 37 bits, two bits fewer than the improved encoding in which each of the 8 characters has a 3-bit encoding! The bits are saved by coding frequently occurring characters like 'g' and 'o' with fewer bits (here two bits) than characters that occur less frequently like 'p', 'h', 'e', and 'r'.\n" +
                "\n" +
                " \n" +
                "\n" +
                "To decode a given stream that has been coded by the given tree, start at the root of the tree, and follow a left-branch if the next bit in the stream is a 0, and a right branch if the next bit in the stream is a 1. When you reach a leaf, write the character stored at the leaf, and start again at the top of the tree. The bit stream 10011101101110011111100 yields right-left-left to the letter 's', followed (starting again at the root) with right-right-right-left to the letter 'p', followed by right-right-left-right to the letter 'h'. Continuing thus yields a decoded string “sphere”.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Prefix codes\n" +
                " \n" +
                "\n" +
                "When all characters are stored in leaves, and every interior (non-leaf) node has two children, the coding induced by the 0/1 convention outlined above satisfies a very important property called the prefix property which states that no bit-sequence encoding of a character is the prefix of the bit-sequence encoding of any other character. This makes it possible to decode a bitstream using the coding tree by following root-to-leaf paths. The tree shown above for \"go go gophers\" satisfies this prefix property and is an optimal tree. There are other trees that use 37 bits; for example you can simply swap any sibling nodes and get a different encoding that uses the same number of bits. Next, we look at an algorithm for constructing such an optimal tree. This algorithm is called Huffman coding, and was invented by David A. Huffman in 1952 when he was a Ph.D. student at MIT.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Huffman Coding\n" +
                " \n" +
                "\n" +
                "In the previous section we saw examples of how a stream of bits can be generated from an encoding. We also saw how the tree can be used to decode a stream of bits. We'll discuss how to construct the tree here using Huffman's algorithm.\n" +
                "\n" +
                " \n" +
                "\n" +
                "We'll assume that associated with each character is a weight that is equal to the number of times the character occurs in a file. For example, in the string \"go go gophers\", the characters 'g' and 'o' have weight 3, the space has weight 2, and the other characters have weight 1. When compressing a file we'll need to first read the file and calculate these weights. Assume that all the character weights have been calculated. Huffman's algorithm assumes that we're building a single tree from a group (or forest) of trees. Initially, all the trees have a single node containing a character and the character's weight. Iteratively, a new tree is formed by picking two trees and making a new tree whose child nodes are the roots of the two trees. The weight of the new tree is the sum of the weights of the two sub-trees. This decreases the number of trees by one in each iteration. The process iterates until there is only one tree left. The algorithm is as follows:\n" +
                "\n" +
                " \n" +
                "\n" +
                "Begin with a forest of trees. All trees have just one node, with the weight of the tree equal to the weight of the character in the node. Characters that occur most frequently have the highest weights. Characters that occur least frequently have the smallest weights.\n" +
                "Repeat this step until there is only one tree:\n" +
                "Choose two trees with the smallest weights; call these trees T1 and T2. Create a new tree whose root has a weight equal to the sum of the weights T1 + T2 and whose left sub-tree is T1 and whose right sub-tree is T2.\n" +
                "\n" +
                "The single tree left after the previous step is an optimal encoding tree.\n" +
                " \n" +
                "\n" +
                "We shall use the string \"go go gophers\" as an example. Initially we have the forest shown below. The nodes are shown with a weight that represents the number of times the node's character occurs in the given input string/file.";

        byte[] codes = HuffmanCode.code(content);
        System.out.println(codes.length);

        HashMap<Byte, String> codeMap = HuffmanCode.getCodeMap();
        HuffmanCode.setCodeMap(codeMap);
        String decode = HuffmanCode.decode(codes);
        System.out.println(decode);
    }

    @Test
    public void testZip() {
        String srcPath = "/tmp/genact.log";
        String desPath = "/tmp/genact.log.hz";
        HuffmanCode.zipFile(srcPath, desPath);
    }

    @Test
    public void testUnzip() {
        String srcPath = "/tmp/genact.log.hz";
        String desPath = "/tmp/genact2.log";
        HuffmanCode.unzipFile(srcPath, desPath);
    }

}
