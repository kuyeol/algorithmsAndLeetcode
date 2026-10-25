import java.util.*;

/**
 * Graph Algorithms Study Quiz - Java Implementation
 * 
 * This file contains:
 * 1. Multiple Choice Questions (with answers in comments)
 * 2. Coding Problems with complete Java implementations
 * 3. Conceptual Questions (as comments)
 * 4. Practice Challenges (descriptions)
 */

// ==================== MULTIPLE CHOICE QUESTIONS ====================
/*
Q1. What is the time complexity of BFS on a graph with V vertices and E edges?
    A) O(V)
    B) O(E)
    C) O(V + E)  ✓ CORRECT
    D) O(V * E)

Q2. Which algorithm is BEST for finding shortest paths in a graph with negative edge weights?
    A) Dijkstra's Algorithm
    B) BFS
    C) Bellman-Ford Algorithm  ✓ CORRECT
    D) Prim's Algorithm

Q3. Topological sort can only be applied to:
    A) Any undirected graph
    B) Directed Acyclic Graphs (DAG)  ✓ CORRECT
    C) Graphs with cycles
    D) Complete graphs

Q4. What is the space complexity of DFS using recursion on a graph with V vertices?
    A) O(1)
    B) O(V)  ✓ CORRECT (call stack)
    C) O(E)
    D) O(V + E)

Q5. Which data structure is used in Dijkstra's algorithm for efficient minimum extraction?
    A) Stack
    B) Queue
    C) Priority Queue (Min-Heap)  ✓ CORRECT
    D) Linked List
*/

// ==================== GRAPH NODE CLASS ====================
class GraphNode {
    int value;
    List<GraphNode> neighbors;
    
    public GraphNode(int value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }
}

// ==================== WEIGHTED GRAPH NODE ====================
class WeightedGraphNode {
    int value;
    List<Edge> edges;
    
    public WeightedGraphNode(int value) {
        this.value = value;
        this.edges = new ArrayList<>();
    }
    
    static class Edge {
        WeightedGraphNode destination;
        int weight;
        
        public Edge(WeightedGraphNode dest, int weight) {
            this.destination = dest;
            this.weight = weight;
        }
    }
}

