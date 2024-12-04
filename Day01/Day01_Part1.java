// Advent of Code 2024, Day 01, Part 1
// Patrick Moran

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Day01_Part1 {
    public static void main(String[] args) {
        // Instantiate two ArrayLists to hold to left and right sides of the arrays
        ArrayList<Integer> arrLeft  = new ArrayList<>();
        ArrayList<Integer> arrRight = new ArrayList<>();

        // Open "input.txt" and fill the left/right ArrayLists
        File file = new File("Day01/input.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                // Get the String on current line, split into left/right
                String[] temp = scanner.nextLine().split("   ");
                arrLeft.add(Integer.valueOf(temp[0]));
                arrRight.add(Integer.valueOf(temp[1]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Sort the two ArrayLists
        Collections.sort(arrLeft);
        Collections.sort(arrRight);

        // Get the absolute difference between the left and right ArrayLists
        int sum = 0;
        for (int i = 0; i < arrLeft.size(); i++) {
            sum += abs(arrLeft.get(i) - arrRight.get(i));
        }

        // Fin!
        System.out.println("AoC Day 01, Part 1 answer: " + sum);
    }
}