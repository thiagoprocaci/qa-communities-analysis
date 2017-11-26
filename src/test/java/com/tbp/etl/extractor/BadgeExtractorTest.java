package com.tbp.etl.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.etl.model.Badge;
import com.tbp.etl.model.Community;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BadgeExtractorTest extends BaseExtractorTest {

    @Test
    public void execute() throws IOException {
        validateInjection();
        assertEquals("Badges.xml", badgeExtractor.getFileName());

        assertEquals(0, badgeRepository.count());

        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);

        badgeExtractor.execute(communityName);
        long count = getNumberOfRows(badgeExtractor.getFileName(), communityName);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(badgeRepository.count() == count);


        int loop = 0;

        while(loop < 5) {
            int lineNumber = 0;
            while(lineNumber <= 0) {
                lineNumber = random.nextInt((int) count);
            }

            Badge badgeFromXml = xmlReader.getBadgeFromXml(communityName, badgeExtractor.getFileName(), lineNumber);

            Community c = communityRepository.findByName(communityName);
            Badge badgeHistory = badgeRepository.findByCommunityAndIdBadgeCommunity(c, badgeFromXml.getIdBadgeCommunity());
            assertNotNull(badgeHistory);
            assertEquals(badgeFromXml.getIdBadgeCommunity(), badgeHistory.getIdBadgeCommunity());
            assertEquals(badgeFromXml.getDate(), badgeHistory.getDate());
            assertEquals(badgeFromXml.getName(), badgeHistory.getName());
            assertEquals(badgeFromXml.getClazz(), badgeHistory.getClazz());
            assertEquals(badgeFromXml.getTagBased(), badgeHistory.getTagBased());
            assertEquals(badgeFromXml.getIdUserCommunity(), badgeHistory.getIdUserCommunity());
            loop++;
        }
    }
    
    
}
