package com.keyvalue.juno.model;

import burp.IHttpRequestResponse;

public record LogEntry(int statusCode, int contentLength, String payload, IHttpRequestResponse requestResponse) {
}
