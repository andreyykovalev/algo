package org.algo;

public class SampleJava {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
        printPairs(8, arr);
    }
    //int array -> 1,2,3,4,5,6,7,8,9 -> O(N)2
    //x = 8
    //output -> (1,7), (2,6) , (3,5)

    public static void printPairs(int x, int[] arr) {
        if(arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                if(sum == x) {
                    System.out.println(arr[i] + " " + arr[j]);
                }
            }
        }
    }
}
