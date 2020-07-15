package unclassify;
// https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/
//给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，
// 数组的和最接近  target （最接近表示两者之差的绝对值最小）。
//
//如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
//
//请注意，答案不一定是 arr 中的数字。
//
// 
//
//示例 1：
//
//输入：arr = [4,9,3], target = 10
//输出：3
//解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
//示例 2：
//
//输入：arr = [2,3,5], target = 10
//输出：5
//示例 3：
//
//输入：arr = [60864,25176,27249,21296,20204], target = 56803
//输出：11361
// 
//
//提示：
//
//1 <= arr.length <= 10^4
//1 <= arr[i], target <= 10^5

import java.util.Arrays;

public class Solution1 {

    //5ms/39.8MB
    public int findBestValue(int[] arr, int target) {
        // 升序排序数组
        Arrays.sort(arr);
        // 数组元素个数
        int len = arr.length;
        // target平均值(double)
        double doubleAvg = (double)target/len;
        // target平均值(int)
        int avg = target/len;
        // 5舍6入
        avg = doubleAvg>avg+0.5 ? avg+1 : avg;
        // 与target之间的gap
        int gap = Integer.MAX_VALUE;
        // 小于avg的值的和
        int sum = 0;
        // 所求返回值
        int res = 0;
        // target平均值比所有元素小时，直接返回该平均值
        if(avg < arr[0]) {
            return avg;
        }else if(avg > arr[len-1]) { // target平均值比所有元素大时，返回最大元素
            return arr[len-1];
        }
        // 遍历数组，若当前元素小于avg，则去掉该元素，重新计算target的平均值，直到找到使gap最小的值
        for(int i=0; i<len; i++) {
            // 将小于avg的值累加到sum
            sum+= arr[i];
            // 若当前元素小于等于avg
            if(arr[i] <= avg) {
                // 遍历到最后一个元素时，直接返回最后一个元素
                if(i == len-1) {
                    return arr[i];
                }
                // 当前平均值(int)
                int intVal = (target-sum)/(len-i-1);
                // 当前平均值(double)
                double doubleVal = (double)(target-sum)/(len-i-1);
                // 5舍6入
                intVal = doubleVal>intVal+0.5 ? intVal+1 : intVal;
                // 如果已经遍历到最后一个元素，则avg=最后一个元素，否则avg=intVal
                avg = len-i-1==0 ? arr[i] : intVal;
                // 使用avg时，数组元素和与target之间的gap
                int tempGap = Math.abs(sum+avg * (len-i-1)-target);
                // 不断更新gap，求最小gap对应的avg
                if(tempGap <= gap) {
                    res = avg;
                    gap = tempGap;
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,9,3};
        int target = 10;//3
        System.out.println(new Solution1().findBestValue(arr, target));
        arr = new int[]{2,3,5};
        target = 10; //5
        System.out.println(new Solution1().findBestValue(arr, target));
        arr = new int[]{1547,83230,57084,93444,70879};
        target = 71237; //17422
        System.out.println(new Solution1().findBestValue(arr, target));
        arr = new int[]{15,1,1,1,1,1,1,1,1,1,1,1};
        target = 50; //15
        System.out.println(new Solution1().findBestValue(arr, target));
        arr = new int[]{60864,25176,27249,21296,20204};
        target = 56803; //11361
        System.out.println(new Solution1().findBestValue(arr, target));
    }
}
