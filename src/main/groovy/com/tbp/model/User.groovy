package com.tbp.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@ToString
@Entity
@Table(name = "user")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @Column(name = "id_user_comm", nullable = false)
    Long idUserCommunity
    @Column(name = "reputation", nullable = false)
    Integer reputation;
    @Column(name = "creation_date", nullable = false)
    Date creationDate
    @Column(name = "display_name", nullable = false)
    String displayName
    @Column(name = "last_access_date", nullable = false)
    Date lastAccessDate
    @Column(name = "website_url")
    String websiteUrl;
    @Column(name = "location")
    String location
    @Column(name = "age")
    Integer age;
    @Column(name = "about_me")
    String aboutMe;
    @Column(name = "views", nullable = false)
    Integer views;
    @Column(name = "up_votes", nullable = false)
    Integer upVotes;
    @Column(name = "down_votes", nullable = false)
    Integer downVotes;

    @ManyToOne
    @JoinColumn(name = "id_community", nullable = false)
    Community community;

}
