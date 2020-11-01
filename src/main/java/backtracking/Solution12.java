package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
//
//说明：
//
//
//分隔时可以重复使用字典中的单词。
//你可以假设字典中没有重复的单词。
//
//
//示例 1：
//
//输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//"cats and dog",
//"cat sand dog"
//]
//
//
//示例 2：
//
//输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//"pine apple pen apple",
//"pineapple pen apple",
//"pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
//
//
//示例 3：
//
//输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/word-break-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution12 {
    // 91ms/39MB
    // 利用dp先判断能否拆分
    // 确定能拆分之后，利用backtrace进行拆分
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        // 判断能否拆分
        if(isCaseAvailable(s, wordDict)) {
            // 利用回溯进行拆分
            helper(res, new StringBuilder(), s, wordDict, 0);
        }
        return res;
    }

    // 判断能否拆分
    private boolean isCaseAvailable(String s, List<String> wordDict) {
        boolean[] res = new boolean[s.length()+1];
        res[0] = true;
        for(int i=0; i<s.length(); i++) {
            for(int j=i; j<=s.length(); j++) {
                // wordDict包含i到j字符所组成的字符串
                if(wordDict.contains(s.substring(i, j))) {
                    // 只能由false向true更新(某个位置判断为能拆分之后，后续无法将该位置改成不能拆分)
                    res[j] = res[j] || res[i];
                }
            }
        }
        return res[s.length()];
    }

    // 利用回溯进行拆分
    private void helper(List<String> res, StringBuilder line, String s, List<String> wordDict, int p) {
        if(p == s.length()) {
            res.add(line.toString());
        }
        for(int i=p; i<=s.length(); i++) {
            String temp = s.substring(p, i);
            if(wordDict.contains(temp)) {
                int len = line.length();
                if(len > 0) {
                    line.append(" ");
                }
                line.append(temp);
                helper(res, line, s, wordDict, i);
                if(len > 0) {
                    line.delete(line.length()-1-temp.length(), line.length());
                }else {
                    line.delete(0, line.length());
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        //"cats and dog",
        //"cat sand dog"
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(new Solution12().wordBreak(s, wordDict).toString());

        s = "pineapplepenapple";
        //"pine apple pen apple",
        //"pineapple pen apple",
        //"pine applepen apple"
        wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(new Solution12().wordBreak(s, wordDict).toString());

        s = "catsandog";
        // []
        wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(new Solution12().wordBreak(s, wordDict).toString());

        s = "aggegbnngohbggalojckbdfjakgnnjadhganfdkefeddjdnabmflabckflfljafdlmmbhijojiaaifedaihnoinedhhnolcjdam";
        // ["aggegbnngohbgga lojckbdfj akgnn jadhganf dkefeddjdnab mflabckflflja fdlmmbhij o jiaaifedaihn oinedhhnolc jdam"]
        wordDict = Arrays.asList("o","b","gbdfgiokkfnhl","glibjohcmd","bblcnhelanckn","mflabckflflja","mgda","oheafhajjo","cc","cffalholojikojm","haljiamccabh","gjkdlonmhdacd","ee","bc","mjj","fdlmmbhij","nn","jiaaifedaihn","nhligg","hooaglldlei","hajhebh","ebijeeh","me","eibm","ekkobhajgkem","ohaofonhjakc","n","kjjogm","mhn","odcamjmodie","edmagbkejiocacl","kcbfnjialef","lhifcohoe","akgnn","fbgakjhjb","belggjekmn","oinedhhnolc","ddekcnag","oneoakldakalb","bodnokemafkhkhf","dkefeddjdnab","gflcngff","fgnfmbcogmojgm","ad","jadhganf","lojckbdfj","gadkaoe","jdam","ljjndlnednnombl","aggegbnngohbgga");
        System.out.println(new Solution12().wordBreak(s, wordDict).toString());

        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        System.out.println(new Solution12().wordBreak(s, wordDict).toString());

        // [true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true,
        // false, false, false, false, false, false, false, false, true,
        // false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false]
        //           [a,     g,     g,     e,     g,     b,     n,     n,     g,     o,     h,     b,     g,     g,    a,
        //     l,     o,     j,     c,     k,     b,     d,     f,     j,
        //     a,     k,     g,     n,     n, j, a, d, h, g, a, n, f, d, k, e, f, e, d, d, j, d, n, a, b, m, f, l, a, b, c, k, f, l, f, l, j, a, f, d, l, m, m, b, h, i, j, o, j, i, a, a, i, f, e, d, a, i, h, n, o, i, n, e, d, h, h, n, o, l, c, j, d, a, m]
        //
    }
}
