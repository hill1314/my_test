package com.hull.test.suanfa.simple;

/**
 * Created by Administrator on 2017/3/17.
 */
public class Sushu {
    public static void main(String[] args) {
        for(int n=1;n<=200;n++){
            if(chk(n)){
                System.out.println(n);
            }
        }

    }

    public static boolean chk(int n){
        boolean result = true;
        for(int j=2;j<=n/2;j++){
            if(n%j==0){
               return false;
            }
        }
        return result;
    }
}
