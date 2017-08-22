package com.tbp.model.graph;


import com.tbp.model.Identifiable;

public class Node extends Identifiable<Long> {

    static final String Q1 = "Bad";
    static final String Q1_MEDIAN = "Ok";
    static final String MEDIAN_Q3 = "Good";
    static final String Q3 = "Excellent";
    static final String BETWEENNESS = "betweenness";
    static final String CLOSENESS = "closeness";
    static final String ECCENTRICITY = "eccentricity";
    static final String HARMONIC_CLOSENESS = "harmonicCloseness";
    static final String PAGE_RANK = "pageRank";
    static final String INDEGREE = "indegree";
    static final String OUTDEGREE = "outdegree";
    static final String DEGREE = "degree";
    static final String EIGENVECTOR = "eigenvector";
    static final String INTERACTIONS = "interactions";
    static final String MODULARITY = "modularity";

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
    Integer modularity;
    Double clusteringCoefficient;
    Integer stronglyComponent;
    Integer weaklyComponent;


    // TODO remove...
    String betweennessDesc = Q1;
    String closenessDesc = Q1;
    String pageRankDesc = Q1;
    String indegreeDesc = Q1;
    String outdegreeDesc = Q1;
    String degreeDesc = Q1;
    String eigenvectorDesc = Q1;
    String eccentricityDesc = Q1;
    String harmonicClosenessDesc = Q1;
    String interactionsDesc = Q1;
    String label;

    Integer interactions = 0;


    public Node(Long id) {
        this(id, null);
    }

    public Node(Long id, String label) {
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

    public String getInteractionsDesc() {
        return interactionsDesc;
    }

    public String getBetweennessDesc() {
        return betweennessDesc;
    }

    public String getClosenessDesc() {
        return closenessDesc;
    }

    public String getEccentricityDesc() {
        return eccentricityDesc;
    }

    public String getHarmonicClosenessDesc() {
        return harmonicClosenessDesc;
    }

    public String getPageRankDesc() {
        return pageRankDesc;
    }

    public String getIndegreeDesc() {
        return indegreeDesc;
    }

    public String getOutdegreeDesc() {
        return outdegreeDesc;
    }

    public String getDegreeDesc() {
        return degreeDesc;
    }

    public String getEigenvectorDesc() {
        return eigenvectorDesc;
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

    void setMetricDescription(String metric, Double q1, Double median, Double q3) {
        String desc = Q1;
        Double value = getMetricValue(metric);
        if(value == null || value.equals(q1)) {
            desc = Q1;
        } else if(q1 < value && value < median) {
            desc = Q1_MEDIAN;
        } else if((median < value && value < q3) || median.equals(value)) {
            desc = MEDIAN_Q3;
        } else if((value > q3) || q3.equals(value)) {
            desc = Q3;
        }
        setMetricDescription(metric, desc);
    }

    void setMetricDescription(String metric, String desc) {
        switch (metric) {
            case BETWEENNESS:
                betweennessDesc = desc;
                break;
            case CLOSENESS:
                closenessDesc = desc;
                break;
            case ECCENTRICITY:
                eccentricityDesc = desc;
                break;
            case HARMONIC_CLOSENESS:
                harmonicClosenessDesc = desc;
                break;
            case PAGE_RANK:
                pageRankDesc = desc;
                break;
            case INDEGREE:
                indegreeDesc = desc;
                break;
            case OUTDEGREE:
                outdegreeDesc = desc;
                break;
            case DEGREE:
                degreeDesc = desc;
                break;
            case EIGENVECTOR:
                eigenvectorDesc = desc;
                break;
            case INTERACTIONS:
                interactionsDesc = desc;
                break;
        }
    }

    Double getMetricValue(String metric) {
        Double d = null;
        switch (metric) {
            case BETWEENNESS:
                d = betweenness;
                break;
            case CLOSENESS:
                d = closeness;
                break;
            case ECCENTRICITY:
                d = eccentricity;
                break;
            case HARMONIC_CLOSENESS:
                d = harmonicCloseness;
                break;
            case PAGE_RANK:
                d = pageRank;
                break;
            case INDEGREE:
                if(indegree != null) {
                    d = indegree.doubleValue();
                    break;
                }
            case OUTDEGREE:
                if(outdegree != null) {
                    d = outdegree.doubleValue();
                    break;
                }
            case DEGREE:
                if(degree != null) {
                    d = degree.doubleValue();
                    break;
                }
            case EIGENVECTOR:
                d = eigenvector;
                break;
            case INTERACTIONS:
                if(interactions != null) {
                    d = interactions.doubleValue();
                    break;
                }
        }
        return d;
    }

    public Integer getInteractions() {
        return interactions;
    }

    public void increaseInteractions() {
        interactions++;
    }
}
