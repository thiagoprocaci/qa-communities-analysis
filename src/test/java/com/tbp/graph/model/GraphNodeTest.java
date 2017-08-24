package com.tbp.graph.model;


import org.junit.Test;
import static org.junit.Assert.*;

public class GraphNodeTest {

    @Test
    public void constructor() {
        Node node = new Node(1L);

        node.setBetweenness(1d);
        node.setCloseness(2d);
        node.setEccentricity(3d);
        node.setHarmonicCloseness(4d);
        node.setPageRank(5d);
        node.setIndegree(1);
        node.setOutdegree(2);
        node.setDegree(3);
        node.setEigenvector(6d);
        node.setModularity(4);
        node.setClusteringCoefficient(7d);
        node.setStronglyComponent(5);
        node.setWeaklyComponent(6);

        node.increaseInteractions();
        node.increaseInteractions();
        node.increaseInteractions();
        node.increaseInteractions();

        GraphAnalysisContext graphAnalysisContext = new GraphAnalysisContext();
        graphAnalysisContext.setIdCommunity(2);
        graphAnalysisContext.setId(100L);

        GraphNode graphNode = new GraphNode(node, graphAnalysisContext);
        assertNull(graphNode.getId());
        assertEquals(node.getBetweenness(), graphNode.getBetweenness());
        assertEquals(node.getCloseness(), graphNode.getCloseness());
        assertEquals(node.getEccentricity(), graphNode.getEccentricity());
        assertEquals(node.getHarmonicCloseness(), graphNode.getHarmonicCloseness());
        assertEquals(node.getPageRank(), graphNode.getPageRank());
        assertEquals(node.getIndegree(), graphNode.getIndegree());
        assertEquals(node.getOutdegree(), graphNode.getOutdegree());
        assertEquals(node.getDegree(), graphNode.getDegree());
        assertEquals(node.getEigenvector(), graphNode.getEigenvector());
        assertEquals(node.getModularity(), graphNode.getModularityClass());
        assertEquals(node.getClusteringCoefficient(), graphNode.getClusteringCoefficient());
        assertEquals(node.getStronglyComponent(), graphNode.getStronglyComponent());
        assertEquals(node.getWeaklyComponent(), graphNode.getWeaklyComponent());
        assertEquals(node.getInteractions(), graphNode.getInteractions());
        assertEquals(node.getId(), graphNode.getIdUser());
        assertEquals(graphAnalysisContext, graphNode.getGraphAnalysisContext());
        assertEquals(graphAnalysisContext.getIdCommunity(), graphNode.getIdCommunity());
        assertEquals(graphAnalysisContext.getId(), graphNode.getGraphAnalysisContext().getId());
    }

    @Test
    public void defaultConstructor() {
        GraphNode graphNode = new GraphNode();
        assertNull(graphNode.getId());
        assertNull(graphNode.getBetweenness());
        assertNull(graphNode.getCloseness());
        assertNull(graphNode.getEccentricity());
        assertNull(graphNode.getHarmonicCloseness());
        assertNull(graphNode.getPageRank());
        assertNull(graphNode.getIndegree());
        assertNull(graphNode.getOutdegree());
        assertNull(graphNode.getDegree());
        assertNull(graphNode.getEigenvector());
        assertNull(graphNode.getModularityClass());
        assertNull(graphNode.getClusteringCoefficient());
        assertNull(graphNode.getStronglyComponent());
        assertNull(graphNode.getWeaklyComponent());
        assertNull(graphNode.getInteractions());
        assertNull(graphNode.getIdUser());
        assertNull(graphNode.getGraphAnalysisContext());
        assertNull(graphNode.getIdCommunity());
    }

}
