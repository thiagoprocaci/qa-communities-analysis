package com.tbp.extractor

import com.tbp.etl.extractor.support.DateUtil
import com.tbp.etl.extractor.support.LineSupport
import com.tbp.etl.extractor.support.NumberUtil
import com.tbp.etl.extractor.support.StringSupport
import com.tbp.etl.model.*
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


    Vote getVoteFromXml(String community, String fileName, long lineNumber) {
        File inputFile = getInputFile(community, fileName)
        long count = 0;
        Vote vote = null
        inputFile.eachLine{ it, i ->
            def line = lineSupport.prepareLine(it)
            if(line != null) {
                count++;
                if (count == lineNumber) {
                    def reader = new StringReader(line)
                    def doc = DOMBuilder.parse(reader)
                    def row = doc.documentElement

                    use(DOMCategory) {
                        vote = new Vote()
                        vote.idVoteCommunity = numberUtil.toLong(row['@Id'])
                        vote.idPostCommunity = numberUtil.toLong(row['@PostId'])
                        vote.creationDate = dateUtil.toDate(row['@CreationDate'])
                        vote.voteType = numberUtil.toInteger(row['@VoteTypeId'])
                        vote.idUserCommunity = numberUtil.toLong(row['@UserId'])
                        vote.bountyAmount = numberUtil.toInteger(row['@BountyAmount'])
                    }
                }
            }
        }
        return vote
    }

    PostLink getPostLinkFromXml(String community, String fileName, long lineNumber) {
        File inputFile = getInputFile(community, fileName)
        long count = 0;
        PostLink postLink = null
        inputFile.eachLine{ it, i ->
            def line = lineSupport.prepareLine(it)
            if(line != null) {
                count++;
                if (count == lineNumber) {
                    def reader = new StringReader(line)
                    def doc = DOMBuilder.parse(reader)
                    def row = doc.documentElement
                    use(DOMCategory) {
                        postLink = new PostLink()
                        postLink.idPostLinkCommunity = numberUtil.toLong(row['@Id'])
                        postLink.creationDate = dateUtil.toDate(row['@CreationDate'])
                        postLink.idPostCommunity = numberUtil.toLong(row['@PostId'])
                        postLink.idRelatedPostCommunity = numberUtil.toLong(row['@RelatedPostId'])
                        postLink.postLinkType = numberUtil.toInteger(row['@LinkTypeId'])
                    }
                }
            }
        }
        return postLink
    }


    Comment getCommentFromXml(String community, String fileName, long lineNumber) {
        File inputFile = getInputFile(community, fileName)
        long count = 0;
        Comment comment = null
        inputFile.eachLine{ it, i ->
            def line = lineSupport.prepareLine(it)
            if(line != null) {
                count++;
                if (count == lineNumber) {
                    def reader = new StringReader(line)
                    def doc = DOMBuilder.parse(reader)
                    def row = doc.documentElement
                    use(DOMCategory) {
                        comment = new Comment()
                        comment.idCommentCommunity = numberUtil.toLong(row['@Id'])
                        comment.idPostCommunity = numberUtil.toLong(row['@PostId'])
                        comment.creationDate = dateUtil.toDate(row['@CreationDate'])
                        comment.score = numberUtil.toInteger(row['@Score'])
                        comment.text = stringSupport.prepare(row['@Text'])
                        comment.idUserCommunity = numberUtil.toLong(row['@UserId'])
                    }
                }
            }
        }
        return comment
    }


    Post getPostFromXml(String community, String fileName, long lineNumber) {
        File inputFile = getInputFile(community, fileName)
        long count = 0;
        Post post = null
        inputFile.eachLine{ it, i ->
            def line = lineSupport.prepareLine(it)
            if(line != null) {
                count++;
                if (count == lineNumber) {
                    def reader = new StringReader(line)
                    def doc = DOMBuilder.parse(reader)
                    def row = doc.documentElement
                    use(DOMCategory) {
                        post = new Post()
                        post.idPostCommunity = numberUtil.toLong(row['@Id'])
                        post.creationDate = dateUtil.toDate(row['@CreationDate'])
                        post.acceptedAnswerId = numberUtil.toLong(row['@AcceptedAnswerId'])
                        post.score = numberUtil.toInteger(row['@Score'])
                        post.viewCount = numberUtil.toInteger(row['@ViewCount'])
                        post.body = stringSupport.prepare(row['@Body'])
                        post.idUserCommunity = numberUtil.toLong(row['@OwnerUserId'])
                        post.lastEditorUserCommunityId = numberUtil.toLong(row['@LastEditorUserId'])
                        post.lastEditorDisplayName = row['@LastEditorDisplayName']
                        post.lastEditDate = dateUtil.toDate(row['@LastEditDate'])
                        post.lastActivityDate = dateUtil.toDate(row['@LastActivityDate'])
                        post.communityOwnedDate = dateUtil.toDate(row['@CommunityOwnedDate'])
                        post.closedDate = dateUtil.toDate(row['@ClosedDate'])
                        post.title = stringSupport.prepare(row['@Title'])
                        post.tags = stringSupport.prepare(row['@Tags'])
                        post.answerCount = numberUtil.toInteger(row['@AnswerCount'])
                        post.commentCount = numberUtil.toInteger(row['@CommentCount'])
                        post.favoriteCount = numberUtil.toInteger(row['@FavoriteCount'])
                        post.postType = numberUtil.toInteger(row['@PostTypeId'])
                        post.parentPostCommunityId = numberUtil.toLong(row['@ParentId'])
                    }
                }
            }
        }
        return post
    }


    User getUserFromXml(String community, String fileName, long lineNumber) {
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
