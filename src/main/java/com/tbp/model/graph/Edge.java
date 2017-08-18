package com.tbp.model.graph;


import com.tbp.model.Identifiable;

public class Edge extends Identifiable<String> {

    String id;
    Node source;
    Node dest;
    Integer weight;


    public Edge(Node s, Node d) {
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

    public Node getSource() {
        return source;
    }

    public Node getDest() {
        return dest;
    }

    public Integer getWeight() {
        return weight;
    }

    public void addWeight() {
        weight++;
    }
}
