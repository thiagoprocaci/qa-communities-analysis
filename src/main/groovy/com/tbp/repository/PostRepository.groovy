package com.tbp.repository

import com.tbp.model.Community
import com.tbp.model.Post
import org.springframework.data.repository.CrudRepository


interface PostRepository extends CrudRepository<Post, Long> {

    Post findByCommunityAndIdPostCommunity(Community community, Long idPostCommunity)

    List<Post> findByCommunity(Community community)

}