package others;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DeplicatedTimeConsume {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    private static void test1() {
        long s = System.currentTimeMillis();
        List<String> ids = new ArrayList<>();
        System.out.println("准备数据");
        for (int i = 0; i < 10000000; i++) {
            ids.add(UUID.randomUUID().toString());
        }
        System.err.println("准备数据耗时：" + (System.currentTimeMillis() - s));
        s = System.currentTimeMillis();
        Set<String> set = new HashSet<>();
        ids.forEach(i -> set.add(i));
        System.err.println("去重数据： " + set.size() + ", 耗时" + (System.currentTimeMillis() - s));
    }

    private static void test2() {
        long s = System.currentTimeMillis();
        List<String> ids = new ArrayList<>();
        System.out.println("多线程测试，准备数据");
        for (int i = 0; i < 10000000; i++) {
            ids.add(UUID.randomUUID().toString());
        }
        System.err.println("准备数据耗时：" + (System.currentTimeMillis() - s));
        s = System.currentTimeMillis();
        Set<String> set = Collections.newSetFromMap(new ConcurrentHashMap<>());
        ids.parallelStream().forEach(i -> set.add(i));
        System.err.println("去重数据： " + set.size() + ", 耗时" + (System.currentTimeMillis() - s));
    }

    private static void test3() {
        long s = System.currentTimeMillis();
        List<String> ids = new ArrayList<>();
        System.out.println("预测大小，多线程测试，准备数据");
        for (int i = 0; i < 10000000; i++) {
            ids.add(UUID.randomUUID().toString());
        }
        System.err.println("准备数据耗时：" + (System.currentTimeMillis() - s));
        s = System.currentTimeMillis();
        Set<String> set = Collections.newSetFromMap(new ConcurrentHashMap<>(10000000));
        ids.parallelStream().forEach(i -> set.add(i));
        System.err.println("去重数据： " + set.size() + ", 耗时" + (System.currentTimeMillis() - s));
    }

    private static void test4() {
        long s = System.currentTimeMillis();
        List<Integer> ids = new ArrayList<>();
        System.out.println("改成int, 多线程测试，准备数据");
        for (int i = 0; i < 10000000; i++) {
            ids.add(i);
        }
        System.err.println("准备数据耗时：" + (System.currentTimeMillis() - s));
        s = System.currentTimeMillis();
        Set<Integer> set = Collections.newSetFromMap(new ConcurrentHashMap<>(10000000));
        ids.parallelStream().forEach(i -> set.add(i));
        System.err.println("去重数据： " + set.size() + ", 耗时" + (System.currentTimeMillis() - s));
    }

    private static void test5() {
        long s = System.currentTimeMillis();
        List<Integer> ids = new ArrayList<>();
        System.out.println("使用bit[], 多线程测试，准备数据");
        for (int i = 0; i < 10000000; i++) {
            ids.add(i);
        }
        System.err.println("准备数据耗时：" + (System.currentTimeMillis() - s));
        s = System.currentTimeMillis();
        byte[] bits = new byte[10000000];
        for(int id : ids){
            bits[id] = 1;
        }
        int count = 0;
        for (int i = 0; i < bits.length; i++) {
            if(bits[i] == 1){
                count ++;
            }
        }
        System.err.println("去重数据： " + count + ", 耗时" + (System.currentTimeMillis() - s));
    }


    private static void test6() {
        long s = System.currentTimeMillis();
        List<Integer> ids = new ArrayList<>();
        System.out.println("使用BitSet, 多线程测试，准备数据");
        for (int i = 0; i < 10000000; i++) {
            ids.add(i);
        }
        System.err.println("准备数据耗时：" + (System.currentTimeMillis() - s));
        s = System.currentTimeMillis();
        BitSet bits = new BitSet(10000000);
        for(int id : ids){
            bits.set(id, true);
        }
        int count = bits.cardinality();
        System.err.println("去重数据： " + count + ", 耗时" + (System.currentTimeMillis() - s));
    }
}
