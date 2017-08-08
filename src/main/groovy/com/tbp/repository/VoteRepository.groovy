package com.tbp.repository

import com.tbp.model.Community
import com.tbp.model.Vote
import org.springframework.data.repository.CrudRepository


interface VoteRepository extends CrudRepository<Vote, Long> {

    Vote findByCommunityAndIdVoteCommunity(Community community, Long idVoteCommunity)

}