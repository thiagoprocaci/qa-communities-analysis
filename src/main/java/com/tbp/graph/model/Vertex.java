package com.tbp.graph.model;


public class Vertex extends Identifiable<Long> {

    Long id;
    Double betweenness;
    Double closeness;
    Double eccentricity;
    Double harmonicCloseness;
    Double pageRank;
    Integer indegree;
    Integer outdegree;
    Integer degree;
    Double eigenvector;
    Integer modularity; // class
    Double clusteringCoefficient;
    Integer stronglyComponent;
    Integer weaklyComponent;
    Integer interactions = 0;
    String label;

    public Vertex(Long id) {
        this(id, null);
    }

    public Vertex(Long id, String label) {
        if(id == null) {
            throw new IllegalArgumentException("Id should not be null.");
        }
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Integer getStronglyComponent() {
        return stronglyComponent;
    }

    public void setStronglyComponent(Integer stronglyComponent) {
        this.stronglyComponent = stronglyComponent;
    }

    public Integer getWeaklyComponent() {
        return weaklyComponent;
    }

    public void setWeaklyComponent(Integer weaklyComponent) {
        this.weaklyComponent = weaklyComponent;
    }


    @Override
    public Long getId() {
        return id;
    }

    public Double getClusteringCoefficient() {
        return clusteringCoefficient;
    }

    public void setClusteringCoefficient(Double clusteringCoefficient) {
        this.clusteringCoefficient = clusteringCoefficient;
    }

    public Double getBetweenness() {
        return betweenness;
    }

    public Double getCloseness() {
        return closeness;
    }

    public Double getEccentricity() {
        return eccentricity;
    }

    public Double getHarmonicCloseness() {
        return harmonicCloseness;
    }

    public Double getPageRank() {
        return pageRank;
    }

    public Integer getIndegree() {
        return indegree;
    }

    public Integer getOutdegree() {
        return outdegree;
    }

    public Integer getDegree() {
        return degree;
    }

    public Double getEigenvector() {
        return eigenvector;
    }

    public void setBetweenness(Double betweenness) {
        this.betweenness = betweenness;
    }

    public void setCloseness(Double closeness) {
        this.closeness = closeness;
    }

    public void setEccentricity(Double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public void setHarmonicCloseness(Double harmonicCloseness) {
        this.harmonicCloseness = harmonicCloseness;
    }

    public void setPageRank(Double pageRank) {
        this.pageRank = pageRank;
    }

    public void setIndegree(Integer indegree) {
        this.indegree = indegree;
    }

    public void setOutdegree(Integer outdegree) {
        this.outdegree = outdegree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public void setEigenvector(Double eigenvector) {
        this.eigenvector = eigenvector;
    }

    public Integer getModularity() {
        return modularity;
    }

    public void setModularity(Integer modularity) {
        this.modularity = modularity;
    }

    public Integer getInteractions() {
        return interactions;
    }

    public void increaseInteractions() {
        interactions++;
    }


    public void setLabel(String label) {
        this.label = label;
    }
}
