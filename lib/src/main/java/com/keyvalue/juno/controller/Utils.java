package com.keyvalue.juno.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.keyvalue.juno.model.Constants;

public class Utils {
    /**
     * Given a string as input, this method generates all the possible
     * upper and lowercase permutations of that string.
     * Example: input = "cat", output = ["cat", "caT", "cAt", ...]
     * @param input - Input string.
     * @return The list of permutations.
     */
    public static List<String> generateCasePermutations(String input) {
        List<String> casePermutations = new ArrayList<>();
        generateCasePermutations(input, "", casePermutations);
        return casePermutations;
    }

    /**
     * Internal helper method that makes use of recursion
     * to find and add all the permutations.
     * @param input - Input string.
     * @param output - Output string.
     * @param casePermutations - List to save the output.
     */
    private static void generateCasePermutations(
        String input,
        String output,
        List<String> casePermutations
    ) {
        if (input.length() == 0) {
            casePermutations.add(output);
            return;
        }

        String lowerCaseCharacter = String.format("%s", input.charAt(0)).toLowerCase();
        String upperCaseCharacter = String.format("%s", input.charAt(0)).toUpperCase();

        input = input.substring(1);

        generateCasePermutations(input, String.format("%s%s", output, lowerCaseCharacter), casePermutations);
        generateCasePermutations(input, String.format("%s%s", output, upperCaseCharacter), casePermutations);
    }

    /**
     * Given a JWT string, a word-list, and a generatorFunction,
     * this method uses them to generate the JWTs to be used as attacking payloads.
     * @param jwt - The original JWT.
     * @param wordList - The word-list to be used for modifying the JWT.
     * @param generatorFunction - The function that specifies the payload generation logic.
     * It should always return the generated payload.
     * @return - The payload map.
     */
    public static HashMap<String, String> generatePayloadMap(
        String jwt,
        List<String> wordList,
        BiFunction<String, String, String> generatorFunction
    ) throws IOException {
        String matchedJWT = grepJWT(jwt);
        HashMap<String, String> payloadMap = new HashMap<>();

        if (matchedJWT != null) {
            wordList.forEach(word -> payloadMap.put(word, generatorFunction.apply(jwt, word)));
            return payloadMap;
        }

        throw new IOException(Constants.INVALID_JWT_EXCEPTION_MESSAGE);
    }

    /**
     * Given a protocol, host and port, this method returns the curresponding web address.
     * @param protocol - Protocol string. Example: "https".
     * @param host - Host string. Example: "example.com".
     * @param port - Port number. Example: 443.
     * @return The web address. Example: "https://example.com", "http://example.com:8080".
     */
    public static String generateWebAddress(String protocol, String host, Integer port) {
        return String.format(
            "%s://%s%s",
            protocol,
            host,
            isDefaultHttpPort(protocol, port) ||
            isDefaultHttpsPort(protocol, port) ?
            "" : ":" + port
        );
    }

    /**
     * Checks whether a given string contains a valid JWT,
     * if so returns the match, null otherwise.
     * @param string - The string to check.
     * @return - The match.
     */
    public static String grepJWT(String string) {
        Pattern pattern = Pattern.compile(Constants.JWT_REGEX);
        Matcher match = pattern.matcher(string);

        if (match.find()) {
            return match.group(0);
        }

        return null;
    }

    /**
     * Checks whether the protocol is HTTP.
     * @param protocol - Protocol string.
     */
    public static Boolean isHttpProtocol(String protocol) {
        return protocol.equals(Constants.HTTP_STRING);
    }
    
    /**
     * Checks whether the protocol is HTTPS.
     * @param protocol - Protocol string.
     */
    public static Boolean isHttpsProtocol(String protocol) {
        return protocol.equals(Constants.HTTPS_STRING);
    }

    /**
     * Compares whether the protocol is HTTP and uses the default port number.
     * @param protocol - Protocol string
     * @param port - Port number
     */
    private static Boolean isDefaultHttpPort(String protocol, Integer port) {
        return isHttpProtocol(protocol) &&
               port == Constants.DEFAULT_HTTP_PORT;
    }

    /**
     * Compares whether the protocol is HTTPS and uses the default port number.
     * @param protocol - Protocol string
     * @param port - Port number
     */
    private static Boolean isDefaultHttpsPort(String protocol, Integer port) {
        return isHttpsProtocol(protocol) &&
               port == Constants.DEFAULT_HTTPS_PORT;
    }
}
