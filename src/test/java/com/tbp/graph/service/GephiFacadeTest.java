package com.tbp.graph.service;


import com.tbp.graph.model.Graph;
import org.junit.Test;

import static org.junit.Assert.*;

public class GephiFacadeTest {


    @Test
    public void testExecuteAlgorithm() {
        // everything calculated using gephi tool
        // this test just checks if the gephi tool result is equal to gephi facade result.
        Graph g = new Graph();
        g.addEdge(1L, 2L);
        g.addEdge(1L, 3L);
        g.addEdge(3L, 4L);
        g.addEdge(2L, 5L);

        GephiFacade gephiFacade = new GephiFacade();
        gephiFacade.executeAlgorithm(g);

        assertEquals(0, g.getNodeMap().get(1L).getIndegree().intValue());
        assertEquals(2, g.getNodeMap().get(1L).getOutdegree().intValue());
        assertEquals(2, g.getNodeMap().get(1L).getDegree().intValue());
        assertEquals(2d, g.getNodeMap().get(1L).getEccentricity(), 0.0001);
        assertEquals(0.6666, g.getNodeMap().get(1L).getCloseness(), 0.0001);
        assertEquals(0.75, g.getNodeMap().get(1L).getHarmonicCloseness(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(1L).getBetweenness(), 0.0001);
        assertEquals(0.1209d, g.getNodeMap().get(1L).getPageRank(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(1L).getEigenvector(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(1L).getClusteringCoefficient(), 0.0001);
        assertEquals(0, g.getNodeMap().get(1L).getWeaklyComponent().intValue());


        assertEquals(1, g.getNodeMap().get(2L).getIndegree().intValue());
        assertEquals(1, g.getNodeMap().get(2L).getOutdegree().intValue());
        assertEquals(2, g.getNodeMap().get(2L).getDegree().intValue());
        assertEquals(1d, g.getNodeMap().get(2L).getEccentricity(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(2L).getCloseness(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(2L).getHarmonicCloseness(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(2L).getBetweenness(), 0.0001);
        assertEquals(0.1722d, g.getNodeMap().get(2L).getPageRank(), 0.0001);
        assertEquals(0.0703d, g.getNodeMap().get(2L).getEigenvector(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(2L).getClusteringCoefficient(), 0.0001);
        assertEquals(0, g.getNodeMap().get(2L).getWeaklyComponent().intValue());


        assertEquals(1, g.getNodeMap().get(3L).getIndegree().intValue());
        assertEquals(1, g.getNodeMap().get(3L).getOutdegree().intValue());
        assertEquals(2, g.getNodeMap().get(3L).getDegree().intValue());
        assertEquals(1d, g.getNodeMap().get(3L).getEccentricity(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(3L).getCloseness(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(3L).getHarmonicCloseness(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(3L).getBetweenness(), 0.0001);
        assertEquals(0.1722d, g.getNodeMap().get(3L).getPageRank(), 0.0001);
        assertEquals(0.0703d, g.getNodeMap().get(3L).getEigenvector(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(3L).getClusteringCoefficient(), 0.0001);
        assertEquals(0, g.getNodeMap().get(3L).getWeaklyComponent().intValue());


        assertEquals(1, g.getNodeMap().get(4L).getIndegree().intValue());
        assertEquals(0, g.getNodeMap().get(4L).getOutdegree().intValue());
        assertEquals(1, g.getNodeMap().get(4L).getDegree().intValue());
        assertEquals(0d, g.getNodeMap().get(4L).getEccentricity(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(4L).getCloseness(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(4L).getHarmonicCloseness(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(4L).getBetweenness(), 0.0001);
        assertEquals(0.2672d, g.getNodeMap().get(4L).getPageRank(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(4L).getEigenvector(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(4L).getClusteringCoefficient(), 0.0001);
        assertEquals(0, g.getNodeMap().get(4L).getWeaklyComponent().intValue());


        assertEquals(1, g.getNodeMap().get(5L).getIndegree().intValue());
        assertEquals(0, g.getNodeMap().get(5L).getOutdegree().intValue());
        assertEquals(1, g.getNodeMap().get(5L).getDegree().intValue());
        assertEquals(0d, g.getNodeMap().get(5L).getEccentricity(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(5L).getCloseness(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(5L).getHarmonicCloseness(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(5L).getBetweenness(), 0.0001);
        assertEquals(0.2672d, g.getNodeMap().get(5L).getPageRank(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(5L).getEigenvector(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(5L).getClusteringCoefficient(), 0.0001);
        assertEquals(0, g.getNodeMap().get(5L).getWeaklyComponent().intValue());


        assertEquals(2, g.getNumberOfCommunity().intValue());
        assertEquals(0.219, g.getModularityWithResolution().doubleValue(), 0.0001);
        assertEquals(0.219, g.getModularityWithResolution().doubleValue(), 0.0001);
        assertEquals(0d, g.getAvgClusteringCoef(), 0.0001);
        assertEquals(0.2d, g.getDensity(), 0.0001);
        assertEquals(1, g.getWeaklyComponentCount().intValue());
        assertEquals(5, g.getStronglyComponentCount().intValue());
        assertEquals(2, g.getDiameter().intValue());
        assertEquals(0, g.getRadius(), 0.0001);
        assertEquals(1.3333, g.getAvgDist(), 0.0001);

        long i = 1;
        while(i < 6) {
            assertTrue(g.getNodeMap().get(i).getModularity() == 0 || g.getNodeMap().get(i).getModularity() == 1);
            long j = 1;
            while(j < 6) {
                if(i != j) {
                    assertFalse(g.getNodeMap().get(i).getStronglyComponent() == g.getNodeMap().get(j).getStronglyComponent());
                }
                j++;
            }
            i++;
        }
    }

}
