package unclassify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
//
// 
//
//示例 1：
//
//输入：S = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca", "defegde", "hijhklij"。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
// 
//
//提示：
//
//S的长度在[1, 500]之间。
//S只包含小写字母 'a' 到 'z' 。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/partition-labels
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution27 {

    // 利用数组memo记录每个字符最后出现的位置
    // 3ms/36.8MB
    public List<Integer> partitionLabels(String S) {
        // 处理边界问题
        if(S == null || S.length() == 0) {
            return new ArrayList<>();
        }
        // 记录每个数字最后出现的位置
        int[] memo = new int[26];
        for(int i=S.length()-1; i>=0; i--) {
            if(memo[S.charAt(i)-97] == 0) {
                memo[S.charAt(i)-97] = i;
            }
        }
        // 循环判断
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<S.length();) {
            int len = memo[S.charAt(i)-97];
            if(len > i) {
                for(int j=i+1; j<len; j++) {
                    len = Math.max(len, memo[S.charAt(j)-97]);
                }
                res.add(len-i+1);
                i = len+1;
            }else {
                res.add(1);
                i++;
            }
        }
        return res;
    }

//    // 循环判断
//    // 11ms/37.1MB
//    public List<Integer> partitionLabels(String S) {
//        // 处理边界问题
//        if(S == null || S.length() == 0) {
//            return new ArrayList<>();
//        }
//        // 结果集
//        List<Integer> res = new ArrayList<>();
//        // 从前向后依次判断每一个字符最后出现的位置
//        for(int pos=0; pos < S.length();) {
//            char current = S.charAt(pos);
//            // 当前字符在字符串中最后出现的位置
//            int len = S.lastIndexOf(current);
//            // 如果该位置大于pos，说明当前字符位置 到 最后出现位置之间字符无法分割
//            if(len > pos) {
//                // 判断 当前字符位置 到 当前字符最后出现位置 之间的字符，更新本次分割字符串的最大长度
//                for(int i=pos; i<len; i++) {
//                    len = Math.max(len, S.lastIndexOf(S.charAt(i)));
//                }
//                // 追加本次字符串的长度
//                res.add(len-pos+1);
//                // 更新指针
//                pos = len+1;
//            }else {
//                // 当前字符只出现一次时
//                res.add(1);
//                pos++;
//            }
//        }
//        return res;
//    }

    public static void main(String[] args) {
        String S = "ababcbacadefegdehijhklij";
        System.out.println(new Solution27().partitionLabels(S)); // 9 7 8
        S = "ababcbacaadefegdehijhklij";
        System.out.println(new Solution27().partitionLabels(S)); // 10 7 8
        S = "caedbdedda";
        System.out.println(new Solution27().partitionLabels(S)); // 1 9
    }

}
