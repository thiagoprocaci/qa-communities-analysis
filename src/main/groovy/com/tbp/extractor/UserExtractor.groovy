package com.tbp.extractor

import com.tbp.extractor.support.DateUtil
import com.tbp.extractor.support.LineSupport
import com.tbp.extractor.support.NumberUtil
import com.tbp.model.Community
import com.tbp.model.User
import com.tbp.repository.CommunityRepository
import com.tbp.repository.UserRepository
import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class UserExtractor {
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

    void execute(String community) {
       Community c = communityRepository.findByName(community)
        File inputFile = new File('src/main/resources/' + community + File.separator + 'Users.xml');

       inputFile.eachLine{ it, i ->
           def line = lineSupport.prepareLine(it)

           if(line != null) {
               def reader = new StringReader(line)
               def doc = DOMBuilder.parse(reader)
               def row = doc.documentElement
               use(DOMCategory) {
                   User u = new User()
                   u.idUserCommunity = numberUtil.toLong(row['@Id'])
                   if(userRepository.findByCommunityAndIdUserCommunity(c, u.idUserCommunity) == null){
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
       }
   }

}
