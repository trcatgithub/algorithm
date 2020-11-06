package sort;

import java.util.*;

//给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
//
//如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
//
//请你返回排序后的数组。
//
// 
//
//示例 1：
//
//输入：arr = [0,1,2,3,4,5,6,7,8]
//输出：[0,1,2,4,8,3,5,6,7]
//解释：[0] 是唯一一个有 0 个 1 的数。
//[1,2,4,8] 都有 1 个 1 。
//[3,5,6] 有 2 个 1 。
//[7] 有 3 个 1 。
//按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
//示例 2：
//
//输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
//输出：[1,2,4,8,16,32,64,128,256,512,1024]
//解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
//示例 3：
//
//输入：arr = [10000,10000]
//输出：[10000,10000]
//示例 4：
//
//输入：arr = [2,3,5,7,11,13,17,19]
//输出：[2,3,5,17,7,11,13,19]
//示例 5：
//
//输入：arr = [10,100,1000,10000]
//输出：[10,100,10000,1000]
// 
//
//提示：
//
//1 <= arr.length <= 500
//0 <= arr[i] <= 10^4
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {


    // 利用1的个数对原数字加权
    // 3ms/38.7MB
    public int[] sortByBits(int[] arr) {
        int[] res = new int[arr.length];
        for(int i=0; i<arr.length; i++) {
            // 1，利用Integer.bitCount获取原数字中1的个数
            // 2，使用该1的个数对数字进行加权
            res[i] = Integer.bitCount(arr[i]) * 100000 + arr[i];
        }
        Arrays.sort(res);
        for(int i=0; i<arr.length; i++) {
            res[i] = res[i] % 100000;
        }
        return res;
    }

//    // 暴力解法，构造二维数组，先统计每个数字包含1的个数，再进行排序
//    // 10ms/39.3MB
//    // 7ms/38.9MB
//    public int[] sortByBits(int[] arr) {
//        // 0位表示原数字，1位表示包含1的个数
//        int[][] temp = new int[arr.length][2];
//        for(int i=0; i<arr.length; i++) {
////            int count = 0;
////            char[] binaryArr = Integer.toBinaryString(arr[i]).toCharArray();
////            for(char c : binaryArr) {
////                if(c == '1') {
////                    count++;
////                }
////            }
//
//            temp[i][0] = arr[i];
//            temp[i][1] = Integer.bitCount(arr[i]);
//        }
//        // 先按1的个数排序，再按大小排序
//        Arrays.sort(temp, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return (o1[1]-o2[1] == 0 ? o1[0]-o2[0] : o1[1]-o2[1]);
//            }
//        });
//        // 构建结果集
//        int[] res = new int[arr.length];
//        for(int i=0; i<arr.length; i++) {
//            res[i] = temp[i][0];
//        }
//        return res;
//    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,1,2,3,4,5,6,7,8};
        // [0,1,2,4,8,3,5,6,7]
        System.out.println(Arrays.toString(new Solution4().sortByBits(arr)));
        arr = new int[]{1024,512,256,128,64,32,16,8,4,2,1};
        // [1,2,4,8,16,32,64,128,256,512,1024]
        System.out.println(Arrays.toString(new Solution4().sortByBits(arr)));
        arr = new int[]{10000,10000};
        // [10000,10000]
        System.out.println(Arrays.toString(new Solution4().sortByBits(arr)));
        arr = new int[]{2,3,5,7,11,13,17,19};
        // [2,3,5,17,7,11,13,19]
        System.out.println(Arrays.toString(new Solution4().sortByBits(arr)));
        arr = new int[]{10,100,1000,10000};
        // [10,100,1000,10000]
        System.out.println(Arrays.toString(new Solution4().sortByBits(arr)));
    }
}
