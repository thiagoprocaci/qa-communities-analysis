package com.tbp.graph.model;


import org.junit.Test;
import static org.junit.Assert.*;

public class GraphEdgeTest {

    @Test
    public void constructor() {
        Node n1 = new Node(1L);
        Node n2 = new Node(2L);
        Edge edge = new Edge(n1, n2);

        GraphAnalysisContext graphAnalysisContext = new GraphAnalysisContext();
        graphAnalysisContext.setIdCommunity(3);
        graphAnalysisContext.setId(4L);

        GraphNode graphNodeSource = new GraphNode();
        graphAnalysisContext.setId(5L);

        GraphNode graphNodeDest = new GraphNode();
        graphNodeDest.setId(6L);

        GraphEdge graphEdge = new GraphEdge(edge, graphAnalysisContext, graphNodeSource, graphNodeDest);
        assertNull(graphEdge.getId());
        assertEquals(n1.getId(), graphEdge.getIdUserSource());
        assertEquals(n2.getId(), graphEdge.getIdUserDest());
        assertEquals(edge.getWeight(), graphEdge.getWeight());
        assertEquals(graphAnalysisContext, graphEdge.getGraphAnalysisContext());
        assertEquals(graphAnalysisContext.getId(), graphEdge.getGraphAnalysisContext().getId());
        assertEquals(graphAnalysisContext.getIdCommunity(), graphEdge.getIdCommunity());
        assertEquals(graphNodeSource, graphEdge.getGraphNodeSource());
        assertEquals(graphNodeDest, graphEdge.getGraphNodeDest());

        assertEquals(graphNodeSource.getId(), graphEdge.getGraphNodeSource().getId());
        assertEquals(graphNodeDest.getId(), graphEdge.getGraphNodeDest().getId());
    }

    @Test
    public void defaultConstructor() {
        GraphEdge graphEdge = new GraphEdge();
        assertNull(graphEdge.getId());
        assertNull(graphEdge.getIdUserSource());
        assertNull(graphEdge.getIdUserDest());
        assertNull(graphEdge.getWeight());
        assertNull(graphEdge.getGraphAnalysisContext());
        assertNull(graphEdge.getIdCommunity());
        assertNull(graphEdge.getGraphNodeSource());
        assertNull(graphEdge.getGraphNodeDest());

    }
}
