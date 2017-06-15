package com.hull.test.javabase.paixu;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/17.
 */
public class Test {
    public static void main(String[] args) {
        int[] numbers = {2,3,5,1,9,4};
//        bubbleSort(numbers);
//        quickSort(numbers,0,5);
//        insertSort(numbers);
        selectSort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void selectSort(int[] numbers) {
        int size = numbers.length;
        int k,j,temp;
        for(int i=0;i<size;i++){
            k=i;
            for(j=size-1;j>i;j--){
                if(numbers[i]>numbers[j]){
                    k=j;
                }
            }
            temp = numbers[k];
            numbers[k] = numbers[i];
            numbers[i] = temp;
        }
    }

    private static void bubbleSort(int[] numbers) {
        int size = numbers.length;
        int temp,j;
        for(int i=0;i<size-1;i++){
            for(j=i+1;j<size;j++){
                if(numbers[i]>numbers[j]){
                    temp = numbers[i];
                    numbers[i]=numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

    private static void insertSort(int[] numbers) {
        int size = numbers.length;
        int temp ,j;
        for(int i=1;i<size;i++){
            temp = numbers[i];
            for(j = i;j>0 && numbers[j-1]>temp;j--){
                numbers[j] = numbers[j-1];
            }
            numbers[j]=temp;
        }
    }
}
