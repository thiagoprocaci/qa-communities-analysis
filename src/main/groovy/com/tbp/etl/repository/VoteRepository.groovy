package com.tbp.etl.repository

import com.tbp.etl.model.Community
import com.tbp.etl.model.Vote
import org.springframework.data.repository.CrudRepository


interface VoteRepository extends CrudRepository<Vote, Long> {

    Vote findByCommunityAndIdVoteCommunity(Community community, Long idVoteCommunity)

}