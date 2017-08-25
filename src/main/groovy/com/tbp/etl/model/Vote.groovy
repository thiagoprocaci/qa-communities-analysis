package com.tbp.etl.model

import javax.persistence.*
import java.sql.Timestamp

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

    @Column(name = "period")
    Integer period;


    Timestamp getCreationDateAsSql() {
        if(creationDate != null) {
            return new Timestamp(creationDate.time)
        }
        return null
    }


}
