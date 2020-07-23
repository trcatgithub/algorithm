package unclassify;

//给定一个正整数，返回它在 Excel 表中相对应的列名称。
//
//例如，
//
//1 -> A
//2 -> B
//3 -> C
//...
//26 -> Z
//27 -> AA
//28 -> AB
//...
//示例 1:
//
//输入: 1
//输出: "A"
//示例 2:
//
//输入: 28
//输出: "AB"
//示例 3:
//
//输入: 701
//输出: "ZY"
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/excel-sheet-column-title
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution5 {

    // 0ms/36.1MB
    public String convertToTitle(int n) {
        // 用于拼接字符串
        StringBuilder str = new StringBuilder();
        // 循环处理
        while(n > 0) {
            // 余数
            int mod = n%26;
            // 余数为0时
            if(mod == 0) {
                // 拼接'Z'
                str.insert(0, (char) ((int) 'A'+25));
                n/= 27;
            }else { // 余数不为0时
                // 拼接余数对应的字符
                str.insert(0, (char) ((int) 'A'-1+mod));
                n/= 26;
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,700,701,702,703};
//        nums = new int[]{702};
        for(int n : nums) {
            System.out.println("n: "+n+"     val: "+new Solution5().convertToTitle(n));
        }
    }

}
