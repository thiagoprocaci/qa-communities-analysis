package com.tbp.model.graph;


import com.tbp.model.Community;
import com.tbp.model.graph.stats.NodeMetrics;
import com.tbp.model.graph.stats.NodeMetricsFactory;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class GraphDtoTest {

    @Test
    public void testConstructor() {
        Graph g = new Graph();
        g.addEdge(1L, 2L);

        Community community = new Community();
        GraphDto graphDto = new GraphDto(g, community);
        assertEquals(community, graphDto.getCommunity());
        assertEquals(2, g.getNodeMap().size());
        assertTrue(graphDto.getNodes().contains(g.getNodeMap().get(1L)));
        assertTrue(graphDto.getNodes().contains(g.getNodeMap().get(2L)));
        assertEquals(1, graphDto.getEdges().size());
        assertTrue(graphDto.getEdges().contains(g.getEdgeMap().get("1_2")));

        assertEquals(1, g.getNodeMap().get(1L).getInteractions().intValue());
        assertEquals(1, g.getNodeMap().get(2L).getInteractions().intValue());


    }

    @Test
    public void testCalcNodeDesc() {
        Graph g = new Graph();
        g.addEdge(1L, 2L);

        Community community = new Community();
        GraphDto graphDto = new GraphDto(g, community);


        graphDto.nodeMetricsFactory = mock(NodeMetricsFactory.class);
        Node n = mock(Node.class);
        Node n2 = mock(Node.class);
        graphDto.nodes = Arrays.asList(n, n2);

        NodeMetrics nodeMetrics = createNodeMetrics();
        when(graphDto.nodeMetricsFactory.create(graphDto.nodes)).thenReturn(nodeMetrics);

        graphDto.calcNodeDesc();

        verify(n).setMetricDescription(Node.BETWEENNESS, nodeMetrics.getQ1Betweenness(), nodeMetrics.getMedianBetweenness(), nodeMetrics.getQ3Betweenness());
        verify(n).setMetricDescription(Node.CLOSENESS, nodeMetrics.getQ1Closeness(), nodeMetrics.getMedianCloseness(), nodeMetrics.getQ3Closeness());
        verify(n).setMetricDescription(Node.ECCENTRICITY, nodeMetrics.getQ1Eccentricity(), nodeMetrics.getMedianEccentricity(), nodeMetrics.getQ3Eccentricity());
        verify(n).setMetricDescription(Node.HARMONIC_CLOSENESS, nodeMetrics.getQ1HarmonicCloseness(), nodeMetrics.getMedianHarmonicCloseness(), nodeMetrics.getQ3HarmonicCloseness());
        verify(n).setMetricDescription(Node.PAGE_RANK, nodeMetrics.getQ1PageRank(), nodeMetrics.getMedianPageRank(), nodeMetrics.getQ3PageRank());
        verify(n).setMetricDescription(Node.INDEGREE, nodeMetrics.getQ1Indegree(), nodeMetrics.getMedianIndegree(), nodeMetrics.getQ3Indegree());
        verify(n).setMetricDescription(Node.OUTDEGREE, nodeMetrics.getQ1Outdegree(), nodeMetrics.getMedianOutdegree(), nodeMetrics.getQ3Outdegree());
        verify(n).setMetricDescription(Node.DEGREE, nodeMetrics.getQ1Degree(), nodeMetrics.getMedianDegree(), nodeMetrics.getQ3Degree());
        verify(n).setMetricDescription(Node.EIGENVECTOR, nodeMetrics.getQ1Eigenvector(), nodeMetrics.getMedianEigenvector(), nodeMetrics.getQ3Eigenvector());
        verify(n).setMetricDescription(Node.INTERACTIONS, nodeMetrics.getQ1Interactions(), nodeMetrics.getMedianInteractions(), nodeMetrics.getQ3Interactions());


        verify(n2).setMetricDescription(Node.BETWEENNESS, nodeMetrics.getQ1Betweenness(), nodeMetrics.getMedianBetweenness(), nodeMetrics.getQ3Betweenness());
        verify(n2).setMetricDescription(Node.CLOSENESS, nodeMetrics.getQ1Closeness(), nodeMetrics.getMedianCloseness(), nodeMetrics.getQ3Closeness());
        verify(n2).setMetricDescription(Node.ECCENTRICITY, nodeMetrics.getQ1Eccentricity(), nodeMetrics.getMedianEccentricity(), nodeMetrics.getQ3Eccentricity());
        verify(n2).setMetricDescription(Node.HARMONIC_CLOSENESS, nodeMetrics.getQ1HarmonicCloseness(), nodeMetrics.getMedianHarmonicCloseness(), nodeMetrics.getQ3HarmonicCloseness());
        verify(n2).setMetricDescription(Node.PAGE_RANK, nodeMetrics.getQ1PageRank(), nodeMetrics.getMedianPageRank(), nodeMetrics.getQ3PageRank());
        verify(n2).setMetricDescription(Node.INDEGREE, nodeMetrics.getQ1Indegree(), nodeMetrics.getMedianIndegree(), nodeMetrics.getQ3Indegree());
        verify(n2).setMetricDescription(Node.OUTDEGREE, nodeMetrics.getQ1Outdegree(), nodeMetrics.getMedianOutdegree(), nodeMetrics.getQ3Outdegree());
        verify(n2).setMetricDescription(Node.DEGREE, nodeMetrics.getQ1Degree(), nodeMetrics.getMedianDegree(), nodeMetrics.getQ3Degree());
        verify(n2).setMetricDescription(Node.EIGENVECTOR, nodeMetrics.getQ1Eigenvector(), nodeMetrics.getMedianEigenvector(), nodeMetrics.getQ3Eigenvector());
        verify(n2).setMetricDescription(Node.INTERACTIONS, nodeMetrics.getQ1Interactions(), nodeMetrics.getMedianInteractions(), nodeMetrics.getQ3Interactions());
    }

    private NodeMetrics createNodeMetrics() {
        NodeMetrics nodeMetrics = mock(NodeMetrics.class);
        when(nodeMetrics.getQ1Betweenness()).thenReturn(1d);
        when(nodeMetrics.getMedianBetweenness()).thenReturn(2d);
        when(nodeMetrics.getQ3Betweenness()).thenReturn(3d);

        when(nodeMetrics.getQ1Closeness()).thenReturn(4d);
        when(nodeMetrics.getMedianCloseness()).thenReturn(5d);
        when(nodeMetrics.getQ3Closeness()).thenReturn(6d);

        when(nodeMetrics.getQ1Eccentricity()).thenReturn(7d);
        when(nodeMetrics.getMedianEccentricity()).thenReturn(8d);
        when(nodeMetrics.getQ3Eccentricity()).thenReturn(9d);

        when(nodeMetrics.getQ1HarmonicCloseness()).thenReturn(10d);
        when(nodeMetrics.getMedianHarmonicCloseness()).thenReturn(11d);
        when(nodeMetrics.getQ3HarmonicCloseness()).thenReturn(12d);

        when(nodeMetrics.getQ1PageRank()).thenReturn(13d);
        when(nodeMetrics.getMedianPageRank()).thenReturn(14d);
        when(nodeMetrics.getQ3PageRank()).thenReturn(15d);

        when(nodeMetrics.getQ1Indegree()).thenReturn(16d);
        when(nodeMetrics.getMedianIndegree()).thenReturn(17d);
        when(nodeMetrics.getQ3Indegree()).thenReturn(18d);

        when(nodeMetrics.getQ1Outdegree()).thenReturn(19d);
        when(nodeMetrics.getMedianOutdegree()).thenReturn(20d);
        when(nodeMetrics.getQ3Outdegree()).thenReturn(21d);

        when(nodeMetrics.getQ1Degree()).thenReturn(22d);
        when(nodeMetrics.getMedianDegree()).thenReturn(23d);
        when(nodeMetrics.getQ3Degree()).thenReturn(24d);

        when(nodeMetrics.getQ1Eigenvector()).thenReturn(25d);
        when(nodeMetrics.getMedianEigenvector()).thenReturn(26d);
        when(nodeMetrics.getQ3Eigenvector()).thenReturn(27d);

        when(nodeMetrics.getQ1Interactions()).thenReturn(28d);
        when(nodeMetrics.getMedianInteractions()).thenReturn(29d);
        when(nodeMetrics.getQ3Interactions()).thenReturn(30d);

        return nodeMetrics;
    }

}
