package com.tbp.graph.model;


public class Edge extends Identifiable<String> {

    String id;
    Vertex source;
    Vertex dest;
    Integer weight;


    public Edge(Vertex s, Vertex d) {
        if(s == null || d == null) {
            throw  new IllegalArgumentException("Source and dest should not be null.");
        }
        source = s;
        dest = d;
        id = source.getId() + "_" + dest.getId();
        weight = 1;
    }

    public String getId() {
        return id;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDest() {
        return dest;
    }

    public Integer getWeight() {
        return weight;
    }

    public void addWeight() {
        weight++;
    }
}
