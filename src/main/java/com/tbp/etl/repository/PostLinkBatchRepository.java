package com.tbp.etl.repository;

import com.tbp.etl.model.PostLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class PostLinkBatchRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    static final String SQL_INSERT = "insert into post_link (id_post_link_comm, creation_date, id_post_comm, id_related_post_comm, post_link_type, id_community, id_post, id_related_post ) values (?,?,?,?,?,?,?,?)";

    public void saveBatch(final List<PostLink> postLinkList) {
        final int batchSize = 500;
        for (int j = 0; j < postLinkList.size(); j += batchSize) {
            final List<PostLink> batchList = postLinkList.subList(j, j + batchSize > postLinkList.size() ? postLinkList.size() : j + batchSize);
            jdbcTemplate.batchUpdate(SQL_INSERT,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i)
                                throws SQLException {
                            PostLink postLink = batchList.get(i);
                            ps.setObject(1, postLink.getIdPostLinkCommunity());
                            ps.setTimestamp(2, postLink.getCreationDateAsSql());
                            ps.setObject(3, postLink.getIdPostCommunity());
                            ps.setObject(4, postLink.getIdRelatedPostCommunity());
                            ps.setObject(5, postLink.getPostLinkType());
                            ps.setObject(6, postLink.getCommunity().getId());
                            if(postLink.getPost() != null) {
                                ps.setObject(7, postLink.getPost().getId());
                            } else {
                                ps.setObject(7, null);
                            }
                            if(postLink.getRelatedPost() != null) {
                                ps.setObject(8, postLink.getRelatedPost().getId());
                            } else {
                                ps.setObject(8, null);
                            }
                        }

                        @Override
                        public int getBatchSize() {
                            return batchList.size();
                        }
                    });
        }
    }

}
