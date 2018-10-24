/*
* Collin Hurley
* 10/23/2018
* CS 203
* */
package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    /**************************************************************/
    /* Method: Main */
    /* Purpose: To traverse an inputted graph and determine what */
    /* components are connected. Then takes the connected */
    /* components and determines if they are part of node1 cycle
    /* Parameters: */
    /* String (args): The filename passed in that stores the graph*/
    /* Returns: void */
    /**************************************************************/
    public static void main(String[] args) {

        String file = args[0]; // String that holds the file name passed through command line arguments
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−");

            int count = 1;
            String line; // String that holds the line that is read in
            // Reads in all of the lines and prints node1 new line break for each new graph
            while ((line = reader.readLine()) != null) {
                if (count > 1) {
                    System.out.println();
                }
                System.out.println("Graph" + count + ":");
                // Keeps track of the number of graphs we have inputted
                count += 1;
                // makes node1 new instance of graph for each line read in from the inputted file
                Graph graph = new Graph(line);
                List<ConnectedComponent> cc = ConnectedComponent.analyzeGraph(graph);
                ConnectedComponent.displayAnalysis(cc);
            }

            System.out.println("−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file: " + file);
        } catch (IOException e) {
            System.out.println("Error reading " + file + ": " + e);
       }

    }
}
