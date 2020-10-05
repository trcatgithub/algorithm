package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给定一个包含 n 个整数的数组 nums 和一个目标值 target，
//判断 nums 中是否存在四个元素 a，b，c 和 d ，
//使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
//
//注意：
//
//答案中不可以包含重复的四元组。
//
//示例：
//
//给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//[-1,  0, 0, 1],
//[-2, -1, 1, 2],
//[-2,  0, 0, 2]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/4sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution9 {

    // 5ms/38.8MB
    // 双指针
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<nums.length; i++) {
            // 过滤重复组合(过滤首位重复)
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for(int j=i+1; j<nums.length; j++) {
                int twoSum = nums[i] + nums[j];
                // 剪支(减少重复计算)
                if(j < nums.length-2) {
                    if(twoSum + nums[nums.length-1] + nums[nums.length-2] < target
                            || twoSum + nums[j+1] + nums[j+2] > target) {
                        continue;
                    }
                }else {
                    break;
                }
                // 过滤重复组合(过滤第二位重复)
                if(j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }
                int left = j+1, right = nums.length-1;
                // 双指针判断
                while(left < right) {
                    // 过滤重复组合(过滤第三位重复)
                    if(left > j+1 && nums[left] == nums[left-1]) {
                        left++;
                        continue;
                    }
                    if(nums[left] + nums[right] + twoSum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                    }else if(nums[left] + nums[right] + twoSum < target) {
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }
        return res;
    }


//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        List<List<Integer>> res = new ArrayList<>();
//        Arrays.sort(nums);
//        helper(res, new ArrayList<>(), nums, target, 0);
//        return res;
//    }
//
//    // 剪支回溯
//    // 647ms/39.6 MB
//    private void helper(List<List<Integer>> res, List<Integer> current, int[] nums, int target, int p) {
//        if(current.size() == 4 && target == 0) {
//            res.add(new ArrayList<>(current));
//            return;
//        }
//        if(current.size() >= 4) {
//            return;
//        }
//        for(int i=p; i<nums.length; i++) {
//            // 剪支
//            // 条件1: target剪掉最大两个值后仍大于0(用于处理原target为正数的情况)
//            // 条件2: target剪掉最小两个值后仍小于0(用于处理原target为负数的情况)
//            if (current.size() == 2
//                && ((nums.length-2 >= p
//                    && target - nums[nums.length-1] - nums[nums.length-2] > 0) // target剪掉最大两个值后仍大于0
//                    || (p+1 < nums.length && target - nums[p] - nums[p+1] < 0))) { // target剪掉最小两个值后仍小于0
//                return;
//            }
//
//            // 过滤重复的组合
//            if(i > p && nums[i] == nums[i-1]) {
//                continue;
//            }
//            current.add(nums[i]);
//            target-= nums[i];
//            helper(res, current, nums, target, i+1);
//            current.remove(current.size()-1);
//            target+= nums[i];
//        }
//    }

    private static void printList(List<List<Integer>> list) {
        for(List<Integer> e : list) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        // -2 -1 0 0 1 2
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;
        //[
        //[-1,  0, 0, 1],
        //[-2, -1, 1, 2],
        //[-2,  0, 0, 2]
        //]
        printList(new Solution9().fourSum(nums, target));
        nums = new int[]{-2,-1,-1,1,1,2,2};
        target = 0;
        printList(new Solution9().fourSum(nums, target)); // [[-2,-1,1,2],[-1,-1,1,1]]
        nums = new int[]{-499,-486,-479,-462,-456,-430,-415,-413,-399,-381,-353,-349,-342,-337,-336,-331,-330,-322,-315,-280,-271,-265,-249,-231,-226,-219,-216,-208,-206,-204,-188,-159,-144,-139,-123,-115,-99,-89,-80,-74,-61,-22,-22,-8,-5,4,43,65,82,86,95,101,103,123,149,152,162,165,168,183,204,209,209,220,235,243,243,244,248,253,260,273,281,284,288,290,346,378,382,384,407,411,423,432,433,445,470,476,497};
        target = 3032;
        printList(new Solution9().fourSum(nums, target));
        nums = new int[]{-4,-3,-2,-1,0};
        target = -10;
        printList(new Solution9().fourSum(nums, target));
        nums = new int[]{-11,0,0,1};
        target = -10;
        printList(new Solution9().fourSum(nums, target));

        nums = new int[]{91277418,66271374,38763793,4092006,11415077,60468277,1122637,72398035,-62267800,22082642,60359529,-16540633,92671879,-64462734,-55855043,-40899846,88007957,-57387813,-49552230,-96789394,18318594,-3246760,-44346548,-21370279,42493875,25185969,83216261,-70078020,-53687927,-76072023,-65863359,-61708176,-29175835,85675811,-80575807,-92211746,44755622,-23368379,23619674,-749263,-40707953,-68966953,72694581,-52328726,-78618474,40958224,-2921736,-55902268,-74278762,63342010,29076029,58781716,56045007,-67966567,-79405127,-45778231,-47167435,1586413,-58822903,-51277270,87348634,-86955956,-47418266,74884315,-36952674,-29067969,-98812826,-44893101,-22516153,-34522513,34091871,-79583480,47562301,6154068,87601405,-48859327,-2183204,17736781,31189878,-23814871,-35880166,39204002,93248899,-42067196,-49473145,-75235452,-61923200,64824322,-88505198,20903451,-80926102,56089387,-58094433,37743524,-71480010,-14975982,19473982,47085913,-90793462,-33520678,70775566,-76347995,-16091435,94700640,17183454,85735982,90399615,-86251609,-68167910,-95327478,90586275,-99524469,16999817,27815883,-88279865,53092631,75125438,44270568,-23129316,-846252,-59608044,90938699,80923976,3534451,6218186,41256179,-9165388,-11897463,92423776,-38991231,-6082654,92275443,74040861,77457712,-80549965,-42515693,69918944,-95198414,15677446,-52451179,-50111167,-23732840,39520751,-90474508,-27860023,65164540,26582346,-20183515,99018741,-2826130,-28461563,-24759460,-83828963,-1739800,71207113,26434787,52931083,-33111208,38314304,-29429107,-5567826,-5149750,9582750,85289753,75490866,-93202942,-85974081,7365682,-42953023,21825824,68329208,-87994788,3460985,18744871,-49724457,-12982362,-47800372,39958829,-95981751,-71017359,-18397211,27941418,-34699076,74174334,96928957,44328607,49293516,-39034828,5945763,-47046163,10986423,63478877,30677010,-21202664,-86235407,3164123,8956697,-9003909,-18929014,-73824245};
        target = -236727523;
        printList(new Solution9().fourSum(nums, target));

        nums = new int[]{-1,-5,-5,-3,2,5,0,4};
        target = -7;
        printList(new Solution9().fourSum(nums, target)); // [[-5,-5,-1,4],[-5,-3,-1,2]]
    }
}
