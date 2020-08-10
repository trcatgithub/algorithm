package unclassify;

//给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
//
//重复出现的子串要计算它们出现的次数。
//
//示例 1 :
//
//输入: "00110011"
//输出: 6
//解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
//
//请注意，一些重复出现的子串要计算它们出现的次数。
//
//另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
//示例 2 :
//
//输入: "10101"
//输出: 4
//解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
//注意：
//
//s.length 在1到50,000之间。
//s 只包含“0”或“1”字符。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-binary-substrings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution11 {

    // 利用整形变量模拟Stack
    // 8ms/40.3MB
    public int countBinarySubstrings(String s) {
        int count = 0;
        int zeroCount = 0;
        int oneCount = 0;
        char[] cs = s.toCharArray();
        for(int i=0; i<cs.length; i++) {
            if(cs[i] == '0') {
                if(i>0 && cs[i-1]=='1') {
                    zeroCount = 0;
                }
                if(oneCount > 0) {
                    oneCount--;
                    count++;
                }
                zeroCount++;
            }else {
                if(i>0 && cs[i-1]=='0') {
                    oneCount = 0;
                }
                if(zeroCount > 0) {
                    zeroCount--;
                    count++;
                }
                oneCount++;
            }
        }
        return count;
    }

//    // 利用两个栈分别保存0与1
//    // 38ms/40.9MB
//    public int countBinarySubstrings(String s) {
//        int count = 0;
//        Stack<Character> zeroMemo = new Stack<>();
//        Stack<Character> oneMemo = new Stack<>();
//        char[] cs = s.toCharArray();
//        // 遍历每一个字符
//        for(int i=0; i<cs.length; i++) {
//            // 当前字符为0
//            if(cs[i] == '0') {
//                // 如果前一个字符为1，则清空存储0的Stack
//                if(i>0 && cs[i-1] == '1') {
//                    zeroMemo.clear();
//                }
//                // 如果存储1的Stack不为空
//                if(!oneMemo.isEmpty()) {
//                    // 弹出一个1
//                    oneMemo.pop();
//                    // 加算count
//                    count++;
//                }
//                // 将0入栈
//                zeroMemo.push(cs[i]);
//            }else { // 当前字符为1
//                // 如果前一个字符为0，则清空存储1的Stack
//                if(i>0 && cs[i-1] == '0') {
//                    oneMemo.clear();
//                }
//                // 如果存储0的Stack不为空
//                if(!zeroMemo.isEmpty()) {
//                    // 弹出一个0
//                    zeroMemo.pop();
//                    // 加算count
//                    count++;
//                }
//                // 将1入栈
//                oneMemo.push(cs[i]);
//            }
//        }
//        // 返回count
//        return count;
//    }

    public static void main(String[] args) {
        System.out.println(new Solution11().countBinarySubstrings("00110011"));
        System.out.println(new Solution11().countBinarySubstrings("10101"));
        System.out.println(new Solution11().countBinarySubstrings("111100011000"));
    }
}
