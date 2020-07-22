package search;

//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
//例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
//
//示例 1：
//
//输入：[3,4,5,1,2]
//输出：1
//示例 2：
//
//输入：[2,2,2,0,1]
//输出：0
//注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution2 {

    // 二分
    // 1ms/39.9MB
    public int minArray(int[] numbers) {
        for(int l=0,r=numbers.length-1; l<r;) {
            int mid = (l+r)/2;
            // 找到旋转点
            if(mid>=1 && numbers[mid] < numbers[mid-1]) {
                return numbers[mid];
            }else if(mid < numbers.length-1 && numbers[mid] > numbers[mid+1]) { // 找到旋转点
                return numbers[mid+1];
            }else if(numbers[mid] <= numbers[r]) { // 缩小右边界
                r--;
            }else if(numbers[mid] >= numbers[l] ) { // 缩小左边界
                l++;
            }
        }

        // 默认值，数组为发生旋转时，返回第一个元素
        return numbers[0];
    }

//    // 遍历数组，寻找旋转点
//    // 1ms/39.5MB
//    public int minArray(int[] numbers) {
//        for(int i=0; i<numbers.length-1; i++) {
//            // 找到旋转点时，返回最小值
//            if(numbers[i] > numbers[i+1]) {
//                return numbers[i+1];
//            }
//        }
//        // 不存在旋转点时，返回第一个元素
//        return numbers[0];
//    }

    public static void main(String[] args) {
        int[] numbers = new int[]{3,4,5,1,2};
        System.out.println(new Solution2().minArray(numbers));
        numbers = new int[]{2,2,2,0,1};
        System.out.println(new Solution2().minArray(numbers));
        numbers = new int[]{1,3,5};
        System.out.println(new Solution2().minArray(numbers));
        numbers = new int[]{3,1,1};
        System.out.println(new Solution2().minArray(numbers));
        numbers = new int[]{2,2,2,2,1};
        System.out.println(new Solution2().minArray(numbers));
        numbers = new int[]{1,1};
        System.out.println(new Solution2().minArray(numbers));
        numbers = new int[]{3,3,3,3,3,3,3,3,1,3};
        System.out.println(new Solution2().minArray(numbers));
    }

//    public int minArray(int[] numbers) {
//        int low = 0;
//        int high = numbers.length - 1;
//        while (low < high) {
//            int pivot = low + (high - low) / 2;
//            if (numbers[pivot] < numbers[high]) {
//                high = pivot;
//            } else if (numbers[pivot] > numbers[high]) {
//                low = pivot + 1;
//            } else {
//                high -= 1;
//            }
//        }
//        return numbers[low];
//    }
}
