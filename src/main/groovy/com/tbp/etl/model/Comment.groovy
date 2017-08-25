package com.tbp.etl.model

import javax.persistence.*

@Entity
@Table(name = "comment",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_comment_comm", "id_community"])
)
class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @Column(name = "id_comment_comm")
    Long idCommentCommunity
    @Column(name = "id_post_comm")
    Long idPostCommunity
    @Column(name = "score")
    Integer score
    @Column(name = "text", columnDefinition = "TEXT")
    String text
    @Column(name = "creation_date")
    Date creationDate
    @Column(name = "id_user_comm")
    Long idUserCommunity

    @ManyToOne
    @JoinColumn(name = "id_community")
    Community community
    @ManyToOne
    @JoinColumn(name = "id_user")
    User user
    @ManyToOne
    @JoinColumn(name = "id_post")
    Post post

    @Column(name = "period")
    Integer period

    @Column(name = "ari")
    Integer ari
    @Column(name = "smog")
    Double smog
    @Column(name = "flesch_reading")
    Double fleschReading
    @Column(name = "flesch_kincaid")
    Double fleschKincaid
    @Column(name = "gunning_fog")
    Double gunningFog
    @Column(name = "coleman_liau")
    Double colemanLiau;
    @Column(name = "smog_index")
    Double smogIndex
    @Column(name = "characters")
    Double characters
    @Column(name = "syllables")
    Double syllables
    @Column(name = "words")
    Double words
    @Column(name = "complexwords")
    Double complexWords
    @Column(name = "sentences")
    Double sentences


}
