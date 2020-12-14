package unclassify;

import java.util.*;

//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
//示例:
//
//输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//["ate","eat","tea"],
//["nat","tan"],
//["bat"]
//]
//说明：
//
//所有输入均为小写字母。
//不考虑答案输出的顺序。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/group-anagrams
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution45 {

    // 散列+计数(代替排序)
    // 12ms/41.8MB
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> memo = new HashMap<>();
        for(String str : strs) {
            int[] counts = new int[26];
            for(char c : str.toCharArray()) {
                counts[c-'a']++;
            }
            StringBuilder temp = new StringBuilder();
            for(int i=0; i<counts.length; i++) {
                for(int j=0; j<counts[i]; j++) {
                    temp.append((char)(i+'a'));
                }
            }
            List list = memo.getOrDefault(temp.toString(), new ArrayList<>());
            list.add(str);
            memo.put(temp.toString(), list);
        }
        return new ArrayList<>(memo.values());
    }

//    // 利用散列+排序
//    // 7ms/41.6MB
//    public List<List<String>> groupAnagrams(String[] strs) {
//        Map<String, List<String>> memo = new HashMap<>();
//        for(int i=0; i<strs.length; i++) {
//            char[] temp = strs[i].toCharArray();
//            Arrays.sort(temp);
//            String key = String.valueOf(temp);
//            List list = memo.getOrDefault(key, new ArrayList<>());
//            list.add(strs[i]);
//            memo.put(key, list);
//        }
//        return new ArrayList<>(memo.values());
//    }

//    // 暴力
//    // 727ms/41.4MB
//    public List<List<String>> groupAnagrams(String[] strs) {
//        List<List<String>> res = new ArrayList<>();
//        for(int i=0; i<strs.length;) {
//            List<String> temp = new ArrayList<>();
//            String current = null;
//            for(int j=0; j<strs.length; j++) {
//                if(strs[j] == null) {
//                    continue;
//                }
//                if(current == null) {
//                    current = strs[j];
//                    temp.add(current);
//                    i++;
//                    strs[j] = null;
//                    continue;
//                }
//                if(customizedEquals(current, strs[j])) {
//                    temp.add(strs[j]);
//                    strs[j] = null;
//                    i++;
//                }
//            }
//            res.add(temp);
//        }
//        return res;
//    }
//
//    private boolean customizedEquals(String a, String b) {
//        if(a.length() != b.length()) {
//            return false;
//        }
//        int[] memo = new int[26];
//        for(char c : a.toCharArray()) {
//            memo[c-'a']++;
//        }
//        for(char c : b.toCharArray()) {
//            memo[c-'a']--;
//            if(memo[c-'a'] == -1) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new Solution45().groupAnagrams(strs).toString());
        strs = new String[0];
        System.out.println(new Solution45().groupAnagrams(strs).toString());
    }
}
