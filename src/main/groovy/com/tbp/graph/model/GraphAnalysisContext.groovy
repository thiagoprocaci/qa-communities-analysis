package com.tbp.graph.model


import javax.persistence.*

@Entity
@Table(name = "graph_analysis_context",
        uniqueConstraints = @UniqueConstraint(columnNames=["period", "id_community"])
)
class GraphAnalysisContext {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @Column(name = "period")
    Integer period
    @Column(name = "id_community")
    Long idCommunity
    @Column(name = "nodes")
    Integer nodes
    @Column(name = "edges")
    Integer edges
    @Column(name = "density")
    Double density
    @Column(name = "diameter")
    Double diameter
    @Column(name = "radius")
    Double radius
    @Column(name = "avg_dist")
    Double avgDist
    @Column(name = "weakly_component_count")
    Integer weaklyComponentCount
    @Column(name = "strongly_component_count")
    Integer stronglyComponentCount
    @Column(name = "number_communities")
    Integer numberCommunities
    @Column(name = "modularity_with_resolution")
    Double modularityWithResolution
    @Column(name = "modularity")
    Double modularity
    @Column(name = "avg_degree")
    Double avgDegree;
    @Column(name = "avg_clustering_coef")
    Double avgClusteringCoef

    GraphAnalysisContext(Graph graph, Integer period, Long idCommunity) {
        this.period = period
        this.idCommunity = idCommunity
        this.nodes = graph.nodeMap.size()
        this.edges = graph.edgeMap.size()
        this.density = graph.getDensity()
        this.diameter = graph.getDiameter()
        this.radius = graph.getRadius()
        this.avgDist = graph.getAvgDist()
        this.weaklyComponentCount = graph.getWeaklyComponentCount()
        this.stronglyComponentCount = graph.getStronglyComponentCount()
        this.numberCommunities = graph.getNumberOfCommunity()
        this.modularityWithResolution = graph.getModularityWithResolution()
        this.modularity = graph.getModularity()
        this.avgDegree = graph.getAvgDegree()
        this.avgClusteringCoef = graph.getAvgClusteringCoef()
    }

    GraphAnalysisContext() {

    }
}
