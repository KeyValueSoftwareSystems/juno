package com.keyvalue.juno.model;

import java.io.IOException;

import com.keyvalue.juno.controller.Utils;

import burp.IBurpExtenderCallbacks;
import burp.IHttpService;

import lombok.Getter;

@Getter
public class Target {
    private final IBurpExtenderCallbacks callbacks;
    private final byte[] request;
    private final String jwt;

    private String protocol;
    private String host;
    private Integer port;
    private IHttpService httpService;
    
    public Target(IBurpExtenderCallbacks callbacks, String address, byte[] request, String jwt) {
        this.callbacks = callbacks;
        this.request = request;
        this.jwt = jwt;

        try {
            buildTarget(address);
        } catch (IOException e) {
            callbacks.printError(e.getMessage());
        }
    }

    /**
     * Wrapper method to set the current instance's protocol, host, port and httpService.
     * @param address - The web address from which the properties to be set.
     */
    private void buildTarget(String address) throws IOException {
        String[] firstSplit = address.split("://");

        if (firstSplit.length == 2) {
            String[] nextSplit = firstSplit[1].split(":");
            String protocol = firstSplit[0];
            String host = nextSplit[0];
            int port;

            if (nextSplit.length == 2) {
                port = Integer.parseInt(nextSplit[1]);
            } else {
                port = Utils.isHttpsProtocol(protocol)
                        ? Constants.DEFAULT_HTTPS_PORT
                        : Constants.DEFAULT_HTTP_PORT;
            }

            this.host = host;
            this.protocol = protocol;
            this.port = port;

            this.httpService = callbacks
                                .getHelpers()
                                .buildHttpService(this.host, this.port, this.protocol);

            return;
        }

        throw new IOException(Constants.INVALID_WEB_ADDRESS_EXCEPTION_MESSAGE);
    }
}
