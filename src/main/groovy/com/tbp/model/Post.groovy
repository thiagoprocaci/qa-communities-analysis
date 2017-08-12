package com.tbp.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "post",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_post_comm", "id_community"])
)
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "ari")
    Integer ari


}
