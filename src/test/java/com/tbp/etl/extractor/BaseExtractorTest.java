package com.tbp.etl.extractor;


import com.tbp.etl.repository.*;
import com.tbp.extractor.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import static org.junit.Assert.*;

public class BaseExtractorTest {

    @Autowired
    protected UserExtractor userExtractor;
    @Autowired
    protected CommunityExtractor communityExtractor;
    @Autowired
    protected CommunityRepository communityRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected PostExtractor postExtractor;
    @Autowired
    protected PostRepository postRepository;
    @Autowired
    protected CommentRepository commentRepository;
    @Autowired
    protected CommentExtractor commentExtractor;
    @Autowired
    protected PostLinkRepository postLinkRepository;
    @Autowired
    protected PostLinkExtractor postLinkExtractor;
    @Autowired
    protected VoteRepository voteRepository;
    @Autowired
    protected VoteExtractor voteExtractor;
    @Autowired
    protected PostHistoryExtractor postHistoryExtractor;
    @Autowired
    protected PostHistoryRepository postHistoryRepository;
    @Autowired
    protected BadgeRepository badgeRepository;
    @Autowired
    protected BadgeExtractor badgeExtractor;

    protected Random random = new Random();
    protected XmlReader xmlReader = new XmlReader();
    protected String communityName = "meta.3dprinting.stackexchange.com";

    void validateInjection() {
        assertNotNull(userExtractor);
        assertNotNull(communityExtractor);
        assertNotNull(communityRepository);
        assertNotNull(userRepository);
        assertNotNull(postExtractor);
        assertNotNull(postRepository);
        assertNotNull(commentRepository);
        assertNotNull(commentExtractor);
        assertNotNull(postLinkRepository);
        assertNotNull(postLinkExtractor);
        assertNotNull(voteRepository);
        assertNotNull(voteExtractor);
        assertNotNull(postHistoryExtractor);
        assertNotNull(postHistoryRepository);
        assertNotNull(badgeExtractor);
        assertNotNull(badgeRepository);
    }

    Long getNumberOfRows(String fileName, String community) throws IOException {
        File inputFile = new File("src/main/resources/" + community + File.separator + fileName);
        long count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line != null && line.trim().startsWith("<row")) {
                    count++;
                }
            }
        }
        return count;
    }

}
