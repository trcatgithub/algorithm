package sort;

import java.util.*;


//假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，
//k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
//
//注意：
//总人数少于1100人。
//
//示例
//
//输入:
//[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//
//输出:
//[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution9 {


    // 降序插入
    // 1, 先将数组按"第0位降序，第1位升序"排序
    // 2, 遍历排序后的数组，按照第1位顺序插入到链表
    // 3, 将链表转换为数组
    // 7ms/39.7MB
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[1]-o2[1];
                }
                return o2[0]-o1[0];
            }
        });
        printArray(people);
        List<int[]> temp = new ArrayList<>();
        for(int[] e : people) {
            temp.add(e[1], e);
        }
        return temp.toArray(new int[people.length][]);
    }

//    // 升序排序，降序插入
//    public int[][] reconstructQueue(int[][] people) {
//        Arrays.sort(people, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0] == o2[0]) {
//                    return o2[1]-o1[1];
//                }
//                return o1[0]-o2[0];
//            }
//        });
//
//        List<int[]> res = new ArrayList<>();
//        for(int i=people.length-1; i>=0; i--) {
//            res.add(people[i][1], people[i]);
//        }
//        return res.toArray(new int[people.length][]);
//    }


    private static void printArray(int[][] arr) {
        StringJoiner temp = new StringJoiner(", ");
        for(int[] e : arr) {
            temp.add(Arrays.toString(e));
        }
        System.out.println(temp.toString());
    }

    public static void main(String[] args) {
        int[][] people = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        printArray(new Solution9().reconstructQueue(people));
    }
}
