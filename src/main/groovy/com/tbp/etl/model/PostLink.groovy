package com.tbp.etl.model

import javax.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "post_link",
        uniqueConstraints = @UniqueConstraint(columnNames=["id_post_link_comm", "id_community"],
        name = "post_link_id_post_link_comm_id_community")
)
class PostLink {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_link_seq")
    @SequenceGenerator(name="post_link_seq", sequenceName = "post_link_seq", allocationSize = 1)
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

    Timestamp getCreationDateAsSql() {
        if(creationDate != null) {
            return new Timestamp(creationDate.time)
        }
        return null
    }


}
