package com.tbp.sentiment;


import com.tbp.etl.model.Post;
import com.tbp.etl.model.SentenceSentiment;
import com.tbp.etl.repository.PostRepository;
import com.tbp.etl.repository.SentenceSentimentRepository;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.ejml.simple.SimpleMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Service
public class SentimentAnalysisService {

    static final Logger LOGGER = LoggerFactory.getLogger(SentimentAnalysisService.class);

    @Autowired
    PostRepository postRepository;
    @Autowired
    SentenceSentimentRepository sentenceSentimentRepository;

    /*
	 * "Very negative" = 0 "Negative" = 1 "Neutral" = 2 "Positive" = 3
	 * "Very positive" = 4
	 */
    static Properties props;
    static StanfordCoreNLP pipeline;

   public SentimentAnalysisService() {
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and sentiment
        props = new Properties();
        //  props.setProperty("annotators", "tokenize, ssplit, parse, sentiment")
        props.setProperty("annotators", "tokenize,ssplit,parse,sentiment");
        props.setProperty("parse.maxlen", "45");
        pipeline = new StanfordCoreNLP(props);
    }

    public void makeAnalysis(String community) {
        if (community != null) {
            LOGGER.info("Starting sentiment analysis. Community : {}", community);
            sentenceSentimentRepository.deleteIncompleteAnalysis();
            Long count = postRepository.countRemaningSentimentAnalysis(community);
            while (count > 0) {
                long startTime = System.currentTimeMillis();
                LOGGER.info("We have more {} posts to analyze in community {}", count, community);
                List<Post> postList = postRepository.findForSentimentAnalysis(community);
                List<SentenceSentiment> sentenceSentimentList = new ArrayList<>();
                for (Post p : postList) {
                    LOGGER.info("Analyzing post id = {} with size of {} chars", p.getId(), p.getBody().length());
                    List<SentenceSentiment> list = getSentimentResult(p);
                    if (!list.isEmpty()) {
                        sentenceSentimentList.addAll(list);
                    }
                    p.setSentProcessOk( "S");
                }
                sentenceSentimentRepository.save(sentenceSentimentList);
                postRepository.save(postList);
                long endTime = System.currentTimeMillis();
                LOGGER.info("Analyzing {} posts took {} secs", postList.size(), (endTime - startTime) / 1000);
                count = postRepository.countRemaningSentimentAnalysis(community);
            }

        } else {
            LOGGER.warn("Nothing can be done on null community");
        }

    }

    private List<SentenceSentiment> getSentimentResult(Post p) {
        List<SentenceSentiment> list = new ArrayList<>();
        String text = p.getBody();
        if (text != null && text.length() > 0) {
            String stripped = text.replaceAll("<[^>]*>", "");
            stripped = stripped.replace("\n", "").replace("\r", "");

            String[] split = stripped.split("\\.");
            if(split.length == 0) {
                split = new String[]{stripped};
            }
            for(String s : split) {
                // run all Annotators on the text
                Annotation annotation = pipeline.process(s);
                for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                    // this is the parse tree of the current sentence
                    Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                    SimpleMatrix sm = RNNCoreAnnotations.getPredictions(tree);
                    String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);

                    SentenceSentiment sentenceSentiment = new SentenceSentiment();
                    sentenceSentiment.setVeryPositiveScore(sm.get(4));
                    sentenceSentiment.setPositiveScore(sm.get(3));
                    sentenceSentiment.setNeutralScore(sm.get(2));
                    sentenceSentiment.setNegativeScore(sm.get(1));
                    sentenceSentiment.setVeryNegativeScore(sm.get(0));
                    sentenceSentiment.setSentType(sentimentType);
                    sentenceSentiment.setPost(p);
                    sentenceSentiment.setSentence(sentence.toString());
                    list.add(sentenceSentiment);
                }
            }
        }
        return list;
    }

}
