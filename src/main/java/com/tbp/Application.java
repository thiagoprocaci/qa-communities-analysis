package com.tbp;


import com.tbp.extractor.*;
import com.tbp.model.Community;
import com.tbp.repository.CommunityRepository;
import com.tbp.repository.DateRepository;
import com.tbp.service.DateService;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;
import java.util.List;


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
        String[] communities = new String[]{}; //new String[]{"android.stackexchange.com" "chemistry.stackexchange.com"};
        for (String community: communities) {
            System.out.println("communityExtractor" + " " + community);
        //    communityExtractor.execute(community);
            System.out.println("userExtractor" + " " + community);
        //    userExtractor.execute(community);
            System.out.println("postExtractor" + " " + community);
       //     postExtractor.execute(community);
            System.out.println("voteExtractor" + " " + community);
        //    voteExtractor.execute(community);
            System.out.println("commentExtractor" + " " + community);
        //    commentExtractor.execute(community);
            System.out.println("postLinkExtractor" + " " + community);
        //    postLinkExtractor.execute(community);
        }
        String community = "meta.3dprinting.stackexchange.com";
        List<Interval> intervalList = dateService.generateInterval(community);
        System.out.println(intervalList);
    }
}
