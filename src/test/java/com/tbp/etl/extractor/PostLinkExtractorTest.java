package com.tbp.etl.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.etl.model.Community;
import com.tbp.etl.model.PostLink;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostLinkExtractorTest extends BaseExtractorTest {

    @Test
    public void execute() throws IOException {
        validateInjection();
        assertEquals("PostLinks.xml", postLinkExtractor.getFileName());

        assertEquals(0, postLinkRepository.count());

        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);

        postLinkExtractor.execute(communityName);
        long count = getNumberOfRows(postLinkExtractor.getFileName(), communityName);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(postLinkRepository.count() == count);


        int loop = 0;

        while(loop < 5) {
            int lineNumber = 0;
            while(lineNumber <= 0) {
                lineNumber = random.nextInt((int) count);
            }

            PostLink postLinkFromXml = xmlReader.getPostLinkFromXml(communityName, postLinkExtractor.getFileName(), lineNumber);

            Community c = communityRepository.findByName(communityName);
            PostLink postLink = postLinkRepository.findByCommunityAndIdPostLinkCommunity(c, postLinkFromXml.getIdPostLinkCommunity() );
            assertNotNull(postLink);

            assertEquals(postLinkFromXml.getIdPostCommunity(), postLink.getIdPostCommunity());
            assertEquals(postLinkFromXml.getCreationDate(), postLink.getCreationDate());
            assertEquals(postLinkFromXml.getIdPostLinkCommunity(), postLink.getIdPostLinkCommunity());
            assertEquals(postLinkFromXml.getIdRelatedPostCommunity(), postLink.getIdRelatedPostCommunity());
            assertEquals(postLinkFromXml.getPostLinkType(), postLink.getPostLinkType());
            assertNull(postLink.getPeriod());

            loop++;
        }
    }
}
