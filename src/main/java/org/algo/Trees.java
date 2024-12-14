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

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
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
        if (root == null) return 0;
        return doDfs(root);
    }

    private int doDfs(TreeNode root) {
        if (root == null) return 1;

        int maxLeft = doDfs(root.left);
        int maxRight = doDfs(root.right);

        return Math.max(maxLeft, maxRight);
    }

    public int maxDepthBfs(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int leavesCount = queue.size();
            depth++;

            for (int i = 0; i < leavesCount; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        return depth;
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        // Step 1: Sort intervals by their start values
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Use a list to collect merged intervals
        List<int[]> merged = new ArrayList<>();

        // Initialize the first interval as the current interval
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        // Step 3: Iterate through intervals and merge
        for (int[] interval : intervals) {
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if (currentEnd >= nextStart) { // Overlapping intervals
                // Merge by extending the end
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // No overlap, add the next interval as a new interval
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        // Convert merged list to array
        return merged.toArray(new int[merged.size()][]);
    }

    public void sortColors(int[] nums) {
        int current = 0;
        int low = 0;
        int high = nums.length - 1;

        while (current <= high) {
            if (nums[current] == 0) {
                int temp = nums[low];
                nums[low] = nums[current];
                nums[current] = temp;
                low++;
                current++;
            } else if (nums[current] == 2) {
                int temp = nums[high];
                nums[high] = nums[current];
                nums[current] = temp;
                high--;
            } else {
                current++;
            }
        }
    }

    public void sortColorsCounting(int[] nums) {
        // Step 1: Count occurrences
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }

        // Step 2: Overwrite the original array
        int index = 0;

        // Fill with 0s
        for (int i = 0; i < count[0]; i++) {
            nums[index++] = 0;
        }

        // Fill with 1s
        for (int i = 0; i < count[1]; i++) {
            nums[index++] = 1;
        }

        // Fill with 2s
        for (int i = 0; i < count[2]; i++) {
            nums[index++] = 2;
        }
    }

    public void mergeSortedArrays(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;


        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // Copy remaining elements from nums2 (if any)
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }

    public int[] sortArray(int[] nums) {
        mergeSort(nums);
        return nums;
    }

    private void mergeSort(int[] nums) {
        int inputLength = nums.length;

        if(inputLength < 2) return;

        int midArrayIndex = inputLength / 2;

        int[] leftHalfArray = new int[midArrayIndex];
        int[] rightHalfArray = new int[inputLength - midArrayIndex];

        for(int i = 0; i < midArrayIndex; i++) {
            leftHalfArray[i] = nums[i];
        }

        for(int i = midArrayIndex; i < inputLength; i++) {
            rightHalfArray[i - midArrayIndex] = nums[i];
        }

        mergeSort(leftHalfArray);
        mergeSort(rightHalfArray);

        merge(nums, leftHalfArray, rightHalfArray);
    }

    private void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0, j = 0, k = 0;

        while(i < leftSize && j < rightSize) {
            if(leftHalf[i] <= rightHalf[j]) {
                inputArray[k] = leftHalf[i];
                i++;
            } else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while(i < leftSize) {
            inputArray[k] = leftHalf[i];
            k++;
            i++;
        }

        while(j < rightSize) {
            inputArray[k] = rightHalf[j];
            k++;
            j++;
        }
    }

    private void quicksort(int[] array, int lowIndex, int highIndex) {

        if (lowIndex >= highIndex) {
            return;
        }

        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex);

        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        quicksort(array, lowIndex, leftPointer - 1);
        quicksort(array, leftPointer + 1, highIndex);

    }

    private int partition(int[] array, int lowIndex, int highIndex, int pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        while (leftPointer < rightPointer) {

            // Walk from the left until we find a number greater than the pivot, or hit the right pointer.
            while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }

            // Walk from the right until we find a number less than the pivot, or hit the left pointer.
            while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }

            swap(array, leftPointer, rightPointer);
        }

        // This is different from what the video has, and fixes an issue where the last value could potentially be out of order.
        // Thanks to viewer Wilson Barbosa for suggesting the fix!
        if(array[leftPointer] > array[highIndex]) {
            swap(array, leftPointer, highIndex);
        }
        else {
            leftPointer = highIndex;
        }

        return leftPointer;
    }

    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
