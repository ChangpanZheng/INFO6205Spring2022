import java.util.*;

public class Assignment6 {
    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

        // Question 1
        int count1;

        public int pathSum(TreeNode root, int k) {
            int currSum = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);

            pathSumHelper(root, currSum, k, map);

            return count1;
        }

        public void pathSumHelper(TreeNode root, int currSum, int k, Map<Integer, Integer> map) {
            // exit
            if (root == null) {
                return;
            }

            currSum += root.val;

            // determine
            if (map.containsKey(currSum - k)) {
                count1 += map.get(currSum - k);
            }

            // backtracking - add
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);

            // backtracking - operation
            pathSumHelper(root.left, currSum, k, map);
            pathSumHelper(root.right, currSum, k, map);

            // backtracking - remove
            map.put(currSum, map.get(currSum) - 1);

        }


        // Question 2
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == q || root == p) {
                return root;
            }

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            if (left != null && right != null) {
                return root;
            }

            if (left != null) {
                return left;
            }

            if (right != null) {
                return right;
            }

            return null;
        }

        // Question 3
        int count3;
        public int longestUnivaluePath(TreeNode root) {
            count3 = 0;
            if (root == null) {
                return 0;
            }

            longestUnivaluePathHelper(root);

            return count3;
        }

        public int longestUnivaluePathHelper(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int left = longestUnivaluePathHelper(root.left);
            int right = longestUnivaluePathHelper(root.right);

            int pathLeft = 0, pathRight = 0;

            if (root.left != null && root.left.val == root.val) {
                pathLeft = left + 1;
            }

            if (root.right != null && root.right.val == root.val) {
                pathRight = right + 1;
            }

            count3 = Math.max(count3, pathLeft + pathRight);
            return Math.max(pathLeft, pathRight);
        }

        // Question 4
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            preorderBuilder(builder, root);
            return builder.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] valList = data.split(",");
            Queue<String> valQueue = new LinkedList<>();
            for (String val : valList) {
                valQueue.add(val);
            }
            return buildTree(valQueue);

        }

        public TreeNode buildTree(Queue<String> valList) {
            if (valList.isEmpty()) {
                return null;
            }
            String val = valList.remove();
            if (val.equals("null")) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(valList);
            node.right = buildTree(valList);
            return node;
        }

        public void preorderBuilder(StringBuilder builder, TreeNode node) {
            if (node == null) {
                builder.append("null");
                builder.append(',');
                return;
            }
            builder.append(String.valueOf(node.val));
            builder.append(",");
            preorderBuilder(builder, node.left);
            preorderBuilder(builder, node.right);
        }

        // Question 5
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            PriorityQueue<Point> pq = new PriorityQueue(new Comparator<Point>() {
                public int compare(Point p1, Point p2) {
                    if (p1.x != p2.x) {
                        return p1.x - p2.x;
                    } else if (p1.y != p2.y) {
                        return p2.y - p1.y;
                    } else {
                        return p1.val - p2.val;
                    }
                }
            });

            dfs(root, 0, 0, pq);

            int prev_x = Integer.MIN_VALUE;

            while (!pq.isEmpty()) {
                Point p = pq.poll();
                if (p.x > prev_x) {
                    List<Integer> list = new ArrayList<>();
                    list.add(p.val);
                    result.add(list);
                } else {
                    List<Integer> list = result.get(result.size() - 1);
                    list.add(p.val);
                }
                prev_x = p.x;
            }
            return result;
        }

        public void dfs(TreeNode root, int x, int y, PriorityQueue<Point> pq) {
            if (root == null) {
                return;
            }

            pq.offer(new Point(x, y, root.val));

            dfs(root.left, x - 1, y - 1, pq);
            dfs(root.right, x + 1, y - 1, pq);
        }

        class Point {
            int x;
            int y;
            int val;

            public Point(int x, int y, int val) {
                this.x = x;
                this.y = y;
                this.val = val;
            }
        }

        // Question 6
        int count6 = 0;
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                count6 ++;
            }

        countNodes(root.left);
        countNodes(root.right);

        return count6;
    }

        // Question 7
        int rootToLeaf = 0;

        public int sumNumbers(TreeNode root) {
            preorder(root, 0);
            return rootToLeaf;
        }

        public void preorder(TreeNode root, int currNumber) {
            // exit
            if (root == null) {
                return;
            }

            currNumber = currNumber * 10 + root.val;

            // determine
            if (root.left == null && root.right == null) {
                rootToLeaf += currNumber;
            }

            // reverse
            preorder(root.left, currNumber);
            preorder(root.right, currNumber);
        }


        // Question 8
        public TreeNode removeLeafNodes(TreeNode root, int target) {
            if (root == null) {
                return null;
            }

            TreeNode left = removeLeafNodes(root.left, target);
            TreeNode right = removeLeafNodes(root.right, target);

            if (left == null && right == null && root.val == target) {
                return null;
            }

            return new TreeNode(root.val, left, right);
        }

}