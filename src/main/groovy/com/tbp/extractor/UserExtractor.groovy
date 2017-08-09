package com.tbp.extractor

import com.tbp.extractor.support.DateUtil
import com.tbp.extractor.support.NumberUtil
import com.tbp.model.Community
import com.tbp.model.User
import com.tbp.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class UserExtractor extends AbstractExtractor {

    @Autowired
    UserRepository userRepository
    @Autowired
    DateUtil dateUtil
    @Autowired
    NumberUtil numberUtil

    @Override
    String getFileName() {
        return 'Users.xml'
    }

    @Override
    void onExecute(Object row, Community c) {
        Long idUserCommunity = numberUtil.toLong(row['@Id'])
        if(userRepository.findByCommunityAndIdUserCommunity(c, idUserCommunity) == null){
            User u = new User()
            u.idUserCommunity = idUserCommunity
            u.reputation = numberUtil.toInteger(row['@Reputation'])
            u.creationDate = dateUtil.toDate(row['@CreationDate'])
            u.displayName = row['@DisplayName']
            u.lastAccessDate = dateUtil.toDate(row['@LastAccessDate'])
            u.websiteUrl = row['@WebsiteUrl']
            u.location = row['@Location']
            u.age = numberUtil.toInteger(row['@Age'])
            u.aboutMe = row['@AboutMe']
            u.views = numberUtil.toInteger(row['@Views'])
            u.upVotes = numberUtil.toInteger(row['@UpVotes'])
            u.downVotes = numberUtil.toInteger(row['@DownVotes'])
            u.community = c
            userRepository.save(u)
        }
    }
}
