package com.tbp;


import com.tbp.extractor.CommunityExtractor;
import com.tbp.extractor.PostExtractor;
import com.tbp.extractor.UserExtractor;
import com.tbp.extractor.VoteExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    CommunityExtractor communityExtractor;
    @Autowired
    UserExtractor userExtractor;

    @Autowired
    PostExtractor postExtractor;
    @Autowired
    VoteExtractor voteExtractor;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        String community = "ai.stackexchange.com";
        communityExtractor.execute(community);
        userExtractor.execute(community);
        postExtractor.execute(community);
        voteExtractor.execute(community);
    }
}
