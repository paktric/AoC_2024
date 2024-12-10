// Advent of Code 2024, Day 03, Part 1
// Patrick Moran

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03_Part1 {
    public static void main(String[] args) {
        // Open "input.txt", concat multiple lines into one String
        File file = new File("Day03/input.txt");
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                sb.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String input = sb.toString();

        // Compile regular expression and search the string for matches
        Pattern pattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        // For each match found, calculate the product of the two numbers and sum results
        int sum = 0;
        while (matcher.find()) {
            String mulFunction = matcher.group();
            mulFunction = mulFunction.substring(4, mulFunction.length()-1); // grabs substring between "mul(" and ")"
            String[] mulParams = mulFunction.split(",");
            sum += Integer.parseInt(mulParams[0]) * Integer.parseInt(mulParams[1]);
        }

        // Fin!
        System.out.println("AoC Day 03, Part 1 answer: " + sum);
    }

}