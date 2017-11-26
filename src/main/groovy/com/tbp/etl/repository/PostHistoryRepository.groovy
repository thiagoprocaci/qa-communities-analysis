package com.tbp.etl.repository

import com.tbp.etl.model.Community
import com.tbp.etl.model.PostHistory
import org.springframework.data.repository.CrudRepository


interface PostHistoryRepository extends CrudRepository<PostHistory, Long> {

    PostHistory findByCommunityAndIdPostHistoryCommunity(Community community, Long idPostHistoryCommunity)

}