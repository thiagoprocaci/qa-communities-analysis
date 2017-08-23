package com.tbp.model.graph;


import com.tbp.model.Community;


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



}
