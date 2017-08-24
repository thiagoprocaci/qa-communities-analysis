package com.tbp.graph.repository

import com.tbp.graph.model.GraphAnalysisContext
import org.springframework.data.repository.CrudRepository


interface GraphAnalysisContextRepository extends CrudRepository<GraphAnalysisContext, Long> {

    GraphAnalysisContext findByPeriodAndIdCommunity(Integer period, Integer idCommunity)

}