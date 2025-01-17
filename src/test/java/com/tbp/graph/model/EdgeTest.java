package com.tbp.graph.model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgBothNull() {
        new Edge(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgFirstNull() {
        new Edge(null, new Vertex(1L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgSecondNull() {
        new Edge(new Vertex(1L), null);
    }

    @Test
    public void testConstructorSuccess() {
        Vertex n1 = new Vertex(1L);
        Vertex n2 = new Vertex(2L);
        Edge e = new Edge(n1, n2);
        assertEquals(n1.getId() + "_" + n2.getId(), e.getId());
        assertEquals(n1, e.getSource());
        assertEquals(n2, e.getDest());
        assertEquals(1, e.getWeight().intValue());
    }

    @Test
    public void testAddWeight() {
        Vertex n1 = new Vertex(1L);
        Vertex n2 = new Vertex(2L);
        Edge e = new Edge(n1, n2);
        assertEquals(1, e.getWeight().intValue());
        e.addWeight();
        assertEquals(2, e.getWeight().intValue());
        e.addWeight();
        assertEquals(3, e.getWeight().intValue());
    }

}
