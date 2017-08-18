package com.tbp;


import com.tbp.extractor.*;
import com.tbp.model.Community;
import com.tbp.repository.CommunityRepository;
import com.tbp.repository.DateRepository;
import com.tbp.service.DateService;
import org.joda.time.Interval;
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
    DateService dateService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        String[] communities = new String[]{"meta.3dprinting.stackexchange.com",
                "android.stackexchange.com", "ai.stackexchange.com",
        "biology.stackexchange.com", "chemistry.stackexchange.com"};
              //  new String[]{};
        for (String community: communities) {
            LOGGER.info("Execution: " + community);

            LOGGER.info("communityExtractor" + " " + community);
        //    communityExtractor.execute(community);
            LOGGER.info("userExtractor" + " " + community);
        //    userExtractor.execute(community);
            LOGGER.info("postExtractor" + " " + community);
       //     postExtractor.execute(community);
            LOGGER.info("voteExtractor" + " " + community);
        //    voteExtractor.execute(community);
            LOGGER.info("commentExtractor" + " " + community);
        //    commentExtractor.execute(community);
            LOGGER.info("postLinkExtractor" + " " + community);
        //    postLinkExtractor.execute(community);
            dateService.updateCommunityPeriods(community);
        }



    }
}
