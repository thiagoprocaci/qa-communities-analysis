package com.tbp.repository

import com.tbp.model.Community
import com.tbp.model.Question
import org.springframework.data.repository.CrudRepository


interface QuestionRepository extends CrudRepository<Question, Long> {

    Question findByCommunityAndIdQuestionCommunity(Community community, Long idQuestionCommunity)

}