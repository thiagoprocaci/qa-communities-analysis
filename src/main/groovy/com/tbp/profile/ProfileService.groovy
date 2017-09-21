package com.tbp.profile

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.springframework.stereotype.Component

import javax.sql.DataSource

@Component
class ProfileService {

    @Autowired
    DataSource dataSource
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileService.class)

    void execute() {
        ClassPathResource resource = new ClassPathResource("sql/update_profile.sql")
        LOGGER.info("Running " + resource.getFilename())
        ScriptUtils.executeSqlScript(dataSource.getConnection(), resource)
    }
}
