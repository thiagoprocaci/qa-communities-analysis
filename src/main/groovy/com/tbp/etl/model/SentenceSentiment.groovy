package com.tbp.etl.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table


@Entity
@Table(name = "sentence_sentiment", schema = "public")
class SentenceSentiment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sentence_sentiment_seq")
    @SequenceGenerator(name="sentence_sentiment_seq", sequenceName = "sentence_sentiment_seq", allocationSize = 1)
    Long id
    @ManyToOne
    @JoinColumn(name = "id_post")
    Post post
    @Column(name = "sent_type")
    String sentType
    @Column(name = "very_positive_score")
    Double veryPositiveScore
    @Column(name = "positive_score")
    Double positiveScore
    @Column(name = "neutral_score")
    Double neutralScore
    @Column(name = "negative_score")
    Double negativeScore
    @Column(name = "very_negative_score")
    Double veryNegativeScore
    @Column(name = "sentence")
    String sentence


}
