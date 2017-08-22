package com.tbp.facade;


import com.tbp.model.Comment;
import com.tbp.model.Community;
import com.tbp.model.Post;
import com.tbp.model.graph.Graph;
import com.tbp.model.graph.GraphDto;
import com.tbp.repository.CommentRepository;
import com.tbp.repository.CommunityRepository;
import com.tbp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphAnalysisFacade {

    @Autowired
    GephiFacade gephiFacade;
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
            gephiFacade.executeAlgorithm(graph);
            return new GraphDto(graph, community);
        }
        return null;
    }
}
