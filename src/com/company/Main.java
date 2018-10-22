package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String file = args[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−");

            int count = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                if (count > 1) {
                    System.out.println();
                }
                System.out.println("Graph" + count + ":");
                count += 1;
                Graph g = new Graph(line);
                List<ConnectedComponent> cc = ConnectedComponent.analyzeGraph(g);
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
