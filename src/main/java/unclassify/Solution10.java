package unclassify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
//
//有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
//
//
//
//示例:
//
//输入: "25525511135"
//输出: ["255.255.11.135", "255.255.111.35"]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/restore-ip-addresses
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution10 {

    // 11ms/39.9MB
    // 取三个点，将字符串分为四段，依次判断每段字符串合法性
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        // 循环分割字符串，每段ip值最长3位，所以循环边界<=len
        for(int i=1; i<=Math.min(3, len); i++) {
            // 截取第一段
            String first = s.substring(0, i);
            if(!isLegal(first)) {
                continue;
            }
            for(int j=i+1; j<=Math.min(7, len); j++) {
                // 截取第二段
                String second = s.substring(i, j);
                if(!isLegal(second)) {
                    continue;
                }
                for(int k=j+1; k<Math.min(11, len); k++) {
                    // 截取第三，四段
                    String third = s.substring(j, k);
                    String fourth = s.substring(k, len);
                    if(!isLegal(third) || !isLegal(fourth)) {
                        continue;
                    }
                    res.add(first+"."+second+"."+third+"."+fourth);
                }
            }
        }
        return res;
    }

    // 判断str是否是合法ip段（0-255）
    private boolean isLegal(String str) {
        if(str.length() > 3) {
            return false;
        }else if(str.length() > 1 && str.charAt(0) == '0') {
            return false;
        }
        return Integer.valueOf(str) > 255 ? false : true;
    }

    public static void main(String[] args) {
        String s = "0000";
        System.out.println(new Solution10().restoreIpAddresses(s));
        s = "25525511135";
        System.out.println(new Solution10().restoreIpAddresses(s));
        s = "010010";
        System.out.println(new Solution10().restoreIpAddresses(s));
    }
}
