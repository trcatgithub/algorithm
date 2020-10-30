package unclassify;

//有一座高度是80级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。
//求爬到80级一共有多少种走法。
public class Solution35 {

    public static void main(String[] args) {
        int level = 3; // 3
        // 1 1 1
        // 1 2
        // 2 1
        System.out.println(new Solution35().getCount(level));
        level = 4; // 5
        // 1 1 1 1
        // 2 1 1
        // 1 2 1
        // 1 1 2
        // 2 2
        System.out.println(new Solution35().getCount(level));
        level = 5; // 8
        // 1 1 1 1 1
        // 2 1 1 1
        // 1 2 1 1
        // 1 1 2 1
        // 1 1 1 2
        // 2 2 1
        // 2 1 2
        // 1 2 2
        System.out.println(new Solution35().getCount(level));
        level = 6; // 13
        // 1 1 1 1 1 1
        // 2 1 1 1 1
        // 1 2 1 1 1
        // 1 1 2 1 1
        // 1 1 1 2 1
        // 1 1 1 1 2
        // 2 2 1 1
        // 2 1 2 1
        // 2 1 1 2
        // 1 2 2 1
        // 1 2 1 2
        // 1 1 2 2
        // 2 2 2
        System.out.println(new Solution35().getCount(level));
        level = 7; // 21
        System.out.println(new Solution35().getCount(level));
        level = 47; // 512559680
        System.out.println(new Solution35().getCount(level));
    }

    public int getCount(int level) {
        return helper(new int[level], level);
    }

    private int helper(int[] memo, int level) {
        if(memo[level-1] > 0) {
            return memo[level-1];
        }else if(level == 1) {
            memo[level-1] = 1;
        }else if(level == 2) {
            memo[level-1] = 2;
        }else {
            memo[level-1] = helper(memo, level-1)+helper(memo, level-2);
        }
        return memo[level-1];

    }
}
