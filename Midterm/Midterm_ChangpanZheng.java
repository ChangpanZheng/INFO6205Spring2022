import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


public class Midterm_ChangpanZheng {
    public static void main(String[] args) {

    }


    // Question 1
    public ArrayList<String> missingNumbers(int[] nums, int lower, int upper) {
        ArrayList<String> results = new ArrayList<>();

        if (nums[0] > lower && nums[0] < upper) {
            results.add(getRange(lower, nums[0] - 1));
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 1) {
                results.add(getRange(nums[i - 1] + 1, nums[i] - 1));
            }
        }

        if (nums[nums.length - 1] > lower && nums[nums.length - 1] < upper) {
            results.add(getRange(nums[nums.length - 1] + 1, upper));
        }

        return results;
    }

    public String getRange(int l, int r) {
        if (l == r) {
            return l + "";
        } else {
            return l + "->" + r;
        }
    }

    // Question 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        int count = 0;
        ListNode p = head;

        while (l1 != null || l2 != null || count != 0) {
            if (l1 != null) {
                count += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                count += l2.val;
                l2 = l2.next;
            }

            p.next = new ListNode(count % 10);
            count = count / 10;
            p = p.next;
        }

        return head.next;
    }

    // Question 3
    public TreeNode constructBinaryTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    public TreeNode dfs(int[] preorder, int preStart, int preEnd,
                        int[] inorder, int inStart, int inEnd,
                        HashMap<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = inMap.get(root.val);
        int leftNums = rootIndex - inStart;

        root.left = dfs(preorder, preStart + 1, preStart + leftNums, inorder, inStart, rootIndex - 1, inMap);
        root.right = dfs(preorder, preStart + leftNums + 1, preEnd,  inorder, rootIndex + 1, inEnd, inMap);

        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Question 4
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
