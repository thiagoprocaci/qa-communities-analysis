package com.tbp.graph.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "graph_edge",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_user_source", "id_user_dest", "id_graph_analysis_context"])
)
class GraphEdge {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "graph_edge_seq")
    @SequenceGenerator(name="graph_edge_seq", sequenceName = "graph_edge_seq", allocationSize = 1)
    Long id
    @Column(name = "id_user_source")
    Long idUserSource
    @Column(name = "id_user_dest")
    Long idUserDest
    @Column(name = "weight")
    Integer weight
    @ManyToOne
    @JoinColumn(name = "id_graph_analysis_context")
    GraphAnalysisContext graphAnalysisContext
    @Column(name = "id_community")
    Integer idCommunity
    @ManyToOne
    @JoinColumn(name = "id_graph_node_source")
    GraphNode graphNodeSource
    @ManyToOne
    @JoinColumn(name = "id_graph_node_dest")
    GraphNode graphNodeDest

    GraphEdge(Edge edge, GraphAnalysisContext graphAnalysisContext,
              GraphNode graphNodeSource,  GraphNode graphNodeDest) {
        this.graphAnalysisContext = graphAnalysisContext
        this.graphNodeDest = graphNodeDest
        this.graphNodeSource = graphNodeSource
        this.idUserDest = edge.getDest().getId()
        this.idUserSource = edge.getSource().getId()
        this.idCommunity = graphAnalysisContext.idCommunity
        this.weight = edge.getWeight()
    }

    GraphEdge() {}

}
