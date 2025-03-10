import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class TwoPointer {

    public static void main(String[] args) {
        // two sum
        int[] nums = {1, 15, 8, 3, 6, 11, 2};
        int target = 9;
        System.out.println(Arrays.toString(findTwoSum2(nums, target)));

        // move zero to end
//        int[] nums1 = {0, 1, 0, 3, 12};
//        System.out.println(Arrays.toString(moveZeroToTheEnd(nums1))); // [1, 3, 12, 0, 0]
//
//        int[] nums2 = {0, 0, 1};
//        System.out.println(Arrays.toString(moveZeroToTheEnd(nums2))); // [1, 0, 0]
//
//        int[] nums3 = {1, 2, 3, 4};
//        System.out.println(Arrays.toString(moveZeroToTheEnd(nums3))); // [1, 2, 3, 4] (No change)
//
//        int[] nums4 = {0, 0, 0};
//        System.out.println(Arrays.toString(moveZeroToTheEnd(nums4))); // [0, 0, 0] (No change)

//        int[] nums = {1, 1, 2, 2, 3, 4, 4, 5};
//        System.out.println(removeDuplicates(nums));

//        int[] nums = {2, 1, 5, 1, 3, 2};
//        System.out.println(maxSum(nums, 3));
//
//        int[] nums2 = {2, 1, 5, 1, 3, 2};
//        System.out.println(maxSum(nums2, 2));

//        String s1 = "abca";
//        System.out.println(isPalindrome(s1));

//        String s2 = "racecara";
////        String s2 = "aracecar";
//        System.out.println(isPalindrome(s2));
////
//        String s3 = "aracecara";
//        System.out.println(isPalindrome(s3));
////
//        String s4 = "abcdef";
//        System.out.println(isPalindrome(s4));
//
//        String s5 = "cbbcc";
//        System.out.println(isPalindrome(s5));
//
//        String s6 = "acbbd";
//        System.out.println(isPalindrome(s6));
////
//        String s7 = "a";
//        System.out.println(isPalindrome(s7));
//
//        String s8 = "ab";
//        System.out.println(isPalindrome(s8));
//
//        String s9 = "abc";
//        System.out.println(isPalindrome(s9));

//        String[] doc1 = {"the", "quick", "brown", "fox", "jumps"};
//        String[] doc2 = {"quick", "brown", "fox", "runs", "fast"};
//        System.out.println("Example 1: " + longestCommonSequence(doc1, doc2));
    }

    // two pointer only for sorted array
    private static int[] findTwoSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                return new int[]{left, right};
            } else if (sum > target) {
                right--;
            } else { // sum < target
                left++;
            }
        }

        return new int[]{};
    }

    // for normal array

    private static int[] findTwoSum2(int[] arr, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < arr.length - 1; i++) {
            int complement = target - arr[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(arr[i], i);
        }

        return new int[]{};
    }

    private static int[] moveZeroToTheEnd(int[] nums) {
        int left = 0; // Points to where the next non-zero should go

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                if (left != right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
                left++;
            }
        }

        return nums;
    }

    private static int removeDuplicates(int[] nums) {

        int left = 0; // Index where next unique element should go

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[left]) {
                left++;
                nums[left] = nums[i];
            }
        }

        return left + 1; // Length is index + 1
    }

    private static int maxSum(int[] nums, int size) {

        int curSum = 0;
        for (int i = 0; i < size; i++) {
            curSum += nums[i];
        }

        int maxSum = curSum;

        for (int i = 1; i <= nums.length - size; i++) {
            curSum -= nums[i - 1];
            curSum += nums[i + size - 1];

            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
    }

    private static boolean isPalindrome(String s) {

        if(s == null || s.length() <= 2) {
            return true;
        }

        if (s.length() == 3) {
            return s.charAt(0) == s.charAt(2);
        }

        int left = 0;
        int right = s.length() - 1;
        int diffChar = 0;

        while (left < right) {
            System.out.println("left: " + s.charAt(left) + " right: " + s.charAt(right));
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else if (s.charAt(right - 1) == s.charAt(left)) {
                right--;
                diffChar++;
            } else {
                left++;
                diffChar++;
            }
        }

        return diffChar < 3;
    }

    private static int longestCommonSequence(String[] s1, String[] s2) {
        if (s1 == null || s2 == null  || s1.length == 0 || s2.length == 0) {
            return 0;
        }

        int maxLength = 0;

        for(int i = 0; i < s1.length; i++) {
            for (int j = 0; j < s2.length; j++) {
                if (s1[i].equals(s2[j])) {
                    int length = 0;

                    int s1Index = i;
                    int s2Index = j;

                    while (s1Index < s1.length && s2Index < s2.length && Objects.equals(s1[s1Index], s2[s2Index])) {
                        length++;
                        s1Index++;
                        s2Index++;
                    }

                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }
}
