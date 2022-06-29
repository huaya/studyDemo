package com.maxlong.study.algorithm.leetcode;

public class SoultionMinDepth {

    public int minDepth(TreeNode root) {
        return 0;
    }

    private int findDeep(TreeNode root, int i) {
        if(root == null){
            return i;
        }
        if(root.left == null && root.right == null){
            return i + 1;
        }

        if(root.left == null){
            return findDeep(root.right, i + 1);
        }
        if(root.right == null){
            return findDeep(root.left, i + 1);
        }
        return Math.min(findDeep(root.left, i + 1), findDeep(root.right, i + 1));
    }

    // Definition for a binary tree node.
    public static class TreeNode {
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
}
