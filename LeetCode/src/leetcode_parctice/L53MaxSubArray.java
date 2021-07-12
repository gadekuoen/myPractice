package leetcode_parctice;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例 1：
 *      输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 *      输出：6
 *      解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *      输入：nums = [1]
 *      输出：1
 * 示例 3：
 *      输入：nums = [0]
 *      输出：0
 * 示例 4：
 *      输入：nums = [-1]
 *      输出：-1
 * 示例 5：
 *      输入：nums = [-100000]
 *      输出：-100000
 *
 * 提示：
 *      1 <= nums.length <= 3 * 104
 *      -105 <= nums[i] <= 105
 */
public class L53MaxSubArray {

    /**
     * 方法一：在线处理
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int maxVal = nums[0];
        int addVal = 0;
        for (int num : nums) {
            addVal += num;
            if (addVal > maxVal){
                maxVal = addVal;
            }
            if (addVal < 0){
                addVal = 0;
            }
        }

        return maxVal;
    }

//    public static void main(String[] args) {
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
//        int[] nums = {-3,-2,0,-1};
//        int[] nums = {-1};
//        int[] nums = {-2,-1};
//        int maxVal = maxSubArray(nums);
//        System.out.println(maxVal);
//    }
}
