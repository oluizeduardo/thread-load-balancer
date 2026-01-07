package br.com.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ServerNodeTest {


    @Test
    @DisplayName("Should Print Processing Request Message")
    void shouldPrintProcessingRequestMessage() {
        ServerNode serverNode = new ServerNode("ServerA");
        assertThat(serverNode.hostName()).isEqualTo("ServerA");
    }

}