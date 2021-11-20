package others;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Queue;
import java.util.*;

/**
 * Created by LJ on 2017/4/18.
 */
public class Main {
    public static void main(String[] args) {
        int[] a = {-2,1};
        System.out.println(maxSubArray(a));
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        list.size();
        HashSet<Integer> set = new HashSet<>();
        set.add(2);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.containsKey("1");
        map.size();

    }


    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i-1]+nums[i] > nums[i] ? dp[i-1]+nums[i] : nums[i];
            max = max > dp[i] ?max : dp[i];
        }
        return max;
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            int idx = bookings[i][0] - 1;
            while ((idx >= bookings[i][0] - 1) && (idx <= bookings[i][1] - 1)) {
                res[idx ++] += bookings[i][2];
            }
        }
        return res;
    }

    public static int NumberOf1(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) count++;
            n = n >> 1;
        }
        return count;
    }

    public static int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        if (null == root.left && null == root.right) return root.val;
        int sum = childSumNumbers(root.left, root.val) + childSumNumbers(root.right, root.val);
        return sum;
    }

    public static int childSumNumbers(TreeNode root, int tmpSum) {
        if (null == root) return tmpSum;
        if (null == root.left && null == root.right) return tmpSum * 10 + root.val;
        return childSumNumbers(root.left, tmpSum * 10 + root.val) + childSumNumbers(root.right, tmpSum * 10 + root.val);
    }

    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int l = matrix.length, c = matrix[0].length;
        int res = 0;
        int[][] dp = new int[l][c];
        for (int i = 0; i < l; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            res = res > dp[i][0] ? res : dp[i][0];
        }
        for (int i = 0; i < c; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            res = res > dp[0][i] ? res : dp[0][i];
        }
        for (int i = 1; i < l; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][j] == '0') dp[i][j] = 0;
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                res = res > dp[i][j] ? res : dp[i][j];

            }
        }
        return res * res;
    }

    public static int integerReplacement(int n) {
        int count = 0;
        while (n != 1) {
            if ((n & 1) == 0) n >>>= 1;
            else if (n == 3 || ((n >>> 1) & 1) == 0) n--;
            else n++;
            count++;

        }
        return count;
    }

    public static int odd(int n) {
        return Math.min(integerReplacement(n + 1), integerReplacement(n - 1)) + 1;
    }

    public boolean canJump(int[] nums) {
        if (nums.length < 1) return false;
        if (nums.length == 1) return true;
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (max < i + 1) return false;
            if (max >= (nums.length - 1)) return true;
        }
        return false;
    }

    public static void getRandArray(int row, int col) {
        double[][] d = new double[row][col];
        Random rd = new Random();
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j] = rd.nextDouble();
            }
        }
        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        long total = rt.totalMemory() / byteToMb;
        long free = rt.freeMemory() / byteToMb;
        long vmMax = rt.maxMemory() / byteToMb;
        long use = total - free;
        System.out.println(total);
        System.out.println(free);
        System.out.println(vmMax);
        System.out.println(use);

        SingularValueDecomposition svd = new SingularValueDecomposition(new Matrix(d));

        total = rt.totalMemory() / byteToMb;
        free = rt.freeMemory() / byteToMb;
        vmMax = rt.maxMemory() / byteToMb;
        use = total - free;
        System.out.println(total);
        System.out.println(free);
        System.out.println(vmMax);
        System.out.println(use);
    }

    public static boolean youzan2() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] strs = line.substring(1, line.length() - 1).split(",");
        int[] num = new int[strs.length];
        if (num.length == 0) return false;
        boolean[] flag = new boolean[num.length];
        for (int i = 0; i < num.length; i++)
            num[i] = Integer.valueOf(strs[i]);
        int idx = 0;
        while (true) {
            if (flag[idx] == true) return false;
            flag[idx] = true;
            idx = idx + num[idx];
            if (idx >= num.length || idx < 0) return true;
        }
    }

    public static boolean isIsomorphic(String s, String t) {
        boolean flag = false;
        if (s.length() != t.length()) return flag;
        return false;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        int idx = 0;
        for (; idx < inorder.length; idx++) {
            if (inorder[idx] == root.val) break;
        }

        int[] leftPre = Arrays.copyOfRange(preorder, 1, idx + 1);
        int[] rightPre = Arrays.copyOfRange(preorder, idx + 1, preorder.length);

        int[] leftIn = Arrays.copyOfRange(inorder, 0, idx);
        int[] rightIn = Arrays.copyOfRange(inorder, idx + 1, inorder.length);

        root.left = buildTree(leftPre, leftIn);
        root.right = buildTree(rightPre, rightIn);

        return root;
    }

    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        boolean[] f = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (f[nums[i] - 1] == false) f[nums[i] - 1] = true;
            else return nums[i];
        }
        return -1;
    }

    public static int getMaxValue(int[] num, int[] idx) {
        //idx长度为3，分别记录：开始坐标、结束坐标和最大值
        int min = num[idx[0]];
        int start = idx[0], end = idx[1];
        for (int i = start + 1; i < end; i++) {
            int cur = num[i] - min;
            if (num[i] < min) {
                idx[0] = i;
                min = num[i];
            }
            if (idx[2] < cur) {
                idx[1] = i;
                idx[2] = cur;
            }
        }
        return idx[2];
    }

    public static int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    public static ArrayList<Integer> maxInWindow(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || num.length <= 0 || size < +0) return res;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size - 1; i++) {
            while (!list.isEmpty() && num[i] > num[list.getLast()]) {
                list.removeLast();
            }
            list.addLast(i);
        }
        for (int i = size - 1; i < num.length; i++) {
            while (!list.isEmpty() && num[i] > num[list.getLast()]) {
                list.removeLast();
            }
            list.addLast(i);
            if (i - list.getFirst() + 1 > size) list.removeFirst();
            res.add(num[list.getFirst()]);
        }
        return res;
    }

    public static ArrayList<ArrayList<Integer>> FindContinuousSequenceBetter(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum < 3) return null;
        int start = 1, end = 2, midSum = (1 + sum) / 2, curSum = start + end;
        while (start < midSum) {
            if (curSum == sum) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    tmp.add(i);
                }
                res.add(tmp);
                end++;
                curSum += end;
            } else if (curSum < sum) {
                end++;
                curSum += end;
            } else {
                curSum -= start;
                start++;
            }
            System.out.println(111111);
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (sum <= 1) return list;
        for (int i = 1; i < (sum + 1) / 2; i++) {
            continousSum(sum, i, list);
        }
        return list;
    }

    public static void continousSum(int sum, int idx, ArrayList<ArrayList<Integer>> list) {
        int tmpSum = 0;
        ArrayList<Integer> tmpList = new ArrayList<>();
        while (tmpSum < sum) {
            tmpSum += idx;
            tmpList.add(idx++);
        }
        if (tmpSum == sum && tmpList.size() >= 2) list.add(tmpList);
    }

    public static int getUglyNumber2(int num) {
        if (num <= 0) return 0;
        int[] uglyNum = new int[num];
        uglyNum[0] = 1;
        int idx2 = 0, idx3 = 0, idx5 = 0, nextIdx = 1;
        while (nextIdx < num) {
            int min = Math.min(uglyNum[idx2] * 2, Math.min(uglyNum[idx3] * 3, uglyNum[idx5] * 5));
            uglyNum[nextIdx] = min;
            while (uglyNum[idx2] * 2 <= min) {
                idx2++;
            }
            while (uglyNum[idx3] * 3 <= min) {
                idx3++;
            }
            while (uglyNum[idx5] * 5 <= min) {
                idx5++;
            }
            nextIdx++;
        }
        return uglyNum[num - 1];
    }

    public static int getUglyNumber1(int num) {
        if (num <= 0) return 0;
        int count = 0;
        int idx = 0;
        while (count < num) {
            idx++;
            if (ifUglyNum(idx)) {
                count++;
            }
            System.out.println(count);
        }
        return idx;
    }

    public static boolean ifUglyNum(int num) {
        while (num % 2 == 0) {
            num = num / 2;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 4 == 0) {
            num = num / 4;
        }
        return num == 1;
    }

    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num.length < size || size <= 0) return list;
        LinkedList<Integer> idxQueue = new LinkedList<>();
        for (int i = 0; i < size - 1; i++) {
            while (!idxQueue.isEmpty() && num[i] > num[idxQueue.getLast()]) {
                idxQueue.removeLast();
            }
            idxQueue.addLast(i);
        }
        for (int i = size - 1; i < num.length; i++) {
            while (!idxQueue.isEmpty() && num[i] > num[idxQueue.getLast()]) {
                idxQueue.removeLast();
            }
            idxQueue.addLast(i);
            if (i - idxQueue.getFirst() + 1 > size) idxQueue.removeFirst();
            list.add(num[idxQueue.getFirst()]);
        }
        return list;
    }

    public static int longestSubstringWithoutDuplication(char[] ch) {
        int curLen = 0, maxLen = 0;
        int[] pos = new int[26];
        for (int i = 0; i < pos.length; i++)
            pos[i] = -1;

        for (int i = 0; i < ch.length; i++) {
            int prePos = pos[ch[i] - 'a'];
            if (prePos < 0 || i - prePos > curLen) {
                curLen++;
            } else {
                curLen = i - prePos;
                if (curLen > maxLen) maxLen = curLen;
            }
            pos[ch[i] - 'a'] = i;
        }
        if (curLen > maxLen) maxLen = curLen;
        return maxLen;

    }

    public static ArrayList<Integer> getMidNumFromFlow() {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int[] num = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            num[i] = Integer.valueOf(str[i]);
        }

        ArrayList<Integer> res = new ArrayList<>();
        TreeSet<Integer> min = new TreeSet<>();
        min.add(Integer.MIN_VALUE);
        TreeSet<Integer> max = new TreeSet<>();
        max.add(Integer.MAX_VALUE);

        for (int i = 0; i < str.length; i++) {
            if (((i + 1) & 1) == 1) {
                if (min.last() > num[i]) {
                    min.add(num[i]);
                    res.add(min.last());
                } else {
                    int tmp = num[i];
                    if (max.first() < num[i]) {
                        tmp = max.pollFirst();
                        max.add(num[i]);
                    }
                    min.add(tmp);
                    res.add(min.last());
                }
            } else {
                int tmp = num[i];
                if (min.last() < tmp) {
                    max.add(tmp);
                    res.add(min.last());
                } else {
                    tmp = min.pollLast();
                    min.add(num[i]);
                    max.add(tmp);
                    res.add(min.last());
                }
            }
        }
        return res;
    }

    public static void combination(char ch[]) {
        if (ch == null || ch.length == 0) return;
        ArrayList<Character> list = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();
        for (int i = 1; i <= ch.length; i++) {
            combinationHelper(ch, 0, i, list, res);
        }
        System.out.println();
    }

    public static void combinationHelper(char[] ch, int begin, int num, ArrayList<Character> list, ArrayList<String> res) {
        //从第begin个字符开始挑选num个放入list
        if (num == 0) {
            res.add(list.toString());
            return;
        }
        if (begin >= ch.length) return;

        list.add(ch[begin]);
        combinationHelper(ch, begin + 1, num - 1, list, res);
        list.remove(list.size() - 1);
        combinationHelper(ch, begin + 1, num, list, res);
    }

    public static boolean isPrime(int prime) {
        if (prime > 2 && prime % 2 == 0) return false;
        for (int devisor = 2; devisor <= prime / 2; devisor++) {
            if (prime % devisor == 0) return false;
        }
        return true;
    }

    public static int ZeroOnePackage(int[] v, int[] s, int c) {
        int[][] dp = new int[v.length + 1][c + 1];
        int max = 0;
        for (int i = 0; i < v.length + 1; i++)
            dp[i][0] = 0;
        for (int i = 0; i < c + 1; i++)
            dp[0][i] = 0;

        for (int i = 1; i < v.length + 1; i++) {
            for (int j = 1; j < c + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s[i - 1] <= j) {
                    if (dp[i - 1][j - s[i - 1]] + v[i - 1] > dp[i - 1][j])
                        dp[i][j] = dp[i - 1][j - s[i - 1]] + v[i - 1];
                }
            }
        }
        return dp[v.length][c];
    }

    public static void DidiPro2() {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int total = 0;
        int n = str.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = Integer.valueOf(str[i]);
            total = total + Integer.valueOf(str[i]);
        }
        int res = arrange(temp, -1, 0, total);
        System.out.println(res);
    }

    public static int arrange(int[] temp, int i, int j, int total) {
        if (j == total) return 1;
        int count = 0;
        for (int k = 0; k < 3; k++) {
            if (k != i && temp[k] > 0) {
                temp[k]--;
                count += arrange(temp, k, j + 1, total);
                temp[k]++;
            }
        }
        return count;
    }

    public static void DidiPro1() {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int num = strs.length - 1;
        String s1 = "qwertasdfgzxcv", s2 = "yuiophjklbnm";
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for (int i = 0; i < s1.length(); i++)
            set1.add(s1.charAt(i));
        for (int i = 0; i < s2.length(); i++)
            set2.add(s2.charAt(i));

        if (num <= 3) {
            for (int i = 1; i <= num; i++) {
                if (i != num) System.out.print(strs[i] + " ");
                else System.out.println(strs[i]);
            }
            return;
        }
        int res[] = new int[num];
        for (int i = 1; i <= num; i++) {
            res[i - 1] = getGrade(strs[0], strs[i], set1, set2);
        }

        int[] index = new int[3];
        for (int i = 0; i < 3; i++) {
            int max = Integer.MAX_VALUE;
            index[i] = 0;
            for (int j = 0; j < res.length; j++) {
                if (res[j] != -1 && max > res[j]) {
                    index[i] = j;
                    max = res[j];
                }
            }
            res[index[i]] = -1;
        }
        System.out.println(strs[index[0] + 1] + " " + strs[index[1] + 1] + " " + strs[index[2] + 1]);

    }

    public static int getGrade(String str1, String str2, Set<Character> set1, Set<Character> set2) {
        int max1 = str1.length();
        int max2 = str2.length();
        //建立数组，比字符长度大一个空间
        int[][] array = new int[max2 + 1][max1 + 1];
        for (int i = 0; i <= max1; i++) {
            array[0][i] = i * 3;
        }
        for (int j = 0; j <= max2; j++) {
            array[j][0] = j * 3;
        }

        for (int i = 1; i <= max1; i++) {
            for (int j = 1; j <= max2; j++) {
                array[j][i] = levenshtein(i, j, str1.charAt(i - 1), str2.charAt(j - 1), array, set1, set2);
            }
        }
        return array[max2][max1];
    }

    public static int levenshtein(int i, int j, char si, char sj, int[][] array, Set<Character> set1, Set<Character> set2) {
        int result = 0;
        if (i >= 1 && j >= 1) {
            int a = array[j - 1][i] + 3;//1;
            int b = array[j][i - 1] + 3;//1;
            int c = 0;
            if (si == sj) c = array[j - 1][i - 1];
            else {
                if ((set1.contains(si) && set1.contains(sj)) || (set2.contains(si) && set2.contains(sj)))
                    c = array[j - 1][i - 1] + 1;
                else c = array[j - 1][i - 1] + 2;
            }
            result = min(a, b, c);
        }
        return result;
    }

    public static int min(int a, int b, int c) {
        int temp = a < b ? a : b;
        return temp < c ? temp : c;
    }

    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        char[] ch = str.toCharArray();
        permutationProcess(ch, 0, list);
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    String tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
        return list;
    }

    public static void permutationProcess(char[] ch, int idx, ArrayList<String> list) {
        if (ch == null) return;
        if (idx == ch.length - 1) {
            if (list.contains(String.valueOf(ch))) {
                return;
            }
            list.add(String.valueOf(ch));
        } else {
            for (int i = idx; i < ch.length; i++) {
                char tmp = ch[i];
                ch[i] = ch[idx];
                ch[idx] = tmp;

                permutationProcess(ch, idx + 1, list);

                tmp = ch[i];
                ch[i] = ch[idx];
                ch[idx] = tmp;
            }
        }
    }

    public static int LCS(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] len = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            len[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            len[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) len[i][j] = len[i - 1][j - 1] + 1;
                else {
                    len[i][j] = (len[i][j - 1] > len[i - 1][j]) ? len[i][j - 1] : len[i - 1][j];
                }
            }
        }
        return len[n][m];
    }

    public static void permute(int[] array, int start, int[] res) {
        if (start == array.length) {  // 输出
            for (int i = 0; i < array.length - 1; i++) {
                res[0] += Math.abs(array[i] - array[i + 1]);
            }
        } else for (int i = start; i < array.length; ++i) {
            swap1(array, start, i);  //  交换元素
            permute(array, start + 1, res);  //交换后，再进行全排列算法
            swap1(array, start, i);  //还原成原来的数组，便于下一次的全排列
        }
    }

    private static void swap1(int[] array, int s, int i) {
        int t = array[s];
        array[s] = array[i];
        array[i] = t;
    }

    public static void NetEase3() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            String[] strs = sc.nextLine().split(" ");
            for (int j = 0; j < strs.length; j++)
                arr[i][j] = Integer.valueOf(strs[j]);
        }

        TableStruct[] ts = new TableStruct[n * (n - 1)];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double v = getValueNetEase2(arr, i, j);
                    ts[count++] = new TableStruct(i, j, v);
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < n; i++) {
            double value = ts[i * (n - 1)].val;
            int idx = i * (n - 1);
            for (int j = i * (n - 1); j < i * (n - 1) + (n - 1); j++) {
                if (ts[j].val < 20 && value > ts[j].val) {
                    idx = j;
                    value = ts[j].val;
                }
            }
            if (idx != -1) {
                // if (i != n-1)
                System.out.println((ts[idx].id1 + 1) + " " + (ts[idx].id2 + 1) + " " + df.format(ts[idx].val));
                //else
                // System.out.print((ts[idx].id1+1)+ " "+(ts[idx].id2+1)+" "+df.format(ts[idx].val));
            }
        }

    }

    static class TableStruct {
        int id1, id2;
        double val;

        TableStruct(int id1, int id2, double val) {
            this.id1 = id1;
            this.id2 = id2;
            this.val = val;
        }
    }

    public static double getValueNetEase2(int[][] arr, int id1, int id2) {
        double res = 0.0;
        res = Math.pow(arr[id1][1] - arr[id2][1], 2) + Math.pow(arr[id1][2] - arr[id2][2], 2) + Math.pow(arr[id1][3] - arr[id2][3], 2);
        res = Math.sqrt(res);
        return res;
    }

    public static int iQiyi1() {
        Scanner sc = new Scanner(System.in);
        char[] ch = sc.nextLine().toCharArray();
        int n = ch.length;
        int[] num = new int[n];
        for (int i = 0; i < n; i++)
            num[i] = Integer.valueOf(ch[i]) - 48;

        int pre = 0, post = 0;
        for (int i = 0; i < 3; i++) {
            pre += num[i];
            post += num[i + 3];
        }

        if (pre == post) return 0;
        else {
            int diff = Math.abs(pre - post);
            boolean flag = false;
            if (pre > post) flag = true;  //left > right
            int count = 0;
            while (diff != 0) {
                count++;
                if (flag) {
                    int left = iQiyi1Helper(num, 0, false);  //最大位置
                    int right = iQiyi1Helper(num, 3, true);  //最小位置
                    if (num[left] > (9 - num[right])) {
                        if (num[left] >= diff) return count;
                        diff -= num[left];
                        num[left] = 0;
                    } else {
                        if ((9 - num[right]) >= diff) return count;
                        diff -= (9 - num[right]);
                        num[right] = 9;
                    }
                } else {
                    int left = iQiyi1Helper(num, 0, true);  // min index
                    int right = iQiyi1Helper(num, 3, false);//max index
                    if (num[right] > (9 - num[left])) {
                        if (num[right] >= diff) return count;
                        diff -= num[right];
                        num[right] = 0;
                    } else {
                        if (9 - num[left] >= diff) return count;
                        diff -= (9 - num[left]);
                        num[left] = 9;
                    }
                }
            }
            return count;
        }
    }

    public static int iQiyi1Helper(int[] num, int start, boolean flag) {
        int index = 0;
        if (flag) { //true 寻找最小
            int min = Integer.MAX_VALUE;
            for (int i = start; i < start + 3; i++) {
                if (min > num[i]) {
                    min = num[i];
                    index = i;
                }
            }
        } else {//false 寻找最大
            int max = Integer.MIN_VALUE;
            for (int i = start; i < start + 3; i++) {
                if (max < num[i]) {
                    max = num[i];
                    index = i;
                }
            }
        }
        return index;
    }

    public static int iQiyi2() {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = Integer.valueOf(str[0]), m = Integer.valueOf(str[1]), p = Integer.valueOf(str[2]);
        int[] food = new int[n];
        str = sc.nextLine().split(" ");
        for (int i = 0; i < str.length; i++)
            food[i] = Integer.valueOf(str[i]);

        for (int i = 0; i < m; i++) {
            String tmp = sc.nextLine();
            char ch = tmp.charAt(0);
            int idx = Integer.valueOf(tmp.split(" ")[1]);
            if (ch == 'A') food[idx - 1]++;
            else food[idx - 1]--;
        }

        int target = food[p - 1], res = 1;
        for (int i = 0; i < food.length; i++) {
            if (food[i] > target) res++;
        }
        return res;
    }

    public static void Xunlei2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] vis = new int[1000005];
        int ans1 = 0, ans2 = 0;


        long m = (long) Math.sqrt(n + 0.5);
        long a, b, c;
        for (long i = 1; i <= m; i++) {
            for (long j = i + 1; j <= m; j += 2) {
                if (gcd(j, i) == 1) {
                    a = j * j - i * i;
                    b = 2 * i * j;
                    c = i * i + j * j;
                    if (c <= n) {
                        ans1++;
                        vis[(int) a] = vis[(int) b] = vis[(int) c] = 1;
                    }
                    for (int k = 2; c * k <= n; k++)
                        vis[(int) (k * a)] = vis[(int) (k * b)] = vis[(int) (k * c)] = 1;
                }
            }
        }
        for (int i = 1; i <= n; i++)
            if (vis[i] != 0) ans2++;
        System.out.println(ans1++);
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void EastRichHelp(int[][] a, int start, int n, ArrayList<Integer> res) {
        //up to down
        for (int i = start; i <= n - start - 1; i++) {
            res.add(a[i][n - start - 1]);
        }
        //right to left
        for (int i = n - start - 2; i >= start; i--) {
            res.add(a[n - start - 1][i]);
        }
        //down to up
        for (int i = n - start - 2; i >= start; i--) {
            res.add(a[i][start]);
        }
        //left to right
        for (int i = start + 1; i <= n - start - 2; i++) {
            res.add(a[start][i]);
        }
    }

    public static int getMaxDiff(int[] a) {
        int max = a[0], res = 0;
        for (int i = 1; i < a.length; i++) {
            res = (max - a[i]) > res ? (max - a[i]) : res;
            max = max > a[i] ? max : a[i];
        }
        return res;
    }

    public static int MaxSubSum(int[] arr) {
        int i;
        int MaxSum = 0;
        int ThisSum = 0;
        for (i = 0; i < arr.length; i++) {
            ThisSum += arr[i];
            if (ThisSum > MaxSum) MaxSum = ThisSum;
        /*如果累加和出现小于0的情况，
           则和最大的子序列肯定不可能包含前面的元素，
           这时将累加和置0，从下个元素重新开始累加  */
            else if (ThisSum < 0) ThisSum = 0;
        }
        return MaxSum;

    }

    public static int findOnce(int[] a, int times) {
        int n = a.length;
        int[] bitCount = new int[32];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 32; j++) {
                bitCount[j] += ((a[i] >> j) & 1);
            }
        }
        int res = 0;
        for (int i = 0; i < bitCount.length; i++) {
            if (bitCount[i] % times != 0) res += (1 << i);
        }
        return res;
    }


    public static int KuaishouPro3() {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = Integer.valueOf(str[0]), m = Integer.valueOf(str[1]);
        if (n == -1 || m == -1) return 0;
        str = sc.nextLine().split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(Integer.valueOf(str[i]));

        int count = 0;

        for (int i = 0; i < m; i++) {
            int tmp = KuaishouPro3Helper(list);
            if (tmp > 0) count += tmp;
        }
        return count;
    }

    public static int KuaishouPro3Helper(ArrayList<Integer> list) {
        if (list.size() == 1) {
            int tmp = list.get(0);
            list.remove(0);
            return tmp;
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = list.get(i);
        int MaxSum = 0, ThisSum = 0, start = 0, end = arr.length - 1, tmpStart = 0, tmpEnd = 0, tmpMax = 0;
        for (int i = 0; i < arr.length; i++) {
            ThisSum += arr[i];
            tmpEnd = i;
            if (ThisSum > tmpMax) {
                tmpMax = ThisSum;
            } else if (ThisSum < 0) {
                ThisSum = 0;
                tmpStart = i + 1;
            }
            if (tmpMax > MaxSum) {
                MaxSum = tmpMax;
                start = tmpStart;
                end = tmpEnd;
            }
        }
        for (int i = end; i >= start; i--)
            list.remove(i);
        return MaxSum;
    }

    public static int KuaishouPro2() {
        String[] str = (new Scanner(System.in)).nextLine().split(",");
        int num = str.length;
        int[] cost = new int[num];
        for (int i = 0; i < str.length; i++)
            cost[i] = Integer.valueOf(str[i]);
        int dp[] = new int[1024];
        dp[0] = 0;
        dp[1] = 0;
        if (cost.length == 0 || cost == null) return 0;
        if (cost.length == 1) return cost[0];
        if (cost.length == 2) return Math.min(cost[0], cost[1]);

        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }

    public static int NumberOfIP() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (s.length() <= 3 || s == null) return 0;
        if (s.length() == 4) return 1;
        int[] res = new int[1];
        helper(s, res, 0, 1);
        return res[0];
    }

    public static void helper(String s, int[] res, int seq, int start) {
        if (seq == 4) {
            int tmp = Integer.valueOf(s.substring(start));
            if (tmp >= 0 && tmp <= 255) res[0] += 1;
            return;
        }
        for (int i = start + 1; i < start + 3; i++) {
            if (i >= s.length()) continue;
            int tmp = Integer.valueOf(s.substring(start, i));
            if (tmp >= 0 && tmp <= 255) {
                helper(s, res, seq + 1, i);
            }
        }

    }

    public static boolean ifIllegal(int[] data) {
        int count = 0;//标记有几个字符
        for (int num : data) {
            if (count == 0) {
                if ((num & 0x80) == 0) {
                    count = 0;
                } else if ((num & 0xe0) == 0xc0) {
                    count = 1;
                } else if ((num & 0xf0) == 0xe0) {
                    count = 2;
                } else if ((num & 0xf8) == 0xf0) {
                    count = 3;
                } else {
                    return false;
                }
            } else {
                if ((num & 0xc0) != 0x80) return false;
                count--;
            }
        }
        return count == 0;
    }

    public static int NumOfDepartment() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        if (n == 0) return 0;
        char[][] ch = new char[n][n];
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++)
                ch[i][j] = s[j].charAt(0);
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ch[i][j] == '1') {
                    count++;
                    FindConnected(ch, i, j, n);
                }
            }
        }
        return count;
    }

    public static void FindConnected(char[][] ch, int i, int j, int n) {
        if (ch[i][j] == '0') return;
        ch[i][j] = '0';
        if (i > 0) FindConnected(ch, i - 1, j, n);
        if (j > 0) FindConnected(ch, i, j - 1, n);
        if (i < n - 1) FindConnected(ch, i + 1, j, n);
        if (j < n - 1) FindConnected(ch, i, j + 1, n);
    }

    public static int MaxLongestSubUndup() {
        String s = (new Scanner(System.in)).nextLine();
        int[] showLoc = new int[26];

        int start = 0, maxLen = 0;
        for (int i = 0; i < 26; i++) {
            showLoc[i] = -1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (showLoc[s.charAt(i) - 'a'] >= start) {
                maxLen = maxLen > (i - start) ? maxLen : (i - start);
                start = showLoc[s.charAt(i) - 'a'] + 1;
            }

            showLoc[s.charAt(i) - 'a'] = i;
        }
        return maxLen > (s.length() - start) ? maxLen : (s.length() - start);
    }

    public static void killer() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        ArrayList<Integer> list = new ArrayList<>();
        String[] str = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++)
            list.add(Integer.valueOf(str[i]));
        boolean ifKill = true;
        int nights = 0;
        while (ifKill) {
            ifKill = false;
            int end = list.size() - 1, start = list.size() - 2, oldRight = list.get(end);
            while (start >= 0) {
                if (list.get(start) > list.get(end)) {
                    list.remove(end);
                    ifKill = true;
                }
                --start;
                --end;
            }
            if (ifKill) nights++;
            if (!ifKill || list.size() <= 1) break;
        }
        System.out.println(nights);
    }

    static class Node {
        //次数
        int times;
        String value;

        Node(String str, int times) {
            this.times = times;
            value = str;
        }
    }

    /*static class myComparator implements Comparator {
        public int compare(Node o1, Node o2) {
            if (o1.times == o2.times) {
                return o1.value.compareTo(o2.value);
            }
            if (o1.times > o2.times) {
                return 1;
            }else {
                return -1;
            }
        }
    }*/
    public static String decodeString1(String s) {
        String res = "";
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                int num = s.charAt(i) - '0';
                i++;
                while (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    num = num * 10 + (s.charAt(i++) - '0');
                }
                i--;
                numStack.push(num);
            } else if (s.charAt(i) == '[') {
                strStack.push(res);
                res = "";
            } else if (s.charAt(i) == ']') {
                StringBuilder sb = new StringBuilder(strStack.pop());
                int count = numStack.pop();
                while (count > 0) {
                    sb.append(res);
                    count--;
                }
                res = sb.toString();
            } else {
                res += s.charAt(i);
            }

        }
        return res;
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0 || intervals == null) return res;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.size(); i++) {
            Interval temp = intervals.get(i);
            if (temp.start > temp.end) continue;
            if (temp.start <= min) min = temp.start;
            if (temp.end >= max) max = temp.end;
        }
        int[] flag = new int[max - min + 1];
        for (int i = 0; i < intervals.size(); i++) {
            Interval temp = intervals.get(i);
            if (temp.start > temp.end) continue;
            for (int j = temp.start - min; j < temp.end - min; j++)
                flag[j] = 1;
        }
        int s = 0, e = 0;
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == 1) {
                s = i;
                while (flag[i] == 1) {
                    i++;
                }
                e = --i;
                res.add(new Interval(s + min, e + min + 1));
            } else continue;
        }
        return res;
    }

    public static int indexFor(int h, int length) {
        return h & (length - 1);  // 作用等价于取模运算，但这种方式效率更高
    }

    public static int jumpII(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int step = 0;
        int nextMax = 0;
        int farthest = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == nextMax) {
                if (farthest <= nextMax) return -1;
                nextMax = farthest;
                step++;
            }
        }
        return step;
    }

    public static int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int length = nums[i];
            while (length > 0) {
                if (length + i < dp.length) dp[i + length] = Math.min(dp[i] + 1, dp[i + length]);
                length--;
            }
        }
        return dp[n - 1];
    }

    public static String decodeString(String s) {
        String res = "";
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                int num = s.charAt(i) - '0';
                i++;
                while (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    num = num * 10 + (s.charAt(i++) - '0');
                }
                i--;
                numStack.push(num);
            } else if (s.charAt(i) == '[') {
                strStack.push(res);
                res = "";
            } else if (s.charAt(i) == ']') {
                StringBuilder sb = new StringBuilder(strStack.pop());
                int count = numStack.pop();
                while (count > 0) {
                    sb.append(res);
                    count--;
                }
                res = sb.toString();
            } else {
                res += s.charAt(i);
            }

        }
        return res;
    }

    public static String decodeStringSolver(String s, int start) {
        if (start >= s.length()) return "";
        if (!(s.charAt(start) > '0' && s.charAt(start) < '9')) return s.substring(start, start + 1);
        return "";
    }

    public static int solver(int[] arr, int left, int right) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = left; i <= right; i++) {
            set.add(arr[i]);
        }
        return set.size();
    }

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker, boolean f) {
        int max = 0;
        for (int w : worker) {
            max = Math.max(max, w);
        }
        int[] wp = new int[max + 1];
        for (int i = 0; i < difficulty.length; i++) {
            if (difficulty[i] > max) continue;
            wp[difficulty[i]] = Math.max(wp[difficulty[i]], profit[i]);
        }
        for (int i = 0; i < wp.length - 1; i++) {
            wp[i + 1] = Math.max(wp[i + 1], wp[i]);
        }
        int ret = 0;
        for (int w : worker) {
            ret += wp[w];
        }
        return ret;
    }

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Point[] jobs = new Point[difficulty.length];
        for (int i = 0; i < jobs.length; i++)
            jobs[i] = new Point(difficulty[i], profit[i]);
        Arrays.sort(jobs, (a, b) -> a.x - b.x);
        Arrays.sort(worker);
        int sum = 0, idx = 0, max = 0;
        for (int i = 0; i < worker.length; i++) {
            int w = worker[i];
            while (idx < difficulty.length && w >= jobs[idx].x) {
                max = Math.max(max, jobs[idx++].y);
            }
            sum += max;
        }
        return sum;
    }

    private static int maxWorkToDo(int[] difficulty, int[] profit, int workerAbility) {
        for (int i = difficulty.length - 1; i >= 0; i--) {
            if (workerAbility >= difficulty[i]) return i;
        }
        return -1;
        /*int low = 0, high = difficulty.length, mid;
        while (low < high){
            mid = (low + high)/2;
            if(difficulty[mid] > workerAbility){
                high = mid;
            }
            else if(difficulty)
        }*/
    }

    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static int findMaxMin(int[] num, int low, int high, int tarIdx) {
        if (low >= high) return -1;
        int cur = Integer.MAX_VALUE, curIdx = -1;
        for (int i = low; i < high; i++) {
            if (num[i] > num[tarIdx] && num[i] < cur) {
                curIdx = i;
                cur = num[i];
            }
        }
        return curIdx;
    }

    public static int longList(int[] array) {
        int[] dp = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i] && (dp[j] + 1 > dp[i])) dp[i] = dp[j] + 1;
            }
        }
        int max = 0;
        for (int k = 0; k < dp.length; k++) {
            if (dp[k] > max) max = dp[k];
        }
        return max;
    }

    public static int minCut(String s) {
        if (s == null || s.length() == 0) return 0;

        System.out.println(Thread.currentThread().getName());
        return 0;
    }

    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        ArrayList<int[]> res = new ArrayList<>();
        int n1 = nums1.length, n2 = nums2.length, maxSum = Integer.MIN_VALUE;
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return res;
        k = Math.min(n1 * n2, k);
        boolean visit[][] = new boolean[nums1.length][nums2.length];
        Queue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] i, int[] j) {
                return (nums1[i[0]] + nums2[i[1]] - (nums1[j[0]] + nums2[j[1]]));
            }
        });

        heap.add(new int[]{0, 0});
        visit[0][0] = true;

        while (!heap.isEmpty() && res.size() < k) {
            int d[] = heap.poll();
            res.add(new int[]{nums1[d[0]], nums2[d[1]]});

            if (d[1] + 1 < nums2.length && visit[d[0]][d[1] + 1] == false) {
                heap.add(new int[]{d[0], d[1] + 1});
                visit[d[0]][d[1] + 1] = true;
            }
            if (d[0] + 1 < nums1.length && visit[d[0] + 1][d[1]] == false) {
                heap.add(new int[]{d[0] + 1, d[1]});
                visit[d[0] + 1][d[1]] = true;
            }
        }
        return res;
    }

    public static int flipgame(int[] fronts, int[] backs) {
        int[] p = new int[2001];
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i]) p[fronts[i]] = -1;
            else {
                if (p[fronts[i]] != -1) p[fronts[i]] = 1;
                if (p[backs[i]] != -1) p[backs[i]] = 1;
            }
        }
        for (int i = 0; i < p.length; i++) {
            if (p[i] == 1) return i;
        }
        return 0;
    }

    public static int findRadius(int[] houses, int[] heaters) {
        int min = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        for (int i = 0; i < houses.length; i++) {
            int left = 0, right = heaters.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (houses[i] > heaters[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (right == 0) min = Math.max(min, Math.abs(heaters[right] - houses[i]));
            else {
                int s1 = Math.abs(heaters[right] - houses[i]);
                int s2 = Math.abs(heaters[right - 1] - houses[i]);
                min = Math.max(min, Math.min(s1, s2));
            }
        }
        return min;
    }

    public static boolean isPopOrder(int[] pushArr, int[] popArr) {
        int pushIdx = 0, popIdx = 0;
        int length = pushArr.length;
        boolean flag = false;
        if (pushArr == null || popArr == null || popArr.length != pushArr.length) return false;
        while (popIdx < length) {
            Stack<Integer> stack = new Stack<>();
            while (popIdx < popArr.length) {
                while (stack.isEmpty() || stack.peek() != popArr[popIdx]) {
                    if (pushIdx >= length) break;
                    stack.push(pushArr[pushIdx++]);
                }
                if (!stack.isEmpty() && stack.peek() != popArr[popIdx]) break;
                stack.pop();
                popIdx++;
            }
            if (stack.isEmpty() && popIdx == length) flag = true;
        }
        return flag;
    }

    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        int idx = s.length() - 1, len = 0;
        while (idx >= 0 && s.charAt(idx) == ' ') idx--;
        while (idx >= 0 && s.charAt(idx) != ' ') {
            len++;
            idx--;
        }
        return len;
    }

    public static int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        int[] cnt = new int[26];
        for (char c : tasks)
            cnt[c - 'A'] += 1;
        Arrays.sort(cnt);
        int len = tasks.length, idx = 25, max = cnt[idx];
        while (idx >= 0 && cnt[idx] == max) idx--;
        return Math.max(len, (max - 1) * (n + 1) + 25 - idx);
    }

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < col; i++)
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        for (int i = 1; i < row; i++)
            dp[i][0] = dp[i - 1][0] + grid[i][0];

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }
        return dp[row - 1][col - 1];
    }

    public static int MaxDistanceMin(int[] num, int n) {
        int maxd = -1;
        int minMaxd = Integer.MAX_VALUE;
        int[] d = new int[n - 1];
        for (int i = 1; i <= n - 1; i++) {
            d[i - 1] = num[i] - num[i - 1];
            maxd = Math.max(maxd, d[i - 1]);
        }
        for (int i = 0; i < d.length - 1; i++) {
            int subd = d[i] + d[i + 1];
            if (subd > maxd) {
                minMaxd = Math.min(minMaxd, subd);
            } else {
                minMaxd = Math.min(minMaxd, maxd);
            }
        }
        return minMaxd;
    }

    public static int countWays(int n) {
        int arr[] = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n] % 1000000007;
    }

    public static int countStars(String s) {
        int count = 0;
        int first = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') count++;
        }
        return count;
    }

    public static String removeStars(String s) {
        int first = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                if (first == -1) {
                    first = i;
                    break;
                }
            }
        }
        if (first == -1) return s;
        return s.substring(0, first);
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int target = 0;
        if ((sum & 1) == 0) target = sum >> 1;
        else return false;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }

    public static int numSquares(int n) {
        if (ifSquares(n)) return 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = dp[1] = 1;
        for (int i = 0; i <= n; i++) {
            if (ifSquares(i)) dp[i] = 1;
            else {
                for (int j = 1; j <= i / 2; j++) {
                    dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
                }
            }
        }
        return dp[n];
    }

    public static boolean ifSquares(int n) {
        int a = (int) Math.sqrt(n);
        if (a * a == n) return true;
        return false;
    }

    public static void MergeSort(int[] a, int low, int high) {
        if (low < high) {
            int mid = (high + low) / 2;
            MergeSort(a, low, mid);
            MergeSort(a, mid + 1, high);
            Merge(a, low, mid, high);
        }
    }

    public static void Merge(int[] a, int low, int mid, int high) {
        int[] tmp = new int[high - low + 1];
        int lowIdx = low, highIdx = mid + 1, index = 0;
        while (lowIdx <= mid && highIdx <= high) {
            if (a[lowIdx] < a[highIdx]) tmp[index++] = a[lowIdx++];
            else tmp[index++] = a[highIdx++];
        }
        while (lowIdx <= mid) {
            tmp[index++] = a[lowIdx++];
        }
        while (highIdx <= high) {
            tmp[index++] = a[highIdx++];
        }
        for (int i = 0; i < tmp.length; i++) {
            a[i + low] = tmp[i];
        }
    }

    public static void QuickSort(int[] a, int low, int high) {
        if (low < high) {
            int idx = quick(a, low, high);
            QuickSort(a, low, idx - 1);
            QuickSort(a, idx + 1, high);
        }
    }

    public static int quick(int[] a, int low, int high) {
        int i = low, j = high;
        while (i < j) {
            while (a[j] >= a[low] && j > i) {
                j--;
            }
            while (a[i] <= a[low] && j > i) {
                i++;
            }
            if (i != j) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            } else {
                int tmp = a[low];
                a[low] = a[j];
                a[j] = tmp;
            }
        }
        return j;
    }

    public static int KMP(String patterns, String str) {
        int n = patterns.length(), m = str.length();
        char[] T = patterns.toCharArray();
        char[] P = str.toCharArray();
        int[] next = getNext(str);
        for (int i = 0, q = 0; i < n; i++) {
            while (q > 0 && P[q] != T[i]) q = next[q - 1];
            if (P[q] == T[i]) q++;
            if (q == m) return i - m + 1;
        }
        return -1;
    }

    private static int[] getNext(String str) {
        int n = str.length();
        char[] ch = str.toCharArray();
        int[] next = new int[n];
        next[0] = 0;
        int k = 0;
        for (int i = 1; i < str.length(); i++) {
            while (k > 0 && ch[i] != ch[k]) k = next[k - 1];
            if (ch[i] == ch[k]) k++;
            next[i] = k;
        }
        return next;
    }

    public static int HuaWeiSecondProblem() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int maxLength = 1;
        char maxChar = str.charAt(0);
        int length = 1;
        char temp = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == temp) {
                length++;
            } else {
                length = 1;
                temp = str.charAt(i);
            }
            if (length > maxLength) {
                maxLength = length;
                maxChar = temp;
            } else if (length == maxLength && maxChar > temp) {
                maxLength = length;
                maxChar = temp;
            }
        }
        for (int i = 0; i < length; i++) {
            System.out.print(maxChar);
        }
        return 0;
    }

    public static int minDistances2(int[] arr, int num) {//邮局选址
        if (arr == null || num == 0 || arr.length < num) {
            return 0;
        }
        int[][] w = new int[arr.length + 1][arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                w[i][j] = w[i][j - 1] + arr[j] - arr[(i + j) / 2];
            }
        }
        int[][] dp = new int[num][arr.length];
        int[][] s = new int[num][arr.length];
        for (int j = 0; j != arr.length; j++) {
            dp[0][j] = w[0][j];
            s[0][j] = 0;
        }
        int minK = 0, maxK = 0, cur = 0;
        for (int i = 1; i < num; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                minK = s[i - 1][j];
                maxK = j == arr.length - 1 ? arr.length - 1 : s[i][j + 1];
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = minK; k <= maxK; k++) {
                    cur = dp[i - 1][k] + w[k + 1][j];
                    if (cur <= dp[i][j]) {
                        dp[i][j] = cur;
                        s[i][j] = k;
                    }
                }
            }
        }
        return dp[num - 1][arr.length - 1];
    }

    public static int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int[] res = new int[2];
        boolean[] flag = new boolean[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (!flag[nums[i]]) {
                flag[nums[i]] = true;
            } else res[0] = nums[i];
        }
        for (int i = 1; i < flag.length; i++) {
            if (!flag[i]) {
                res[1] = i;
                break;
            }
        }
        return res;
    }

    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) return 0;
        int time = 0, n = timeSeries.length, last = 0;
        for (int i = 0; i < n - 1; i++) {
            if ((timeSeries[i] + duration) <= timeSeries[i + 1]) {
                time = time + duration;
            } else {
                int k = timeSeries[i + 1] - timeSeries[i];
                time = time + k;
            }
        }
        return time;
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) return 0;
        int n = prices.length;
        int[] sell = new int[n];
        int[] buy = new int[n];
        buy[0] = -prices[0];
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(sell[0], prices[1] - prices[0]);
        for (int i = 2; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[n - 1];
    }

    public static int maxCoins(int[] nums) { //unblieveable
        int n = nums.length;
        int[] a = new int[n + 2];
        for (int i = 0; i < n; i++)
            a[i + 1] = nums[i];
        a[0] = a[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n - k + 1; i++) {
                int j = i + k - 1;
                for (int x = i; x <= j; x++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][x - 1] + a[i - 1] * a[x] * a[j + 1] + dp[x + 1][j]);
                }
            }
        }
        return dp[1][n];
    }

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int res = dpMax[0];
        for (int i = 1; i < nums.length; i++) {
            int min = dpMin[i - 1] * nums[i];
            int max = dpMax[i - 1] * nums[i];
            if (min > max) {
                int tmp = min;
                min = max;
                max = tmp;
            }
            dpMax[i] = max > nums[i] ? max : nums[i];
            dpMin[i] = min > nums[i] ? nums[i] : min;
            res = res > dpMax[i] ? res : dpMax[i];
        }
        return res;
    }

    public static void sortColors(int[] nums) {
        int red = 0, blue = nums.length - 1;
        for (int i = 0; i <= blue; i++) {
            if (nums[i] == 0) {
                int tmp = nums[i];
                nums[i] = nums[red];
                nums[red] = tmp;
                red++;
            } else if (nums[i] == 2) {
                int tmp = nums[i];
                nums[i] = nums[blue];
                nums[blue] = tmp;
                blue--;
                i--;
            }
            System.out.println(111);
        }
    }

    public static int lengthOfLIS(int[] nums) {
        int len = 0;
        if (nums.length == 0 || nums == null) return 0;
        if (nums.length == 1) return 1;
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            int v = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    v = v > dp[j] ? v : dp[j] + 1;
                }
            }
            dp[i] = v;
        }
        for (int i = 0; i < dp.length; i++) {
            len = len > dp[i] ? len : dp[i];
        }
        return len + 1;
    }

    public static int longestConsecutive(int[] nums) {
        int len = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
                int low = num - 1, high = num + 1;
                while (set.contains(low)) {
                    set.remove(low);
                    low--;
                }
                while (set.contains(high)) {
                    set.remove(high);
                    high++;
                }
                int tmp = high - low - 1;
                len = len > tmp ? len : tmp;
            }
        }
        return len;
    }

    public static int longestConsecutive1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            max = max > nums[i] ? max : nums[i];
            min = min > nums[i] ? nums[i] : min;
        }

        BitSet tmp = new BitSet(max - min + 1);
        for (int i = 0; i < nums.length; i++) {
            tmp.set(nums[i] - min, true);
        }

        int len = 0, tmpLen = 0;
        int idx = 1;
        while (idx < tmp.size()) {
            tmpLen = 0;
            while (idx < tmp.size() && tmp.get(idx) == true) {
                tmpLen++;
                idx++;
            }
            idx++;
            len = len > tmpLen ? len : tmpLen;
        }
        return len;
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                int tmp = matrix[i][j];

                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = tmp;
            }
        }
    }

    public static String sortString(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length - 1; i++) {
            for (int j = i; j < c.length; j++) {
                char tmp = c[i];
                c[i] = c[j];
                c[j] = c[i];
            }
        }
        return String.valueOf(c);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            if (p == null && q == null) return true;
            else return false;
        }
        if (p.val == q.val) return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        else return false;
    }

    public static int rob(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> oddQueue = new ArrayDeque<>();
        Queue<TreeNode> evenQueue = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();

        oddQueue.add(root);
        while (oddQueue.size() != 0 || evenQueue.size() != 0) {
            int oddSum = 0, evenSum = 0;

            if (oddQueue.size() > 0) {
                while (oddQueue.size() > 0) {
                    TreeNode tmp = oddQueue.poll();
                    oddSum += tmp.val;
                    if (tmp.left != null) evenQueue.add(tmp.left);
                    if (tmp.right != null) evenQueue.add(tmp.right);
                }
                list.add(oddSum);
            }

            if (evenQueue.size() > 0) {
                while (evenQueue.size() > 0) {
                    TreeNode tmp = evenQueue.poll();
                    evenSum += tmp.val;
                    if (tmp.left != null) oddQueue.add(tmp.left);
                    if (tmp.right != null) oddQueue.add(tmp.right);
                }
                list.add(evenSum);
            }


        }

        System.out.println(list.size());

        return 0;
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n < 2) return nums[0];
        if (n <= 3) return nums[0] + nums[2] > nums[1] ? nums[0] + nums[2] : nums[1];
        int[] res = new int[n];
        res[0] = nums[0];
        res[1] = nums[1];
        res[2] = nums[2] + nums[0];
        for (int i = 3; i < nums.length; i++) {
            res[i] = res[i - 2] > res[i - 3] ? res[i - 2] : res[i - 3];
            res[i] += nums[i];

        }
        return res[n - 1] > res[n - 2] ? res[n - 1] : res[n - 2];
    }

    static int round(float x) {
        return (int) (x + 0.5);
    }

    public static long getUnsignedInt(int data) {
        return data & 0x0FFFFFFFF;
    }

    public static int hash(int[] k, int capacity_) {
        double r = 0;
        for (int i = 0; i < 2; i++) {
            r += k[i];
            r *= 1664525;
            r %= capacity_;
        }
        return (int) r % capacity_;
    }

    public void Xin1() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] strs = line.split(" ");
        int m = Integer.valueOf(strs[0]), n = Integer.valueOf(strs[1]);
        Map<Character, HashSet<Character>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            strs = sc.nextLine().split(" ");
            char head = strs[0].charAt(0);
            for (int j = 1; j < strs.length; j++) {
                char c = strs[j].charAt(0);
                if (c == '*') continue;
                else if (!map.containsKey(head)) {
                    HashSet<Character> set = new HashSet<>();
                    set.add(c);
                    map.put(head, set);
                } else {
                    HashSet<Character> set = map.get(head);
                    set.add(c);
                    map.put(head, set);
                }
            }
        }
        Set<Character> res = new HashSet<>();
        for (char key : map.keySet()) {
            HashSet<Character> set = map.get(key);
            HashSet<Character> tmpSet = new HashSet<>();
            tmpSet.addAll(set);
            Iterator<Character> it = tmpSet.iterator();
            while (it.hasNext()) {
                char tmp = it.next();
                if (map.containsKey(tmp)) {
                    set.addAll(map.get(tmp));
                }
            }
            map.put(key, set);
            if (set.size() >= n) res.add(key);
        }
        Iterator<Character> it = res.iterator();
        while (it.hasNext()) System.out.print(it.next() + " ");
    }

    public int diameterOfBinaryTree(TreeNode root) {
        int[] res = new int[1];
        diameterOfBinaryTreeHelper(root, res);
        return res[0];
    }

    public void diameterOfBinaryTreeHelper(TreeNode root, int[] res) {
        if (root == null) return;
        int left = depthOfTree(root.left);
        int right = depthOfTree(root.right);
        res[0] = res[0] > (left + right) ? res[0] : (left + right);
        diameterOfBinaryTreeHelper(root.left, res);
        diameterOfBinaryTreeHelper(root.right, res);
    }

    public int depthOfTree(TreeNode root) {
        if (root == null) return 0;
        int left = depthOfTree(root.left) + 1;
        int right = depthOfTree(root.right) + 1;
        return left > right ? left : right;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        subsetsHelper(nums, 0, lists, temp);
        return lists;
    }

    public void subsetsHelper(int[] nums, int idx, List<List<Integer>> lists, List<Integer> temp) {
        lists.add(new ArrayList<>(temp));
        for (int i = idx; i < nums.length; i++) {
            temp.add(nums[i]);
            subsetsHelper(nums, i + 1, lists, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        permuteHelper(nums, 0, res);
        return res;
    }

    public void permuteHelper(int[] nums, int i, List<List<Integer>> res) {
        if (i == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                list.add(nums[j]);
            }
            res.add(list);
        }
        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            permuteHelper(nums, i + 1, res);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int majorityElement(int[] nums) {
        int tmp = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                tmp = nums[i];
                count++;
            } else if (nums[i] == tmp) {
                count++;
            } else {
                count--;
            }
        }
        return tmp;
    }

    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        convert(root);
        return root;
    }

    int convertSum = 0;

    public void convert(TreeNode cur) {
        if (cur == null) return;
        convert(cur.right);
        cur.val += convertSum;
        convertSum = cur.val;
        convert(cur.left);
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) map.put(nums[i], map.get(nums[i]) + 1);
            else map.put(nums[i], 1);
        }
        for (int key : map.keySet()) {
            int idx = map.get(key);
            if (bucket[idx] == null) {
                bucket[idx] = new ArrayList<>();
                bucket[idx].add(key);
            } else bucket[idx].add(key);
        }

        for (int i = bucket.length - 1; i >= 0 && list.size() < k; i--) {
            if (bucket[i] != null) list.addAll(bucket[i]);
        }
        return list;
    }

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        //pre
        res[0] = 1;
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        //pose
        int tmp = 1;
        for (int i = res.length - 2; i >= 0; i--) {
            tmp *= nums[i + 1];
            res[i] *= tmp;
        }
        return res;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int max = 0;
        BitSet bs = new BitSet();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
            bs.set(nums[i], true);
        }
        for (int i = 1; i < nums.length + 1; i++) {
            if (bs.get(i) == false) res.add(i);
        }
        return res;
    }

    public static int longestCommonSubsequence(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int[][] L = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            L[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            L[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) L[i][j] = L[i - 1][j - 1] + 1;
                else L[i][j] = L[i - 1][j] > L[i][j - 1] ? L[i - 1][j] : L[i][j - 1];
            }
        }
        return L[n][m];
    }

    public void moveZeroes1(int[] nums) {
        int p0 = 0, num0 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (num0 == 0) p0 = i;
                num0++;
            } else if (num0 > 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                p0++;
            }
        }
    }

    public void moveZeroes(int[] nums) {
        int zeorIdx = 0, idx = 0;
        if (nums == null || nums.length <= 1) return;
        while (zeorIdx < nums.length && idx < nums.length) {
            while (zeorIdx < nums.length && nums[zeorIdx] != 0) {
                zeorIdx++;
            }
            while (idx < nums.length && nums[idx] == 0) {
                idx++;
            }
            if (zeorIdx < nums.length && idx < nums.length && zeorIdx < idx) {
                int tmp = nums[zeorIdx];
                nums[zeorIdx] = nums[idx];
                nums[idx] = tmp;
                zeorIdx++;
                idx++;
            } else idx++;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

    public int countSubstrings1(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expand(s, i, i);
            count += expand(s, i, i + 1);
        }
        return count;
    }

    public int expand(String s, int start, int end) {
        int S = start, E = end, count = 0;
        while (S >= 0 && E < s.length() && s.charAt(S) == s.charAt(E)) {
            count++;
            S--;
            E++;
        }
        return count;
    }

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int count = s.length();
        int len = 2;
        while (len <= s.length()) {
            for (int i = 0; i < s.length() - 1; i++) {
                int endIdx = i + len;
                if (endIdx <= s.length() && ifPalindromic(s.substring(i, endIdx))) {
                    count++;
                }
            }
            len++;
        }
        return count;
    }

    public boolean ifPalindromic(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    public int singleNumber(int[] nums) {
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            tmp = tmp ^ nums[i];
        }
        return tmp;
    }

    public int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] != b[0] ? -a[0] + b[0] : a[1] - b[1];
            }
        });

        LinkedList<int[]> list = new LinkedList<int[]>();
        for (int[] cur : people)
            list.add(cur[1], cur);
        return list.toArray(new int[people.length][]);
    }

    public int[][] reconstructQueue(int[][] people) {
        for (int i = 0; i < people.length - 1; i++) {
            for (int j = i + 1; j < people.length; j++) {
                if ((people[i][0] > people[j][0]) || (people[i][0] == people[j][0] && people[i][1] > people[j][1])) {
                    int[] tmp = people[i];
                    people[i] = people[j];
                    people[j] = tmp;
                }
            }
        }

        for (int i = people.length - 1; i >= 0; i--) {
            int start = i, hight = people[i][0];
            while (start >= 0 && people[start][0] == hight) {
                start--;
            }
            start += 1;
            for (int j = i; j >= start; j--) {
                int mvnNum = people[j][1] - (j - start), idx = j;
                while (mvnNum > 0) {
                    for (int k = j + 1; k < people.length; k++) {
                        if (people[j][0] < people[k][0]) {
                            int[] tmp = people[k];
                            people[k] = people[j];
                            people[j] = tmp;
                            j = k;
                            mvnNum--;
                        }
                        if (mvnNum <= 0) break;
                    }
                }
                j = idx;
            }
            i = start;
        }
        return people;
    }

    public int[] countBits1(int num) {
        int[] f = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            f[i] = f[i >> 1] + (i & 1);
            System.out.println();
        }
        return f;
    }

    public int[] countBits(int num) {
        int[] ret = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int count = 0, tmp = i;
            while (tmp != 0) {
                count += tmp & 1;
                tmp >>>= 1;
            }
            ret[i] = count;
        }
        return ret;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode t = mergeTwoTree(t1, t2);
        return t;
    }

    public TreeNode mergeTwoTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode t = new TreeNode(t1.val + t2.val);
        t.left = mergeTwoTree(t1.left, t2.left);
        t.right = mergeTwoTree(t1.right, t2.right);
        return t;
    }

    public int hammingDistance(int x, int y) {
        int tmp = x ^ y, count = 0;
        while (tmp != 0) {
            count += tmp & 1;
            tmp = tmp >>> 1;
        }
        return count;
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int flag[] = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (helper(matrix, rows, cols, i, j, str, 0, flag)) return true;
            }
        }
        return false;
    }

    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1) return false;
        if (k == str.length - 1) return true;
        flag[index] = 1;
        if (helper(matrix, rows, cols, i - 1, j, str, k + 1, flag) || helper(matrix, rows, cols, i + 1, j, str, k + 1, flag) || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag) || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
            return true;
        }
        flag[index] = 0;
        return false;
    }

    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] flag = new boolean[rows][cols];
        return nextStep(threshold, rows, cols, 0, 0, flag);
    }

    public int nextStep(int threshold, int rows, int cols, int r, int c, boolean[][] flag) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || flag[r][c] == true || sumXY(r, c) > threshold) return 0;
        flag[r][c] = true;
        return nextStep(threshold, rows, cols, r + 1, c, flag) + nextStep(threshold, rows, cols, r, c + 1, flag) + 1;
    }

    public static int sumXY(int x, int y) {
        int count = 0;
        while (x != 0) {
            count += x % 10;
            x = x / 10;
        }
        while (y != 0) {
            count += y % 10;
            y = y / 10;
        }
        return count;
    }

    private int count = 0;
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(15, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public void Insert(Integer num) {
        if (count % 2 == 0) {
            maxHeap.offer(num);
            int tmpMax = maxHeap.poll();
            minHeap.offer(tmpMax);
        } else {
            minHeap.offer(num);
            int tmpMin = minHeap.poll();
            maxHeap.offer(tmpMin);
        }
        ++count;
    }

    public Double GetMedian() {
        if (count % 2 == 0) return new Double(minHeap.peek() + maxHeap.peek()) / 2;
        else return new Double(minHeap.peek());
    }

    public static TreeNode KthNode(TreeNode pRoot, int k) {
        int count = 0;
        if (pRoot == null || k <= 0) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = pRoot;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (stack.isEmpty()) break;
            node = stack.pop();
            count++;
            if (count == k) return node;
            node = node.right;
        }
        return null;
    }

    public static String Serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if (root == null) return sb.append("#,").toString();
        sb.append(root.val + ",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();
    }

    public int index = -1;

    public TreeNode Deserialize(String str) {
        index++;
        int len = str.length();
        if (index >= len) {
            return null;
        }
        String[] strr = str.split(",");
        TreeNode node = null;
        if (!strr[index].equals("#")) {
            node = new TreeNode(Integer.valueOf(strr[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }

    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (pRoot == null) return list;
        TreeNode node = pRoot;
        queue.add(pRoot);
        int count1 = 1, count2 = 0;
        while (!queue.isEmpty()) {
            ArrayList<Integer> tmpList = new ArrayList<>();
            while (count1-- > 0) {
                node = queue.poll();
                tmpList.add(node.val);
                System.out.print(node.val + " ");
                if (node.left != null) {
                    queue.add(node.left);
                    ++count2;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    ++count2;
                }
            }
            if (!tmpList.isEmpty()) list.add(tmpList);
            count1 = count2;
            count2 = 0;
        }
        return list;
    }

    public static boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return Symmetrical(pRoot.left, pRoot.right);
    }

    public ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (pRoot == null) return list;
        Stack<TreeNode> oddStack = new Stack<>();
        Stack<TreeNode> evenStack = new Stack<>();
        oddStack.push(pRoot);
        while (!oddStack.isEmpty() || !evenStack.isEmpty()) {
            ArrayList<Integer> tmpList1 = new ArrayList<>();
            while (!oddStack.isEmpty()) {
                TreeNode tmp = oddStack.pop();
                tmpList1.add(tmp.val);
                if (tmp.left != null) evenStack.push(tmp.left);
                if (tmp.right != null) evenStack.push(tmp.right);
            }
            if (tmpList1.size() != 0) list.add(tmpList1);
            ArrayList<Integer> tmpList2 = new ArrayList<>();
            while (!evenStack.isEmpty()) {
                TreeNode tmp = evenStack.pop();
                tmpList2.add(tmp.val);
                if (tmp.right != null) oddStack.push(tmp.right);
                if (tmp.left != null) oddStack.push(tmp.left);
            }
            if (tmpList2.size() != 0) list.add(tmpList2);
        }
        return list;
    }

    public TreeNode CreateTree(TreeNode root, int[] a, int idx) {
        if (idx < a.length) {
            if (a[idx] == 0) {
                return null;
            } else {
                root = new TreeNode(a[idx]);
                root.left = CreateTree(root, a, 2 * idx + 1);
                root.right = CreateTree(root, a, 2 * idx + 2);
                return root;
            }
        } else return null;
    }

    public static boolean Symmetrical(TreeNode left, TreeNode right) {
        if (left == null) return right == null;
        if (right == null) return false;
        if (left.val != right.val) return false;
        return Symmetrical(left.right, right.left) && Symmetrical(left.left, right.right);
    }

    public static TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return null;
        TreeLinkNode result = null;
        if (pNode.right != null) {
            result = pNode.right;
            while (result.left != null) {
                result = result.left;
            }
            return result;
        } else {
            result = pNode;
            while (result.next != null && result.next.right == result) {
                result = result.next;
            }
            return result.next;
        }
    }

    public static int JumpBoxes(int[] a, int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a[i]; j++) {
                if (i + j >= n) continue;
                else {
                    dp[i + j] = dp[i + j] > dp[i] + 1 ? dp[i] + 1 : dp[i + j];
                }
            }
        }
        return dp[n];
    }

    public static int gcd(int a, int b) {
        int result = 0;
        if (a == b) return a;
        if (a < b) {   //交换a、b的值
            a = a + b;
            b = a - b;
            a = a - b;
        }
        if (a % b == 0) {
            result = b;
        }
        while (a % b > 0) {
            a = a % b;
            if (a < b) {
                a = a + b;
                b = a - b;
                a = a - b;
            }
            if (a % b == 0) {
                result = b;
            }
        }
        return result;
    }

    public ListNode deleteDuplication1(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        if (pHead.val == pHead.next.val) {
            ListNode pNode = pHead.next;
            while (pNode != null && pNode.val == pHead.val) {
                pNode = pNode.next;
            }
            return deleteDuplication(pNode);
        } else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }

    public static ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        ListNode result = null;
        ListNode q = pHead;
        ListNode p = null;
        boolean flag = false;  //equal to the pre is true
        while (q.next != null) {
            if (flag) {
                flag = q.val == q.next.val;
                q = q.next;
            } else {
                flag = q.val == q.next.val;
                if (!flag) {
                    if (result == null) {
                        result = q;
                        p = q;
                    } else {
                        p.next = q;
                        p = q;
                    }
                }
                q = q.next;
            }
            if (!flag && q.next == null) {
                if (p == null) {
                    p = q;
                    return p;
                } else p.next = q;
            }
            if (flag && q.next == null) {
                p.next = q.next;
            }
        }
        return result == null ? result : q.next;
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null || pHead.next.next == null) return null;
        ListNode p = pHead.next.next;
        ListNode q = pHead.next;
        while (p != q) {
            if (p.next.next != null && q.next != null) {
                p = p.next.next;
                q = q.next;
            } else return null;
        }
        p = pHead;
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

    public static boolean isNumberic(char[] str) {
        if (str == null || str.length == 0) return false;
        int eIdx = -1;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'e' || str[i] == 'E') eIdx = i;
        }
        if (eIdx == str.length - 1 || eIdx == 0) return false;
        if (eIdx == -1) {
            return ifLeft(str, 0, str.length);
        } else {
            return ifLeft(str, 0, eIdx) && ifRight(str, eIdx + 1, str.length);
        }
    }

    public static List<Integer> getPrimes(int n) {
        List<Integer> ret = new ArrayList<>();
        int i = 2;
        while (ret.size() < n) {
            if (isPrime(i)) ret.add(i);
            i++;
        }
        return ret;
    }

    public static boolean ifLeft(char[] str, int start, int end) {
        if (start > str.length - 1) return false;
        if (str[start] == '-' || str[start] == '+') {
            start++;
            if (start > str.length - 1) return false;
        }
        boolean flag = false;
        for (int i = start; i < end; i++) {
            if (str[i] < '0' || str[i] > '9') {
                if (str[i] == '.') {
                    if (!flag) flag = true;
                    else return false;
                } else return false;
            }
        }
        return true;
    }

    public static boolean ifRight(char[] str, int start, int end) {
        if (start > str.length - 1) return false;
        if (str[start] == '-' || str[start] == '+') {
            start++;
            if (start > str.length - 1) return false;
        }
        boolean flag = false;
        for (int i = start; i < end; i++) {
            if (str[i] < '0' || str[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean match(char[] str, char[] pattern) {
        /*
        如果 pattern[j] == str[i] || pattern[j] == '.', 此时dp[i][j] = dp[i-1][j-1];
        如果 pattern[j] == '*'
        分两种情况:
        1: 如果pattern[j-1] != str[i] && pattern[j-1] != '.', 此时dp[i][j] = dp[i][j-2] //a*匹配0次
        2: 如果pattern[j-1] == str[i] || pattern[j-1] == '.'
            此时dp[i][j] = dp[i][j-2] // a*匹配0次
            或者 dp[i][j] = dp[i][j-1] // a*匹配1次
            或者 dp[i][j] = dp[i-1][j] // a*匹配多次
         */
        int m = str.length, n = pattern.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++)
            dp[0][i] = pattern[i - 1] == '*' && dp[0][i - 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pattern[j - 1] != '*')
                    dp[i][j] = dp[i - 1][j - 1] && (pattern[j - 1] == '.' || pattern[j - 1] == str[i - 1]);
                else {
                    if (str[i - 1] != pattern[j - 2] && pattern[j - 2] != '.') dp[i][j] = dp[i][j - 2];
                    else dp[i][j] = dp[i][j - 1] || dp[i][j - 2] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    public static int[] multiply(int[] A) {
        if (A == null || A.length == 0) return null;
        int[] B = new int[A.length];
        B[0] = 1;
        for (int i = 1; i < A.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        int tmp = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            tmp *= A[i + 1];
            B[i] *= tmp;
        }
        return B;
    }

    public static boolean duplicate(int[] numbers, int length, int[] duplication) {
        for (int i = 0; i < length; i++) {
            int idx = numbers[i] % length;
            if (numbers[idx] > length) {
                duplication[0] = idx;
                return true;
            }
            numbers[idx] += length;
        }
        return false;
    }

    public static int StrToInt(String str) {
        if (str == null || str.length() == 0) return 0;
        char tmp = str.charAt(0);
        if ((tmp < '0' || tmp > '9') && tmp != '+' && tmp != '-') return 0;
        int result = 0, len = 1;
        if (tmp != '-' && tmp != '+') result = tmp - '0';
        while (len < str.length()) {
            tmp = str.charAt(len++);
            if (tmp < '0' || tmp > '9') return 0;
            result = result * 10 + (tmp - '0');
        }
        return str.charAt(0) == '-' ? 0 - result : result;
    }

    public static int Add(int num1, int num2) {
        int sum = num1 ^ num2;
        while (num2 != 0) {
            sum = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = sum;
        }
        return sum;
    }

    public static int Sum_Solution(int n) {
        int sum = n;
        boolean flag = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }

    public static int LastRemaining_Solution(int n, int m) {
        if (n == 0 || m == 0) return -1;
        int stu[] = new int[n];
        int count = 0, idx = 0, num = 0, result = 0;
        while (count < n) {
            while (stu[idx] == -1) {
                idx = (idx + 1) == n ? 0 : (idx + 1);
            }
            if (num == m - 1) {
                stu[idx] = -1;
                num = 0;
                if (count == n - 1) result = idx;
                idx = (idx + 1) == n ? 0 : (idx + 1);
                count++;
                continue;
            } else {
                idx = (idx + 1) == n ? 0 : (idx + 1);
                num++;
            }
        }
        return result;
    }

    public static boolean isContinuous(int[] numbers) {
        if (numbers.length != 5) return false;
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j] && numbers[i] != 0) return false;
            }
        }
        int min = 14, max = -1;
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (number > 13 || number < 0) return false;
            if (number == 0) continue;
            min = min > number ? number : min;
            max = max < number ? number : max;
        }
        if ((max - min) >= 5) return false;
        return true;
    }

    public static String ReverseSentence(String str) {
        if (str.length() == 0 || str == null || str == " ") return str;
        if (str.trim().equals("")) {
            return str;
        }
        String res = "";
        String[] strings = str.split(" ");
        for (int i = strings.length - 1; i >= 0; i--) {
            res += strings[i] + " ";
        }
        return res.substring(0, res.length() - 1);
    }

    public static void swap(String str) {
        int start = 0, end = str.length() - 1;
        while (start < end) {
            char ch = str.charAt(start);

        }
    }

    public static String LeftRotateString(String str, int n) {
        if (str == null || str.length() == 0) return "";
        n = n % str.length();
        if (n == 0) return str;
        return str.substring(n, str.length()) + str.substring(0, n);
    }

    public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        int start = 0, end = array.length - 1;
        int num1 = 0, num2 = 0, num = 0;
        while (start < end && array[start] < ((sum + 1) >> 1)) {
            if ((array[start] + array[end]) == sum) {
                if (num == 0 || num > (array[start] * array[end])) {
                    num1 = array[start];
                    num2 = array[end];
                    num = num1 * num2;
                }
                start++;
                end--;
            } else if ((array[start] + array[end]) > sum) end--;
            else start++;
        }
        if (num != 0) {
            list.add(num1);
            list.add(num2);
        }
        return list;
    }

    public static void FindNumsAppearOnce1(int[] array, int num1[], int num2[]) {
        if (array.length < 2) return;
        int tmp = array[0];
        for (int i = 1; i < array.length; i++) {
            tmp ^= array[i];
        }
        if (tmp == 0) return;
        int idx = 0;
        while ((tmp & 1) == 0) {
            tmp = tmp >> 1;
            idx++;
        }
        num1[0] = 0;
        num2[0] = 0;
        for (int i = 0; i < array.length; i++) {
            if (((array[i] >> idx) & 1) == 0) num1[0] ^= array[i];
            else num2[0] ^= array[i];
        }
    }

    public static void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (set.contains(array[i])) set.remove(array[i]);
            else set.add(array[i]);
        }
        int[] b = new int[2];
        int idx = 0;
        for (Integer a : set) {
            b[idx++] = a;
        }
        num1[0] = b[0];
        num2[0] = b[1];
    }

    public static boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        if (Math.abs(left - right) > 1) return false;
        else return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    public static int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        int c1 = TreeDepth(root.left) + 1;
        int c2 = TreeDepth(root.right) + 1;
        return c1 > c2 ? c1 : c2;
    }

    public static int GetNumberOfK(int[] array, int k) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) count++;
            if (array[i] > k) i = array.length;
        }
        return count;
    }

    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode h1 = pHead1;
        ListNode h2 = pHead2;
        while (h1 != h2) {
            if (h1 != null) h1 = h1.next;
            if (h2 != null) h2 = h2.next;
            if (h1 != h2) {
                if (h1 == null) h1 = pHead2;
                if (h2 == null) h2 = pHead1;
            }
        }
        return h1;
    }

    public static ListNode FindCommon(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) return null;
        if (head1 == head2) return head1;
        ListNode r1 = FindCommon(head1.next, head2);
        if (r1 != null) return r1;
        return null;
        //return r1!=null ? r1 : (r2!=null ? r2 : r3);
    }

    public static int InversePairs(int[] array) {
        if (array == null || array.length == 0) return 0;
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return InverseTool(array, copy, 0, array.length - 1);
    }

    public static int InverseTool(int[] array, int[] copy, int low, int high) {
        if (low == high) {
            return 0;
        }
        int mid = (low + high) / 2;
        int left = InverseTool(array, copy, low, mid) % 1000000007;
        int right = InverseTool(array, copy, mid + 1, high) % 1000000007;
        int count = 0;
        int i = mid, j = high, lowCopy = high;
        while (i >= low && j > mid) {
            if (array[i] > array[j]) {
                count += j - mid;
                copy[lowCopy--] = array[i--];
                count %= 1000000007;
            } else {
                copy[lowCopy--] = array[j--];
            }
        }
        for (; i >= low; i--) {
            copy[lowCopy--] = array[i];
        }
        for (; j > mid; j--) {
            copy[lowCopy--] = array[j];
        }
        for (int k = low; k <= high; k++) {
            array[k] = copy[k];
        }
        return (left + right + count) % 1000000007;
    }

    public static int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        return count;
    }

    public static int FindGreatestSumOfSubArray(int[] array) {
        int sum = Integer.MIN_VALUE;
        int max = sum;
        for (int i = 0; i < array.length; i++) {
            if (sum < 0) {
                if (array[i] >= 0) sum = array[i];
                else sum = array[i] > sum ? array[i] : sum;
            } else {
                sum += array[i];
                max = sum > max ? sum : max;
            }
        }
        max = sum > max ? sum : max;
        return max;
    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k <= 0 || input.length < k || input == null) return list;
        int[] last = new int[k];
        int maxIdx = 0;
        for (int i = 0; i < last.length; i++) {
            last[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < input.length; i++) {
            if (last[maxIdx] > input[i]) {
                last[maxIdx] = input[i];
                maxIdx = 0;
                for (int j = 1; j < last.length; j++) {
                    if (last[maxIdx] < last[j]) maxIdx = j;
                }
            } else continue;
        }
        for (int i = 0; i < last.length; i++) {
            list.add(last[i]);
        }
        return list;
    }

    public static int MoreThanHalfNum_Solution(int[] array) {
        int count = 1;
        int cur = array[0];
        for (int i = 1; i < array.length; i++) {
            if (count == 0) {
                cur = array[i];
                count = 1;
            } else {
                if (cur == array[i]) count++;
                else count--;
            }
        }
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (cur == array[i]) count++;
        }
        if (count > array.length / 2) return cur;
        else return 0;
    }

    public static TreeNode Convert(TreeNode pRootOfTree) {
        TreeNode lastNode = null;
        TreeNode head = convertNode(pRootOfTree, lastNode);
        while (head != null && head.left != null) {
            head = head.left;
        }
        return head;
    }

    public static TreeNode convertNode(TreeNode tnode, TreeNode lastNode) {
        if (tnode == null) return null;
        if (tnode.left != null) {
            lastNode = convertNode(tnode.left, lastNode);
        }
        tnode.left = lastNode;
        if (lastNode != null) {
            lastNode.right = tnode;
        }
        lastNode = tnode;
        if (tnode.right != null) {
            lastNode = convertNode(tnode.right, lastNode);
        }
        return lastNode;
    }

    public static RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null || pHead.next == null) return null;
        RandomListNode pNext = pHead;
        while (pNext != null) {
            RandomListNode tmp = new RandomListNode(pNext.label);
            tmp.next = pNext.next;
            pNext.next = tmp;
            pNext = tmp.next;
        }
        pNext = pHead;
        while (pNext != null) {
            RandomListNode tmp = pNext.next;
            if (pNext.random != null) {
                tmp.random = pNext.random.next;
            }
            pNext = tmp.next;
        }
        RandomListNode head = pHead.next;
        pNext = head;
        RandomListNode tmp;
        while (pNext != null) {
            tmp = pNext.next;
            pNext.next = tmp.next;
            pNext = tmp;
        }
        return head;
    }

    public static void insertDbList(DbList head, int i, int str) {
        int j = 1;
        DbList cur = head.next;
        // 移动到第i个位置
        while (cur != null && j < i) {
            cur = cur.next;
            j++;
        }
        if (cur == null || j > i) {
            return;
        }
        DbList node = new DbList();
        node.data = str;
        node.prior = cur.prior;
        cur.prior.next = node;
        node.next = cur;
        cur.prior = node;
    }

    public static int firstMissingPositive(int[] nums) {
        if (nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= nums.length && nums[i] > 0 && nums[i] != i + 1) {
                if (nums[nums[i] - 1] != nums[i]) { //line 9
                    int tmp = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = tmp;
                    i--; //line 13
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        getList2(result, new ArrayList<>(), candidates, target, 0, new ArrayList<>());
        return result;
    }

    public static void getList2(List<List<Integer>> result, List<Integer> list, int[] candidates, int target, int idx, ArrayList<Integer> used) {
        if (target > 0) {
            for (int i = idx; i < candidates.length && target >= candidates[i]; i++) {
                if (used.contains(i)) {
                    continue;
                } else {
                    list.add(candidates[i]);
                    used.add(i);
                    getList2(result, list, candidates, target - candidates[i], i, used);
                    used.remove(list.size() - 1);
                    list.remove(list.size() - 1);
                }

            }
        } else if (target == 0) {
            if (!result.contains(new ArrayList<>(list))) result.add(new ArrayList<>(list));
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        getList(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    public static void getList(List<List<Integer>> result, List<Integer> list, int[] candidates, int target, int idx) {
        if (target > 0) {
            for (int i = idx; i < candidates.length && target >= candidates[i]; i++) {
                list.add(candidates[i]);
                getList(result, list, candidates, target - candidates[i], i);
                list.remove(list.size() - 1);
            }
        } else if (target == 0) {
            result.add(new ArrayList<Integer>(list));
        }
    }

    public static int findMissing2(int n, String str) {
        /*给一个由 1 - n 的整数随机组成的一个字符串序列，其中丢失了一个整数，请找到它。 */
        return 0;
    }

    public static String countAndSay(int n) {
        String result = "1";
        for (int i = 1; i < n; i++) {
            String tmp = result;
            result = "";
            int count = 1;
            char ch = tmp.charAt(0);

            for (int j = 1; j < tmp.length(); j++) {
                if (tmp.charAt(j) == ch) count++;
                else {
                    result = result + count + ch;
                    count = 1;
                    ch = tmp.charAt(j);
                }
            }
            result = result + count + ch;
        }
        return result;
    }

    public static void solveSudoku(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        solve(board);
    }

    public static boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) return true;
                            else board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] != '.' && board[row][i] == c) return false;
            if (board[i][col] != '.' && board[i][col] == c) return false;
            if (board[(row / 3 * 3) + i / 3][col / 3 * 3 + i % 3] != '.' && board[(row / 3 * 3) + i / 3][col / 3 * 3 + i % 3] == c)
                return false;
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            HashSet<Character> row = new HashSet<>();
            HashSet<Character> col = new HashSet<>();
            HashSet<Character> cub = new HashSet<>();
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.' && !row.add(board[i][j])) return false;
                if (board[j][i] != '.' && !col.add(board[j][i])) return false;
                int crow = i / 3 * 3;
                int ccol = i % 3 * 3;
                if (board[crow + j / 3][ccol + j % 3] != '.' && !cub.add(board[crow + j / 3][ccol + j % 3]))
                    return false;
            }
        }
        return true;
    }

    public static int searchInsert(int[] nums, int target) {
        if (nums.length == 0 || nums[0] >= target) return 0;
        if (nums.length == 1) return nums[0] < target ? 1 : 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) return i;
        }
        return nums.length;
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        Arrays.fill(result, -1);

        if (nums.length == 0) return result;
        int low = 0, high = nums.length - 1, mid = 0;

        while (low < high) {
            mid = (low + high) / 2;
            if (nums[mid] < target) low = mid + 1;
            else high = mid;
        }
        if (nums[low] != target) return result;
        else result[0] = low;

        high = nums.length - 1;
        while (low < high) {
            mid = (low + high + 1) / 2;
            if (nums[mid] <= target) low = mid;
            else high = mid - 1;
        }
        result[1] = high;
        return result;
    }

    public static int search(int[] nums, int target) {
        int min = 0, max = nums.length - 1, mid = 0;
        while (min <= max) {
            mid = (min + max) / 2;
            if (nums[mid] == target) return mid;
            if (nums[min] <= nums[mid]) {
                if (nums[min] <= target && target < nums[mid]) max = mid - 1;
                else min = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[max]) min = mid + 1;
                else max = mid - 1;
            }
        }
        return -1;
    }

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') stack.push(i);
            else {
                if (!stack.isEmpty()) {
                    if (ch[stack.peek()] == '(') stack.pop();
                    else stack.push(i);
                } else stack.push(i);
            }
        }
        if (stack.isEmpty()) return ch.length;
        int a = ch.length, b = 0;
        while (!stack.isEmpty()) {
            b = stack.pop();
            max = Math.max(max, a - b - 1);
            a = b;
        }
        return Math.max(max, a);

    }

    public static void nextPermutation(int[] nums) {  //求下一个全排列
        if (nums.length <= 1) return;
        int idx = nums.length - 1;
        while (idx >= 1 && nums[idx - 1] >= nums[idx]) {
            idx--;
        }
        idx--;
        if (idx != -1) {
            int endIdx = nums.length - 1;
            while (nums[endIdx] <= nums[idx] && idx < endIdx) {
                endIdx--;
            }
            int tmp = nums[idx];
            nums[idx] = nums[endIdx];
            nums[endIdx] = tmp;
        }
        reverse(nums, idx + 1);
    }

    private static void reverse(int[] nums, int idx) {
        int l = idx, r = nums.length - 1;
        while (l < r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (words.length == 0 || words == null || s == null || s.length() == 0) return list;
        int len = words[0].length(), times = words.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) map.put(words[i], map.get(words[i]) + 1);
            else {
                map.put(words[i], 1);
            }
        }

        for (int i = 0; i <= s.length() - len * words.length; i++) {
            int start = i;
            String tmp = s.substring(start, start + len);
            int count = 0;
            while (map.containsKey(tmp) && map.get(tmp) > 0) {
                map.put(tmp, map.get(tmp) - 1);
                count++;
                start += len;
                if (start + len > s.length()) break;
                tmp = s.substring(start, start + len);
            }
            if (count == times) {
                list.add(i);
            }
            if (count > 0) {
                map.clear();
                for (int j = 0; j < words.length; j++) {
                    if (map.containsKey(words[j])) map.put(words[j], map.get(words[j]) + 1);
                    else {
                        map.put(words[j], 1);
                    }
                }
            }
        }
        return list;
    }

    public static int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 0) return 0;
        if (dividend == Integer.MIN_VALUE && (divisor == 1 || divisor == -1)) {
            return divisor == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        boolean isPositive = (dividend < 0) ^ (divisor < 0);

        long dv = abs(dividend);
        long ds = abs(divisor);
        int finalCount = 0;
        int count = 0;

        while (dv >= ds) {
            count = 1;
            ds = abs(divisor);
            long sum = ds;
            while (sum + sum <= dv) {
                sum += sum;
                count += count;
            }
            dv -= sum;
            finalCount += count;
        }
        return isPositive == false ? finalCount : -finalCount;
    }

    private static long abs(int num) {
        if (num < 0) {
            return -(long) num;
        }
        return (long) num;
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0 || needle == null) return 0;
        if (haystack == null || haystack.length() == 0 || needle.length() > haystack.length()) return -1;
        int len = needle.length();
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.length() - i < len) return -1;
            if (haystack.charAt(i) == needle.charAt(0)) {
                String str = haystack.substring(i, i + len);
                if (haystack.substring(i, i + len).equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            } else {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return nums.length;
        int count = 1;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != pre) {
                pre = nums[i];
                nums[count] = pre;
                count++;
            }
        }
        return count;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode hh = new ListNode(0);
        if (!canReverse(head, k) || k == 1) return head;
        else {
            ListNode h1 = head;
            ListNode h2;
            for (int i = 0; i < k - 1; i++) {
                h1 = h1.next;
            }
            h2 = h1.next;
            h1.next = null;
            reverseList(head);
            hh.next = h1;
            head.next = reverseKGroup(h2, k);

        }
        return hh.next;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode h1 = head;
        ListNode h2 = h1.next;
        head.next = null;
        ListNode h3;
        do {
            h3 = h2.next;
            h2.next = h1;
            h1 = h2;
            h2 = h3;
        } while (h2 != null);
        return h1;
    }

    public static boolean canReverse(ListNode head, int k) {
        int count = 0;
        while (head != null) {
            count++;
            if (count >= k) break;
            head = head.next;
        }
        return count >= k;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode h1 = new ListNode(0);
        h1.next = head;
        ListNode h2;
        ListNode h3 = h1.next.next;
        do {
            head = h1.next;
            h2 = head.next;
            head.next = h2.next;
            h2.next = head;
            h1.next = h2;
            h1 = head;
        } while (head.next != null && head.next.next != null);
        return h3;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 1) return lists[0];
        if (lists.length == 0 || lists == null) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) return -1;
                else if (o1.val == o2.val) return 0;
                else return 1;
            }
        });
        ListNode head = new ListNode(0);
        ListNode resu = head;
        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }

        while (!queue.isEmpty()) {
            head.next = queue.poll();
            head = head.next;
            if (head.next != null) queue.add(head.next);
        }
        return resu.next;
    }

    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generate(list, "", n, n);
        return list;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = new ListNode(0);
        ListNode h = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                l1 = l1.next;
            } else {
                h.next = l2;
                l2 = l2.next;
            }
            h = h.next;
        }
        if (l1 != null) h.next = l1;
        if (l2 != null) h.next = l2;
        return head.next;
    }

    private static void generate(List<String> list, String s, int left, int right) {
        if (left == 0 && left == right) {
            list.add(s);
            return;
        }
        if (left > 0) generate(list, s + '(', left - 1, right);
        if (right > left) generate(list, s + ')', left, right - 1);
    }

    public static ListNode mergeTwoListsIter(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode h;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static boolean isValid(String s) {
        Stack<Character> ss = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    ss.push(s.charAt(i));
                    break;
                case ')': {
                    if (ss.isEmpty() || ss.peek() != '(') return false;
                    else ss.pop();
                    break;
                }
                case '[':
                    ss.push(s.charAt(i));
                    break;
                case ']': {
                    if (ss.isEmpty() || ss.peek() != '[') return false;
                    else ss.pop();
                    break;
                }
                case '{':
                    ss.push(s.charAt(i));
                    break;
                case '}': {
                    if (ss.isEmpty() || ss.peek() != '{') return false;
                    else ss.pop();
                    break;
                }
                default:
                    break;
            }
        }
        return ss.isEmpty();
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode ln = head;
        if (n == 0) return head;
        if (ln == null || ln.next == null) return null;
        int index = 0;
        while (ln != null) {
            index++;
            ln = ln.next;
        }
        if (index == n) return head.next;
        int in = index - n;
        ln = head;
        for (int i = 0; i < in - 1; i++) {
            ln = ln.next;
        }
        ln.next = ln.next.next;
        return head;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(nums);
        if (nums.length < 4 || nums == null) return list;
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int sum = target - nums[i] - nums[j];
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    if (sum == nums[left] + nums[right]) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    } else if (sum > nums[left] + nums[right]) {
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        left++;
                    } else {
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    }
                }
            }
        }
        return list;
    }

    public static List<List<Integer>> fourSumFast(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                int s = j + 1;
                int e = nums.length - 1;
                while (s < e) {
                    int sum = nums[i] + nums[j] + nums[s] + nums[e];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[s]);
                        list.add(nums[e]);
                        lists.add(list);
                        do {
                            s++;
                        } while (s < nums.length - 1 && nums[s - 1] == nums[s]);

                        do {
                            e--;
                        } while (e > 0 && nums[e + 1] == nums[e]);
                    } else if (sum > target) {
                        do {
                            e--;
                        } while (e > 0 && nums[e + 1] == nums[e]);
                    } else {
                        do {
                            s++;
                        } while (s < nums.length && nums[s - 1] == nums[s]);
                    }
                }
            }
        }

        return lists;
    }

    public static List<String> letterCombinations(String digits) {
        LinkedList<String> resu = new LinkedList<>();
        String[] str = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if (digits == null || digits.length() == 0) return resu;
        if (digits.length() == 1) {
            char[] ch = str[digits.charAt(0) - '0'].toCharArray();
            for (int i = 0; i < ch.length; i++) {
                resu.add(String.valueOf(ch[i]));
            }
            return resu;
        }
        resu.add("");
        for (int i = 0; i < digits.length(); i++) {
            int index = digits.charAt(i) - '0';
            while (resu.peek().length() == i) {
                String t = resu.remove();
                for (int j = 0; j < str[index].length(); j++) {
                    resu.add(t + str[index].charAt(j));
                }
            }
        }
        return resu;
    }

    public static int threeSumClosest(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            } else {
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum < target) {
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                    } else if (sum > target) {
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else return sum;
                    if (Math.abs(target - sum) < Math.abs(target - result)) result = sum;
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            else {
                int sum = 0 - nums[i], left = i + 1, right = nums.length - 1;
                while (left < right) {
                    if (sum == nums[left] + nums[right]) {
                        list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[right - 1] == nums[right]) right--;
                        while (left < right && nums[left + 1] == nums[left]) left++;
                        right--;
                        left++;
                    } else if (sum > nums[left] + nums[right]) {
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        left++;
                    } else {
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    }
                }
            }
        }
        return list;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        int index = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i)) return strs[0].substring(0, index);
            }
            index++;
        }
        return strs[0].substring(0, index);
    }

    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    public static int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) left++;
            else right--;
        }
        /*for (int i = 0; i < height.length - 1; i++) {
            int h = height[i];
            for (int j = 1; j < height.length; j++) {
                int temp = (h>height[j]?height[j]:h) * (j-i);
                if(temp>max)
                    max=temp;
            }
        }*/
        return max;
    }

    public static double[][] getRandArrays(int m, int n) {
        double[][] a = new double[m][n];
        Random rand = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = rand.nextInt(100);
                a[i][j] = t;
            }
        }
        return a;
    }

    public static int aplusb(int a, int b) {
        int t = 0;
        while (b != 0) {
            t = a ^ b;
            b = (a & b) << 1;
            a = t;
        }
        return t;
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = p.charAt(i - 1) == '*' && dp[0][i - 2] == true;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                } else
                    //   dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    dp[i][j] = dp[i][j - 2] || (s.charAt(i - 1) == p.charAt(j - 2) || '.' == p.charAt(j - 2)) && dp[i - 1][j];
            }
        }

        return dp[m][n];
    }

    public static void fileOperationByLine() throws Exception {
        //read file by lines
        File fin1 = new File("D:/cities.txt");
        BufferedReader bf1 = new BufferedReader(new FileReader(fin1));
        String sin1 = "";
        String text1 = null;
        while ((sin1 = bf1.readLine()) != null) {
            text1 += sin1 + "\n";
        }
        System.out.println(text1);
        bf1.close();
        //write whole string into file
        File fout1 = new File("D:/out1.txt");
        if (!fout1.exists()) fout1.delete();
        fout1.createNewFile();
        try {
            FileWriter fw1 = new FileWriter(fout1, true);
            fw1.write(text1);
            fw1.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Occurs!");
        }

    }

    public static void fileOperationByBytes() throws Exception {
        File file = new File("D:/cities.txt");
        InputStream in = null;
        byte[] tmp = new byte[5];  //get 5 bytes per read operation
        in = new FileInputStream(file);
        int lenBytes = 0;
        int count = 0;
        while ((lenBytes = in.read(tmp)) != -1) {
            System.out.write(tmp, 0, lenBytes);
            System.out.println(count++);
        }
    }

    public int findDeep2(BiTree root) {
        if (root == null) return 0;
        BiTree current = null;
        LinkedList<BiTree> queue = new LinkedList<BiTree>();
        queue.offer(root);
        int cur, last;
        int level = 0;
        while (!queue.isEmpty()) {
            cur = 0;//记录本层已经遍历的节点个数
            last = queue.size();//当遍历完当前层以后，队列里元素全是下一层的元素，队列的长度是这一层的节点的个数
            while (cur < last)//当还没有遍历到本层最后一个节点时循环
            {
                current = queue.poll();//出队一个元素
                cur++;
                //把当前节点的左右节点入队（如果存在的话）
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            level++;//每遍历完一层level+1
        }
        return level;
    }

    static int[] flag = new int[128];
    static StringBuffer sb = new StringBuffer();

    public static void Insert(char ch) {
        sb.append(ch);
        flag[ch] = flag[ch] == 0 ? 1 : flag[ch] + 1;
    }

    public static char FirstAppearingOnce() {
        for (int i = 0; i < sb.length(); i++) {
            if (flag[sb.charAt(i)] == 1) return sb.charAt(i);
        }
        return '#';
    }

    int[] flag1 = new int[128];
    int idx1;
    int[] seq1 = new int[128];

    public void Insert1(char ch) {
        flag1[ch] += 1;
        seq1[ch] = seq1[ch] == 0 ? (idx1 == 0 ? -1 : idx1) : seq1[ch];
        idx1++;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce1() {
        char result = '#';
        int idx = Integer.MAX_VALUE;
        for (int i = 0; i < flag1.length; i++) {
            if (flag1[i] == 1 && seq1[i] < idx) {
                result = (char) i;
                idx = seq1[i];
            }
        }
        return result;
    }

    public static ListNode CreateList(int[] a) {
        ListNode head = new ListNode(a[0]);
        ListNode res = head;
        for (int i = 1; i < a.length; i++) {
            head.next = new ListNode(a[i]);
            head = head.next;
        }
        return res;
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public static class BiTree {
        int data;
        BiTree left;
        BiTree right;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static class DbList {
        int data;
        DbList next;
        DbList prior;
    }

    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
