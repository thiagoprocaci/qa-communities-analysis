package com.tbp.repository

import com.tbp.model.Community
import com.tbp.model.PostLink
import org.springframework.data.repository.CrudRepository


interface PostLinkRepository extends CrudRepository<PostLink, Long> {

    PostLink findByCommunityAndIdPostLinkCommunity(Community community, Long idPostLinkCommunity)
}