package com.tbp.readability

import com.ipeirotis.readability.engine.ISentenceExtractor
import com.ipeirotis.readability.engine.Readability
import com.ipeirotis.readability.engine.SentenceExtractorStanfordNlp
import com.ipeirotis.readability.enums.MetricType
import com.tbp.etl.model.Comment
import com.tbp.etl.model.Community
import com.tbp.etl.model.Post
import com.tbp.etl.repository.CommentRepository
import com.tbp.etl.repository.CommunityRepository
import com.tbp.etl.repository.PostRepository

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class ReadabilityService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ReadabilityService.class);

    @Autowired
    CommunityRepository communityRepository
    @Autowired
    CommentRepository commentRepository
    @Autowired
    PostRepository postRepository


    void execute(String communityName) {
        LOGGER.info("Updating readability scores of " + communityName)
        Community community = communityRepository.findByName(communityName)
        if(community != null) {
            handlePages(postRepository, "post", community)
            handlePages(commentRepository, "comment", community)
        }
    }

    def handlePages(def repository, def entityName, def community) {
        PageRequest pageRequest = new PageRequest(0, 200)
        pageRequest = pageRequest.first()
        while (true) {
            Page page = repository.findByCommunity(community, pageRequest)
            LOGGER.info("Got $entityName page " + pageRequest.getPageNumber() + " of " + page.totalPages)
            pageRequest = pageRequest.next()
            if(page.totalPages < pageRequest.getPageNumber()) {
                break
            }
            page.getContent().each { c ->
                if(c instanceof Comment) {
                    handleComment(c)
                } else if (c instanceof  Post) {
                    handlePost(c)
                }
            }

            repository.save(page.getContent())
        }
    }

    def handleComment(Comment comment) {
        ISentenceExtractor sentenceExtractor = new SentenceExtractorStanfordNlp()
        Readability readability = new Readability(comment.text, sentenceExtractor)
        comment.ari = readability.getMetric(MetricType.ARI)
        comment.smog = readability.getMetric(MetricType.SMOG)
        comment.fleschReading = readability.getMetric(MetricType.FLESCH_READING)
        comment.fleschKincaid = readability.getMetric(MetricType.FLESCH_KINCAID)
        comment.gunningFog = readability.getMetric(MetricType.GUNNING_FOG)
        comment.colemanLiau = readability.getMetric(MetricType.COLEMAN_LIAU)
        comment.smogIndex = readability.getMetric(MetricType.SMOG_INDEX)
        comment.characters = readability.getMetric(MetricType.CHARACTERS)
        comment.syllables = readability.getMetric(MetricType.SYLLABLES)
        comment.words = readability.getMetric(MetricType.WORDS)
        comment.complexWords = readability.getMetric(MetricType.COMPLEXWORDS)
        comment.sentences = readability.getMetric(MetricType.SENTENCES)
    }

    def handlePost(Post post) {
        ISentenceExtractor sentenceExtractor = new SentenceExtractorStanfordNlp()
        if(StringUtils.hasText(post.getBody())) {
            Readability readability = new Readability(post.getBody(), sentenceExtractor)
            post.ariText = readability.getMetric(MetricType.ARI)
            post.smogText = readability.getMetric(MetricType.SMOG)
            post.fleschReadingText = readability.getMetric(MetricType.FLESCH_READING)
            post.fleschKincaidText = readability.getMetric(MetricType.FLESCH_KINCAID)
            post.gunningFogText = readability.getMetric(MetricType.GUNNING_FOG)
            post.colemanLiauText = readability.getMetric(MetricType.COLEMAN_LIAU)
            post.smogIndexText = readability.getMetric(MetricType.SMOG_INDEX)
            post.charactersText = readability.getMetric(MetricType.CHARACTERS)
            post.syllablesText = readability.getMetric(MetricType.SYLLABLES)
            post.wordsText = readability.getMetric(MetricType.WORDS)
            post.complexWordsText = readability.getMetric(MetricType.COMPLEXWORDS)
            post.sentencesText = readability.getMetric(MetricType.SENTENCES)
        }
        if(StringUtils.hasText(post.getTitle())) {
            Readability readability = new Readability(post.getTitle(), sentenceExtractor)
            post.ariTitle = readability.getMetric(MetricType.ARI)
            post.smogTitle = readability.getMetric(MetricType.SMOG)
            post.fleschReadingTitle = readability.getMetric(MetricType.FLESCH_READING)
            post.fleschKincaidTitle = readability.getMetric(MetricType.FLESCH_KINCAID)
            post.gunningFogTitle = readability.getMetric(MetricType.GUNNING_FOG)
            post.colemanLiauTitle = readability.getMetric(MetricType.COLEMAN_LIAU)
            post.smogIndexTitle = readability.getMetric(MetricType.SMOG_INDEX)
            post.charactersTitle = readability.getMetric(MetricType.CHARACTERS)
            post.syllablesTitle = readability.getMetric(MetricType.SYLLABLES)
            post.wordsTitle = readability.getMetric(MetricType.WORDS)
            post.complexWordsTitle = readability.getMetric(MetricType.COMPLEXWORDS)
            post.sentencesTitle = readability.getMetric(MetricType.SENTENCES)
        }
    }
}
