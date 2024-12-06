// Advent of Code 2024, Day 02, Part 1
// Patrick Moran

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Day02_Part2 {
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
                for (String s : temp) {
                    dataset.add(Integer.valueOf(s));
                }

                // Add the completed dataset ArrayList to the dataReports ArrayList
                dataReports.add(dataset);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Review each dataReport dataset and count the 'safe' reports
        int safeReports = 0;
        for (ArrayList<Integer> dataReport : dataReports) {
            // See if the dataset is safe without modification
            if (isSafe(dataReport)) {
                safeReports++;
            }
            // If the dataset is not safe, try using the "Problem Dampener"
            else {
                for (int j = 0; j < dataReport.size(); j++) {
                    ArrayList<Integer> copy = new ArrayList<>(dataReport);
                    copy.remove(j);

                    // Check if the copy with current index removed is safe, if so increment and break loop
                    if (isSafe(copy)) {
                        safeReports++;
                        break;
                    }
                }
            }
        }

        // Fin!
        System.out.println("AoC Day 02, Part 2 answer: " + safeReports);
    }

    /*
     * This method determines if the dataset provided is safe or not
     * @param dataset The dataset from the current dataReport
     * @return boolean representing isSafe
     */
    private static boolean isSafe(ArrayList<Integer> dataset){
        boolean ascending = true;
        boolean descending = true;
        boolean difference = true;

        for (int j = 0; j < dataset.size()-1 && (ascending || descending ) && difference; j++) {
            // Get the current and next numbers from the dataset
            int current = dataset.get(j);
            int next = dataset.get(j + 1);

            // For each current/next pair, compare against the three conditions that make the data 'safe'
            ascending = ascending && (current <= next);
            descending = descending && (current >= next);
            difference = abs(current - next) >= 1 && abs(current - next) <= 3;
        }

        return (ascending || descending) && difference;
    }
}