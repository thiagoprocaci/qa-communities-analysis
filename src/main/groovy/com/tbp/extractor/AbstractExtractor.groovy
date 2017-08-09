package com.tbp.extractor

import com.tbp.extractor.support.LineSupport
import com.tbp.model.Community

import com.tbp.repository.CommunityRepository
import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
import org.springframework.beans.factory.annotation.Autowired


abstract class AbstractExtractor {

    @Autowired
    CommunityRepository communityRepository
    @Autowired
    LineSupport lineSupport

    abstract String getFileName()
    abstract void onExecute(def row, Community c)


    void execute(String community) {
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
