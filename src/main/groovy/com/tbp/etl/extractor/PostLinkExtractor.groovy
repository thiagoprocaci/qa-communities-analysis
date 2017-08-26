package com.tbp.etl.extractor

import com.tbp.etl.extractor.support.DateUtil
import com.tbp.etl.extractor.support.NumberUtil
import com.tbp.etl.model.Community
import com.tbp.etl.model.PostLink

import com.tbp.etl.repository.PostLinkRepository
import com.tbp.etl.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PostLinkExtractor extends AbstractExtractor<PostLink> {

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
    PostLink onExecute(Object row, Community c) {
        Long idPostLinkCommunity = numberUtil.toLong(row['@Id'])
        PostLink postLink = postLinkRepository.findByCommunityAndIdPostLinkCommunity(c, idPostLinkCommunity)
        if(postLink == null) {
            postLink = new PostLink()
            postLink.idPostLinkCommunity = idPostLinkCommunity
            postLink.creationDate = dateUtil.toDate(row['@CreationDate'])
            postLink.idPostCommunity = numberUtil.toLong(row['@PostId'])
            postLink.idRelatedPostCommunity = numberUtil.toLong(row['@RelatedPostId'])
            postLink.postLinkType = numberUtil.toInteger(row['@LinkTypeId'])
            postLink.community = c
            postLink.post = postRepository.findByCommunityAndIdPostCommunity(c, postLink.idPostCommunity)
            postLink.relatedPost = postRepository.findByCommunityAndIdPostCommunity(c, postLink.idRelatedPostCommunity)
            return postLink
        }

    }


    @Override
    void save(List<PostLink> list) {
        postLinkRepository.save(list)
    }
}
