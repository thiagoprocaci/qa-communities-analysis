package com.tbp.extractor

import com.tbp.extractor.support.DateUtil
import com.tbp.extractor.support.LineSupport
import com.tbp.extractor.support.NumberUtil
import com.tbp.model.Comment
import com.tbp.model.Community
import com.tbp.model.Post
import com.tbp.model.User
import com.tbp.model.Vote
import com.tbp.repository.CommentRepository
import com.tbp.repository.CommunityRepository
import com.tbp.repository.PostRepository
import com.tbp.repository.UserRepository
import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CommentExtractor {

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

    void execute(String community) {
        Community c = communityRepository.findByName(community)
        File inputFile = new File('src/main/resources/' + community + File.separator + 'Comments.xml');

        inputFile.eachLine{ it, i ->
            def line = lineSupport.prepareLine(it)

            if(line != null) {
                def reader = new StringReader(line)
                def doc = DOMBuilder.parse(reader)
                def row = doc.documentElement
                use(DOMCategory) {
                    Comment u = new Comment()
                    u.idCommentCommunity = numberUtil.toLong(row['@Id'])
                    if(commentRepository.findByCommunityAndIdCommentCommunity(c, u.idCommentCommunity) == null){
                        u.idPostCommunity = numberUtil.toLong(row['@PostId'])
                        u.creationDate = dateUtil.toDate(row['@CreationDate'])
                        u.score = numberUtil.toInteger(row['@Score'])
                        u.text = row['@Text']
                        u.idUserCommunity = numberUtil.toLong(row['@UserId'])
                        u.community = c
                        if( u.idUserCommunity != null) {
                            u.user = userRepository.findByCommunityAndIdUserCommunity(c, u.idUserCommunity)
                        }
                        u.post = postRepository.findByCommunityAndIdPostCommunity(c, u.idPostCommunity)
                        commentRepository.save(u)
                    }

                }
            }
        }
    }

}
