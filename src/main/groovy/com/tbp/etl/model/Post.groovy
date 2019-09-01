package com.tbp.etl.model

import javax.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "post",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_post_comm", "id_community"],
        name = "post_id_post_comm_id_community")
)
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name="post_seq", sequenceName = "post_seq", allocationSize = 1)
    Long id
    @Column(name = "id_post_comm")
    Long idPostCommunity
    @Column(name = "creation_date")
    Date creationDate
    @Column(name = "post_type")
    Integer postType // 1 = question ; 2 = answer
    @Column(name = "parent_post_comm_id")
    Long parentPostCommunityId;
    @Column(name = "accepted_answer_comm_id")
    Long acceptedAnswerId
    @Column(name = "score")
    Integer score
    @Column(name = "view_count")
    Integer viewCount
    @Column(name = "body", columnDefinition = "TEXT")
    String body
    @Column(name = "id_user_community")
    Long idUserCommunity
    @Column(name = "last_editor_user_community_id")
    Long lastEditorUserCommunityId
    @Column(name = "last_editor_display_name")
    String lastEditorDisplayName
    @Column(name = "last_edit_date")
    Date lastEditDate
    @Column(name = "last_activity_date")
    Date lastActivityDate
    @Column(name = "community_owned_date")
    Date communityOwnedDate
    @Column(name = "closed_date")
    Date closedDate
    @Column(name = "title")
    String title
    @Column(name = "tags")
    String tags
    @Column(name = "answer_count")
    Integer answerCount
    @Column(name = "comment_count")
    Integer commentCount
    @Column(name = "favorite_count")
    Integer favoriteCount
    @ManyToOne
    @JoinColumn(name = "id_community")
    Community community
    @ManyToOne
    @JoinColumn(name = "id_user")
    User user
    @Column(name = "period")
    Integer period

    @Column(name = "ari_text")
    Double ariText
    @Column(name = "smog_text")
    Double smogText
    @Column(name = "flesch_reading_text")
    Double fleschReadingText
    @Column(name = "flesch_kincaid_text")
    Double fleschKincaidText
    @Column(name = "gunning_fog_text")
    Double gunningFogText
    @Column(name = "coleman_liau_text")
    Double colemanLiauText
    @Column(name = "smog_index_text")
    Double smogIndexText
    @Column(name = "characters_text")
    Double charactersText
    @Column(name = "syllables_text")
    Double syllablesText
    @Column(name = "words_text")
    Double wordsText
    @Column(name = "complexwords_text")
    Double complexWordsText
    @Column(name = "sentences_text")
    Double sentencesText

    @Column(name = "ari_title")
    Double ariTitle
    @Column(name = "smog_title")
    Double smogTitle
    @Column(name = "flesch_reading_title")
    Double fleschReadingTitle
    @Column(name = "flesch_kincaid_title")
    Double fleschKincaidTitle
    @Column(name = "gunning_fog_title")
    Double gunningFogTitle
    @Column(name = "coleman_liau_title")
    Double colemanLiauTitle
    @Column(name = "smog_index_title")
    Double smogIndexTitle
    @Column(name = "characters_title")
    Double charactersTitle
    @Column(name = "syllables_title")
    Double syllablesTitle
    @Column(name = "words_title")
    Double wordsTitle
    @Column(name = "complexwords_title")
    Double complexWordsTitle
    @Column(name = "sentences_title")
    Double sentencesTitle
    @Column(name = "sent_process_ok")
    String sentProcessOk

    static Timestamp getDateAsSql(Date d) {
        if(d != null) {
            return new Timestamp(d.time)
        }
        return null
    }

}
