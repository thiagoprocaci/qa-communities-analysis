package com.tbp.repository;

import com.tbp.TestApplicationConfiguration;
import com.tbp.extractor.*;
import com.tbp.model.Community;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class DateRepositoryTest {

    @Autowired
    CommunityExtractor communityExtractor;
    @Autowired
    UserExtractor userExtractor;
    @Autowired
    PostExtractor postExtractor;
    @Autowired
    VoteExtractor voteExtractor;
    @Autowired
    CommentExtractor commentExtractor;
    @Autowired
    PostLinkExtractor postLinkExtractor;
    @Autowired
    DateRepository dateRepository;
    @Autowired
    CommunityRepository communityRepository;

    String community = "meta.3dprinting.stackexchange.com";

    @Before
    public void before() {
        communityExtractor.execute(community);
        userExtractor.execute(community);
        postExtractor.execute(community);
        voteExtractor.execute(community);
        commentExtractor.execute(community);
        postLinkExtractor.execute(community);
    }

    @Test
    public void testDate() {
        Community c = communityRepository.findByName(community);
        Date min = dateRepository.getMinCreationDateByCommunity(c.getId());
        Date max = dateRepository.getMaxCreationDateByCommunity(c.getId());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        assertEquals("2017-06-11 00:22:49", simpleDateFormat.format(max));
        assertEquals("2016-01-11 22:16:50", simpleDateFormat.format(min));

    }
}
