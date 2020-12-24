package math;

import java.util.Arrays;

//老师想给孩子们分发糖果，有 N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
//
//你需要按照以下要求，帮助老师给这些孩子分发糖果：
//
//每个孩子至少分配到 1 个糖果。
//相邻的孩子中，评分高的孩子必须获得更多的糖果。
//那么这样下来，老师至少需要准备多少颗糖果呢？
//
//示例 1:
//
//输入: [1,0,2]
//输出: 5
//解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
//示例 2:
//
//输入: [1,2,2]
//输出: 4
//解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
//第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/candy
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution8 {

    // 左规则：当 ratings[i−1]<ratings[i] 时，i 号学生的糖果数量将比 i−1 号孩子的糖果数量多。
    // 右规则：当 ratings[i]>ratings[i+1] 时，i 号学生的糖果数量将比 i+1 号孩子的糖果数量多。
    // 6ms/39.7MB
    public int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        // 从左向右 分配糖果
        for (int i=0; i<ratings.length; i++) {
            if (i > 0 && ratings[i] > ratings[i-1]) {
                left[i] = left[i-1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0;
        // 从右向左 分配糖果
        for (int i=ratings.length-1; i>=0; i--) {
            if (i < ratings.length-1 && ratings[i] > ratings[i+1]) {
                right++;
            } else {
                right = 1;
            }
            left[i] = Math.max(left[i], right);
        }
        return Arrays.stream(left).sum();
    }

//    // 先排序，按从小到大顺分发糖果
//    // 28ms/39.1MB
//    public int candy(int[] ratings) {
//        int[][] memo = new int[ratings.length][2];
//        int[] dp = new int[ratings.length];
//        Arrays.fill(dp, 1);
//        for(int i=0; i<ratings.length; i++) {
//            memo[i][0] = ratings[i];
//            memo[i][1] = i;
//        }
//        Arrays.sort(memo, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0] > o2[0]) {
//                    return 1;
//                }else if(o1[0] < o2[0]) {
//                    return -1;
//                }else {
//                    return 0;
//                }
//            }
//        });
//        for(int i=0; i<ratings.length; i++) {
//            if(memo[i][1] > 0 && ratings[memo[i][1]] < ratings[memo[i][1]-1] &&  dp[memo[i][1]-1] <= dp[memo[i][1]]) {
//                dp[memo[i][1]-1] = dp[memo[i][1]] + 1;
//            }
//            if(memo[i][1] < ratings.length-1 && ratings[memo[i][1]] < ratings[memo[i][1]+1] && dp[memo[i][1]+1] <= dp[memo[i][1]]) {
//                dp[memo[i][1]+1] = dp[memo[i][1]] + 1;
//            }
//        }
//        return Arrays.stream(dp).sum();
//    }

    public static void main(String[] args) {
        System.out.println(new Solution8().candy(new int[]{1,0,2}));
        System.out.println(new Solution8().candy(new int[]{1,2,2}));
        System.out.println(new Solution8().candy(new int[]{1,0,2,1,0}));
    }

}
