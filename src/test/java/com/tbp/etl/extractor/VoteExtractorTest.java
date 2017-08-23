package com.tbp.etl.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.etl.model.Community;
import com.tbp.etl.model.Vote;
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
public class VoteExtractorTest extends BaseExtractorTest {

    @Test
    public void execute() throws IOException {
        validateInjection();
        assertEquals("Votes.xml", voteExtractor.getFileName());

        assertEquals(0, voteRepository.count());

        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);

        voteExtractor.execute(communityName);
        long count = getNumberOfRows(voteExtractor.getFileName(), communityName);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(voteRepository.count() == count);


        int loop = 0;

        while(loop < 5) {
            int lineNumber = 0;
            while(lineNumber <= 0) {
                lineNumber = random.nextInt((int) count);
            }
            Vote voteFromXml = xmlReader.getVoteFromXml(communityName, voteExtractor.getFileName(), lineNumber);
            Community c = communityRepository.findByName(communityName);
            Vote voteFromDataBase = voteRepository.findByCommunityAndIdVoteCommunity(c, voteFromXml.getIdVoteCommunity() );
            assertNotNull(voteFromDataBase);

            assertEquals(voteFromXml.getIdVoteCommunity(), voteFromDataBase.getIdVoteCommunity());
            assertEquals(voteFromXml.getIdPostCommunity(), voteFromDataBase.getIdPostCommunity());
            assertEquals(voteFromXml.getCreationDate(), voteFromDataBase.getCreationDate());
            assertEquals(voteFromXml.getVoteType(), voteFromDataBase.getVoteType());
            assertEquals(voteFromXml.getIdUserCommunity(), voteFromDataBase.getIdUserCommunity());
            assertEquals(voteFromXml.getBountyAmount(), voteFromDataBase.getBountyAmount());
            assertNull(voteFromDataBase.getPeriod());
            loop++;
        }
    }
    
}
