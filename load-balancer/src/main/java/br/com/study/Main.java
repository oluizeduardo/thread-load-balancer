package br.com.study;


import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

        List<ServerNode> serverNodes = List.of(
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

/**
 * This interface will define a method for selecting a server node from the list of available nodes.
 */
interface LoadBalancingAlgorithm {
    ServerNode selectServerNode(List<ServerNode> serverNods);
}

final class RoundRobingAlgorithm implements LoadBalancingAlgorithm {

    private final AtomicInteger currentIndex = new AtomicInteger(0);

    @Override
    public ServerNode selectServerNode(List<ServerNode> serverNodes) {
        Objects.requireNonNull(serverNodes, "The list of servers cannot be null");
        if (serverNodes.isEmpty()) {
            throw new IllegalArgumentException("No server nodes available");
        }
        int index = currentIndex.getAndIncrement() % serverNodes.size();
        return serverNodes.get(index);
    }
}

// immutable class
record Request(String message) {
}

// immutable class
record ServerNode(String hostName) {
    public void processRequest(Request request) {
        Objects.requireNonNull(request, "Request cannot be null");
        System.out.println("Processing request [" + request.message() + "] on server [" + hostName + "]");
    }
}

class LoadBalancer {

    private final List<ServerNode> serverNodes;
    private final LoadBalancingAlgorithm algorithm;

    public LoadBalancer(List<ServerNode> serverNodes, LoadBalancingAlgorithm algorithm) {
        Objects.requireNonNull(serverNodes, "The list of servers cannot be null");
        Objects.requireNonNull(algorithm, "The load balancing algorithm cannot be null");
        this.serverNodes = List.copyOf(serverNodes);
        this.algorithm = algorithm;
    }

    public void routRequest(Request request) {
        ServerNode serverNode = algorithm.selectServerNode(serverNodes);
        serverNode.processRequest(request);
    }

    public List<ServerNode> getServerNodes() {
        return serverNodes;
    }

    public LoadBalancingAlgorithm getAlgorithm() {
        return algorithm;
    }
}
