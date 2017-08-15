package com.tbp.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DateRepository {

    @Autowired
    JdbcTemplate jdbcTemplate

    private static String MIN_CREATION_DATE_QUERY = "select min(A.min_date) as min_date from (" +
            " select min(t.creation_date) as min_date FROM comment t where t.id_community = %1\$s " +
            " union all " +
            " select min(t.creation_date) as min_date FROM post t where t.id_community = %1\$s  " +
            " union all " +
            " select min(t.creation_date) as min_date FROM post_link t where t.id_community = %1\$s " +
            " union all " +
            " select min(t.creation_date) as min_date FROM user t where t.id_community = %1\$s " +
            " union all " +
            " select min(t.creation_date) as min_date FROM vote t where t.id_community = %1\$s " +
            ")A"

    private static String MAX_CREATION_DATE_QUERY = "select max(A.max_date) as max_date from (" +
            " select max(t.creation_date) as max_date FROM comment t where t.id_community = %1\$s " +
            " union all " +
            " select max(t.creation_date) as max_date FROM post t where t.id_community = %1\$s  " +
            " union all " +
            " select max(t.creation_date) as max_date FROM post_link t where t.id_community = %1\$s " +
            " union all " +
            " select max(t.creation_date) as max_date FROM user t where t.id_community = %1\$s " +
            " union all " +
            " select max(t.creation_date) as max_date FROM vote t where t.id_community = %1\$s " +
            ")A"

    Date getMinCreationDateByCommunity(Integer communityId) {
        if(communityId != null) {
            String sql = String.format(MIN_CREATION_DATE_QUERY, communityId.toString())
            Date d = jdbcTemplate.queryForObject(sql, Date.class);
            return d
        }
        return null
    }

    Date getMaxCreationDateByCommunity(Integer communityId) {
        if(communityId != null) {
            String sql = String.format(MAX_CREATION_DATE_QUERY, communityId.toString())
            Date d = jdbcTemplate.queryForObject(sql, Date.class);
            return d
        }
        return null
    }

}
