package com.example.soap;

import com.example.soap.pret.PretRequest;
import com.example.soap.pret.PretResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PretEndpoint {

    private static final String NAMESPACE_URI = "http://soap.example.com/pret";

    private final PretService pretService;

    @Autowired
    public PretEndpoint(PretService pretService) {
        this.pretService = pretService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "pretRequest")
    @ResponsePayload
    public PretResponse checkPretEligibility(@RequestPayload PretRequest request) {
        return pretService.checkEligibility(request);
    }
}