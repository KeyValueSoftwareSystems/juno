package com.keyvalue.juno.controller;

import java.util.HashMap;
import java.util.function.BiConsumer;

import burp.IHttpRequestResponse;

public interface IAttacker {
    void startAttack(BiConsumer<String, IHttpRequestResponse> callback);

    HashMap<String, byte[]> buildAttackRequests(byte[] requestBytes, String jwt);
}
