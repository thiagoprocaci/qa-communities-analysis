package com.tbp.etl.repository;

import com.tbp.TestApplicationConfiguration;
import com.tbp.etl.extractor.CommunityExtractor;
import com.tbp.etl.extractor.PostExtractor;
import com.tbp.etl.model.Community;
import com.tbp.etl.model.Post;
import com.tbp.period.service.DateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostRepositoryTest {

    @Autowired
    CommunityExtractor communityExtractor;
    @Autowired
    PostExtractor postExtractor;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    DateService dateService;

    protected String communityName = "meta.3dprinting.stackexchange.com";

    @Test
    public void findByCommunityAndPeriodLessThan() {
        communityExtractor.execute(communityName);
        postExtractor.execute(communityName);
        dateService.updateCommunityPeriods(communityName);

        Iterable<Post> posts = postRepository.findAll();
        List<Long> selectedPostIdList = new ArrayList<>();
        Integer period = 4;
        assertNotNull(posts);
        for(Post p: posts) {
            if(p.getPeriod() < period) {
                selectedPostIdList.add(p.getId());
            }
        }
        assertFalse(selectedPostIdList.isEmpty());

        Community c = communityRepository.findByName(communityName);

        posts = postRepository.findByCommunityAndPeriodLessThan(c, period);
        List<Long> selectedPostIdList2 = new ArrayList<>();
        for(Post p: posts) {
            selectedPostIdList2.add(p.getId());
        }
        Collections.sort(selectedPostIdList);
        Collections.sort(selectedPostIdList2);

        assertTrue(selectedPostIdList.size() > 0);
        assertEquals(selectedPostIdList.size(), selectedPostIdList2.size());
        for(int i = 0; i < selectedPostIdList.size(); i++) {
            assertEquals(selectedPostIdList.get(i), selectedPostIdList2.get(i));
        }
    }

}
