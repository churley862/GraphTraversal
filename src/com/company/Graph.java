package com.company;
import javafx.util.Pair;

import javax.sound.midi.SysexMessage;
import java.util.*;
import java.util.regex.Pattern;

public class Graph implements Iterable<Edge> {
    private int nodes;
    private int count;
    private boolean[] matrix;

    public Graph(int nodes) {
        this.nodes = nodes;
        matrix = new boolean[summation(nodes)];
    }

    public Graph(String graph) {
        Pattern edgePattern = Pattern.compile(" *");
//        Pattern edgePattern = Pattern.compile(" *\( *(\d+) *, *(\d+) *\) *");
        Scanner sc = new Scanner(graph);

        if (sc.hasNextInt()) {
            nodes = sc.nextInt();
            matrix = new boolean[summation(nodes)];
        }

        int a, b;
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

    public boolean hasEdge(int a, int b) {
        if (a == b || a > nodes || b > nodes || a <= 0 || b <= 0) {
            return false;
        }
        return matrix[offset(a, b)];
    }

    public void addEdge(int a, int b) {
        if (a == b) return;

        final int offset = offset(a,b);
//        System.out.println("addEdge(" + a + "," + b + "): " + offset);
        count += matrix[offset] ? 0 : 1;
        matrix[offset] = true;
    }

    protected int offset(int a, int b) {
        if (a > b) {
            int tmp = b;
            b = a;
            a = tmp;
        }
        return rowOffset(a - 1) + (b - a - 1);
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
//        for (boolean b : matrix) {
//            result += b ? "1 " : "0 ";
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
