package com.tbp.etl.extractor

import com.tbp.etl.model.Community
import com.tbp.etl.repository.CommunityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class CommunityExtractor {

    @Autowired
    CommunityRepository communityRepository

    Community execute(String community) {
        Community c = communityRepository.findByName(community)
        if(c == null) {
            c = new Community()
            c.name = community
            c = communityRepository.save(c)
        }
        return c
    }
}
