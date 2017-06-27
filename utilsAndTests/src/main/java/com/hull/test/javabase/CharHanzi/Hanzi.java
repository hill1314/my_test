package com.hull.test.javabase.CharHanzi;

/**
 * Created by hull on 2017/3/1.
 *
 * 关于 汉字占用几位的测试
 */
public class Hanzi {
    public static void main(String[] args) {
//        String str= "中";
//        char c ='中';
//        byte[] bytes1=null;
//        byte[] bytes2=null;
//        try {
//            bytes1 = str.getBytes("utf-8");
//            bytes2 = charToByte(c);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //String.getBytes(encoding)方法是获取指定编码的byte数组表示，通常gbk/gb2312是2个字节，utf- 8是3个字节。
//        // 如果不指定encoding则取系统默认的encoding
//        System.out.println("bytes 大小："+bytes1.length);
//        //java中的char是Unicode编码的，而Unicode编码占两个字节，也就是16位，一个汉字占用两个字节。
//        System.out.println("bytes1大小："+bytes2.length);
    }

    /**
     * char 转为 byte数组
     * @param c
     * @return
     */
    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

}
