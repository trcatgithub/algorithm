package unclassify;

import java.util.*;

// https://leetcode-cn.com/problems/diving-board-lcci/submissions/
//    你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
//    你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
//
//    返回的长度需要从小到大排列。
//
//    示例：
//
//    输入：
//    shorter = 1
//    longer = 2
//    k = 3
//    输出： {3,4,5,6}
//    提示：
//
//    0 < shorter <= longer
//    0 <= k <= 100000

// 基于TreeSet (56 ms/48.6 MB)
// 基于数组    (2 ms/47.9 MB)
public class Solution2 {
//    // 56 ms/48.6 MB
//    public int[] divingBoard(int shorter, int longer, int k) {
//        // k为0时，直接返回空数组
//        if(k == 0) {
//            return new int[0];
//        }
//        // 用于保存长度（TreeSet用于排序并去重）
//        Set<Integer> memo = new TreeSet<>();
//        // 计算所有可能长度
//        // 选择k个shorter 计算到 选择0个shorter
//        for(int i=0; i<=k; i++) {
//            memo.add(shorter*(k-i)+longer*i);
//        }
//        // 结果集
//        int[] res = new int[memo.size()];
//        int pos = 0;
//        for(int i : memo) {
//            res[pos++] = i;
//        }
//        return res;
//    }

    // 2 ms/47.9 MB
    public int[] divingBoard(int shorter, int longer, int k) {
        // k为0时，直接返回空数组
        if(k == 0) {
            return new int[0];
        }
        // shorter 与 longer相等时，只有一种情况
        if(shorter == longer) {
            return new int[]{shorter*k};
        }
        // 用于保存长度（必须使用k个木板，所以共有k+1中情况）
        int[] memo = new int[k+1];
        // 计算所有可能长度
        // 选择k个shorter 计算到 选择0个shorter
        for(int i=0; i<=k; i++) {
            memo[i] = shorter*(k-i)+longer*i;
        }
        return memo;
    }

    public static void main(String[] args) {
        int shorter = 1;
        int longer = 2;
        int k = 3;
        System.out.println(Arrays.toString(new Solution2().divingBoard(shorter, longer, k)));
        shorter = 1;
        longer = 1;
        k = 0;
        System.out.println(Arrays.toString(new Solution2().divingBoard(shorter, longer, k)));
        shorter = 1;
        longer = 1;
        k = 100000;
        System.out.println(Arrays.toString(new Solution2().divingBoard(shorter, longer, k)));

    }

}
