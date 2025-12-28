package org.algo;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class MyStack {
    Queue<Integer> queue;
    Queue<Integer> queue2;

    public MyStack() {
        queue = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    //
    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        while (queue.size() > 1) {
            queue2.add(queue.poll());
        }
        int top = queue.poll();

        while (!queue2.isEmpty()) {
            queue.add(queue2.poll());
        }
        return top;
    }

    public int top() {
        while (queue.size() > 1) {
            queue2.add(queue.poll());
        }
        int top = queue.peek();

        queue2.add(queue.poll());

        while (!queue2.isEmpty()) {
            queue.add(queue2.poll());
        }
        return top;
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public int maxDepth(Trees.TreeNode root) {
        if(root == null) return 0;
        Queue<Trees.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;

        while(!queue.isEmpty()) {
            int leavesCount = queue.size();
            depth++;

            for(int i = 0; i < leavesCount; i++) {
                Trees.TreeNode current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        return depth;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        return dummy.next;
    }

    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) return true;

        }
        return false;
    }


    public class ListNode {
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
}