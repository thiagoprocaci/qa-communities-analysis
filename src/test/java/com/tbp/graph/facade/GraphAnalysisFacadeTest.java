package com.tbp.graph.facade;


import com.tbp.etl.model.Comment;
import com.tbp.etl.model.Community;
import com.tbp.etl.model.Post;
import com.tbp.etl.model.User;
import com.tbp.etl.repository.CommentRepository;
import com.tbp.etl.repository.CommunityRepository;
import com.tbp.etl.repository.PostRepository;
import com.tbp.graph.model.Graph;
import com.tbp.graph.repository.GraphAnalysisContextRepository;
import com.tbp.graph.repository.GraphEdgeRepository;
import com.tbp.graph.repository.GraphNodeRepository;
import com.tbp.graph.service.GephiService;

import com.tbp.period.repository.DateRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class GraphAnalysisFacadeTest {


    GephiService gephiService;
    PostRepository postRepository;
    CommunityRepository communityRepository;
    CommentRepository commentRepository;
    GraphAnalysisFacade graphAnalysisFacade;

    DateRepository dateRepository;
    GraphAnalysisContextRepository graphAnalysisContextRepository;
    GraphNodeRepository graphNodeRepository;
    GraphEdgeRepository graphEdgeRepository;

    String communityName = "abc";
    Community community = new Community();

    @Before
    public void before() {
        gephiService = new GephiService();
        postRepository = mock(PostRepository.class);
        communityRepository = mock(CommunityRepository.class);
        commentRepository = mock(CommentRepository.class);

        dateRepository = mock(DateRepository.class);
        graphAnalysisContextRepository = mock(GraphAnalysisContextRepository.class);
        graphNodeRepository = mock(GraphNodeRepository.class);
        graphEdgeRepository =  mock(GraphEdgeRepository.class);

        graphAnalysisFacade = new GraphAnalysisFacade();
        graphAnalysisFacade.gephiService = gephiService;
        graphAnalysisFacade.postRepository = postRepository;
        graphAnalysisFacade.commentRepository = commentRepository;
        graphAnalysisFacade.communityRepository = communityRepository;
        graphAnalysisFacade.dateRepository = dateRepository;
        graphAnalysisFacade.graphAnalysisContextRepository = graphAnalysisContextRepository;
        graphAnalysisFacade.graphNodeRepository = graphNodeRepository;
        graphAnalysisFacade.graphEdgeRepository = graphEdgeRepository;

        when(communityRepository.findByName(communityName)).thenReturn(community);
    }

    @Test
    public void makeAnalysisNullParam() {
        graphAnalysisFacade.makeAnalysis(null);
        verifyZeroInteractions(postRepository, communityRepository, commentRepository, dateRepository, graphAnalysisContextRepository, graphNodeRepository, graphEdgeRepository);
    }

    @Test
    public void makeAnalysisinvalidCommunity() {
        graphAnalysisFacade.makeAnalysis("xxx");
        verifyZeroInteractions(postRepository, commentRepository, dateRepository, graphAnalysisContextRepository, graphNodeRepository, graphEdgeRepository);
    }

    @Test
    public void makeAnalysis() {


        Integer period = 4;
        Integer periodPlusOne = period + 1;

        Post post1 = createPost(1L, period, null);
        Post post2 = createPost(2L, period, post1);
        Post post3 = createPost(3L, period, post1);

        Comment comment4 = createComment(4L, period, post3);
        Comment comment5 = createComment(5L, period, post2);

        List<Post> postList = new ArrayList<>();
        postList.addAll(Arrays.asList(post1, post2, post3));

        List<Comment> commentList = new ArrayList<>();
        commentList.addAll(Arrays.asList(comment4, comment5));

        when(postRepository.findByCommunityAndPeriodLessThan(community, periodPlusOne)).thenReturn(postList);
        when(commentRepository.findByCommunityAndPeriodLessThan(community, periodPlusOne)).thenReturn(commentList);

        Graph g = graphAnalysisFacade.makeAnalysis(communityName, period);

        assertEquals(0, g.getNodeMap().get(1L).getIndegree().intValue());
        assertEquals(2, g.getNodeMap().get(1L).getOutdegree().intValue());
        assertEquals(2, g.getNodeMap().get(1L).getDegree().intValue());
        assertEquals(2d, g.getNodeMap().get(1L).getEccentricity(), 0.0001);
        assertEquals(0.6666, g.getNodeMap().get(1L).getCloseness(), 0.0001);
        assertEquals(0.75, g.getNodeMap().get(1L).getHarmonicCloseness(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(1L).getBetweenness(), 0.0001);
        assertEquals(0.1209d, g.getNodeMap().get(1L).getPageRank(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(1L).getEigenvector(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(1L).getClusteringCoefficient(), 0.0001);
        assertEquals(0, g.getNodeMap().get(1L).getWeaklyComponent().intValue());


        assertEquals(1, g.getNodeMap().get(2L).getIndegree().intValue());
        assertEquals(1, g.getNodeMap().get(2L).getOutdegree().intValue());
        assertEquals(2, g.getNodeMap().get(2L).getDegree().intValue());
        assertEquals(1d, g.getNodeMap().get(2L).getEccentricity(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(2L).getCloseness(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(2L).getHarmonicCloseness(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(2L).getBetweenness(), 0.0001);
        assertEquals(0.1722d, g.getNodeMap().get(2L).getPageRank(), 0.0001);
        assertEquals(0.0703d, g.getNodeMap().get(2L).getEigenvector(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(2L).getClusteringCoefficient(), 0.0001);
        assertEquals(0, g.getNodeMap().get(2L).getWeaklyComponent().intValue());


        assertEquals(1, g.getNodeMap().get(3L).getIndegree().intValue());
        assertEquals(1, g.getNodeMap().get(3L).getOutdegree().intValue());
        assertEquals(2, g.getNodeMap().get(3L).getDegree().intValue());
        assertEquals(1d, g.getNodeMap().get(3L).getEccentricity(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(3L).getCloseness(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(3L).getHarmonicCloseness(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(3L).getBetweenness(), 0.0001);
        assertEquals(0.1722d, g.getNodeMap().get(3L).getPageRank(), 0.0001);
        assertEquals(0.0703d, g.getNodeMap().get(3L).getEigenvector(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(3L).getClusteringCoefficient(), 0.0001);
        assertEquals(0, g.getNodeMap().get(3L).getWeaklyComponent().intValue());


        assertEquals(1, g.getNodeMap().get(4L).getIndegree().intValue());
        assertEquals(0, g.getNodeMap().get(4L).getOutdegree().intValue());
        assertEquals(1, g.getNodeMap().get(4L).getDegree().intValue());
        assertEquals(0d, g.getNodeMap().get(4L).getEccentricity(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(4L).getCloseness(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(4L).getHarmonicCloseness(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(4L).getBetweenness(), 0.0001);
        assertEquals(0.2672d, g.getNodeMap().get(4L).getPageRank(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(4L).getEigenvector(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(4L).getClusteringCoefficient(), 0.0001);
        assertEquals(0, g.getNodeMap().get(4L).getWeaklyComponent().intValue());


        assertEquals(1, g.getNodeMap().get(5L).getIndegree().intValue());
        assertEquals(0, g.getNodeMap().get(5L).getOutdegree().intValue());
        assertEquals(1, g.getNodeMap().get(5L).getDegree().intValue());
        assertEquals(0d, g.getNodeMap().get(5L).getEccentricity(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(5L).getCloseness(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(5L).getHarmonicCloseness(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(5L).getBetweenness(), 0.0001);
        assertEquals(0.2672d, g.getNodeMap().get(5L).getPageRank(), 0.0001);
        assertEquals(1d, g.getNodeMap().get(5L).getEigenvector(), 0.0001);
        assertEquals(0d, g.getNodeMap().get(5L).getClusteringCoefficient(), 0.0001);
        assertEquals(0, g.getNodeMap().get(5L).getWeaklyComponent().intValue());


        assertEquals(2, g.getNumberOfCommunity().intValue());
        assertEquals(0.219, g.getModularityWithResolution().doubleValue(), 0.0001);
        assertEquals(0.219, g.getModularityWithResolution().doubleValue(), 0.0001); //TODO repeated ...
        assertEquals(0d, g.getAvgClusteringCoef(), 0.0001);
        assertEquals(0.2d, g.getDensity(), 0.0001);
        assertEquals(1, g.getWeaklyComponentCount().intValue());
        assertEquals(5, g.getStronglyComponentCount().intValue());
        assertEquals(2, g.getDiameter().intValue());
        assertEquals(0, g.getRadius(), 0.0001);
        assertEquals(1.3333, g.getAvgDist(), 0.0001);

        //TODO test avg degree...

        long i = 1;
        while(i < 6) {
            assertTrue(g.getNodeMap().get(i).getModularity() == 0 || g.getNodeMap().get(i).getModularity() == 1);
            long j = 1;
            while(j < 6) {
                if(i != j) {
                    assertFalse(g.getNodeMap().get(i).getStronglyComponent() == g.getNodeMap().get(j).getStronglyComponent());
                }
                j++;
            }
            i++;
        }


    }

    Post createPost(Long id, Integer period, Post parent) {
        User u = new User();
        u.setId(id);

        Post p = new Post();
        p.setId(id);
        p.setIdPostCommunity(id);
        p.setUser(u);
        p.setPeriod(period);

        if(parent != null) {
            p.setParentPostCommunityId(parent.getIdPostCommunity());
            when(postRepository.findByCommunityAndIdPostCommunity(community, p.getParentPostCommunityId())).thenReturn(parent);
        }
        return p;
    }

    Comment createComment(Long id, Integer period, Post post) {
        User u = new User();
        u.setId(id);

        Comment comment = new Comment();
        comment.setId(id);
        comment.setPeriod(period);
        comment.setUser(u);
        if(post != null) {
            comment.setPost(post);
        }
        return comment;
    }

}
