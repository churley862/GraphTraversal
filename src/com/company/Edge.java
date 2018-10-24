/*
 * Collin Hurley
 * 10/23/2018
 * CS 203
 * */
package com.company;

public class Edge {
    final int node1;
    final int node2;
    /**************************************************************/
    /* Method: Edge Constructor  */
    /* Purpose: constructor for the edge class takes in 2 nodes
    /* and creates an instance of Edge, to be used in operations

    /* Parameters: int node1 and int node2 */
    /* Returns: an instance of the edge class */
    /**************************************************************/
    public Edge(int node1, int node2) {
        if (node1 > node2) {
            int tmp = node2;
            node2 = node1;
            node1 = tmp;
        }
        this.node1 = node1;
        this.node2 = node2;
    }
    /**************************************************************/
    /* Method: otherNode */
    /* Purpose: returns the int value of the other node in a given
    /* edge*/
    /* Parameters: int n the number of the current node */
    /* Returns: the int value of the other node */
    /**************************************************************/
    public int otherNode(int n) {
        if (node1 == n) return node2;
        return node1;
    }
    /**************************************************************/
    /* Method: hashCode */
    /* Purpose: overrides the hashCode java method with a different
    /* hash for each edge, can be looked up later
    /* Parameters: none */
    /* Returns: the int value of the hash */
    /**************************************************************/
    @Override
    public int hashCode() {
        return node1 * 1000 + node2;
    }
    /**************************************************************/
    /* Method: equals */
    /* Purpose: overrides the equals class in base java
    /* comparison for edge class
    /* Parameters: Object to compare */
    /* Returns: the boolean if they are equal */
    /**************************************************************/
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Edge.class) {
            return false;
        }
        Edge other = (Edge) obj;
        return (node1 == other.node1) && (node2 == other.node2);
    }
    /**************************************************************/
    /* Method: toString */
    /* Purpose: overrides the to string method in java to format
    /* (node1, node2)
    /* Parameters: none */
    /* Returns: the string node1,node2 */
    /**************************************************************/
    @Override
    public String toString() {
        return String.valueOf(node1) + "," + node2;
    }
}
