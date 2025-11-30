package org.algo;

import java.util.*;

public class Main {
//    You are given a sorted array of integers, a[0..n-1], and a target number x.
//    Write a function to efficiently find the index of x in the array.

//    If x is found, return its index. If x is not in the array, indicate that the number is not present.
//
//    Constraints:The array a is sorted in non-decreasing order.You can assume the array contains integers.
//     Input: a = [1, 3, 5, 7, 9], x = 5
//     Output: 2
//     Input: a = [2, 4, 6, 8, 10], x = 7
//     Output: -1  # Number not found

    public static void main(String[] args) {

    }

    public int findIndex(int[] input, int n) {
        if (input.length == 0) {
            return -1;
        }

        return binarySearchIterative(0, input.length - 1, input, n);
    }

    private int binarySearchIterative(int start, int end, int[] array, int n) {

        while (start <= end) {
            int newMid = (start + end) / 2;

            if (array[newMid] == n) {
                return newMid;
            }

            if (array[newMid] > n) {
                end = newMid - 1;
            } else {
                start = newMid + 1;
            }
        }
        return -1;
    }


    private int binarySearch(int start, int end, int[] array, int n) {
        int newMid = (start + end) / 2;

        //base
        if (array[newMid] == n) {
            return newMid;
        }

        if (start > end) {
            return -1;
        }

        //4  n == 5
        if (array[newMid] > n) {
            return binarySearch(start, newMid - 1, array, n);
        } else {
            return binarySearch(newMid + 1, end, array, n);
        }
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        //[4,9,5]
        //[9,4,9,8,4]
//        L
//        R

        //map
        //4 -> 1
        //9 -> 1
        //5 -> 1

        //if map.get(4) >= 1 -> res.add(4)

        // out -> {4, 9}

        Map<Integer, Integer> map = new HashMap<>();

        for (int current : nums2) {
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();

        for (int current : nums1) {
            if (map.containsKey(current) && map.get(current) > 0) {
                result.add(current);
                map.put(current, map.get(current) - 1);
            }
        }

        return  result.stream().mapToInt(i -> i).toArray();
    }

    public int[] intersectTwoPointer(int[] nums1, int[] nums2) {
        //[4,5,9]
//             L
        //[4,4,7,8,9]
//                 R
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int l = 0;
        int r = 0;
        while (l < nums2.length && r < nums1.length) {
            if(nums2[l] == nums1[r]) {
                result.add(nums2[l]);
                l++;
                r++;
            } else if (nums2[l] < nums1[r]) {
                l++;
            } else {
                r++;
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public int[] twoSum(int[] nums, int target) {

          //[1,2,3,4]
        //i  0
        //j    1

        for(int i = 0; i < nums.length; i++) {
            for(int j = 1; j < nums.length; j++) {

                if(i == j) continue;

                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public List<List<Integer>> threeSum2(int[] nums) {
    //[-1,0,1,2,-1,-4]
    //[[-1,-1,2],[-1,0,1]]

        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < nums.length - 2; i++) {

            int target = -nums[i];
            Map<Integer, Integer> map = new HashMap<>();

            for(int j = i + 1; j < nums.length; j++) {
                int complement = target - nums[j];

                if(map.containsKey(complement)) {
                    result.add(Arrays.asList(nums[i], nums[j], complement));
                }
                map.put(nums[j], j);
            }
        }

        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicate a's

            int target = -nums[i];
            Map<Integer, Integer> map = new HashMap<>();

            for (int j = i + 1; j < nums.length; j++) {
                int complement = target - nums[j];

                if (map.containsKey(complement)) {
                    result.add(Arrays.asList(nums[i], nums[j], complement));

                    // skip duplicates for b and c
                    while (j + 1 < nums.length && nums[j] == nums[j + 1]) j++;
                }

                map.put(nums[j], j);
            }
        }

        return result;
    }

    public int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        int currentValue = 0;

        //[1,8,6,2,5,4,8,3,7]

        //[1, 1]
        // i

        for(int i = 0; i < height.length; i++) {

            for(int j = i; j < height.length; j++) {
                if(i != j) {
                    int start = height[i];
                    int end = height[j];

                    maxArea = Math.max(maxArea, start * end);
                }
            }
        }
        return maxArea;
    }
}
