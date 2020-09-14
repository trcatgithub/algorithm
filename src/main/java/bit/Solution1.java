package bit;

import java.util.Arrays;

//编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
//
//示例：
//
//输入: numbers = [1,2]
//输出: [2,1]
//提示：
//
//numbers.length == 2
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/swap-numbers-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {

    // 利用异或运算
    //  &: 每一位 相同为1，不同为0
    //  |: 每一位 一方为1即为1，两方为0则为0
    //  ^: 每一位 相同为0，不同为1
    // 0ms/37.6MB
    public int[] swapNumbers(int[] numbers) {
        // numbers[0]: 1101
        // numbers[1]: 0011
        numbers[0] ^= numbers[1]; // 1110
        numbers[1] ^= numbers[0]; // 1101
        numbers[0] ^= numbers[1]; // 0011
        return numbers;
    }

//    两数和 在 Integer.MIN_VALUE 与 Integer.MAX_VALUE之间时
//    0ms/37.7MB
//    public int[] swapNumbers(int[] numbers) {
//        numbers[0]+= numbers[1];
//        numbers[1] = numbers[0] - numbers[1];
//        numbers[0] = numbers[0] - numbers[1];
//        return numbers;
//    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1,2};
        System.out.println("before: "+Arrays.toString(numbers));
        System.out.println("after: "+Arrays.toString(new Solution1().swapNumbers(numbers)));
        numbers = new int[]{13,3};
        System.out.println("before: "+Arrays.toString(numbers));
        System.out.println("after: "+Arrays.toString(new Solution1().swapNumbers(numbers)));
    }
}
