package CodingTrain;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class TreeOperate {
    static int counter = 0;
    public static void main(String[] args) {
        int[] a = {23,10,0,88,0,0,15,0,34,0,0,0};
        int[] b = {3,4,5,1,3,1,1};
        char[] c = {'A','B','#','C','D','#','#','#','E','#','F','G','H','#','#','K','#'};
        char[] d = {'1','2','3','4','5'};
        TreeOperate to = new TreeOperate();
        TreeNode1 r = null;
        TreeNode1 root = to.CreateTree1(r, b, 0);
        System.out.println();
        int res = to.diameterOfBinaryTree(root);
        System.out.println(res);

    }



    public int diameterOfBinaryTree(TreeNode1 root) {
        int[] res = new int[1];
        diameterOfBinaryTreeHelper(root, res);
        return res[0];
    }
    public void diameterOfBinaryTreeHelper(TreeNode1 root, int[] res){
        if(root == null)
            return;
        int left = depthOfTree(root.left);
        int right = depthOfTree(root.right);
        res[0] = res[0]>(left+right) ? res[0] :(left+right);
        diameterOfBinaryTreeHelper(root.left, res);
        diameterOfBinaryTreeHelper(root.right, res);
    }
    public int depthOfTree(TreeNode1 root){
        if(root == null)
            return 0;
        int left = depthOfTree(root.left)+1;
        int right = depthOfTree(root.right)+1;
        return left>right ? left : right;
    }


    //change left tree with the right one
    public void SwapLeftAndRight(TreeNode root){
        if(root == null)
            return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        SwapLeftAndRight(root.left);
        SwapLeftAndRight(root.right);
    }

    //the number of leaves
    public int LeafNumber(TreeNode root){
        if (root == null)
            return 0;
        if (root.right==null && root.left==null)
            return 1;
        return LeafNumber(root.left)+LeafNumber(root.right);
    }

    //go through at level (also broad order)
    public void LevelTranverse(TreeNode root){
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root == null)
            return;
        TreeNode node = root;
        queue.add(root);
        while (!queue.isEmpty()){
            node = queue.poll();
            System.out.print(node.val+" ");
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
    }

    //pre order to go through a tree  (also depth order)
    //recursion
    public void PreTranverse(TreeNode root){
        if(root != null){
            System.out.print(root.val+" ");
            PreTranverse(root.left);
            PreTranverse(root.right);
        }
    }
    //none-recursion
    public void pre(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node!=null || stack.size()>0){
            while (node != null){
                System.out.print(node.val+" ");
                stack.push(node);
                node = node.left;
            }
            if(stack.size() > 0){
                node = stack.pop();
                node = node.right;
            }
        }
    }

    //mid order to go through a tree
    //recursion
    public void MidTranverse(TreeNode root){
        if(root != null){
            MidTranverse(root.left);
            System.out.print(root.val+" ");
            MidTranverse(root.right);
        }
    }
    //none-recursion
    public void mid(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node!=null || stack.size()>0){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            if(stack.isEmpty())
                break;
            node = stack.pop();
            System.out.print(node.val+" ");
            node = node.right;
        }
    }

    //post order to go through a tree
    public void PostTranverse(TreeNode root){
        if(root != null){
            PostTranverse(root.left);
            PostTranverse(root.right);
            System.out.print(root.val+" ");
        }
    }
    //none-recursion
    public void post(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            while (node.left != null) {
                stack.push(node);
                node = node.left;
            }
            while (node != null && (node.right==null || node.right==root)) {
                System.out.print(node.val+" ");
                root = node;
                if(!stack.isEmpty())
                    node = stack.pop();
                else
                    return;
            }
            stack.push(node);
            node = node.right;
        }
    }


    //get the depth of a tree
    public int Depth(TreeNode root){
        int left;
        int right;
        if(root == null)
            return 0;
        else{
            left = 1 + Depth(root.left);
            right = 1 + Depth(root.right);
        }
        return left > right ? left : right;
    }


    //pre order construct a chars tree
    public TreeNode CreateTree(TreeNode root, char[] a, int idx){
        if(idx < a.length){
            if(a[idx] == '#'){
                return null;
            }
            else{
                root = new TreeNode(a[idx]);
                root.left = CreateTree(root, a, ++counter);
                root.right = CreateTree(root, a, ++counter);
                return root;
            }
        }
        else
            return null;
    }
    //construct a tree according to a array
    public TreeNode1 CreateTree1(TreeNode1 root, int[] a, int idx){
        if(idx < a.length){
            if(a[idx] == 0){
                return null;
            }
            else{
                root = new TreeNode1(a[idx]);
                root.left = CreateTree1(root, a, 2*idx + 1);
                root.right = CreateTree1(root, a, 2*idx + 2);
                return root;
            }
        }
        else
            return null;
    }
    class TreeNode1{
        int val;
        TreeNode1 left;
        TreeNode1 right;
        public TreeNode1(int val){
            this.val = val;
        }
    }

    class TreeNode{
        char val;
        TreeNode left;
        TreeNode right;
        public TreeNode(char val){
            this.val = val;
        }
        public TreeNode(char val, TreeNode l, TreeNode r){
            this.val = val;
            this.left = l;
            this.right = r;
        }
    }
}