package structure;

import java.util.*;

//设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
//
//注意: 允许出现重复元素。
//
//
//insert(val)：向集合中插入元素 val。
//remove(val)：当 val 存在时，从集合中移除一个 val。
//getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
//
//
//示例:
//
//// 初始化一个空的集合。
//RandomizedCollection collection = new RandomizedCollection();
//
//// 向集合中插入 1 。返回 true 表示集合不包含 1 。
//collection.insert(1);
//
//// 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
//collection.insert(1);
//
//// 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
//collection.insert(2);
//
//// getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
//collection.getRandom();
//
//// 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
//collection.remove(1);
//
//// getRandom 应有相同概率返回 1 和 2 。
//collection.getRandom();
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution1 {
    // 利用HashMap<Integer, HashSet<Integer>> 与 List<Integer>来描述该结构
    // List<Integer>中存放的是实际数据
    // HashMap<Integer, HashSet<Integer>>的key是实际数据对应的key
    // HashMap<Integer, HashSet<Integer>>的value是实际数据在List<Integer>中的index
    // 15ms/45.7MB
    class RandomizedCollection {

        // 维护val与实际数据的对应关系
        Map<Integer, Set<Integer>> table = null;
        // 存储实际数据，辅助实现线性概率的random
        List<Integer> list = null;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            table = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            list.add(val);
            Set<Integer> index = table.getOrDefault(val, new HashSet<>());
            index.add(list.size()-1);
            table.put(val, index);
            return index.size() == 1;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if(table.containsKey(val)) {
                Set<Integer> index = table.get(val);
                Iterator<Integer> target = index.iterator();
                int pos = target.next();
                // 删除val对应的index
                target.remove();
                // 如果val对应的index已经不存在，清除val对应的HashSet
                if(index.size() == 0) {
                    table.remove(val);
                }
                if(pos < list.size()-1) {
                    // 将list末尾元素移动到pos
                    list.set(pos, list.get(list.size()-1));
                    // 更新末尾元素的index集合
                    Set<Integer> moved = table.get(list.get(list.size()-1));
                    moved.remove(list.size()-1);
                    moved.add(pos);
                }
                // 删除末尾元素
                list.remove(list.size()-1);
                return true;
            }
            return false;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return list.get((int)(Math.random()*list.size()));
        }
    }

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

    public static void main(String[] args) {
        RandomizedCollection rc = new Solution1().new RandomizedCollection();
        // ["RandomizedCollection","insert","insert","insert","getRandom","remove","getRandom"]
        // [[],[1],[1],[2],[],[1],[]]
//        System.out.println(rc.insert(1));
//        System.out.println(rc.insert(1));
//        System.out.println(rc.insert(2));
//        System.out.println(rc.getRandom());
//        System.out.println(rc.remove(1));
//        System.out.println(rc.getRandom());

        // ["RandomizedCollection","insert","remove","insert","getRandom","remove","insert","getRandom"]
        // [[],[-1],[-2],[-2],[],[-1],[-2],[]]
        rc = new Solution1().new RandomizedCollection();
        System.out.println(rc.insert(-1));
        System.out.println(rc.remove(-2));
        System.out.println(rc.insert(-2));
        System.out.println(rc.getRandom());
        System.out.println(rc.remove(-1));
        System.out.println(rc.insert(-2));
        System.out.println(rc.getRandom());
    }
}
