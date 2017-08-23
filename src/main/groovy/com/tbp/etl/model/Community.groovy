package com.tbp.etl.model

import groovy.transform.EqualsAndHashCode

import javax.persistence.*

@Entity
@Table(name = "community")
@EqualsAndHashCode
class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name", nullable = false, unique = true)
    String name;

}
