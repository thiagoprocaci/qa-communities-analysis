package com.tbp.etl.extractor

import com.tbp.etl.extractor.support.LineSupport
import com.tbp.etl.model.Community
import com.tbp.etl.repository.CommunityRepository

import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractExtractor.class);

    @Autowired
    CommunityRepository communityRepository
    @Autowired
    LineSupport lineSupport

    abstract String getFileName()
    abstract void onExecute(def row, Community c)


    void execute(String community) {
        LOGGER.info("Executing extractor of " + getFileName() + ". Community: " + community )
        Community c = communityRepository.findByName(community)
        File inputFile = new File('src/main/resources/' + community + File.separator + getFileName());

        inputFile.eachLine{ it, i ->
            def line = lineSupport.prepareLine(it)
            if(line != null) {
                def reader = new StringReader(line)
                def doc = DOMBuilder.parse(reader)
                def row = doc.documentElement
                use(DOMCategory) {
                    onExecute(row, c)
                }
            }
        }
    }
}
