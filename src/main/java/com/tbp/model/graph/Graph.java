package com.tbp.model.graph;


import java.util.HashMap;
import java.util.Map;


public class Graph {

    Map<Long, Node> nodeMap;
    Map<String, Edge> edgeMap;
    Double avgClusteringCoef;
    Double density;
    Double diameter;
    Double radius;
    Double avgDist; //Path Length
    Integer weaklyComponentCount;
    Integer stronglyComponentCount;
    Integer numberOfCommunity;
    Double avgDegree;


    public Graph() {
        nodeMap = new HashMap();
        edgeMap = new HashMap();
    }

    public Double getAvgDegree() {
        return avgDegree;
    }

    public void setAvgDegree(Double avgDegree) {
        this.avgDegree = avgDegree;
    }

    public Integer getNumberOfCommunity() {
        return numberOfCommunity;
    }

    public void setNumberOfCommunity(Integer numberOfCommunity) {
        this.numberOfCommunity = numberOfCommunity;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getAvgDist() {
        return avgDist;
    }

    public void setAvgDist(Double avgDist) {
        this.avgDist = avgDist;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Integer getWeaklyComponentCount() {
        return weaklyComponentCount;
    }

    public void setWeaklyComponentCount(Integer weaklyComponentCount) {
        this.weaklyComponentCount = weaklyComponentCount;
    }

    public Integer getStronglyComponentCount() {
        return stronglyComponentCount;
    }

    public void setStronglyComponentCount(Integer stronglyComponentCount) {
        this.stronglyComponentCount = stronglyComponentCount;
    }

    public Double getAvgClusteringCoef() {
        return avgClusteringCoef;
    }

    public void setAvgClusteringCoef(Double avgClusteringCoef) {
        this.avgClusteringCoef = avgClusteringCoef;
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
