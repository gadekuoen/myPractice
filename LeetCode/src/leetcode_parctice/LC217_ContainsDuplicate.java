package leetcode_parctice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 * 示例 1:
 *      输入: [1,2,3,1]
 *      输出: true
 *
 * 示例 2:
 *      输入: [1,2,3,4]
 *      输出: false
 *
 * 示例 3:
 *      输入: [1,1,1,3,3,4,3,2,4,2]
 *      输出: true
 */
public class LC217_ContainsDuplicate {

    /** 方法一：比较数组长度 */
    public static boolean containsDuplicate(int[] nums) {

        long length = nums.length;
        long count = Arrays.stream(nums).distinct().count();

        return length != count;
    }

    /** 方法二：哈希方法 */
    public static boolean containsDuplicate1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)){
                return true;
            }
        }

        return false;
    }

//    public static void main(String[] args) {
//
//        int[] arr1 = {1,2,3,1};
//        int[] arr2 = {1,2,3,4};
//        int[] arr3 = {1,1,1,3,3,4,3,2,4,2};
//        System.out.println(containsDuplicate(arr1));
//        System.out.println(containsDuplicate(arr2));
//        System.out.println(containsDuplicate(arr3));
//
//
//    }

}
