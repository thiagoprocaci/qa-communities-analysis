package com.tbp.extractor;


import com.tbp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class BaseExtractorTest {

    @Autowired
    UserExtractor userExtractor;
    @Autowired
    CommunityExtractor communityExtractor;
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostExtractor postExtractor;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentExtractor commentExtractor;
    @Autowired
    PostLinkRepository postLinkRepository;
    @Autowired
    PostLinkExtractor postLinkExtractor;


    Random random = new Random();
    XmlReader xmlReader = new XmlReader();
    String communityName = "meta.3dprinting.stackexchange.com";

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
