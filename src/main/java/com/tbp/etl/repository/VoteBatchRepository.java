package com.tbp.etl.repository;

import com.tbp.etl.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class VoteBatchRepository {

    private static final String SQL_INSERT = "insert into vote (id_vote_comm, id_post_comm, vote_type, creation_date, id_user_comm, bounty_amount, id_community, id_user, id_post ) values (?,?,?,?,?,?,?,?,?)";

    @Autowired
    JdbcTemplate jdbcTemplate;


    public void saveBatch(final List<Vote> userList) {
        final int batchSize = 500;
        for (int j = 0; j < userList.size(); j += batchSize) {
            final List<Vote> batchList = userList.subList(j, j + batchSize > userList.size() ? userList.size() : j + batchSize);
            jdbcTemplate.batchUpdate(SQL_INSERT,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i)
                                throws SQLException {
                            Vote vote = batchList.get(i);


                            ps.setObject(1, vote.getIdVoteCommunity());
                            ps.setObject(2, vote.getIdPostCommunity());
                            ps.setObject(3, vote.getVoteType());
                            ps.setTimestamp(4, vote.getCreationDateAsSql());
                            ps.setObject(5, vote.getIdUserCommunity());
                            ps.setObject(6, vote.getBountyAmount());
                            ps.setObject(7, vote.getCommunity().getId());
                            if(vote.getUser() == null) {
                                ps.setObject(8, null);
                            } else {
                                ps.setObject(8, vote.getUser().getId());
                            }
                            if(vote.getPost() == null) {
                                ps.setObject(9, null);
                            } else {
                                ps.setObject(9, vote.getPost().getId());
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
