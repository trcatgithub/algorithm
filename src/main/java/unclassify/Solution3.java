package unclassify;
// https://leetcode-cn.com/problems/triangle
//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
//
//相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
//
// 
//
//例如，给定三角形：
//
//[
//   [2],
//  [3,4],
// [6,5,7],
//[4,1,8,3]
//]
//自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

import java.util.*;

public class Solution3 {

    // 使用输入List保存各个位置的最小路径和
    // 7ms/39.9MB
    public int minimumTotal(List<List<Integer>> triangle) {
        // 处理边界问题
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }else if(triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        // 最小路径和
        int res = Integer.MAX_VALUE;
        // 遍历每一层
        for(int i=1; i<triangle.size(); i++) {
            // 当前层元素
            List<Integer> currentLevel = triangle.get(i);
            // 上一层元素
            List<Integer> previousLevel = triangle.get(i-1);
            // 遍历当前层每一个元素,计算每一个元素的路径和，并将路径和保存到list当中
            for(int j=0; j<currentLevel.size(); j++) {
                // 三角形左边界
                if(j == 0) {
                    currentLevel.set(j, currentLevel.get(j)+previousLevel.get(j));
                }else if(j == currentLevel.size()-1) { // 三角形右边界
                    currentLevel.set(j, currentLevel.get(j)+previousLevel.get(j-1));
                }else { // 左右边界之间的路径
                    currentLevel.set(j, currentLevel.get(j)+Math.min(previousLevel.get(j), previousLevel.get(j-1)));
                }
                // 遍历最后一层时，计算最短路径
                if(i == triangle.size()-1) {
                    res = Math.min(res, currentLevel.get(j));
                }
            }
        }
        // 返回最短路径
        return res;
    }

//    // 利用map保存每层各个位置的路径和
//    // 30ms/41.5MB
//    public int minimumTotal(List<List<Integer>> triangle) {
//        if(triangle == null || triangle.size() == 0) {
//            return 0;
//        }
//        Map<String, Integer> memo = new HashMap<>();
//        memo.put("0:0", triangle.get(0).get(0));
//        int res = triangle.get(0).get(0);
//        int temp = Integer.MAX_VALUE;
//        for(int i=1; i<triangle.size(); i++) {
//            for(int j=0; j<triangle.get(i).size(); j++) {
//                int min = Math.min(memo.getOrDefault((i-1)+":"+(j-1), Integer.MAX_VALUE), memo.getOrDefault((i-1)+":"+j, Integer.MAX_VALUE));
//                min+= triangle.get(i).get(j);
//                memo.put(i+":"+j, min);
//                if(i == triangle.size()-1) {
//                    temp = Math.min(temp, min);
//                }
//            }
//        }
//        return temp==Integer.MAX_VALUE ? res : temp;
//    }

    private List<List<Integer>> generateTriangle(int[][] triangle) {
        List<List<Integer>> res = new ArrayList<>();
        for(int[] level : triangle) {
            List<Integer> levels = new ArrayList<>();
            for(int val : level) {
                levels.add(val);
            }
            res.add(levels);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution3 s3 = new Solution3();
        int[][] triangle = new int[][]{{2},{3,4},{6,5,7},{4,1,8,3}};
//        triangle = new int[][]{{-10}};
        System.out.println(s3.minimumTotal(s3.generateTriangle(triangle)));
    }
}
