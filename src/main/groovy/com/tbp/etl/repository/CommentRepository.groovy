package com.tbp.etl.repository

import com.tbp.etl.model.Comment
import com.tbp.etl.model.Community
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository


interface CommentRepository extends CrudRepository<Comment, Long> {

    Comment findByCommunityAndIdCommentCommunity(Community community, Long idCommentCommunity)

    List<Comment> findByCommunity(Community community)

    List<Comment> findByCommunityAndPeriodLessThan(Community community, Integer period)

    Page<Comment> findByCommunity(Community community, Pageable pageable);
}