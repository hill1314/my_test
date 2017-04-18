package com.hull.test.suanfa.tree;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BinaryTree<K extends Calendar,V> {
    private Node<K,V> root;

    /**
     * 二叉树添加元素
     * @param treeNode
     * @param key
     * @param value
     */
    public void addNode(Node<K,V> treeNode,K key,V value){
        Node<K,V>  node = new Node<K, V>(key,value);
        add(root,node);
    }

    /**
     * 二叉树添加元素
     * @param treeNode
     * @param node
     */
    public void addNode(Node<K,V> treeNode,Node<K,V> node){
        add(root,node);
    }
    /**
     * 二叉树添加元素
     * @param treeNode
     * @param node
     */
    private Node<K,V>  add(Node<K,V> treeNode,Node<K,V> node){
        if(treeNode==null){
            return node;
        }
        int r = treeNode.getKey().compareTo(node.key);
        if(r==0){
            treeNode.setValue(node.getValue());
        }else if(r>0){
            treeNode.setLeft(add(treeNode.getLeft(),node));
        }else if(r<0){
            treeNode.setRight(add(treeNode.getRight(),node));
        }
        return treeNode;
    }

    /**
     *  模拟二叉树 search 方法
     * @param key
     * @return
     */
    public V getValue(K key){
        return getNode(key).getValue();
    }
    /**
     *  模拟二叉树 search 方法
     * @param key
     * @return
     */
    public Node<K,V> getNode(K key){
        return get(root,key);
    }

    /**
     *  递归查询 key所在的节点
     * @param node  当前的节点
     * @param key   要找的key
     * @return
     */
    private Node<K,V> get(Node<K, V> node, K key) {
        if(node==null || key==null){
            return null;
        }
        int r = node.getKey().compareTo(key);
        if(r==0){   //当前节点的key就是要找的key
            return node;
        }else if(r>0){  //当前节点的key > 要找的key
            get(node.getLeft(),key);    //到左子节点继续找
        }else if(r<0){//当前节点的key < 要找的key
            get(node.getRight(),key); //到右子节点继续找
        }
        return null;
    }


    /**
     * 构造一个Node结构
     * @param <K>
     * @param <V>
     */
    static class Node<K,V>{
        private Node<K,V> left;
        private Node<K,V> right;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
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

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
