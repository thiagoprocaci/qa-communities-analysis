package com.tbp.graph.facade;



import com.tbp.etl.model.Comment;
import com.tbp.etl.model.Community;
import com.tbp.etl.model.Post;
import com.tbp.etl.repository.CommentRepository;
import com.tbp.etl.repository.CommunityRepository;
import com.tbp.etl.repository.PostRepository;
import com.tbp.graph.model.*;

import com.tbp.graph.repository.GraphAnalysisContextRepository;
import com.tbp.graph.repository.GraphEdgeRepository;
import com.tbp.graph.repository.GraphNodeRepository;
import com.tbp.graph.service.GephiService;
import com.tbp.period.repository.DateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GraphAnalysisFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphAnalysisFacade.class);

    @Autowired
    GephiService gephiService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    DateRepository dateRepository;
    @Autowired
    GraphAnalysisContextRepository graphAnalysisContextRepository;
    @Autowired
    GraphNodeRepository graphNodeRepository;
    @Autowired
    GraphEdgeRepository graphEdgeRepository;

    public void makeAnalysis(String communityName) {
        if(communityName == null) {
            LOGGER.info("Nothing to do with null community name");
            return;
        }
        Community community = communityRepository.findByName(communityName);
        if(community == null || community.getId() == null) {
            LOGGER.info("Nothing to do with null community");
            return;
        }
        Integer minPeriod =  dateRepository.getMinPeriodByCommunity(community.getId());
        Integer maxPeriod = dateRepository.getMaxPeriodByCommunity(community.getId());
        LOGGER.info("Min period: " + minPeriod);
        LOGGER.info("Max period: " + maxPeriod);
        if(minPeriod == null || maxPeriod == null) {
            LOGGER.info("Nothing to do with null period");
        }

        while(minPeriod <= maxPeriod) {
            if(graphAnalysisContextRepository.findByPeriodAndIdCommunity(minPeriod, community.getId()) != null) {
                LOGGER.info("There is a graph context analysis with period " + minPeriod + " and community " + communityName);
                minPeriod++;
                continue;
            }
            Graph graph = makeAnalysis(communityName, minPeriod);
            if(graph.getNodeMap().size() == 0 || graph.getEdgeMap().size() == 0) {
                minPeriod++;
                continue;
            }
            // creating graph persistence
            LOGGER.info("Creating graph persistence for " + communityName);
            GraphAnalysisContext graphAnalysisContext = new GraphAnalysisContext(graph, minPeriod, community.getId());
            LOGGER.info("Saving graph context of " + communityName);
            graphAnalysisContext = graphAnalysisContextRepository.save(graphAnalysisContext);
            LOGGER.info("Saving graph nodes of " + communityName);
            List<GraphNode> graphNodes = new ArrayList<>();
            for(Vertex vertex : graph.getNodeMap().values()) {
                GraphNode graphNode = new GraphNode(vertex, graphAnalysisContext);
                graphNodes.add(graphNode);
                if(graphNodes.size() == 1000) {
                    graphNodeRepository.save(graphNodes);
                    graphNodes.clear();
                }
            }
            graphNodeRepository.save(graphNodes);

            LOGGER.info("Saving graph edges of " + communityName);
            List<GraphEdge> graphEdges = new ArrayList<>();
            for(Edge edge: graph.getEdgeMap().values()) {
                GraphNode graphNodeSource = graphNodeRepository.findByGraphAnalysisContextAndIdUser(graphAnalysisContext, edge.getSource().getId());
                GraphNode graphNodeDest = graphNodeRepository.findByGraphAnalysisContextAndIdUser(graphAnalysisContext, edge.getDest().getId());
                GraphEdge graphEdge = new GraphEdge(edge, graphAnalysisContext, graphNodeSource, graphNodeDest);
                graphEdges.add(graphEdge);
                if(graphEdges.size() == 1000) {
                    graphEdgeRepository.save(graphEdges);
                    graphEdges.clear();
                }
            }
            graphEdgeRepository.save(graphEdges);
            minPeriod++;
        }

    }

    Graph makeAnalysis(String communityName, Integer period) {
        LOGGER.info("Graph analysis of " + communityName + ", considering the period " + period);
        Community community = communityRepository.findByName(communityName);
        List<Post> postList = postRepository.findByCommunityAndPeriodLessThan(community, (period + 1));
        if(postList != null && !postList.isEmpty()) {
            Graph graph = new Graph();
            for(Post post : postList) {
                if(post.getParentPostCommunityId() != null && post.getUser() != null) {
                    Post parent = postRepository.findByCommunityAndIdPostCommunity(community, post.getParentPostCommunityId());
                    if(parent.getPeriod() < (period + 1) && parent.getUser() != null) {
                        graph.addEdge(parent.getUser().getId(), parent.getUser().getDisplayName(), post.getUser().getId(), post.getUser().getDisplayName());
                    }
                }
            }
            postList.clear();
            List<Comment> commentList = commentRepository.findByCommunityAndPeriodLessThan(community, (period + 1));
            if(commentList != null && !commentList.isEmpty()) {
                for(Comment comment: commentList) {
                    if(comment.getPost() != null && comment.getPost().getPeriod() < (period + 1) && comment.getUser() != null) {
                        Post parent = comment.getPost();
                        if(parent.getUser() != null) {
                            graph.addEdge(parent.getUser().getId(), parent.getUser().getDisplayName(), comment.getUser().getId(), comment.getUser().getDisplayName());
                        }
                    }
                }
            }
            commentList.clear();
            gephiService.executeAlgorithm(graph);
            return graph;
        }
        return null;
    }
}
