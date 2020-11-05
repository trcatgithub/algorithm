package bfs;

import java.util.*;

//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
//
//每次转换只能改变一个字母。
//转换过程中的中间单词必须是字典中的单词。
//说明:
//
//如果不存在这样的转换序列，返回 0。
//所有单词具有相同的长度。
//所有单词只由小写字母组成。
//字典中不存在重复的单词。
//你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
//示例 1:
//
//输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//返回它的长度 5。
//示例 2:
//
//输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/word-ladder
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {
    // hit
    // cog
    // [hig hio hpt cpt cot cog]
    // hig hio

    // 由beginWord和endWord同时开始bfs
    // 23ms/39.9MB
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 单词长度不等 或 wordList不包含endWord时，返回0
        if(!wordList.contains(endWord) || beginWord.length() != endWord.length()) {
            return 0;
        }
        // 记录wordList中的单词
        Set<String> dictionary = new HashSet<>();
        for(String word : wordList) {
            dictionary.add(word);
        }
        // 两个队列，分别用于从beginWord与endWord开始bfs
        Queue<String> beginWords = new LinkedList<>();
        Queue<String> endWords = new LinkedList<>();
        // 两个HashSet，分别用于从beginWord与endWord开始bfs过程中，过滤掉重复路径
        Set<String> beginUsed = new HashSet<>();
        Set<String> endUsed = new HashSet<>();
        beginUsed.add(beginWord);
        endUsed.add(endWord);
        beginWords.offer(beginWord);
        endWords.offer(endWord);
        // 计数器
        int beginCount = 1;
        int endCount = 1;
        // 每次选择元素数量少的队列开始bfs
        while(!beginWords.isEmpty() && !endWords.isEmpty()) {
            int beginSize = beginWords.size();
            int endSize = endWords.size();
            if(beginSize <= endSize) {
                beginCount+= helper(beginWords, dictionary, beginUsed);
                // 判断是否找到最短路径
                if(hasSameWord(beginWords, endWords)) {
                    return beginCount+endCount-1;
                }
            }
            if(beginSize >= endSize) {
                endCount+= helper(endWords, dictionary, endUsed);
                if(hasSameWord(beginWords, endWords)) {
                    return beginCount+endCount-1;
                }
            }
        }
        return 0;
    }

    // 判断两个队列中是否包含重复元素
    private boolean hasSameWord(Queue<String> beginWords, Queue<String> endWords) {
        Set<String> memo = new HashSet<>();
        for(Iterator<String> iterator = beginWords.iterator(); iterator.hasNext();) {
            String word = iterator.next();
            memo.add(word);
        }

        for(Iterator<String> iterator = endWords.iterator(); iterator.hasNext();) {
            String word = iterator.next();
            if(memo.contains(word)) {
                return true;
            }
        }
        return false;
    }

    // bfs方法，找到任意一条路径时返回1，否则返回0
    private int helper(Queue<String> words, Set<String> dictionary, Set<String> used) {
        int size = words.size();
        int count = 0;
        // 遍历每一个单词
        for(int i=0; i<size; i++) {
            String word = words.poll();
            StringBuilder temp = new StringBuilder(word);
            // 按位替换字母，判断dictionary中是否包含该替换后的单词
            for(int j=0; j<word.length(); j++) {
                char ori = temp.charAt(j);
                for(int k=0; k<26; k++) {
                    char tar = (char)('a'+k);
                    if(ori == tar) {
                        continue;
                    }
                    String changed = temp.replace(j, j+1, String.valueOf(tar)).toString();
                    // dictionary包含该替换后的单词 且 该单词未使用过(找到新的路径)
                    if(dictionary.contains(changed) && !used.contains(changed)) {
                        words.offer(changed);
                        used.add(changed);
                        count = (count == 0 ? 1 : count);
                    }
                }
                // 还原单词
                temp.replace(j, j+1, String.valueOf(ori));
            }
        }
        return count;
    }

//    回溯，个别用例超时
//    private int min = Integer.MAX_VALUE;
//
//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        if(!wordList.contains(endWord) || beginWord.length() != endWord.length()) {
//            return 0;
//        }
//        int[] used = new int[wordList.size()];
//        helper(wordList, used, beginWord, endWord, 1);
//        return min == Integer.MAX_VALUE ? 0 : min;
//    }
//
//    private void helper(List<String> wordList, int[] used, String current, String target, int count) {
//        if(current.equals(target)) {
//            min = Math.min(min, count);
//            return;
//        }
//
//        for(int i=0; i<used.length; i++) {
//            if(used[i] == 0 && canReplace(current, wordList.get(i))) {
//                used[i] = 1;
//                helper(wordList, used, wordList.get(i), target, count+1);
//                used[i] = 0;
//                if(wordList.get(i).equals(target)) {
//                    return;
//                }
//            }
//        }
//    }
//
//    private boolean canReplace(String ori, String tar) {
//        int count = 0;
//        for(int i=0; i<ori.length(); i++) {
//            if(count == 2) {
//                return false;
//            }
//            if(ori.charAt(i) != tar.charAt(i)) {
//                count++;
//            }
//        }
//        return count == 1;
//    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(new Solution1().ladderLength(beginWord, endWord, wordList)); // 5
        beginWord = "hit";
        endWord = "cog";
        wordList = Arrays.asList("hot","dot","dog","lot","log");
        System.out.println(new Solution1().ladderLength(beginWord, endWord, wordList)); // 0
        beginWord = "hit";
        endWord = "cog";
        wordList = Arrays.asList("hig","hio","hpt","cpt","cot","cog");
        System.out.println(new Solution1().ladderLength(beginWord, endWord, wordList)); // 5
        beginWord = "a";
        endWord = "c";
        wordList = Arrays.asList("a","b","c");
        System.out.println(new Solution1().ladderLength(beginWord, endWord, wordList)); // 2
        beginWord = "hot";
        endWord = "dog";
        wordList = Arrays.asList("hot","dog","dot");
        System.out.println(new Solution1().ladderLength(beginWord, endWord, wordList)); // 3
        beginWord = "qa";
        endWord = "sq";
        wordList = Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar",
                "ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn",
                "au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga",
                "li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc",
                "ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye");
        System.out.println(new Solution1().ladderLength(beginWord, endWord, wordList)); // 5
    }
}
