package com.tbp.etl.repository

import com.tbp.etl.model.Community
import com.tbp.etl.model.User
import org.springframework.data.repository.CrudRepository


interface UserRepository extends CrudRepository<User, Long> {

    User findByCommunityAndIdUserCommunity(Community community, Long idUserCommunity)

}