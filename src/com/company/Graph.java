/*
 * Collin Hurley
 * 10/22/2018
 * CS 203
 * */
package com.company;

import java.util.*;

public class Graph implements Iterable<Edge> {
    private int nodes;
    private int count;
    private boolean[] matrix;

    public Graph(int nodes) {
        this.nodes = nodes;
        matrix = new boolean[summation(nodes)];
    }

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

    public int getNodes() {
        return nodes;
    }

    public boolean hasEdge(int node1, int node2) {
        if (node1 == node2 || node1 > nodes || node2 > nodes || node1 <= 0 || node2 <= 0) {
            return false;
        }
        return matrix[offset(node1, node2)];
    }

    public void addEdge(int node1, int node2) {
        if (node1 == node2) return;

        final int offset = offset(node1,node2);
        count += matrix[offset] ? 0 : 1;
        matrix[offset] = true;
    }

    protected int offset(int node1, int node2) {
        if (node1 > node2) {
            int tmp = node2;
            node2 = node1;
            node1 = tmp;
        }
        return rowOffset(node1 - 1) + (node2 - node1 - 1);
    }

    protected static int summation(int n) {
        return n * (n + 1) / 2;
    }

    protected int rowOffset(int n) {
        return n * nodes - summation(n);
    }

    @Override
    public String toString() {
        String result = "";
        for (int row = 1; row <= nodes; ++row) {
            for (int col = 1; col <= nodes; ++col) {
                result += (hasEdge(row, col) ? "1 " : "0 ");
            }
            result += "\n";
        }
//        for (boolean node2 : matrix) {
//            result += node2 ? "1 " : "0 ";
//        }
        return result;
    }

    @Override
    public Iterator<Edge> iterator() {
        return new Iterator<Edge>() {
            int found = 0;
            int pos = -1;
            int row = 1;
            int col = 1;

            @Override
            public boolean hasNext() {
                return found < count;
            }

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
