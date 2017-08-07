package com.tbp.repository

import com.tbp.model.Community
import com.tbp.model.User
import org.springframework.data.repository.CrudRepository


interface UserRepository extends CrudRepository<User, Long> {

    User findByCommunityAndIdUserCommunity(Community community, Long idUserCommunity)

}