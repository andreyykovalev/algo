package org.algo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<List<String>> groupAnagrams2(String[] strs) {
        //"eat", "tae", "tea", "art" -> ["eat", "tae", "tea"], ["art"]

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] sortedStr = str.toCharArray();
            Arrays.sort(sortedStr);

            if (!map.containsKey(new String(sortedStr))) {
                List<String> anagramGroup = new ArrayList<>();
                anagramGroup.add(str);
                map.put(new String(sortedStr), anagramGroup);
            } else {
                map.get(new String(sortedStr)).add(str);
            }
        }
        return new ArrayList<>(map.values());
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
        Integer i = Stream.of(3, 5, 4, 2, 6, 2)
                .collect(Collectors.groupingBy(key -> key, Collectors.counting()))
                .entrySet()
                .stream()
                .max((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))
                .map((entry1) -> entry1.getKey())
                .orElse(null);

        System.out.println(i);

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

        for (String str : strs) {
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);

            String sortedStr = new String(strArr);

            if (!map.containsKey(sortedStr)) {
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

    public String longestCommonPrefix(String[] strs) {
//        Input: strs = ["flower","flow","flight"]
//        Output: "fl"

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public String intToRoman(int num) {
        int[] values = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[]sympols = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < values.length; i++) {

            while(num > values[i]) {
                num -= values[i];
                sb.append(sympols[i]);
            }
        }
        return sb.toString();

    }

    public String longestCommonPrefixVertical(String[] strs) {
//        Input: strs = ["flower","flow","flight"]
//        Output: "fl"

        StringBuilder sb = new StringBuilder();

        String firstStr = strs[0];

        for(int i = 0; i < strs[0].length(); i++) {
            char c = firstStr.charAt(i);

            for(int j = 1; j < strs.length; j++) {
                if(i >= strs[j].length() || c != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(c);
        }
        return sb.toString();
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
        if (head == null) return head;

        int count = 0;

        ListNode current = head;
        while (current != null) {
            current = current.next;
            count++;
        }

        int nodeToRemoveIndex = count - n;

        if (nodeToRemoveIndex == 0) {
            return head.next;
        }

        current = head;
        ListNode newHead = current;
        for (int i = 0; i < count; i++) {
            if (i == nodeToRemoveIndex - 1) {
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

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            char c = '2';
            Integer.parseInt(Character.toString(c));

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public boolean isHappy(int n) {
        Set<Integer> seenNumbers = new HashSet<>();

        while(true) {
            int temp = n;
            int newNum = 0;
            while(temp > 0) {
                int digit = temp % 10;
                newNum +=  digit * digit;
                temp = temp / 10;
            }
            if(newNum == 1) {
                return true;
            }
            if(seenNumbers.contains(newNum)) {
                return false;
            } else {
                seenNumbers.add(newNum);
            }
            n = newNum;
        }
    }

    //aerbbbet -> 2 (bb)
    public int longestRepeatingSubstring(String s) {
        if (s.isEmpty()) return 0;

        char[] sArr = s.toCharArray();
        int max = 1;
        int count = 1;

        for (int i = 0; i < s.length() - 1; i++) {
            char current = sArr[i];
            char next = sArr[i + 1];

            if (current == next) {
                count++;
                max = Math.max(count, max);
            } else {
                count = 1;
            }
        }
        return max;
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        Map<Character, Integer> need = new HashMap<>();

        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int len = s1.length();

        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (i >= len) {
                char rm = s2.charAt(i - len);
                window.put(rm, window.get(rm) - 1);
                if (window.get(rm) == 0) window.remove(rm);
            }
            if (window.equals(need)) return true;
        }
        return false;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magazineMap = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            magazineMap.put(c, magazineMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);

            if (!magazineMap.containsKey(c)) {
                return false;
            } else {
                magazineMap.put(c, magazineMap.get(i) - 1);
                if (magazineMap.get(c) == 0) ;
                magazineMap.remove(c);
            }
        }
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        //Input: s = "egg", t = "add"
        // egg -> add
        // e -> a
        // g -> d

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (!sMap.containsKey(s.charAt(i))) {
                sMap.put(s.charAt(i), i);
            }
            if (!tMap.containsKey(t.charAt(i))) {
                tMap.put(t.charAt(i), i);
            }
            if (sMap.get(s.charAt(i)) != tMap.get(t.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}