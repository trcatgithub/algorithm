package unclassify;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//
//给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
// 1(!@#)   2(abc)   3(def)
// 4(ghi)   5(jkl)   6(mno)
// 7(pqrs)  8(tuv)   9(wxyz)
//
//示例:
//
//输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//说明:
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution16 {

    // 排列组合
    // 2ms/38.3MB
    public List<String> letterCombinations(String digits) {
        // 处理边界
        if(digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        // 所有组合的数量
        int size = 1;
        char[] cDigits = digits.toCharArray();
        // 模拟9宫格键盘
        char[][] cs = new char[][]{{'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}};
        // 计算组合数量
        for(int i=0; i<cDigits.length; i++) {
            if(cDigits[i] == '7' || cDigits[i] == '9') {
                size*= 4;
            }else {
                size*= 3;
            }
        }
        // 结果集
        List<StringBuilder> res = new ArrayList<>(size);
        // 控制字母切换的基准值
        // 例子: "23"
        // 第一次循环后res如下
        // [a, a, a, b, b, b, c, c, c]
        // 此时base为 size/letters.length = 9/3 =3
        // 第二次循环后res如下
        // [ad, ae, af, bd, be, bf, cd, ce, cf]
        // 此时base为 base/= letters.length = 3/3 =1
        int base = size;

        // 向res中每个字符串 循环添加字符
        for(int i=0; i<cDigits.length; i++) {
            // 数字cDigits[i]对应的字母数组
            char[] letters = cs[cDigits[i]-'2'];
            // 本次循环的基准值
            base/= letters.length;
            // 与基准值配合 用于切换 当前输出的字符
            int ctrl = base;
            // 字符指针，指向当前输出的字符
            int count = 0;
            // 向res中的每一个字符串添加一个字符
            for(int j=0; j<size; j++) {
                // 初始化res
                if(j >= res.size()) {
                    StringBuilder str = new StringBuilder();
                    res.add(str);
                }
                // 当指针超过ctrl时，对count归零
                if(j >= ctrl) {
                    count = (count == letters.length-1 ? 0 : count+1);
                    // 当指针刚好等于ctrl时，扩大ctrl的范围
                    if(j == ctrl) {
                        ctrl+= base;
                    }
                }
                // 向res中的字符串添加字符
                res.get(j).append(letters[count]);
            }
        }
        // 将StringBuilder转换为String
        return res.stream().map(x ->
                x.toString()
        ).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        System.out.println(new Solution16().letterCombinations("23"));
//        System.out.println(new SolutionX().letterCombinations("234"));
        // "wdtp","wdtq","wdtr","wdts",    wdtq, wdtr, wdts, wdtp, wdtq,
        // "wdup","wduq","wdur","wdus",    wdur, wdus, wdup, wduq, wdur,
        // "wdvp","wdvq","wdvr","wdvs",    wdvs, wdvp, wdvq, wdvr, wdvs,
        //
        // "wetp","wetq","wetr","wets",    wdtp, wetq, wetr, wets, wetp,
        // "weup","weuq","weur","weus",    weuq, weur, weus, weup, weuq,
        // "wevp","wevq","wevr","wevs",    wevr, wevs, wevp, wevq, wevr,
        //
        // "wftp","wftq","wftr","wfts",    wets, wetp, wftq, wftr, wfts,
        // "wfup","wfuq","wfur","wfus",    wfup, xfuq, xfur, xfus, xfup,
        // "wfvp","wfvq","wfvr","wfvs",    xfvq, xfvr, xfvs, xfvp, xfvq,
        //
        // "xdtp","xdtq","xdtr","xdts",    xftr, xfts, xftp, xdtq, xdtr,
        // "xdup","xduq","xdur","xdus",    xdus, xdup, xduq, xdur, xdus,
        // "xdvp","xdvq","xdvr","xdvs",    xdvp, xdvq, xdvr, xdvs, xdvp,
        //
        // "xetp","xetq","xetr","xets",    xdtq, xdtr, xdts, xdtp, xetq,
        // "xeup","xeuq","xeur","xeus",    xeur, xeus, xeup, xeuq, xeur,
        // "xevp","xevq","xevr","xevs",    xevs, xevp, yevq, yevr, yevs,
        //
        // "xftp","xftq","xftr","xfts",    yetp, yetq, yetr, yets, yetp,
        // "xfup","xfuq","xfur","xfus",    yfuq, yfur, yfus, yfup, yfuq,
        // "xfvp","xfvq","xfvr","xfvs",    yfvr, yfvs, yfvp, yfvq, yfvr,
        //
        // "ydtp","ydtq","ydtr","ydts",    yfts, yftp, yftq, yftr, yfts,
        // "ydup","yduq","ydur","ydus",    yfup, yduq, ydur, ydus, ydup,
        // "ydvp","ydvq","ydvr","ydvs",    ydvq, ydvr, ydvs, ydvp, ydvq,
        //
        // "yetp","yetq","yetr","yets",    ydtr, ydts, ydtp, zdtq, zdtr,
        // "yeup","yeuq","yeur","yeus",    zdus, zdup, zeuq, zeur, zeus,
        // "yevp","yevq","yevr","yevs",    zevp, zevq, zevr, zevs, zevp,
        //
        // "yftp","yftq","yftr","yfts",    zetq, zetr, zets, zetp, zetq,
        // "yfup","yfuq","yfur","yfus",    zeur, zeus, zeup, zfuq, zfur,
        // "yfvp","yfvq","yfvr","yfvs",    zfvs, zfvp, zfvq, zfvr, zfvs,
        //
        // "zdtp","zdtq","zdtr","zdts",    zftp, zftq, zftr, zfts, zftp, zfuq, zfur, zfus, zfup
        // "zdup","zduq","zdur","zdus",
        // "zdvp","zdvq","zdvr","zdvs",
        //
        // "zetp","zetq","zetr","zets",
        // "zeup","zeuq","zeur","zeus",
        // "zevp","zevq","zevr","zevs",
        //
        // "zftp","zftq","zftr","zfts",
        // "zfup","zfuq","zfur","zfus",
        // "zfvp","zfvq","zfvr","zfvs"]
//        System.out.println(new SolutionX().letterCombinations("9387"));
    }
}
