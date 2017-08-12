package com.tbp.extractor

import com.tbp.extractor.support.DateUtil
import com.tbp.extractor.support.LineSupport
import com.tbp.extractor.support.NumberUtil
import com.tbp.extractor.support.StringSupport
import com.tbp.model.User
import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory


class XmlReader {


    LineSupport lineSupport = new LineSupport()
    NumberUtil numberUtil = new NumberUtil()
    DateUtil dateUtil = new DateUtil()
    StringSupport stringSupport = new StringSupport()

    File getInputFile(String community, String fileName) {
        return new File('src/main/resources/' + community + File.separator + fileName);
    }


    User getFromXml(String community, String fileName, long lineNumber) {
        File inputFile = getInputFile(community, fileName)
        long count = 0;
        User u = null
        inputFile.eachLine{ it, i ->
            def line = lineSupport.prepareLine(it)
            if(line != null) {
                count++;
                if (count == lineNumber) {
                    def reader = new StringReader(line)
                    def doc = DOMBuilder.parse(reader)
                    def row = doc.documentElement
                    use(DOMCategory) {
                        u = new User()
                        u.idUserCommunity = numberUtil.toLong(row['@Id'])
                        u.reputation = numberUtil.toInteger(row['@Reputation'])
                        u.creationDate = dateUtil.toDate(row['@CreationDate'])
                        u.displayName = stringSupport.prepare(row['@DisplayName'])
                        u.lastAccessDate = dateUtil.toDate(row['@LastAccessDate'])
                        u.websiteUrl = stringSupport.prepare(row['@WebsiteUrl'])
                        u.location = stringSupport.prepare(row['@Location'])
                        u.age = numberUtil.toInteger(row['@Age'])
                        u.aboutMe = stringSupport.prepare(row['@AboutMe'])
                        u.views = numberUtil.toInteger(row['@Views'])
                        u.upVotes = numberUtil.toInteger(row['@UpVotes'])
                        u.downVotes = numberUtil.toInteger(row['@DownVotes'])
                    }
                }
            }
        }
        return u
    }
}
