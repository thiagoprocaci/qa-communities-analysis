package com.tbp.etl.model

import groovy.transform.ToString

import javax.persistence.*
import java.sql.Timestamp

@ToString
@Entity
@Table(name = "comm_user",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_user_comm", "id_community"],
                name = "user_id_user_comm_id_community")
)
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name="user_seq", sequenceName = "user_seq", allocationSize = 1)
    Long id
    @Column(name = "id_user_comm", nullable = false)
    Long idUserCommunity
    @Column(name = "reputation", nullable = false)
    Integer reputation
    @Column(name = "creation_date", nullable = false)
    Date creationDate
    @Column(name = "display_name", nullable = false)
    String displayName
    @Column(name = "last_access_date", nullable = false)
    Date lastAccessDate
    @Column(name = "website_url")
    String websiteUrl
    @Column(name = "location")
    String location
    @Column(name = "age")
    Integer age
    @Column(name = "about_me", columnDefinition = "TEXT")
    String aboutMe
    @Column(name = "views", nullable = false)
    Integer views
    @Column(name = "up_votes", nullable = false)
    Integer upVotes
    @Column(name = "down_votes", nullable = false)
    Integer downVotes

    @ManyToOne
    @JoinColumn(name = "id_community", nullable = false)
    Community community

    @Column(name = "period")
    Integer period

    Timestamp getCreationDateAsSql() {
        if(creationDate != null) {
            return new Timestamp(creationDate.time)
        }
        return null
    }

    Timestamp getLastAccessDateAsSql() {
        if(lastAccessDate != null) {
            return new Timestamp(lastAccessDate.time)
        }
        return null
    }

}
