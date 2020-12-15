package math;

//给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
//
//（当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
//
//示例 1:
//
//输入: N = 10
//输出: 9
//示例 2:
//
//输入: N = 1234
//输出: 1234
//示例 3:
//
//输入: N = 332
//输出: 299
//说明: N 是在 [0, 10^9] 范围内的一个整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/monotone-increasing-digits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution7 {
    // 贪心
    // 从前向后遍历，寻找第一个波峰。
    // 从波峰向前遍历，寻找位置pos ((int)cs[pos]-1 >= cs[pos-1])
    // 将位置pos之后的数字都变成9
    // 1ms/35.5MB
    public int monotoneIncreasingDigits(int N) {
        char[] cs = String.valueOf(N).toCharArray();
        int res = 0;
        int pos = -1;
        // 寻找波峰，根据波峰寻找位置pos
        for(int i=0; i<cs.length-1; i++) {
            if(cs[i] > cs[i+1]) {
                if(i == 0) {
                    cs[i] = (char)((int)cs[i]-1);
                    pos = i+1;
                }else if((int)cs[i]-1 >= cs[i-1]) {
                    cs[i] = (char)((int)cs[i]-1);
                    pos = i+1;
                }else {
                    for(int j=i-1; j>=0; j--) {
                        if(j == 0 || (int)cs[j]-1 >= cs[j-1]) {
                            cs[j] = (char)((int)cs[j]-1);
                            pos = j+1;
                            break;
                        }
                    }
                }
                break;
            }
        }
        // 补9
        if(pos >= 0) {
            for(int i=pos; i<cs.length; i++) {
                cs[i] = '9';
            }
        }
        // 将字符数组转换为数字
        for(int i=cs.length-1, base=1; i>=0; i--) {
            res+= (cs[i]-'0')*base;
            base*= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution7().monotoneIncreasingDigits(10)); // 9
        System.out.println(new Solution7().monotoneIncreasingDigits(100)); // 99
        System.out.println(new Solution7().monotoneIncreasingDigits(110)); // 99
        System.out.println(new Solution7().monotoneIncreasingDigits(1234)); // 1234
        System.out.println(new Solution7().monotoneIncreasingDigits(332)); // 299
        // 1323 1299
        // 210
        // 1110 -> 999
        // 11110 -> 9999
        // 1332 -> 1299
        System.out.println("=========");
        System.out.println(new Solution7().monotoneIncreasingDigits(1323)); // 1299
        System.out.println(new Solution7().monotoneIncreasingDigits(210)); // 199
        System.out.println(new Solution7().monotoneIncreasingDigits(1110)); // 999
        System.out.println(new Solution7().monotoneIncreasingDigits(11110)); // 9999
        System.out.println(new Solution7().monotoneIncreasingDigits(1332)); // 1299

    }

    // 1 2 3 4 5 6 7 8 9
    // 11 12 13 14 15 16 17 18 19
    // 22 23 24 25 26 27 28 29
    // 33 34 35 36 37 38 39
    // 44 45 46 47 48 49
    // 55 56 57 58 59
    // 66 67 68 69
    // 77 78 79
    // 88 89
    // 99
    // 111 112 113 114 115 116 117 118 119
    // 122 123 124 125 126 127 128 129
    // 133 134 135 136 137 138 139
    // 144 145 146 147 148 149
    // 155 156 157 158 159
    // 166 167 168 169
    // 177 178 179
    // 188 189
    // 199
    // 222 223 224 225 226 227 228 229
    // 233 234 235 236 237 238 239
    // 244 245 246 247 248 249
    // 255 256 257 258 259
    // 266 267 268 269
    // 277 278 279
    // 288 289
    // 299
    // 333 334 335 336 337 338 339
    // 344 345 346 347 348 349
    // 355 356 357 358 359
    // 366 367 368 369
    // 377 378 379
    // 388 389
    // 399
    // 444 445 446 447 448 449
    // 455 456 457 458 459
    // 466 467 468 469
    // 477 478 479
    // 488 489
    // 499
    // 555 556 557 558 559
    // 566 567 568 569
    // 577 578 579
    // 588 589
    // 599
    // 666 667 668 669
    // 677 678 679
    // 688 689
    // 699
    // 777 778 779
    // 788 789
    // 799
    // 888 889
    // 899
    // 999
    // 1111 1112 1113 1114 1115 1116 117 1118 1119
    // 1122 1123 1124 1125 1126 1127 1128 1129
    // 1133 1134 1135 1136 1137 1138 1139
    // 1144 1145 1146 1147 1148 1149
    // 1155 1156 1157 1158 1159
    // 1166 1167 1168 1169
    // 1177 1178 1179
    // 1188 1189
    // 1199
    // 1222 1223 1224 1225 1226 1227 1228 1229
    // 1233 1234 1235 1236 1237 1238 1239
    // 1244 1245 1246 1247 1248 1249
    // 1255 1256 1257 1258 1259
    // 1266 1267 1268 1269
    // 1277 1278 1279
    // 1288 1289
    // 1299

}
