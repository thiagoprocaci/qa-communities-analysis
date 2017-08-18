package com.tbp.model.graph;


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
    public void testSetMetricDesc() {
        Node node = new Node(1L);

        String metric = Node.BETWEENNESS;
        Double q1 =  0d;
        Double median = 0d;
        Double q3 = 0d;


        node.setBetweenness(0d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getBetweennessDesc());

        node.setBetweenness(null);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getBetweennessDesc());

    }

    @Test
    public void testSetMetricDescInteractions() {
        Node node = new Node(1L);

        String metric = Node.INTERACTIONS;
        Double q1 =  1d;
        Double median = 3d;
        Double q3 = 5d;

        node.interactions = -1;
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getInteractionsDesc());

        node.interactions = 0;
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getInteractionsDesc());

        node.interactions = 1;
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getInteractionsDesc());

        node.interactions = 2;
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1_MEDIAN, node.getInteractionsDesc());

        node.interactions = 3;
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getInteractionsDesc());

        node.interactions = 4;
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getInteractionsDesc());

        node.interactions = 5;
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getInteractionsDesc());

        node.interactions = 6;
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getInteractionsDesc());
    }


    @Test
    public void testSetMetricDescBetweenness() {
        Node node = new Node(1L);

        String metric = Node.BETWEENNESS;
        Double q1 =  1d;
        Double median = 3d;
        Double q3 = 5d;

        node.setBetweenness(-1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getBetweennessDesc());

        node.setBetweenness(0d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getBetweennessDesc());

        node.setBetweenness(1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getBetweennessDesc());

        node.setBetweenness(2d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1_MEDIAN, node.getBetweennessDesc());

        node.setBetweenness(3d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getBetweennessDesc());

        node.setBetweenness(4d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getBetweennessDesc());

        node.setBetweenness(5d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getBetweennessDesc());

        node.setBetweenness(6d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getBetweennessDesc());
    }


    @Test
    public void testSetMetricDescCloseness() {
        Node node = new Node(1L);

        String metric = Node.CLOSENESS;
        Double q1 =  1d;
        Double median = 3d;
        Double q3 = 5d;

        node.setCloseness(-1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getClosenessDesc());

        node.setCloseness(0d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getClosenessDesc());

        node.setCloseness(1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getClosenessDesc());

        node.setCloseness(2d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1_MEDIAN, node.getClosenessDesc());

        node.setCloseness(3d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getClosenessDesc());

        node.setCloseness(4d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getClosenessDesc());

        node.setCloseness(5d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getClosenessDesc());

        node.setCloseness(6d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getClosenessDesc());
    }

    @Test
    public void testSetMetricDescEccentricity() {
        Node node = new Node(1L);

        String metric = Node.ECCENTRICITY;
        Double q1 =  1d;
        Double median = 3d;
        Double q3 = 5d;

        node.setEccentricity(-1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getEccentricityDesc());

        node.setEccentricity(0d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getEccentricityDesc());

        node.setEccentricity(1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getEccentricityDesc());

        node.setEccentricity(2d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1_MEDIAN, node.getEccentricityDesc());

        node.setEccentricity(3d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getEccentricityDesc());

        node.setEccentricity(4d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getEccentricityDesc());

        node.setEccentricity(5d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getEccentricityDesc());

        node.setEccentricity(6d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getEccentricityDesc());
    }

    @Test
    public void testSetMetricDescHarmonicCloseness() {
        Node node = new Node(1L);

        String metric = Node.HARMONIC_CLOSENESS;
        Double q1 =  1d;
        Double median = 3d;
        Double q3 = 5d;

        node.setHarmonicCloseness(-1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getHarmonicClosenessDesc());

        node.setHarmonicCloseness(0d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getHarmonicClosenessDesc());

        node.setHarmonicCloseness(1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getHarmonicClosenessDesc());

        node.setHarmonicCloseness(2d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1_MEDIAN, node.getHarmonicClosenessDesc());

        node.setHarmonicCloseness(3d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getHarmonicClosenessDesc());

        node.setHarmonicCloseness(4d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getHarmonicClosenessDesc());

        node.setHarmonicCloseness(5d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getHarmonicClosenessDesc());

        node.setHarmonicCloseness(6d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getHarmonicClosenessDesc());
    }

    @Test
    public void testSetMetricDescPageRank() {
        Node node = new Node(1L);

        String metric = Node.PAGE_RANK;
        Double q1 =  1d;
        Double median = 3d;
        Double q3 = 5d;

        node.setPageRank(-1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getPageRankDesc());

        node.setPageRank(0d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getPageRankDesc());

        node.setPageRank(1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getPageRankDesc());

        node.setPageRank(2d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1_MEDIAN, node.getPageRankDesc());

        node.setPageRank(3d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getPageRankDesc());

        node.setPageRank(4d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getPageRankDesc());

        node.setPageRank(5d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getPageRankDesc());

        node.setPageRank(6d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getPageRankDesc());
    }


    @Test
    public void testSetMetricDescIndegree() {
        Node node = new Node(1L);

        String metric = Node.INDEGREE;
        Double q1 =  1d;
        Double median = 3d;
        Double q3 = 5d;

        node.setIndegree(-1);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getIndegreeDesc());

        node.setIndegree(0);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getIndegreeDesc());

        node.setIndegree(1);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getIndegreeDesc());

        node.setIndegree(2);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1_MEDIAN, node.getIndegreeDesc());

        node.setIndegree(3);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getIndegreeDesc());

        node.setIndegree(4);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getIndegreeDesc());

        node.setIndegree(5);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getIndegreeDesc());

        node.setIndegree(6);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getIndegreeDesc());
    }

    @Test
    public void testSetMetricDescOutdegree() {
        Node node = new Node(1L);

        String metric = Node.OUTDEGREE;
        Double q1 =  1d;
        Double median = 3d;
        Double q3 = 5d;

        node.setOutdegree(-1);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getOutdegreeDesc());

        node.setOutdegree(0);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getOutdegreeDesc());

        node.setOutdegree(1);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getOutdegreeDesc());

        node.setOutdegree(2);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1_MEDIAN, node.getOutdegreeDesc());

        node.setOutdegree(3);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getOutdegreeDesc());

        node.setOutdegree(4);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getOutdegreeDesc());

        node.setOutdegree(5);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getOutdegreeDesc());

        node.setOutdegree(6);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getOutdegreeDesc());
    }

    @Test
    public void testSetMetricDescDegree() {
        Node node = new Node(1L);

        String metric = Node.DEGREE;
        Double q1 =  1d;
        Double median = 3d;
        Double q3 = 5d;

        node.setDegree(-1);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getDegreeDesc());

        node.setDegree(0);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getDegreeDesc());

        node.setDegree(1);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getDegreeDesc());

        node.setDegree(2);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1_MEDIAN, node.getDegreeDesc());

        node.setDegree(3);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getDegreeDesc());

        node.setDegree(4);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getDegreeDesc());

        node.setDegree(5);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getDegreeDesc());

        node.setDegree(6);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getDegreeDesc());
    }

    @Test
    public void testSetMetricDescEigenvector() {
        Node node = new Node(1L);

        String metric = Node.EIGENVECTOR;
        Double q1 =  1d;
        Double median = 3d;
        Double q3 = 5d;

        node.setEigenvector(-1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getEigenvectorDesc());

        node.setEigenvector(0d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getEigenvectorDesc());

        node.setEigenvector(1d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1, node.getEigenvectorDesc());

        node.setEigenvector(2d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q1_MEDIAN, node.getEigenvectorDesc());

        node.setEigenvector(3d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getEigenvectorDesc());

        node.setEigenvector(4d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.MEDIAN_Q3, node.getEigenvectorDesc());

        node.setEigenvector(5d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getEigenvectorDesc());

        node.setEigenvector(6d);
        node.setMetricDescription(metric, q1, median, q3);
        assertEquals(Node.Q3, node.getEigenvectorDesc());
    }

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

        assertEquals(betweenness, node.getMetricValue(Node.BETWEENNESS));
        assertEquals(closeness, node.getMetricValue(Node.CLOSENESS));
        assertEquals(eccentricity, node.getMetricValue(Node.ECCENTRICITY));
        assertEquals(harmonicCloseness, node.getMetricValue(Node.HARMONIC_CLOSENESS));
        assertEquals(pageRank, node.getMetricValue(Node.PAGE_RANK));
        assertEquals(indegree.intValue(), node.getMetricValue(Node.INDEGREE).intValue());
        assertEquals(outdegree.intValue(), node.getMetricValue(Node.OUTDEGREE).intValue());
        assertEquals(degree.intValue(), node.getMetricValue(Node.DEGREE).intValue());
        assertEquals(eigenvector, node.getMetricValue(Node.EIGENVECTOR));
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
