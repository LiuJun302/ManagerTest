package CodingTrain;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCodeTreeTrainning {

    public static void main(String[] args) {

    }
    public static void test(){
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node3.left = node4;
        node3.right = node5;

        root.left = node2;
        root.right = node3;

        Codec codec = new Codec();
        String code = codec.serialize(root);
        System.out.println(code);

        TreeNode resultRoot = codec.deserialize(code);
        System.out.println();
    }

    /**
     * 96. Unique Binary Search Trees
     */
    public int numTrees(int n) {
        int [] dp = new int [n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i=2; i<=n; i++) {
            for (int j=1; j<=i; j++) {
                // j-1 on left * n-i on right + dp[i]
                dp[i] = dp[i] + (dp[i-j] * dp[j-1]);
            }
        }
        return dp[n];
    }

    /**
     * 297. Serialize and Deserialize Binary Tree
     */
    static class Codec {
        final private String NULL = "#";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuffer sb = new StringBuffer();
            Queue<TreeNode> treeQueue = new LinkedList<>();
            if(root == null)
                return sb.append(NULL).toString();

            treeQueue.add(root);
            while (!treeQueue.isEmpty()){
                TreeNode tmpNode = treeQueue.poll();

                if(tmpNode != null){
                    sb.append(tmpNode.val + ",");
                    treeQueue.offer(tmpNode.left);
                    treeQueue.offer(tmpNode.right);
                }else {
                    sb.append(NULL +  ",");
                }
            }
            return sb.substring(0, sb.length() - 1  );
        }


        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] values = data.split(",");
            if (values[0].equals("#")){
                return null;
            }
            int idx = 0;
            TreeNode root = new TreeNode(Integer.parseInt(values[idx ++]));
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty() && idx < values.length){
                TreeNode tmp = queue.poll();
                if (values[idx].equals("#")){
                    tmp.left = null;
                }else {
                    tmp.left = new TreeNode(Integer.parseInt(values[idx]));
                    queue.add(tmp.left);
                }
                idx ++;

                if (values[idx].equals("#")){
                    tmp.right = null;
                }else {
                    tmp.right = new TreeNode(Integer.parseInt(values[idx]));
                    queue.add(tmp.right);
                }
                idx ++;

            }
            return  root;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
