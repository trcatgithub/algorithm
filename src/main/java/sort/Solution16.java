package sort;

import java.util.Arrays;

//给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
//
//若可行，输出任意可行的结果。若不可行，返回空字符串。
//
//示例 1:
//
//输入: S = "aab"
//输出: "aba"
//示例 2:
//
//输入: S = "aaab"
//输出: ""
//注意:
//
//S 只包含小写字母并且长度在[1, 500]区间内。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reorganize-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution16 {

    // 1，按桶统计每个字母出现次数
    // 2，根据统计结果拼接字符串(间隔1位进行拼接)
    // 1ms/36.5MB
    public String reorganizeString(String S) {
        // 记录每个字母的出现次数
        int[] memo = new int[26];
        int max = 0;
        char[] cs = S.toCharArray();
        for(char c : cs) {
            memo[c-97]++;
            max = Math.max(max, memo[c-97]);
        }
        // 出现次数最多的字母 超过了(cs.length+1)/2次 此时必然有相同字母相邻
        if(max > (cs.length+1)/2) {
            return "";
        }
        // aaaabbbcc
        // 0 1 2 3 4 5 6 7 8
        // a b a b a c a c b
        char[] temp = new char[cs.length];
        // true:表示出现次数最多的字母必须放在偶数下标
        boolean flag = (cs.length&1)==1 && max == (cs.length+1)/2;
        // 遍历桶，依次拼接字符
        for(int i=0,even=0,odd=1; i<memo.length; i++) {
            if(memo[i] == 0) {
                continue;
            }
            // 将出现次数最多的字母放到偶数下标
            if(memo[i] == max && flag) {
                while(memo[i] > 0) {
                    temp[even] = (char)(i+'a');
                    memo[i]--;
                    even+= 2;
                }
                flag = false;
                continue;
            }
            while(memo[i] > 0) {
                if(odd < cs.length) {
                    temp[odd] = (char)(i+'a');
                    odd+= 2;
                }else {
                    temp[even] = (char)(i+'a');
                    even+= 2;
                }
                memo[i]--;
            }
        }
        return String.valueOf(temp);
    }

    private static void printArray(int[][] arr) {
        for(int[] e : arr) {
            System.out.println(Arrays.toString(e));
        }
    }

    private static void check(int order, String ori, String tar) {
        System.out.println("tar: "+tar);
        System.out.print(order+": ");
        if(tar.length() == 0) {
            System.out.println("结果正确");
            return;
        }

        char[] cs = tar.toCharArray();
        char prev = cs[0];
        for(int i=1; i<cs.length; i++) {
            if(prev == cs[i]) {
                System.out.println("存在连续相同字符");
                return;
            }
            prev = cs[i];
        }

        int[] memo = new int[26];
        for(char c : ori.toCharArray()) {
            memo[c-97]++;
        }
        for(char c : tar.toCharArray()) {
            memo[c-97]--;
        }
        for(int i=0; i<memo.length; i++) {
            if(memo[i] != 0) {
                System.out.println("缺少字符: \""+(char)(i+97)+" "+i+"\"");
                return;
            }
        }
        System.out.println("结果正确");
    }

    public static void main(String[] args) {
        String S = "aab";
        check(1, S, new Solution16().reorganizeString(S));
        S = "aaab";
        check(2, S, new Solution16().reorganizeString(S));
        S = "";
        check(3, S, new Solution16().reorganizeString(S));
        S = "vvvlo";
        check(4, S, new Solution16().reorganizeString(S));
        S = "aaabbcd";
        check(5, S, new Solution16().reorganizeString(S));
        S = "aaaaaabbbbbccccddd";
        check(6, S, new Solution16().reorganizeString(S));
        S = "aaaaaabbbcc";
        check(7, S, new Solution16().reorganizeString(S));
        S = "aaaaabbbccc";
        check(8, S, new Solution16().reorganizeString(S));
        S = "aaabbbc";
        check(9, S, new Solution16().reorganizeString(S));
        S = "a";
        check(10, S, new Solution16().reorganizeString(S));
        S = "aa";
        check(11, S, new Solution16().reorganizeString(S));
        S = "abcde";
        check(12, S, new Solution16().reorganizeString(S));
        S = "ogccckcwmbmxtsbmozli";
        //   cccc
        //       mmm
        //          bb
        //            oo
        //              giklstwxz
        check(13, S, new Solution16().reorganizeString(S));
        S = "wawwivhwfrgontvvfggh";
        //   wwww
        //       ggg
        //          vvv
        //             ff
        //               hh
        //                 airont
        check(14, S, new Solution16().reorganizeString(S));
        // aaabbcc  ->  ababcac
        // aaaabbcc ->  abab
        S = "snnnnbpngobwznvnnnlnwhvnnnnfjnnlnnnnnnbnknnqkndzefncknnnnnaiqrntnndnnnjninnnunnunqhndnnqnnsjqnnpiqshntnnncnvnnnncnnqenlnninyndnnnljongnnjwnnnngllnnngkbnllnnnnontlbpngjnnenqnsnnnnnjeqqghnfpngepnodnnnnnnvnsrnughbnipvnhqmnzonoonnnjotnnonoennnpnfnnkdnnbmnmnpnqninnxronnnnvnlanlnnnebnnnlnvnfknsnbincnttnmnguqenhnnxunnnntnnnnhnqnzehvunfnvnndvnjnnnbnnpxnqipwnmnonnndlnsnonnninnxnnnjnnnnnesennmyiednnnnnnnnnhimtnnnonjlicnwnwvnntaxmnrntnnnnsnbnanninnecbcfjxncnnkvnnqgnunensanpnngjnzxjnopnnyvnnxskniyytnsnnnnx";
        // "nqnqnqnqnqnqnqnqnqnqnqnqnqnqnqnqnonononononononononononononononenenenenenenenenenenenenenenininininininininininininininjnjnjnjnjnjnjnjnjnjnjnjnjnjnlnlnlnlnlnlnlnlnlnlnlnlnlnlnvnvnvnvnvnvnvnvnvnvnvnvnvnbnbnbnbnbnbnbnbnbnbnbnbnpnpnpnpnpnpnpnpnpnpnpnpnsnsnsnsnsnsnsnsnsnsnsnsngngngngngngngngngngngntntntntntntntntntntntnhnhnhnhnhnhnhnhnhnhndndndndndndndndndnxnxnxnxnxnxnxnxnxncncncncncncncncnknknknknknknknknmnmnmnmnmnmnmnmnfnfnfnfnfnfnfnunununununununwnwnwnwnwnwnanananananynynynynynznznznznznrnrnrnrn"
        check(15, S, new Solution16().reorganizeString(S));
        S = "eyunyjremkzgblsfsrtmomdydeshldqxmwikjtfnupbcwhfcipzvuciehpelkmtnuttectqzeaeswritrfrrchkuqswcgsuoshkxvthzjjcxfgtcezgxblhkdgubhempnaoossyypewihccbzbkdjjxbqvnzqycdlwmrjjfykuitkzfhchuambdagictmjatwnttpcenraowhzmlgfvxcyamfonupldrrnvnebtzqxdjongapktgmiytqiqseizonpitnknfzwuendmvxhbsobidnqwhplolahpijafzjistxtnfdcxxkrruxbphmjqzanebmioyqmyqwayunokwbvmckgpmcxoeqtadafkbxnjvknhmjtlzkiqxirobjcpsikcyhvmoehsompftkxxfkmneqtpjntrcatlwgvgmrrvaraytvhpbidajyqolnzqchxwvpdvchgfnhohypbkzohgdchxspsylhxaefbpzaomwgxghpniy";
        check(16, S, new Solution16().reorganizeString(S));
        S = "tndsewnllhrtwsvxenkscbivijfqnysamckzoyfnapuotmdexzkkrpmppttficzerdndssuveompqkemtbwbodrhwsfpbmkafpwyedpcowruntvymxtyyejqtajkcjakghtdwmuygecjncxzcxezgecrxonnszmqmecgvqqkdagvaaucewelchsmebikscciegzoiamovdojrmmwgbxeygibxxltemfgpogjkhobmhwquizuwvhfaiavsxhiknysdghcawcrphaykyashchyomklvghkyabxatmrkmrfsppfhgrwywtlxebgzmevefcqquvhvgounldxkdzndwybxhtycmlybhaaqvodntsvfhwcuhvuccwcsxelafyzushjhfyklvghpfvknprfouevsxmcuhiiiewcluehpmzrjzffnrptwbuhnyahrbzqvirvmffbxvrmynfcnupnukayjghpusewdwrbkhvjnveuiionefmnfxao";
        check(17, S, new Solution16().reorganizeString(S));

    }
}
