package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//有一堆石头，每块石头的重量都是正整数。
//
//每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为x 和y，且x <= y。那么粉碎的可能结果如下：
//
//如果x == y，那么两块石头都会被完全粉碎；
//如果x != y，那么重量为x的石头将会完全粉碎，而重量为y的石头新重量为y-x。
//最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
//
//
//
//示例：
//
//输入：[2,7,4,1,8,1]
//输出：1
//解释：
//先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
//再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
//接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
//最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
//
//
//提示：
//
//1 <= stones.length <= 30
//1 <= stones[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/last-stone-weight
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution17 {

    public int lastStoneWeight(int[] stones) {
        if (stones.length == 2) {
            return Math.abs(stones[0] - stones[1]);
        }
        if (stones.length == 1) {
            return stones[0];
        }

        int len = stones.length;
        // 使用大顶堆找最大值
        // 每次找到最大值时，将其与(len-1)的值进行交换，交换后len减1
        while(len > (stones.length-3)) {
            // len/2-1为大顶堆的第一个非叶子节点
            // 从左至右，从下至上维护大顶堆
            for(int i=len/2-1; i>=0; i--) {
                int left = i*2+1;
                int right = i*2+2 > len-1 ? len-1 : i*2+2;
                // 调整i与其左右节点，直到i与其左右节点满足大顶堆的性质
                while(stones[i] < stones[left] || stones[i] < stones[right]) {
                    if(stones[left] > stones[i]) {
                        int swap = stones[left];
                        stones[left] = stones[i];
                        stones[i] = swap;
                    }
                    if(stones[right] > stones[i]) {
                        int swap = stones[right];
                        stones[right] = stones[i];
                        stones[i] = swap;
                    }
                }
            }
            // 将(0, len)范围内的input维护成大顶堆之后，将最大值(位置0)交换到len-1
            // 然后将len减1
            int swap = stones[0];
            stones[0] = stones[len-1];
            stones[len-1] = swap;
            len--;
        }
        if (stones[stones.length - 3] == 0) {
            return stones[stones.length - 1] - stones[stones.length - 2];
        }
        stones[stones.length - 1] = stones[stones.length - 1] - stones[stones.length - 2];
        stones[stones.length - 2] = 0;
        return lastStoneWeight(stones);
    }

    public static void main(String[] args) {
        int[] stones = new int[]{2,7,4,1,8,1};
        System.out.println(new Solution17().lastStoneWeight(stones)); // 1
        stones = new int[]{2,6,4,1,8,1};
        System.out.println(new Solution17().lastStoneWeight(stones)); // 0
        stones = new int[]{9,10,4,5,7,1};
        System.out.println(new Solution17().lastStoneWeight(stones)); // 0
        stones = new int[]{3,7,2};
        System.out.println(new Solution17().lastStoneWeight(stones)); // 2
    }
}
