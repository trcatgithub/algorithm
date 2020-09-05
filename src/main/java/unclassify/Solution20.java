package unclassify;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

//给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
//
//按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
//
//"123"
//"132"
//"213"
//"231"
//"312"
//"321"
//给定 n 和 k，返回第 k 个排列。
//
//说明：
//
//给定 n 的范围是 [1, 9]。
//给定 k 的范围是[1,  n!]。
//示例 1:
//
//输入: n = 3, k = 3
//输出: "213"
//示例 2:
//
//输入: n = 4, k = 9
//输出: "2314"
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/permutation-sequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution20 {
    // 回溯 overtime - -！
    public String getPermutation(int n, int k) {
        Set<String> memo = new TreeSet<>();
        helper(memo, new StringBuilder(), 1, n, k);
        return (String) memo.toArray()[k-1];
    }

    private void helper(Set<String> memo, StringBuilder temp, int p, int n, int k) {
        if(temp.length() == n) {
            memo.add(temp.toString());
        }
        for(int i=p, len=temp.length(); i<=n; i++) {
            if(temp.indexOf(String.valueOf(i)) < 0 && (len == 0 || (len>0 && temp.charAt(len-1)-'0' < i))) {
                temp.append(i);
                helper(memo, temp, p+1, n, k);
                temp.deleteCharAt(len);
            }else {
                for(int j=1; j<=n; j++) {
                    if(temp.indexOf(String.valueOf(j)) < 0) {
                        temp.append(j);
                        helper(memo, temp, p+1, n, k);
                        temp.deleteCharAt(len);
                    }
                }
            }
        }
    }


    // 找规律
    // 1ms/37.2MB
    public String getPermutation2(int n, int k) {
        // 截取的目标字符串，数字升序排列
        StringBuilder ori= new StringBuilder("123456789");
        // n-1对应的排列总数
        int sum=1;
        //计算(n-1)!
        for(int i=1;i<n;i++) {
            sum*=i;
        }
        // group1      group2      group3      group4
        // 1234        2134        3124        4123
        // 1243        2143        3142        4132
        // 1324        2314        3214        4213
        // 1342        2341        3241        4231
        // 1423        2413        3412        4312
        // 1432        2431        3421        4321
        // 例子: n=4,k=9
        // 设 x=k-1, y=(n-1)!
        // k-1    = 8
        // (n-1)! = 6
        // 第一次分组x/y = 8/6 = 1 (group2)
        //
        // group1      group2      group3
        // 2134        2314        2413
        // 2143        2341        2431
        // x = x%y = 8%6 = 2
        // y = (n-2)! = 2
        // 第二次分组x/y = 2/2 = 1 (group2)
        //
        // group1      group2
        // 2314        2341
        // x = x%y = 2%2 = 0
        // y = (n-3)! = 1
        // 第二次分组x/y = 0/1 = 0 (group1)
        int x = n - 1;
        k--;
        StringBuilder res = new StringBuilder();

        //逐位计算
        for(int i=0; i<n-1; i++) {
            // index值即为当前位数字应是未被选出数字中 第几小的，即ori中下标
            int index = k/sum;
            res.append(ori.charAt(index));
            // 将已选出数字在ori中删除
            ori.deleteCharAt(index);
            k%= sum;
            sum/= x--;
        }
        res.append(ori.charAt(0));
        return res.toString();
    }

    public static void main(String[] args) {
        Solution20 s = new Solution20();
        System.out.println(s.getPermutation(3, 6).equals(s.getPermutation2(3, 6))); // 213
        System.out.println(s.getPermutation(4, 24).equals(s.getPermutation2(4, 24))); // 2314
        System.out.println(s.getPermutation(5, 120).equals(s.getPermutation2(5, 120))); // 123459876
        System.out.println(s.getPermutation(6, 720).equals(s.getPermutation2(6, 720))); // 126543
        System.out.println(s.getPermutation(7, 5040).equals(s.getPermutation2(7, 5040))); // 1237654
        System.out.println(s.getPermutation(8, 31492).equals(s.getPermutation2(8, 31492))); // 12348765
        System.out.println(s.getPermutation(4, 19).equals(s.getPermutation2(4, 19))); // 123459876
//        System.out.println(s.getPermutation(9, 24).equals(s.getPermutation2(9, 24))); // 123459876

    }
}
