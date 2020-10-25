package unclassify;

//我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
//
//
//B.length >= 3
//存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
//
//
//（注意：B 可以是 A 的任意子数组，包括整个数组 A。）
//
//给出一个整数数组 A，返回最长 “山脉” 的长度。
//
//如果不含有 “山脉” 则返回 0。
//
//
//
//示例 1：
//
//输入：[2,1,4,7,3,2,5]
//输出：5
//解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
//
//
//示例 2：
//
//输入：[2,2,2]
//输出：0
//解释：不含 “山脉”。
//
//
//
//
//提示：
//
//
//0 <= A.length <= 10000
//0 <= A[i] <= 10000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-mountain-in-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution29 {
    // 分别计算升序与降序元素的个数，在顺序发生改变时，将其累加并与longest比较
    // 3ms/39.6MB
    public int longestMountain(int[] A) {
        int longest = 0;
        int ascCount = 0;
        int descCount = 0;
        for(int i=0; i<A.length; i++) {
            // 寻找山脉的起点
            if((i == 0 || (i > 0 && A[i] <= A[i-1])) && (i < A.length-1 && A[i] < A[i+1])) {
                int prev = A[i];
                ascCount = 1;
                // 从起点向后遍历判断
                for(int j=i+1; j<A.length; j++) {
                    // 升序
                    if(prev < A[j]) {
                        if(ascCount > 0 && descCount > 0) {
                            // 计算最大长度
                            longest = Math.max(longest, ascCount+descCount);
                            ascCount = 1;
                            descCount = 0;
                        }
                        ascCount++;
                    }else if(prev > A[j]) {// 降序
                        descCount++;
                        if(j == A.length-1 && ascCount > 0) {
                            // 计算最大长度
                            return Math.max(longest, ascCount+descCount);
                        }
                    }else {// 相等
                        if(ascCount > 0 && descCount > 0) {
                            longest = Math.max(longest, ascCount+descCount);
                        }
                        // 注意更新i的位置，减少重复计算
                        i = j-1;
                        break;
                    }
                    prev = A[j];
                    if(j == A.length-1) {
                        // 返回最大长度
                        return longest;
                    }
                }
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] A = new int[]{2,1,4,7,3,2,5};
        System.out.println(new Solution29().longestMountain(A)); // 5
        A = new int[]{2,2,2};
        System.out.println(new Solution29().longestMountain(A)); // 0
        A = new int[]{1,2,3};
        System.out.println(new Solution29().longestMountain(A)); // 0
        A = new int[]{1,2,3,2};
        System.out.println(new Solution29().longestMountain(A)); // 4
        A = new int[]{2,3,3,2,0,2};
        System.out.println(new Solution29().longestMountain(A)); // 0
        A = new int[]{875,884,239,731,723,685};
        System.out.println(new Solution29().longestMountain(A)); // 4
        A = new int[]{0,2,0,0,2,0,2,1,1,0,2,1,0,0,1,0,2,1,2,0,1,1,0,2,2,
                1,2,2,0,0,0,1,0,2,0,0,1,2,0,1,0,2,0,2,0,0,0,0,2,1,0,0,0,
                0,1,0,2,1,2,2,1,0,0,1,0,2,0,0,0,2,1, 0,1,2,1,0 ,1,0,2,1,0,
                2,0,2,1,1,2,0,1,0,1,1,1,1,2,1,2,2,2,0};
        System.out.println(new Solution29().longestMountain(A)); // 5
        A = new int[]{0,2,1,0,1,2,1,0};
        System.out.println(new Solution29().longestMountain(A)); // 5
    }
}
