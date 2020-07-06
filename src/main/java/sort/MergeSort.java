package sort;

// https://leetcode-cn.com/problems/sort-an-array/
// 8 ms/47.2 MB
// 归并排序
// 先按中位数分割数组，再合并排序
public class MergeSort {
    // 归并排序
    public int[] sortArray(int[] input) {
        divide(input, 0, input.length-1);
        return input;
    }

    // 按数组长度中位数分割数组
    // case1，当前数组超过2位，继续递归分割
    // case2，当前数组只有2位，或只有1位，直接对该数组进行排序(根据情况交换1次)
    private void divide(int[] input, int start, int end) {
        if(end-start>1) {
            divide(input, start, (start+end)/2);
            divide(input, (start+end)/2+1, end);
            merge(input, start, (start+end)/2, (start+end)/2+1, end);
        }else if(input[start]>input[end]){
            int temp = input[start];
            input[start] = input[end];
            input[end] = temp;
        }
    }

    // 合并2个数组
    // input: 原数组
    // start1: 数组1的开始位置
    // end1: 数组1的结束位置
    // start2: 数组2的开始位置
    // end2: 数组2的结束位置
    private void merge(int[] input, int start1, int end1, int start2, int end2) {
        // 新数组，用于保存合并结果
        int[] res = new int[end2-start1+1];
        // 新数组指针
        int pos = 0;
        // 双指针分别指向两个数组的开始位置,遍历直到两个数组指针都达到对应数组的末尾
        for(int i=start1, j=start2; i<=end1 || j<=end2;) {
            // 如果数组1已经遍历结束，数组2剩余部分无需比较，直接复制到res当中
            if(i>end1) {
                // 将input数组从位置j开始长度为end2-j+1的数据，复制到数组res的pos位置之后
                System.arraycopy(input, j, res, pos, end2-j+1);
                // 复制结束直接跳出循环
                break;
            }
            // 如果数组2已经遍历结束，数组1剩余部分无需比较，直接复制到res当中
            if(j>end2) {
                // 将input数组从位置i开始长度为end1-i+1的数据，复制到数组res的pos位置之后
                System.arraycopy(input, i, res, pos, end1-i+1);
                // 复制结束直接跳出循环
                break;
            }
            // 将数组1，2中对应位置较小的元素赋值给res(升序排列时)
            // 在赋值的过程中，同时移动对应数组的指针
            if(input[i] >= input[j]) {
                res[pos++] = input[j++];
            }else {
                res[pos++] = input[i++];
            }
            // 当pos等于res的长度时，说明所有数据都已经复制到res中，直接结束循环
            if(pos == end2-start1+1) {
                break;
            }
        }
        // 保存排序结果
        // 将res数组从位置0开始长度为res.length的数据，复制到数组input的start1位置之后
        System.arraycopy(res, 0, input, start1, res.length);
    }
}
