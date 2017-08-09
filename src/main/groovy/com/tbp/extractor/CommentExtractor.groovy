package com.tbp.extractor

import com.tbp.extractor.support.DateUtil
import com.tbp.extractor.support.LineSupport
import com.tbp.extractor.support.NumberUtil
import com.tbp.model.Comment
import com.tbp.model.Community

import com.tbp.repository.CommentRepository
import com.tbp.repository.CommunityRepository
import com.tbp.repository.PostRepository
import com.tbp.repository.UserRepository
import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CommentExtractor extends AbstractExtractor {

    @Autowired
    CommunityRepository communityRepository
    @Autowired
    UserRepository userRepository
    @Autowired
    LineSupport lineSupport
    @Autowired
    DateUtil dateUtil
    @Autowired
    NumberUtil numberUtil
    @Autowired
    PostRepository postRepository
    @Autowired
    CommentRepository commentRepository


    @Override
    String getFileName() {
        return 'Comments.xml'
    }

    @Override
    void onExecute(Object row, Community c) {
        Long idCommentCommunity = numberUtil.toLong(row['@Id'])
        if(commentRepository.findByCommunityAndIdCommentCommunity(c, idCommentCommunity) == null) {
            Comment comment = new Comment()
            comment.idCommentCommunity = numberUtil.toLong(row['@Id'])
            comment.idPostCommunity = numberUtil.toLong(row['@PostId'])
            comment.creationDate = dateUtil.toDate(row['@CreationDate'])
            comment.score = numberUtil.toInteger(row['@Score'])
            comment.text = row['@Text']
            comment.idUserCommunity = numberUtil.toLong(row['@UserId'])
            comment.community = c
            if( comment.idUserCommunity != null) {
                comment.user = userRepository.findByCommunityAndIdUserCommunity(c, comment.idUserCommunity)
            }
            comment.post = postRepository.findByCommunityAndIdPostCommunity(c, comment.idPostCommunity)
            commentRepository.save(comment)
        }
    }
}
