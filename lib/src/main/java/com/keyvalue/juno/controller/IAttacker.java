package com.keyvalue.juno.controller;

import java.util.HashMap;
import java.util.function.BiConsumer;
import burp.IHttpRequestResponse;

public interface IAttacker {
    public void startAttack(BiConsumer<String, IHttpRequestResponse> callback);
    public HashMap<String, byte[]> getPayloadRequests(byte[] requestBytes, String jwt);
}
