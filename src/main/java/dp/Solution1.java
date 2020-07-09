package dp;

import java.util.HashSet;
import java.util.Set;

// 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
// 像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
// 在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
// 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
//
// 注意：本题相对原题稍作改动，只需返回未识别的字符数
//
// 
//
// 示例：
//
// 输入：
// dictionary = ["looked","just","like","her","brother"]
// sentence = "jesslookedjustliketimherbrother"
// 输出： 7
// 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
// 提示：
//
// 0 <= len(sentence) <= 1000
// dictionary中总字符数不超过 150000。
// 你可以认为dictionary和sentence中只包含小写字母。
//https://leetcode-cn.com/problems/re-space-lcci/submissions/
// 829 ms/41MB
public class Solution1 {

    //字符串各字符的位置    j  e  s  s  l  o  o  k  e  d  j  u  s  t  l  i  k  e  t  i  m  h  e  r  b  r  o  t  h  e  r
    //计算前dp数组的值  0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31
    //计算后dp数组的值  0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 4, 5, 6, 7, 4, 5, 6, 7, 8, 9, 7, 8, 9,10,11,12,13, 7
    public int respace(String[] dictionary, String sentence) {
        // 保存dictionary中内容，用于快速查询
        Set<String> dic = new HashSet<>();
        for(String word : dictionary) {
            dic.add(word);
        }
        // 记录dp结果。（表示初始位置到当前位置为止，除去dic中的字符串后的剩余长度）
        int[] memo = new int[sentence.length()+1];
        // 从位置1开始循环计算
        for(int i=1; i<=sentence.length(); i++) {
            // 初始memo[i]为memo[i-1]+1
            memo[i] = memo[i-1] + 1;
            // 从i向前遍历
            for(int j=i; j>=0; j--) {
                // 如果j到i范围的字符串包含在dic中，则更新一次memo[i]的值
                if(dic.contains(sentence.substring(j, i))) {
                    memo[i] = Math.min(memo[i], memo[j]);
                }
            }
        }
        // 返回剩余长度
        return memo[sentence.length()];
    }

    public static void main(String[] args) {
        String[] dictionary = new String[]{"looked","just","like","her","brother"};
        String sentence = "jesslookedjustliketimherbrother";
//        dictionary = new String[]{"bt","vbtbvtvvbbvtbvvbbbvbtbbv","bvvbbbvvvbvttbtbvtvtvvbttbbbtvvvb","btttbvbbbtbbtbvvttbvbvtvbtbbttb","bt","vvbvbvbvtbvbvvvvtv","tbvvvtttvtbvbtttvvbtvvvvtvvbvvtvvbbvbbbvb","v","bvb","vvtbvtvbttbttvvbvttbt","btbtbtttvbbtbttbtvvttbvtbtvtbvvtbbbb","bttbvbbttvvbtvvvvb","bvvbvbvttbvtbvvtbttvvvvtvbtvbttbbvvtvtvv","vbtttt","btbvbbbvbtvtbvvv","b","tbtbtv","vbvbbvvbvbbvvb","vbvvtvbvbvbttvbvbtvbtbtvtbvbbtb","bvvbvvttttttbtvvvttvbvtvvbvtbtvtbvttvvtbt","bvtbttv","bbbt","vtt","ttvv","b","vbb","vtvvbtttvtbbvvbbtbb","vvv","vvvvbbbtbbbvbbbb","ttvvbtvv","v","btvbbvtbbvbvtvttvvbbbtbvvvtbtb","vv","btbttbtbbvbvvbvttbttvtbbtbvtttvbbtbbtvtvvvvbbttvtvvbbbv","ttvbbbbttbtbtb","tvvbvbvvb","vv","ttbttvtvbtbbbbv","bvvvtbbvvvbtvbvtvtvvvvbb","vtbvvbvvvvvttvbbttbbvtvt","tbvbbt","b","v","tvbbtvvtvvtbtbttvvvb","tbttbttbbbtbtvtvtvtbbbvvtbbbvbbvvvbbttvvt","bbvttvtvvtbvbbttvbbtbvvttbvbvbtbvvvbbbvbvbvbtvbtvvvtvvtbttbttbbvbbbttvvvbvvtb","vttvvbvv","tbttbvvttvbtvvtbbvvv","bvbbbbbbbb","vtbvvtbbvtt","bvttbvvbvb","tvttttbbvvvbbtttvvv"};
//        sentence = "btbvtttttbvttbvvbbtvvbvbvvbtbtbtvbtttbvbbbtbbtbvvttbvbvtvbtbbttbvvbvbtttbvttbvvbbvvv";
        System.out.println(new Solution1().respace(dictionary, sentence));
    }
}
