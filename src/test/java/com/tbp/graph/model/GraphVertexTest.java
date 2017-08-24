package com.tbp.graph.model;


import org.junit.Test;
import static org.junit.Assert.*;

public class GraphVertexTest {

    @Test
    public void constructor() {
        Vertex vertex = new Vertex(1L);

        vertex.setBetweenness(1d);
        vertex.setCloseness(2d);
        vertex.setEccentricity(3d);
        vertex.setHarmonicCloseness(4d);
        vertex.setPageRank(5d);
        vertex.setIndegree(1);
        vertex.setOutdegree(2);
        vertex.setDegree(3);
        vertex.setEigenvector(6d);
        vertex.setModularity(4);
        vertex.setClusteringCoefficient(7d);
        vertex.setStronglyComponent(5);
        vertex.setWeaklyComponent(6);

        vertex.increaseInteractions();
        vertex.increaseInteractions();
        vertex.increaseInteractions();
        vertex.increaseInteractions();

        GraphAnalysisContext graphAnalysisContext = new GraphAnalysisContext();
        graphAnalysisContext.setIdCommunity(2);
        graphAnalysisContext.setId(100L);

        GraphNode graphNode = new GraphNode(vertex, graphAnalysisContext);
        assertNull(graphNode.getId());
        assertEquals(vertex.getBetweenness(), graphNode.getBetweenness());
        assertEquals(vertex.getCloseness(), graphNode.getCloseness());
        assertEquals(vertex.getEccentricity(), graphNode.getEccentricity());
        assertEquals(vertex.getHarmonicCloseness(), graphNode.getHarmonicCloseness());
        assertEquals(vertex.getPageRank(), graphNode.getPageRank());
        assertEquals(vertex.getIndegree(), graphNode.getIndegree());
        assertEquals(vertex.getOutdegree(), graphNode.getOutdegree());
        assertEquals(vertex.getDegree(), graphNode.getDegree());
        assertEquals(vertex.getEigenvector(), graphNode.getEigenvector());
        assertEquals(vertex.getModularity(), graphNode.getModularityClass());
        assertEquals(vertex.getClusteringCoefficient(), graphNode.getClusteringCoefficient());
        assertEquals(vertex.getStronglyComponent(), graphNode.getStronglyComponent());
        assertEquals(vertex.getWeaklyComponent(), graphNode.getWeaklyComponent());
        assertEquals(vertex.getInteractions(), graphNode.getInteractions());
        assertEquals(vertex.getId(), graphNode.getIdUser());
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
