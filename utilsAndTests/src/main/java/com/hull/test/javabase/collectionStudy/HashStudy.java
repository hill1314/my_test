package com.hull.test.javabase.collectionStudy;

/**
 * Created by Administrator on 2017/3/8.
 */
public class HashStudy {
    public static void main(String[] args) {
        String key = "100";
        int h = key.hashCode();
        int h2 = h>>>16;
        int hash = h^h2;
        System.out.println(hash);
    }

    /***
     * HashMap 的hash算法
     * @param key
     * @return
     */
    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
