package com.tbp.etl.extractor

import com.tbp.etl.extractor.support.LineSupport
import com.tbp.etl.model.Community
import com.tbp.etl.repository.CommunityRepository

import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

abstract class AbstractExtractor<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractExtractor.class);

    @Autowired
    CommunityRepository communityRepository
    @Autowired
    LineSupport lineSupport


    List<E> entityList = new ArrayList<>()

    abstract String getFileName()

    abstract E onExecute(def row, Community c)

    abstract void save(List<E> list)

    void cleanList() {
        if(entityList != null) {
            entityList.clear()
        }
    }

    void execute(String community) {
        LOGGER.info("Executing extractor of " + getFileName() + ". Community: " + community )

        Community c = communityRepository.findByName(community)
        File inputFile = new File('src/main/resources/' + community + File.separator + getFileName());
        int count = 0
        inputFile.eachLine{ it, i ->
            def line = lineSupport.prepareLine(it)
            if(line != null) {
                def reader = new StringReader(line)
                def doc = DOMBuilder.parse(reader)
                def row = doc.documentElement
                use(DOMCategory) {
                    E entity = onExecute(row, c)
                    if(entity != null) {
                        entityList.add(entity)
                    }
                }
                count++
                if(count == 1000) {
                    if(!entityList.isEmpty()) {
                        LOGGER.debug("Saving " + entityList.size() + " entities")
                        save(entityList)
                        cleanList()
                    }
                    count = 0
                }
            }
        }
        if(!entityList.isEmpty()) {
            LOGGER.debug("Saving " + entityList.size() + " entities")
            save(entityList)
            cleanList()
        }
        cleanList()
    }
}
