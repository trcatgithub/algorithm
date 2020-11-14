package sort;

import java.util.Arrays;

//给你两个数组，arr1 和 arr2，
//
//
//arr2 中的元素各不相同
//arr2 中的每个元素都出现在 arr1 中
//
//
//对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
//未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
//
//
//
//示例：
//
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
//
//
//
//
//提示：
//
//
//arr1.length, arr2.length <= 1000
//0 <= arr1[i], arr2[i] <= 1000
//arr2 中的元素 arr2[i] 各不相同
//arr2 中的每个元素 arr2[i] 都出现在 arr1 中
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/relative-sort-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution8 {
    // 循环判断
    // 1ms/38.5MB
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 第0位: 表示arr2包含该数字
        // 第1位: 记录该数字出现的次数
        int[][] memo = new int[1001][2];
        for(int num : arr2) {
            memo[num][0] = 1;
        }
        // 从前向后循环遍历
        // 将不报哈在arr2的数字移动到数组的后半部分
        int k = arr1.length;
        for(int l=0; l<k;) {
            if(memo[arr1[l]][0] == 1) {
                memo[arr1[l]][1]++;
                l++;
                continue;
            }
            k--;
            if(k == l) {
                break;
            }
            arr1[k] = arr1[k] + arr1[l];
            arr1[l] = arr1[k] - arr1[l];
            arr1[k] = arr1[k] - arr1[l];
        }

        // 根据数字的出现次数，生成arr1的前半部分(包含在arr2中的元素)
        int pos = 0;
        for(int num : arr2) {
            for(int i=0; i<memo[num][1]; i++) {
                arr1[pos++] = num;
            }
        }
        // 将未包含在arr2中的元素升序排序
        Arrays.sort(arr1, pos, arr1.length);
        return arr1;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = new int[]{2,1,4,3,9,6};
        // [2,2,2,1,4,3,3,9,6,7,19]
        System.out.println(Arrays.toString(new Solution8().relativeSortArray(arr1, arr2)));
        arr1 = new int[]{28,6,22,8,44,17};
        arr2 = new int[]{22,28,8,6};
        // [22,28,8,6,17,44]
        System.out.println(Arrays.toString(new Solution8().relativeSortArray(arr1, arr2)));
    }
}
