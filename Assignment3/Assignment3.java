import java.util.*;

public class Assignment3 {
    public static void main(String[] args) {

    }

    public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }

        public Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }


// Question 1:
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

// Question 2:
HashMap<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        Node oldNode = head;
        Node newNode = new Node(oldNode.val);

        this.map.put(oldNode, newNode);

        while (oldNode != null) {
            newNode.random = this.getClonedNode(oldNode.random);
            newNode.next = this.getClonedNode(oldNode.next);
            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return map.get(head);
    }

    public Node getClonedNode(Node node) {
        if (node != null) {
            if (this.map.containsKey(node)) {
                return this.map.get(node);
            } else {
                map.put(node, new Node(node.val, null, null));
                return this.map.get(node);
            }
        }
        return null;
    }


// Question 3:
    class Solution {
        public static ListNode mergeKLists(ListNode[] lists) {
            Comparator<ListNode> cmp;
            cmp = new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    return o1.val-o2.val;
                }
            };

            Queue<ListNode> q = new PriorityQueue<ListNode>(cmp);
            for(ListNode l : lists){
                ListNode head = l;
                while(head!=null){
                    q.add(head);
                    ListNode next = head.next;
                    head.next = null;
                    head = next;
                }
            }
            ListNode head = new ListNode(0);
            ListNode point = head;
            while(!q.isEmpty()){
                point.next = q.poll();
                point = point.next;
            }
            return head.next;
        }
    }

// Question 4:
    public void reorderList(ListNode head) {
        if (head==null || head.next == null) {
            return;
        }

        Stack<ListNode> s = new Stack<>();
        int count = 0;
        ListNode curr = head;

        while (curr != null) {
            s.add(curr);
            curr = curr.next;
            count++;
        }

        curr = head;

        for (int i=0;i <= count / 2;i++) {
            ListNode temp = curr.next;
            curr.next = s.pop();
            curr = curr.next;
            curr.next = temp;
            curr = curr.next;
        }

        if (count % 2 != 0) {
            curr.next.next=null;
        } else {
            curr.next=null;
        }
    }

// Question 5:
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<>();

        ListNode p = head;

        while (p != null) {
            vals.add(p.val);
            p = p.next;
        }

        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }

        return true;
    }

// Question 6:
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = head;

        while (p != null) {
            length++;
            p = p.next;
        }

        n = length - n;
        p = dummy;

        while (n > 0) {
            p = p.next;
            n--;
        }

        p.next = p.next.next;

        return dummy.next;
    }

// Question 7:
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head.next;
        ListNode even = new ListNode(0, null);
        ListNode evenDummy = even;
        int i = 2;

        while (fast != null) {
            if (i % 2 == 1) {
                slow = slow.next;
                fast = fast.next;
            } else {
                slow.next = fast.next;
                fast.next = even.next;
                even.next = fast;
                even = fast;
                fast = slow.next;
            }
            i++;
        }
        slow.next = evenDummy.next;
        return head;
    }

// Question 8:
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newNode = new Node(insertVal);
            newNode.next = newNode;
            return newNode;
        }

        Node node = head;
        while (node.next != head) {
            if (node.next.val >= node.val) {
                if (insertVal >= node.val && insertVal <= node.next.val) {
                    break;
                }
            } else {
                if (insertVal >= node.val || insertVal <= node.next.val) {
                    break;
                }
            }
            node = node.next;
        }

        Node next = new Node(insertVal, node.next);
        node.next = next;

        return head;
    }

// Question 9:
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            //System.out.println(head.val);
            head = head.next;
        }

        int[] output = new int[list.size()];
        //System.out.println(output.length);

        for (int i = 0; i < list.size(); i++) {
            output[i] = findNextGreater(i, list);
            //System.out.println(output[i]);
        }

        return output;
    }

    public int findNextGreater(int i, List<Integer> list) {
        int original = list.get(i);

        while (i < list.size()) {
            if (list.get(i) > original) {
                return list.get(i);
            }
            i++;
        }

        return 0;
    }

// Question 10:
    public ListNode deleteDuplicates(ListNode head) {
        // slow track the the node before the duplicated nodes
        // fast find the last node of duplicates
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = head;

        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;
            }

            if (slow.next != fast) {
                slow.next = fast.next;
                fast = fast.next;
            } else {
                slow.next = fast;
                slow = slow.next;
                fast = fast.next;
            }
        }

        return dummy.next;
    }
}

