package com.tbp.etl.repository

import com.tbp.etl.model.Community
import com.tbp.etl.model.Post
import org.springframework.data.repository.CrudRepository


interface PostRepository extends CrudRepository<Post, Long> {

    Post findByCommunityAndIdPostCommunity(Community community, Long idPostCommunity)

    List<Post> findByCommunity(Community community)

    List<Post> findByCommunityAndPeriodLessThan(Community community, Integer period)

}