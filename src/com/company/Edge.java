/*
 * Collin Hurley
 * 10/22/2018
 * CS 203
 * */
package com.company;

public class Edge {
    final int node1;
    final int node2;

    public Edge(int node1, int node2) {
        if (node1 > node2) {
            int tmp = node2;
            node2 = node1;
            node1 = tmp;
        }
        this.node1 = node1;
        this.node2 = node2;
    }

    public int otherNode(int n) {
        if (node1 == n) return node2;
        return node1;
    }

    @Override
    public int hashCode() {
        return node1 * 1000 + node2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Edge.class) {
            return false;
        }
        Edge other = (Edge) obj;
        return (node1 == other.node1) && (node2 == other.node2);
    }

    @Override
    public String toString() {
        return String.valueOf(node1) + "," + node2;
    }
}
