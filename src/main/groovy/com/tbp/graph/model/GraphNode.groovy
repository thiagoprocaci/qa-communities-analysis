package com.tbp.graph.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "graph_node",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_graph_analysis_context", "id_user"])
)
class GraphNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @Column(name = "betweenness")
    Double betweenness
    @Column(name = "closeness")
    Double closeness
    @Column(name = "eccentricity")
    Double eccentricity
    @Column(name = "harmonic_closeness")
    Double harmonicCloseness
    @Column(name = "page_rank")
    Double pageRank
    @Column(name = "indegree")
    Integer indegree
    @Column(name = "outdegree")
    Integer outdegree
    @Column(name = "degree")
    Integer degree
    @Column(name = "eigenvector")
    Double eigenvector
    @Column(name = "modularity_class")
    Integer modularityClass
    @Column(name = "clustering_coefficient")
    Double clusteringCoefficient
    @Column(name = "strongly_component")
    Integer stronglyComponent
    @Column(name = "weakly_component")
    Integer weaklyComponent
    @Column(name = "interactions")
    Integer interactions
    @ManyToOne
    @JoinColumn(name = "id_graph_analysis_context")
    GraphAnalysisContext graphAnalysisContext
    @Column(name = "id_user")
    Long idUser
    @Column(name = "id_community")
    Long idCommunity

    GraphNode(Node node, GraphAnalysisContext graphAnalysisContext) {
        this.graphAnalysisContext = graphAnalysisContext
        this.idUser = node.getId()
        this.idCommunity = graphAnalysisContext.idCommunity
        this.betweenness = node.getBetweenness()
        this.closeness = node.getCloseness()
        this.eccentricity = node.getEccentricity()
        this.harmonicCloseness = node.getHarmonicCloseness()
        this.indegree = node.getIndegree()
        this.outdegree = node.getOutdegree()
        this.degree = node.getDegree()
        this.pageRank = node.getPageRank()
        this.eigenvector = node.getEigenvector()
        this.modularityClass = node.getModularity()
        this.clusteringCoefficient = node.getClusteringCoefficient()
        this.stronglyComponent = node.getStronglyComponent()
        this.weaklyComponent = node.getWeaklyComponent()
        this.interactions = node.getInteractions()
    }

    GraphNode() {}

}
