import java.util.*;

/**
 * Problem 3: Dijkstra's Shortest Path Algorithm
 * 
 * Finds shortest paths from a source vertex to all other vertices.
 * Time Complexity: O((V + E) log V)
 * Space Complexity: O(V)
 */
public class Problem3_Dijkstra {
    
    /**
     * Edge class representing a weighted edge
     */
    static class Edge {
        int to;
        int weight;
        
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    /**
     * Node class for PriorityQueue
     */
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;
        
        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    
    /**
     * Weighted Graph class using adjacency list
     */
    static class WeightedGraph {
        private int vertices;
        private List<List<Edge>> adjList;
        private static final int INF = Integer.MAX_VALUE;
        
        public WeightedGraph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }
        
        /**
         * Add a directed weighted edge
         */
        public void addEdge(int from, int to, int weight) {
            adjList.get(from).add(new Edge(to, weight));
        }
        
        /**
         * Add an undirected weighted edge
         */
        public void addUndirectedEdge(int u, int v, int weight) {
            addEdge(u, v, weight);
            addEdge(v, u, weight);
        }
        
        /**
         * Dijkstra's algorithm to find shortest distances from source
         * @param source Source vertex
         * @return Array of shortest distances from source to all vertices
         */
        public int[] dijkstra(int source) {
            int[] dist = new int[vertices];
            Arrays.fill(dist, INF);
            dist[source] = 0;
            
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(source, 0));
            
            while (!pq.isEmpty()) {
                Node current = pq.poll();
                int u = current.vertex;
                
                // Skip if we've already found a better path
                if (current.distance > dist[u]) {
                    continue;
                }
                
                // Explore neighbors
                for (Edge edge : adjList.get(u)) {
                    int v = edge.to;
                    int newDist = dist[u] + edge.weight;
                    
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.offer(new Node(v, newDist));
                    }
                }
            }
            
            return dist;
        }
        
        /**
         * Get the shortest path from source to destination
         * @param source Source vertex
         * @param destination Destination vertex
         * @return List of vertices in the shortest path, or empty list if unreachable
         */
        public List<Integer> getShortestPath(int source, int destination) {
            int[] dist = new int[vertices];
            int[] prev = new int[vertices];
            Arrays.fill(dist, INF);
            Arrays.fill(prev, -1);
            dist[source] = 0;
            
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(source, 0));
            
            while (!pq.isEmpty()) {
                Node current = pq.poll();
                int u = current.vertex;
                
                if (current.distance > dist[u]) {
                    continue;
                }
                
                for (Edge edge : adjList.get(u)) {
                    int v = edge.to;
                    int newDist = dist[u] + edge.weight;
                    
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        prev[v] = u;
                        pq.offer(new Node(v, newDist));
                    }
                }
            }
            
            // Reconstruct path
            List<Integer> path = new ArrayList<>();
            if (dist[destination] == INF) {
                return path; // Unreachable
            }
            
            int current = destination;
            while (current != -1) {
                path.add(current);
                current = prev[current];
            }
            
            Collections.reverse(path);
            return path;
        }
        
        /**
         * Print distances in readable format
         */
        public void printDistances(int[] dist) {
            System.out.print("Distances: [");
            for (int i = 0; i < dist.length; i++) {
                if (dist[i] == INF) {
                    System.out.print("∞");
                } else {
                    System.out.print(dist[i]);
                }
                if (i < dist.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
    
    /**
     * Test cases for Dijkstra's algorithm
     */
    public static void main(String[] args) {
        System.out.println("=== Problem 3: Dijkstra's Shortest Path ===\n");
        
        // Test Case 1: Simple graph from example
        System.out.println("Test Case 1: Simple connected graph");
        WeightedGraph graph1 = new WeightedGraph(5);
        graph1.addEdge(0, 1, 4);
        graph1.addEdge(0, 2, 1);
        graph1.addEdge(2, 1, 2);
        graph1.addEdge(1, 3, 1);
        graph1.addEdge(2, 3, 5);
        graph1.addEdge(3, 4, 3);
        
        int[] dist1 = graph1.dijkstra(0);
        graph1.printDistances(dist1);
        System.out.println("Expected: [0, 3, 1, 4, 7]");
        
        List<Integer> path1 = graph1.getShortestPath(0, 4);
        System.out.println("Path to 4: " + path1);
        System.out.println("Expected: [0, 2, 1, 3, 4]\n");
        
        // Test Case 2: Disconnected graph
        System.out.println("Test Case 2: Disconnected graph");
        WeightedGraph graph2 = new WeightedGraph(4);
        graph2.addEdge(0, 1, 5);
        graph2.addEdge(2, 3, 2);
        
        int[] dist2 = graph2.dijkstra(0);
        graph2.printDistances(dist2);
        System.out.println("Expected: [0, 5, ∞, ∞]\n");
        
        // Test Case 3: Single vertex
        System.out.println("Test Case 3: Single vertex");
        WeightedGraph graph3 = new WeightedGraph(1);
        int[] dist3 = graph3.dijkstra(0);
        graph3.printDistances(dist3);
        System.out.println("Expected: [0]\n");
        
        // Test Case 4: Linear graph (chain)
        System.out.println("Test Case 4: Linear graph (chain)");
        WeightedGraph graph4 = new WeightedGraph(5);
        graph4.addEdge(0, 1, 2);
        graph4.addEdge(1, 2, 3);
        graph4.addEdge(2, 3, 1);
        graph4.addEdge(3, 4, 4);
        
        int[] dist4 = graph4.dijkstra(0);
        graph4.printDistances(dist4);
        System.out.println("Expected: [0, 2, 5, 6, 10]");
        
        List<Integer> path4 = graph4.getShortestPath(0, 4);
        System.out.println("Path to 4: " + path4);
        System.out.println("Expected: [0, 1, 2, 3, 4]\n");
        
        // Test Case 5: Multiple paths - choose shortest
        System.out.println("Test Case 5: Multiple paths between vertices");
        WeightedGraph graph5 = new WeightedGraph(4);
        graph5.addEdge(0, 1, 10);
        graph5.addEdge(0, 2, 1);
        graph5.addEdge(2, 1, 2);
        graph5.addEdge(1, 3, 5);
        graph5.addEdge(2, 3, 10);
        
        int[] dist5 = graph5.dijkstra(0);
        graph5.printDistances(dist5);
        System.out.println("Expected: [0, 3, 1, 8]");
        
        List<Integer> path5 = graph5.getShortestPath(0, 3);
        System.out.println("Path to 3: " + path5);
        System.out.println("Expected: [0, 2, 1, 3]\n");
        
        // Test Case 6: Complete graph
        System.out.println("Test Case 6: Complete graph (triangle)");
        WeightedGraph graph6 = new WeightedGraph(3);
        graph6.addEdge(0, 1, 1);
        graph6.addEdge(1, 2, 1);
        graph6.addEdge(0, 2, 3); // Direct path is longer
        
        int[] dist6 = graph6.dijkstra(0);
        graph6.printDistances(dist6);
        System.out.println("Expected: [0, 1, 2]");
        
        List<Integer> path6 = graph6.getShortestPath(0, 2);
        System.out.println("Path to 2: " + path6);
        System.out.println("Expected: [0, 1, 2]\n");
        
        System.out.println("=== All Tests Completed ===");
    }
}
