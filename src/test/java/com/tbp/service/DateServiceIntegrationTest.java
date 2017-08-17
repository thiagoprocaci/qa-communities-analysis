package com.tbp.service;

import com.tbp.TestApplicationConfiguration;
import com.tbp.extractor.BaseExtractorTest;
import com.tbp.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DateServiceIntegrationTest extends BaseExtractorTest {

    @Autowired
    DateService dateService;

    @Test
    public void testUpdatePeriod() {
        communityExtractor.execute(communityName);
        userExtractor.execute(communityName);
        postExtractor.execute(communityName);
        voteExtractor.execute(communityName);
        commentExtractor.execute(communityName);
        postLinkExtractor.execute(communityName);

        dateService.updateCommunityPeriods(communityName);

        assertUser();
        assertPost();
        assertVote();
        assertComment();
        assertPostLink();
    }

    void assertPostLink() {
        List<PostLink> list = new ArrayList();
        list.addAll((Collection<? extends PostLink>) postLinkRepository.findAll());
        assertNotNull(list);
        assertTrue(list.size() > 0);
        for(PostLink u: list) {
            assertNotNull(u.getPeriod());
        }
    }


    void assertComment() {
        List<Comment> list = new ArrayList();
        list.addAll((Collection<? extends Comment>) commentRepository.findAll());
        assertNotNull(list);
        assertTrue(list.size() > 0);
        for(Comment u: list) {
            assertNotNull(u.getPeriod());
        }
    }

    void assertVote() {
        List<Vote> list = new ArrayList();
        list.addAll((Collection<? extends Vote>) voteRepository.findAll());
        assertNotNull(list);
        assertTrue(list.size() > 0);
        for(Vote u: list) {
            assertNotNull(u.getPeriod());
        }
    }

    void assertPost() {
        List<Post> list = new ArrayList();
        list.addAll((Collection<? extends Post>) postRepository.findAll());
        assertNotNull(list);
        assertTrue(list.size() > 0);
        for(Post u: list) {
            assertNotNull(u.getPeriod());
        }
    }

    void assertUser() {
        List<User> list = new ArrayList();
        list.addAll((Collection<? extends User>) userRepository.findAll());
        assertNotNull(list);
        assertTrue(list.size() > 0);
        for(User u: list) {
            assertNotNull(u.getPeriod());
        }
    }

}
