package com.tbp.graph.repository

import com.tbp.graph.model.GraphAnalysisContext
import com.tbp.graph.model.GraphEdge
import org.springframework.data.repository.CrudRepository


interface GraphEdgeRepository extends CrudRepository<GraphEdge, Long> {

    GraphEdge findByGraphAnalysisContextAndIdUserSourceAndIdUserDest(GraphAnalysisContext graphAnalysisContext,
                                                                     Long idUserSource, Long idUserDest)


}