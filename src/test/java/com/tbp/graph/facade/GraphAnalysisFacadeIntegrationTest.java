package com.tbp.graph.facade;

import com.tbp.TestApplicationConfiguration;
import com.tbp.etl.extractor.*;

import com.tbp.graph.model.*;
import com.tbp.graph.repository.GraphAnalysisContextRepository;
import com.tbp.graph.repository.GraphEdgeRepository;
import com.tbp.graph.repository.GraphNodeRepository;

import com.tbp.period.service.DateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GraphAnalysisFacadeIntegrationTest {

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
    @Autowired
    GraphAnalysisFacade graphAnalysisFacade;
    @Autowired
    GraphAnalysisContextRepository graphAnalysisContextRepository;
    @Autowired
    GraphNodeRepository graphNodeRepository;
    @Autowired
    GraphEdgeRepository graphEdgeRepository;

    String community = "meta.3dprinting.stackexchange.com";

    @Before
    public void before() {
        communityExtractor.execute(community);
        userExtractor.execute(community);
        postExtractor.execute(community);
        voteExtractor.execute(community);
        commentExtractor.execute(community);
        postLinkExtractor.execute(community);
        dateService.updateCommunityPeriods(community);
    }

    @Test
    public void makeAnalysis() {
        graphAnalysisFacade.makeAnalysis(community);
        List<GraphAnalysisContext> contextList = (List<GraphAnalysisContext>) makeCollection(graphAnalysisContextRepository.findAll());
        assertEquals(17, contextList.size());

        for(int i = 0; i < contextList.size(); i++) {
            GraphAnalysisContext context = contextList.get(i);
            Graph g = graphAnalysisFacade.makeAnalysis(community, i);

            assertEquals(g.getModularity(), context.getModularity(), 0.1);
            assertNotNull(g.getNodeMap());
            assertTrue(g.getNodeMap().size() > 0);
            assertNotNull(g.getEdgeMap());
            assertTrue(g.getEdgeMap().size() > 0);
            assertEquals(g.getNodeMap().size(), context.getNodes().intValue());
            assertEquals(g.getEdgeMap().size(), context.getEdges().intValue());
            assertEquals(g.getAvgClusteringCoef(), context.getAvgClusteringCoef());
            assertEquals(g.getDensity(), context.getDensity());
            assertEquals(g.getDiameter(), context.getDiameter());
            assertEquals(g.getRadius(), context.getRadius());
            assertEquals(g.getAvgDist(), context.getAvgDist());
            assertEquals(g.getWeaklyComponentCount(), context.getWeaklyComponentCount());
            assertEquals(g.getStronglyComponentCount(), context.getStronglyComponentCount());
            assertEquals(g.getModularityWithResolution(), context.getModularityWithResolution(), 0.1);
            assertEquals(g.getAvgDegree(), context.getAvgDegree());

            for(Vertex v: g.getNodeMap().values()) {
                assertNotNull(v);
                GraphNode node = graphNodeRepository.findByGraphAnalysisContextAndIdUser(context, v.getId());
                assertNotNull(node);
                assertEquals(v.getBetweenness(), node.getBetweenness());
                assertEquals(v.getCloseness(), node.getCloseness());
                assertEquals(v.getEccentricity(), node.getEccentricity());
                assertEquals(v.getHarmonicCloseness(), node.getHarmonicCloseness());
                assertEquals(v.getPageRank(), node.getPageRank());
                assertEquals(v.getIndegree(), node.getIndegree());
                assertEquals(v.getOutdegree(), node.getOutdegree());
                assertEquals(v.getDegree(), node.getDegree());
                assertEquals(v.getEigenvector(), node.getEigenvector());
                assertEquals(v.getClusteringCoefficient(), node.getClusteringCoefficient());
                assertEquals(v.getStronglyComponent(), node.getStronglyComponent());
                assertEquals(v.getWeaklyComponent(), node.getWeaklyComponent());
                assertEquals(v.getInteractions(), node.getInteractions());
            }

            for(Edge edge: g.getEdgeMap().values()) {
                assertNotNull(edge);
                GraphEdge graphEdge = graphEdgeRepository.findByGraphAnalysisContextAndIdUserSourceAndIdUserDest(context, edge.getSource().getId(), edge.getDest().getId());
                assertNotNull(graphEdge);

                assertEquals(edge.getSource().getId(), graphEdge.getIdUserSource());
                assertEquals(edge.getDest().getId(), graphEdge.getIdUserDest());
                assertEquals(edge.getWeight(), graphEdge.getWeight());
            }
        }

    }

    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }


}
