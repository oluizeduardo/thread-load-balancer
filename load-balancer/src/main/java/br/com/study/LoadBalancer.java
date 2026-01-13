package br.com.study;

import java.util.Objects;
import java.util.Set;

class LoadBalancer {

    private final Set<ServerNode> serverNodes;
    private final LoadBalancingAlgorithm algorithm;

    public LoadBalancer(Set<ServerNode> serverNodes, LoadBalancingAlgorithm algorithm) {
        Objects.requireNonNull(serverNodes, "The list of servers cannot be null");
        Objects.requireNonNull(algorithm, "The load balancing algorithm cannot be null");

        if (serverNodes.size() > 10) {
            throw new IllegalArgumentException("Up to 10 servers are permitted");
        }

        this.serverNodes = Set.copyOf(serverNodes);
        this.algorithm = algorithm;
    }

    public void routRequest(Request request) {
        ServerNode serverNode = algorithm.selectServerNode(serverNodes);
        serverNode.processRequest(request);
    }
}
