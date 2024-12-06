// Advent of Code 2024, Day 02, Part 1
// Patrick Moran

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Day02_Part1 {
    public static void main(String[] args) {
        // Instantiate an ArrayList to hold the data reports
        ArrayList<ArrayList<Integer>> dataReports  = new ArrayList<>();

        // Open "input.txt" and fill the dataReports ArrayLists
        File file = new File("Day02/input.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                // Get the current line as a String, split into individual Strings
                String[] temp = scanner.nextLine().split(" ");

                // Fill dataset ArrayList with the individual Strings convered to Integers
                ArrayList<Integer> dataset = new ArrayList<>();
                for (int i = 0; i < temp.length; i++) {
                    dataset.add(Integer.valueOf(temp[i]));
                }

                // Add the completed dataset ArrayList to the dataReports ArrayList
                dataReports.add(dataset);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Review each dataReport dataset and count the 'safe' reports
        int safeReports = 0;
        for (int i = 0; i < dataReports.size(); i++) {
            boolean ascending = true;
            boolean descending = true;
            boolean difference = true;
            for (int j = 0; j < dataReports.get(i).size()-1 && (ascending || descending ) && difference; j++) {
                // Get the current and next numbers from the dataset
                int current = dataReports.get(i).get(j);
                int next = dataReports.get(i).get(j+1);

                // For each current/next pair, compare against the three conditions that make the data 'safe'
                ascending = ascending && (current <= next);
                descending = descending && (current >= next);
                difference = difference && (abs(current - next) >= 1 && abs(current - next) <= 3);
            }

            // Check to see if the report is safe, increment the counter if so
            if ((ascending || descending) && difference){
                safeReports++;
            }
        }

        // Fin!
        System.out.println("AoC Day 02, Part 1 answer: " + safeReports);
    }
}