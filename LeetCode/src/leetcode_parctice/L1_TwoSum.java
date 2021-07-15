package leetcode_parctice;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 *      输入：nums = [2,7,11,15], target = 9
 *      输出：[0,1]
 *      解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *      输入：nums = [3,2,4], target = 6
 *      输出：[1,2]
 * 示例 3：
 *      输入：nums = [3,3], target = 6
 *      输出：[0,1]
 *
 * 提示：
 *      2 <= nums.length <= 104
 *      -109 <= nums[i] <= 109
 *      -109 <= target <= 109
 *      只会存在一个有效答案
 */
public class L1_TwoSum {

    public static int[] twoSum(int[] nums, int target) {

        int[] idexArr = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complete = target - nums[i];
            if (map.containsKey(complete) && map.get(complete) != i){
                idexArr[0] = i;
                idexArr[1] = map.get(complete);
                return idexArr;
            }
            map.put(nums[i],i);
        }
        throw new NullPointerException("no two num solution");
    }

//    public static void main(String[] args) {
//        int[] testArr = {2,7,11,15};
//        int[] testArr = {3,2,4};
//        int[] res = twoSum(testArr,6);
//        System.out.println("[" + res[0] + "," + res[1] + "]");
//    }
}
