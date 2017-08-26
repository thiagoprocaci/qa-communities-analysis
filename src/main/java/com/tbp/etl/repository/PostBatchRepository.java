package com.tbp.etl.repository;

import com.tbp.etl.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class PostBatchRepository {

    private static final String SQL_INSERT = "insert into post (id_post_comm, creation_date, post_type, parent_post_comm_id, accepted_answer_comm_id, score, view_count, body, id_user_community, last_editor_user_community_id, last_editor_display_name, last_edit_date, last_activity_date, community_owned_date, closed_date, title, tags, answer_count, comment_count, favorite_count, id_community, id_user, ari_text, smog_text, flesch_reading_text, flesch_kincaid_text, gunning_fog_text, coleman_liau_text, smog_index_text, characters_text, syllables_text, words_text, complexwords_text, sentences_text, ari_title, smog_title, flesch_reading_title, flesch_kincaid_title, gunning_fog_title, coleman_liau_title, smog_index_title, characters_title, syllables_title, words_title, complexwords_title, sentences_title ) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveBatch(final List<Post> postList) {
        final int batchSize = 500;
        for (int j = 0; j < postList.size(); j += batchSize) {
            final List<Post> batchList = postList.subList(j, j + batchSize > postList.size() ? postList.size() : j + batchSize);
            jdbcTemplate.batchUpdate(SQL_INSERT,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i)
                                throws SQLException {
                            Post post = batchList.get(i);
                            ps.setObject(1, post.getIdPostCommunity());
                            ps.setTimestamp(2, Post.getDateAsSql(post.getCreationDate()));
                            ps.setObject(3, post.getPostType());
                            ps.setObject(4, post.getParentPostCommunityId());
                            ps.setObject(5, post.getAcceptedAnswerId());
                            ps.setObject(6, post.getScore());
                            ps.setObject(7, post.getViewCount());
                            ps.setObject(8, post.getBody());
                            ps.setObject(9, post.getIdUserCommunity());
                            ps.setObject(10, post.getLastEditorUserCommunityId());
                            ps.setObject(11, post.getLastEditorDisplayName());
                            ps.setTimestamp(12, Post.getDateAsSql(post.getLastEditDate()));
                            ps.setTimestamp(13, Post.getDateAsSql(post.getLastActivityDate()));
                            ps.setTimestamp(14, Post.getDateAsSql(post.getCommunityOwnedDate()));
                            ps.setTimestamp(15, Post.getDateAsSql(post.getClosedDate()));
                            ps.setObject(16, post.getTitle());
                            ps.setObject(17, post.getTags());
                            ps.setObject(18, post.getAnswerCount());
                            ps.setObject(19, post.getCommentCount());
                            ps.setObject(20, post.getFavoriteCount());
                            ps.setObject(21, post.getCommunity().getId());
                            if(post.getUser() != null) {
                                ps.setObject(22, post.getUser().getId());
                            } else {
                                ps.setObject(22, null);
                            }
                            ps.setObject(23, post.getAriText());
                            ps.setObject(24, post.getSmogText());
                            ps.setObject(25, post.getFleschReadingText());
                            ps.setObject(26, post.getFleschKincaidText());
                            ps.setObject(27, post.getGunningFogText());
                            ps.setObject(28, post.getColemanLiauText());
                            ps.setObject(29, post.getSmogIndexText());
                            ps.setObject(30, post.getCharactersText());
                            ps.setObject(31, post.getSyllablesText());
                            ps.setObject(32, post.getWordsText());
                            ps.setObject(33, post.getComplexWordsText());
                            ps.setObject(34, post.getSentencesText());
                            ps.setObject(35, post.getAriTitle());
                            ps.setObject(36, post.getSmogTitle());
                            ps.setObject(37, post.getFleschReadingTitle());
                            ps.setObject(38, post.getFleschKincaidTitle());
                            ps.setObject(39, post.getGunningFogTitle());
                            ps.setObject(40, post.getColemanLiauTitle());
                            ps.setObject(41, post.getSmogIndexTitle());
                            ps.setObject(42, post.getCharactersTitle());
                            ps.setObject(43, post.getSyllablesTitle());
                            ps.setObject(44, post.getWordsTitle());
                            ps.setObject(45, post.getComplexWordsTitle());
                            ps.setObject(46, post.getSentencesTitle());
                        }

                        @Override
                        public int getBatchSize() {
                            return batchList.size();
                        }
                    });
        }
    }
}