// ==================== CODING PROBLEM 1: BFS TRAVERSAL ====================
class BFSTraversal {
    /**
     * Perform BFS traversal starting from a given node
     * @param start The starting node
     * @return List of node values in BFS order
     */
    public static List<Integer> bfs(GraphNode start) {
        if (start == null) return new ArrayList<>();
        
        List<Integer> result = new ArrayList<>();
        Set<GraphNode> visited = new HashSet<>();
        Queue<GraphNode> queue = new LinkedList<>();
        
        queue.offer(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();
            result.add(current.value);
            
            for (GraphNode neighbor : current.neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        
        return result;
    }
    
    // Test
    public static void main(String[] args) {
        // Create graph: 0 -> 1, 2; 1 -> 3; 2 -> 3; 3 -> 4
        GraphNode[] nodes = new GraphNode[5];
        for (int i = 0; i < 5; i++) nodes[i] = new GraphNode(i);
        
        nodes[0].neighbors.add(nodes[1]);
        nodes[0].neighbors.add(nodes[2]);
        nodes[1].neighbors.add(nodes[3]);
        nodes[2].neighbors.add(nodes[3]);
        nodes[3].neighbors.add(nodes[4]);
        
        List<Integer> bfsResult = bfs(nodes[0]);
        System.out.println("BFS Traversal: " + bfsResult);
        // Expected: [0, 1, 2, 3, 4] (or similar valid BFS order)
    }
}

// ==================== CODING PROBLEM 2: CYCLE DETECTION ====================
class CycleDetection {
    /**
     * Detect cycle in a directed graph using DFS
     * @param nodes Array of all nodes in the graph
     * @return true if cycle exists, false otherwise
     */
    public static boolean hasCycle(GraphNode[] nodes) {
        Set<GraphNode> visited = new HashSet<>();
        Set<GraphNode> recStack = new HashSet<>();
        
        for (GraphNode node : nodes) {
            if (!visited.contains(node)) {
                if (hasCycleDFS(node, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean hasCycleDFS(GraphNode node, Set<GraphNode> visited, 
                                       Set<GraphNode> recStack) {
        visited.add(node);
        recStack.add(node);
        
        for (GraphNode neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                if (hasCycleDFS(neighbor, visited, recStack)) {
                    return true;
                }
            } else if (recStack.contains(neighbor)) {
                return true;
            }
        }
        
        recStack.remove(node);
        return false;
    }
    
    // Test
    public static void main(String[] args) {
        // Graph with cycle: 0 -> 1 -> 2 -> 0
        GraphNode[] nodesWithCycle = new GraphNode[3];
        for (int i = 0; i < 3; i++) nodesWithCycle[i] = new GraphNode(i);
        
        nodesWithCycle[0].neighbors.add(nodesWithCycle[1]);
        nodesWithCycle[1].neighbors.add(nodesWithCycle[2]);
        nodesWithCycle[2].neighbors.add(nodesWithCycle[0]); // Cycle
        
        System.out.println("Has cycle: " + hasCycle(nodesWithCycle)); // true
        
        // Graph without cycle: 0 -> 1 -> 2
        GraphNode[] nodesNoCycle = new GraphNode[3];
        for (int i = 0; i < 3; i++) nodesNoCycle[i] = new GraphNode(i);
        
        nodesNoCycle[0].neighbors.add(nodesNoCycle[1]);
        nodesNoCycle[1].neighbors.add(nodesNoCycle[2]);
        
        System.out.println("Has cycle: " + hasCycle(nodesNoCycle)); // false
    }
}

// ==================== CODING PROBLEM 3: DIJKSTRA'S ALGORITHM ====================
class DijkstraAlgorithm {
    /**
     * Find shortest paths from source to all other nodes
     * @param nodes Array of all nodes
     * @param sourceIndex Index of source node
     * @return Map of node index to shortest distance
     */
    public static Map<Integer, Integer> dijkstra(WeightedGraphNode[] nodes, int sourceIndex) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingInt(nd -> nd.distance));
        
        // Initialize distances
        for (int i = 0; i < nodes.length; i++) {
            distances.put(i, Integer.MAX_VALUE);
        }
        distances.put(sourceIndex, 0);
        pq.offer(new NodeDistance(sourceIndex, 0));
        
        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            int u = current.nodeIndex;
            int dist = current.distance;
            
            if (dist > distances.get(u)) continue;
            
            for (WeightedGraphNode.Edge edge : nodes[u].edges) {
                int v = edge.destination.value;
                int newDist = dist + edge.weight;
                
                if (newDist < distances.get(v)) {
                    distances.put(v, newDist);
                    pq.offer(new NodeDistance(v, newDist));
                }
            }
        }
        
        return distances;
    }
    
    static class NodeDistance {
        int nodeIndex;
        int distance;
        
        public NodeDistance(int nodeIndex, int distance) {
            this.nodeIndex = nodeIndex;
            this.distance = distance;
        }
    }
    
    // Test
    public static void main(String[] args) {
        // Create weighted graph: 0 -> 1 (4), 0 -> 2 (1), 2 -> 1 (2), 1 -> 3 (1), 2 -> 3 (5)
        WeightedGraphNode[] nodes = new WeightedGraphNode[4];
        for (int i = 0; i < 4; i++) nodes[i] = new WeightedGraphNode(i);
        
        nodes[0].edges.add(new WeightedGraphNode.Edge(nodes[1], 4));
        nodes[0].edges.add(new WeightedGraphNode.Edge(nodes[2], 1));
        nodes[2].edges.add(new WeightedGraphNode.Edge(nodes[1], 2));
        nodes[1].edges.add(new WeightedGraphNode.Edge(nodes[3], 1));
        nodes[2].edges.add(new WeightedGraphNode.Edge(nodes[3], 5));
        
        Map<Integer, Integer> distances = dijkstra(nodes, 0);
        System.out.println("Shortest distances from node 0: " + distances);
        // Expected: {0=0, 1=3, 2=1, 3=4}
    }
}

// ==================== CODING PROBLEM 4: TOPOLOGICAL SORT ====================
class TopologicalSort {
    /**
     * Perform topological sort on a DAG
     * @param nodes Array of all nodes
     * @return List of node values in topological order, or empty list if cycle exists
     */
    public static List<Integer> topologicalSort(GraphNode[] nodes) {
        List<Integer> result = new ArrayList<>();
        Set<GraphNode> visited = new HashSet<>();
        Set<GraphNode> recStack = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        
        for (GraphNode node : nodes) {
            if (!visited.contains(node)) {
                if (!topoDFS(node, visited, recStack, stack)) {
                    return new ArrayList<>(); // Cycle detected
                }
            }
        }
        
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        
        return result;
    }
    
    private static boolean topoDFS(GraphNode node, Set<GraphNode> visited, 
                                   Set<GraphNode> recStack, Stack<Integer> stack) {
        visited.add(node);
        recStack.add(node);
        
        for (GraphNode neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                if (!topoDFS(neighbor, visited, recStack, stack)) {
                    return false;
                }
            } else if (recStack.contains(neighbor)) {
                return false; // Cycle
            }
        }
        
        recStack.remove(node);
        stack.push(node.value);
        return true;
    }
    
    // Test
    public static void main(String[] args) {
        // DAG: 0 -> 1, 0 -> 2, 1 -> 3, 2 -> 3
        GraphNode[] nodes = new GraphNode[4];
        for (int i = 0; i < 4; i++) nodes[i] = new GraphNode(i);
        
        nodes[0].neighbors.add(nodes[1]);
        nodes[0].neighbors.add(nodes[2]);
        nodes[1].neighbors.add(nodes[3]);
        nodes[2].neighbors.add(nodes[3]);
        
        List<Integer> topoOrder = topologicalSort(nodes);
        System.out.println("Topological order: " + topoOrder);
        // Expected: [0, 1, 2, 3] or [0, 2, 1, 3]
    }
}

// ==================== CODING PROBLEM 5: CONNECTED COMPONENTS ====================
class ConnectedComponents {
    /**
     * Find number of connected components in an undirected graph
     * @param nodes Array of all nodes
     * @return Number of connected components
     */
    public static int countComponents(GraphNode[] nodes) {
        if (nodes.length == 0) return 0;
        
        Set<GraphNode> visited = new HashSet<>();
        int count = 0;
        
        for (GraphNode node : nodes) {
            if (!visited.contains(node)) {
                dfs(node, visited);
                count++;
            }
        }
        
        return count;
    }
    
    private static void dfs(GraphNode node, Set<GraphNode> visited) {
        visited.add(node);
        for (GraphNode neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }
    
    // Test
    public static void main(String[] args) {
        // Graph with 2 components: {0,1,2} and {3,4}
        GraphNode[] nodes = new GraphNode[5];
        for (int i = 0; i < 5; i++) nodes[i] = new GraphNode(i);
        
        nodes[0].neighbors.add(nodes[1]);
        nodes[1].neighbors.add(nodes[0]);
        nodes[1].neighbors.add(nodes[2]);
        nodes[2].neighbors.add(nodes[1]);
        
        nodes[3].neighbors.add(nodes[4]);
        nodes[4].neighbors.add(nodes[3]);
        
        int components = countComponents(nodes);
        System.out.println("Number of connected components: " + components); // Expected: 2
    }
}

// ==================== CONCEPTUAL QUESTIONS ====================
/*
Q1. Compare BFS and DFS:
    - BFS uses a queue, DFS uses a stack (or recursion)
    - BFS finds shortest path in unweighted graphs, DFS doesn't
    - BFS explores level by level, DFS goes deep first
    - Space: BFS O(w) where w is max width, DFS O(h) where h is max height

Q2. Difference between Kruskal's and Prim's MST algorithms:
    - Kruskal's: Sorts all edges, adds smallest edge that doesn't create cycle
    - Prim's: Starts from a node, grows tree by adding minimum weight edge from tree
    - Kruskal's better for sparse graphs, Prim's better for dense graphs
    - Kruskal's uses Union-Find, Prim's uses Priority Queue

Q3. Adjacency Matrix vs Adjacency List:
    - Matrix: O(1) edge lookup, O(V²) space, good for dense graphs
    - List: O(degree) edge lookup, O(V+E) space, good for sparse graphs
    - Matrix easier to implement, List more memory efficient for most cases
*/

// ==================== PRACTICE CHALLENGES ====================
/*
Challenge 1: Implement Union-Find (Disjoint Set Union) with path compression and union by rank

Challenge 2: Find shortest path in a maze using BFS (grid as graph)

Challenge 3: Implement Floyd-Warshall algorithm for all-pairs shortest paths

Challenge 4: Find strongly connected components using Kosaraju's or Tarjan's algorithm

Challenge 5: Solve Traveling Salesman Problem using dynamic programming with bitmask
*/

// ==================== RUN ALL TESTS ====================
public class GraphQuiz {
    public static void main(String[] args) {
        System.out.println("=== Running Graph Algorithm Tests ===\n");
        
        System.out.println("1. BFS Traversal:");
        BFSTraversal.main(args);
        System.out.println();
        
        System.out.println("2. Cycle Detection:");
        CycleDetection.main(args);
        System.out.println();
        
        System.out.println("3. Dijkstra's Algorithm:");
        DijkstraAlgorithm.main(args);
        System.out.println();
        
        System.out.println("4. Topological Sort:");
        TopologicalSort.main(args);
        System.out.println();
        
        System.out.println("5. Connected Components:");
        ConnectedComponents.main(args);
        System.out.println();
        
        System.out.println("=== All Tests Complete ===");
    }
}
