import java.util.*;

/**
 * Problem 1: BFS Graph Traversal
 * 
 * Implements Breadth-First Search to traverse a graph starting from a given
 * vertex.
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class Problem1_BFS {

    /**
     * Graph class using adjacency list representation
     */
    static class Graph {
        private int vertices;
        private List<List<Integer>> adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        /**
         * Add an undirected edge between two vertices
         */
        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        /**
         * Perform BFS traversal starting from a given vertex
         * 
         * @param startVertex The starting vertex for BFS
         * @return List of vertices in BFS order
         */
        public List<Integer> bfs(int startVertex) {
            List<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();

            // Start BFS from the given vertex
            queue.offer(startVertex);
            visited[startVertex] = true;

            while (!queue.isEmpty()) {
                int current = queue.poll();
                result.add(current);

                // Visit all adjacent vertices
                for (int neighbor : adjList.get(current)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }

            return result;
        }

        /**
         * Perform BFS on entire graph (handles disconnected components)
         * 
         * @return List of vertices in BFS order, visiting all components
         */
        public List<Integer> bfsAllComponents() {
            List<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();

            // Iterate through all vertices to handle disconnected components
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    queue.offer(i);
                    visited[i] = true;

                    while (!queue.isEmpty()) {
                        int current = queue.poll();
                        result.add(current);

                        for (int neighbor : adjList.get(current)) {
                            if (!visited[neighbor]) {
                                visited[neighbor] = true;
                                queue.offer(neighbor);
                            }
                        }
                    }
                }
            }

            return result;
        }
    }

    /**
     * Test cases for BFS implementation
     */
    public static void main(String[] args) {
        System.out.println("=== Problem 1: BFS Graph Traversal ===\n");

        // Test Case 1: Simple connected graph
        System.out.println("Test Case 1: Simple connected graph");
        Graph graph1 = new Graph(6);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(1, 3);
        graph1.addEdge(1, 4);
        graph1.addEdge(2, 5);

        List<Integer> result1 = graph1.bfs(0);
        System.out.println("BFS from vertex 0: " + result1);
        System.out.println("Expected: [0, 1, 2, 3, 4, 5] (or similar level-order)\n");

        // Test Case 2: Disconnected graph
        System.out.println("Test Case 2: Disconnected graph");
        Graph graph2 = new Graph(7);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(3, 4);
        graph2.addEdge(5, 6);
        // Components: {0,1,2}, {3,4}, {5,6}

        List<Integer> result2 = graph2.bfsAllComponents();
        System.out.println("BFS all components: " + result2);
        System.out.println("Should visit all 7 vertices\n");

        // Test Case 3: Single vertex
        System.out.println("Test Case 3: Single vertex graph");
        Graph graph3 = new Graph(1);
        List<Integer> result3 = graph3.bfs(0);
        System.out.println("BFS from vertex 0: " + result3);
        System.out.println("Expected: [0]\n");

        // Test Case 4: Complete graph (triangle)
        System.out.println("Test Case 4: Complete graph (triangle)");
        Graph graph4 = new Graph(3);
        graph4.addEdge(0, 1);
        graph4.addEdge(1, 2);
        graph4.addEdge(0, 2);

        List<Integer> result4 = graph4.bfs(0);
        System.out.println("BFS from vertex 0: " + result4);
        System.out.println("Expected: [0, 1, 2] (or [0, 2, 1])\n");

        // Test Case 5: Linear graph
        System.out.println("Test Case 5: Linear graph");
        Graph graph5 = new Graph(5);
        graph5.addEdge(0, 1);
        graph5.addEdge(1, 2);
        graph5.addEdge(2, 3);
        graph5.addEdge(3, 4);

        List<Integer> result5 = graph5.bfs(0);
        System.out.println("BFS from vertex 0: " + result5);
        System.out.println("Expected: [0, 1, 2, 3, 4]\n");

        System.out.println("=== All Tests Completed ===");
    }
}
