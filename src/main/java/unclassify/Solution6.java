package unclassify;

//给定一个Excel表格中的列名称，返回其相应的列序号。
//
//例如，
//
//A -> 1
//B -> 2
//C -> 3
//...
//Z -> 26
//AA -> 27
//AB -> 28
//...
//示例 1:
//
//输入: "A"
//输出: 1
//示例 2:
//
//输入: "AB"
//输出: 28
//示例 3:
//
//输入: "ZY"
//输出: 701
//致谢：
//特别感谢 @ts 添加此问题并创建所有测试用例。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/excel-sheet-column-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution6 {

    // 2ms/37.8MB
    public int titleToNumber(String s) {
        // 转换字符数组
        char[] cs = s.toCharArray();
        // 用于累加结果值
        int res = 0;
        // 从后向前遍历每一个字符
        for(int i=cs.length-1; i>=0; i--) {
            // cs[i]-'A'+1 为单独字符对应的数值
            // (int)Math.pow(26, cs.length-i-1) 为该字符对应的进位
            res+= (cs[i]-'A'+1)*(int)Math.pow(26, cs.length-i-1);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] sa = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
                "U","V","W","X","Y","Z","AA","AB","AAA","ZZ","ZY"};
        for(String s : sa) {
            System.out.println("str: "+s+"     n: "+new Solution6().titleToNumber(s));
        }
    }
}
