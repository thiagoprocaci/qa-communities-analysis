package com.tbp.graph.model;


import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    Double betweenness = 1d;
    Double closeness =  2d;
    Double eccentricity = 3d;
    Double harmonicCloseness = 4d;
    Double pageRank = 5d;
    Integer indegree = 6;
    Integer outdegree = 7;
    Integer degree = 8;
    Double eigenvector = 9d;

    @Test
    public void testMetrics() {
        Node node = new Node(1L);
        node.setBetweenness(betweenness);
        node.setCloseness(closeness);
        node.setEccentricity(eccentricity);
        node.setHarmonicCloseness(harmonicCloseness);
        node.setPageRank(pageRank);
        node.setIndegree(indegree);
        node.setOutdegree(outdegree);
        node.setDegree(degree);
        node.setEigenvector(eigenvector);

        assertEquals(betweenness, node.getBetweenness());
        assertEquals(closeness, node.getCloseness());
        assertEquals(eccentricity, node.getEccentricity());
        assertEquals(harmonicCloseness, node.getHarmonicCloseness());
        assertEquals(pageRank, node.getPageRank());
        assertEquals(indegree, node.getIndegree());
        assertEquals(outdegree, node.getOutdegree());
        assertEquals(degree, node.getDegree());
        assertEquals(eigenvector, node.getEigenvector());
    }

    @Test
    public void testConstructor() {
        Node node = new Node(1L);
        assertEquals(1L, node.getId(), 0.00001);
    }

    @Test
    public void testConstructorNullParam() {
        try {
            new Node(null);
            fail("Should not be executed");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }



}
