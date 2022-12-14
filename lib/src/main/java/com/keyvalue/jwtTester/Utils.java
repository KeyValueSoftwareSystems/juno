package com.keyvalue.jwtTester;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class Utils {
    public static List<String> generateCasePermutations(String input) {
        List<String> casePermutations = new ArrayList<>();

        generateCasePermutations(input, "", casePermutations);

        return casePermutations;
    }

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

    public static String generateTarget(String protocol, String host, int port) {
        return String.format(
            "%s://%s%s",
            protocol,
            host,
            (Objects.equals(protocol, Constants.HTTP_STRING) && port == 80) ||
            (Objects.equals(protocol, Constants.HTTPS_STRING) && port == 443) ?
            "" : ":" + port
        );
    }

    public static HashMap<String, String> generateTargetMap(String target) {
        HashMap<String, String> targetMap = new HashMap<>();
        String[] lSplit = target.split("://");
        String[] rSplit = lSplit[1].split(":");
        String protocol = lSplit[0];
        String host = rSplit[0];
        String port =  rSplit.length == 2
                            ? rSplit[1]
                            : protocol.equals(Constants.HTTPS_STRING) ? "443" : "80";

        targetMap.put(Constants.PROTOCOL_STRING, protocol);
        targetMap.put(Constants.HOST_STRING, host);
        targetMap.put(Constants.PORT_STRING, port);
        
        return targetMap;
    }

    public static Map<String, String> generateMutatedJwtTokens(String jwt) {
        String[] jwtSplit = jwt.split("\\.");
        String header = new String(Base64.getDecoder().decode(jwtSplit[0]));
        JSONObject headerJson = new JSONObject(header);

        return Constants.PAYLOAD_LIST.stream().map(payload -> {
            headerJson.put(Constants.ALGORITHM_TYPE_STRING, payload);
            String mutatedHeader = Base64.getEncoder().encodeToString(headerJson.toString().getBytes());
            jwtSplit[0] = mutatedHeader.replaceAll("=", "");
            String mutatedJwt = String.join(".", jwtSplit);

            return new String[] { payload, mutatedJwt };
        }).collect(Collectors.toMap(result -> result[0], result -> result[1]));
    }
}
