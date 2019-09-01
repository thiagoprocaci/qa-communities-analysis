package com.tbp.sentiment


import com.tbp.etl.model.Post
import com.tbp.etl.model.SentenceSentiment
import com.tbp.etl.repository.PostRepository
import com.tbp.etl.repository.SentenceSentimentRepository
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations
import edu.stanford.nlp.trees.Tree
import edu.stanford.nlp.util.CoreMap
import org.ejml.simple.SimpleMatrix
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SentimentAnalysisService {

    static final Logger LOGGER = LoggerFactory.getLogger(SentimentAnalysisService.class)

    @Autowired
    PostRepository postRepository
    @Autowired
    SentenceSentimentRepository sentenceSentimentRepository

    /*
	 * "Very negative" = 0 "Negative" = 1 "Neutral" = 2 "Positive" = 3
	 * "Very positive" = 4
	 */
    static Properties props
    static StanfordCoreNLP pipeline

    SentimentAnalysisService() {
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and sentiment
        props = new Properties()
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment")
        pipeline = new StanfordCoreNLP(props)
    }

    void makeAnalysis(String community) {
        if(community != null) {
            LOGGER.info("Starting sentiment analysis. Community : {}", community)
            sentenceSentimentRepository.deleteIncompleteAnalysis()
            Long count = postRepository.countRemaningSentimentAnalysis(community)
            while (count > 0) {
                LOGGER.info("We have more {} posts to analyze in community {}", count, community)
                List<Post> postList = postRepository.findForSentimentAnalysis(community)
                List<SentenceSentiment> sentenceSentimentList = new ArrayList<>()
                for(Post p : postList) {
                    LOGGER.info("Analyzing post id = {}", p.id)
                    List<SentenceSentiment> list = getSentimentResult(p)
                    if(!list.isEmpty()) {
                        sentenceSentimentList.addAll(list)
                    }
                    p.sentProcessOk = "S"
                }
                sentenceSentimentRepository.save(sentenceSentimentList)
                postRepository.save(postList)
                count = postRepository.countRemaningSentimentAnalysis(community)
            }

        } else {
            LOGGER.warn("Nothing can be done on null community")
        }

    }

    List<SentenceSentiment> getSentimentResult(Post p ) {

        List<SentenceSentiment> list = new ArrayList<>()
        String text = p.body
        if (text != null && text.length() > 0) {
            // run all Annotators on the text
            Annotation annotation = pipeline.process(text)

            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                // this is the parse tree of the current sentence
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class)
                SimpleMatrix sm = RNNCoreAnnotations.getPredictions(tree)
                String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class)

                SentenceSentiment sentenceSentiment = new SentenceSentiment()
                sentenceSentiment.veryPositiveScore = sm.get(4)
                sentenceSentiment.positiveScore = sm.get(3)
                sentenceSentiment.neutralScore = sm.get(2)
                sentenceSentiment.negativeScore = sm.get(1)
                sentenceSentiment.veryNegativeScore = sm.get(0)
                sentenceSentiment.sentType = sentimentType
                sentenceSentiment.post = p
                sentenceSentiment.sentence = sentence.toString()
                list.add(sentenceSentiment)
            }

        }
        return list
    }
}
