package com.tbp.extractor

import com.tbp.extractor.support.DateUtil
import com.tbp.extractor.support.LineSupport
import com.tbp.extractor.support.NumberUtil
import com.tbp.model.Community
import com.tbp.model.Post
import com.tbp.model.User
import com.tbp.model.Vote
import com.tbp.repository.CommunityRepository
import com.tbp.repository.PostRepository
import com.tbp.repository.UserRepository
import com.tbp.repository.VoteRepository
import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class VoteExtractor {

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
    VoteRepository voteRepository
    @Autowired
    PostRepository postRepository

    void execute(String community) {
        Community c = communityRepository.findByName(community)
        File inputFile = new File('src/main/resources/' + community + File.separator + 'Votes.xml');

        inputFile.eachLine{ it, i ->
            def line = lineSupport.prepareLine(it)

            if(line != null) {
                def reader = new StringReader(line)
                def doc = DOMBuilder.parse(reader)
                def row = doc.documentElement
                use(DOMCategory) {
                    Vote u = new Vote()
                    u.idVoteCommunity = numberUtil.toLong(row['@Id'])
                    if(voteRepository.findByCommunityAndIdVoteCommunity(c, u.idVoteCommunity) == null){
                        u.idPostCommunity = numberUtil.toLong(row['@PostId'])
                        u.creationDate = dateUtil.toDate(row['@CreationDate'])
                        u.voteType = numberUtil.toInteger(row['@VoteTypeId'])
                        u.idUserCommunity = numberUtil.toLong(row['@UserId'])
                        u.bountyAmount = numberUtil.toInteger(row['@BountyAmount'])
                        u.community = c
                        Integer userCommId = numberUtil.toLong(row['@UserId'])
                        if( userCommId != null) {
                            u.user = userRepository.findByCommunityAndIdUserCommunity(c, userCommId)
                        }
                        u.post = postRepository.findByCommunityAndIdPostCommunity(c, u.idPostCommunity)

                        voteRepository.save(u)

                    }

                }
            }
        }
    }

}
