package com.tbp.etl.repository;

import com.tbp.etl.model.SentenceSentiment
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

import javax.transaction.Transactional

interface SentenceSentimentRepository extends CrudRepository<SentenceSentiment, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from sentence_sentiment ss where ss.id in (select s.id from sentence_sentiment s inner join post p on p.id = s.id_post and (p.sent_process_ok  in ('N') or p.sent_process_ok is null))", nativeQuery = true)
    void deleteIncompleteAnalysis()

}
