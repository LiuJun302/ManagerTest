package CodingTrain;

public class HeapOperate {
    public static void main(String[] args) {
        //int[] a = {4, 3, 8, 10, 11, 13, 7, 30, 17, 26};
        int a[] = {5, 17, 9, 10, 8, 4, 13, 3 ,7 ,11};
        MakeHeap(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void HeapSort(int[] a){
        MakeHeap(a);
        for (int i = a.length-1; i >= 1; i --) {
            swap(a, i, 0);
            ShiftDown(a, 0, i);
        }
    }
    public static void MakeHeap(int[] a){
        int len = a.length - 1;
        int idx = len%2==0 ? len/2-1 : len/2;
        while (idx >= 0){
            ShiftDown(a, idx, a.length);
            idx --;
        }
    }
    public static void ShiftDown(int[] a, int idx, int len){
        while (idx<len && (2*idx+1)<len){
            idx = 2*idx + 1;
            if (idx<len-1 && a[idx]<a[idx+1])
                idx ++;
            int k = idx%2==1 ? idx/2 : (idx/2-1);
            if(a[idx] > a[k] ){
                swap(a, idx, k);
            }
            else
                idx = len;
        }
    }
    public static void ShiftUp(int[] a, int idx){
        while (idx>0){
            int k = idx%2==0 ? idx/2-1 : idx/2;
            if(a[idx] < a[k]){
                swap(a, idx, k);
                idx = k;
            }
            else
                idx = 0;
        }
    }
    public static int[] Insert(int[] a, int v){
        int[] b = new int[a.length+1];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        b[a.length] = v;
        ShiftUp(b, b.length-1);
        return b;
    }
    public static int[] Delete(int[] a, int idx){
        swap(a, idx, a.length-1);
        int[] b = new int[a.length-1];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[i];
        }
        int k = idx%2==1 ? idx/2 : (idx/2-1);
        if(b[idx] < b[k])
            ShiftUp(b, idx);
        else
            ShiftDown(b, idx, b.length);
        return b;
    }
    public static void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
