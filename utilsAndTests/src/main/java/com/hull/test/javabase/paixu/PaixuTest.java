package com.hull.test.javabase.paixu;

import org.apache.poi.ss.formula.functions.T;

import java.sql.Array;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/4.
 *
 *  内部排序（使用内存）------| 插入排序--直接插入排序、希尔排序
                             |选择排序--简单选择排序、堆排序
                             |交换排序--冒泡排序、快速排序
                             |归并排序
                             |基数排序
   外部排序（内存和外存结合排序）
 http://blog.csdn.net/hguisu/article/details/7776068

 * Java常用排序方法
 * http://www.cnblogs.com/sevenyuan/archive/2009/12/04/1616897.html
 */
public class PaixuTest {
    public static void main(String[] args) {
        int[] numbers = {6,3,5,1,9,4};
//        bubbleSort(numbers);
//        quickSort(numbers,0,5);
        myQuickSort(numbers,0,5);
//        insertSort(numbers);
        System.out.println(Arrays.toString(numbers));
    }


    public static void myQuickSort(int[] numbers,int start,int end){
        int i=start,j=end;
        int key = numbers[i];
        while(i<j){
            while(i<end && numbers[i]<key)
                i++;
            while(j>start && numbers[j]>key)
                j--;
            if(i<=j){
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                i++;
                j--;
            }
        }

        if(j>start){
            myQuickSort(numbers,start,j);
        }
        if(i<end){
            myQuickSort(numbers,i,end);
        }
    }

    /**
     * 冒泡法排序<br/>

     * <li>比较相邻的元素。如果第一个比第二个大，就交换他们两个。</li>
     * <li>对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。</li>
     * <li>针对所有的元素重复以上的步骤，除了最后一个。</li>
     * <li>持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。</li>
     *
     *  @param numbers
     *   需要排序的整型数组
     */
    public static void bubbleSort(int[] numbers){
        int temp;
        int size = numbers.length;
        for(int i=0;i<size-1;i++){
            for(int j=i+1;j<size;j++){
                if(numbers[i]>numbers[j]){
                    temp = numbers[i];
                    numbers[i]=numbers[j];
                    numbers[j]=temp;
                }
            }
        }
    }

    /**
     * 快速排序<br/>
     * <ul>
     * <li>从数列中挑出一个元素，称为“基准”</li>
     * <li>重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
     * 在这个分割之后，该基准是它的最后位置。这个称为分割（partition）操作。</li>
     * <li>递归地把小于基准值元素的子数列和大于基准值元素的子数列排序。</li>
     * </ul>
     *
     * @param targetArr
     * @param start
     * @param end
     */


    public static void quickSort2(int[]targetArr,int start,int end)
    {
        int i=start,j=end;
        int key = targetArr[start];

        while(i<j){
            /*按j--方向遍历目标数组，直到比key小的值为止*/
            while(j>i && targetArr[j]>=key){
                j--;
            }
            if(i<j){
                /*targetArr[i]已经保存在key中，可将后面的数填入*/
                targetArr[i]=targetArr[j];
                i++;
            }
            /*按i++方向遍历目标数组，直到比key大的值为止*/
            while(i<j&&targetArr[i]<=key){
            /*此处一定要小于等于零，假设数组之内有一亿个1，0交替出现的话，
            而key的值又恰巧是1的话，那么这个小于等于的作用就会使下面的if语句少执行一亿次。*/
                i++;
            }
            if(i<j){
                /*targetArr[j]已保存在targetArr[i]中，可将前面的值填入*/
                targetArr[j]=targetArr[i];
                j--;
            }
        }
        /*此时i==j*/
        targetArr[i]=key;

        /*递归调用，把key前面的完成排序*/
        quickSort2(targetArr,start,i-1);

        /*递归调用，把key后面的完成排序*/
        quickSort2(targetArr,j+1,end);

    }


