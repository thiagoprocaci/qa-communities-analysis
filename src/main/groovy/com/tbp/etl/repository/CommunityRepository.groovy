package com.tbp.etl.repository

import com.tbp.etl.model.Community
import org.springframework.data.repository.CrudRepository


interface CommunityRepository extends CrudRepository<Community, Integer> {

    Community findByName(String name)

}
