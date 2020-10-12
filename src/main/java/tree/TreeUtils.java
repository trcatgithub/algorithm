package tree;

import java.util.*;

public class TreeUtils {

    public static TreeNode generateTree(Integer[] nodes) {
        if(nodes == null || nodes.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for(int pos=1; pos<nodes.length;) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                TreeNode current = queue.poll();
                if(pos < nodes.length && nodes[pos] != null) {
                    current.left = new TreeNode(nodes[pos]);
                    queue.offer(current.left);
                }
                pos++;
                if(pos < nodes.length && nodes[pos] != null) {
                    current.right = new TreeNode(nodes[pos]);
                    queue.offer(current.right);
                }
                pos++;
            }

        }
        return root;
    }

//    // backup-2020/10/12
//    public static TreeNode generateTree(Integer[] nodes) {
//        if(nodes == null || nodes.length == 0) {
//            return null;
//        }
//        // {1,2,null,3,null,4,null,5};
//        TreeNode root = new TreeNode(nodes[0]);
//        List<TreeNode> current = new ArrayList<>();
//        current.add(root);
//        int pos = 1;
//        while(pos < nodes.length) {
//            List<TreeNode> nextLevel = new ArrayList<>();
//            for(TreeNode node : current) {
//                if(nodes[pos] != null) {
//                    node.left = new TreeNode(nodes[pos++]);
//                    nextLevel.add(node.left);
//                }else {
//                    pos++;
//                }
//                if(pos == nodes.length) {
//                    return root;
//                }
//                if(nodes[pos] != null) {
//                    node.right = new TreeNode(nodes[pos++]);
//                    nextLevel.add(node.right);
//                }else {
//                    pos++;
//                }
//                if(pos == nodes.length) {
//                    return root;
//                }
//            }
//            if(nextLevel.size() > 0) {
//                current = nextLevel;
//            }else {
//                break;
//            }
//        }
//        return root;
//    }

    public static void printTree(TreeNode root) {
        if(root == null) {
            System.out.println("[]");
        }
        List<Integer> all = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);

        while(true) {
            List<TreeNode> nextLevel = new ArrayList<>();
            List<Integer> vals = new ArrayList<>();
            boolean notAllNull = false;
            for(TreeNode node : nodes) {
                if(node == null) {
                    vals.add(null);
                    nextLevel.add(null);
                    nextLevel.add(null);
                }else {
                    vals.add(node.val);
                    notAllNull = true;
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                }
            }
            if(notAllNull) {
                nodes = nextLevel;
                all.addAll(vals);
            }else {
                break;
            }
        }
        System.out.println(all.toString());
    }
}
