package com.tbp;


import com.tbp.extractor.CommunityExtractor;
import com.tbp.extractor.QuestionExtractor;
import com.tbp.extractor.UserExtractor;
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
    QuestionExtractor questionExtractor;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        String community = "biology.stackexchange.com";
        //communityExtractor.execute(community);
        //userExtractor.execute(community);
        questionExtractor.execute(community);
    }
}
