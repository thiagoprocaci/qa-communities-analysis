package com.tbp.repository

import com.tbp.model.Comment
import com.tbp.model.Community
import org.springframework.data.repository.CrudRepository


interface CommentRepository extends CrudRepository<Comment, Long> {

    Comment findByCommunityAndIdCommentCommunity(Community community, Long idCommentCommunity)

}