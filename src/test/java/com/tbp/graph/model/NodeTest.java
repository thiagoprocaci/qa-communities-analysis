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
    Integer modularity = 10;
    Double clusteringCoefficient = 10d;
    Integer stronglyComponent = 11;
    Integer weaklyComponent = 12;
    String label = "label";

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
        node.setModularity(modularity);
        node.setClusteringCoefficient(clusteringCoefficient);
        node.setStronglyComponent(stronglyComponent);
        node.setWeaklyComponent(weaklyComponent);
        node.setLabel(label);

        assertEquals(betweenness, node.getBetweenness());
        assertEquals(closeness, node.getCloseness());
        assertEquals(eccentricity, node.getEccentricity());
        assertEquals(harmonicCloseness, node.getHarmonicCloseness());
        assertEquals(pageRank, node.getPageRank());
        assertEquals(indegree, node.getIndegree());
        assertEquals(outdegree, node.getOutdegree());
        assertEquals(degree, node.getDegree());
        assertEquals(eigenvector, node.getEigenvector());
        assertEquals(modularity, node.getModularity());
        assertEquals(clusteringCoefficient, node.getClusteringCoefficient());
        assertEquals(stronglyComponent, node.getStronglyComponent());
        assertEquals(weaklyComponent, node.getWeaklyComponent());
        assertEquals(label, node.getLabel());
        assertEquals(0, node.getInteractions().intValue());
    }

    @Test
    public void testIncreaseInteractions() {
        Node node = new Node(1L);
        int i = 0;
        while(i < 10) {
            assertEquals(i, node.getInteractions().intValue());
            node.increaseInteractions();
            i++;
        }
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

    @Test
    public void testIdentifiableEqualsAndHashCode() {
        Node n1 = new Node(1L);
        Node n2 = new Node(2L);
        Node n3 = new Node(1L);

        assertFalse(n1.equals(n2));
        assertFalse(n1.equals(null));
        assertFalse(n1.equals("abc"));
        assertTrue(n1.equals(n1));
        assertTrue(n1.equals(n3));
        assertEquals(n1.hashCode(), n3.hashCode());

        n1.id = null;
        n2.id = null;
        assertTrue(n1.equals(n2));
        assertEquals(0, n1.hashCode());
    }

}
