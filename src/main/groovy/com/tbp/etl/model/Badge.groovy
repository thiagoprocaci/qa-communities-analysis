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
@Table(name = "badge",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_badge_comm", "id_community"],
                name = "badge_id_badge_comm_id_community")
)
class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "badge_seq")
    @SequenceGenerator(name="badge_seq", sequenceName = "badge_seq", allocationSize = 1)
    Long id
    @Column(name = "id_badge_comm")
    Long idBadgeCommunity
    @Column(name = "id_user_community")
    Long idUserCommunity
    @Column(name = "name")
    String name
    @Column(name = "date")
    Date date
    @ManyToOne
    @JoinColumn(name = "id_community")
    Community community
    @ManyToOne
    @JoinColumn(name = "id_user")
    User user
    @Column(name = "class")
    String clazz
    @Column(name = "tag_based")
    String tagBased

}

