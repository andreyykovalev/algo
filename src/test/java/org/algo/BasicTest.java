package org.algo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BasicTest {
    Basic basic = new Basic();

    @Test
    void test() {
        int[] input = {3, 2, 4};
        int[] result = basic.twoSum(input, 6);

        int a = 9;

        System.out.println(9/2);
    }


    @Test
    void longestSubstr() {
        int i = basic.lengthOfLongestSubstring("pwwkew");
        int result = i;

        assertEquals(3, i);
    }

    @Test
    void buildPrefixSumArray() {
        int[] input = {4, 2, 1, 1};

        int[] result = basic.buildPrefixSumArray(input);
        assertArrayEquals(new int[]{4, 6, 7, 8}, result);
    }

    @Test
    void removeNthListNode() {
        Basic.ListNode listNode1 = new Basic.ListNode(1);
        Basic.ListNode listNode2 = new Basic.ListNode(2);
        Basic.ListNode listNode3 = new Basic.ListNode(3);
        Basic.ListNode listNode4 = new Basic.ListNode(4);
        Basic.ListNode listNode5 = new Basic.ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        Basic.ListNode result = basic.removeNthFromEnd(listNode1, 2);
        System.out.println();
    }

    @Test
    void removeNthListNode2() {
        Basic.ListNode listNode1 = new Basic.ListNode(1);
        Basic.ListNode listNode2 = new Basic.ListNode(2);

        listNode1.next = listNode2;

        Basic.ListNode result = basic.removeNthFromEnd(listNode1, 2);
        System.out.println();
    }
}