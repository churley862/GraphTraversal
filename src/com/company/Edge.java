package com.company;

public class Edge {
    final int a;
    final int b;

    public Edge(int a, int b) {
        if (a > b) {
            int tmp = b;
            b = a;
            a = tmp;
        }
        this.a = a;
        this.b = b;
    }

    public int otherNode(int n) {
        if (a == n) return b;
        return a;
    }

    @Override
    public int hashCode() {
        return a * 1000 + b;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Edge.class) {
            return false;
        }
        Edge other = (Edge) obj;
        return (a == other.a) && (b == other.b);
    }

    @Override
    public String toString() {
        return String.valueOf(a) + "," + b;
    }
}
