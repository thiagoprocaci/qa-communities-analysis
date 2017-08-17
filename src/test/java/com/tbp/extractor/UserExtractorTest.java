package com.tbp.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.model.Community;
import com.tbp.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserExtractorTest extends BaseExtractorTest {

    @Test
    public void execute() throws IOException {
        assertEquals("Users.xml", userExtractor.getFileName());

        assertEquals(0, userRepository.count());

        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);

        userExtractor.execute(communityName);
        long count = getNumberOfRows(userExtractor.getFileName(), communityName);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(userRepository.count() == count);


        int loop = 0;

        while(loop < 5) {
            int lineNumber = random.nextInt((int) count);
            User userFromXml = xmlReader.getUserFromXml(communityName, userExtractor.getFileName(), lineNumber);
            Community c =communityRepository.findByName(communityName);
            User userFromDataBase = userRepository.findByCommunityAndIdUserCommunity(c, userFromXml.getIdUserCommunity() );
            assertNotNull(userFromDataBase);

            assertEquals(userFromXml.getAboutMe(), userFromDataBase.getAboutMe());
            assertEquals(userFromXml.getAge(), userFromDataBase.getAge());
            assertEquals(userFromXml.getReputation(), userFromDataBase.getReputation());
            assertEquals(userFromXml.getCreationDate(), userFromDataBase.getCreationDate());
            assertEquals(userFromXml.getDisplayName(), userFromDataBase.getDisplayName());
            assertEquals(userFromXml.getLastAccessDate(), userFromDataBase.getLastAccessDate());
            assertEquals(userFromXml.getWebsiteUrl(), userFromDataBase.getWebsiteUrl());
            assertEquals(userFromXml.getLocation(), userFromDataBase.getLocation());
            assertEquals(userFromXml.getViews(), userFromDataBase.getViews());

            assertEquals(userFromXml.getUpVotes(), userFromDataBase.getUpVotes());
            assertEquals(userFromXml.getDownVotes(), userFromDataBase.getDownVotes());
            assertNull(userFromDataBase.getPeriod());
            loop++;
        }
    }
}
