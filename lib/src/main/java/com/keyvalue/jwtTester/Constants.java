package com.keyvalue.jwtTester;

public class Constants {
    public static final String EXTENTION_NAME = "JWT Tester";
    public static final String ADD_TEXT = "Add §";
    public static final String CLEAR_TEXT = "Clear §";
    public static final String AUTO_TEXT = "Auto §";
    public static final String OPTIONS_TEXT = "Options";
    public static final String PAYLOAD_TEXT = "Payload";
    public static final String ATTACK_TEXT = "Start attack";
    public static final String THREADS_TEXT = "Number of threads";
    public static final String HTTPS_TEXT = "Use HTTPS";
    public static final String TARGET_TEXT = "Target";
    public static final String TOKEN_TEXT = "Token";
    public static final String RESULTS_STRING = "Results";
    public static final String REQUEST_STRING = "Request";
    public static final String RESPONSE_STRING = "Response";
    public static final String DEFAULT_TARGET_TEXT = "http://localhost:80";
    public static final String JWT_REGEX = "ey[A-Za-z\\d_-]{2,}(?:\\.[A-Za-z\\d_-]{2,}){2}";
    public static final String SEND_MESSAGE_TEXT = String.format("Send to %s", EXTENTION_NAME);
    public static final String RESULTS_WINDOW_NAME = String.format("%s - Attack Results", EXTENTION_NAME);
    public static final Object[][] RESULT_TABLE_MODEL = new Object [][] {};
    public static final String[] RESULT_TABLE_COLUMNS = new String [] { REQUEST_STRING, "Payload", "Status", "Length" };
}
