package graph;

import java.util.*;

//在本问题中，有根树 指满足以下条件的有向图。
//该树只有一个根节点，所有其他节点都是该根节点的后继。
//每一个节点只有一个父节点，除了根节点没有父节点。
//
//输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
//附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
//
//结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
//
//返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
//
//示例 1:
//
//输入: [[1,2], [1,3], [2,3]]
//输出: [2,3]
//解释: 给定的有向图如下:
//      1
//     / \
//    v   v
//    2-->3
//示例 2:
//
//输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
//输出: [4,1]
//解释: 给定的有向图如下:
//     5 <- 1 -> 2
//          ^    |
//          |    v
//          4 <- 3
//注意:
//
//二维数组大小的在3到1000范围内。
//二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/redundant-connection-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution3 {
    // 27ms/48MB
    public int[] findRedundantDirectedConnection(int[][] edges) {
        // memo
        // 第一维: 节点值(key)
        // 第二维: 第一位节点指向的节点值
        int[][] memo = new int[edges.length+1][edges.length+1];
        int[] pointer = new int[edges.length+1];
        // 记录每个节点的父节点个数
        int[] parent = new int[edges.length+1];
        // 记录节点个数
        int[] count = new int[edges.length+1];
        // 唯一节点数
        int unique = 0;
        // 统计各种数据
        for(int i=edges.length-1; i>=0; i--) {
            parent[edges[i][1]]++;
            memo[edges[i][0]][pointer[edges[i][0]]++] = edges[i][1];
            if(count[edges[i][0]] == 0) {
                unique++;
            }
            count[edges[i][0]]++;
            if(count[edges[i][1]] == 0) {
                unique++;
            }
            count[edges[i][1]]++;
        }

        // 从后向前依次删除对应位置的边
        for(int i=edges.length-1; i>=0; i--) {
            //# 删除操作
            // 维护父节点数
            parent[edges[i][1]]--;
            // 维护唯一节点数
            count[edges[i][0]]--;
            count[edges[i][1]]--;
            if(count[edges[i][0]] == 0) {
                unique--;
            }
            if(count[edges[i][1]] == 0) {
                unique--;
            }

            // 维护图memo的到达节点
            for(int k=0; k<pointer[edges[i][0]]; k++) {
                if(memo[edges[i][0]][k] == edges[i][1]) {
                    for(int m=k; m<pointer[edges[i][0]]; m++) {
                        int temp = memo[edges[i][0]][m];
                        memo[edges[i][0]][m] = memo[edges[i][0]][m+1];
                        memo[edges[i][0]][m+1] = temp;
                    }
                    pointer[edges[i][0]]--;
                    break;
                }
            }

            // 计算根节点
            int root = 1;
            for(int j=1; j<parent.length; j++) {
                if(parent[j] == 0) {
                    root = j;
                    break;
                }
            }
            int[] tempParent = Arrays.copyOf(parent, parent.length);
            Arrays.sort(tempParent);
            if(tempParent[tempParent.length-unique] == 0 && tempParent[tempParent.length-unique+1] > 0 // 只有一个节点没有父节点(即只存在一个父节点)
                    && tempParent[edges.length] <= 1) { // 每个节点只有一个父节点
                int[] left = new int[]{unique};
                dfs(memo, pointer, left, root);
                // 处理环
                if(left[0] <= 0) {
                    return edges[i];
                }

            }

            //# 回溯操作
            if(count[edges[i][0]] == 0) {
                unique++;
            }
            if(count[edges[i][1]] == 0) {
                unique++;
            }
            count[edges[i][0]]++;
            count[edges[i][1]]++;
            parent[edges[i][1]]++;
            // 将删除的节点加入到图memo中
            memo[edges[i][0]][pointer[edges[i][0]]++] = edges[i][1];
        }

        return edges[0];
    }

    // 根据根节点root向下dfs，计算经过的节点数count[0]
    private void dfs(int[][] edges, int[] pointer, int[] count, int root) {
        if(count[0] == 0) {
            return;
        }
        count[0]--;
        for(int i=0; i<pointer[root]; i++) {
            dfs(edges, pointer, count, edges[root][i]);
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{1,2}, {1,3}, {2,3}}; // [2,3]
        System.out.println(Arrays.toString(new Solution3().findRedundantDirectedConnection(edges)));
        edges = new int[][]{{1,2}, {2,3}, {3,4}, {4,1}, {1,5}}; // [4,1]
        System.out.println(Arrays.toString(new Solution3().findRedundantDirectedConnection(edges)));
        // 4 -> 2 -> 1 <- 3
        // ^         |
        // | --------
        edges = new int[][]{{2,1}, {3,1}, {4,2}, {1,4}}; // [2,1]
        System.out.println(Arrays.toString(new Solution3().findRedundantDirectedConnection(edges)));
        // 1 -> 5 -> 3
        //      |
        //      v
        // 4 -> 2
        // ^    |
        // | <- v
        edges = new int[][]{{4,2}, {1,5}, {5,2}, {5,3}, {2,4}}; // [4,2]
        System.out.println(Arrays.toString(new Solution3().findRedundantDirectedConnection(edges)));
        // 1 -> 3 -> 2
        //      ^    |
        //      | <-
        edges = new int[][]{{1,3}, {3,2}, {2,3}}; // [2,3]
        System.out.println(Arrays.toString(new Solution3().findRedundantDirectedConnection(edges)));
////        // 1,3  2,4  5,1  2,5


        // [627, 164]
        edges = new int[][]{ { 896, 577 }, { 755, 784 }, { 400, 791 }, { 490, 375 }, { 534, 714 }, { 8, 575 }, { 461, 391 }, { 239, 40 }, { 306, 48 }, { 745, 62 }, { 62, 963 }, { 389, 778 }, { 395, 46 }, { 402, 36 }, { 236, 907 }, { 508, 138 }, { 222, 661 }, { 926, 32 }, { 792, 394 }, { 315, 716 }, { 806, 990 }, { 728, 25 }, { 521, 14 }, { 324, 448 }, { 482, 584 }, { 270, 230 }, { 937, 450 }, { 218, 836 }, { 759, 960 }, { 930, 146 }, { 977, 954 }, { 255, 618 }, { 649, 60 }, { 686, 380 }, { 473, 75 }, { 566, 614 }, { 281, 109 }, { 670, 537 }, { 771, 973 }, { 526, 428 }, { 572, 817 }, { 517, 702 }, { 187, 943 }, { 970, 658 }, { 601, 517 }, { 869, 744 }, { 713, 882 }, { 729, 745 }, { 907, 126 }, { 616, 111 }, { 900, 687 }, { 411, 610 }, { 727, 460 }, { 671, 754 }, { 866, 783 }, { 145, 369 }, { 484, 655 }, { 159, 912 }, { 490, 762 }, { 597, 519 }, { 55, 803 }, { 490, 810 }, { 501, 289 }, { 94, 449 }, { 822, 787 }, { 654, 700 }, { 701, 456 }, { 874, 208 }, { 796, 261 }, { 885, 738 }, { 613, 574 }, { 222, 78 }, { 368, 223 }, { 635, 93 }, { 226, 232 }, { 872, 991 }, { 113, 651 }, { 685, 304 }, { 830, 235 }, { 569, 165 }, { 948, 339 }, { 239, 854 }, { 115, 135 }, { 27, 100 }, { 949, 659 }, { 275, 388 }, { 498, 822 }, { 139, 981 }, { 501, 522 }, { 530, 940 }, { 796, 322 }, { 723, 483 }, { 332, 730 }, { 918, 686 }, { 814, 984 }, { 25, 98 }, { 224, 506 }, { 395, 241 }, { 354, 705 }, { 82, 628 }, { 156, 190 }, { 502, 24 }, { 623, 298 }, { 224, 793 }, { 120, 681 }, { 669, 540 }, { 705, 410 }, { 129, 741 }, { 471, 953 }, { 90, 122 }, { 620, 301 }, { 297, 647 }, { 581, 407 }, { 558, 856 }, { 1000, 585 }, { 875, 242 }, { 218, 97 }, { 679, 825 }, { 222, 708 }, { 590, 763 }, { 262, 430 }, { 200, 262 }, { 353, 962 }, { 655, 731 }, { 202, 695 }, { 624, 859 }, { 817, 330 }, { 175, 872 }, { 653, 977 }, { 210, 608 }, { 116, 927 }, { 738, 604 }, { 153, 918 }, { 981, 601 }, { 851, 31 }, { 346, 143 }, { 667, 163 }, { 516, 150 }, { 203, 654 }, { 531, 91 }, { 921, 217 }, { 161, 470 }, { 137, 774 }, { 123, 245 }, { 819, 327 }, { 330, 477 }, { 78, 712 }, { 544, 961 }, { 895, 1000 }, { 207, 67 }, { 235, 293 }, { 859, 945 }, { 416, 414 }, { 927, 184 }, { 715, 562 }, { 694, 765 }, { 662, 566 }, { 493, 606 }, { 934, 901 }, { 326, 904 }, { 383, 11 }, { 284, 910 }, { 860, 842 }, { 570, 302 }, { 68, 401 }, { 794, 670 }, { 284, 623 }, { 927, 866 }, { 138, 965 }, { 757, 295 }, { 215, 35 }, { 139, 491 }, { 515, 458 }, { 228, 644 }, { 931, 883 }, { 450, 733 }, { 951, 168 }, { 588, 216 }, { 239, 376 }, { 819, 751 }, { 151, 690 }, { 328, 405 }, { 992, 206 }, { 582, 914 }, { 282, 595 }, { 481, 534 }, { 9, 815 }, { 597, 128 }, { 797, 583 }, { 942, 433 }, { 763, 195 }, { 865, 268 }, { 602, 342 }, { 552, 782 }, { 109, 889 }, { 782, 586 }, { 760, 621 }, { 971, 988 }, { 506, 49 }, { 61, 929 }, { 224, 133 }, { 508, 142 }, { 699, 33 }, { 126, 868 }, { 338, 313 }, { 353, 315 }, { 139, 243 }, { 408, 931 }, { 1000, 38 }, { 385, 283 }, { 923, 799 }, { 391, 518 }, { 796, 349 }, { 129, 130 }, { 460, 865 }, { 352, 826 }, { 356, 831 }, { 490, 331 }, { 367, 192 }, { 96, 629 }, { 115, 821 }, { 90, 82 }, { 234, 103 }, { 676, 903 }, { 55, 164 }, { 151, 285 }, { 965, 185 }, { 197, 549 }, { 477, 926 }, { 197, 390 }, { 520, 938 }, { 672, 547 }, { 720, 383 }, { 567, 413 }, { 949, 734 }, { 712, 207 }, { 881, 896 }, { 373, 487 }, { 258, 756 }, { 26, 707 }, { 316, 726 }, { 734, 875 }, { 999, 967 }, { 171, 446 }, { 581, 760 }, { 480, 947 }, { 269, 291 }, { 285, 795 }, { 483, 816 }, { 422, 464 }, { 742, 336 }, { 757, 435 }, { 182, 813 }, { 337, 974 }, { 717, 187 }, { 42, 54 }, { 589, 465 }, { 899, 955 }, { 921, 271 }, { 317, 808 }, { 299, 662 }, { 948, 749 }, { 5, 850 }, { 134, 948 }, { 431, 916 }, { 483, 717 }, { 347, 932 }, { 148, 750 }, { 299, 432 }, { 234, 801 }, { 825, 316 }, { 922, 359 }, { 118, 95 }, { 880, 946 }, { 408, 457 }, { 774, 709 }, { 133, 39 }, { 473, 515 }, { 182, 194 }, { 58, 279 }, { 926, 118 }, { 448, 935 }, { 346, 354 }, { 643, 325 }, { 297, 680 }, { 520, 418 }, { 108, 8 }, { 337, 17 }, { 680, 649 }, { 676, 107 }, { 6, 264 }, { 325, 769 }, { 276, 461 }, { 885, 459 }, { 981, 879 }, { 671, 664 }, { 73, 311 }, { 7, 996 }, { 234, 941 }, { 279, 922 }, { 454, 543 }, { 640, 893 }, { 998, 338 }, { 524, 563 }, { 555, 507 }, { 348, 474 }, { 786, 528 }, { 638, 469 }, { 672, 698 }, { 421, 345 }, { 986, 634 }, { 117, 73 }, { 17, 21 }, { 291, 532 }, { 321, 47 }, { 167, 510 }, { 889, 406 }, { 642, 145 }, { 811, 272 }, { 882, 181 }, { 123, 154 }, { 608, 167 }, { 627, 164 }, { 973, 356 }, { 341, 59 }, { 183, 823 }, { 69, 972 }, { 536, 280 }, { 877, 802 }, { 872, 505 }, { 294, 92 }, { 842, 10 }, { 25, 913 }, { 132, 385 }, { 593, 169 }, { 933, 254 }, { 634, 640 }, { 972, 123 }, { 874, 16 }, { 518, 496 }, { 254, 177 }, { 246, 683 }, { 572, 713 }, { 795, 362 }, { 460, 639 }, { 202, 273 }, { 531, 379 }, { 607, 983 }, { 401, 157 }, { 718, 219 }, { 286, 198 }, { 492, 758 }, { 343, 780 }, { 775, 785 }, { 270, 427 }, { 416, 862 }, { 651, 451 }, { 437, 525 }, { 448, 788 }, { 952, 137 }, { 17, 691 }, { 184, 42 }, { 458, 108 }, { 731, 612 }, { 998, 951 }, { 640, 599 }, { 894, 429 }, { 367, 155 }, { 254, 294 }, { 726, 447 }, { 625, 484 }, { 242, 807 }, { 319, 403 }, { 379, 632 }, { 999, 837 }, { 409, 887 }, { 971, 588 }, { 952, 442 }, { 540, 930 }, { 413, 116 }, { 857, 214 }, { 638, 538 }, { 636, 424 }, { 892, 624 }, { 420, 676 }, { 187, 986 }, { 827, 933 }, { 370, 220 }, { 737, 463 }, { 566, 848 }, { 9, 739 }, { 770, 200 }, { 911, 134 }, { 622, 952 }, { 30, 501 }, { 375, 159 }, { 441, 238 }, { 501, 367 }, { 709, 256 }, { 720, 480 }, { 776, 153 }, { 239, 672 }, { 986, 500 }, { 496, 250 }, { 433, 897 }, { 531, 351 }, { 603, 884 }, { 726, 52 }, { 73, 127 }, { 442, 382 }, { 767, 627 }, { 949, 757 }, { 921, 809 }, { 305, 827 }, { 558, 355 }, { 96, 561 }, { 813, 523 }, { 235, 215 }, { 529, 890 }, { 990, 9 }, { 209, 631 }, { 304, 888 }, { 615, 346 }, { 312, 666 }, { 340, 979 }, { 672, 76 }, { 145, 626 }, { 133, 720 }, { 743, 558 }, { 19, 224 }, { 123, 389 }, { 457, 87 }, { 461, 531 }, { 404, 437 }, { 838, 416 }, { 512, 722 }, { 788, 652 }, { 519, 395 }, { 101, 886 }, { 116, 917 }, { 616, 348 }, { 800, 350 }, { 366, 209 }, { 915, 843 }, { 365, 555 }, { 211, 227 }, { 259, 638 }, { 463, 546 }, { 166, 536 }, { 256, 277 }, { 72, 252 }, { 347, 923 }, { 884, 45 }, { 149, 286 }, { 268, 942 }, { 98, 869 }, { 183, 246 }, { 831, 426 }, { 249, 579 }, { 536, 975 }, { 580, 226 }, { 841, 22 }, { 931, 727 }, { 31, 570 }, { 456, 786 }, { 817, 978 }, { 749, 956 }, { 940, 725 }, { 413, 524 }, { 464, 495 }, { 123, 467 }, { 904, 364 }, { 973, 420 }, { 563, 736 }, { 227, 636 }, { 477, 592 }, { 43, 870 }, { 921, 694 }, { 485, 50 }, { 695, 12 }, { 506, 444 }, { 178, 193 }, { 591, 746 }, { 72, 83 }, { 218, 580 }, { 201, 398 }, { 949, 396 }, { 452, 189 }, { 629, 334 }, { 455, 737 }, { 350, 721 }, { 629, 419 }, { 568, 438 }, { 964, 877 }, { 147, 569 }, { 937, 378 }, { 703, 814 }, { 775, 13 }, { 229, 576 }, { 719, 473 }, { 428, 321 }, { 596, 861 }, { 611, 971 }, { 426, 160 }, { 131, 611 }, { 739, 202 }, { 809, 703 }, { 569, 874 }, { 413, 735 }, { 83, 23 }, { 385, 567 }, { 680, 332 }, { 256, 789 }, { 249, 176 }, { 189, 341 }, { 616, 689 }, { 933, 319 }, { 893, 915 }, { 84, 602 }, { 311, 482 }, { 759, 99 }, { 772, 249 }, { 621, 556 }, { 718, 526 }, { 156, 987 }, { 496, 125 }, { 873, 290 }, { 672, 247 }, { 336, 772 }, { 913, 969 }, { 695, 26 }, { 402, 573 }, { 377, 800 }, { 345, 110 }, { 194, 966 }, { 885, 650 }, { 111, 591 }, { 105, 61 }, { 129, 878 }, { 467, 212 }, { 555, 619 }, { 605, 309 }, { 507, 439 }, { 809, 839 }, { 209, 508 }, { 430, 452 }, { 339, 404 }, { 455, 443 }, { 62, 445 }, { 612, 550 }, { 754, 853 }, { 799, 255 }, { 390, 635 }, { 438, 493 }, { 500, 41 }, { 76, 511 }, { 450, 847 }, { 564, 906 }, { 136, 740 }, { 744, 173 }, { 614, 80 }, { 872, 832 }, { 252, 140 }, { 977, 693 }, { 653, 841 }, { 484, 656 }, { 613, 225 }, { 249, 131 }, { 424, 581 }, { 102, 553 }, { 260, 790 }, { 720, 596 }, { 600, 492 }, { 526, 292 }, { 35, 657 }, { 278, 72 }, { 43, 361 }, { 758, 178 }, { 637, 568 }, { 445, 259 }, { 341, 759 }, { 352, 94 }, { 644, 37 }, { 204, 222 }, { 485, 63 }, { 145, 393 }, { 774, 156 }, { 555, 333 }, { 799, 633 }, { 212, 58 }, { 901, 498 }, { 844, 840 }, { 711, 329 }, { 595, 276 }, { 196, 400 }, { 302, 732 }, { 325, 270 }, { 369, 161 }, { 300, 30 }, { 325, 777 }, { 209, 642 }, { 463, 489 }, { 733, 377 }, { 781, 834 }, { 857, 792 }, { 934, 982 }, { 556, 267 }, { 509, 257 }, { 145, 275 }, { 895, 864 }, { 86, 57 }, { 253, 819 }, { 964, 180 }, { 223, 386 }, { 379, 70 }, { 433, 2 }, { 6, 472 }, { 372, 306 }, { 7, 820 }, { 315, 197 }, { 645, 773 }, { 250, 422 }, { 881, 43 }, { 992, 617 }, { 242, 183 }, { 185, 607 }, { 22, 368 }, { 669, 968 }, { 512, 894 }, { 806, 297 }, { 993, 530 }, { 214, 307 }, { 732, 641 }, { 483, 85 }, { 509, 299 }, { 507, 265 }, { 914, 761 }, { 936, 876 }, { 658, 203 }, { 649, 218 }, { 160, 343 }, { 818, 239 }, { 674, 411 }, { 893, 6 }, { 371, 269 }, { 540, 529 }, { 70, 18 }, { 261, 71 }, { 279, 179 }, { 683, 678 }, { 789, 412 }, { 669, 399 }, { 667, 578 }, { 55, 863 }, { 922, 172 }, { 352, 542 }, { 964, 499 }, { 670, 328 }, { 21, 139 }, { 158, 213 }, { 87, 685 }, { 715, 488 }, { 945, 7 }, { 754, 237 }, { 726, 344 }, { 350, 995 }, { 630, 475 }, { 410, 478 }, { 4, 852 }, { 865, 287 }, { 454, 674 }, { 554, 775 }, { 864, 490 }, { 964, 504 }, { 37, 711 }, { 743, 742 }, { 373, 572 }, { 992, 65 }, { 544, 497 }, { 544, 51 }, { 590, 105 }, { 552, 900 }, { 721, 370 }, { 918, 158 }, { 921, 860 }, { 724, 548 }, { 116, 976 }, { 345, 454 }, { 700, 833 }, { 781, 485 }, { 300, 310 }, { 252, 106 }, { 575, 381 }, { 943, 514 }, { 586, 587 }, { 335, 892 }, { 365, 992 }, { 999, 221 }, { 434, 552 }, { 76, 871 }, { 307, 53 }, { 326, 462 }, { 452, 357 }, { 526, 767 }, { 18, 55 }, { 85, 288 }, { 906, 728 }, { 656, 28 }, { 704, 937 }, { 612, 957 }, { 819, 44 }, { 297, 503 }, { 533, 934 }, { 525, 949 }, { 696, 704 }, { 833, 114 }, { 750, 440 }, { 520, 885 }, { 256, 81 }, { 911, 320 }, { 102, 441 }, { 270, 980 }, { 215, 643 }, { 669, 653 }, { 748, 605 }, { 879, 620 }, { 293, 812 }, { 801, 90 }, { 767, 15 }, { 101, 512 }, { 190, 371 }, { 67, 27 }, { 552, 174 }, { 347, 835 }, { 240, 132 }, { 537, 589 }, { 129, 699 }, { 635, 284 }, { 944, 684 }, { 171, 715 }, { 405, 502 }, { 214, 919 }, { 726, 999 }, { 113, 818 }, { 737, 149 }, { 868, 771 }, { 130, 846 }, { 372, 881 }, { 169, 471 }, { 907, 415 }, { 439, 804 }, { 967, 152 }, { 16, 121 }, { 480, 719 }, { 781, 920 }, { 324, 911 }, { 354, 682 }, { 862, 421 }, { 571, 258 }, { 246, 849 }, { 909, 34 }, { 287, 468 }, { 345, 671 }, { 100, 352 }, { 109, 905 }, { 570, 104 }, { 113, 296 }, { 360, 692 }, { 775, 318 }, { 931, 533 }, { 721, 858 }, { 867, 196 }, { 207, 119 }, { 858, 300 }, { 648, 895 }, { 116, 697 }, { 700, 939 }, { 687, 867 }, { 288, 899 }, { 955, 950 }, { 981, 924 }, { 380, 248 }, { 30, 603 }, { 912, 748 }, { 310, 811 }, { 756, 675 }, { 624, 453 }, { 604, 281 }, { 775, 151 }, { 39, 845 }, { 861, 96 }, { 774, 985 }, { 612, 4 }, { 86, 373 }, { 398, 527 }, { 121, 282 }, { 926, 365 }, { 584, 77 }, { 373, 20 }, { 570, 509 }, { 404, 314 }, { 6, 384 }, { 784, 372 }, { 644, 710 }, { 881, 66 }, { 380, 857 }, { 739, 494 }, { 328, 755 }, { 425, 274 }, { 850, 615 }, { 638, 554 }, { 401, 56 }, { 198, 199 }, { 612, 598 }, { 340, 646 }, { 574, 582 }, { 712, 117 }, { 255, 397 }, { 253, 244 }, { 715, 768 }, { 348, 233 }, { 389, 1 }, { 984, 113 }, { 321, 374 }, { 902, 392 }, { 204, 481 }, { 375, 423 }, { 694, 89 }, { 223, 539 }, { 501, 74 }, { 37, 86 }, { 334, 366 }, { 626, 557 }, { 746, 360 }, { 657, 204 }, { 281, 3 }, { 22, 112 }, { 598, 668 }, { 475, 743 }, { 988, 663 }, { 736, 324 }, { 814, 535 }, { 125, 696 }, { 729, 571 }, { 85, 79 }, { 602, 766 }, { 683, 188 }, { 405, 312 }, { 969, 335 }, { 634, 144 }, { 509, 551 }, { 797, 236 }, { 914, 600 }, { 880, 148 }, { 996, 829 }, { 208, 593 }, { 999, 263 }, { 69, 266 }, { 890, 830 }, { 428, 436 }, { 791, 844 }, { 131, 560 }, { 726, 425 }, { 47, 64 }, { 260, 234 }, { 220, 688 }, { 882, 891 }, { 666, 201 }, { 542, 417 }, { 440, 838 }, { 299, 513 }, { 162, 409 }, { 680, 855 }, { 997, 921 }, { 820, 166 }, { 56, 124 }, { 64, 136 }, { 552, 994 }, { 828, 347 }, { 439, 993 }, { 186, 305 }, { 199, 466 }, { 881, 337 }, { 333, 997 }, { 711, 824 }, { 698, 724 }, { 225, 253 }, { 446, 718 }, { 885, 936 }, { 722, 902 }, { 840, 806 }, { 683, 175 }, { 183, 141 }, { 136, 101 }, { 894, 69 }, { 294, 170 }, { 884, 68 }, { 840, 431 }, { 54, 84 }, { 102, 229 }, { 612, 171 }, { 632, 303 }, { 225, 387 }, { 922, 898 }, { 787, 317 }, { 996, 240 }, { 391, 909 }, { 998, 260 }, { 557, 964 }, { 517, 851 }, { 674, 353 }, { 960, 630 }, { 342, 182 }, { 968, 805 }, { 820, 565 }, { 294, 723 }, { 768, 358 }, { 847, 609 }, { 566, 597 }, { 321, 402 }, { 847, 781 }, { 542, 594 }, { 645, 648 }, { 675, 797 }, { 699, 162 }, { 655, 798 }, { 89, 251 }, { 5, 340 }, { 12, 520 }, { 843, 408 }, { 73, 706 }, { 161, 231 }, { 758, 147 }, { 109, 102 }, { 14, 476 }, { 548, 928 }, { 27, 211 }, { 90, 669 }, { 680, 989 }, { 148, 544 }, { 882, 559 }, { 135, 434 }, { 31, 541 }, { 128, 729 }, { 824, 326 }, { 169, 779 }, { 173, 278 }, { 362, 616 }, { 549, 228 }, { 341, 191 }, { 29, 625 }, { 607, 998 }, { 882, 516 }, { 135, 660 }, { 702, 959 }, { 991, 613 }, { 594, 828 }, { 397, 622 }, { 890, 479 }, { 293, 205 }, { 646, 120 }, { 998, 667 }, { 707, 210 }, { 493, 665 }, { 120, 794 }, { 947, 908 }, { 176, 19 }, { 941, 323 }, { 435, 970 }, { 943, 944 }, { 728, 873 }, { 616, 776 }, { 936, 925 }, { 213, 637 }, { 433, 115 }, { 307, 880 }, { 392, 186 }, { 364, 88 }, { 238, 679 }, { 84, 747 }, { 373, 677 }, { 586, 796 }, { 484, 129 }, { 534, 521 }, { 535, 486 }, { 478, 545 }, { 226, 308 }, { 428, 753 }, { 802, 701 }, { 528, 455 }, { 125, 958 }, { 415, 764 }, { 433, 363 }, { 52, 564 }, { 534, 673 }, { 752, 645 }, { 238, 590 }, { 928, 752 }, { 511, 770 }, { 532, 29 } };
        System.out.println(Arrays.toString(new Solution3().findRedundantDirectedConnection(edges)));
    }
}
