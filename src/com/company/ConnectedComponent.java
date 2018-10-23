/**************************************************************/
/* Collin Hurley */
/* Login ID: hurl4145 */
/* CS-203, Fall 2018 */
/* Programming Assignment 1 */
/* Connected component class:
 * takes in node1 graph component and outputs  */
/**************************************************************/
package com.company;
import java.util.*;

public class ConnectedComponent {
    // all of the components in the inputed line
    private List<Integer> components = new LinkedList<>();
    // A list of all of the cycles
    private List<String> cycles = new LinkedList<>();

    /**************************************************************/
    /* Method: analyzeGraph */
    /* Purpose: Takes in a graph object and outputs a list of */
    /* connected component objects
    /* Parameters:  */
    /* graph: a graph object to be analyzed */
    /* Returns: a list of connected component objects  */
    /**************************************************************/
    public static List<ConnectedComponent> analyzeGraph(Graph graph) {
        List<ConnectedComponent> result = new LinkedList<>();
        Set<Integer> everVisited = new LinkedHashSet<>();

        for (int node = 1; node <= graph.getNodes(); ++node) {
            List<Integer> components = findConnected(graph, node, everVisited);
            if (!components.isEmpty()) {
                ConnectedComponent component = new ConnectedComponent();
                component.components = components;

                // calculate cycles
                component.cycles = findCycles(graph, components);
                
                result.add(component);
            }
        }

        return result;
    }
    /**************************************************************/
    /* Method: displayAnalysis */
    /* Purpose:  displays the analysis of the connected components*/
    /* Parameters:  */
    /* List<ConnectedComponent>: A list of connected components
    /* with results to print */
    /* Returns: void*/
    /**************************************************************/
    public static void displayAnalysis(List<ConnectedComponent> results) {
        String cycles = "";
        int cycleCount = 0;

        System.out.print(NumberNames.capitalizedNumberToWords(results.size()));
        System.out.print(" connected components: ");
        for (ConnectedComponent component : results) {
            System.out.print(component);
            System.out.print(" ");
            for (String cycle : component.getCycles()) {
                if (cycles.length() > 0)
                    cycles += ", ";
                cycles += cycle;
                cycleCount += 1;
            }
        }
        System.out.println();
        if (cycleCount > 0) {
            System.out.print("Cycle");
            if (cycleCount > 1)
                System.out.print("s");
            System.out.println(" detected: " + cycles);
        } else {
            System.out.println("The graph is acyclic.");
        }
    }

    /**************************************************************/
    /* Method: findCycles */
    /* Purpose:  takes in a graph and a list of integer components
    /* recursive function that returns a list of integer nodes */
    /* in the order they were traversed through
    /* Parameters:  */
    /* List<Integer>: A list of Integer nodes
    /* Graph: graph object that has a list of nodes and edges  */
    /* Returns: List <String> of cycles that show the order
    /* of traversal of complete cycles */
    /**************************************************************/
    private static List<String> findCycles(Graph graph, List<Integer> components) {
        int firstNode = components.get(0);
        return findCycles(graph, firstNode, new LinkedList<Integer>(), new HashSet<Edge>());
    }
    /**************************************************************/
    /* Method: findCycles */
    /* Purpose:  takes in a graph and a list of integer components
    /* recursive function that returns a list of integer nodes */
    /* in the order they were traversed through
    /* Parameters:  */
    /* List<Integer>: A list of Integer nodes
    /* Graph: graph object that has a list of nodes and edges  */
    /* Returns: List <String> of cycles that show the order
    /* of traversal of complete cycles */
    /**************************************************************/
    private static List<String> findCycles(Graph graph, int node, LinkedList<Integer> path, Set<Edge> visited) {
        List<String> result = new LinkedList<>();
        int index = path.indexOf(node);
        if (index != -1) {
            String cycle = "";
            for (Integer i : path.subList(index, path.size())) {
                cycle += i.toString() + "-";
            }
            cycle += node;
            result.add(cycle);
            return result;
        }

        path.add(node);
        for (Edge edge : graph.edgesAt(node)) {
            if (!visited.contains(edge)) {
                visited.add(edge);
                result.addAll(findCycles(graph, edge.otherNode(node), path, visited));
            }
        }
        path.remove(path.size() - 1);

        return result;
    }
    /**************************************************************/
    /* Method: getCycles */
    /* Purpose: getter method to get the cycles in the connected
    /* component class*/
    /* Parameters: none */
    /* Returns: List<String>: A list of nodes that complete
    /*cycles in order*/
    /**************************************************************/
    public List<String> getCycles() {
        return cycles;
    }

    /**************************************************************/
    /* Method: toString */
    /* Purpose: to convert the Integer nodes to string
    /* Parameters: none */
    /* Returns: String: converts integer number */
    /**************************************************************/
    @Override
    public String toString() {
        String result = "{ ";
        for(Integer component : components) {
            result += String.valueOf(component) + " ";
        }
        result += "}";
        return result;
    }

    /*******************************************************************/
    /* Method: findConnected */
    /* Purpose: Finds the nodes connected to a current node in a graph
    /* Parameters: Graph, int, Set<Integer> */
    /* Graph: the graph to search for the integer inputted
    /* int: node - the node to look for
    /* Set<Integer>: everVisited - a list of integers that have ever
    /* been visited
    /* Returns: List<Integers>: A list of integers that are connected */
    /*******************************************************************/
    private static List<Integer> findConnected(Graph graph, int node, Set<Integer> everVisited) {
        if (everVisited.contains(node)) return new LinkedList<>();
        everVisited.add(node);

        List<Integer> result = new LinkedList<>();
        result.add(node);

        for (Edge edge : graph.edgesAt(node)) {
            result.addAll(findConnected(graph, edge.otherNode(node), everVisited));
        }

        return result;
    }

}
