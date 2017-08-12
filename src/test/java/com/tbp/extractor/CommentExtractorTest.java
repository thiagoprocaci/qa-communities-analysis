package com.tbp.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.model.Comment;
import com.tbp.model.Community;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class CommentExtractorTest extends BaseExtractorTest {
    @Test
    public void execute() throws IOException {
        assertEquals("Comments.xml", commentExtractor.getFileName());

        assertTrue(commentRepository.count() == 0L);

        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);

        commentExtractor.execute(communityName);
        long count = getNumberOfRows(commentExtractor.getFileName(), communityName);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(commentRepository.count() == count);


        int loop = 0;

        while(loop < 5) {
            int lineNumber = random.nextInt((int) count);
            Comment userFromXml = xmlReader.getCommentFromXml(communityName, commentExtractor.getFileName(), lineNumber);
            Community c = communityRepository.findByName(communityName);
            Comment userFromDataBase = commentRepository.findByCommunityAndIdCommentCommunity(c, userFromXml.getIdCommentCommunity() );
            assertNotNull(userFromDataBase);

            assertEquals(userFromXml.getIdCommentCommunity(), userFromDataBase.getIdCommentCommunity());
            assertEquals(userFromXml.getIdPostCommunity(), userFromDataBase.getIdPostCommunity());
            assertEquals(userFromXml.getCreationDate(), userFromDataBase.getCreationDate());
            assertEquals(userFromXml.getScore(), userFromDataBase.getScore());
            assertEquals(userFromXml.getText(), userFromDataBase.getText());
            assertEquals(userFromXml.getIdUserCommunity(), userFromDataBase.getIdUserCommunity());

            loop++;
        }
    }
    
}
