package com.tbp.graph.facade;


import com.tbp.etl.repository.CommentRepository;
import com.tbp.etl.repository.CommunityRepository;
import com.tbp.etl.repository.PostRepository;
import com.tbp.graph.service.GephiService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class GraphAnalysisFacadeTest {


    GephiService gephiService;
    PostRepository postRepository;
    CommunityRepository communityRepository;
    CommentRepository commentRepository;
    GraphAnalysisFacade graphAnalysisFacade;

    @Before
    public void before() {
        gephiService = new GephiService();
        postRepository = mock(PostRepository.class);
        communityRepository = mock(CommunityRepository.class);
        commentRepository = mock(CommentRepository.class);

        graphAnalysisFacade = new GraphAnalysisFacade();
        graphAnalysisFacade.gephiService = gephiService;
        graphAnalysisFacade.postRepository = postRepository;
        graphAnalysisFacade.commentRepository = commentRepository;
        graphAnalysisFacade.communityRepository = communityRepository;
    }

    @Test
    public void makeAnalysis() {

    }
}
