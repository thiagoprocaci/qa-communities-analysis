package com.tbp.repository

import org.joda.time.Interval

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

import java.text.SimpleDateFormat

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

    private static String UPDATE_PERIOD = "update %s p  " +
            // " inner join community c on c.id = p.id_community " +
            " set p.period = %s " +
            " where (creation_date BETWEEN '%s' AND '%s') and " +
            " p.id_community in (select c.id from community c where c.name = '%s')" ;

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

    void updatePeriod(String communityName,  List<Interval> intervalList) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] tables = ["comment", "post", "post_link", "user", "vote"]
        for(int i = 0; i < intervalList.size(); i++) {
            Interval interval = intervalList.get(i);
            Date start = interval.getStart().toDate();
            Date end = interval.getEnd().toDate();
            for(String table : tables) {
                String sql = String.format(UPDATE_PERIOD, table, i, format.format(start), format.format(end), communityName)
                println sql
                jdbcTemplate.execute(sql)
            }
        }
    }

}
