package com.tbp.model.graph.stats;


import com.tbp.model.graph.Node;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class NodeMetricsTest {

    static final String BETWEENNESS = "betweenness";
    static final String CLOSENESS = "closeness";
    static final String ECCENTRICITY = "eccentricity";
    static final String HARMONIC_CLOSENESS = "harmonicCloseness";
    static final String PAGE_RANK = "pageRank";
    static final String INDEGREE = "indegree";
    static final String OUTDEGREE = "outdegree";
    static final String DEGREE = "degree";
    static final String EIGENVECTOR = "eigenvector";
    static final String INTERACTIONS = "interactions";

    @Test
    public void testInteractions() {
        List<Node> nodeList = createNodes(INTERACTIONS);
        NodeMetrics nodeMetrics = new NodeMetrics(nodeList);
        assertEquals(1.25, nodeMetrics.getQ1Interactions(), 0.0001);
        assertEquals(2.5, nodeMetrics.getMedianInteractions(), 0.0001);
        assertEquals(3.75, nodeMetrics.getQ3Interactions(), 0.0001);
        assertZeroExcept(INTERACTIONS, nodeMetrics);
    }

    @Test
    public void testEigenvector() {
        List<Node> nodeList = createNodes(EIGENVECTOR);
        NodeMetrics nodeMetrics = new NodeMetrics(nodeList);
        assertEquals(2.5, nodeMetrics.getQ1Eigenvector(), 0.0001);
        assertEquals(5.0, nodeMetrics.getMedianEigenvector(), 0.0001);
        assertEquals(7.5, nodeMetrics.getQ3Eigenvector(), 0.0001);
        assertZeroExcept(EIGENVECTOR, nodeMetrics);
    }

    @Test
    public void testDegree() {
        List<Node> nodeList = createNodes(DEGREE);
        NodeMetrics nodeMetrics = new NodeMetrics(nodeList);
        assertEquals(2.5, nodeMetrics.getQ1Degree(), 0.0001);
        assertEquals(5.0, nodeMetrics.getMedianDegree(), 0.0001);
        assertEquals(7.5, nodeMetrics.getQ3Degree(), 0.0001);
        assertZeroExcept(DEGREE, nodeMetrics);
    }

    @Test
    public void testOutdegree() {
        List<Node> nodeList = createNodes(OUTDEGREE);
        NodeMetrics nodeMetrics = new NodeMetrics(nodeList);
        assertEquals(2.5, nodeMetrics.getQ1Outdegree(), 0.0001);
        assertEquals(5.0, nodeMetrics.getMedianOutdegree(), 0.0001);
        assertEquals(7.5, nodeMetrics.getQ3Outdegree(), 0.0001);
        assertZeroExcept(OUTDEGREE, nodeMetrics);
    }

    @Test
    public void testIndegree() {
        List<Node> nodeList = createNodes(INDEGREE);
        NodeMetrics nodeMetrics = new NodeMetrics(nodeList);
        assertEquals(2.5, nodeMetrics.getQ1Indegree(), 0.0001);
        assertEquals(5.0, nodeMetrics.getMedianIndegree(), 0.0001);
        assertEquals(7.5, nodeMetrics.getQ3Indegree(), 0.0001);
        assertZeroExcept(INDEGREE, nodeMetrics);
    }

    @Test
    public void testPageRank() {
        List<Node> nodeList = createNodes(PAGE_RANK);
        NodeMetrics nodeMetrics = new NodeMetrics(nodeList);
        assertEquals(2.5, nodeMetrics.getQ1PageRank(), 0.0001);
        assertEquals(5.0, nodeMetrics.getMedianPageRank(), 0.0001);
        assertEquals(7.5, nodeMetrics.getQ3PageRank(), 0.0001);
        assertZeroExcept(PAGE_RANK, nodeMetrics);
    }

    @Test
    public void testHarmonicCloseness() {
        List<Node> nodeList = createNodes(HARMONIC_CLOSENESS);
        NodeMetrics nodeMetrics = new NodeMetrics(nodeList);
        assertEquals(2.5, nodeMetrics.getQ1HarmonicCloseness(), 0.0001);
        assertEquals(5.0, nodeMetrics.getMedianHarmonicCloseness(), 0.0001);
        assertEquals(7.5, nodeMetrics.getQ3HarmonicCloseness(), 0.0001);
        assertZeroExcept(HARMONIC_CLOSENESS, nodeMetrics);
    }


    @Test
    public void testEccentricity() {
        List<Node> nodeList = createNodes(ECCENTRICITY);
        NodeMetrics nodeMetrics = new NodeMetrics(nodeList);
        assertEquals(2.5, nodeMetrics.getQ1Eccentricity(), 0.0001);
        assertEquals(5.0, nodeMetrics.getMedianEccentricity(), 0.0001);
        assertEquals(7.5, nodeMetrics.getQ3Eccentricity(), 0.0001);
        assertZeroExcept(ECCENTRICITY, nodeMetrics);
    }

    @Test
    public void testCloseness() {
        List<Node> nodeList = createNodes(CLOSENESS);
        NodeMetrics nodeMetrics = new NodeMetrics(nodeList);
        assertEquals(2.5, nodeMetrics.getQ1Closeness(), 0.0001);
        assertEquals(5.0, nodeMetrics.getMedianCloseness(), 0.0001);
        assertEquals(7.5, nodeMetrics.getQ3Closeness(), 0.0001);
        assertZeroExcept(CLOSENESS, nodeMetrics);
    }

    @Test
    public void testBetweenness() {
        List<Node> nodeList = createNodes(BETWEENNESS);
        NodeMetrics nodeMetrics = new NodeMetrics(nodeList);
        assertEquals(2.5, nodeMetrics.getQ1Betweenness(), 0.0001);
        assertEquals(5.0, nodeMetrics.getMedianBetweenness(), 0.0001);
        assertEquals(7.5, nodeMetrics.getQ3Betweenness(), 0.0001);
        assertZeroExcept(BETWEENNESS, nodeMetrics);
    }

    private void assertZeroExcept(String metric, NodeMetrics nodeMetrics) {
        if(!BETWEENNESS.equals(metric)) {
            assertEquals(0d, nodeMetrics.getQ1Betweenness(), 0.0001);
            assertEquals(0d, nodeMetrics.getMedianBetweenness(), 0.0001);
            assertEquals(0d, nodeMetrics.getQ3Betweenness(), 0.0001);

        }
        if(!CLOSENESS.equals(metric)) {
            assertEquals(0d, nodeMetrics.getQ1Closeness(), 0.0001);
            assertEquals(0d, nodeMetrics.getMedianCloseness(), 0.0001);
            assertEquals(0d, nodeMetrics.getQ3Closeness(), 0.0001);
        }
        if(!ECCENTRICITY.equals(metric)) {
            assertEquals(0d, nodeMetrics.getQ1Eccentricity(), 0.0001);
            assertEquals(0d, nodeMetrics.getMedianEccentricity(), 0.0001);
            assertEquals(0d, nodeMetrics.getQ3Eccentricity(), 0.0001);
        }
        if(!HARMONIC_CLOSENESS.equals(metric)) {
            assertEquals(0d, nodeMetrics.getQ1HarmonicCloseness(), 0.0001);
            assertEquals(0d, nodeMetrics.getMedianHarmonicCloseness(), 0.0001);
            assertEquals(0d, nodeMetrics.getQ3HarmonicCloseness(), 0.0001);
        }
        if(!PAGE_RANK.equals(metric)) {
            assertEquals(0d, nodeMetrics.getQ1PageRank(), 0.0001);
            assertEquals(0d, nodeMetrics.getMedianPageRank(), 0.0001);
            assertEquals(0d, nodeMetrics.getQ3PageRank(), 0.0001);
        }
        if(!INDEGREE.equals(metric)) {
            assertEquals(0d, nodeMetrics.getQ1Indegree(), 0.0001);
            assertEquals(0d, nodeMetrics.getMedianIndegree(), 0.0001);
            assertEquals(0d, nodeMetrics.getQ3Indegree(), 0.0001);
        }
        if(!OUTDEGREE.equals(metric)) {
            assertEquals(0d, nodeMetrics.getQ1Outdegree(), 0.0001);
            assertEquals(0d, nodeMetrics.getMedianOutdegree(), 0.0001);
            assertEquals(0d, nodeMetrics.getQ3Outdegree(), 0.0001);
        }
        if(!DEGREE.equals(metric)) {
            assertEquals(0d, nodeMetrics.getQ1Degree(), 0.0001);
            assertEquals(0d, nodeMetrics.getMedianDegree(), 0.0001);
            assertEquals(0d, nodeMetrics.getQ3Degree(), 0.0001);
        }
        if(!EIGENVECTOR.equals(metric)) {
            assertEquals(0d, nodeMetrics.getQ1Eigenvector(), 0.0001);
            assertEquals(0d, nodeMetrics.getMedianEigenvector(), 0.0001);
            assertEquals(0d, nodeMetrics.getQ3Eigenvector(), 0.0001);
        }
        if(!INTERACTIONS.equals(metric)) {
            assertEquals(0d, nodeMetrics.getQ1Interactions(), 0.0001);
            assertEquals(0d, nodeMetrics.getMedianInteractions(), 0.0001);
            assertEquals(0d, nodeMetrics.getQ3Interactions(), 0.0001);
        }
    }


    private List<Node> createNodes(String metric) {
        Node node = new Node(1L);
        Node node2 = new Node(2L);
        Node node3 = new Node(3L);
        Node node4 = new Node(4L);

        switch (metric) {
            case BETWEENNESS:
                node.setBetweenness(2d);
                node2.setBetweenness(4d);
                node3.setBetweenness(6d);
                node4.setBetweenness(8d);
                break;
            case CLOSENESS:
                node.setCloseness(2d);
                node2.setCloseness(4d);
                node3.setCloseness(6d);
                node4.setCloseness(8d);
                break;
            case ECCENTRICITY:
                node.setEccentricity(2d);
                node2.setEccentricity(4d);
                node3.setEccentricity(6d);
                node4.setEccentricity(8d);
                break;
            case HARMONIC_CLOSENESS:
                node.setHarmonicCloseness(2d);
                node2.setHarmonicCloseness(4d);
                node3.setHarmonicCloseness(6d);
                node4.setHarmonicCloseness(8d);
                break;
            case PAGE_RANK:
                node.setPageRank(2d);
                node2.setPageRank(4d);
                node3.setPageRank(6d);
                node4.setPageRank(8d);
                break;
            case INDEGREE:
                node.setIndegree(2);
                node2.setIndegree(4);
                node3.setIndegree(6);
                node4.setIndegree(8);
                break;
            case OUTDEGREE:
                node.setOutdegree(2);
                node2.setOutdegree(4);
                node3.setOutdegree(6);
                node4.setOutdegree(8);
                break;
            case DEGREE:
                node.setDegree(2);
                node2.setDegree(4);
                node3.setDegree(6);
                node4.setDegree(8);
                break;
            case EIGENVECTOR:
                node.setEigenvector(2d);
                node2.setEigenvector(4d);
                node3.setEigenvector(6d);
                node4.setEigenvector(8d);
                break;
            case INTERACTIONS:
                node.increaseInteractions();

                node2.increaseInteractions();
                node2.increaseInteractions();

                node3.increaseInteractions();
                node3.increaseInteractions();
                node3.increaseInteractions();

                node4.increaseInteractions();
                node4.increaseInteractions();
                node4.increaseInteractions();
                node4.increaseInteractions();

                break;
        }
        List<Node> nodeList = Arrays.asList(node, node2, node3, node4);
        return nodeList;
    }




}
