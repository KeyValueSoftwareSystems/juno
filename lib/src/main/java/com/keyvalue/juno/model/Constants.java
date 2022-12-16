package com.keyvalue.juno.model;

import java.util.List;

import com.keyvalue.juno.controller.Utils;

public class Constants {
    public static final String EXTENSION_NAME = "Juno";
    public static final String ADD_STRING = "Add §";
    public static final String CLEAR_STRING = "Clear §";
    public static final String AUTO_STRING = "Auto §";
    public static final String OPTIONS_STRING = "Options";
    public static final String PAYLOAD_STRING = "Payload";
    public static final String ATTACK_STRING = "Start attack";
    public static final String THREADS_STRING = "Number of threads";
    public static final String TARGET_STRING = "Target";
    public static final String TOKEN_STRING = "Token";
    public static final String RESULTS_STRING = "Results";
    public static final String REQUEST_STRING = "Request";
    public static final String RESPONSE_STRING = "Response";
    public static final String STATUS_STRING = "Status";
    public static final String LENGTH_STRING = "Length";
    public static final String DEFAULT_TARGET_STRING = "http://localhost:80";
    public static final String NONE_STRING = "NONE";
    public static final String ALGORITHM_TYPE_STRING = "alg";
    public static final String PROTOCOL_STRING = "protocol";
    public static final String HOST_STRING = "host";
    public static final String PORT_STRING = "port";
    public static final String HTTP_STRING = "http";
    public static final String HTTPS_STRING = "https";
    public static final String JWT_REGEX = "ey[A-Za-z\\d_-]{2,}(?:\\.[A-Za-z\\d_-]{2,}){2}";
    public static final String SEND_MESSAGE_STRING = String.format("Send to %s", EXTENSION_NAME);
    public static final String RESULTS_WINDOW_NAME = String.format("%s - Attack Results", EXTENSION_NAME);
    public static final String[] RESULT_TABLE_COLUMNS = new String [] { REQUEST_STRING, PAYLOAD_STRING, STATUS_STRING, LENGTH_STRING };
    public static final List<String> PAYLOAD_LIST = Utils.generateCasePermutations(NONE_STRING);
}
