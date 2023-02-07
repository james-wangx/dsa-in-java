package com.codicefun.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        tree.setRoot(node1);

        // System.out.println("前序遍历");
        // tree.preOrder();
        //
        // System.out.println("中序遍历");
        // tree.infixOrder();
        //
        // System.out.println("后序遍历");
        // tree.postOrder();

        // HeroNode res1 = tree.preOrderSearch(5);
        // System.out.println(res1);
        //
        // HeroNode res2 = tree.infixOrderSearch(5);
        // System.out.println(res2);
        //
        // HeroNode res3 = tree.postOrderSearch(5);
        // System.out.println(res3);

        System.out.println("删除前");
        tree.preOrder();
        System.out.println("删除后");
        tree.delNode(3);
        tree.preOrder();
    }
}

class BinaryTree {
    public HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
