package org.algo;

import javax.swing.tree.TreeNode;
import java.util.*;

public class Trees {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val,
                 TreeNode left,
                 TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<>();

        doInorderTraversal(out, root);

        return out;
    }

    private void doInorderTraversal(List<Integer> result, TreeNode root) {
        if (root == null) return;

        doInorderTraversal(result, root.left);

        result.add(root.val);

        doInorderTraversal(result, root.right);
    }

    public List<Integer> inorderTraversalIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        TreeNode current = root;

        while(current != null || !stack.isEmpty()) {

            while(current != null) {
                stack.push(current);
                current = current.left;
            }
            TreeNode item = stack.pop();
            result.add(item.val);
            current = item.right;
        }
        return result;
    }

    public int maxDepthSimplified(TreeNode root) {
        if (root == null) return 0;

        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);

        return Math.max(maxLeft, maxRight) + 1;
    }

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return doDfs(root);
    }

    private int doDfs(TreeNode root) {
        if(root == null) return 1;

        int maxLeft = doDfs(root.left);
        int maxRight = doDfs(root.right);

        return Math.max(maxLeft, maxRight);
    }

    public int maxDepthBfs(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;

        while(!queue.isEmpty()) {
            int leavesCount = queue.size();
            depth++;

            for(int i = 0; i < leavesCount; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        return depth;
    }
}
