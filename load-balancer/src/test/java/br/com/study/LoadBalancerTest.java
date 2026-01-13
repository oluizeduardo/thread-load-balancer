package br.com.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class LoadBalancerTest {

    @Test
    @DisplayName("Should Throw NullPointerException when the list of server nodes is null")
    void shouldThrowNullPointerException1() {

        LoadBalancingAlgorithm algorithm = new RoundRobingAlgorithm();

        assertThatThrownBy(() -> new LoadBalancer(null, algorithm))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The list of servers cannot be null");
    }

    @Test
    @DisplayName("Should Throw NullPointerException when the load balancing algorithm is null")
    void shouldThrowNullPointerException2() {

        LoadBalancingAlgorithm algorithm = null;

        assertThatThrownBy(() -> new LoadBalancer(new HashSet<ServerNode>(), algorithm))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The load balancing algorithm cannot be null");
    }

    @Test
    @DisplayName("Should Delegate Server Selection To The Algorithm.")
    void shouldDelegateServerSelectionToAlgorithm() {

        // arrange
        Set<ServerNode> nodes = Set.of(new ServerNode("A"));

        LoadBalancingAlgorithm algorithm = mock(LoadBalancingAlgorithm.class);
        ServerNode server = mock(ServerNode.class);

        // act
        when(algorithm.selectServerNode(nodes)).thenReturn(server);

        LoadBalancer balancer = new LoadBalancer(nodes, algorithm);

        Request req = new Request("msg");

        balancer.routRequest(req);

        // assert
        verify(algorithm, times(1)).selectServerNode(nodes);
    }

}