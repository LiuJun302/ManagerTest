package CodingTrain;

import java.util.ArrayList;
import java.util.Collections;

public class SortAlgorithms {
    public static void main(String[] args) {
        int[] a = {1, 5, 2, 12, 34, 23, 9, 5, 22};

        SelectSort(a);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        String s = "";
        s.equals("");
       // System.out.println(GetKthNum(3207, 3));
    }



    //selection sort
    public static void SelectSort(int[] a){
        for (int i = 0; i < a.length - 1; i++) {
            int max = a[i], index = i;
            for (int j = i+1; j < a.length; j++) {
                if(max > a[j]){
                    index = j;
                    max = a[j];
                }
            }
            a[index] = a[i];
            a[i] = max;
        }
    }

    //bubble sort
    public static void BubbleSort(int[] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if(a[j] > a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
    }

    //insert sort
    public static void InsertSort(int[] a){
        for (int i = 1; i < a.length; i++) {
            int tmpIdx = i;
            for (int j = i-1; j >= 0; j--) {
                if(a[tmpIdx] < a[j]){
                    int tmp =a[j];
                    a[j] = a[tmpIdx];
                    a[tmpIdx] = tmp;
                    tmpIdx = j;
                }
            }
        }
    }

    //merge sort
    public static void MergeSort(int[] a, int low, int high){
        if(low < high){
            int mid = (high+low) / 2;
            MergeSort(a, low, mid);
            MergeSort(a, mid+1, high);
            Merge(a, low, mid, high);
        }
    }
    public static void Merge(int[] a, int low, int mid, int high){
        int[] tmp = new int[high - low + 1];
        int lowIdx = low, highIdx = mid+1, index = 0;
        while (lowIdx<=mid && highIdx<=high){
            if(a[lowIdx] < a[highIdx])
                tmp[index ++] = a[lowIdx ++];
            else
                tmp[index ++] = a[highIdx ++];
        }
        while (lowIdx <= mid){
            tmp[index ++] = a[lowIdx ++];
        }
        while (highIdx <= high){
            tmp[index ++] = a[highIdx ++];
        }
        for (int i = 0; i < tmp.length; i++) {
            a[i + low] = tmp[i];
        }
    }
    public static int[] merge(int[] a, int[] b){
        int aIdx=0, bIdx=0, rIdx=0;
        int[] result = new int[a.length + b.length];
        while(aIdx<a.length && bIdx<b.length){
            if(a[aIdx] < b[bIdx])
                result[rIdx ++] = a[aIdx];
            else
                result[rIdx ++] = b[bIdx];
        }
        while (aIdx < a.length){
            result[rIdx ++] = a[aIdx ++];
        }
        while (bIdx < b.length){
            result[rIdx ++] = b[bIdx ++];
        }
        return result;
    }

    //quick sort
    public static void QuickSort(int[] a, int low, int high){
        int index;
        if(low < high){
            index = quick(a, low, high);
            QuickSort(a, low, index-1);
            QuickSort(a, index+1, high);
        }
    }
    public static int quick(int[] a, int low, int high){
        int key = a[low];
        while(low < high){
            while (low<high && a[high]>=key){
                high --;
            }
            a[low] = a[high];
            while (low<high && a[low]<=key){
                low ++;
            }
            a[high] = a[low];
        }  
        a[low] = key;
        return low;
    }

    //shell sort
    public static void HillSort(int[] a){
        int gap = a.length / 2;
        while (gap >= 1){
            for (int i = 0; i < a.length; i++) {
                for (int j = i; j < a.length - gap; j+=gap) {
                 if(a[j] > a[j+gap])
                     swap(a, j, j+gap);
                }
            }
            gap /= 2;
        }
    }

    //counting sort
    public static void CountSort(int[] a){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = a[i]>max ? a[i] : max;
        }
        int[] tmp = new int[max + 1];

        for (int i = 0; i < a.length; i++) {
            tmp[a[i]] ++;
        }
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
            while (tmp[i] > 0){
                a[index ++] = i;
                tmp[i] --;
            }
        }
    }

    //heap sort
    public static void HeapSort(int[] a){
        HeapOperate.HeapSort(a);
    }

    //radix sort
    public static void RadixSort(int[] a) {
        int k = 1;
        int[][] space = new int[10][a.length];
        int[] count = new int[10];
        int loop;
        do{
            loop = 0;
            int index = 0;

            //put numbers to 10 bucket according to the radix
            for (int i = 0; i < a.length; i++) {
                int idx = GetKthNum(a[i], k);
                //if redix==-1, no bucket can put in, so continue
                if (idx <= -1)
                    a[index ++] = a[i];
                else{
                    space[idx][count[idx]] = a[i];
                    count[idx] ++;
                }
            }
            //move num from bucket to a[]
            for (int i = 0; i < space.length; i++) {
                int tmp = 0;
                if(count[i] > 0)
                    loop ++;
                while (count[i] > 0){
                    a[index++] = space[i][tmp++];
                    count[i] --;
                }
            }
            //next radix
            k ++;
            System.out.println("++++++++++++++++++++");
        }while (loop >= 2);
        System.out.println(11);
    }
    public static int GetKthNum(int b, int k){
        int div = (int)Math.pow(10, k-1);
        int tmp = b / div;
        int result = tmp==0 ? -1 : tmp%10;
        return result;
    }

    //bucket sort
    public static void BucketSort(int[] a){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < a.length; i++){
            max = Math.max(max, a[i]);
            min = Math.min(min, a[i]);
        }
        //桶数
        int bucketNum = (max - min) / a.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<Integer>());
        }
        //将每个元素放入桶
        for(int i = 0; i < a.length; i++){
            int num = (a[i] - min) / (a.length);
            bucketArr.get(num).add(a[i]);
        }
        //对每个桶进行排序
        for(int i = 0; i < bucketArr.size(); i++){
            Collections.sort(bucketArr.get(i));
        }
        int idx = 0;
        for (int i = 0; i < bucketArr.size(); i++) {
            if(bucketArr.get(i).size()>0){
                for (int j = 0; j < bucketArr.get(i).size(); j++) {
                    a[idx ++] =  bucketArr.get(i).get(j);
                }
            }
        }
    }

    public static void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
