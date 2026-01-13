package br.com.study;


import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<ServerNode> serverNodes = Set.of(
                new ServerNode("Server-A"),
                new ServerNode("Server-B"),
                new ServerNode("Server-C")
        );

        LoadBalancer loadBalancer = new LoadBalancer(serverNodes, new RoundRobingAlgorithm());

        for (int i = 0; i < 10; i++) {
            Request request = new Request("Request-" + (i + 1));
            loadBalancer.routRequest(request);
        }
    }
}