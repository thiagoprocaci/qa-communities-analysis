package com.tbp.extractor

import com.tbp.extractor.support.DateUtil
import com.tbp.extractor.support.NumberUtil
import com.tbp.model.Community
import com.tbp.model.Vote
import com.tbp.repository.PostRepository
import com.tbp.repository.UserRepository
import com.tbp.repository.VoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class VoteExtractor extends AbstractExtractor {

    @Autowired
    UserRepository userRepository
    @Autowired
    DateUtil dateUtil
    @Autowired
    NumberUtil numberUtil
    @Autowired
    VoteRepository voteRepository
    @Autowired
    PostRepository postRepository

    @Override
    String getFileName() {
        return 'Votes.xml'
    }

    @Override
    void onExecute(Object row, Community c) {
        Long idVoteCommunity = numberUtil.toLong(row['@Id'])
        if(voteRepository.findByCommunityAndIdVoteCommunity(c, idVoteCommunity) == null){
            Vote vote = new Vote()
            vote.idVoteCommunity = idVoteCommunity
            vote.idPostCommunity = numberUtil.toLong(row['@PostId'])
            vote.creationDate = dateUtil.toDate(row['@CreationDate'])
            vote.voteType = numberUtil.toInteger(row['@VoteTypeId'])
            vote.idUserCommunity = numberUtil.toLong(row['@UserId'])
            vote.bountyAmount = numberUtil.toInteger(row['@BountyAmount'])
            vote.community = c
            Integer userCommId = numberUtil.toLong(row['@UserId'])
            if( userCommId != null) {
                vote.user = userRepository.findByCommunityAndIdUserCommunity(c, userCommId)
            }
            vote.post = postRepository.findByCommunityAndIdPostCommunity(c, vote.idPostCommunity)
            voteRepository.save(vote)
        }
    }
}
