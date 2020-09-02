package unclassify;

//请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
//例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、
// "1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution19 {
    // 3ms/39.6MB
    public boolean isNumber(String s) {
        // 处理null
        if(s == null) {
            return false;
        }
        // 处理两端空格
        s = s.trim();
        if(s.length() == 0) {
            return false;
        }else if(s.length() == 1) { // 处理单个字符
            return !isNotNumber(s.charAt(0));
        }

        char[] cs = s.toCharArray();
        // '+', '-', 'e', '.'
        // 0位表示字符出现次数
        // 1位表示字符最新出现的位置
        int[][] count = new int[4][2];
        // 遍历字符串
        for(int i=0; i<cs.length; i++) {
            // 当前字符不是0-9的数字
            if(isNotNumber(cs[i])) {
                // 当前字符是'+'
                if('+' == cs[i]) {
                    // 当前字符为第一个字符 或 前一个字符为'e'或'E'
                    if(i == 0 || cs[i-1] == 'e' || cs[i-1] == 'E') {
                        // '+'号出现次数加一
                        count[0][0]++;
                        // 判断'+'与'-'的数量是否超过2
                        if(count[0][0]+count[1][0] == 3) {
                            return false;
                        }
                        // 更新'+'与'-'的位置
                        // 如果'+'号出现
                        if(count[0][0] == 1) {
                            count[0][1] = i;
                        }else {
                            count[1][1] = i;
                        }
                    }else {
                        return false;
                    }
                }else if('-' == cs[i]) {
                    if(i == 0 || cs[i-1] == 'e' || cs[i-1] == 'E') {
                        count[1][0]++;
                        // 判断'+'与'-'的数量是否超过2
                        if(count[0][0]+count[1][0] == 3) {
                            return false;
                        }
                        // 更新'+'与'-'的位置
                        if(count[1][0] == 1) {
                            count[1][1] = i;
                        }else {
                            count[0][1] = i;
                        }
                    }else {
                        return false;
                    }
                }else if('e' == cs[i] || 'E' == cs[i]) {
                    if(i == 0 // 'e'在第一位
                            || i == cs.length-1 // 'e'在最后一位
                            || (i == 1 && isNotNumber(cs[i-1])) // 'e'在第二位，且前一位不是数字
                            || (i == cs.length-2 && isNotNumber(cs[i+1])) // 'e'在倒数第二位，且下一位不是数字
                            || (isNotNumber(cs[i-1]) && cs[i-1] != '.') // 前一位不是数字，不是'.'
                            || (isNotNumber(cs[i+1]) && cs[i+1] != '+' && cs[i+1] != '-') // 下一位不是数字，不是'+'，不是'-'
                            || (i < cs.length-2 && isNotNumber(cs[i+2]) && (cs[i+1] == '+' || cs[i+1] == '-')) // 下一位是'+'或'-' 且 再下一位不是数字
                    ) {
                        return false;
                    }else {
                        count[2][0]++;
                        if(count[2][0] == 2) {
                            return false;
                        }
                        count[2][1] = i;
                    }
                }else if('.' == cs[i]) {
                    // 前一位不是数字，不是'+'，不是'-'
                    if((i > 0 && isNotNumber(cs[i-1]) && cs[i-1] != '+' && cs[i-1] != '-')
                        // 前一位是'+'或'-'，后一位不是数字 或 当前位就是最后一位
                        || (i > 0 && (cs[i-1] == '+' || cs[i-1] == '-')) && (i == cs.length-1 || isNotNumber(cs[i+1]))
                    ) {
                        return false;
                    }else {
                        count[3][0]++;
                        if(count[3][0] == 2) {
                            return false;
                        }
                        count[3][1] = i;
                    }
                }else {
                    return false;
                }
            }
        }
        // '+'与'-'出现次数之和大于2时，不是合法数值
        if(count[0][0]+count[1][0] > 2) {
            return false;
        }else if(count[0][0]+count[1][0] == 2) {
            if(count[2][0] == 0) {
                return false;
            }else if((count[2][1] < count[0][1] && count[2][1] < count[1][1]) ||
                    (count[2][1] > count[0][1] && count[2][1] > count[1][1])) {
                // '+'与'-'出现在'E'的一侧时
                return false;
            }
        }
        // 存在'e' 且 'e'之后存在'.'
        if(count[2][0] == 1 && count[3][1] > count[2][1]) {
            return false;
        }
        return true;
    }

    private boolean isNotNumber(char c) {
        return c < '0' || c > '9' ? true : false;
    }

    public static void main(String[] args) {
//        字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、
//// "1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
        System.out.println(new Solution19().isNumber("+100") == true);
        System.out.println(new Solution19().isNumber("5e2") == true);
        System.out.println(new Solution19().isNumber("-123") == true);
        System.out.println(new Solution19().isNumber("3.1416") == true);
        System.out.println(new Solution19().isNumber("-1E-16") == true);
        System.out.println(new Solution19().isNumber("0123") == true);
        System.out.println(new Solution19().isNumber("46.e3") == true);
        System.out.println(new Solution19().isNumber(".12") == true);
        System.out.println(new Solution19().isNumber("3.") == true);
        System.out.println(new Solution19().isNumber(".3") == true);
        System.out.println(new Solution19().isNumber("+.8") == true);

        System.out.println(new Solution19().isNumber(".e3") == false);
        System.out.println(new Solution19().isNumber("1a3.14") == false);
        System.out.println(new Solution19().isNumber("1.2.3") == false);
        System.out.println(new Solution19().isNumber("+-5") == false);
        System.out.println(new Solution19().isNumber("12e+5.4") == false);
        System.out.println(new Solution19().isNumber(".") == false);
        System.out.println(new Solution19().isNumber("0e") == false);
        System.out.println(new Solution19().isNumber(" -.") == false);
        System.out.println(new Solution19().isNumber("+.e4") == false);

    }

}
