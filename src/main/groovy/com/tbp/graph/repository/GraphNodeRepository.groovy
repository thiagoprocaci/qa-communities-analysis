package com.tbp.graph.repository

import com.tbp.graph.model.GraphAnalysisContext
import com.tbp.graph.model.GraphNode
import org.springframework.data.repository.CrudRepository


interface GraphNodeRepository extends CrudRepository<GraphNode, Long> {

    GraphNode findByGraphAnalysisContextAndIdUser(GraphAnalysisContext graphAnalysisContext, Long idUser)

}