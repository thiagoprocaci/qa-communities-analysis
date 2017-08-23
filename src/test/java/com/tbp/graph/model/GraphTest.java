package com.tbp.graph.model;


import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testCosntructor() {
        Graph g = new Graph();
        assertNotNull(g.getEdgeMap());
        assertNotNull(g.getNodeMap());
        assertTrue(g.getEdgeMap().isEmpty());
        assertTrue(g.getNodeMap().isEmpty());
    }

    @Test
    public void testAddEdge() {
        Graph g = new Graph();
        // adding the same edge 2 times ...
        g.addEdge(1L, 2L);
        g.addEdge(1L, 2L);

        assertEquals(2, g.getNodeMap().size());
        assertEquals(1, g.getEdgeMap().size());
        assertEquals(1L, g.getNodeMap().get(1L).getId().longValue());
        assertEquals(2L, g.getNodeMap().get(2L).getId().longValue());
        assertEquals("1_2", g.getEdgeMap().get("1_2").getId());
        assertEquals(1L, g.getEdgeMap().get("1_2").getSource().getId().longValue());
        assertEquals(2L, g.getEdgeMap().get("1_2").getDest().getId().longValue());

        assertEquals(2, g.getNodeMap().get(1L).getInteractions().intValue());
        assertEquals(2, g.getNodeMap().get(2L).getInteractions().intValue());

        // one edge already exists
        g.addEdge(1L, 3L);
        assertEquals(3, g.getNodeMap().size());
        assertEquals(2, g.getEdgeMap().size());
        assertEquals(3L, g.getNodeMap().get(3L).getId().longValue());
        assertEquals("1_3", g.getEdgeMap().get("1_3").getId());
        assertEquals(1L, g.getEdgeMap().get("1_3").getSource().getId().longValue());
        assertEquals(3L, g.getEdgeMap().get("1_3").getDest().getId().longValue());

        assertEquals(3, g.getNodeMap().get(1L).getInteractions().intValue());
        assertEquals(1, g.getNodeMap().get(3L).getInteractions().intValue());

        // one edge already exists
        g.addEdge(4L, 3L);
        assertEquals(4, g.getNodeMap().size());
        assertEquals(3, g.getEdgeMap().size());
        assertEquals(4L, g.getNodeMap().get(4L).getId().longValue());
        assertEquals("4_3", g.getEdgeMap().get("4_3").getId());
        assertEquals(4L, g.getEdgeMap().get("4_3").getSource().getId().longValue());
        assertEquals(3L, g.getEdgeMap().get("4_3").getDest().getId().longValue());

        assertEquals(1, g.getNodeMap().get(4L).getInteractions().intValue());
        assertEquals(2, g.getNodeMap().get(3L).getInteractions().intValue());
    }

}
