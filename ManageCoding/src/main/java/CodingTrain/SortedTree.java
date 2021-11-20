package CodingTrain;

import java.util.Stack;

public class SortedTree {
    public static void main(String[] args) {
        int[] a = {62, 88, 58, 47, 35, 73, 51, 99, 37, 93};
        int[] b = {53, 17, 78, 9, 45, 65, 94, 23, 81, 88};
        TreeNode root =  CreateSortedTree(b);
        RemoveNode(root, 81);
        System.out.println("========");
        mid(root);
    }

    //create a sorted tree
    public static TreeNode CreateSortedTree(int[] a){
        TreeNode root= new TreeNode(a[0]);
        for (int i = 1; i < a.length; i++) {
            InsertNode(root, a[i]);
        }
        return root;
    }

    //insert a value into a sorted tree
    public static void InsertNode(TreeNode root, int value){
        TreeNode node = root;
        while (true){
            if (value <= node.val){
                if(node.left == null){
                    node.left = new TreeNode(value);
                    return;
                }
                else
                    node = node.left;
            }
            else {
                if(node.right == null){
                    node.right = new TreeNode(value);
                    return;
                }
                else
                    node = node.right;
            }
        }
    }

    //mid transverse
    public static void mid(TreeNode root){
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

    //delete a value from a sorted tree
    public static void RemoveNode(TreeNode root, int value){
        if (root == null)
            return;
        TreeNode preNode = null;
        TreeNode delNode = null;
        TreeNode node = root;
        boolean flag = false;
        //find the node to remove and the pre-node
        while (node != null){
            if(node.val == value){
                delNode = node;
                break;
            }
            preNode = node;
            if(value < node.val){
                node = node.left;
                flag = false;
            }
            else{
                node = node.right;
                flag = true;
            }
        }

        if(delNode.right==null){
            if(!flag)
                preNode.left = delNode.left;
            else
                preNode.right = delNode.left;
            return;
        }
        else if(delNode.left==null){
            if(!flag)
                preNode.left = delNode.right;
            else
                preNode.right = delNode.right;
            return;
        }
        else {
            TreeNode tmp = delNode.left;
            TreeNode preTmp = delNode;
            while (tmp.right != null){
                preTmp = tmp;
                tmp = tmp.right;
            }
            if(preTmp == delNode)
                preTmp.left = tmp.left;
            else
                preTmp.right = tmp.right;
            delNode.val = tmp.val;

        }


    }
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
}
