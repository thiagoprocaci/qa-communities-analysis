package com.tbp.model.graph.stats;


import com.tbp.model.graph.Node;

import java.util.Collection;

public class NodeMetricsFactory {

    public NodeMetrics create(Collection<Node> nodes) {
        return new NodeMetrics(nodes);
    }

}
