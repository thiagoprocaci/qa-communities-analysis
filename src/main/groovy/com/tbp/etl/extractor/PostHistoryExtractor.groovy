package com.tbp.etl.extractor

import com.tbp.etl.extractor.support.DateUtil
import com.tbp.etl.extractor.support.NumberUtil
import com.tbp.etl.extractor.support.StringSupport
import com.tbp.etl.model.Community
import com.tbp.etl.model.PostHistory
import com.tbp.etl.repository.PostHistoryRepository
import com.tbp.etl.repository.PostRepository
import com.tbp.etl.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PostHistoryExtractor extends AbstractExtractor<PostHistory> {

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
    PostHistoryRepository postHistoryRepository

    @Override
    String getFileName() {
        return "PostHistory.xml"
    }

    @Override
    PostHistory onExecute(Object row, Community c) {
        Long idPostHistoryCommunity = numberUtil.toLong(row['@Id'])
        PostHistory postHistory = postHistoryRepository.findByCommunityAndIdPostHistoryCommunity(c, idPostHistoryCommunity)
        if(postHistory == null) {
            postHistory = new PostHistory()
            postHistory.idPostHistoryCommunity = idPostHistoryCommunity
            postHistory.creationDate = dateUtil.toDate(row['@CreationDate'])
            postHistory.type = numberUtil.toInteger(row['@PostHistoryTypeId'])
            postHistory.idPostCommunity = numberUtil.toLong(row['@PostId'])
            postHistory.revisionGUID = stringSupport.prepare(row['@RevisionGUID'])
            postHistory.userDisplayName = stringSupport.prepare(row['@UserDisplayName'])
            postHistory.comment = stringSupport.prepare(row['@Comment'])
            postHistory.text = stringSupport.prepare(row['@Text'])
            postHistory.closeReason = numberUtil.toInteger(row['@CloseReasonId'])
            postHistory.idUserCommunity = numberUtil.toLong(row['@UserId'])
            if(postHistory.idUserCommunity != null) {
                postHistory.user = userRepository.findByCommunityAndIdUserCommunity(c, postHistory.idUserCommunity)
            }
            if(postHistory.idPostCommunity != null) {
                postHistory.post = postRepository.findByCommunityAndIdPostCommunity(c, postHistory.idPostCommunity)
            }
            postHistory.community = c
            return postHistory
        }
        return null
    }

    @Override
    void save(List<PostHistory> list) {
        postHistoryRepository.save(list)
    }
}
