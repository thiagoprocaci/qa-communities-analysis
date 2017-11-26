package com.tbp.etl.repository

import com.tbp.etl.model.Badge
import com.tbp.etl.model.Community
import org.springframework.data.repository.CrudRepository


interface BadgeRepository extends CrudRepository<Badge, Long> {

    Badge findByCommunityAndIdBadgeCommunity(Community community, Long idBadgeCommunity)

}