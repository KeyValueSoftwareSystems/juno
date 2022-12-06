package com.keyvalue.jwtTester;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static List<String> casePermutations;

    public static List<String> generateCasePermutations(String input) {
        casePermutations = new ArrayList<>();

        generateCasePermutations(input, "");

        return casePermutations;
    }

    private static void generateCasePermutations(String input, String output) {
        if (input.length() == 0) {
            casePermutations.add(output);
            return;
        }

        String lowerCaseCharacter = String.format("%s", input.charAt(0)).toLowerCase();
        String upperCaseCharacter = String.format("%s", input.charAt(0)).toUpperCase();

        input = input.substring(1, input.length());

        generateCasePermutations(input, String.format("%s%s", output, lowerCaseCharacter));
        generateCasePermutations(input, String.format("%s%s", output, upperCaseCharacter));
    }
}
