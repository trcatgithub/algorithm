package search;

//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
//
// 
//
//示例：
//
//s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
//提示：你可以假定该字符串只包含小写字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution9 {

    // 记录每个字母的出现次数
    // 从前向后遍历，寻找次数为1的字母
    // 4ms/38.8MB
    public int firstUniqChar(String s) {
        int[] memo = new int[26];
        char[] cs = s.toCharArray();
        for(char c : cs) {
            memo[c-'a']++;
        }
        for(int i=0; i<cs.length; i++) {
            if(memo[cs[i]-'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(new Solution9().firstUniqChar(s));
        s = "loveleetcode";
        System.out.println(new Solution9().firstUniqChar(s));
        s = "";
        System.out.println(new Solution9().firstUniqChar(s));
    }
}
