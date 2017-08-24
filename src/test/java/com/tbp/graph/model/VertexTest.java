package com.tbp.graph.model;


import org.junit.Test;

import static org.junit.Assert.*;

public class VertexTest {

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
        Vertex vertex = new Vertex(1L);
        vertex.setBetweenness(betweenness);
        vertex.setCloseness(closeness);
        vertex.setEccentricity(eccentricity);
        vertex.setHarmonicCloseness(harmonicCloseness);
        vertex.setPageRank(pageRank);
        vertex.setIndegree(indegree);
        vertex.setOutdegree(outdegree);
        vertex.setDegree(degree);
        vertex.setEigenvector(eigenvector);
        vertex.setModularity(modularity);
        vertex.setClusteringCoefficient(clusteringCoefficient);
        vertex.setStronglyComponent(stronglyComponent);
        vertex.setWeaklyComponent(weaklyComponent);
        vertex.setLabel(label);

        assertEquals(betweenness, vertex.getBetweenness());
        assertEquals(closeness, vertex.getCloseness());
        assertEquals(eccentricity, vertex.getEccentricity());
        assertEquals(harmonicCloseness, vertex.getHarmonicCloseness());
        assertEquals(pageRank, vertex.getPageRank());
        assertEquals(indegree, vertex.getIndegree());
        assertEquals(outdegree, vertex.getOutdegree());
        assertEquals(degree, vertex.getDegree());
        assertEquals(eigenvector, vertex.getEigenvector());
        assertEquals(modularity, vertex.getModularity());
        assertEquals(clusteringCoefficient, vertex.getClusteringCoefficient());
        assertEquals(stronglyComponent, vertex.getStronglyComponent());
        assertEquals(weaklyComponent, vertex.getWeaklyComponent());
        assertEquals(label, vertex.getLabel());
        assertEquals(0, vertex.getInteractions().intValue());
    }

    @Test
    public void testIncreaseInteractions() {
        Vertex vertex = new Vertex(1L);
        int i = 0;
        while(i < 10) {
            assertEquals(i, vertex.getInteractions().intValue());
            vertex.increaseInteractions();
            i++;
        }
    }

    @Test
    public void testConstructor() {
        Vertex vertex = new Vertex(1L);
        assertEquals(1L, vertex.getId(), 0.00001);
    }

    @Test
    public void testConstructorNullParam() {
        try {
            new Vertex(null);
            fail("Should not be executed");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testIdentifiableEqualsAndHashCode() {
        Vertex n1 = new Vertex(1L);
        Vertex n2 = new Vertex(2L);
        Vertex n3 = new Vertex(1L);

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
