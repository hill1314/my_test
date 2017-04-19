package com.hull.test.suanfa.tree;


/**
 * Created by Administrator on 2017/4/18.
 */
public class BinaryTree<K extends Comparable,V> {
    private Node<K,V> root;

    public Node<K, V> getRoot() {
        return root;
    }

    public void setRoot(Node<K, V> root) {
        this.root = root;
    }

    /**
     * 二叉树添加元素
     * @param key
     * @param value
     */
    public void addNode(K key,V value){
        Node<K,V>  node = new Node<K, V>(key,value);
        add(root,node);
    }

    /**
     * 二叉树添加元素
     * @param node
     */
    public void addNode(Node<K,V> node){
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
        Node<K,V> node = getNode(key);
        return null==node?null:node.getValue();
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
            return get(node.getLeft(),key);    //到左子节点继续找
        }else if(r<0){//当前节点的key < 要找的key
            return get(node.getRight(),key); //到右子节点继续找
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
            return "{\"" + key + "\":\"" + value +
                    "\", left:" + (null==left?"\"\"":left.toString()) +
                    ", right:" + (null==right?"\"\"":right.toString()) +
                    '}';
        }
    }

    /**
     * 前续遍历
     * @param node
     */
    public void preOrder(Node node){
        if (null==node)   return;
        print(node);
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    /**
     * 中续遍历
     * @param node
     */
    public void inOrder(Node node){
        if (null==node)   return;
        inOrder(node.getLeft());
        print(node);
        inOrder(node.getRight());
    }
    /**
     * 后续遍历
     * @param node
     */
    public void postOrder(Node node){
        if (null==node)   return;
        postOrder(node.getLeft());
        postOrder(node.getRight());
        print(node);
    }

    private void print(Node node) {
        System.out.print("["+node.getKey()+"]="+node.getValue()+",");
    }

    public static void main(String[] args) {
        BinaryTree<Integer,String> tree = new BinaryTree<Integer, String>();
        Node<Integer,String> root = new Node<>(12,"V12");
        tree.setRoot(root);
        tree.addNode(new Node<>(5,"V5"));
        tree.addNode(new Node<>(3,"V3"));
        tree.addNode(new Node<>(6,"V6"));
        tree.addNode(new Node<>(19,"V19"));
        tree.addNode(new Node<>(8,"V8"));
        tree.addNode(new Node<>(15,"V15"));
        tree.addNode(new Node<>(13,"V13"));
        tree.addNode(new Node<>(20,"V20"));
        tree.addNode(new Node<>(21,"V21"));
        tree.addNode(new Node<>(28,"V28"));
        System.out.println(root.toString());

        //search
//        String value = tree.getValue(19);
//        System.out.println(value);

        //遍历
        System.out.println("前续：");
        tree.preOrder(root);
        System.out.println("中续：");
        tree.inOrder(root);
        System.out.println("后续：");
        tree.postOrder(root);

    }
}
