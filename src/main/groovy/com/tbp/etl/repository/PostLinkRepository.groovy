package com.tbp.etl.repository

import com.tbp.etl.model.Community
import com.tbp.etl.model.PostLink
import org.springframework.data.repository.CrudRepository


interface PostLinkRepository extends CrudRepository<PostLink, Long> {

    PostLink findByCommunityAndIdPostLinkCommunity(Community community, Long idPostLinkCommunity)
}