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
@Table(name = "vote",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_vote_comm", "id_community"])
)
class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @Column(name = "id_vote_comm")
    Long idVoteCommunity
    @Column(name = "id_post_comm")
    Long idPostCommunity
    @Column(name = "vote_type")
    Integer voteType
    @Column(name = "creation_date")
    Date creationDate
    @Column(name = "id_user_comm")
    Long idUserCommunity
    @Column(name = "bounty_amount")
    Integer bountyAmount

    @ManyToOne
    @JoinColumn(name = "id_community")
    Community community
    @ManyToOne
    @JoinColumn(name = "id_user")
    User user
    @ManyToOne
    @JoinColumn(name = "id_post")
    Post post



}
