package bit;

//两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
//
//给出两个整数 x 和 y，计算它们之间的汉明距离。
//
//注意：
//0 ≤ x, y < 2^31.
//
//示例:
//
//输入: x = 1, y = 4
//
//输出: 2
//
//解释:
//1   (0 0 0 1)
//4   (0 1 0 0)
//       ↑   ↑
//
//上面的箭头指出了对应二进制位不同的位置。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/hamming-distance
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution2 {

    // 0ms/35.2MB
    public int hammingDistance(int x, int y) {
        // 同位只有一个1时,^结果才为1
        int ret = x^y;
        int count = 0;
        while(ret != 0) {
            count+= ret&1; // 当前位为1时,进行累加
            ret>>= 1; // 右移1位
            // 101  >>1  010

        }
        return count;
    }

//    // 双指针
//    // 1ms/34.9MB
//    public int hammingDistance(int x, int y) {
//        char[] cx = Integer.toBinaryString(x).toCharArray();
//        char[] cy = Integer.toBinaryString(y).toCharArray();
//        int count = 0;
//        for(int p1=cx.length-1, p2=cy.length-1; p1>=0 || p2>=0;) {
//            if(p1 == -1) {
//                if(cy[p2] == '1') {
//                    count++;
//                }
//                p2--;
//                continue;
//            }else if(p2 == -1) {
//                if(cx[p1] == '1') {
//                    count++;
//                }
//                p1--;
//                continue;
//            }
//            if(cx[p1] != cy[p2]) {
//                count++;
//            }
//            p1--;
//            p2--;
//        }
//        return count;
//    }

    public static void main(String[] args) {
        int x = 1;
        int y = 4;
        System.out.println(new Solution2().hammingDistance(x, y)); // 2
        x = 0;
        y = 0;
        System.out.println(new Solution2().hammingDistance(x, y)); // 0

        // 101
        System.out.println(5>>1);
        System.out.println(5>>>1);
        System.out.println(5<<1);
    }
}
