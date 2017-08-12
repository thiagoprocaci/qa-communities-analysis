package com.tbp.extractor;


import com.tbp.TestApplicationConfiguration;
import com.tbp.model.Community;
import com.tbp.model.Vote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class VoteExtractorTest extends BaseExtractorTest {

    @Test
    public void execute() throws IOException {
        assertEquals("Votes.xml", voteExtractor.getFileName());

        assertTrue(voteRepository.count() == 0L);

        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);

        voteExtractor.execute(communityName);
        long count = getNumberOfRows(voteExtractor.getFileName(), communityName);
        assertTrue(communityRepository.count() == 1L);
        assertTrue(voteRepository.count() == count);


        int loop = 0;

        while(loop < 5) {
            int lineNumber = random.nextInt((int) count);
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
            loop++;
        }
    }
    
}
