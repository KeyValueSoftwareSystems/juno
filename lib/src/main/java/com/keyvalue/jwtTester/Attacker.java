package com.keyvalue.jwtTester;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

import burp.IBurpExtenderCallbacks;
import burp.IHttpRequestResponse;
import burp.IHttpService;

public class Attacker {
    private final IBurpExtenderCallbacks callbacks;
    private final int threads;
    private final IHttpService httpService;
    private final Map<String, byte[]> payloadRequests;

    public Attacker(IBurpExtenderCallbacks callbacks, String target, byte[] baseRequest, String baseJwtToken, int threads) {
        this.callbacks = callbacks;
        this.threads = threads;
        this.httpService = buildHttpService(target);
        this.payloadRequests = buildPayloadRequests(baseRequest, baseJwtToken);
    }

    /**
     * Performs the attack on the target.
     * @param callback - The callback to run on the response of each payload request.
     */
    public void startAttack(BiConsumer<String, IHttpRequestResponse> callback) {
        ExecutorService executor = threads > 0
                                        ? Executors.newScheduledThreadPool(threads)
                                        : Executors.newCachedThreadPool();

        payloadRequests.forEach((payload, request) ->
            executor.execute(() -> {
                IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(httpService, request);
                callback.accept(payload, requestResponse);
            })
        );

        executor.shutdown();
    }

    /**
     * Builds the IHttpService for performing the attack.
     * This is needed in order to make HTTP Requests using Burp API.
     * @param target - The target host.
     * @return IHttpService
     */
    private IHttpService buildHttpService(String target) {
        Map<String, String> targetMap = Utils.generateTargetMap(target);

        return callbacks.getHelpers().buildHttpService(
            targetMap.get(Constants.HOST_STRING),
            Integer.parseInt(targetMap.get(Constants.PORT_STRING)),
            targetMap.get(Constants.PROTOCOL_STRING)
        );
    }

    /**
     * Builds a map of payload requests for the given base HTTP request.
     * The key points to the payload used to tamper the JWT.
     * The value points to the request carrying the payload.
     * @param baseRequestBytes - The base HTTP request.
     * @param baseJwtToken - The actual JWT token obtained from the base request.
     * @return The map of payload requests.
     */
    private Map<String, byte[]> buildPayloadRequests(byte[] baseRequestBytes, String baseJwtToken) {
        String baseRequest = new String(baseRequestBytes);
        Map<String, String> mutatedJwtTokenMap = Utils.generateMutatedJwtTokens(baseJwtToken);
        Map<String, byte[]> payloadRequestMap = new HashMap<>();

        mutatedJwtTokenMap.forEach((payload, mutatedJwtToken) ->
            payloadRequestMap.put(payload, baseRequest.replaceAll(baseJwtToken, mutatedJwtToken).getBytes()));

        return payloadRequestMap;
    }
}
