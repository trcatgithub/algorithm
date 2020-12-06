package unclassify;

import list.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
//
//
//
//在杨辉三角中，每个数是它左上方和右上方的数的和。
//
//示例:
//
//输入: 5
//输出:
//[
//[1],
//[1,1],
//[1,2,1],
//[1,3,3,1],
//[1,4,6,4,1]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/pascals-triangle
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution44 {
    // 按层循环处理
    // res.get(i).add(res.get(i-1).get(j-1)+res.get(i-1).get(j))
    // 1ms/36.2MB
    public List<List<Integer>> generate(int numRows) {
        if(numRows == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(1));
        for(int i=1; i<numRows; i++) {
            if(res.size() == i) {
                res.add(new ArrayList<>());
            }
            res.get(i).add(1);
            for(int j=1; j<i; j++) {
                res.get(i).add(res.get(i-1).get(j-1)+res.get(i-1).get(j));
            }
            res.get(i).add(1);
        }
        return res;
    }

    private static void printList(List<List<Integer>> list) {
        for(List<Integer> e : list) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        printList(new Solution44().generate(1));
        printList(new Solution44().generate(2));
        printList(new Solution44().generate(3));
        printList(new Solution44().generate(4));
        printList(new Solution44().generate(5));
        printList(new Solution44().generate(6));
    }
}
