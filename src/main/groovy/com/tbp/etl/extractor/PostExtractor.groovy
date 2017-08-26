package com.tbp.etl.extractor

import com.tbp.etl.extractor.support.DateUtil
import com.tbp.etl.extractor.support.NumberUtil
import com.tbp.etl.extractor.support.StringSupport
import com.tbp.etl.model.Community
import com.tbp.etl.model.Post
import com.tbp.etl.repository.PostBatchRepository
import com.tbp.etl.repository.PostRepository
import com.tbp.etl.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PostExtractor extends AbstractExtractor<Post> {


    @Autowired
    DateUtil dateUtil
    @Autowired
    NumberUtil numberUtil
    @Autowired
    UserRepository userRepository
    @Autowired
    PostRepository postRepository
    @Autowired
    StringSupport stringSupport
    @Autowired
    PostBatchRepository postBatchRepository;

    @Override
    String getFileName() {
        return 'Posts.xml'
    }

    @Override
    Post onExecute(Object row, Community c) {
        Long idPostCommunity = numberUtil.toLong(row['@Id'])
        Post post = postRepository.findByCommunityAndIdPostCommunity(c, idPostCommunity)
        if(post == null) {
            post = new Post()
        }
        post.idPostCommunity = numberUtil.toLong(row['@Id'])
        post.creationDate = dateUtil.toDate(row['@CreationDate'])
        post.acceptedAnswerId = numberUtil.toLong(row['@AcceptedAnswerId'])
        post.score = numberUtil.toInteger(row['@Score'])
        post.viewCount = numberUtil.toInteger(row['@ViewCount'])
        post.body = stringSupport.prepare(row['@Body'])
        post.idUserCommunity = numberUtil.toLong(row['@OwnerUserId'])
        post.lastEditorUserCommunityId = numberUtil.toLong(row['@LastEditorUserId'])
        post.lastEditorDisplayName = row['@LastEditorDisplayName']
        post.lastEditDate = dateUtil.toDate(row['@LastEditDate'])
        post.lastActivityDate = dateUtil.toDate(row['@LastActivityDate'])
        post.communityOwnedDate = dateUtil.toDate(row['@CommunityOwnedDate'])
        post.closedDate = dateUtil.toDate(row['@ClosedDate'])
        post.title = stringSupport.prepare(row['@Title'])
        post.tags = stringSupport.prepare(row['@Tags'])
        post.answerCount = numberUtil.toInteger(row['@AnswerCount'])
        post.commentCount = numberUtil.toInteger(row['@CommentCount'])
        post.favoriteCount = numberUtil.toInteger(row['@FavoriteCount'])
        post.community = c
        post.user = userRepository.findByCommunityAndIdUserCommunity(c, post.idUserCommunity)
        post.postType = numberUtil.toInteger(row['@PostTypeId'])
        post.parentPostCommunityId = numberUtil.toLong(row['@ParentId'])
        return post

    }

    @Override
    void save(List<Post> list) {
        postBatchRepository.saveBatch(list)
    }
}
