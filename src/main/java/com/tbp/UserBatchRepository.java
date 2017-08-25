package com.tbp;

import com.tbp.etl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserBatchRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    static final String SQL_INSERT = "insert into user (id_user_comm, reputation, creation_date, display_name, last_access_date, website_url, location, age, about_me, views, up_votes, down_votes, " +
            "id_community)\n" +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

    public void saveBatch(final List<User> userList) {
        final int batchSize = 500;
        for (int j = 0; j < userList.size(); j += batchSize) {
            final List<User> batchList = userList.subList(j, j + batchSize > userList.size() ? userList.size() : j + batchSize);
            jdbcTemplate.batchUpdate(SQL_INSERT,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i)
                                throws SQLException {
                            User user = batchList.get(i);
                            ps.setLong(1, user.getIdUserCommunity());
                            ps.setObject(2, user.getReputation());
                            ps.setTimestamp(3, user.getCreationDateAsSql());
                            ps.setString(4, user.getDisplayName());
                            ps.setTimestamp(5, user.getLastAccessDateAsSql());
                            ps.setString(6, user.getWebsiteUrl());
                            ps.setString(7, user.getLocation());
                            ps.setObject(8, user.getAge());
                            ps.setString(9, user.getAboutMe());
                            ps.setObject(10, user.getViews());
                            ps.setObject(11, user.getUpVotes());
                            ps.setObject(12, user.getDownVotes());
                            ps.setLong(13, user.getCommunity().getId());
                        }

                        @Override
                        public int getBatchSize() {
                            return batchList.size();
                        }
                    });
        }
    }

}
