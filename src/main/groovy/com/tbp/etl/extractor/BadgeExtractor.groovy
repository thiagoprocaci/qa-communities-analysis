package com.tbp.etl.extractor

import com.tbp.etl.extractor.support.DateUtil
import com.tbp.etl.extractor.support.NumberUtil
import com.tbp.etl.extractor.support.StringSupport
import com.tbp.etl.model.Badge
import com.tbp.etl.model.Community
import com.tbp.etl.repository.BadgeRepository
import com.tbp.etl.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BadgeExtractor  extends AbstractExtractor<Badge> {

    @Autowired
    DateUtil dateUtil
    @Autowired
    NumberUtil numberUtil
    @Autowired
    UserRepository userRepository
    @Autowired
    StringSupport stringSupport
    @Autowired
    BadgeRepository badgeRepository

    @Override
    String getFileName() {
        return "Badges.xml"
    }

    @Override
    Badge onExecute(Object row, Community c) {
        Long idBadgeCommunity = numberUtil.toLong(row['@Id'])
        Badge badge = badgeRepository.findByCommunityAndIdBadgeCommunity(c, idBadgeCommunity)
        if(badge == null) {
            badge = new Badge()
            badge.idBadgeCommunity = idBadgeCommunity
            badge.date = dateUtil.toDate(row['@Date'])
            badge.name = stringSupport.prepare(row['@Name'])
            badge.clazz = stringSupport.prepare(row['@Class'])
            badge.tagBased = stringSupport.prepare(row['@TagBased'])
            badge.idUserCommunity = numberUtil.toLong(row['@UserId'])
            badge.community = c
            if(badge.idUserCommunity != null) {
                badge.user = userRepository.findByCommunityAndIdUserCommunity(c, badge.idUserCommunity)
            }
            return badge
        }
        return null
    }

    @Override
    void save(List<Badge> list) {
        badgeRepository.save(list)
    }
}
