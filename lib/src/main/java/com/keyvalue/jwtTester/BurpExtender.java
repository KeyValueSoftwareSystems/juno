package com.keyvalue.jwtTester;

import java.io.PrintWriter;

import burp.IBurpExtender;
import burp.IBurpExtenderCallbacks;

public class BurpExtender implements IBurpExtender
{
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        callbacks.setExtensionName("KeyValue JWT Tester");

        PrintWriter stdout = new PrintWriter(callbacks.getStdout(), true);
        
        stdout.println("Hello output");
    }
}
