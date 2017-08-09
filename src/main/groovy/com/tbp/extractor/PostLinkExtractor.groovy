package com.tbp.extractor

import com.tbp.extractor.support.DateUtil
import com.tbp.extractor.support.NumberUtil
import com.tbp.model.Community
import com.tbp.model.PostLink
import com.tbp.repository.PostLinkRepository
import com.tbp.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PostLinkExtractor extends AbstractExtractor {

    @Autowired
    DateUtil dateUtil
    @Autowired
    NumberUtil numberUtil
    @Autowired
    PostRepository postRepository
    @Autowired
    PostLinkRepository postLinkRepository


    @Override
    String getFileName() {
        return 'PostLinks.xml'
    }

    @Override
    void onExecute(Object row, Community c) {
        Long idPostLinkCommunity = numberUtil.toLong(row['@Id'])
        if(postLinkRepository.findByCommunityAndIdPostLinkCommunity(c, idPostLinkCommunity) == null) {
            PostLink postLink = new PostLink()
            postLink.idPostLinkCommunity = idPostLinkCommunity
            postLink.creationDate = dateUtil.toDate(row['@CreationDate'])
            postLink.idPostCommunity = numberUtil.toLong(row['@PostId'])
            postLink.idRelatedPostCommunity = numberUtil.toLong(row['@RelatedPostId'])
            postLink.postLinkType = numberUtil.toInteger(row['@LinkTypeId'])
            postLink.community = c
            postLink.post = postRepository.findByCommunityAndIdPostCommunity(c, postLink.idPostCommunity)
            postLink.relatedPost = postRepository.findByCommunityAndIdPostCommunity(c, postLink.idRelatedPostCommunity)
            postLinkRepository.save(postLink)
        }

    }
}
