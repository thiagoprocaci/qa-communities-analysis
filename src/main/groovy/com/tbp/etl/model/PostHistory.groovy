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
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "post_history",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_post_hist_comm", "id_community"],
                name = "post_hist_id_post_hist_comm_id_community")
)
class PostHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_hist_seq")
    @SequenceGenerator(name="post_hist_seq", sequenceName = "post_hist_seq", allocationSize = 1)
    Long id
    @Column(name = "id_post_hist_comm")
    Long idPostHistoryCommunity
    @Column(name = "id_post_comm")
    Long idPostCommunity
    @Column(name = "creation_date")
    Date creationDate
    @Column(name = "post_history_type")
    Integer type
    @Column(name = "text", columnDefinition = "TEXT")
    String text
    @Column(name = "revision_guid")
    String revisionGUID
    @Column(name = "user_display_name")
    String userDisplayName
    @Column(name = "close_Reason")
    Integer closeReason
    @Column(name = "user_comment", columnDefinition = "TEXT")
    String comment
    @Column(name = "id_user_community")
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
    Integer period;

}
