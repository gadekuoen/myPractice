package leetcode_parctice;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 * 示例 1：
 *      输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 *      输出：[1,2,2,3,5,6]
 * 示例 2：
 *      输入：nums1 = [1], m = 1, nums2 = [], n = 0
 *      输出：[1]
 *  
 * 提示：
 *      nums1.length == m + n
 *      nums2.length == n
 *      0 <= m, n <= 200
 *      1 <= m + n <= 200
 *      -109 <= nums1[i], nums2[i] <= 109
 */
public class LC88_Merge {

    /**
     * 方法一：直接合并后排序  T：O((m+n)log(m+n))  N: O((m+n)log(m+n))
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m+i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 方法二： 双指针   T：O(m+n)  N：O(m+n)
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m+n];
        int cur = 0;
        while(p1 < m || p2 < n){
            if (p1 == m){
                cur = nums2[p2++];
            } else if (p2 == n){
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]){
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1+p2-1] = cur;
        }
        for (int i = 0; i < m+n; i++) {
            nums1[i] = sorted[i];
        }
    }

    /**
     *  方法三：逆向双指针
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1, p2 = n-1;
        int tail = m + n - 1;
        while(p1 > -1 || p2 > -1){
            if (p1 == -1){
                nums1[tail--] = nums2[p2--];
            } else if (p2 == -1){
                nums1[tail--] = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]){
                nums1[tail--] = nums1[p1--];
            } else {
                nums1[tail--] = nums2[p2--];
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

//    public static void main(String[] args) {
//        LC88_Merge merge = new LC88_Merge();
//        int[] nums1 = {1,2,3,0,0,0};
//        int[] nums2 = {2,5,6};
//        merge.merge3(nums1,3,nums2,3);
//    }
}
