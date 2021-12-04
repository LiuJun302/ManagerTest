package CodingTrain;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LeetCodeTrainning {

    public LeetCodeTrainning() {
    }

    public static void main(String[] args) {
        LeetCodeTrainning main = new LeetCodeTrainning();

        int[] a1 = {1, 2, 6, 4, 8, 10, 9, 15};
        System.out.println(main.findUnsortedSubarray(a1));

    }


    /**
     * 581. 最短无序连续子数组
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

    /**
     * 1446. 连续字符
     *
     * @param s
     * @return
     */
    public int maxPower(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ret = 1, idx = 1, tmp = 1;
        while (idx < s.length()) {
            if (s.charAt(idx) == s.charAt(idx - 1)) {
                tmp++;
            } else {
                ret = Math.max(tmp, ret);
                tmp = 1;
            }
            idx++;
        }
        return Math.max(ret, tmp);
    }

    /**
     * 384. 打乱数组
     */
    class Solution {

        int[] originNums;
        int[] changedNums;
        int n;

        public Solution(int[] nums) {
            n = nums.length;
            originNums = nums;
            changedNums = Arrays.copyOf(originNums, n);

        }

        public int[] reset() {
            changedNums = Arrays.copyOf(originNums, n);
            return changedNums;
        }

        public int[] shuffle() {
            for (int i = 0; i < n; i++) {
                int idx = i + (new Random()).nextInt(n - i);
                int tmp = changedNums[i];
                changedNums[i] = changedNums[idx];
                changedNums[idx] = tmp;
            }
            return changedNums;
        }
    }

    /**
     * 1114. 按序打印
     */
    class Foo {

        AtomicInteger atomicInteger = new AtomicInteger(1);

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {

            while (atomicInteger.get() != 1) {
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();

            atomicInteger.set(2);
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (atomicInteger.get() != 2) {
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            atomicInteger.set(3);
        }

        public void third(Runnable printThird) throws InterruptedException {

            while (atomicInteger.get() != 3) {
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    /**
     * 397. 整数替换
     *
     * @param n
     * @return
     */
    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + integerReplacement(n / 2);
        } else {
            return 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1));
        }
    }

    /**
     * 563. 二叉树的坡度
     *
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return findTilt(root.left) + findTilt(root.right) + Math.abs(getNodeSum(root.right) - getNodeSum(root.left));
    }

    public int getNodeSum(TreeNode node) {
        if (null == node) {
            return 0;
        }
        return getNodeSum(node.left) + getNodeSum(node.right) + node.val;
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<TreeNode> deque = new LinkedList<TreeNode>();
        if (root != null) {
            deque.add(root);
        }
        while (!deque.isEmpty()) {
            int time = res.size();
            List<Integer> list = new ArrayList<>(deque.size());
            if (time % 2 == 0) {
                LinkedList<TreeNode> tmpDeque = new LinkedList<TreeNode>();
                while (!deque.isEmpty()) {
                    TreeNode node = deque.poll();
                    list.add(node.val);
                    if (null != node.left) {
                        tmpDeque.addFirst(node.left);
                    }
                    if (null != node.right) {
                        tmpDeque.addFirst(node.right);
                    }
                }
                deque = tmpDeque;
            } else {
                LinkedList<TreeNode> tmpDeque = new LinkedList<TreeNode>();
                while (!deque.isEmpty()) {
                    TreeNode node = deque.poll();
                    list.add(node.val);
                    if (null != node.right) {
                        tmpDeque.addFirst(node.right);
                    }
                    if (null != node.left) {
                        tmpDeque.addFirst(node.left);
                    }
                }
                deque = tmpDeque;
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 318. 最大单词长度乘积
     *
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int[] convert = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int tmp = 0;
            for (int j = 0; j < words[i].length(); j++) {
                int u = words[i].charAt(j) - 'a';
                tmp |= (1 << u);
            }
            convert[i] = tmp;
        }

        int result = 0;
        for (int i = 0; i < convert.length; i++) {
            for (int j = 0; j < i; j++) {
                if ((convert[i] & convert[j]) == 0) {
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }

    /**
     * 124. 二叉树中的最大路径和
     *
     * @param root
     * @return
     */
    int maxPath = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumIter(root);
        return maxPath;
    }

    public int maxPathSumIter(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftVal = Math.max(maxPathSumIter(node.left), 0);
        int rightVal = Math.max(maxPathSumIter(node.right), 0);
        int currVal = leftVal + rightVal + node.val;

        maxPath = Math.max(maxPath, currVal);

        return node.val + Math.max(leftVal, rightVal);
    }

    /**
     * 114. 二叉树展开为链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (pre != null) {
                pre.left = null;
                pre.right = cur;
            }
            TreeNode left = cur.left, right = cur.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            pre = cur;
            ;
        }
    }


    /**
     * 98. 验证二叉搜索树
     *
     * @param root
     * @return
     */
    long isValidBSTPre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (!isValidBST(root.left)) {
            return false;
        }

        if (root.val <= isValidBSTPre) {
            return false;
        }

        isValidBSTPre = root.val;

        return isValidBST(root.right);
    }

    /**
     * 76. 最小覆盖子串
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int begin = 0, end = 0, sIdx = 0, eIdx = Integer.MAX_VALUE;
        while (end < s.length()) {
            while (end < s.length()) {
                char ch = s.charAt(end);
                if (map.containsKey(ch)) {
                    map.put(ch, map.get(ch) - 1);
                }
                end++;
                if (checkMap(map)) {
                    break;
                }
            }
            while (checkMap(map) && begin < end) {
                char ch = s.charAt(begin);
                if (!map.containsKey(ch)) {
                    begin++;
                } else if (map.containsKey(ch) && map.get(ch) < 0) {
                    map.put(ch, map.get(ch) + 1);
                    begin++;
                } else {
                    break;
                }
            }
            if (eIdx - sIdx > end - begin) {
                eIdx = end;
                sIdx = begin;
            }
        }
        if (checkMap(map)) {
            return s.substring(sIdx, eIdx);
        } else {
            return "";
        }
    }

    public boolean checkMap(Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 102. 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.size() > 0) {
            Queue<TreeNode> tmp = new ArrayDeque<>();
            List<Integer> tmpRes = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                tmpRes.add(node.val);
                if (node.left != null) {
                    tmp.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }
            res.add(tmpRes);
            queue = tmp;
        }
        return res;
    }

    /**
     * 62. 不同路径
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 49. 字母异位词分组
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chs = strs[i].toCharArray();
            Arrays.sort(chs);
            String str = String.valueOf(chs);
            List<String> list = map.getOrDefault(str, new ArrayList<String>());
            list.add(strs[i]);
            map.put(str, list);
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    /**
     * 56. 合并区间
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if (res.size() == 0 || res.get(res.size() - 1)[1] < left) {
                res.add(new int[]{left, right});
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], right);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 981. 基于时间的键值存储
     */
    class TimeMap {

        Map<String, TreeMap<Integer, String>> keyValue;

        /**
         * Initialize your data structure here.
         */
        public TimeMap() {
            keyValue = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> map = keyValue.get(key);
            if (map != null) {
                map.put(timestamp, value);
            } else {
                map = new TreeMap<>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                });
                map.put(timestamp, value);
                keyValue.put(key, map);
            }
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> map = keyValue.get(key);
            if (map != null) {

                for (int time : map.keySet()) {
                    if (time <= timestamp) {
                        return map.get(time);
                    }
                }
            }
            return "";
        }
    }

    /**
     * 930. 和相同的二元子数组
     *
     * @param nums
     * @param goal
     * @return
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        return ret;
    }

    /**
     * 1418. 点菜展示表
     *
     * @param orders
     * @return
     */ //[["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> res = new ArrayList<>();
        Set<String> foodSet = new TreeSet<>();
        Map<String, Map<String, Integer>> orderItems = new TreeMap<>((o1, o2) -> Integer.valueOf(o1).compareTo(Integer.valueOf(o2)));
        for (int i = 0; i < orders.size(); i++) {
            List<String> order = orders.get(i);
            Map<String, Integer> orderItem = orderItems.get(order.get(1));
            foodSet.add(order.get(2));
            if (orderItem != null) {
                orderItem.put(order.get(2), orderItem.getOrDefault(order.get(2), 0) + 1);
            } else {
                orderItem = new HashMap<>();
                orderItem.put(order.get(2), 1);
                orderItems.put(order.get(1), orderItem);
            }
        }

        List<String> tmpRes = new ArrayList<>();
        tmpRes.add("Table");
        for (String food : foodSet) {
            tmpRes.add(food);
        }
        res.add(tmpRes);

        for (String key : orderItems.keySet()) {
            tmpRes = new ArrayList<>();
            Map<String, Integer> orderItem = orderItems.get(key);
            tmpRes.add(key);
            for (String food : foodSet) {
                tmpRes.add(String.valueOf(orderItem.getOrDefault(food, 0)));
            }
            res.add(tmpRes);
        }
        return res;
    }

    /**
     * 1267. 统计参与通信的服务器
     *
     * @param grid
     * @return
     */
    public int countServers(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] rows = new int[n];
        int[] cols = new int[m];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && (rows[i] > 1 || cols[j] > 1)) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    /**
     * 1、第一次遍历，记录每行每列的服务器的个数，并记录服务器的总数；
     * 2、第二次遍历，记录有服务器的节点上，对应行和列的服务器数量，如果大于1则表示可以通信；
     * 3、总数减不可通信的服务器
     */
    /**
     * 645. 错误的集合
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        if (nums.length < 2) {
            return null;
        }
        int[] res = new int[2];
        BitSet bitSet = new BitSet(nums.length);
        for (int num : nums) {
            if (bitSet.get(num)) {
                res[0] = num;
            }
            bitSet.set(num, true);
        }

        for (int i = 1; i <= nums.length; i++) {
            if (!bitSet.get(i)) {
                res[1] = i;
                break;
            }
        }
        return res;
    }

    /**
     * 1451. 重新排列句子中的单词
     *
     * @param text
     * @return
     */
    public String arrangeWords1(String text) {
        String[] s = text.toLowerCase().split(" ");
        Arrays.sort(s, Comparator.comparingInt(String::length));
        s[0] = s[0].substring(0, 1).toUpperCase() + s[0].substring(1);
        return String.join(" ", s);
    }

    public String arrangeWords(String text) {
        List<String> list = new ArrayList<>();
        for (String s : text.split(" ")) {
            list.add(s);
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        StringBuffer sb = new StringBuffer();
        for (String s : list) {
            sb.append(s.toLowerCase()).append(" ");
        }
        return sb.substring(0, 1).toUpperCase() + sb.substring(1, sb.length() - 1);
    }

    /**
     * 451. 根据字符出现频率排序
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        Map<Character, Integer> map = new TreeMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<Character, Integer> map0 : list) {
            for (int i = 0; i < map0.getValue(); i++) {
                sb.append(map0.getKey());
            }
        }
        return sb.toString();
    }

    public String frequencySort1(String s) {
        int[]  a = new int[127];
        byte[] s1 = s.getBytes();
        int x;
        for (byte k : s1) {
            ++a[k];
        }
        for (int i = -1, i1, j = 0; i != 0; ) {
            i1 = i = x = 0;
            byte n = 32;
            while (n < 127) {
                if (a[n] > i) {
                    i = a[n];
                    x = n;
                }
                ++n;
            }
            for (i1 = i, a[x] = 0; 0 != i1--; s1[j++] = (byte) x) {
                ;
            }
        }
        return new String(s1);
    }

    /**
     * 65. 有效数字
     *
     * @param s
     * @return
     */
    public boolean isNumber1(String s) {

        //        String number = "([+-]?(\\d+(\\.\\d*)?|(\\.\\d+)))";
        //        Pattern base = Pattern.compile(number + "([eE][+-]?\\d+)?");
        //        return base.matcher(s).matches();

        return s.matches("([+-]?(\\d+(\\.\\d*)?|(\\.\\d+)))([eE][+-]?\\d+)?");

    }

    /**
     * 877. 石子游戏
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] > 0;
    }

    /**
     * 852. 山脉数组的峰顶索引
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid - 1]) {
                right = mid;
            } else {
                if (arr[mid] < arr[mid + 1]) {
                    left = mid;
                } else {
                    return mid;
                }
            }
        }
        return left;
    }

    /**
     * 151. 翻转字符串里的单词
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        StringBuffer sb = new StringBuffer();
        String[] strs = s.split(" ");
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].length() > 0) {
                sb.append(strs[i]).append(" ");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 374. 猜数字大小
     *
     * @param n
     * @return
     */
    public int guessNumber(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }

    private int guess(int num) {
        return 0;
    }

    /**
     * 278. 第一个错误的版本
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isBadVersion(int n) {
        return false;
    }

    /**
     * 623. 在二叉树中增加一行
     *
     * @param root
     * @param val
     * @param depth
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        insertRow(root, val, depth, 1);
        return root;
    }

    private void insertRow(TreeNode node, int val, int depth, int curDepth) {
        if (node == null) {
            return;
        }
        if (depth - 1 == curDepth) {
            TreeNode tmp = node.left;
            TreeNode newLeft = new TreeNode(val);
            node.left = newLeft;
            newLeft.left = tmp;

            tmp = node.right;
            TreeNode newRight = new TreeNode(val);
            node.right = newRight;
            newRight.right = tmp;
        } else {
            insertRow(node.left, val, depth, curDepth + 1);
            insertRow(node.right, val, depth, curDepth + 1);
        }
    }

    /**
     * 1449. 数位成本和为目标值的最大数字
     *
     * @param cost
     * @param target
     * @return
     */
    public String largestNumber(int[] cost, int target) {
        int[] dp = new int[target + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < cost.length; i++) {
            for (int j = cost[i]; j <= target; j++) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + 1);
            }
        }

        if (dp[target] <= 0) {
            return "0";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 8, j = target; i >= 0; i--) {
            for (int c = cost[i]; j >= c && dp[j] == dp[j - c] + 1; j -= c) {
                sb.append(i + 1);
            }
        }
        return sb.toString();
    }

    /**
     * 279. 完全平方数
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 518. 零钱兑换 II
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    /**
     * 879. 盈利计划
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length, MOD = (int) 1e9 + 7;
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < members) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]) % MOD;
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[len][j][minProfit]) % MOD;
        }
        return sum;

    }

    /**
     * 1309. 解码字母到整数映射
     *
     * @param s
     * @return
     */
    public String freqAlphabets(String s) {
        String ret = "";
        for (int i = 0; i < s.length(); i++) {
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                int x = (s.charAt(i) - 48) * 10;
                ret += (char) ('0' + x + s.charAt(i + 1));
                i += 2;
            } else {
                ret += (char) ('0' + s.charAt(i));
            }
        }
        return ret;
    }

    /**
     * 1049. 最后一块石头的重量 II
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int target = sum / 2;

        int[][] dp = new int[stones.length][target + 1];

        for (int i = 0; i <= target; i++) {
            dp[0][i] = stones[0] <= i ? stones[0] : 0;
        }

        for (int i = 1; i < stones.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < stones[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                }
            }
        }
        return sum - dp[stones.length - 1][target] * 2;
    }

    /**
     * 494. 目标和
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        List<Integer> sum = new ArrayList<>();
        int[] cnt = new int[1];
        findTargetSumWaysTool(nums, target, 0, cnt, 0);
        return cnt[0];
    }

    public void findTargetSumWaysTool(int[] nums, int target, int sum, int[] cnt, int idx) {
        if (idx == nums.length) {
            if (sum == target) {
                cnt[0]++;
                return;
            }
        } else {
            findTargetSumWaysTool(nums, target, sum + nums[idx], cnt, idx + 1);
            findTargetSumWaysTool(nums, target, sum - nums[idx], cnt, idx + 1);
        }
    }

    /**
     * 474. 一和零
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            int zeros = 0, ones = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            for (int j = m; j >= zeros; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(dp[j - zeros][k - ones] + 1, dp[j][k]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 6. Z 字形变换
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        StringBuffer[] sbs = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuffer();
        }
        int cnt = 0;
        while (cnt < s.length()) {
            for (int i = 0; i < numRows && cnt < s.length(); i++) {
                sbs[i].append(s.charAt(cnt++));
            }
            for (int i = numRows - 2; i > 0 && cnt < s.length(); i--) {
                sbs[i].append(s.charAt(cnt++));
            }
        }
        for (int i = 1; i < numRows; i++) {
            sbs[0].append(sbs[i]);
        }
        return sbs[0].toString();
    }

    /**
     * 160. 相交链表
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode tmpA = headA, tmpB = headB;
        while (tmpA != tmpB) {
            tmpA = tmpA == null ? headB : tmpA.next;
            tmpB = tmpB == null ? headA : tmpB.next;
        }
        return tmpA;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode tmp = headA;
        while (tmp != null) {
            set.add(tmp);
            tmp = tmp.next;
        }

        tmp = headB;
        while (tmp != null) {
            if (set.contains(tmp)) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }

    /**
     * 525. 连续数组
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        HashMap<Integer, Integer> zeroSubOne = new HashMap<>();

        int zeroCnt = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCnt++;
            }
            int tmp = zeroCnt - (i + 1 - zeroCnt);
            if (tmp == 0) {
                max = i + 1;
            }
            if (zeroSubOne.containsKey(tmp)) {
                int idx = zeroSubOne.get(tmp);
                max = Math.max(max, i - idx);
            } else {
                zeroSubOne.put(tmp, i);
            }
        }
        return max;
    }

    /**
     * 523. 连续的子数组和d
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            int a = sum % k;
            if (a == 0 && i >= 1) {
                return true;
            }
            if (map.containsKey(a)) {
                int idx = map.get(a);
                if (i - idx >= 2) {
                    return true;
                }
            } else {
                map.put(a, i);
            }
        }
        return false;
    }

    /**
     * 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
     *
     * @param candiesCount
     * @param queries [favoriteTypei, favoriteDayi, dailyCapi]
     * @return
     */
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        boolean[] ans = new boolean[queries.length];

        int[] sum = new int[candiesCount.length];
        sum[0] = candiesCount[0];

        for (int i = 1; i < candiesCount.length; i++) {
            sum[i] = sum[i - 1] + candiesCount[i];
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            long min = query[1] + 1;
            long max = (long) (query[1] + 1) * query[2];

            long sum1 = query[0] == 0 ? 1 : (sum[query[0] - 1] + 1);
            long sum2 = sum[query[0]];

            ans[i] = !(min > sum2 || max < sum1);
        }
        return ans;
    }

    /**
     * 633. 平方数之和
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int left = 0, right = (int) Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum < c) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    /**
     * 1011. 在 D 天内送达包裹的能力
     *
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int left = Arrays.stream(weights).max().getAsInt(), right = Arrays.stream(weights).sum();

        while (left < right) {
            int day = 1;
            int mid = (left + right) / 2, cur = 0;
            for (int i = 0; i < weights.length; i++) {
                if (cur + weights[i] > mid) {
                    cur = 0;
                    ++day;
                }
                cur += weights[i];
            }
            if (day <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 377. 组合总和 Ⅳ
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    /**
     * 216. 组合总和 III
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        getCombinationSum3(k, n, res, new ArrayList<Integer>(), 1);
        return res;
    }

    private void getCombinationSum3(int k, int target, List<List<Integer>> res, List<Integer> tmpList, int idx) {
        if (target > 0) {
            for (int i = idx; i < 10; i++) {
                if (target - i < 0 || tmpList.size() >= k) {
                    break;
                }
                if (tmpList.contains(i)) {
                    continue;
                }
                tmpList.add(i);
                getCombinationSum3(k, target - i, res, tmpList, i + 1);
                tmpList.remove(tmpList.size() - 1);
            }

        } else if (target == 0 && tmpList.size() == k) {
            List list = new ArrayList<>(tmpList);
            if (!res.contains(list)) {
                res.add(list);
            }
        }
    }

    /**
     * 40. 组合总和 II
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        getCombinationSum2(candidates, res, new ArrayList<Integer>(), new HashSet<Integer>(), target, 0);
        return res;
    }

    private void getCombinationSum2(int[] candinates, List<List<Integer>> res, List<Integer> tmpList, Set<Integer> used, int target, int idx) {
        if (target > 0) {
            for (int i = idx; i < candinates.length && target >= candinates[i]; i++) {
                if (!used.contains(i)) {
                    tmpList.add(candinates[i]);
                    used.add(i);
                    getCombinationSum2(candinates, res, tmpList, used, target - candinates[i], i);
                    tmpList.remove(tmpList.size() - 1);
                    used.remove(i);
                }

            }
        } else if (target == 0) {
            List list = new ArrayList<>(tmpList);
            if (!res.contains(list)) {
                res.add(list);
            }
        }
    }

    /**
     * 39. 组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        getCombinationSum(candidates, res, new ArrayList<Integer>(), target, 0);
        return res;
    }

    private void getCombinationSum(int[] candinates, List<List<Integer>> res, List<Integer> tmpList, int target, int idx) {
        if (target > 0) {
            for (int i = idx; i < candinates.length && target >= candinates[i]; i++) {
                tmpList.add(candinates[i]);
                getCombinationSum(candinates, res, tmpList, target - candinates[i], i);
                tmpList.remove(tmpList.size() - 1);
            }
        } else if (target == 0) {
            res.add(new ArrayList<>(tmpList));
        }
    }


    /**
     * 368. 最大整除子集
     *
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List[] dp = new List[n];
        dp[0] = new ArrayList<Integer>();
        dp[0].add(nums[0]);

        List<Integer> res = dp[0];

        for (int i = 1; i < n; i++) {
            dp[i] = new ArrayList<Integer>();
            dp[i].add(nums[i]);

            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[i].size() < dp[j].size() + 1) {
                        dp[i] = new ArrayList(dp[j]);
                        dp[i].add(nums[i]);
                    }
                }
            }
            if (res.size() < dp[i].size()) {
                res = dp[i];
            }
        }
        return res;
    }

    /**
     * 1. 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 363. 矩形区域不超过 K 的最大数值和
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
        // O(cols ^ 2 * rows)
        for (int l = 0; l < cols; l++) { // 枚举左边界
            int[] rowSum = new int[rows]; // 左边界改变才算区域的重新开始
            for (int r = l; r < cols; r++) { // 枚举右边界
                for (int i = 0; i < rows; i++) { // 按每一行累计到 dp
                    rowSum[i] += matrix[i][r];
                }
                // 求 rowSum 连续子数组 的 和
                // 和 尽量大，但不大于 k
                max = Math.max(max, getMax(rowSum, k));
            }
        }
        return max;
    }

    private int getMax(int[] arr, int k) {
        int rollSum = arr[0], rollMax = rollSum;
        // O(rows)
        for (int i = 1; i < arr.length; i++) {
            if (rollSum > 0) {
                rollSum += arr[i];
            } else {
                rollSum = arr[i];
            }
            if (rollSum > rollMax) {
                rollMax = rollSum;
            }
        }
        if (rollMax <= k) {
            return rollMax;
        }
        // O(rows ^ 2)
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < arr.length; l++) {
            int sum = 0;
            for (int r = l; r < arr.length; r++) {
                sum += arr[r];
                if (sum > max && sum <= k) {
                    max = sum;
                }
                if (max == k) {
                    return k; // 尽量提前
                }
            }
        }
        return max;
    }

    /**
     * 91. 解码方法
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    /**
     * 28. 实现 strStr()
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int n = needle.length();
        if (n == 0 || (n == haystack.length() && haystack.equals(needle))) {
            return 0;
        }
        for (int i = 0; i < haystack.length() - n + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0) && haystack.substring(i, n + i).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
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

    /**
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        //判断字符个数是否一致
        for (int i = 0; i < s1.length(); i++) {
            map1.put(s1.charAt(i), map1.getOrDefault(s1.charAt(i), 0) + 1);
            map2.put(s2.charAt(i), map2.getOrDefault(s2.charAt(i), 0) + 1);
        }
        for (Character ch : map1.keySet()) {
            if (!map1.get(ch).equals(map2.get(ch))) {
                return false;
            }
        }

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i)) || isScramble(s1.substring(0, i), s2.substring(s1.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s1.length() - i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 26. 删除有序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length, left = 0, right = 1;
        if (n < 2) {
            return n;
        }
        while (right < n) {
            while (right < n && nums[left] >= nums[right]) {
                right++;
            }
            left++;
            if (left != right) {
                nums[left] = nums[right];
            }
            if (right == n - 1) {
                break;
            }
        }
        return left + 1;
    }

    /**
     * 220. 存在重复元素 III
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            Long num = 1L * nums[i];
            Long l = set.floor(num);
            Long b = set.ceiling(num);
            if (null != l && num - l <= t) {
                return true;
            }
            if (null != b && b - num <= t) {
                return true;
            }
            set.add(num);
            if (i >= k) {
                set.remove(nums[i - k] * 1L);
            }
        }
        return false;
    }

    /**
     * 213. 打家劫舍 II
     *
     * @param nums
     * @return
     */
    /**
     * 198. 打家劫舍
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        return Math.max(robTool(Arrays.copyOfRange(nums, 0, n - 1)), robTool(Arrays.copyOfRange(nums, 1, n)));
    }

    public int robTool(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }

    /**
     * 208. 实现 Trie (前缀树)
     */
    class Trie {

        Trie[] children;
        private boolean isEnd;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (null == node.children[idx]) {
                    node.children[idx] = new Trie();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return null != node && node.isEnd;

        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return null != searchPrefix(prefix);
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }


    /**
     * 208. 实现 Trie (前缀树) using set
     */
    class Trie1 {

        Set<String> set;

        /**
         * Initialize your data structure here.
         */
        public Trie1() {
            set = new HashSet<>();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            set.add(word);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return set.contains(word);
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            for (String str : set) {
                if (str.startsWith(prefix)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 79. 单词搜索
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (exsitDFS(board, word, visited, 0, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean exsitDFS(char[][] board, String word, boolean[][] visited, int wordIdx, int row, int col) {
        if (!(row >= 0 && row < board.length && col >= 0 && col < board[0].length) || board[row][col] != word.charAt(wordIdx) || visited[row][col]) {
            return false;
        }
        if (wordIdx == word.length() - 1) {
            return true;
        }
        visited[row][col] = true;
        if (exsitDFS(board, word, visited, wordIdx + 1, row - 1, col)) {
            return true;
        }
        if (exsitDFS(board, word, visited, wordIdx + 1, row + 1, col)) {
            return true;
        }
        if (exsitDFS(board, word, visited, wordIdx + 1, row, col - 1)) {
            return true;
        }
        if (exsitDFS(board, word, visited, wordIdx + 1, row, col + 1)) {
            return true;
        }
        visited[row][col] = false;
        return false;
    }

    /**
     * 783. 二叉搜索树节点最小距离
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) - list.get(i - 1) < min) {
                min = list.get(i) - list.get(i - 1);
            }
        }
        return min;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    /**
     * 264. 丑数 II
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    /**
     * 263. 丑数
     *
     * @param n
     * @return
     */
    public boolean isUgly(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 2 == 0) {
            n = n / 2;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }
        while (n % 4 == 0) {
            n = n / 4;
        }
        return n == 1;
    }

    /**
     * 179. 最大数
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        PriorityQueue<String> maxhHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o2.compareTo(o1);
                } else {
                    return (o2 + o1).compareTo(o1 + o2);
                }
            }
        });
        for (int i = 0; i < nums.length; i++) {
            maxhHeap.add(String.valueOf(nums[i]));
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nums.length; i++) {
            sb.append(maxhHeap.remove());
        }
        while (sb.length() >= 2 && sb.charAt(0) == '0' && sb.charAt(1) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * 456. 132模式
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] min = new int[n];
        min[0] = nums[0];
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }

        Deque<Integer> stack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > min[i]) {
                while (!stack.isEmpty() && stack.peek() <= min[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[i]) {
                    return true;
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }

    /**
     * 341. 扁平化嵌套列表迭代器
     */
    public class NestedIterator implements Iterator<Integer> {
        private Deque<Iterator<NestedInteger>> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new LinkedList<Iterator<NestedInteger>>();
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return stack.peek().next().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                Iterator<NestedInteger> iterator = stack.peek();
                if (!iterator.hasNext()) {
                    stack.pop();
                    continue;
                }
                NestedInteger nestedInteger = iterator.next();
                // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
                if (nestedInteger.isInteger()) {
                    List<NestedInteger> list = new ArrayList<NestedInteger>();
                    list.add(nestedInteger);
                    stack.push(list.iterator());
                    return true;
                }
                stack.push(nestedInteger.getList().iterator());
            }
            return false;
        }
    }

    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /**
     * 1603. 设计停车系统
     */
    static class ParkingSystem {

        int big;
        int medium;
        int small;

        public ParkingSystem(int big, int medium, int small) {
            this.big = big;
            this.medium = medium;
            this.small = small;
        }

        public boolean addCar(int carType) {
            if (carType == 1) {
                if (big > 0) {
                    big--;
                    return true;
                }
            } else if (carType == 2) {
                if (medium > 0) {
                    medium--;
                    return true;
                }
            } else if (carType == 3) {
                if (small > 0) {
                    small--;
                    return true;
                }
            } else {
                return false;
            }
            return false;
        }
    }

    /**
     * 150. 逆波兰表达式求值
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int post = 0, pre = 0;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    post = stack.pop();
                    pre = stack.pop();
                    stack.push(pre + post);
                    break;
                case "-":
                    post = stack.pop();
                    pre = stack.pop();
                    stack.push(pre - post);
                    break;

                case "*":
                    post = stack.pop();
                    pre = stack.pop();
                    stack.push(pre * post);
                    break;

                case "/":
                    post = stack.pop();
                    pre = stack.pop();
                    stack.push(pre / post);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    /**
     * 73. 矩阵置零
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        BitSet rawSet = new BitSet(matrix.length);
        BitSet colSet = new BitSet(matrix[0].length);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rawSet.set(i);
                    colSet.set(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rawSet.get(i) || colSet.get(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 92. 反转链表 II
     */
    public ListNode reverseBetween1(ListNode head, int left, int right) {
        int length = 0;
        ListNode tmpHead = head;
        while (null != tmpHead) {
            tmpHead = tmpHead.next;
            length++;
        }
        if (length < right || left == right) {
            return head;
        }

        int cnt = 1;
        ListNode realHead = new ListNode(-1);
        realHead.next = head;
        tmpHead = realHead;
        while (cnt < left) {
            tmpHead = tmpHead.next;
            cnt++;
        }

        ListNode cur = tmpHead.next, post = cur.next;
        while (cnt < right) {
            cnt++;
            ListNode tmp = post.next;
            post.next = cur;
            cur = post;
            post = tmp;
        }

        tmpHead.next.next = post;
        tmpHead.next = cur;

        return realHead.next;
    }

    /**
     * 59. 螺旋矩阵 II
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int count = 1, row = 0, col = 0, endRow = n - 1, endCol = n - 1;
        while (count <= n * n) {
            for (int i = col; i <= endCol; i++) {
                res[row][i] = count++;
            }
            if (count == 0) {
                return res;
            }

            for (int i = row + 1; i <= endRow; i++) {
                res[i][endCol] = count++;
            }
            if (count == 0) {
                return res;
            }

            for (int i = endCol - 1; i >= col; i--) {
                res[endRow][i] = count++;
            }
            if (count == 0) {
                return res;
            }

            for (int i = endRow - 1; i > row; i--) {
                res[i][col] = count++;
            }
            if (count == 0) {
                return res;
            }

            row++;
            col++;
            endRow--;
            endCol--;
        }
        return res;
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return new int[0];
        }
        int r = matrix.length, c = matrix[0].length;
        int[] res = new int[r * c];
        int count = 0, row = 0, col = 0, endRow = r - 1, endCol = c - 1;
        while (count < res.length) {
            for (int i = col; i <= endCol; i++) {
                res[count++] = matrix[row][i];
            }
            if (count == res.length) {
                return res;
            }

            for (int i = row + 1; i <= endRow; i++) {
                res[count++] = matrix[i][endCol];
            }
            if (count == res.length) {
                return res;
            }

            for (int i = endCol - 1; i >= col; i--) {
                res[count++] = matrix[endRow][i];
            }
            if (count == res.length) {
                return res;
            }

            for (int i = endRow - 1; i > row; i--) {
                res[count++] = matrix[i][col];
            }
            if (count == res.length) {
                return res;
            }

            row++;
            col++;
            endRow--;
            endCol--;
        }
        return res;
    }

    /**
     * 706. 设计哈希映射
     */
    class MyHashMap {
        private final int MAX = 1000001;

        int[] map;

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            map = new int[MAX];
            Arrays.fill(map, -1);
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            map[key] = value;
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            return map[key];
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            map[key] = -1;
        }
    }

    /**
     * 705. 设计哈希集合
     */
    class MyHashSet {
        BitSet hashSet;
        final int MAX_LENGTH = 1000001;

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
            hashSet = new BitSet(MAX_LENGTH);
        }

        public void add(int key) {
            if (hashSet.get(key)) {
                return;
            }
            hashSet.set(key);
        }

        public void remove(int key) {
            if (hashSet.get(key)) {
                hashSet.set(key, false);
            }
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            return hashSet.get(key);
        }
    }

    /**
     * 331. 验证二叉树的前序序列化
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        Stack<Integer> stack = new Stack<>();
        int n = preorder.length();
        stack.push(1);
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                return false;
            }
            if (Character.isDigit(preorder.charAt(i))) {
                while (i < n && Character.isDigit(preorder.charAt(i))) {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            } else if ('#' == preorder.charAt(i)) {
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
            } else {
                continue;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 227. 基本计算器 II
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char operator = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            }
            if (!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1) {
                switch (operator) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(num * stack.pop());
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                num = 0;
                operator = ch;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    /**
     * 480. 滑动窗口中位数
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n < k) {
            return null;
        }
        PriorityQueue<Double> small = new PriorityQueue<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return (int) (o2 - o1);
            }
        });
        PriorityQueue<Double> big = new PriorityQueue<>();

        //   initialize
        for (int i = 0; i < k; i++) {
            small.add((double) nums[i]);
        }
        for (int i = 0; i < k / 2; i++) {
            big.add(small.poll());
        }

        double[] res = new double[n - k + 1];
        int idx = 0;
        res[idx++] = getMid(small, big);
        for (int i = k; i < n; i++) {
            double tmp = nums[i], toDel = nums[i - k];

            if (tmp <= small.peek()) {
                small.add(tmp);
            } else {
                big.add(tmp);
            }

            if (toDel <= small.peek()) {
                small.remove(toDel);
            } else {
                big.remove(toDel);
            }

            while (big.size() > small.size()) {
                small.add(big.poll());
            }
            while (small.size() - 1 > big.size()) {
                big.add(small.poll());
            }

            res[idx++] = getMid(small, big);
        }
        return res;
    }

    private double getMid(PriorityQueue<Double> small, PriorityQueue<Double> big) {
        if (small.size() > big.size()) {
            return small.peek();
        } else {
            return big.peek() / 2.0 + small.peek() / 2.0;
        }
    }

    /**
     * 503. 下一个更大元素 II
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(res, -1);
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek() % n] < nums[i % n]) {
                res[stack.pop() % n] = nums[i % n];
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 896. 单调数列
     */
    public boolean isMonotonic(int[] A) {
        if (A.length <= 2) {
            return true;
        }
        int flag = 0; //0:=  1:<  2:>
        for (int i = 1; i < A.length; i++) {
            int diff = A[i] - A[i - 1];
            if (flag == 0) {
                if (diff > 0) {
                    flag = 1;
                } else if (diff < 0) {
                    flag = 2;
                } else {
                    continue;
                }
            } else if (flag == 1) {
                if (diff < 0) {
                    return false;
                }
            } else {
                if (diff > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 395. 至少有K个重复字符的最长子串
     */
    public int longestSubstring(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (char ch : map.keySet()) {
            if (map.get(ch) < k) {
                int cnt = 0;
                for (String str : s.split(String.valueOf(ch))) {
                    cnt = Math.max(cnt, longestSubstring(str, k));
                }
                return cnt;
            }
        }
        return s.length();
    }

    /**
     * 856.括号的分数
     */
    public int scoreOfParentheses(String S) {
        if (null == S || S.length() == 0) {
            return 0;
        }
        Stack<Character> stack = new Stack<>();
        int score = 0;
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '(') {
                stack.push(ch);
            } else {
                stack.pop();
                if (chars[i - 1] == '(') {
                    score += Math.pow(2, stack.size());
                }
            }
        }
        return score;
    }

    /**
     * 123. 买卖股票的最佳时机 III
     */
    public int maxProfitIII2(int[] prices) {
        int n = prices.length;
        int[] dp = new int[5];
        dp[0] = 0;
        dp[1] = -prices[0];
        dp[3] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[0] = dp[0];
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
            dp[2] = Math.max(dp[2], dp[1] + prices[i]);
            dp[3] = Math.max(dp[3], dp[2] - prices[i]);
            dp[4] = Math.max(dp[4], dp[3] + prices[i]);
        }
        return dp[4];
    }

    public int maxProfitIII(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[n - 1][4];
    }

    /**
     * 122. 买卖股票的最佳时机 II
     */
    public int maxProfitII(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 121. 买卖股票的最佳时机
     */
    public int maxProfit(int[] prices) {
        int max = 0, min = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price <= min) {
                min = price;
            } else {
                max = Math.max(max, price - min);
            }
        }
        return max;
    }

    /**
     * 413. 等差数列划分
     */
    public int numberOfArithmeticSlices(int[] A) {
        if (null == A || A.length <= 2) {
            return 0;
        }
        int[] diff = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            diff[i] = A[i] - A[i - 1];
        }
        int left = 1, right = 1, res = 0;
        while (right < diff.length) {
            while (right < diff.length && diff[left] == diff[right]) {
                right++;
            }
            int cnt = right - left + 1;
            if (cnt > 2) {
                res += (cnt + 1) * cnt / 2 - 2 * cnt + 1;
            }
            left = right;
        }
        return res;
    }

    /**
     * 697. 数组的度
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int[] a = map.get(nums[i]);
                a[0]++;
                a[2] = i;
            } else {
                int[] a = {1, i, i};
                map.put(nums[i], a);
            }
        }
        int degree = 0, minLen = Integer.MIN_VALUE;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            if (degree < entry.getValue()[0]) {
                degree = entry.getValue()[0];
                minLen = entry.getValue()[2] - entry.getValue()[1] + 1;
            } else if (degree == entry.getValue()[0]) {
                minLen = Math.min(minLen, entry.getValue()[2] - entry.getValue()[1] + 1);
            }
        }
        return minLen;
    }

    private int getDegree(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            max = Math.max(max, map.get(num));
        }
        return max;
    }

    public int findShortestSubArray1(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int maxCnt = 1, shortest = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            int cnt = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    cnt++;
                }
                if (maxCnt < cnt) {
                    maxCnt = cnt;
                    shortest = j - i + 1;
                } else if (maxCnt == cnt) {
                    shortest = Math.min(j - i + 1, shortest);
                }
            }
        }
        return shortest;
    }

    /**
     * 最大连续1的个数 III
     */
    public int longestOnes(int[] A, int K) {
        int max = Integer.MIN_VALUE, zero = 0, left = 0;

        for (int right = 0; right < A.length; right++) {
            if (A[right] == 0) {
                zero++;
            }
            while (zero > K) {
                if (A[left] == 0) {
                    zero--;
                }
                left++;
            }
            max = Math.max(right - left + 1, max);
        }
        return max;
    }

    /**
     * 539. 最小时间差
     */
    public int findMinDifference(List<String> timePoints) {
        int[] mins = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            mins[i] = computeMinute(timePoints.get(i));
        }
        Arrays.sort(mins);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < mins.length; i++) {
            min = Math.min(min, mins[i] - mins[i - 1]);
            if (min == 0) {
                return min;
            }
        }
        return Math.min(min, 1440 + mins[0] - mins[mins.length - 1]);
    }

    public int computeMinute(String s) {
        String[] strs = s.split(":");
        return Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
    }

    /**
     * 804. 唯一摩尔斯密码词
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] Morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < words[i].length(); j++) {
                sb.append(Morse[words[i].charAt(j) - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    /**
     * 20. 表示数值的字符串
     */
    public boolean isNumber(String s) {
        return s.matches("[-/+]{0,1}[0-9]{1,}[.]{0,1}]");
    }

    /**
     * 58 - II. 左旋转字符串
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);

    }

    /**
     * 995. K 连续位的最小翻转次数
     */
    public int minKBitFlips(int[] A, int K) {
        int cnt = 0;
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            if (!queue.isEmpty() && queue.getLast() == i - K) {
                queue.pollLast();
            }
            if ((queue.size() + A[i]) % 2 == 0) {
                if (i + K > A.length) {
                    return -1;
                }
                cnt++;
                queue.push(i);
            }
        }
        return cnt;
    }

    /**
     * 笔试题
     */
    public String maxSubLenNum(String s) {
        String[] strs = s.split("\\.");
        if (strs == null || strs.length < 2) {
            return "";
        }
        boolean[] flag = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            flag[i] = isNum(strs[i]);
        }
        int max = 0, maxIdx = 0;
        for (int i = 1; i < strs.length; i++) {
            if (flag[i] && flag[i - 1]) {
                if (strs[i].length() + strs[i - 1].length() >= max) {
                    max = strs[i].length() + strs[i - 1].length();
                    maxIdx = i;
                }
            }
        }
        if (maxIdx == 0) {
            return "";
        } else {
            return strs[maxIdx - 1] + "." + strs[maxIdx];
        }
    }

    public boolean isNum(String s) {
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= 48 && s.charAt(i) <= 57)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 566. 重塑矩阵
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int n = nums.length, m = nums[0].length;
        if (n * m != r * c) {
            return nums;
        }
        int[][] res = new int[r][c];
        int cnt = 0;
        while (cnt < n * m) {
            res[cnt / c][cnt % c] = nums[cnt / m][cnt % m];
            cnt++;
        }
        return res;
    }

    /**
     * 561. 数组拆分 I
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            cnt += nums[i];
        }
        return cnt;
    }

    /**
     * 485. 最大连续1的个数
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, idx = 0;
        while (idx < nums.length) {
            int cnt = 0;
            while (idx < nums.length && nums[idx] == 1) {
                idx++;
                cnt++;
            }
            idx++;
            max = max > cnt ? max : cnt;
        }
        return max;
    }

    /**
     * 448. 找到所有数组中消失的数字
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        BitSet bs = new BitSet();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            bs.set(nums[i] - 1, true);
        }
        for (int i = 0; i < nums.length; i++) {
            if (bs.get(i) == false) {
                res.add(i + 1);
            }
        }
        return res;
    }

    /**
     * 119. 杨辉三角 II
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> lists = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i < rowIndex + 1; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j <= i; j++) {
                list.add(lists.get(i - 1).get(j - 1) + lists.get(i - 1).get(j));
            }
            list.add(0);
            lists.add(list);
        }
        return lists.get(rowIndex).subList(0, lists.get(rowIndex).size() - 1);
    }

    /**
     * 703. 数据流中的第 K 大元素
     */
    PriorityQueue<Integer> kthArrQueuq;
    int kTh = 0;

    public LeetCodeTrainning(int k, int[] nums) {
        kthArrQueuq = new PriorityQueue<>();
        kTh = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        kthArrQueuq.offer(val);
        if (kthArrQueuq.size() > kTh) {
            kthArrQueuq.poll();
        }
        return kthArrQueuq.peek();
    }


    /**
     * 567. 字符串的排列
     */
    public boolean checkInclusion1(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            if (map.containsKey(s1.charAt(i))) {
                map.put(s1.charAt(i), map.get(s1.charAt(i)) + 1);
            } else {
                map.put(s1.charAt(i), 1);
            }
        }
        for (int i = 0; i < s1.length(); i++) {
            if (map.containsKey(s2.charAt(i))) {
                map.put(s2.charAt(i), map.get(s2.charAt(i)) - 1);
            }
        }

        if (checkMapValueZero(map)) {
            return true;
        }
        int left = 0, right = s1.length();
        while (right < s2.length()) {
            if (map.containsKey(s2.charAt(left))) {
                map.put(s2.charAt(left), map.get(s2.charAt(left)) + 1);
            }
            if (map.containsKey(s2.charAt(right))) {
                map.put(s2.charAt(right), map.get(s2.charAt(right)) - 1);
            }
            left++;
            right++;
            if (checkMapValueZero(map)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkMapValueZero(Map<Character, Integer> map) {
        boolean[] isAllEmpty = new boolean[1];
        isAllEmpty[0] = true;
        map.forEach((k, v) -> {
            if (v != 0) {
                isAllEmpty[0] = false;
            }
        });
        return isAllEmpty[0];
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }

        int[] letterCount = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letterCount[s1.charAt(i) - 'a']++;
        }
        int[] letterCountTmp = Arrays.copyOf(letterCount, letterCount.length);
        boolean flag = false;
        int startIdx = 0;
        for (int i = 0; i < s2.length(); i++) {
            char tmpCh = s2.charAt(i);
            if (letterCountTmp[tmpCh - 'a'] > 0) {
                if (!flag) {
                    flag = true;
                    startIdx = i;
                }
                letterCountTmp[tmpCh - 'a']--;
            } else {
                if (flag) {
                    letterCountTmp = Arrays.copyOf(letterCount, letterCount.length);
                    i = startIdx;
                    flag = false;
                }

            }

            if (checkLettersEmpty(letterCountTmp)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLettersEmpty(int[] letterCount) {
        for (int i = 0; i < letterCount.length; i++) {
            if (letterCount[i] > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 992. Subarrays with K Different Integers
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        return mostWithKDistinct(A, K) - mostWithKDistinct(A, K - 1);
    }

    public int mostWithKDistinct(int[] A, int K) {
        int n = A.length;
        int[] freq = new int[n + 1];
        int tmpCount = 0, left = 0, right = 0, res = 0;
        while (right < n) {
            if (freq[A[right]] == 0) {
                tmpCount++;
            }
            freq[A[right]]++;
            right++;
            while (tmpCount > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    tmpCount--;
                }
                left++;
            }
            res += right - left;
        }
        return res;
    }

    /**
     * 978. Longest Turbulent Subarray
     */
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int res = 1, left = 0, right = 0;
        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    /**
     * 4. Median of Two Sorted Arrays
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] num = new int[nums1.length + nums2.length];

        int idx1 = 0, idx2 = 0, idx = 0;
        if (nums1.length == 0) {
            num = nums2;
        }

        if (nums2.length == 0) {
            num = nums1;
        }
        while (nums1.length > 0 && nums2.length > 0 && idx1 < nums1.length && idx2 < nums2.length) {
            while (idx1 < nums1.length && nums1[idx1] <= nums2[idx2]) {
                num[idx++] = nums1[idx1++];
            }
            if (idx1 == nums1.length) {
                while (idx2 < nums2.length) {
                    num[idx++] = nums2[idx2++];
                }
            }
            while (idx2 < nums2.length && nums2[idx2] <= nums1[idx1]) {
                num[idx++] = nums2[idx2++];
            }
            if (idx2 == nums2.length) {
                while (idx1 < nums1.length) {
                    num[idx++] = nums1[idx1++];
                }
            }
        }

        if (num.length % 2 == 0) {
            return (num[num.length / 2] + num[num.length / 2 - 1]) / 2.0d;
        } else {
            return num[num.length / 2];
        }

    }

    /**
     * 665. Non-decreasing Array
     */
    public boolean checkPossibility(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return true;
        }
        boolean flag = false;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (flag) {
                    return false;
                }
                flag = true;
                if (i > 0 && nums[i - 1] > nums[i + 1]) {
                    nums[i + 1] = nums[i];
                } else {
                    nums[i] = nums[i + 1];
                }
            }

        }
        return true;
    }

    /**
     * 1423. Maximum Points You Can Obtain from Cards
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        if (n < k) {
            return 0;
        }
        int sum = 0;
        for (int card : cardPoints) {
            sum += card;
        }
        int start = 0, end = 0, subSum = 0;
        while (end - start < n - k) {
            subSum += cardPoints[end];
            end++;
        }
        int minSubSum = subSum;
        while (end < n) {
            subSum = subSum - cardPoints[start++] + cardPoints[end++];
            minSubSum = subSum < minSubSum ? subSum : minSubSum;
        }
        return sum - minSubSum;
    }

    /**
     * 1208. Get Equal Substrings Within Budget
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int[] sub = new int[s.length()];
        int res = 0, sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sub[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int start = 0, end = 0;
        while (end < s.length()) {
            sum += sub[end++];
            while (sum > maxCost) {
                sum -= sub[start++];
            }
            res = res > end - start ? res : end - start;
        }
        return res;
    }

    /**
     * 101. Symmetric Tree
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return subIsSymmetric(root.left, root.right);
        //        return check(root, root);
    }

    public boolean subIsSymmetric(TreeNode left, TreeNode right) {
        if (null == left && null == right) {
            return true;
        } else if (null == left || null == right) {
            return false;
        } else {
            if (left.val == right.val) {
                return subIsSymmetric(left.left, right.right) && subIsSymmetric(left.right, right.left);
            } else {
                return false;
            }
        }
    }

    public boolean check(TreeNode n1, TreeNode n2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(n1);
        queue.offer(n2);
        while (!queue.isEmpty()) {
            n1 = queue.poll();
            n2 = queue.poll();
            if (n1 == null && n2 == null) {
                continue;
            }
            if ((n1 == null || n2 == null) || (n1.val != n2.val)) {
                return false;
            }
            queue.offer(n1.left);
            queue.offer(n2.right);

            queue.offer(n1.right);
            queue.offer(n2.left);
        }
        return true;
    }


    /**
     * 1507. Reformat Date
     */

    public String reformatDate(String date) {
        StringBuilder sb = new StringBuilder();
        // get the index of the first whitespace
        int indx = date.indexOf(" ");

        // year is always the last 4 digits
        String year = date.substring(date.length() - 4, date.length());
        // month is always the 3 digits after the whitespace
        String month = date.substring(indx + 1, indx + 4);
        // day is always the first 3-4 chars before the whitespace
        String day = date.substring(0, indx);

        // append year
        sb.append(year);
        // append month
        sb.append(getMonth(month));
        // append day
        sb.append(getDay(day));

        return sb.toString();
    }

    public String getDay(String day) {
        // day is < 10
        if (day.length() == 3) {
            return "0" + day.substring(0, 1);
        }
        // day is >= 10
        return day.substring(0, 2);
    }

    public String getMonth(String month) {
        switch (month) {
            case "Jan":
                return "-01-";
            case "Feb":
                return "-02-";
            case "Mar":
                return "-03-";
            case "Apr":
                return "-04-";
            case "May":
                return "-05-";
            case "Jun":
                return "-06-";
            case "Jul":
                return "-07-";
            case "Aug":
                return "-08-";
            case "Sep":
                return "-09-";
            case "Oct":
                return "-10-";
            case "Nov":
                return "-11-";
            case "Dec":
                return "-12-";
        }
        return "";
    }

    /**
     * 322. Coin Change
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static int dfsCoinChange(int[] coins, int leftAmount, Map<Integer, Integer> map) {
        return 0;
    }

    /**
     * 219. Contains Duplicate II
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int j = map.get(nums[i]);
                if (i - j <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }


    /**
     * 560. Subarray Sum Equals K
     */
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            count += preSumMap.getOrDefault(sum - k, 0);
            preSumMap.put(sum, preSumMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /**
     * 5614. Find the Most Competitive Subsequence
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] mostCompetitive2(int[] nums, int k) {
        int pre = 0, post = nums.length - k, idx = 0;
        int[] res = new int[k];
        while (idx < k) {
            int[] minValue = new int[post - pre + 1];
            minValue[0] = nums[pre];
            for (int i = 1; i < post - pre + 1; i++) {
                minValue[i] = minValue[i - 1] > nums[i + pre] ? nums[i + pre] : minValue[i - 1];
            }
            res[idx] = minValue[post - pre];
            for (int i = pre; i <= post; i++) {
                if (res[idx] == nums[i]) {
                    pre = i + 1;
                    break;
                }
            }
            idx++;
            post = nums.length - k + idx;
        }
        return res;
    }

    public static int[] mostCompetitive1(int[] nums, int k) {
        int n = nums.length;
        int[][] minValue = new int[n][n];
        for (int i = 0; i < n; i++) {
            minValue[i][i] = nums[i];
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                minValue[i][j] = minValue[i][j - 1] > nums[j] ? nums[j] : minValue[i][j - 1];
            }
        }
        int pre = 0, post = nums.length - k, idx = 0;
        int[] res = new int[k];
        while (idx < k) {
            res[idx] = minValue[pre][post];
            for (int i = pre; i <= post; i++) {
                if (res[idx] == nums[i]) {
                    pre = i + 1;
                    break;
                }
            }
            idx++;
            post = nums.length - k + idx;
        }
        return res;
    }

    public static int[] mostCompetitive(int[] nums, int k) {
        if (nums.length <= k) {
            return nums;
        }
        List<Integer> list = new ArrayList<>();
        findMin(nums, list, k, 0, nums.length - k + list.size() + 1);
        int[] res = new int[k];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private static void findMin(int[] nums, List<Integer> list, int k, int pre, int end) {
        if (list.size() >= k) {
            return;
        }
        int min = nums[pre];
        int index = pre;
        for (int i = pre; i < end; i++) {
            if (min > nums[i]) {
                min = nums[i];
                index = i;
            }
        }
        list.add(min);
        findMin(nums, list, k, index + 1, nums.length - k + list.size() + 1);
    }

    /**
     * 5615. Minimum Moves to Make Array Complementary
     *
     * @param nums
     * @param limit
     * @return
     */
    public static int minMoves(int[] nums, int limit) {
        int pre = 0, post = nums.length - 1;
        int max = Integer.MIN_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (pre < post) {
            int sum = nums[pre] + nums[post];
            if (map.containsKey(sum)) {
                map.replace(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
            max = max > map.get(sum) ? max : map.get(sum);
            pre++;
            post--;
        }
        return nums.length / 2 - max;
    }

    /**
     * 92. Reverse Linked List II
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode tmpHead = new ListNode();
        tmpHead.next = head;
        ListNode prev = tmpHead;
        ListNode cur = head;
        int i = 0;
        while (i < m) {
            prev = cur;
            cur = prev.next;
            i++;
        }
        ListNode pre = null;
        while (i <= n) {
            ListNode post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
            i++;
        }

        ListNode tail = prev.next;
        prev.next = pre;
        tail.next = cur;
        return tmpHead.next;
    }

    /**
     * 5606. Smallest String With A Given Numeric Value
     */
    public static String getSmallestString(int n, int k) {
        int MaxInt = 26;
        int[] res = new int[n];
        int leftCount = n;
        while (k > 0 && leftCount > 0) {
            int tmp = MaxInt;
            while (!((leftCount - 1) <= (k - tmp))) {
                tmp--;
            }
            res[--leftCount] = tmp;
            k -= tmp;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int tmp = 96 + res[i];
            char c = (char) tmp;
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 5607. Ways to Make a Fair Array
     */
    public static int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[][] sums = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            sums[i] = new int[2];
        }
        sums[0][0] = sums[0][1] = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                sums[i + 1][0] = nums[i] + sums[i][0];
                sums[i + 1][1] = sums[i][1];
            } else {
                sums[i + 1][1] = nums[i] + sums[i][1];
                sums[i + 1][0] = sums[i][0];
            }
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int oddSum = sums[n][1] - sums[i][1] + sums[i - 1][0];
            int evenSum = sums[n][0] - sums[i][0] + sums[i - 1][1];
            if (oddSum == evenSum) {
                count++;
            }
        }

        return count;
    }

    /**
     * 1091. Shortest Path in Binary Matrix
     */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int[] idx = new int[2];
        idx[0] = idx[1] = 0;
        int res = search(grid, 0, 0, 0);
        return res == Integer.MAX_VALUE ? -1 : res + 1;
    }

    private static int search(int[][] grid, int row, int col, int length) {
        if (row == col && row == grid.length - 1) {
            if (grid[row][col] == 0) {
                return length;
            }
        }
        if (grid[row][col] == 1) {
            return Integer.MAX_VALUE;
        }
        length++;
        int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;

        if (col < grid.length - 1) {
            left = search(grid, row, col + 1, length);
        }

        if (row < grid.length - 1) {
            right = search(grid, row + 1, col, length);
        }

        if (col < grid.length - 1 && col < grid.length - 1) {
            mid = search(grid, row + 1, col + 1, length);
        }

        return Math.min(Math.min(left, right), mid);
    }

    /**
     * 5601. Design an Ordered Stream
     */
    static class OrderedStream {

        int times = 0;
        int ptr = 0;
        String[] strings;

        public OrderedStream(int n) {
            times = n;
            strings = new String[n];
        }

        public List<String> insert(int id, String value) {
            List<String> res = new ArrayList<>();
            strings[id - 1] = value;
            while (ptr < times && strings[ptr] != null && strings[ptr].length() > 0) {
                res.add(strings[ptr]);
                ptr++;
            }
            return res;
        }
    }

    /**
     * 5602. Minimum Operations to Reduce X to Zero
     */
    public static int minOperations(int[] nums, int x) {
        int n = nums.length;
        int[][] res = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            res[i][0] = x;
            res[0][i] = x;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                res[i][j] = res[i][j - 1] - (x - res[j - 1][i] + nums[j - 1]);
            }

        }
        return x;
    }

    /**
     * 210. Course Schedule II
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] preNodeNum = new int[numCourses];  //前面有多少节点
        for (int i = 0; i < numCourses; i++) {
            preNodeNum[i] = 0;
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prer : prerequisites) {
            if (map.containsKey(prer[1])) {
                map.get(prer[1]).add(prer[0]);
            } else {
                List tmpList = new ArrayList<>();
                tmpList.add(prer[0]);
                map.put(prer[1], tmpList);
            }
            preNodeNum[prer[0]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (preNodeNum[i] == 0) {
                queue.offer(i);
            }
        }
        int[] res = new int[numCourses];
        Set<Integer> set = new HashSet<>();
        int index = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int key = queue.poll();
                if (set.add(key)) {
                    res[index++] = key;
                }
                numCourses--;
                if (map.containsKey(key)) {
                    for (Integer val : map.get(key)) {
                        if (--preNodeNum[val] <= 0) {
                            queue.offer(val);
                        }
                    }
                }
            }
        }
        return numCourses == 0 ? res : new int[0];
    }


    public static void listNodeTest() {
        ListNode root = new ListNode(1);
        ListNode tmp = root;
        for (int i = 2; i <= 10; i++) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }

        tmp = reverseNodes(root, 3);
        while (tmp != null) {
            System.out.print(tmp.val + " ");
            tmp = tmp.next;
        }
    }

    /**
     * reverse list nodes by k
     */
    public static ListNode reverseNodes(ListNode head, int k) {
        if (null == head || null == head.next) {
            return head;
        }
        int num = 0;
        ListNode current = head;
        while (current != null) {
            num++;
            current = current.next;
        }
        if (num < k) {
            return head;
        }
        int times = num / k;
        ListNode tmpHead = new ListNode(-1);
        ListNode pre = null, tail, post = null;
        pre = head;
        for (int i = 0; i < times; i++) {
            int nodesNum = 0;
            tail = tmpHead;
            tmpHead = pre;
            current = pre.next;
            while (current != null && nodesNum < k - 1) {
                nodesNum++;
                post = current.next;
                current.next = pre;
                pre = current;
                current = post;
            }
            tail.next = pre;
            pre = current;
            if (i == 0) {
                head = tail.next;
            }
        }
        tmpHead.next = post;
        return head;
    }

    /**
     * reverse list nodes
     */
    public static ListNode reverseNodes(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode current = head;
        ListNode pre = null;
        ListNode post;
        while (current != null) {
            post = current.next;
            current.next = pre;
            pre = current;
            current = post;
        }
        return pre;
    }

    /**
     * 148. Sort List
     */
    public static ListNode sortList(ListNode head) {
        if (null == head) {
            return head;
        }

        ArrayList<Integer> values = new ArrayList<>();
        ListNode tmp = head;
        while (null != tmp) {
            values.add(tmp.val);
            tmp = tmp.next;
        }

        Collections.sort(values);

        int idx = 0;
        tmp = head;
        while (null != tmp) {
            tmp.val = values.get(idx++);
            tmp = tmp.next;
        }
        return head;
    }


    /**
     * 72. Edit Distance
     */
    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < word2.length() + 1; i++) {
            dp[0][i] = i;
        }
        char c1, c2;
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                c1 = word1.charAt(i - 1);
                c2 = word2.charAt(j - 1);
                dp[i][j] = c1 == c2 ? dp[i - 1][j - 1] : min(dp[i - 1][j], dp[i - 1][j - 1], dp[i][j - 1]) + 1;
            }
        }

        return dp[word1.length()][word2.length()];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * 155. Min Stack
     */
    class MinStack {
        Stack<Pair> stack = new Stack<>();

        public MinStack() {

        }

        public void push(int x) {
            if (stack.empty()) {
                stack.push(new Pair(x, x));
            } else {
                int min = stack.peek().min;
                min = x < min ? x : min;
                stack.push(new Pair(x, min));
            }
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek().x;
        }

        public int getMin() {
            return stack.peek().min;
        }

        class Pair {
            int x;
            int min;

            public Pair(int x, int min) {
                this.x = x;
                this.min = min;

            }
        }
    }

    /**
     * ListNode
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            stack1.push(x);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            return stack1.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            return stack1.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stack1.isEmpty();
        }
    }
}
