package com.tbp.etl.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.etl.model.Community;
import com.tbp.etl.model.PostHistory;

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
public class PostHistoryExtractorTest extends BaseExtractorTest {
    @Test
    public void execute() throws IOException {
        validateInjection();
        assertEquals("PostHistory.xml", postHistoryExtractor.getFileName());

        assertEquals(0, postHistoryRepository.count());

        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);

        postHistoryExtractor.execute(communityName);
        long count = getNumberOfRows(postHistoryExtractor.getFileName(), communityName);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(postHistoryRepository.count() == count);


        int loop = 0;

        while(loop < 5) {
            int lineNumber = 0;
            while(lineNumber <= 0) {
                lineNumber = random.nextInt((int) count);
            }

            PostHistory postHistoryFromXml = xmlReader.getPostHistoryFromXml(communityName, postHistoryExtractor.getFileName(), lineNumber);

            Community c = communityRepository.findByName(communityName);
            PostHistory postHistory = postHistoryRepository.findByCommunityAndIdPostHistoryCommunity(c, postHistoryFromXml.getIdPostHistoryCommunity() );
            assertNotNull(postHistory);
            assertEquals(postHistoryFromXml.getIdPostHistoryCommunity(), postHistory.getIdPostHistoryCommunity());
            assertEquals(postHistoryFromXml.getCreationDate(), postHistory.getCreationDate());
            assertEquals(postHistoryFromXml.getType(), postHistory.getType());
            assertEquals(postHistoryFromXml.getIdPostCommunity(), postHistory.getIdPostCommunity());
            assertEquals(postHistoryFromXml.getRevisionGUID(), postHistory.getRevisionGUID());
            assertEquals(postHistoryFromXml.getUserDisplayName(), postHistory.getUserDisplayName());
            assertEquals(postHistoryFromXml.getComment(), postHistory.getComment());
            assertEquals(postHistoryFromXml.getText(), postHistory.getText());
            assertEquals(postHistoryFromXml.getCloseReason(), postHistory.getCloseReason());
            assertEquals(postHistoryFromXml.getIdUserCommunity(), postHistory.getIdUserCommunity());
            loop++;
        }
    }

}
