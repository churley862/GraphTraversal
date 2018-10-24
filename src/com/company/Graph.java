/*
 * Collin Hurley
 * 10/23/2018
 * CS 203
 * */
package com.company;

import java.util.*;

public class Graph implements Iterable<Edge> {
    // nodes stores the number of nodes
    private int nodes;
    private int count;
    private boolean[] matrix;

    public Graph(int nodes) {
        this.nodes = nodes;
        matrix = new boolean[summation(nodes)];
    }
    /**************************************************************/
    /* Method: Graph */
    /* Purpose: graph constructor creating an instance of graph
    /* Parameters: the string to be made into a graph */
    /* Returns: the boolean if they are equal */
    /**************************************************************/
    public Graph(String graph) {
        Scanner sc = new Scanner(graph);

        if (sc.hasNextInt()) {
            nodes = sc.nextInt();
            matrix = new boolean[summation(nodes)];
        }

        while (sc.hasNext()) {
            String edge = sc.next();
            String[] nodes = edge.split("[ (),]+");
            if (nodes.length == 3) {
                addEdge(Integer.valueOf(nodes[1]), Integer.valueOf(nodes[2]));
            }
        }
    }
    /**************************************************************/
    /* Method: getNodes*/
    /* Purpose: node getter method
    /* Parameters: none */
    /* Returns: the int node from get */
    /**************************************************************/
    public int getNodes() {
        return nodes;
    }
    /**************************************************************/
    /* Method: hasEdge */
    /* Purpose: Determines if the edge exists
    /* comparison for edge class
    /* Parameters: node1 and node2 */
    /* Returns: the boolean if the edge exists at the two nodes */
    /**************************************************************/
    public boolean hasEdge(int node1, int node2) {
        if (node1 == node2 || node1 > nodes || node2 > nodes || node1 <= 0 || node2 <= 0) {
            return false;
        }
        return matrix[offset(node1, node2)];
    }
    /**************************************************************/
    /* Method: addEdge */
    /* Purpose: Adds an edge to the matrix
    /* Parameters: node1 and node2 */
    /* Returns: void */
    /**************************************************************/
    public void addEdge(int node1, int node2) {
        if (node1 == node2) return;

        final int offset = offset(node1,node2);
        count += matrix[offset] ? 0 : 1;
        matrix[offset] = true;
    }
    /**************************************************************/
    /* Method: offset */
    /* Purpose: returns the offset of the the point in the matrix
    /* returns the integer offset of the point in the matrix
    /* Parameters: node1 and node2 */
    /* Returns: the integer offset of the point in the matrix*/
    /**************************************************************/
    protected int offset(int node1, int node2) {
        if (node1 > node2) {
            int tmp = node2;
            node2 = node1;
            node1 = tmp;
        }
        return rowOffset(node1 - 1) + (node2 - node1 - 1);
    }

    /**************************************************************/
    /* Method: summation */
    /* Purpose: summation to help calculate row offset
    /* Parameters: int node */
    /* Returns: int total summation */
    /**************************************************************/
    protected static int summation(int n) {
        return n * (n + 1) / 2;
    }
    /**************************************************************/
    /* Method: rowOffset */
    /* Purpose: calculates the number of rows to offset
    /* Parameters: node
    /* Returns: nodes to offset */
    /**************************************************************/
    protected int rowOffset(int n) {
        return n * nodes - summation(n);
    }
    /**************************************************************/
    /* Method: toString */
    /* Purpose: Converts the graph to a string matrix
    /* Parameters: none */
    /* Returns: the String matrix */
    /**************************************************************/
    @Override
    public String toString() {
        String result = "";
        for (int row = 1; row <= nodes; ++row) {
            for (int col = 1; col <= nodes; ++col) {
                result += (hasEdge(row, col) ? "1 " : "0 ");
            }
            result += "\n";
        }

        return result;
    }
    /**************************************************************/
    /* Method: iterator
    /* Purpose:Overrides the edge iterator to incorporate offsets */
    /* comparison for edge class
    /* Parameters: none */
    /* Returns: An edge iterator  */
    /**************************************************************/
    @Override
    public Iterator<Edge> iterator() {
        return new Iterator<Edge>() {
            int found = 0;
            int pos = -1;
            int row = 1;
            int col = 1;
            /**************************************************************/
            /* Method: hasNext
            /* Purpose:Overrides the hasNext function in java to see if
            /* found is less than count */
            /* comparison for edge class
            /* Parameters: none */
            /* Returns: An edge iterator  */
            /**************************************************************/
            @Override
            public boolean hasNext() {
                return found < count;
            }
            /**************************************************************/
            /* Method: next
            /* Purpose:overrides the next method to return the next edge */
            /* Parameters: none */
            /* Returns: the next edge instance */
            /**************************************************************/
            @Override
            public Edge next() {
                // note 0, 0 is always false, so it is safe
                // to increment before our first check...
                while (pos < matrix.length) {
                    col += 1;
                    if (col > nodes) {
                        row += 1;
                        col = row + 1;
                    }
                    pos += 1;
                    if (matrix[pos]) {
                        found += 1;
                        return new Edge(row,col);
                    }
                }

                return null;
            }
        };
    }
    /**************************************************************/
    /* Method: edgesAt
    /* Purpose:returns the edges at a node */
    /* Parameters: none */
    /* Returns: returns an iterable of the edges at a node */
    /**************************************************************/
    public Iterable<Edge> edgesAt(int n) {
        Set<Edge> edges = new LinkedHashSet<>();

        for (int i = 1; i <= nodes; ++i) {
            if (hasEdge(n, i)) {
                edges.add(new Edge(n, i));
            }
        }
        return edges;
    }
}
