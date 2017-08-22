package com.tbp.repository

import org.joda.time.Interval
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

import java.text.SimpleDateFormat

@Component
class DateRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate

    private static String MIN_PERIOD_QUERY = "select min(A.period) as period from (" +
            " select min(t.period) as period FROM comment t where t.id_community = %1\$s " +
            " union all " +
            " select min(t.period) as period FROM post t where t.id_community = %1\$s  " +
            " union all " +
            " select min(t.period) as period FROM post_link t where t.id_community = %1\$s " +
            " union all " +
            " select min(t.period) as period FROM user t where t.id_community = %1\$s " +
            " union all " +
            " select min(t.period) as period FROM vote t where t.id_community = %1\$s " +
            ")A"

    private static String MAX_PERIOD_QUERY = "select max(A.period) as period from (" +
            " select max(t.period) as period FROM comment t where t.id_community = %1\$s " +
            " union all " +
            " select max(t.period) as period FROM post t where t.id_community = %1\$s  " +
            " union all " +
            " select max(t.period) as period FROM post_link t where t.id_community = %1\$s " +
            " union all " +
            " select max(t.period) as period FROM user t where t.id_community = %1\$s " +
            " union all " +
            " select max(t.period) as period FROM vote t where t.id_community = %1\$s " +
            ")A"



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

    Integer getMinPeriodByCommunity(Integer communityId) {
        if(communityId != null) {
            String sql = String.format(MIN_PERIOD_QUERY, communityId.toString())
            Integer i = jdbcTemplate.queryForObject(sql, Integer.class);
            return i
        }
        return null
    }

    Integer getMaxPeriodByCommunity(Integer communityId) {
        if(communityId != null) {
            String sql = String.format(MAX_PERIOD_QUERY, communityId.toString())
            Integer i = jdbcTemplate.queryForObject(sql, Integer.class);
            return i
        }
        return null
    }


    void updatePeriod(String communityName,  List<Interval> intervalList) {
        LOGGER.info("updating period of " + communityName)
        LOGGER.info("Interval size " + intervalList.size())
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] tables = ["comment", "post", "post_link", "user", "vote"]
        for(int i = 0; i < intervalList.size(); i++) {
            Interval interval = intervalList.get(i);
            Date start = interval.getStart().toDate();
            Date end = interval.getEnd().toDate();
            for(String table : tables) {
                String sql = String.format(UPDATE_PERIOD, table, i, format.format(start), format.format(end), communityName)
                LOGGER.debug(sql)
                jdbcTemplate.execute(sql)
            }
        }
    }

}
