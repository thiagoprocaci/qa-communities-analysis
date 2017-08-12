package com.tbp.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.model.Community;
import com.tbp.model.User;
import com.tbp.repository.CommunityRepository;
import com.tbp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class UserExtractorTest extends BaseExtractorTest {

    @Autowired
    UserExtractor userExtractor;
    @Autowired
    CommunityExtractor communityExtractor;
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    UserRepository userRepository;

    XmlReader xmlReader = new XmlReader();

    Random random = new Random();

    @Test
    public void execute() throws IOException {
        assertEquals("Users.xml", userExtractor.getFileName());
        assertTrue(communityRepository.count() == 0L);
        assertTrue(userRepository.count() == 0L);

        String community = "meta.3dprinting.stackexchange.com";
        communityExtractor.execute(community);
        userExtractor.execute(community);
        long count = getNumberOfRows(userExtractor.getFileName(), community);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(userRepository.count() == count);

        int loop = 0;

        while(loop < 5) {
            int lineNumber = random.nextInt((int) count);
            User userFromXml = xmlReader.getFromXml(community, userExtractor.getFileName(), lineNumber);
            Community c =communityRepository.findByName(community);
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
            loop++;
        }
    }
}
