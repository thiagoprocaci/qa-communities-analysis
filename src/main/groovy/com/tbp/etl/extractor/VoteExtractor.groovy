package com.tbp.etl.extractor

import com.tbp.etl.extractor.support.DateUtil
import com.tbp.etl.extractor.support.NumberUtil
import com.tbp.etl.model.Community
import com.tbp.etl.model.Vote
import com.tbp.etl.repository.PostRepository
import com.tbp.etl.repository.UserRepository

import com.tbp.etl.repository.VoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class VoteExtractor extends AbstractExtractor<Vote> {

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
    Vote onExecute(Object row, Community c) {
        Long idVoteCommunity = numberUtil.toLong(row['@Id'])
        Vote vote = voteRepository.findByCommunityAndIdVoteCommunity(c, idVoteCommunity)
        if(vote == null) {
            vote = new Vote()
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
            return vote
        }
        return null

    }


    @Override
    void save(List<Vote> list) {
        voteRepository.save(list)
    }
}
