package com.tbp.repository

import com.tbp.model.Community
import org.springframework.data.repository.CrudRepository


interface CommunityRepository extends CrudRepository<Community, Integer> {

    Community findByName(String name)

}
