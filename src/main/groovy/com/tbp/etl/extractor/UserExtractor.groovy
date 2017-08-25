package com.tbp.etl.extractor

import com.tbp.etl.repository.UserBatchRepository
import com.tbp.etl.extractor.support.DateUtil
import com.tbp.etl.extractor.support.NumberUtil
import com.tbp.etl.extractor.support.StringSupport
import com.tbp.etl.model.Community
import com.tbp.etl.model.User

import com.tbp.etl.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class UserExtractor extends AbstractExtractor<User> {

    @Autowired
    UserRepository userRepository
    @Autowired
    DateUtil dateUtil
    @Autowired
    NumberUtil numberUtil
    @Autowired
    StringSupport stringSupport
    @Autowired
    UserBatchRepository userBatchRepository

    @Override
    String getFileName() {
        return 'Users.xml'
    }

    @Override
    User onExecute(Object row, Community c) {
        Long idUserCommunity = numberUtil.toLong(row['@Id'])
        User u = userRepository.findByCommunityAndIdUserCommunity(c, idUserCommunity)
        if(u == null) {
            u = new User()
        }
        u.idUserCommunity = idUserCommunity
        u.reputation = numberUtil.toInteger(row['@Reputation'])
        u.creationDate = dateUtil.toDate(row['@CreationDate'])
        u.displayName = stringSupport.prepare(row['@DisplayName'])
        u.lastAccessDate = dateUtil.toDate(row['@LastAccessDate'])
        u.websiteUrl = stringSupport.prepare(row['@WebsiteUrl'])
        u.location = stringSupport.prepare(row['@Location'])
        u.age = numberUtil.toInteger(row['@Age'])
        u.aboutMe = stringSupport.prepare(row['@AboutMe'])
        u.views = numberUtil.toInteger(row['@Views'])
        u.upVotes = numberUtil.toInteger(row['@UpVotes'])
        u.downVotes = numberUtil.toInteger(row['@DownVotes'])
        u.community = c
        return u
    }

    @Override
    void save(List<User> list) {
        userBatchRepository.saveBatch(list)
    }
}
