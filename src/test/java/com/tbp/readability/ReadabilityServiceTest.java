package com.tbp.readability;


import com.ipeirotis.readability.engine.ISentenceExtractor;
import com.ipeirotis.readability.engine.Readability;
import com.ipeirotis.readability.engine.SentenceExtractorStanfordNlp;
import com.ipeirotis.readability.enums.MetricType;
import com.itextpdf.awt.geom.CubicCurve2D;
import com.tbp.TestApplicationConfiguration;
import com.tbp.etl.extractor.BaseExtractorTest;
import com.tbp.etl.model.Comment;
import com.tbp.etl.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReadabilityServiceTest extends BaseExtractorTest {

    @Autowired
    ReadabilityService readabilityService;

    @Test
    public void testExecutePosts() {
        // setup
        assertEquals("Posts.xml", postExtractor.getFileName());
        assertEquals(0, postRepository.count());
        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);
        postExtractor.execute(communityName);

        readabilityService.execute(communityName);

        Iterable<Post> all = postRepository.findAll();
        int countBody = 0;
        int countTitle = 0;
        for(Post post: all) {
            if(post.getBody() != null) {
                countBody++;
                ISentenceExtractor sentenceExtractor = new SentenceExtractorStanfordNlp();
                Readability readability = new Readability(post.getBody(), sentenceExtractor);
                assertEquals(post.getAriText(), readability.getMetric(MetricType.ARI), 0.00001);
                assertEquals(post.getSmogText(), readability.getMetric(MetricType.SMOG), 0.00001);
                assertEquals(post.getFleschReadingText(), readability.getMetric(MetricType.FLESCH_READING), 0.00001);
                assertEquals(post.getFleschKincaidText(), readability.getMetric(MetricType.FLESCH_KINCAID), 0.00001);
                assertEquals(post.getGunningFogText(), readability.getMetric(MetricType.GUNNING_FOG), 0.00001);
                assertEquals(post.getColemanLiauText(), readability.getMetric(MetricType.COLEMAN_LIAU), 0.00001);
                assertEquals(post.getSmogIndexText(), readability.getMetric(MetricType.SMOG_INDEX), 0.00001);
                assertEquals(post.getCharactersText(), readability.getMetric(MetricType.CHARACTERS), 0.00001);
                assertEquals(post.getSyllablesText(), readability.getMetric(MetricType.SYLLABLES), 0.00001);
                assertEquals(post.getWordsText(), readability.getMetric(MetricType.WORDS), 0.00001);
                assertEquals(post.getComplexWordsText(), readability.getMetric(MetricType.COMPLEXWORDS), 0.00001);
                assertEquals(post.getSentencesText(), readability.getMetric(MetricType.SENTENCES), 0.00001);
            }
            if(post.getTitle() != null) {
                countTitle++;
                ISentenceExtractor sentenceExtractor = new SentenceExtractorStanfordNlp();
                Readability readability = new Readability(post.getTitle(), sentenceExtractor);
                assertEquals(post.getAriTitle(), readability.getMetric(MetricType.ARI), 0.00001);
                assertEquals(post.getSmogTitle(), readability.getMetric(MetricType.SMOG), 0.00001);
                assertEquals(post.getFleschReadingTitle(), readability.getMetric(MetricType.FLESCH_READING), 0.00001);
                assertEquals(post.getFleschKincaidTitle(), readability.getMetric(MetricType.FLESCH_KINCAID), 0.00001);
                assertEquals(post.getGunningFogTitle(), readability.getMetric(MetricType.GUNNING_FOG), 0.00001);
                assertEquals(post.getColemanLiauTitle(), readability.getMetric(MetricType.COLEMAN_LIAU), 0.00001);
                assertEquals(post.getSmogIndexTitle(), readability.getMetric(MetricType.SMOG_INDEX), 0.00001);
                assertEquals(post.getCharactersTitle(), readability.getMetric(MetricType.CHARACTERS), 0.00001);
                assertEquals(post.getSyllablesTitle(), readability.getMetric(MetricType.SYLLABLES), 0.00001);
                assertEquals(post.getWordsTitle(), readability.getMetric(MetricType.WORDS), 0.00001);
                assertEquals(post.getComplexWordsTitle(), readability.getMetric(MetricType.COMPLEXWORDS), 0.00001);
                assertEquals(post.getSentencesTitle(), readability.getMetric(MetricType.SENTENCES), 0.00001);
            }

        }
        assertTrue(countBody > 0);
        assertTrue(countTitle > 0);
    }


    @Test
    public void testExecuteComments() {
        // setup
        assertEquals("Comments.xml", commentExtractor.getFileName());
        assertEquals(0, commentRepository.count());
        communityExtractor.execute(communityName);
        assertTrue(communityRepository.findByName(communityName).getName() == communityName);
        commentExtractor.execute(communityName);

        readabilityService.execute(communityName);

        Iterable<Comment> all = commentRepository.findAll();
        int count = 0;
        for(Comment comment: all) {
            count++;
            ISentenceExtractor sentenceExtractor = new SentenceExtractorStanfordNlp();
            Readability readability = new Readability(comment.getText(), sentenceExtractor);
            assertEquals(comment.getAri(), readability.getMetric(MetricType.ARI), 0.00001);
            assertEquals(comment.getSmog(), readability.getMetric(MetricType.SMOG), 0.00001);
            assertEquals(comment.getFleschReading(), readability.getMetric(MetricType.FLESCH_READING), 0.00001);
            assertEquals(comment.getFleschKincaid(), readability.getMetric(MetricType.FLESCH_KINCAID), 0.00001);
            assertEquals(comment.getGunningFog(), readability.getMetric(MetricType.GUNNING_FOG), 0.00001);
            assertEquals(comment.getColemanLiau(), readability.getMetric(MetricType.COLEMAN_LIAU), 0.00001);
            assertEquals(comment.getSmogIndex(), readability.getMetric(MetricType.SMOG_INDEX), 0.00001);
            assertEquals(comment.getCharacters(), readability.getMetric(MetricType.CHARACTERS), 0.00001);
            assertEquals(comment.getSyllables(), readability.getMetric(MetricType.SYLLABLES), 0.00001);
            assertEquals(comment.getWords(), readability.getMetric(MetricType.WORDS), 0.00001);
            assertEquals(comment.getComplexWords(), readability.getMetric(MetricType.COMPLEXWORDS), 0.00001);
            assertEquals(comment.getSentences(), readability.getMetric(MetricType.SENTENCES), 0.00001);
        }
        assertTrue(count > 0);
    }
}
