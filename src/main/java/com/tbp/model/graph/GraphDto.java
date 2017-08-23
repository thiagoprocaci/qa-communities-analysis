package com.tbp.model.graph;

import com.tbp.model.Community;

import java.util.Collection;

public class GraphDto {

    Community community;
    Collection<Node> nodes;
    Collection<Edge> edges;


    public GraphDto(Graph graph, Community community) {
        nodes = graph.nodeMap.values();
        edges = graph.edgeMap.values();
        this.community = community;
    }

    public Community getCommunity() {
        return community;
    }

    public Collection<Node> getNodes() {
        return nodes;
    }

    public Collection<Edge> getEdges() {
        return edges;
    }




}
