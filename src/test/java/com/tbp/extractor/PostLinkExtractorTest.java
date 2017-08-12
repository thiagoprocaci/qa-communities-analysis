package com.tbp.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.model.Community;
import com.tbp.model.PostLink;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.*;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class PostLinkExtractorTest extends BaseExtractorTest {

    @Test
    public void execute() throws IOException {
        assertEquals("PostLinks.xml", postLinkExtractor.getFileName());

        assertTrue(postLinkRepository.count() == 0L);

        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);

        postLinkExtractor.execute(communityName);
        long count = getNumberOfRows(postLinkExtractor.getFileName(), communityName);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(postLinkRepository.count() == count);


        int loop = 0;

        while(loop < 5) {
            int lineNumber = random.nextInt((int) count);

            PostLink postLinkFromXml = xmlReader.getPostLinkFromXml(communityName, postLinkExtractor.getFileName(), lineNumber);
            System.out.println(lineNumber);
            Community c = communityRepository.findByName(communityName);
            PostLink postLink = postLinkRepository.findByCommunityAndIdPostLinkCommunity(c, postLinkFromXml.getIdPostLinkCommunity() );
            assertNotNull(postLink);

            assertEquals(postLinkFromXml.getIdPostCommunity(), postLink.getIdPostCommunity());
            assertEquals(postLinkFromXml.getCreationDate(), postLink.getCreationDate());
            assertEquals(postLinkFromXml.getIdPostLinkCommunity(), postLink.getIdPostLinkCommunity());
            assertEquals(postLinkFromXml.getIdRelatedPostCommunity(), postLink.getIdRelatedPostCommunity());
            assertEquals(postLinkFromXml.getPostLinkType(), postLink.getPostLinkType());

            loop++;
        }
    }
}
