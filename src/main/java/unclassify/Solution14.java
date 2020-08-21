package unclassify;

//罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
//
//字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000
//例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
//
//通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
//所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
//
//I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
//X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
//C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
//给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
//
//示例 1:
//
//输入: 3
//输出: "III"
//示例 2:
//
//输入: 4
//输出: "IV"
//示例 3:
//
//输入: 9
//输出: "IX"
//示例 4:
//
//输入: 58
//输出: "LVIII"
//解释: L = 50, V = 5, III = 3.
//示例 5:
//
//输入: 1994
//输出: "MCMXCIV"
//解释: M = 1000, CM = 900, XC = 90, IV = 4.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/integer-to-roman
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution14 {

    //I             1
    //V             5
    //X             10
    //L             50
    //C             100
    //D             500
    //M             1000
    // 5ms/39.3MB
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        // 处理大于等于1000的部分
        if(num >= 1000) {
            helper(res, "M", num/1000);
            num = num % 1000;
        }

        // 处理大于等于900，小于1000的部分
        if(num >= 900) {
            helper(res, "C", 9);
            num = num % 900;
        }else if(num >= 500) { // 处理大于等于500，小于900的部分
            helper(res, "D", 1);
            num = num % 500;
        }

        // 处理大于等于100的部分，小于500的部分
        if(num >= 100) {
            helper(res, "C", num/100);
            num = num % 100;
        }

        // 处理大于等于90，小于100的部分
        if(num >= 90) {
            helper(res, "X", 9);
            num = num % 90;
        }else if(num >= 50) { // 处理大于等于50，小于90的部分
            helper(res, "L", 1);
            num = num % 50;
        }

        // 处理大于等于10，小于50的部分
        if(num >= 10) {
            helper(res, "X", num/10);
            num = num % 10;
        }
        // 处理大于等于9，小于10的部分
        if(num >= 9) {
            helper(res, "I", 9);
            num = num % 9;
        }else if(num >= 5) { // 处理大于等于5，小于9的部分
            helper(res, "V", 1);
            num = num % 5;
        }
        // 处理大于等于1，小于5的部分
        if(num >= 1) {
            helper(res, "I", num);
        }

        return res.toString();
    }

    private void helper(StringBuilder str, String number, int count) {
        //I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
        //X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
        //C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
        // count==4或9时，表示为IV或IX
        if("I".equals(number) && (count == 4 || count == 9)) {
            str.append(number).append(count == 4 ? "V" : "X");
        }else if("X".equals(number) && (count == 4 || count == 9)) { // count==40或90时，表示为IL或IC
            str.append(number).append(count == 4 ? "L" : "C");
        }else if("C".equals(number) && (count == 4 || count == 9)) { // count==400或900时，表示为ID或IM
            str.append(number).append(count == 4 ? "D" : "M");
        }else { // 按count数，拼接累加字符
            for(int i=0; i<count; i++) {
                str.append(number);
            }
        }
    }

    public static void main(String[] args) {
//        2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
        System.out.println(new Solution14().intToRoman(2).equals("II")); // II
        System.out.println(new Solution14().intToRoman(3).equals("III")); // III
        System.out.println(new Solution14().intToRoman(4).equals("IV")); // IV
        System.out.println(new Solution14().intToRoman(12).equals("XII")); // XII
        System.out.println(new Solution14().intToRoman(27).equals("XXVII")); // XXVII
        System.out.println(new Solution14().intToRoman(9).equals("IX")); // IX
        System.out.println(new Solution14().intToRoman(58).equals("LVIII")); // LVIII
        System.out.println(new Solution14().intToRoman(1994).equals("MCMXCIV")); // MCMXCIV


    }

}
