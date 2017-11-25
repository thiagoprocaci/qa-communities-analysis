package com.tbp.etl.model

import groovy.transform.EqualsAndHashCode

import javax.persistence.*

@Entity
@Table(name = "community")
@EqualsAndHashCode
class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "community_seq")
    @SequenceGenerator(name="community_seq", sequenceName = "community_seq", allocationSize = 1)
    Integer id;
    @Column(name = "name", nullable = false, unique = true)
    String name;

}
