package unclassify;

//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
//示例 1:
//
//输入: num1 = "2", num2 = "3"
//输出: "6"
//示例 2:
//
//输入: num1 = "123", num2 = "456"
//输出: "56088"
//说明：
//
//num1 和 num2 的长度小于110。
//num1 和 num2 只包含数字 0-9。
//num1 和 num2 均不以零开头，除非是数字 0 本身。
//不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/multiply-strings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution12 {
    // 10ms/40.1MB
    public String multiply(String num1, String num2) {
        // 处理与"0"相乘
        if("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        // 结果集
        StringBuilder res = new StringBuilder();
        // 两字符串对应的字符数组
        char[] cnum1 = num1.toCharArray();
        char[] cnum2 = num2.toCharArray();
        // 两字符串的长度
        int len1 = cnum1.length;
        int len2 = cnum2.length;
        // 每次乘算后的进位
        int add0 = 0;
        // 每位乘算后的值
        int[] add = new int[len1+len2];
        // 表示当前乘算结果的位置
        int pos = 0;
        // add   |0|1|2|3|4|5|
        // num1  | | | |4|5|6|
        // num2  | | | |1|2|3|
        //       | | |1|3|6|8|
        //       | | |9|1|2| |
        //       | |4|5|6| | |
        //       | |5|6|0|8|8|
        // 依次乘算每一位
        for(int i=len1-1; i>=0; i--) {
            for(int j=len2-1; j>=0; j--) {
                // 乘算结果保存的位置
                pos = i+j+1;
                // 乘算结果(值可能大于9)
                int current = (cnum2[j]-'0')*(cnum1[i]-'0')+add0;
                // 进位值(无需进位时值为0)
                add0 = current/10;
                // 计算进位后的剩余值
                int left = add0>0 ? current%10 : current;
                // 将left加到add数组中的pos位置
                helper(left, pos, add);
            }
            // 当计算完num1的某一位 与 num2的所有位乘积后，将进位值add0加算到add数组中
            if(add0 > 0) {
                add[pos-1]+= add0;
                add0 = 0;
            }
        }
        // 将add数组转换为string
        for(int i=add.length-1; i>=0; i--) {
            if(i == 0 && add[i] == 0) {
                break;
            }
            res.insert(0, add[i]);
        }
        return res.toString();
    }

    // 将val加算为nums的pos位置，同时处理好进位问题
    private void helper(int val, int pos, int[] nums) {
        int add = 0;
        for(int i=pos; i>=0; i--) {
            nums[i] = nums[i] + val + add;
            if(nums[i] > 9) {
                val = 0;
                add = nums[i]/10;
                nums[i] = nums[i]%10;
            }else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        String num1 = "2", num2 = "3";
        System.out.println(new Solution12().multiply(num1, num2));
        num1 = "123";  num2 = "456";
        System.out.println(new Solution12().multiply(num1, num2));
        num1 = "0";  num2 = "456";
        System.out.println(new Solution12().multiply(num1, num2));
        num1 = "789";  num2 = "321";
        System.out.println(new Solution12().multiply(num1, num2));
        num1 = "100";  num2 = "100";
        System.out.println(new Solution12().multiply(num1, num2));
//        int[] nums = new int[]{4,5,6};
//        System.out.println(Arrays.toString(nums));
//        new SolutionX().helper(5,2, nums);
    }
}
