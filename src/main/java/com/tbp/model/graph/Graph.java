package com.tbp.model.graph;


import java.util.HashMap;
import java.util.Map;


public class Graph {

    Map<Long, Node> nodeMap;
    Map<String, Edge> edgeMap;


    public Graph() {
        nodeMap = new HashMap();
        edgeMap = new HashMap();
    }

    public void addEdge(Long sourceId, Long destId) {
        addEdge(sourceId, null, destId, null);
    }

    public void addEdge(Long sourceId, String labelSource, Long destId, String labelDest) {
        Node source = new Node(sourceId, labelSource);
        Node dest = new Node(destId, labelDest);
        if(!nodeMap.containsKey(source.getId())) {
            nodeMap.put(source.getId(), source);
        } else {
            source = nodeMap.get(sourceId);
        }
        if(!nodeMap.containsKey(dest.getId())) {
            nodeMap.put(dest.getId(), dest);
        } else {
            dest = nodeMap.get(destId);
        }
        Edge e = new Edge(source, dest);
        if(edgeMap.containsKey(e.getId())) {
            edgeMap.get(e.getId()).addWeight();
        } else {
            edgeMap.put(e.getId(), e);
        }
        source.increaseInteractions();
        dest.increaseInteractions();
    }

    public Map<Long, Node> getNodeMap() {
        return nodeMap;
    }

    public Map<String, Edge> getEdgeMap() {
        return edgeMap;
    }
}
