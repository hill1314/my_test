package com.hull.test.suanfa.kaoshi;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

/**
 * Created by Administrator on 2017/3/17.
 *   永洪科技 网上测试题
 */
public class MM {
    public static void main(String[] args) {
        //1
//        String[] strings = {};

        //2 a,b,a1,a2,a,b,b,b,b3,d,e
//        String[] strings = {"a","b","a1","a2","a","b","b","b","b3","d","e"};
//        renameStrings(strings);

        //4
//        String[] dates = {"20170101 00:00:10","20170301 00:00:30"};
//        calDates(dates);
        //5
        String[] strs = {"-100.1","abcd","1.0023e13","true","tttt","20","20170101",
                "False","100%","20170103 04:00:00"};
//        chkStringType(strs);

        int[] numbers = {4,5};
        buttons(numbers);

    }

    //1
    public void chkSql(String[] strs){
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<strs.length;i++){
            if(strs[i].contains("limit")){
                System.out.println("no,");
            }else{
                System.out.println("yes,");
            }
        }
        String result = buffer.toString().substring(0,buffer.toString().length()-1);
        System.out.println(result);

    }

    //2
    public static void renameStrings(String[] strings){
        String[] otherStrs = strings;
        HashMap<String,Integer> map = new HashMap();
        int n=0;
        for(int i=0;i<strings.length;i++){
            if(map.containsKey(strings[i])){
                n = map.get(strings[i])+1;
                map.put(strings[i],n);
                otherStrs[i] = strings[i]+n;
            }else{
                map.put(strings[i],1);
            }
        }
        System.out.println(Arrays.toString(otherStrs));
    }

    //4
    public static void calDates(String[] dates){
        int[] i1 = date2Int(dates[0]);
        int[] i2 = date2Int(dates[1]);
        compare(i2,i1);

    }

    public static int[] date2Int(String date){
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6,8));
        int hour = Integer.parseInt(date.substring(9,11));
        int minute = Integer.parseInt(date.substring(12,14));
        int second = Integer.parseInt(date.substring(15,17));
        int[] ints = {year,month,day,hour,minute,second};
        return ints;
    }

    public static void compare(int[] i1,int[] i2){
        StringBuffer buffer = new StringBuffer();
        int num = 0;
        if((num = i1[0]-i2[0])>0){
            buffer.append(num+"year,");
        }
        if((num = i1[1]-i2[1])>0){
            buffer.append(num+"month,");
        }
        if((num = i1[2]-i2[2])>0){
            buffer.append(num+"day,");
        }
        if((num = i1[3]-i2[3])>0){
            buffer.append(num+"hour,");
        }
        if((num = i1[4]-i2[4])>0){
            buffer.append(num+"minute,");
        }
        if((num = i1[5]-i2[5])>0){
            buffer.append(num+"second");
        }
        System.out.println(buffer.toString());
    }



    //5
    public static void chkStringType(String[] strs){
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<strs.length;i++){
            String str = strs[i].toLowerCase();
            if(str.equals("true") || str.equals("false")){
                buffer.append("Boolean,");
                continue;
            }
            if(NumberUtils.isNumber(str)){
                if(str.length()==8){
                    buffer.append("Date,");
                    continue;
                }else{
                    buffer.append("Number,");
                    continue;
                }
            }

            if(str.length()==17 && str.contains(":")){
                buffer.append("Date,");
                continue;
            }
            buffer.append("String,");
        }
        String result = buffer.toString().substring(0,buffer.toString().length()-1);
        System.out.println(result);
    }


    //8
    public static void buttons(int[] numbers){
        StringBuffer buffer = new StringBuffer();
        Map<String,String> buttonMap = new HashMap();
        buttonMap.put("1","");
        buttonMap.put("2","abc");
        buttonMap.put("3","def");
        buttonMap.put("4","ghi");
        buttonMap.put("5","jkl");
        buttonMap.put("6","mno");
        buttonMap.put("7","pqrs");
        buttonMap.put("8","tuv");
        buttonMap.put("9","wxyz");

        char[] chars1 = buttonMap.get(numbers[0]+"").toCharArray();
        char[] chars2 = buttonMap.get(numbers[1]+"").toCharArray();
        for(int m=0;m<chars1.length;m++){
            for(int n=0;n<chars2.length;n++){
                buffer.append(chars1[m]+""+chars2[n]+",");
            }
        }
        String result = buffer.toString().substring(0,buffer.toString().length()-1);
        System.out.println(result);
    }

    //9
    public static void goSetp(int m,int n){
        for(int i=m;i>0;i--){
            for(int j=n;j>0;j--){

            }
        }
    }
}
