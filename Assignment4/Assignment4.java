import java.util.*;

public class Assignment4 {
    public static void main(String[] args) {

    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Question 1:
    public ListNode mergeInBetween(ListNode l1, int a, int b, ListNode l2) {
        ListNode dummy = new ListNode(0, l1);

        while (a > 1) {
            l1 = l1.next;
            a--;
            b--;
        }

        ListNode joint = l1;

        while (b > -1) {
            l1 = l1.next;
            b--;
        }

        joint.next = l2;

        while (l2.next != null) {
            l2 = l2.next;
        }

        l2.next = l1;

        return dummy.next;
    }

    // Question 2:
    public ListNode partition(ListNode head, int x) {
        ListNode before_dummy = new ListNode(0);
        ListNode before = before_dummy;
        ListNode after_dummy = new ListNode(0);
        ListNode after = after_dummy;

        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }

        after.next = null;
        before.next = after_dummy.next;

        return before_dummy.next;
    }

    // Question 3:
    public ListNode reverseEvenLengthGroups(ListNode head) {
        int group = 1;
        ListNode node = head;

        while (node != null) {
            int count = 0;
            ListNode start = node;

            Stack<Integer> stack = new Stack<>();
            while (count != group && node != null) {
                stack.push(node.val);
                node = node.next;
                count++;
            }

            // if count is odd, then group++;
            // if count is even, then use the stack to revalue the group
            if (count % 2 == 0) {
                while (start != node) {
                    start.val = stack.pop();
                    start = start.next;
                }
            }
            group++;
        }
        return head;
    }

    // Question 4:
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        int i = 1, j = 0;
        int min = Integer.MAX_VALUE;

        while (node.next.next != null) {
            if ((node.val > node.next.val && node.next.next.val > node.next.val) ||
                    (node.val < node.next.val && node.next.next.val < node.next.val)) {
                list.add(i);
                if (list.size() >= 2) {
                    min = Math.min(min, list.get(j) - list.get(j - 1));
                }
                j++;
            }
            i++;
            node = node.next;
        }

        int[] output = new int[2];

        if (list.size() < 2) {
            output[0] = -1;
            output[1] = -1;
            return output;
        }

        output[0] = min;
        output[1] = list.get(list.size() - 1) - list.get(0);

        return output;
    }

    // Question 5:
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode left = head;
        ListNode right = partition(head);

        left = sortList(left);
        right = sortList(right);

        return merge(left, right);
    }

    public ListNode partition(ListNode head) {
        ListNode prev = head, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        return slow;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode();
        ListNode node = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                node.next = head1;
                head1 = head1.next;
                node = node.next;
                node.next = null;
            } else {
                node.next = head2;
                head2 = head2.next;
                node = node.next;
                node.next = null;
            }
        }

        if (head1 != null) {
            node.next = head1;
        } else if (head2 != null) {
            node.next = head2;
        }

        return dummy.next;
    }

    // Question 6:
    class Solution {

        ListNode head;

        public Solution(ListNode head) {
            this.head = head;
        }

        public int getRandom() {
            ListNode dummy = head;
            int scale = 1, selectedValue = 0;

            while (dummy != null) {
                if (Math.random() < 1.0 / scale) {
                    selectedValue = dummy.val;
                }
                dummy = dummy.next;
                scale++;
            }
            return selectedValue;
        }
    }


    // Question 7:
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy, fast = dummy.next;
        int i = 1;

        while (i < left) {
            slow = slow.next;
            fast = fast.next;
            i++;
        }

        ListNode jointLeft = slow;
        slow = slow.next;
        fast = fast.next;
        i++;

        while (i <= right) {
            ListNode next = fast.next;
            fast.next = slow;
            slow = fast;
            fast = next;

            i++;
        }

        jointLeft.next.next = fast;
        jointLeft.next = slow;

        return dummy.next;

    }

    // Question 8:
    public ListNode[] splitListToParts(ListNode head, int k) {

        ListNode[] output = new ListNode[k];
        ListNode node = head;
        int length = 0;

        while (node != null) {
            length++;
            node = node.next;
        }

        node = head;
        int size = length / k;
        int extra = length % k;

        for (int i = 0; i < k && node != null; i++) {
            output[i] = node;

            for (int j = 0; j < size + (extra > 0 ? 1 : 0) - 1; j++) {
                node = node.next;
            }

            ListNode temp = node.next;
            node.next = null;
            node = temp;

            extra--;
        }
        return output;
    }

    // Question 9:
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int count = 0;

        while (head != null ) {
            if (set.contains(head.val) && (head.next == null || !set.contains(head.next.val))) {
                count++;
            }
            head = head.next;
        }
        return count;
    }

    // Question 10:
    public int pairSum(ListNode head) {
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode next = null;
        fast = slow.next;

        while (fast != null) {
            slow.next = next;
            next = slow;
            slow = fast;
            fast = slow.next;
        }
        slow.next = next;
        prev.next = slow;

        int max = Integer.MIN_VALUE;
        fast = slow;
        slow = head;

        while (fast != null) {
            max = Math.max(max, slow.val + fast.val);
            slow = slow.next;
            fast = fast.next;
        }

        return max;
    }
}
