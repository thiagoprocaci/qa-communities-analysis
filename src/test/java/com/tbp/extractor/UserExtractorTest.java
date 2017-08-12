package com.tbp.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.repository.CommunityRepository;
import com.tbp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

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

    }
}
