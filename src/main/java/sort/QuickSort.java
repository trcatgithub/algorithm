package sort;

import java.util.Arrays;

// https://leetcode-cn.com/problems/sort-an-array/
// 8 ms/47.1MB
// 快排，基准为指定范围数组的中位元素
// 采用双指针，从数组两端向中心寻找需要交换的元素
public class QuickSort {

    public void quickSort(int[] input) {
        quickSort(input, 0, input.length-1);
    }

    // 快排，基准为指定范围数组的中位元素
    // 采用双指针，从数组两端向中心寻找需要交换的元素
    private void quickSort(int[] input, int left, int right) {
        // 指定范围的中间值
        int mid = left+(right-left)/2;
        // 定义双指针，从数组两端向中间遍历
        for(int l=left, r=right; l<r;) {
            // 双指针未到达中位数时
            if(l < mid && r > mid) {
                // 左指针元素 大于 中位数元素 且 右指针元素 小于等于 中位数元素时
                if(input[l] > input[mid] && input[r] <= input[mid]) {
                    // 交换左右指针元素
                    swap(input, l, r);
                    // 两指针向中心移动
                    l++;
                    r--;
                }else if(input[l] > input[mid] && input[r] > input[mid]) { // 左指针元素 大于 中位数元素 且 右指针元素 大于 中位数元素时
                    // 右指针向中心移动
                    r--;
                }else if(input[l] <= input[mid] && input[r] > input[mid]) { // 左指针元素 小于等于 中位数元素 且 右指针元素 大于 中位数元素时
                    // 两指针向中心移动
                    l++;
                    r--;
                }else if(input[l] <= input[mid] && input[r] <= input[mid]) { // 左指针元素 小于等于 中位数元素 且 右指针元素 小于等于 中位数元素时
                    // 左指针向中心移动
                    l++;
                }
            }else if(l == mid) { // 左指针到达中位数时
                if(input[r] <= input[mid]) { // 右指针元素小于等于中位数元素(需要交换)
                    // 左右指针未相邻时
                    if(l+1 < r) {
                        // 交换中位数位置元素 与 中位数+1位置元素
                        swap(input, l, l+1);
                    }
                    // 交换左右指针位置元素
                    swap(input, l, r);
                    // 中位数右移
                    mid++;
                    // 左指针右移
                    l++;
                }else { // 右指针元素大于中位数元素(无需交换)
                    // 右指针向中心移动
                    r--;
                }
            }else if(r == mid) { // 右指针到达中位数时
                if(input[l] > input[mid]) { // 左指针元素大于中位数元素(需要交换)
                    // 左右指针未相邻时
                    if(r-1 > l) {
                        // 交换中位数位置元素 与 中位数-1位置元素
                        swap(input, r, r-1);
                    }
                    // 交换左右指针位置元素
                    swap(input, l, r);
                    // 中位数左移
                    mid--;
                    // 右指针左移
                    r--;
                }else { // 左指针元素小于等于中位数元素(无需交换)
                    // 左指针右移
                    l++;
                }
            }
        }
        // left到mid-1范围内元素超过1个时
        if(left < mid-1) {
            quickSort(input, left, mid-1);
        }
        // mid+1到right范围内元素超过1个时
        if(mid+1 < right) {
            quickSort(input, mid+1, right);
        }

    }

    private void swap(int[] input, int ori, int tar) {
        int temp = input[ori];
        input[ori] = input[tar];
        input[tar] = temp;
    }

    public static void main(String[] args) {
        int[] input = new int[]{-4,0,7,4,9,-5,-1,0,-7,-1};
        input = new int[]{4,2,1,5,3,6,8,7};
        input = new int[]{5,1,1,2,0,0};
        input = new int[]{-74,48,-20,2,10,-84,-5,-9,11,-24,-91,2,-71,64,63,80,28,-30,-58,-11,-44,-87,-22,54,-74,-10,-55,-28,-46,29,10,50,-72,34,26,25,8,51,13,30,35,-8,50,65,-6,16,-2,21,-78,35,-13,14,23,-3,26,-90,86,25,-56,91,-13,92,-25,37,57,-20,-69,98,95,45,47,29,86,-28,73,-44,-46,65,-84,-96,-24,-12,72,-68,93,57,92,52,-45,-2,85,-63,56,55,12,-85,77,-39};
        new QuickSort().quickSort(input);
        System.out.println(Arrays.toString(input));
    }

}
