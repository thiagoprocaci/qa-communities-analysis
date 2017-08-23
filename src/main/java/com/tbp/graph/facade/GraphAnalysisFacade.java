package com.tbp.graph.facade;


import com.tbp.etl.model.Comment;
import com.tbp.etl.model.Community;
import com.tbp.etl.model.Post;
import com.tbp.etl.repository.CommentRepository;
import com.tbp.etl.repository.CommunityRepository;
import com.tbp.etl.repository.PostRepository;
import com.tbp.graph.model.Graph;
import com.tbp.graph.model.GraphDto;
import com.tbp.graph.service.GephiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphAnalysisFacade {

    @Autowired
    GephiService gephiService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    CommentRepository commentRepository;



    public GraphDto makeAnalysis(String communityName, Integer period) {
        Community community = communityRepository.findByName(communityName);
        List<Post> postList = postRepository.findByCommunityAndPeriodLessThan(community, (period + 1));
        if(postList != null && !postList.isEmpty()) {
            Graph graph = new Graph();
            for(Post post : postList) {
                if(post.getParentPostCommunityId() != null) {
                    Post parent = postRepository.findByCommunityAndIdPostCommunity(community, post.getParentPostCommunityId());
                    if(parent.getPeriod() < (period + 1)) {
                        graph.addEdge(post.getUser().getId(), post.getUser().getDisplayName(), parent.getUser().getId(), parent.getUser().getDisplayName());
                    }
                }
            }
            postList.clear();
            List<Comment> commentList = commentRepository.findByCommunityAndPeriodLessThan(community, (period + 1));
            if(commentList != null && !commentList.isEmpty()) {
                for(Comment comment: commentList) {
                    if(comment.getPost() != null && comment.getPost().getPeriod() < (period + 1)) {
                        Post parent = comment.getPost();
                        graph.addEdge(comment.getUser().getId(), comment.getUser().getDisplayName(), parent.getUser().getId(), parent.getUser().getDisplayName());
                    }
                }
            }
            commentList.clear();
            gephiService.executeAlgorithm(graph);
            return new GraphDto(graph, community);
        }
        return null;
    }
}
