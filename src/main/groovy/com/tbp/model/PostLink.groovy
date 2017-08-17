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
@Table(name = "post_link",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_post_link_comm", "id_community"])
)
class PostLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @Column(name = "id_post_link_comm")
    Long idPostLinkCommunity
    @Column(name = "creation_date")
    Date creationDate
    @Column(name = "id_post_comm")
    Long idPostCommunity
    @Column(name = "id_related_post_comm")
    Long idRelatedPostCommunity
    @Column(name = "post_link_type")
    Integer postLinkType

    @ManyToOne
    @JoinColumn(name = "id_community")
    Community community

    @ManyToOne
    @JoinColumn(name = "id_post")
    Post post

    @ManyToOne
    @JoinColumn(name = "id_related_post")
    Post relatedPost

    @Column(name = "period")
    Integer period;


}
