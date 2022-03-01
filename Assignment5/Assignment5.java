import java.util.ArrayList;
import java.util.List;

public class Assignment5 {
    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Assignment 1
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        levelOrderBottomHelper(results, root, 0);
        return results;
    }

    public void levelOrderBottomHelper(List<List<Integer>> results, TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        if (depth == results.size()) {
            results.add(0, new ArrayList<Integer>());
        }

        levelOrderBottomHelper(results, root.left, depth + 1);
        levelOrderBottomHelper(results, root.right, depth + 1);

        results.get(results.size() - 1 - depth).add(root.val);
    }

    // Assignment 2
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        findLeavesHelper(results, root);
        return results;
    }

    public int findLeavesHelper(List<List<Integer>> results, TreeNode root) {
        // exit
        if (root == null) {
            return -1;
        }

        // divide
        int left = findLeavesHelper(results, root.left);
        int right = findLeavesHelper(results, root.right);

        // merge
        int level = Math.max(left, right) + 1;

        // enlarge the results size if necessary and add root.val
        if (level == results.size()) {
            List<Integer> sub = new ArrayList<>();
            sub.add(root.val);
            results.add(sub);
        } else {
            results.get(level).add(root.val);
        }

        return level;
    }

    // Assignment 3
    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<>(), new ArrayList<>());
    }

    public int dfs(TreeNode root, int level, int order, List<Integer> start, List<Integer> end) {
        if (root == null) {
            return 0;
        }

        if(start.size() == level) {
            start.add(order);
            end.add(order);
        } else {
            end.set(level, order);
        }

        int cur = end.get(level) - start.get(level) + 1;
        int left = dfs(root.left, level + 1, 2 * order, start, end);
        int right = dfs(root.right, level + 1, 2 * order + 1, start, end);

        return Math.max(cur, Math.max(left, right));
    }

    // Assignment 4
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        largestValuesHelper(root, result, 0);
        return result;
    }

    public void largestValuesHelper(TreeNode root, List<Integer> result, int depth) {
        if (root == null) {
            return;
        }

        // expand the list size
        if (depth == result.size()) {
            result.add(root.val);
        } else {
            result.set(depth, Math.max(result.get(depth), root.val));
        }

        largestValuesHelper(root.left, result, depth + 1);
        largestValuesHelper(root.right, result, depth + 1);
    }
}
