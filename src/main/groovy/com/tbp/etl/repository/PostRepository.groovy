package com.tbp.etl.repository

import com.tbp.etl.model.Community
import com.tbp.etl.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param


interface PostRepository extends CrudRepository<Post, Long> {

    Post findByCommunityAndIdPostCommunity(Community community, Long idPostCommunity)

    List<Post> findByCommunity(Community community)

    List<Post> findByCommunityAndPeriodLessThan(Community community, Integer period)

    Page<Post> findByCommunity(Community community, Pageable pageable);

    @Query(value = "select * from post p inner join community c on c.id = p.id_community where  c.\"name\" in (:communityName) and p.body is not null and (p.sent_process_ok is null or p.sent_process_ok in ('N'))  limit 1", nativeQuery = true)
    List<Post> findForSentimentAnalysis(@Param("communityName")  String communityName);

    @Query(value = "select count(1) from post p inner join community c on c.id = p.id_community where  c.\"name\" in (:communityName) and p.body is not null and (p.sent_process_ok is null or p.sent_process_ok in ('N'))", nativeQuery =  true)
    Long countRemaningSentimentAnalysis(@Param("communityName")  String communityName);

}
