import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignment7 {

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) {

    }

    //  Question 1
    public Node cloneGraph(Node node) {
        // enum the edge case
        if (node == null) {
            return node;
        }
        // using map to store the original node and cloneNode
        Map<Node, Node> map = new HashMap<>();
        return helper(node, map);
    }

    private Node helper(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList<Node>());
        map.put(node, cloneNode);

        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(helper(neighbor, map));
        }

        return cloneNode;
    }
    // tc : O(E + V)
    // sc : O(E)

    //  Question 2
        public int shortestPathLength(int[][] graph) {
            // enum the edge case
            if (graph.length == 1) {
                return 0;
            }

            // create the boolean map
            int n = graph.length;
            int endingMask = (1 << n) - 1;
            boolean[][] seen = new boolean[n][endingMask];
            ArrayList<int[]> queue = new ArrayList<>();

            // populate the queue and seen boolean
            for (int i = 0; i < n; i++) {
                queue.add(new int[] {i, 1 << i});
                seen[i][1 << i] = true;
            }

            int steps = 0;
            // find the shortest approach
            while (!queue.isEmpty()) {
                ArrayList<int[]> nextQueue = new ArrayList<>();

                for (int i = 0; i < queue.size(); i++) {
                    int[] currentPair = queue.get(i);
                    int node = currentPair[0];
                    int mask = currentPair[1];
                    for (int neighbor : graph[node]) {
                        // new operator
                        int nextMask = mask | (1 << neighbor);
                        if (nextMask == endingMask) {
                            return 1 + steps;
                        }
                        // determine
                        if (!seen[neighbor][nextMask]) {
                            seen[neighbor][nextMask] = true;
                            nextQueue.add(new int[] {neighbor, nextMask});
                        }
                    }
                }
                steps++;
                queue = nextQueue;
            }
            // edge case
            return -1;
        }


    //  Question 3
    public int ans;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        List<List<int[]>> adjList = new ArrayList();

        // populate the
        for(int value : values) {
            adjList.add(new ArrayList());
        }

        for(int edge[] : edges) {
            adjList.get(edge[0]).add(new int[] {edge[1], edge[2]});
            adjList.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }
        int[] visited = new int[values.length];
        solve(values, adjList, visited, 0, maxTime, 0, 0);
        return ans;
    }

    public void solve(int[] values, List<List<int[]>> adjList, int[] visited, int node, int maxTime, int currTime, int score) {
        if(currTime > maxTime) {
            return;
        }

        if(visited[node] == 0) {
            score += values[node];
        }

        if(node == 0) {
            ans = Math.max(ans, score);
        }

        // backtracking
        visited[node]++;
        for(int[] v : adjList.get(node)) {
            solve(values, adjList, visited, v[0], maxTime, currTime + v[1], score);
        }
        visited[node]--;
    }
}
