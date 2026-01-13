package br.com.study;

import java.util.Objects;

// immutable class
record ServerNode(String hostName) {
    public void processRequest(Request request) {
        Objects.requireNonNull(request, "Request cannot be null");
        System.out.println("Processing request [" + request.message() + "] on server [" + hostName + "]");
    }
}
