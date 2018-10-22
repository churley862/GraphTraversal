package com.company;
import java.util.*;
import java.util.stream.Collectors;

public class ConnectedComponent {
    private List<Integer> components = new LinkedList<>();
    private List<String> cycles = new LinkedList<>();

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

    private static List<String> findCycles(Graph graph, List<Integer> components) {
        int firstNode = components.get(0);
        return findCycles(graph, firstNode, new LinkedList<Integer>(), new HashSet<Edge>());
    }

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

    public List<String> getCycles() {
        return cycles;
    }

    @Override
    public String toString() {
        String result = "{ ";
        for(Integer component : components) {
            result += String.valueOf(component) + " ";
        }
        result += "}";
        return result;
    }

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
