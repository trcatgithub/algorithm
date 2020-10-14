package unclassify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
//例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
//
//你可以按任意顺序返回答案。
//
// 
//
//示例 1：
//
//输入：["bella","label","roller"]
//输出：["e","l","l"]
//示例 2：
//
//输入：["cool","lock","cook"]
//输出：["c","o"]
// 
//
//提示：
//
//1 <= A.length <= 100
//1 <= A[i].length <= 100
//A[i][j] 是小写字母
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-common-characters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution25 {

    // 4ms/38.4MB
    public List<String> commonChars(String[] A) {
        if(A == null || A.length == 0) {
            return new ArrayList<>();
        }
        // 统计各个字母在每个字符串中出现次数
        int[][] memo = new int[26][A.length];
        for(int i=0; i<A.length; i++) {
            for(int code : A[i].getBytes()) {
                memo[code-97][i]++;
            }
        }
        // 根据字母最小出现次数构造结果集
        List<String> res = new ArrayList<>();
        for(int i=0; i<memo.length; i++) {
            int min = 100;
            for(int j=0; j<A.length; j++) {
                min = Math.min(min, memo[i][j]);
            }
            String current = String.valueOf((char)(i+97));
            for(int k=0; k<min; k++) {
                res.add(current);
            }
        }
        return res;
    }

//    // 23ms/38.2MB
//    public List<String> commonChars(String[] A) {
//        // 处理边界问题
//        if(A == null || A.length == 0) {
//            return new ArrayList<>();
//        }
//        // 统计26个小写英文字母出现次数
//        int[] memo = new int[26];
//        for(int i=0; i<memo.length; i++) {
//            int min = 100;
//            for(String a : A) {
//                int count = 0;
//                char[] ac = a.toCharArray();
//                for(int j=0; j<a.length(); j++) {
//                    if(ac[j] == (char)(i+97)) {
//                        count++;
//                    }
//                }
//                min = Math.min(count, min);
//            }
//            memo[i] = min;
//        }
//        // 根据小写字母出现次数向res中添加该字符
//        List<String> res = new ArrayList<>();
//        for(int i=0; i<memo.length; i++) {
//            for(int j=0; j<memo[i]; j++) {
//                res.add(String.valueOf((char)(i+97)));
//            }
//        }
//        return res;
//    }

    public static void main(String[] args) {
        String[] A = new String[]{"bella","label","roller"};
        System.out.println(new Solution25().commonChars(A)); // [e, l, l]
        A = new String[]{"cool","lock","cook"};
        System.out.println(new Solution25().commonChars(A)); // [c, o]
        A = new String[]{"acabcddd","bcbdbcbd","baddbadb","cbdddcac","aacbcccd","ccccddda","cababaab","addcaccd"};
        System.out.println(new Solution25().commonChars(A)); // []
    }
}
