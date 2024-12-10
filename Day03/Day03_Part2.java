// Advent of Code 2024, Day 03, Part 2
// Patrick Moran

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03_Part2 {
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

        // Compile regular expressions and search the string for matches
        Pattern pattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)|do\\(\\)|don't\\(\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        // For each mul() function found, if the do() function is currently enabled,
        //     calculate the product of the two numbers and sum the results
        int sum = 0;
        boolean instructionsEnabled = true;
        while (matcher.find()) {
            String match = matcher.group();

            if(match.matches("do\\(\\)")){
                instructionsEnabled = true;
            }
            else if(match.matches("don't\\(\\)")){
                instructionsEnabled = false;
            }
            else if (instructionsEnabled && match.matches("mul\\([0-9]+,[0-9]+\\)")){
                match = match.substring(4, match.length()-1); // grabs substring between "mul(" and ")"
                String[] mulParams = match.split(",");
                sum += Integer.parseInt(mulParams[0]) * Integer.parseInt(mulParams[1]);
            }
        }

        // Fin!
        System.out.println("AoC Day 03, Part 2 answer: " + sum);
    }

}