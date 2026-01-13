package br.com.study;

import java.util.Set;

/**
 * This interface will define a method for selecting a server node from the list of available nodes.
 */
interface LoadBalancingAlgorithm {
    ServerNode selectServerNode(Set<ServerNode> serverNodes);
}
