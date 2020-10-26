package com.xearth.sp.seatonproject;

/**
 * @author wangxudong
 * @date 2020/7/21 16:19
 */
public class RBTree<K extends Comparable<K>, V> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    //树根的引用
    private RBNode root;

    /**
     * 获取当前节点的父节点
     * @param node
     * @return
     */
    private RBNode parentOf(RBNode node) {
        if(node != null) {
            return node.parent;
        }
        return null;
    }

    /**
     * 节点是否为红色
     * @param node
     * @return
     */
    private Boolean isRed(RBNode node) {
        if(node != null) {
            return node.color == RED;
        }
        return false;
    }

    /**
     * 设置节点位红色
     * @param node
     */
    private void setRed(RBNode node) {
        if(node != null) {
            node.color = RED;
        }
    }

    /**
     * 设置节点位黑色
     * @param node
     */
    private void setBlack(RBNode node) {
        if(node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 节点是否为黑色
     * @param node
     * @return
     */
    private Boolean isBlack(RBNode node) {
        if(node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    /**
     * 中序打印二叉树
     */
    public void inOrderPrint() {
        inOrderPrint(this.root);
    }
    private void inOrderPrint(RBNode node) {
        if(node != null) {
            inOrderPrint(node.left);
            System.out.println("key:" + node.key + ",value:" + node.value);
            inOrderPrint(node.right);
        }
    }

    /**
     *左旋方法
     */


    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
