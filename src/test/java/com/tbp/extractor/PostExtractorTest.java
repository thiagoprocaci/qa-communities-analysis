package com.tbp.extractor;

import com.tbp.TestApplicationConfiguration;
import com.tbp.model.Community;
import com.tbp.model.Post;

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
public class PostExtractorTest extends BaseExtractorTest  {


    @Test
    public void execute() throws IOException {
        assertEquals("Posts.xml", postExtractor.getFileName());

        assertEquals(0, postRepository.count());

        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);
        postExtractor.execute(communityName);
        long count = getNumberOfRows(postExtractor.getFileName(), communityName);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(postRepository.count() == count);

        int loop = 0;

        while(loop < 5) {
            int lineNumber = random.nextInt((int) count);
            Post postFromXml = xmlReader.getPostFromXml(communityName, postExtractor.getFileName(), lineNumber);
            Community c = communityRepository.findByName(communityName);
            Post postFromDataBase = postRepository.findByCommunityAndIdPostCommunity(c, postFromXml.getIdPostCommunity() );
            assertNotNull(postFromDataBase);

            assertEquals(postFromXml.getIdPostCommunity(), postFromDataBase.getIdPostCommunity());
            assertEquals(postFromXml.getCreationDate(), postFromDataBase.getCreationDate());
            assertEquals(postFromXml.getAcceptedAnswerId(), postFromDataBase.getAcceptedAnswerId());
            assertEquals(postFromXml.getScore(), postFromDataBase.getScore());

            assertEquals(postFromXml.getViewCount(), postFromDataBase.getViewCount());
            assertEquals(postFromXml.getBody(), postFromDataBase.getBody());
            assertEquals(postFromXml.getIdUserCommunity(), postFromDataBase.getIdUserCommunity());
            assertEquals(postFromXml.getLastEditDate(), postFromDataBase.getLastEditDate());
            assertEquals(postFromXml.getLastEditorDisplayName(), postFromDataBase.getLastEditorDisplayName());
            assertEquals(postFromXml.getLastEditorUserCommunityId(), postFromDataBase.getLastEditorUserCommunityId());

            assertEquals(postFromXml.getLastActivityDate(), postFromDataBase.getLastActivityDate());
            assertEquals(postFromXml.getCommunityOwnedDate(), postFromDataBase.getCommunityOwnedDate());
            assertEquals(postFromXml.getClosedDate(), postFromDataBase.getClosedDate());
            assertEquals(postFromXml.getTitle(), postFromDataBase.getTitle());
            assertEquals(postFromXml.getTags(), postFromDataBase.getTags());
            assertEquals(postFromXml.getAnswerCount(), postFromDataBase.getAnswerCount());
            assertEquals(postFromXml.getCommentCount(), postFromDataBase.getCommentCount());
            assertEquals(postFromXml.getFavoriteCount(), postFromDataBase.getFavoriteCount());
            assertEquals(postFromXml.getPostType(), postFromDataBase.getPostType());
            assertEquals(postFromXml.getParentPostCommunityId(), postFromDataBase.getParentPostCommunityId());
            assertNull(postFromDataBase.getPeriod());
            loop++;

        }
    }

}
