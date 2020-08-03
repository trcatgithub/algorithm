package unclassify;

//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
//
//注意：
//
//num1 和num2 的长度都小于 5100.
//num1 和num2 都只包含数字 0-9.
//num1 和num2 都不包含任何前导零。
//你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/add-strings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution8 {

    // 6ms/39.7MB
    public String addStrings(String num1, String num2) {
        // 进位
        int add = 0;
        // 结果集
        StringBuilder res = new StringBuilder();
        // 遍历每一个字符进行累加
        for(int i=1, j=1; i<=num1.length() || j<=num2.length() || add>0;i++, j++) {
            // 获取位置i对应的数字
            int n1 = (i>num1.length() ? '0' : num1.charAt(num1.length()-i))-'0';
            // 获取位置j对应的数字
            int n2 = (j>num2.length() ? '0' : num2.charAt(num2.length()-j))-'0';
            // 计算两数字与进位的和
            int sum = n1+n2+add;
            // 判断下次是否需要进位
            add = sum>9 ? 1 : 0;
            // 将计算结果插入到结果集
            res.insert(0, sum%10);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s1 = "9133";
        String s2 = "0";
        System.out.println(new Solution8().addStrings(s1, s2));
        s1 = "9133";
        s2 = "1";
        System.out.println(new Solution8().addStrings(s1, s2));
        s1 = "0";
        s2 = "0";
        System.out.println(new Solution8().addStrings(s1, s2));
        s1 = "1230";
        s2 = "779";
        System.out.println(new Solution8().addStrings(s1, s2));
        s1 = "1230";
        s2 = "8779";
        System.out.println(new Solution8().addStrings(s1, s2));
        s1 = "408";
        s2 = "5";
        System.out.println(new Solution8().addStrings(s1, s2));
    }

}
