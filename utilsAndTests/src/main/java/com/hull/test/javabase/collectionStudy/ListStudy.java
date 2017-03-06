package com.hull.test.javabase.collectionStudy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/1/21.
 */
public class ListStudy {
    public static void main(String[] args) {
        List arraylist = new ArrayList();      //底层实现为数组
        List linkedList = new LinkedList();     //底层实现为链表


        HashSet hashSet = new HashSet();
        hashSet.add(4);
        hashSet.add(1);
        hashSet.add(6);
        hashSet.add(3);
        System.out.println(hashSet);

        TreeSet treeSet = new TreeSet();
        treeSet.add(4);
        treeSet.add(1);
        treeSet.add(6);
        treeSet.add(3);
        System.out.println(treeSet);


        HashMap hashMap = new HashMap();
        Hashtable hashtable = new Hashtable();


        /**
         * 它引入了一个“分段锁”的概念，具体可以理解为把一个大的Map拆分成N个小的HashTable，
         * 根据key.hashCode()来决定把key放到哪个HashTable中
         * 在ConcurrentHashMap中，就是把Map分成了N个Segment，put和get的时候，
         * 都是现根据key.hashCode()算出放到哪个Segment中
         * ConcurrentHashMap中默认是把segments初始化为长度为16的数组。
         * 通过把整个Map分为N个Segment（类似HashTable），可以提供相同的线程安全，但是效率提升N倍，默认提升16倍
         */
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();


    }



}
