package com.tbp.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.model.Comment;
import com.tbp.model.Community;
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
public class CommentExtractorTest extends BaseExtractorTest {
    @Test
    public void execute() throws IOException {
        assertEquals("Comments.xml", commentExtractor.getFileName());

        assertEquals(0, commentRepository.count());

        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);

        commentExtractor.execute(communityName);
        long count = getNumberOfRows(commentExtractor.getFileName(), communityName);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(commentRepository.count() == count);


        int loop = 0;

        while(loop < 5) {
            int lineNumber = random.nextInt((int) count);
            Comment commentFromXml = xmlReader.getCommentFromXml(communityName, commentExtractor.getFileName(), lineNumber);
            Community c = communityRepository.findByName(communityName);
            Comment commentFromDataBase = commentRepository.findByCommunityAndIdCommentCommunity(c, commentFromXml.getIdCommentCommunity() );
            assertNotNull(commentFromDataBase);

            assertEquals(commentFromXml.getIdCommentCommunity(), commentFromDataBase.getIdCommentCommunity());
            assertEquals(commentFromXml.getIdPostCommunity(), commentFromDataBase.getIdPostCommunity());
            assertEquals(commentFromXml.getCreationDate(), commentFromDataBase.getCreationDate());
            assertEquals(commentFromXml.getScore(), commentFromDataBase.getScore());
            assertEquals(commentFromXml.getText(), commentFromDataBase.getText());
            assertEquals(commentFromXml.getIdUserCommunity(), commentFromDataBase.getIdUserCommunity());
            assertNull(commentFromDataBase.getPeriod());

            loop++;
        }
    }
    
}
