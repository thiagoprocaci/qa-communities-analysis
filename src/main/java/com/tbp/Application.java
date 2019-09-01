package com.tbp;


import com.tbp.etl.extractor.*;
import com.tbp.graph.facade.GraphAnalysisFacade;

import com.tbp.period.service.DateService;
import com.tbp.profile.ProfileService;
import com.tbp.readability.ReadabilityService;
import com.tbp.sentiment.SentimentAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

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
    PostHistoryExtractor postHistoryExtractor;
    @Autowired
    DateService dateService;
    @Autowired
    GraphAnalysisFacade graphAnalysisFacade;
    @Autowired
    ReadabilityService redabilityService;
    @Autowired
    BadgeExtractor badgeExtractor;
    @Autowired
    SentimentAnalysisService sentimentAnalysisService;


    @Autowired
    ProfileService profileService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        String[] communities = new String[]{"biology.stackexchange.com", "chemistry.stackexchange.com"};
                //new String[] {"ai.stackexchange.com"};
                //= new String[]{"meta.3dprinting.stackexchange.com", "android.stackexchange.com", "ai.stackexchange.com", "biology.stackexchange.com", "chemistry.stackexchange.com"};

        long startTime = System.currentTimeMillis();
        for (String community: communities) {
            LOGGER.info("Execution: " + community);

            /*LOGGER.info("communityExtractor" + " " + community);
            communityExtractor.execute(community);
            LOGGER.info("userExtractor" + " " + community);
            userExtractor.execute(community);
            LOGGER.info("postExtractor" + " " + community);
            postExtractor.execute(community);
            LOGGER.info("voteExtractor" + " " + community);
            voteExtractor.execute(community);
            LOGGER.info("commentExtractor" + " " + community);
            commentExtractor.execute(community);
            LOGGER.info("postLinkExtractor" + " " + community);
            postLinkExtractor.execute(community);
            LOGGER.info("updateCommunityPeriods" + " " + community);
            dateService.updateCommunityPeriods(community);
            LOGGER.info("postHistoryExtractor" + " " + community);
            postHistoryExtractor.execute(community);
            LOGGER.info("badgeExtractor" + " " + community);
            badgeExtractor.execute(community);
            LOGGER.info("Redability analysis" + " " + community);
            redabilityService.execute(community);
            LOGGER.info("graphAnalysisFacade" + " " + community);
            graphAnalysisFacade.makeAnalysis(community);
*/
            LOGGER.info("Sentiment analysis. Community {}", community);
            sentimentAnalysisService.makeAnalysis(community);
        }
        //LOGGER.info("Updating profiles");
       // profileService.execute();
        long end = System.currentTimeMillis();
        LOGGER.info("Total time (seconds): " +  ((end - startTime) * 0.001));


    }
}
