package search;

//魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，
//在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
//
//示例1:
//
//输入：nums = [0, 2, 3, 4, 5]
//输出：0
//说明: 0下标的元素为0
//示例2:
//
//输入：nums = [1, 1, 1]
//输出：1
//提示:
//
//nums长度在[1, 1000000]之间
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/magic-index-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution3 {

    // 跳跃法
    // 0ms/40.4MB
    public int findMagicIndex(int[] nums) {
        for(int i=0;i<nums.length; ){
            if(nums[i]==i)
                return i;
            // 每次选择 当前位置+1 与 当前位置值 中较大的作为跳跃步数
            i=Math.max(nums[i],i+1);
        }
        return -1;
    }


//    // 从前向后遍历数组
//    // 0ms/40.7MB
//    public int findMagicIndex(int[] nums) {
//        for(int i=0; i<nums.length; i++) {
//            // 魔术索引
//            if(i == nums[i]) {
//                return i;
//            }
//        }
//        return -1;
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,3,4,5};
        System.out.println(new Solution3().findMagicIndex(nums));
//        nums = new int[]{1,1,1};
//        System.out.println(new Solution8().findMagicIndex(nums));
//        nums = new int[]{0,2,2,3,4};
//        System.out.println(new Solution8().findMagicIndex(nums));
//        nums = new int[]{0,0,0,3,4};
//        System.out.println(new Solution8().findMagicIndex(nums));
        // 1,2,3,3,4
        // 0,1,2,3,4

        // 0,2,3,4,5
        // 0,1,2,3,4
    }
}
