package com.codicefun.tree.threadedbinarytree;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "1");
        HeroNode node2 = new HeroNode(3, "2");
        HeroNode node3 = new HeroNode(6, "3");
        HeroNode node4 = new HeroNode(8, "4");
        HeroNode node5 = new HeroNode(10, "4");
        HeroNode node6 = new HeroNode(14, "5");

        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(node1);
        tree.threadedNodes(node1);

        // System.out.println("node5.getLeft() = " + node5.getLeft());
        // System.out.println("node5.getRight() = " + node5.getRight());
        tree.list();
    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode prev;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 遍历
    public void list() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    // 中序线索化
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }

        threadedNodes(node.getLeft());

        if (node.getLeft() == null) {
            node.setLeft(prev); // 第一个节点指向 null
            node.setLeftType(1);
        }
        if (prev != null && prev.getRight() == null) {
            prev.setRight(node);
            prev.setRightType(1);
        }
        prev = node;

        threadedNodes(node.getRight());
    }

    public void preOrder() {
        if (root != null) {
            root.preOder();
        } else {
            System.out.println("null");
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("null");
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("null");
        }
    }

    public HeroNode preOrderSearch(int no) {
        return root.preOrderSearch(no);
    }

    public HeroNode infixOrderSearch(int no) {
        return root.infixOrderSearch(no);
    }

    public HeroNode postOrderSearch(int no) {
        return root.postOrderSearch(no);
    }

    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树");
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    private int leftType; // 0 表示左子树，1 表示前驱节点
    private int rightType; // 0 表示右子树，1 表示后继节点

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    // 前序遍历
    public void preOder() {
        System.out.println(this);

        if (left != null) {
            left.preOder();
        }
        if (right != null) {
            right.preOder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (left != null) {
            left.infixOrder();
        }
        System.out.println(this);
        if (right != null) {
            right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }
        if (right != null) {
            right.postOrder();
        }
        System.out.println(this);
    }

    // 前序查找
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }

        HeroNode node = null;
        if (left != null) {
            node = left.preOrderSearch(no);
        }
        if (node != null) {
            return node;
        }

        if (right != null) {
            node = right.preOrderSearch(no);
        }
        return node;
    }

    // 中序查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode node = null;
        if (left != null) {
            node = left.infixOrderSearch(no);
        }
        if (node != null) {
            return node;
        }

        if (this.no == no) {
            return this;
        }

        if (right != null) {
            node = right.infixOrderSearch(no);
        }
        return node;
    }

    // 后续查找
    public HeroNode postOrderSearch(int no) {
        HeroNode node = null;
        if (left != null) {
            node = left.postOrderSearch(no);
        }
        if (node != null) {
            return node;
        }

        if (right != null) {
            node = right.postOrderSearch(no);
        }
        if (node != null) {
            return node;
        }

        return this.no == no ? this : null;
    }

    // 递归删除节点
    public void delNode(int no) {
        if (left != null && left.no == no) {
            left = null;
            return;
        }
        if (right != null && right.no == no) {
            right = null;
            return;
        }

        if (left != null) {
            left.delNode(no);
        }
        if (right != null) {
            right.delNode(no);
        }
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
