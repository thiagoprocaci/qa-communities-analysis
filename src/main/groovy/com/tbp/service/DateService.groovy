package com.tbp.service

import com.tbp.model.Community
import com.tbp.repository.CommunityRepository
import com.tbp.repository.DateRepository
import org.joda.time.DateTime
import org.joda.time.Interval
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DateService {

    @Autowired
    DateRepository dateRepository
    @Autowired
    CommunityRepository communityRepository

    List<Interval> generateInterval(String communityName) {
        Community c = communityRepository.findByName(communityName)
        Date max = dateRepository.getMaxCreationDateByCommunity(c.id)
        Date min = dateRepository.getMinCreationDateByCommunity(c.id)
        return generateInterval(min, max)
    }

    List<Interval> generateInterval(Date min, Date max) {
        List<Interval> dateList = new ArrayList<Interval>();
        if(min != null && max != null) {
            DateTime minDate = new DateTime(min);
            DateTime maxDate = new DateTime(max);
            DateTime aux = new DateTime(minDate);
            while(aux.isBefore(maxDate)) {
                Date start = aux.toDate()
                aux = aux.plusMonths(1);
                Date end = aux.toDate()
                Interval interval = new Interval(start.getTime(), end.getTime());
                dateList.add(interval);
                aux = aux.plusSeconds(1);
            }
        }
        return dateList;
    }
}
