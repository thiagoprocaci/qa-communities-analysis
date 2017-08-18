package com.tbp.model.graph.stats;

import com.tbp.model.graph.Node;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;


public class NodeMetricsFactoryTest {

    @Test
    public void testCreate() {
        List<Node> nodeList = Arrays.asList(new Node(2L));
        NodeMetricsFactory factory = new NodeMetricsFactory();
        assertNotNull(factory.create(nodeList));
    }

}
