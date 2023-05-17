package com.codicefun.dsa.binarytree;

import com.codicefun.dsa.tree.binarytree.BinaryTree;
import com.codicefun.dsa.tree.binarytree.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    BinaryTree<Integer> tree = new BinaryTree<>();
    Node<Integer> node1 = new Node<>(1);
    Node<Integer> node2 = new Node<>(2);
    Node<Integer> node3 = new Node<>(3);
    Node<Integer> node4 = new Node<>(4);
    Node<Integer> node5 = new Node<>(5);
    Node<Integer> node6 = new Node<>(6);

    @BeforeEach
    public void before() {
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        tree.setRoot(node1);
    }

    @Test
    public void testPrintPreorder() {
        System.out.print("preorder: ");
        tree.printPreorder();
    }

    @Test
    public void testPrintInorder() {
        System.out.print("inorder: ");
        tree.printInorder();
    }

    @Test
    public void testPrintPostorder() {
        System.out.print("postorder: ");
        tree.printPostorder();
    }

    @Test
    public void testSearchPreorder() {
        assertEquals(node4, tree.searchPreorder(4));
        assertNull(tree.searchPreorder(9));
    }

    @Test
    public void testSearchInorder() {
        assertEquals(node5, tree.searchInorder(5));
        assertNull(tree.searchInorder(9));
    }

    @Test
    public void testSearchPostorder() {
        assertEquals(node1, tree.searchPostorder(1));
        assertNull(tree.searchPostorder(9));
    }

    @Test
    public void testDeleteNode() {
        tree.deleteNode(4);
        tree.printPreorder();
        tree.deleteNode(3);
        tree.printPreorder();
        tree.deleteNode(1);
        tree.printPreorder();
    }
}