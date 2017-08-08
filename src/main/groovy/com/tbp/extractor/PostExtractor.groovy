package com.tbp.extractor

import com.tbp.extractor.support.DateUtil
import com.tbp.extractor.support.LineSupport
import com.tbp.extractor.support.NumberUtil
import com.tbp.model.Community
import com.tbp.model.Post
import com.tbp.repository.CommunityRepository
import com.tbp.repository.PostRepository
import com.tbp.repository.UserRepository
import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PostExtractor {

    @Autowired
    LineSupport lineSupport
    @Autowired
    DateUtil dateUtil
    @Autowired
    NumberUtil numberUtil
    @Autowired
    CommunityRepository communityRepository
    @Autowired
    UserRepository userRepository
    @Autowired
    PostRepository postRepository

    void execute(String community) {
        Community c = communityRepository.findByName(community)
        File inputFile = new File('src/main/resources/' + community + File.separator + 'Posts.xml');

        inputFile.eachLine { it, i ->
            def line = lineSupport.prepareLine(it)
            if (line != null) {
                def reader = new StringReader(line)
                def doc = DOMBuilder.parse(reader)
                def row = doc.documentElement
                use(DOMCategory) {
                    handlePost(row, c)
                }
            }
        }
    }

    def handlePost(def row, Community c) {
        Post post = new Post()
        post.idPostCommunity = numberUtil.toLong(row['@Id'])
        post.creationDate = dateUtil.toDate(row['@CreationDate'])
        post.acceptedAnswerId = numberUtil.toLong(row['@AcceptedAnswerId'])
        post.score = numberUtil.toInteger(row['@Score'])
        post.viewCount = numberUtil.toInteger(row['@ViewCount'])
        post.body = row['@Body']
        post.idUserCommunity = numberUtil.toLong(row['@OwnerUserId'])
        post.lastEditorUserCommunityId = numberUtil.toLong(row['@LastEditorUserId'])
        post.lastEditorDisplayName = row['@LastEditorDisplayName']
        post.lastEditDate = dateUtil.toDate(row['@LastEditDate'])
        post.lastActivityDate = dateUtil.toDate(row['@LastActivityDate'])
        post.communityOwnedDate = dateUtil.toDate(row['@CommunityOwnedDate'])
        post.closedDate = dateUtil.toDate(row['@ClosedDate'])
        post.title = row['@Title']
        post.tags = row['@Tags']
        post.answerCount = numberUtil.toInteger(row['@AnswerCount'])
        post.commentCount = numberUtil.toInteger(row['@CommentCount'])
        post.favoriteCount = numberUtil.toInteger(row['@FavoriteCount'])
        post.community = c
        post.user = userRepository.findByCommunityAndIdUserCommunity(c, post.idUserCommunity)
        post.ari = null
        post.postType = numberUtil.toInteger(row['@PostTypeId'])
        post.parentPostCommunityId = numberUtil.toLong(row['@ParentId'])
        if(postRepository.findByCommunityAndIdPostCommunity(c, post.idPostCommunity) == null) {
            postRepository.save(post)
        }
    }
}
