import java.util.*;

/**
 * Problem 2: Cycle Detection in Graph
 * 
 * Detects cycles in both directed and undirected graphs using DFS.
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class Problem2_CycleDetection {
    
    /**
     * Graph class supporting both directed and undirected graphs
     */
    static class Graph {
        private int vertices;
        private List<List<Integer>> adjList;
        private boolean isDirected;
        
        public Graph(int vertices, boolean isDirected) {
            this.vertices = vertices;
            this.isDirected = isDirected;
            adjList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }
        
        /**
         * Add an edge between two vertices
         */
        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            if (!isDirected) {
                adjList.get(v).add(u);
            }
        }
        
        /**
         * Detect cycle in undirected graph using DFS
         * @return true if cycle exists, false otherwise
         */
        public boolean hasCycle() {
            if (isDirected) {
                throw new IllegalStateException("Use hasCycleDirected() for directed graphs");
            }
            
            boolean[] visited = new boolean[vertices];
            
            // Check all components
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    if (hasCycleUtil(i, visited, -1)) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        /**
         * Helper method for undirected cycle detection
         * @param current Current vertex
         * @param visited Visited array
         * @param parent Parent vertex to avoid going back
         * @return true if cycle found
         */
        private boolean hasCycleUtil(int current, boolean[] visited, int parent) {
            visited[current] = true;
            
            for (int neighbor : adjList.get(current)) {
                if (!visited[neighbor]) {
                    if (hasCycleUtil(neighbor, visited, current)) {
                        return true;
                    }
                } else if (neighbor != parent) {
                    // Found a visited vertex that is not the parent - cycle detected
                    return true;
                }
            }
            return false;
        }
        
        /**
         * Detect cycle in directed graph using DFS with recursion stack
         * @return true if cycle exists, false otherwise
         */
        public boolean hasCycleDirected() {
            if (!isDirected) {
                throw new IllegalStateException("Use hasCycle() for undirected graphs");
            }
            
            boolean[] visited = new boolean[vertices];
            boolean[] recStack = new boolean[vertices]; // Recursion stack
            
            // Check all components
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    if (hasCycleDirectedUtil(i, visited, recStack)) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        /**
         * Helper method for directed cycle detection
         * @param current Current vertex
         * @param visited Visited array
         * @param recStack Recursion stack to track current path
         * @return true if cycle found
         */
        private boolean hasCycleDirectedUtil(int current, boolean[] visited, boolean[] recStack) {
            visited[current] = true;
            recStack[current] = true;
            
            for (int neighbor : adjList.get(current)) {
                if (!visited[neighbor]) {
                    if (hasCycleDirectedUtil(neighbor, visited, recStack)) {
                        return true;
                    }
                } else if (recStack[neighbor]) {
                    // Found a vertex in current recursion stack - cycle detected
                    return true;
                }
            }
            
            // Remove from recursion stack
            recStack[current] = false;
            return false;
        }
    }
    
    /**
     * Test cases for cycle detection
     */
    public static void main(String[] args) {
        System.out.println("=== Problem 2: Cycle Detection ===\n");
        
        // Test Case 1: Undirected graph with cycle
        System.out.println("Test Case 1: Undirected graph with cycle");
        Graph undirectedWithCycle = new Graph(4, false);
        undirectedWithCycle.addEdge(0, 1);
        undirectedWithCycle.addEdge(1, 2);
        undirectedWithCycle.addEdge(2, 3);
        undirectedWithCycle.addEdge(3, 0);
        System.out.println("Has cycle: " + undirectedWithCycle.hasCycle());
        System.out.println("Expected: true\n");
        
        // Test Case 2: Undirected graph without cycle (tree)
        System.out.println("Test Case 2: Undirected graph without cycle (tree)");
        Graph undirectedNoCycle = new Graph(4, false);
        undirectedNoCycle.addEdge(0, 1);
        undirectedNoCycle.addEdge(1, 2);
        undirectedNoCycle.addEdge(2, 3);
        System.out.println("Has cycle: " + undirectedNoCycle.hasCycle());
        System.out.println("Expected: false\n");
        
        // Test Case 3: Directed graph with cycle
        System.out.println("Test Case 3: Directed graph with cycle");
        Graph directedWithCycle = new Graph(4, true);
        directedWithCycle.addEdge(0, 1);
        directedWithCycle.addEdge(1, 2);
        directedWithCycle.addEdge(2, 3);
        directedWithCycle.addEdge(3, 1); // Back edge creating cycle
        System.out.println("Has cycle: " + directedWithCycle.hasCycleDirected());
        System.out.println("Expected: true\n");
        
        // Test Case 4: Directed acyclic graph (DAG)
        System.out.println("Test Case 4: Directed acyclic graph (DAG)");
        Graph dag = new Graph(4, true);
        dag.addEdge(0, 1);
        dag.addEdge(0, 2);
        dag.addEdge(1, 3);
        dag.addEdge(2, 3);
        System.out.println("Has cycle: " + dag.hasCycleDirected());
        System.out.println("Expected: false\n");
        
        // Test Case 5: Single vertex
        System.out.println("Test Case 5: Single vertex with no edges");
        Graph singleVertex = new Graph(1, false);
        System.out.println("Has cycle: " + singleVertex.hasCycle());
        System.out.println("Expected: false\n");
        
        // Test Case 6: Disconnected graph with cycle in one component
        System.out.println("Test Case 6: Disconnected graph with cycle in one component");
        Graph disconnected = new Graph(6, false);
        disconnected.addEdge(0, 1);
        disconnected.addEdge(1, 2);
        disconnected.addEdge(2, 0); // Cycle in first component
        disconnected.addEdge(3, 4);
        disconnected.addEdge(4, 5); // No cycle in second component
        System.out.println("Has cycle: " + disconnected.hasCycle());
        System.out.println("Expected: true\n");
        
        // Test Case 7: Directed graph with self-loop (if allowed)
        System.out.println("Test Case 7: Small directed cycle");
        Graph smallCycle = new Graph(3, true);
        smallCycle.addEdge(0, 1);
        smallCycle.addEdge(1, 2);
        smallCycle.addEdge(2, 0);
        System.out.println("Has cycle: " + smallCycle.hasCycleDirected());
        System.out.println("Expected: true\n");
        
        System.out.println("=== All Tests Completed ===");
    }
}
