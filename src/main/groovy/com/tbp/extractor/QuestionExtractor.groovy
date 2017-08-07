package com.tbp.extractor

import com.tbp.extractor.support.DateUtil
import com.tbp.extractor.support.LineSupport
import com.tbp.extractor.support.NumberUtil
import com.tbp.model.Community
import com.tbp.model.Question
import com.tbp.model.User
import com.tbp.repository.CommunityRepository
import com.tbp.repository.QuestionRepository
import com.tbp.repository.UserRepository
import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class QuestionExtractor {

    @Autowired
    LineSupport lineSupport
    @Autowired
    DateUtil dateUtil
    @Autowired
    NumberUtil numberUtil
    @Autowired
    CommunityRepository communityRepository
    @Autowired
    UserRepository userRepository
    @Autowired
    QuestionRepository questionRepository

    void execute(String community) {
        Community c = communityRepository.findByName(community)
        File inputFile = new File('src/main/resources/' + community + File.separator + 'Posts.xml');

        inputFile.eachLine { it, i ->
            def line = lineSupport.prepareLine(it)

            if (line != null) {
                def reader = new StringReader(line)
                def doc = DOMBuilder.parse(reader)
                def row = doc.documentElement
                use(DOMCategory) {
                    Question q = new Question()
                    if(numberUtil.toInteger(row['@PostTypeId']) == 1) {
                        q.idQuestionCommunity = numberUtil.toLong(row['@Id'])
                        q.creationDate = dateUtil.toDate(row['@CreationDate'])
                        q.acceptedAnswerId = numberUtil.toLong(row['@AcceptedAnswerId'])
                        q.score = numberUtil.toInteger(row['@Score'])
                        q.viewCount = numberUtil.toInteger(row['@ViewCount'])
                        q.body = row['@Body']
                        q.idUserCommunity = numberUtil.toLong(row['@OwnerUserId'])
                        q.lastEditorUserCommunityId = numberUtil.toLong(row['@LastEditorUserId'])
                        q.lastEditorDisplayName = row['@LastEditorDisplayName']
                        q.lastEditDate = dateUtil.toDate(row['@LastEditDate'])
                        q.lastActivityDate = dateUtil.toDate(row['@LastActivityDate'])
                        q.communityOwnedDate = dateUtil.toDate(row['@CommunityOwnedDate'])
                        q.closedDate = dateUtil.toDate(row['@ClosedDate'])
                        q.title = row['@Title']
                        q.tags = row['@Tags']
                        q.answerCount = numberUtil.toInteger(row['@AnswerCount'])
                        q.commentCount = numberUtil.toInteger(row['@CommentCount'])
                        q.favoriteCount = numberUtil.toInteger(row['@FavoriteCount'])
                        q.community = c
                        q.user = userRepository.findByCommunityAndIdUserCommunity(c, q.idUserCommunity)
                        q.ari = null

                        if(questionRepository.findByCommunityAndIdQuestionCommunity(c, q.idQuestionCommunity) == null) {
                            questionRepository.save(q)
                        }
                    }
                }
            }
        }
    }

}
