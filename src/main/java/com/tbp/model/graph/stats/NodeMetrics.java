package com.tbp.model.graph.stats;


import com.tbp.model.graph.Node;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.Collection;

public class NodeMetrics {

        double[] betweennessList;
        Double q1Betweenness, medianBetweenness, q3Betweenness = 0d;

        double[] closenessList;
        Double q1Closeness, medianCloseness, q3Closeness = 0d;

        double[] eccentricityList;
        Double q1Eccentricity, medianEccentricity, q3Eccentricity = 0d;

        double[] harmonicClosenessList;
        Double q1HarmonicCloseness, medianHarmonicCloseness, q3HarmonicCloseness = 0d;

        double[] pageRankList;
        Double q1PageRank, medianPageRank, q3PageRank = 0d;

        double[] indegreeList;
        Double q1Indegree, medianIndegree, q3Indegree = 0d;

        double[] outdegreeList;
        Double q1Outdegree, medianOutdegree, q3Outdegree = 0d;

        double[] degreeList;
        Double q1Degree, medianDegree, q3Degree = 0d;

        double[] eigenvectorList;
        Double q1Eigenvector, medianEigenvector, q3Eigenvector = 0d;

        double[] interactionsList;
        Double q1Interactions, medianInteractions, q3Interactions = 0d;


       public NodeMetrics(Collection<Node> nodes) {
            init(nodes);
            int i = 0;
            for(Node n: nodes) {
                if(n.getBetweenness() != null) {
                    betweennessList[i] = n.getBetweenness();
                }
                if(n.getCloseness() != null) {
                    closenessList[i] = n.getCloseness();
                }
                if(n.getEccentricity() != null) {
                    eccentricityList[i] = n.getEccentricity();
                }
                if(n.getHarmonicCloseness() != null) {
                    harmonicClosenessList[i] = n.getHarmonicCloseness();
                }
                if(n.getPageRank() != null) {
                    pageRankList[i] = n.getPageRank();
                }
                if(n.getIndegree() != null) {
                    indegreeList[i] = n.getIndegree();
                }
                if(n.getOutdegree() != null) {
                    outdegreeList[i] = n.getOutdegree();
                }
                if(n.getDegree() != null) {
                    degreeList[i] = n.getDegree();
                }
                if(n.getEigenvector() != null) {
                    eigenvectorList[i] = n.getEigenvector();
                }
                if(n.getInteractions() != null) {
                    interactionsList[i] = n.getInteractions();
                }
                i++;
            }

           double[] percentiles = calcPercentile(betweennessList);
           q1Betweenness = percentiles[0];
           medianBetweenness = percentiles[1];
           q3Betweenness= percentiles[2];

           percentiles = calcPercentile(closenessList);
           q1Closeness = percentiles[0];
           medianCloseness = percentiles[1];
           q3Closeness = percentiles[2];

           percentiles = calcPercentile(eccentricityList);
           q1Eccentricity = percentiles[0];
           medianEccentricity = percentiles[1];
           q3Eccentricity = percentiles[2];

           percentiles = calcPercentile(harmonicClosenessList);
           q1HarmonicCloseness = percentiles[0];
           medianHarmonicCloseness = percentiles[1];
           q3HarmonicCloseness = percentiles[2];

           percentiles = calcPercentile(pageRankList);
           q1PageRank = percentiles[0];
           medianPageRank = percentiles[1];
           q3PageRank = percentiles[2];

           percentiles = calcPercentile(indegreeList);
           q1Indegree = percentiles[0];
           medianIndegree = percentiles[1];
           q3Indegree = percentiles[2];

           percentiles = calcPercentile(outdegreeList);
           q1Outdegree = percentiles[0];
           medianOutdegree = percentiles[1];
           q3Outdegree = percentiles[2];

           percentiles = calcPercentile(degreeList);
           q1Degree = percentiles[0];
           medianDegree = percentiles[1];
           q3Degree = percentiles[2];

           percentiles = calcPercentile(eigenvectorList);
           q1Eigenvector = percentiles[0];
           medianEigenvector = percentiles[1];
           q3Eigenvector = percentiles[2];

           percentiles = calcPercentile(interactionsList);
           q1Interactions = percentiles[0];
           medianInteractions = percentiles[1];
           q3Interactions = percentiles[2];
       }


        void init(Collection<Node> nodes) {
            betweennessList = new double[nodes.size()];
            closenessList = new double[nodes.size()];
            eccentricityList = new double[nodes.size()];
            harmonicClosenessList = new double[nodes.size()];
            pageRankList = new double[nodes.size()];
            indegreeList = new double[nodes.size()];
            outdegreeList = new double[nodes.size()];
            degreeList = new double[nodes.size()];
            eigenvectorList = new double[nodes.size()];
            interactionsList = new double[nodes.size()];
        }

        double[] calcPercentile(double[] values) {
            DescriptiveStatistics stats = new DescriptiveStatistics(values);
            Double q1 = stats.getPercentile(25);
            Double median = stats.getPercentile(50);
            Double q3 = stats.getPercentile(75);
            double[] percentiles = new double[3];
            percentiles[0] = q1;
            percentiles[1] = median;
            percentiles[2] = q3;
            return percentiles;
        }

    public Double getQ1Betweenness() {
        return q1Betweenness;
    }

    public Double getMedianBetweenness() {
        return medianBetweenness;
    }

    public Double getQ3Betweenness() {
        return q3Betweenness;
    }

    public Double getQ1Closeness() {
        return q1Closeness;
    }

    public Double getMedianCloseness() {
        return medianCloseness;
    }

    public Double getQ3Closeness() {
        return q3Closeness;
    }

    public Double getQ1Eccentricity() {
        return q1Eccentricity;
    }

    public Double getMedianEccentricity() {
        return medianEccentricity;
    }

    public Double getQ3Eccentricity() {
        return q3Eccentricity;
    }

    public Double getQ1HarmonicCloseness() {
        return q1HarmonicCloseness;
    }

    public Double getMedianHarmonicCloseness() {
        return medianHarmonicCloseness;
    }

    public Double getQ3HarmonicCloseness() {
        return q3HarmonicCloseness;
    }

    public Double getQ1PageRank() {
        return q1PageRank;
    }

    public Double getMedianPageRank() {
        return medianPageRank;
    }

    public Double getQ3PageRank() {
        return q3PageRank;
    }

    public Double getQ1Indegree() {
        return q1Indegree;
    }

    public Double getMedianIndegree() {
        return medianIndegree;
    }

    public Double getQ3Indegree() {
        return q3Indegree;
    }

    public Double getQ1Outdegree() {
        return q1Outdegree;
    }

    public Double getMedianOutdegree() {
        return medianOutdegree;
    }

    public Double getQ3Outdegree() {
        return q3Outdegree;
    }

    public Double getQ1Degree() {
        return q1Degree;
    }

    public Double getMedianDegree() {
        return medianDegree;
    }

    public Double getQ3Degree() {
        return q3Degree;
    }

    public Double getQ1Eigenvector() {
        return q1Eigenvector;
    }

    public Double getMedianEigenvector() {
        return medianEigenvector;
    }

    public Double getQ3Eigenvector() {
        return q3Eigenvector;
    }

    public Double getQ1Interactions() {
        return q1Interactions;
    }

    public Double getMedianInteractions() {
        return medianInteractions;
    }

    public Double getQ3Interactions() {
        return q3Interactions;
    }
}
