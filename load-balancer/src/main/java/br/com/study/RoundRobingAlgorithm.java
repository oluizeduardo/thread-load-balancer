package br.com.study;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

final class RoundRobingAlgorithm implements LoadBalancingAlgorithm {

    private final AtomicInteger currentIndex = new AtomicInteger(0);

    @Override
    public ServerNode selectServerNode(Set<ServerNode> serverNodes) {
        Objects.requireNonNull(serverNodes, "The list of servers cannot be null");
        if (serverNodes.isEmpty()) {
            throw new IllegalArgumentException("No server nodes available");
        }

        List<ServerNode> serverNodesList = new ArrayList<>(serverNodes);

        int index = currentIndex.getAndIncrement() % serverNodes.size();

        return serverNodesList.get(index);
    }
}
