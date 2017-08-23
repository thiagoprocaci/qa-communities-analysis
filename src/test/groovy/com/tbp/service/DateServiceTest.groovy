package com.tbp.service

import com.tbp.etl.model.Community
import com.tbp.etl.repository.CommunityRepository
import com.tbp.period.repository.DateRepository
import com.tbp.period.service.DateService
import org.joda.time.DateTime
import org.joda.time.Interval
import org.mockito.Mockito


class DateServiceTest extends GroovyTestCase  {

    public static final String COMMUNITY = "community"
    DateService dateService;
    DateRepository dateRepository
    CommunityRepository communityRepository
    Community c


    void setUp() {
        c = new Community()
        c.id = 1
        dateService = new DateService()

        communityRepository = Mockito.mock(CommunityRepository.class)
        dateRepository = Mockito.mock(DateRepository.class)
        dateService.dateRepository = dateRepository
        dateService.communityRepository = communityRepository

        Mockito.when(communityRepository.findByName(COMMUNITY)).thenReturn(c)

    }

    void testGenerateIntervalNullDate() {
        assertNotNull(dateService.generateInterval(null, new Date()))
        assertNotNull(dateService.generateInterval(new Date(), null))
        assertNotNull(dateService.generateInterval(null, null))

        assertTrue(dateService.generateInterval(null, new Date()).size() == 0)
        assertTrue(dateService.generateInterval(new Date(), null).size() == 0)
        assertTrue(dateService.generateInterval(null, null).size() == 0)
    }

    void testGenerateIntervalUsingCommunity() {

        DateTime dateTime = DateTime.now().withMonthOfYear(1).withDayOfMonth(1)
                .withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                .withMillisOfSecond(0)
        int year = dateTime.getYear()
        Date min = dateTime.toDate()
        Date max = dateTime.plusYears(2).toDate()

        Mockito.when(dateRepository.getMaxCreationDateByCommunity(c.id)).thenReturn(max)
        Mockito.when(dateRepository.getMinCreationDateByCommunity(c.id)).thenReturn(min)

        List<Interval> intervalList = dateService.generateInterval(COMMUNITY)
        assertInterval(intervalList, year)
    }

    void testGenerateInterval() {
        DateTime dateTime = DateTime.now().withMonthOfYear(1).withDayOfMonth(1)
                        .withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                        .withMillisOfSecond(0)
        int year = dateTime.getYear()
        Date min = dateTime.toDate()
        Date max = dateTime.plusYears(2).toDate()

        List<Interval> intervalList = dateService.generateInterval(min, max)
        assertInterval(intervalList, year)
    }

    void assertInterval(List<Interval> intervalList, year) {
        assertNotNull(intervalList)
        assertEquals(24, intervalList.size());
        int month = 1
        for(int i = 0; i < intervalList.size(); i++) {
            assertEquals(month, intervalList.get(i).getStart().monthOfYear)
            assertEquals(1, intervalList.get(i).getStart().dayOfMonth)
            assertEquals(1, intervalList.get(i).getEnd().dayOfMonth)
            assertEquals(year, intervalList.get(i).getStart().year)
            if(month < 12) {
                assertEquals((month + 1), intervalList.get(i).getEnd().monthOfYear)
                assertEquals(year , intervalList.get(i).getEnd().year)
            } else {
                assertEquals(1, intervalList.get(i).getEnd().monthOfYear)
                assertEquals((year + 1), intervalList.get(i).getEnd().year)
            }
            month++
            if(month == 13) {
                month = 1
                year++
            }
        }
    }

}
