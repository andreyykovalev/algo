package org.algo;

import java.util.*;

public class Basic {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(target - nums[i], i);
            }
        }
        return new int[0];
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;

        if (s.isEmpty()) {
            return 0;
        }

        Set<Character> subStringSet = new HashSet<>();

        for (int right = 0, left = 0; right < s.toCharArray().length; ) {
            char charAtRight = s.charAt(right);
            if (subStringSet.contains(charAtRight)) {
                subStringSet.remove(s.charAt(left));
                left++;
            } else {
                subStringSet.add(s.charAt(right));
                max = Math.max(max, subStringSet.size());
                right++;
            }
        }

        return max;
    }

    public int test(String s) {
        int max = 0;

        "asd".charAt(0);

        return max;
    }

    public static void main(String[] args) {
        System.out.println("asd".charAt(1));
    }


    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum;

        for (int i = 0; i < nums.length; i++) {
            currentSum = 0;

            for (int j = i; j < nums.length; j++) {
                currentSum = currentSum + nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }

    public int maxSubArraySlidingWindow(int[] nums) {
        int maxSum = Integer.MIN_VALUE; // To track the maximum sum
        int currentSum = 0; // To track the sum of the current window

        for (int i = 0; i < nums.length; i++) {

            currentSum = nums[i] + currentSum;
            maxSum = Math.max(currentSum, maxSum);

            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }

    public boolean isAnagram(String s, String t) {
        //two empty strings are anagram
        if (s.isEmpty() && t.isEmpty()) return true;

        char[] sArr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < sArr.length; i++) {
            char c = sArr[i];
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }

        return map.isEmpty();
    }

    public boolean isAnagramSort(String s, String t) {
        Map<String, List<String>> map = new HashMap<>();
        ArrayList<Object> objects = new ArrayList<>(map.values());
        //two empty strings are anagram
        if (s.isEmpty() && t.isEmpty()) return true;

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        //strings with different length can't be anagram
        if (sArr.length != tArr.length) {
            return false;
        }

        Arrays.sort(sArr);
        Arrays.sort(tArr);

        return new String(sArr).equals(new String(tArr));
    }

    public boolean isAnagramArray(String s, String t) {
        // If lengths differ, they can't be anagrams
        if (s.length() != t.length()) return false;

        // Array to store frequency counts of letters
        int[] charCount = new int[26];

        // Increment counts for s, decrement for t
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
            charCount[t.charAt(i) - 'a']--;
        }

        // Check if all counts are zero
        for (int count : charCount) {
            if (count != 0) return false;
        }

        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String str: strs) {
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);

            String sortedStr = new String(strArr);

            if(!map.containsKey(sortedStr)) {
                List<String> anagramGroup = new ArrayList<>();
                anagramGroup.add(str);
                map.put(sortedStr, anagramGroup);
            } else {
                map.get(sortedStr).add(str);
            }
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagramsFrequencyArray(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            // Frequency array for characters 'a' to 'z'
            int[] charCount = new int[26];
            for (char c : str.toCharArray()) {
                charCount[c - 'a']++;
            }

            // Convert frequency array to a string key
            StringBuilder keyBuilder = new StringBuilder();
            for (int count : charCount) {
                keyBuilder.append('#'); // Separator to avoid ambiguity
                keyBuilder.append(count);
            }
            String key = keyBuilder.toString();

            // Group strings with the same key
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public int[] buildPrefixSumArray(int nums[]) {
        int[] output = new int[nums.length];
        output[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            output[i] = nums[i] + output[i - 1];
        }
        return output;
    }

    int subArraySumEqualsK(int[] nums, int k) {
        int[] prefixSumArr = new int[nums.length];
        prefixSumArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSumArr[i] = nums[i] + prefixSumArr[i - 1];
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int count = 0;

        for (int i = 0; i < prefixSumArr.length; i++) {
            int prefixSum = prefixSumArr[i];
            int complement = prefixSum - k;

            if (map.containsKey(complement)) {
                count += map.get(complement);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }

    public static class ListNode {
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

    public ListNode reverseListStack(ListNode head) {
        if (head == null) return null;

        Stack<ListNode> stack = new Stack<>();
        ListNode current = head;

        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        ListNode newHead = stack.pop();
        ListNode temp = newHead;

        while (!stack.isEmpty()) {
            temp.next = stack.pop();
            temp = temp.next;
        }

        temp.next = null;

        return newHead;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;

            prev = current;
            current = temp;
        }

        return prev;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;

        int count = 0;

        ListNode current = head;
        while(current != null) {
            current = current.next;
            count++;
        }

        int nodeToRemoveIndex = count - n;

        if (nodeToRemoveIndex == 0) {
            return head.next;
        }

        current = head;
        ListNode newHead = current;
        for(int i = 0; i < count; i++) {
            if(i == nodeToRemoveIndex - 1) {
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
        return newHead;
    }

    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>(); // Store nodes, not their values
        ListNode current = head;

        while (current != null) {
            if (set.contains(current)) { // If the node is already in the set, there's a cycle
                return true;
            }
            set.add(current); // Add the current node to the set
            current = current.next;
        }
        return false; // No cycle detected
    }

    public boolean hasCycleFloydsAlgo(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast!= null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}