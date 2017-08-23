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
    Integer idCommunity;
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
    Double avgClusterionCoef


}