    public static void quickSort(int[] numbers, int start, int end) {
        if (start < end) {
            int base = numbers[start]; // 选定的基准值（第一个数值作为基准值）
            int temp; // 记录临时中间值
            int i = start, j = end;
            do {
                //从左向右遍历，找出大于等于基准的数
                while ((numbers[i] < base) && (i < end))
                    i++;
                //从右向左遍历，找出小于等于基准的数
                while ((numbers[j] > base) && (j > start))
                    j--;
                //交换 上面找到的两个数
                if (i <= j) {
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                    i++;
                    j--;
                }
            } while (i < j); //遍历直到左右碰头

            //没有遍历完继续遍历
            if (j > start)
                quickSort(numbers, start, j);
            if ( i < end)
                quickSort(numbers, i, end);
        }
    }

    /**
     * 选择排序<br/>
     * <li>在未排序序列中找到最小元素，存放到排序序列的起始位置</li>
     * <li>再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。</li>
     * <li>以此类推，直到所有元素均排序完毕。</li>

     *
     * @param numbers
     */
    public static void selectSort(int[] numbers) {
        int size = numbers.length, temp;
        for (int i = 0; i < size; i++) {
            int k = i;
            //在未排序序列中找到最小元素，存放到排序序列的起始位置
            for (int j = size - 1; j >i; j--)  {
                if (numbers[j] < numbers[k])  k = j;
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }

    /**
     * 插入排序<br/>
     * <ul>
     * <li>从第一个元素开始，该元素可以认为已经被排序</li>
     * <li>取出下一个元素，在已经排序的元素序列中从后向前扫描</li>
     * <li>如果该元素（已排序）大于新元素，将该元素移到下一位置</li>
     * <li>重复步骤3，直到找到已排序的元素小于或者等于新元素的位置</li>
     * <li>将新元素插入到该位置中</li>
     * <li>重复步骤2</li>
     * </ul>
     *
     *  时间复杂度：O（n^2）.
     * @param numbers
     */
    public static void insertSort(int[] numbers) {
        int size = numbers.length, temp, j;
        for(int i=1; i<size; i++) {
            //取出下一个元素，在已经排序的元素序列中从后向前扫描
            temp = numbers[i];
            for(j = i; j > 0 && temp < numbers[j-1]; j--)
                //如果该元素（已排序）大于新元素，将该元素移到下一位置
                numbers[j] = numbers[j-1];
            //直到找到已排序的元素小于或者等于新元素的位置,将新元素插入到该位置中
            numbers[j] = temp;
        }
    }

    /**
     * 归并排序<br/>
     * <ul>
     * <li>申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列</li>
     * <li>设定两个指针，最初位置分别为两个已经排序序列的起始位置</li>
     * <li>比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置</li>
     * <li>重复步骤3直到某一指针达到序列尾</li>
     * <li>将另一序列剩下的所有元素直接复制到合并序列尾</li>
     * </ul>
     *
     * @param numbers
     */
    public static void mergeSort(int[] numbers, int left, int right) {
        int t = 1;// 每组元素个数
        int size = right - left + 1;
        while (t < size) {
            int s = t;// 本次循环每组元素个数
            t = 2 * s;
            int i = left;
            while (i + (t - 1) < size) {
                merge(numbers, i, i + (s - 1), i + (t - 1));
                i += t;
            }
            if (i + (s - 1) < right)
                merge(numbers, i, i + (s - 1), right);
        }
    }
    /**
     * 归并算法实现
     *
     * @param data
     * @param p
     * @param q
     * @param r
     */
    private static void merge(int[] data, int p, int q, int r) {
        int[] B = new int[data.length];
        int s = p;
        int t = q + 1;
        int k = p;
        while (s <= q && t <= r) {
            if (data[s] <= data[t]) {
                B[k] = data[s];
                s++;
            } else {
                B[k] = data[t];
                t++;
            }
            k++;
        }
        if (s == q + 1)
            B[k++] = data[t++];
        else
            B[k++] = data[s++];
        for (int i = p; i <= r; i++)
            data[i] = B[i];
    }


}
