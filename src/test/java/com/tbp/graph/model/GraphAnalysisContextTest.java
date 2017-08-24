package com.tbp.graph.model;


import org.junit.Test;
import static org.junit.Assert.*;

public class GraphAnalysisContextTest {


    @Test
    public void constructor() {
        Graph g = new Graph();
        g.addEdge(1L, 2L);
        g.addEdge(1L, 3L);
        g.addEdge(3L, 4L);
        g.addEdge(2L, 5L);

        g.setAvgClusteringCoef(1d);
        g.setDensity(2d);
        g.setDiameter(3d);
        g.setRadius(4d);
        g.setAvgDist(5d);
        g.setWeaklyComponentCount(6);
        g.setStronglyComponentCount(7);
        g.setNumberOfCommunity(8);
        g.setModularityWithResolution(9d);
        g.setModularity(10d);
        g.setAvgDegree(11d);


        Integer idCommunity = 1;
        Integer period = 0;

        GraphAnalysisContext graphAnalysisContext = new GraphAnalysisContext(g, period, idCommunity);
        assertNull(graphAnalysisContext.getId());
        assertEquals(idCommunity, graphAnalysisContext.getIdCommunity());
        assertEquals(period, graphAnalysisContext.getPeriod());
        assertEquals(g.getAvgClusteringCoef(), graphAnalysisContext.getAvgClusteringCoef());
        assertEquals(g.getDensity(), graphAnalysisContext.getDensity());
        assertEquals(g.getDiameter(), graphAnalysisContext.getDiameter());
        assertEquals(g.getRadius(), graphAnalysisContext.getRadius());
        assertEquals(g.getAvgDist(), graphAnalysisContext.getAvgDist());
        assertEquals(g.getWeaklyComponentCount(), graphAnalysisContext.getWeaklyComponentCount());
        assertEquals(g.getStronglyComponentCount(), graphAnalysisContext.getStronglyComponentCount());
        assertEquals(g.getNumberOfCommunity(), graphAnalysisContext.getNumberCommunities());
        assertEquals(g.getModularityWithResolution(), graphAnalysisContext.getModularityWithResolution());
        assertEquals(g.getModularity(), graphAnalysisContext.getModularity());
        assertEquals(g.getAvgDegree(), graphAnalysisContext.getAvgDegree());
    }

    @Test
    public void defaultConstructor() {

        GraphAnalysisContext graphAnalysisContext = new GraphAnalysisContext();
        assertNull(graphAnalysisContext.getId());
        assertNull(graphAnalysisContext.getIdCommunity());
        assertNull(graphAnalysisContext.getPeriod());
        assertNull(graphAnalysisContext.getAvgClusteringCoef());
        assertNull(graphAnalysisContext.getDensity());
        assertNull(graphAnalysisContext.getDiameter());
        assertNull(graphAnalysisContext.getRadius());
        assertNull(graphAnalysisContext.getAvgDist());
        assertNull(graphAnalysisContext.getWeaklyComponentCount());
        assertNull(graphAnalysisContext.getStronglyComponentCount());
        assertNull(graphAnalysisContext.getNumberCommunities());
        assertNull(graphAnalysisContext.getModularityWithResolution());
        assertNull(graphAnalysisContext.getModularity());
        assertNull(graphAnalysisContext.getAvgDegree());
    }
}
