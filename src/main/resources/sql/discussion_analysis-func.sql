CREATE OR REPLACE FUNCTION discussion_analysis_2(community_name text)
 RETURNS TABLE(
id_question  bigint,
period  integer,
finished  integer,
thread_length  bigint,
total_top_5_participation  bigint,
question_made_by_top_5  bigint,
answers_by_top_5  bigint,
comments_by_top_5  bigint,
question_comment_by_top_5  bigint,
answer_comment_by_top_5  bigint,
total_top_5_10_participation  bigint,
question_made_by_top_5_10  bigint,
answers_by_top_5_10  bigint,
comments_by_top_5_10  bigint,
question_comment_by_top_5_10  bigint,
answer_comment_by_top_5_10  bigint,
total_top_10_15_participation  bigint,
question_made_by_top_10_15  bigint,
answers_by_top_10_15  bigint,
comments_by_top_10_15  bigint,
question_comment_by_top_10_15  bigint,
answer_comment_by_top_10_15  bigint,
total_top_15_20_participation  bigint,
question_made_by_top_15_20  bigint,
answers_by_top_15_20  bigint,
comments_by_top_15_20  bigint,
question_comment_by_top_15_20  bigint,
answer_comment_by_top_15_20  bigint,
total_top_20_25_participation  bigint,
question_made_by_top_20_25  bigint,
answers_by_top_20_25  bigint,
comments_by_top_20_25  bigint,
question_comment_by_top_20_25  bigint,
answer_comment_by_top_20_25  bigint,
total_top_25_30_participation  bigint,
question_made_by_top_25_30  bigint,
answers_by_top_25_30  bigint,
comments_by_top_25_30  bigint,
question_comment_by_top_25_30  bigint,
answer_comment_by_top_25_30  bigint,
total_top_30_35_participation  bigint,
question_made_by_top_30_35  bigint,
answers_by_top_30_35  bigint,
comments_by_top_30_35  bigint,
question_comment_by_top_30_35  bigint,
answer_comment_by_top_30_35  bigint,
total_top_35_40_participation  bigint,
question_made_by_top_35_40  bigint,
answers_by_top_35_40  bigint,
comments_by_top_35_40  bigint,
question_comment_by_top_35_40  bigint,
answer_comment_by_top_35_40  bigint,
total_top_40_45_participation  bigint,
question_made_by_top_40_45  bigint,
answers_by_top_40_45  bigint,
comments_by_top_40_45  bigint,
question_comment_by_top_40_45  bigint,
answer_comment_by_top_40_45  bigint,
total_top_45_50_participation  bigint,
question_made_by_top_45_50  bigint,
answers_by_top_45_50  bigint,
comments_by_top_45_50  bigint,
question_comment_by_top_45_50  bigint,
answer_comment_by_top_45_50  bigint,
total_top_50_100_participation  bigint,
question_made_by_top_50_100  bigint,
answers_by_top_50_100  bigint,
comments_by_top_50_100  bigint,
question_comment_by_top_50_100  bigint,
answer_comment_by_top_50_100  bigint,

total_NULL_USER_participation bigint ,
question_made_by_NULL_USER bigint ,
answers_by_NULL_USER bigint ,
comments_by_NULL_USER bigint ,
question_comment_by_NULL_USER bigint ,
answer_comment_by_NULL_USER bigint ,

total_answer_count  integer,
total_answer_comment_count  bigint,
total_question_comment_count  bigint



)
LANGUAGE sql
AS $function$

with QUESTIONS as (
	select *
	from post p
	where
	p.post_type = 1
	and p.id_community in (select id from community where name = $1)
) ,

ANSWER_COMMENTS as (
	select question.id, count(*)  as answer_comment_count from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	group by question.id

),
QUESTION_COMMENTS as (
	select question.id, count(*)  as question_comment_count from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	group by question.id

),
USER_CLASSIFICATION as (
	select * from comm_user_ranking_profile($1)
),
QUESTION_MADE_BY_TOP_5 as (
	select question.id, count(*) as question_made_by_top_5 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_5 = 1)
	group by question.id
),
ANSWERS_BY_TOP_5 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_5 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_5 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_5 as (
	select question.id, count(*)  as question_comment_by_top_5 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_5 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_5 as (
	select question.id, count(*)  as answer_comment_by_top_5 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_5 = 1)
	group by question.id
),
QUESTION_MADE_BY_TOP_5_10 as (
	select question.id, count(*) as question_made_by_top_5_10 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_5_10 = 1)
	group by question.id
),
ANSWERS_BY_TOP_5_10 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_5_10 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_5_10 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_5_10 as (
	select question.id, count(*)  as question_comment_by_top_5_10 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_5_10 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_5_10 as (
	select question.id, count(*)  as answer_comment_by_top_5_10 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_5_10 = 1)
	group by question.id
),
QUESTION_MADE_BY_TOP_10_15 as (
	select question.id, count(*) as question_made_by_top_10_15 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_10_15 = 1)
	group by question.id
),
ANSWERS_BY_TOP_10_15 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_10_15 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_10_15 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_10_15 as (
	select question.id, count(*)  as question_comment_by_top_10_15 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_10_15 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_10_15 as (
	select question.id, count(*)  as answer_comment_by_top_10_15 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_10_15 = 1)
	group by question.id
),
QUESTION_MADE_BY_TOP_15_20 as (
	select question.id, count(*) as question_made_by_top_15_20 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_15_20 = 1)
	group by question.id
),
ANSWERS_BY_TOP_15_20 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_15_20 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_15_20 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_15_20 as (
	select question.id, count(*)  as question_comment_by_top_15_20 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_15_20 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_15_20 as (
	select question.id, count(*)  as answer_comment_by_top_15_20 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_15_20 = 1)
	group by question.id
),
QUESTION_MADE_BY_TOP_20_25 as (
	select question.id, count(*) as question_made_by_top_20_25 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_20_25 = 1)
	group by question.id
),
ANSWERS_BY_TOP_20_25 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_20_25 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_20_25 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_20_25 as (
	select question.id, count(*)  as question_comment_by_top_20_25 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_20_25 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_20_25 as (
	select question.id, count(*)  as answer_comment_by_top_20_25 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_20_25 = 1)
	group by question.id
),
QUESTION_MADE_BY_TOP_25_30 as (
	select question.id, count(*) as question_made_by_top_25_30 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_25_30 = 1)
	group by question.id
),
ANSWERS_BY_TOP_25_30 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_25_30 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_25_30 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_25_30 as (
	select question.id, count(*)  as question_comment_by_top_25_30 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_25_30 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_25_30 as (
	select question.id, count(*)  as answer_comment_by_top_25_30 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_25_30 = 1)
	group by question.id
),
QUESTION_MADE_BY_TOP_30_35 as (
	select question.id, count(*) as question_made_by_top_30_35 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_30_35 = 1)
	group by question.id
),
ANSWERS_BY_TOP_30_35 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_30_35 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_30_35 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_30_35 as (
	select question.id, count(*)  as question_comment_by_top_30_35 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_30_35 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_30_35 as (
	select question.id, count(*)  as answer_comment_by_top_30_35 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_30_35 = 1)
	group by question.id
),
QUESTION_MADE_BY_TOP_35_40 as (
	select question.id, count(*) as question_made_by_top_35_40 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_35_40 = 1)
	group by question.id
),
ANSWERS_BY_TOP_35_40 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_35_40 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_35_40 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_35_40 as (
	select question.id, count(*)  as question_comment_by_top_35_40 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_35_40 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_35_40 as (
	select question.id, count(*)  as answer_comment_by_top_35_40 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_35_40 = 1)
	group by question.id
),
QUESTION_MADE_BY_TOP_40_45 as (
	select question.id, count(*) as question_made_by_top_40_45 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_40_45 = 1)
	group by question.id
),
ANSWERS_BY_TOP_40_45 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_40_45 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_40_45 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_40_45 as (
	select question.id, count(*)  as question_comment_by_top_40_45 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_40_45 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_40_45 as (
	select question.id, count(*)  as answer_comment_by_top_40_45 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_40_45 = 1)
	group by question.id
),
QUESTION_MADE_BY_TOP_45_50 as (
	select question.id, count(*) as question_made_by_top_45_50 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_45_50 = 1)
	group by question.id
),
ANSWERS_BY_TOP_45_50 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_45_50 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_45_50 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_45_50 as (
	select question.id, count(*)  as question_comment_by_top_45_50 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_45_50 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_45_50 as (
	select question.id, count(*)  as answer_comment_by_top_45_50 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_45_50 = 1)
	group by question.id
),
QUESTION_MADE_BY_TOP_50_100 as (
	select question.id, count(*) as question_made_by_top_50_100 from QUESTIONS question
	where question.id_user in (select u.id from USER_CLASSIFICATION u where u.top_50_100 = 1)
	group by question.id
),
ANSWERS_BY_TOP_50_100 as (
	select p.parent_post_comm_id, count(*) as answers_by_top_50_100 from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user in (select u.id from USER_CLASSIFICATION u where u.top_50_100 = 1)
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_TOP_50_100 as (
	select question.id, count(*)  as question_comment_by_top_50_100 from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_50_100 = 1)
	group by question.id
),
ANSWER_COMMENTS_BY_TOP_50_100 as (
	select question.id, count(*)  as answer_comment_by_top_50_100 from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user in (select u.id from USER_CLASSIFICATION u where u.top_50_100 = 1)
	group by question.id
),
QUESTION_MADE_BY_NULL_USER as (
	select question.id, count(*) as question_made_by_NULL_USER from QUESTIONS question
	where question.id_user is null
	group by question.id
),
ANSWERS_BY_NULL_USER as (
	select p.parent_post_comm_id, count(*) as answers_by_NULL_USER from post p
	where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user is null
	group by p.parent_post_comm_id
),
QUESTION_COMMENTS_BY_NULL_USER as (
	select question.id, count(*)  as question_comment_by_NULL_USER from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user is null
	group by question.id
),
ANSWER_COMMENTS_BY_NULL_USER as (
	select question.id, count(*)  as answer_comment_by_NULL_USER from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = $1)
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	and c.id_user is null
	group by question.id
)

select q.id as id_question, q.period,
		case
			when q.accepted_answer_comm_id is not null then 1
			else 0
		end as finished,
	   (1 + COALESCE(q.answer_count,0) + COALESCE(a.answer_comment_count,0) + COALESCE(qc.question_comment_count,0)) as thread_length,

	   (COALESCE(qtop5.question_made_by_top_5,0) + COALESCE(atop5.answers_by_top_5,0) + COALESCE(qctop5.question_comment_by_top_5,0) + COALESCE(actop5.answer_comment_by_top_5,0)) as total_top_5_participation,


	   COALESCE(qtop5.question_made_by_top_5,0) as question_made_by_top_5,
	   COALESCE(atop5.answers_by_top_5,0) as answers_by_top_5,
	   (COALESCE(qctop5.question_comment_by_top_5,0) + COALESCE(actop5.answer_comment_by_top_5,0)) as comments_by_top_5,
	   COALESCE(qctop5.question_comment_by_top_5,0) as question_comment_by_top_5,
	   COALESCE(actop5.answer_comment_by_top_5,0) as answer_comment_by_top_5,

	   (COALESCE(qtop510.question_made_by_top_5_10,0) + COALESCE(atop510.answers_by_top_5_10,0) + COALESCE(qctop510.question_comment_by_top_5_10,0) + COALESCE(actop510.answer_comment_by_top_5_10,0)) as total_top_5_10_participation,
	   COALESCE(qtop510.question_made_by_top_5_10,0) as question_made_by_top_5_10,
	   COALESCE(atop510.answers_by_top_5_10,0) as answers_by_top_5_10,
	   (COALESCE(qctop510.question_comment_by_top_5_10,0) + COALESCE(actop510.answer_comment_by_top_5_10,0)) as comments_by_top_5_10,
		COALESCE(qctop510.question_comment_by_top_5_10,0) as question_comment_by_top_5_10,
		COALESCE(actop510.answer_comment_by_top_5_10,0) as answer_comment_by_top_5_10,

		(COALESCE(qtop1015.question_made_by_top_10_15,0) + COALESCE(atop1015.answers_by_top_10_15,0) + COALESCE(qctop1015.question_comment_by_top_10_15,0) + COALESCE(actop1015.answer_comment_by_top_10_15,0)) as total_top_10_15_participation,
		COALESCE(qtop1015.question_made_by_top_10_15,0) as question_made_by_top_10_15,
		COALESCE(atop1015.answers_by_top_10_15,0) as answers_by_top_10_15,
		(COALESCE(qctop1015.question_comment_by_top_10_15,0) + COALESCE(actop1015.answer_comment_by_top_10_15,0)) as comments_by_top_10_15,
		COALESCE(qctop1015.question_comment_by_top_10_15,0) as question_comment_by_top_10_15,
		COALESCE(actop1015.answer_comment_by_top_10_15,0) as answer_comment_by_top_10_15,

		(COALESCE(qtop1520.question_made_by_top_15_20,0) + COALESCE(atop1520.answers_by_top_15_20,0) + COALESCE(qctop1520.question_comment_by_top_15_20,0) + COALESCE(actop1520.answer_comment_by_top_15_20,0)) as total_top_15_20_participation,
		COALESCE(qtop1520.question_made_by_top_15_20,0) as question_made_by_top_15_20,
		COALESCE(atop1520.answers_by_top_15_20,0) as answers_by_top_15_20,
		(COALESCE(qctop1520.question_comment_by_top_15_20,0) + COALESCE(actop1520.answer_comment_by_top_15_20,0)) as comments_by_top_15_20,
		COALESCE(qctop1520.question_comment_by_top_15_20,0) as question_comment_by_top_15_20,
		COALESCE(actop1520.answer_comment_by_top_15_20,0) as answer_comment_by_top_15_20,

		(COALESCE(qtop2025.question_made_by_top_20_25,0) + COALESCE(atop2025.answers_by_top_20_25,0) + COALESCE(qctop2025.question_comment_by_top_20_25,0) + COALESCE(actop2025.answer_comment_by_top_20_25,0)) as total_top_20_25_participation,
		COALESCE(qtop2025.question_made_by_top_20_25,0) as question_made_by_top_20_25,
		COALESCE(atop2025.answers_by_top_20_25,0) as answers_by_top_20_25,
		(COALESCE(qctop2025.question_comment_by_top_20_25,0) + COALESCE(actop2025.answer_comment_by_top_20_25,0)) as comments_by_top_20_25,
		COALESCE(qctop2025.question_comment_by_top_20_25,0) as question_comment_by_top_20_25,
		COALESCE(actop2025.answer_comment_by_top_20_25,0) as answer_comment_by_top_20_25,

		(COALESCE(qtop2530.question_made_by_top_25_30,0) + COALESCE(atop2530.answers_by_top_25_30,0) + COALESCE(qctop2530.question_comment_by_top_25_30,0) + COALESCE(actop2530.answer_comment_by_top_25_30,0)) as total_top_25_30_participation,
		COALESCE(qtop2530.question_made_by_top_25_30,0) as question_made_by_top_25_30,
		COALESCE(atop2530.answers_by_top_25_30,0) as answers_by_top_25_30,
		(COALESCE(qctop2530.question_comment_by_top_25_30,0) + COALESCE(actop2530.answer_comment_by_top_25_30,0)) as comments_by_top_25_30,
		COALESCE(qctop2530.question_comment_by_top_25_30,0) as question_comment_by_top_25_30,
		COALESCE(actop2530.answer_comment_by_top_25_30,0) as answer_comment_by_top_25_30,

		(COALESCE(qtop3035.question_made_by_top_30_35,0) + COALESCE(atop3035.answers_by_top_30_35,0) + COALESCE(qctop3035.question_comment_by_top_30_35,0) + COALESCE(actop3035.answer_comment_by_top_30_35,0)) as total_top_30_35_participation,
		COALESCE(qtop3035.question_made_by_top_30_35,0) as question_made_by_top_30_35,
		COALESCE(atop3035.answers_by_top_30_35,0) as answers_by_top_30_35,
		(COALESCE(qctop3035.question_comment_by_top_30_35,0) + COALESCE(actop3035.answer_comment_by_top_30_35,0)) as comments_by_top_30_35,
		COALESCE(qctop3035.question_comment_by_top_30_35,0) as question_comment_by_top_30_35,
		COALESCE(actop3035.answer_comment_by_top_30_35,0) as answer_comment_by_top_30_35,

		(COALESCE(qtop3540.question_made_by_top_35_40,0) + COALESCE(atop3540.answers_by_top_35_40,0) + COALESCE(qctop3540.question_comment_by_top_35_40,0) + COALESCE(actop3540.answer_comment_by_top_35_40,0)) as total_top_35_40_participation,
		COALESCE(qtop3540.question_made_by_top_35_40,0) as question_made_by_top_35_40,
		COALESCE(atop3540.answers_by_top_35_40,0) as answers_by_top_35_40,
		(COALESCE(qctop3540.question_comment_by_top_35_40,0) + COALESCE(actop3540.answer_comment_by_top_35_40,0)) as comments_by_top_35_40,
		COALESCE(qctop3540.question_comment_by_top_35_40,0) as question_comment_by_top_35_40,
		COALESCE(actop3540.answer_comment_by_top_35_40,0) as answer_comment_by_top_35_40,

		(COALESCE(qtop4045.question_made_by_top_40_45,0) + COALESCE(atop4045.answers_by_top_40_45,0) + COALESCE(qctop4045.question_comment_by_top_40_45,0) + COALESCE(actop4045.answer_comment_by_top_40_45,0)) as total_top_40_45_participation,
		COALESCE(qtop4045.question_made_by_top_40_45,0) as question_made_by_top_40_45,
		COALESCE(atop4045.answers_by_top_40_45,0) as answers_by_top_40_45,
		(COALESCE(qctop4045.question_comment_by_top_40_45,0) + COALESCE(actop4045.answer_comment_by_top_40_45,0)) as comments_by_top_40_45,
		COALESCE(qctop4045.question_comment_by_top_40_45,0) as question_comment_by_top_40_45,
		COALESCE(actop4045.answer_comment_by_top_40_45,0) as answer_comment_by_top_40_45,

		(COALESCE(qtop4550.question_made_by_top_45_50,0) + COALESCE(atop4550.answers_by_top_45_50,0) + COALESCE(qctop4550.question_comment_by_top_45_50,0) + COALESCE(actop4550.answer_comment_by_top_45_50,0)) as total_top_45_50_participation,
		COALESCE(qtop4550.question_made_by_top_45_50,0) as question_made_by_top_45_50,
		COALESCE(atop4550.answers_by_top_45_50,0) as answers_by_top_45_50,
		(COALESCE(qctop4550.question_comment_by_top_45_50,0) + COALESCE(actop4550.answer_comment_by_top_45_50,0)) as comments_by_top_45_50,
		COALESCE(qctop4550.question_comment_by_top_45_50,0) as question_comment_by_top_45_50,
		COALESCE(actop4550.answer_comment_by_top_45_50,0) as answer_comment_by_top_45_50,

		(COALESCE(qtop50100.question_made_by_top_50_100,0) + COALESCE(atop50100.answers_by_top_50_100,0) + COALESCE(qctop50100.question_comment_by_top_50_100,0) + COALESCE(actop50100.answer_comment_by_top_50_100,0)) as total_top_50_100_participation,
		COALESCE(qtop50100.question_made_by_top_50_100,0) as question_made_by_top_50_100,
		COALESCE(atop50100.answers_by_top_50_100,0) as answers_by_top_50_100,
		(COALESCE(qctop50100.question_comment_by_top_50_100,0) + COALESCE(actop50100.answer_comment_by_top_50_100,0)) as comments_by_top_50_100,
		COALESCE(qctop50100.question_comment_by_top_50_100,0) as question_comment_by_top_50_100,
		COALESCE(actop50100.answer_comment_by_top_50_100,0) as answer_comment_by_top_50_100,

		(COALESCE(qnullUser.question_made_by_NULL_USER,0) + COALESCE(anullUser.answers_by_NULL_USER,0) + COALESCE(qcnullUser.question_comment_by_NULL_USER,0) + COALESCE(acnullUser.answer_comment_by_NULL_USER,0)) as total_NULL_USER_participation,
		COALESCE(qnullUser.question_made_by_NULL_USER,0) as question_made_by_NULL_USER,
		COALESCE(anullUser.answers_by_NULL_USER,0) as answers_by_NULL_USER,
		(COALESCE(qcnullUser.question_comment_by_NULL_USER,0) + COALESCE(acnullUser.answer_comment_by_NULL_USER,0)) as comments_by_NULL_USER,
		COALESCE(qcnullUser.question_comment_by_NULL_USER,0) as question_comment_by_NULL_USER,
		COALESCE(acnullUser.answer_comment_by_NULL_USER,0) as answer_comment_by_NULL_USER,

	   COALESCE(q.answer_count,0) as total_answer_count,
	   COALESCE(a.answer_comment_count,0) as total_answer_comment_count,
	   COALESCE(qc.question_comment_count,0) as total_question_comment_count
	from  QUESTIONS q
	left join ANSWER_COMMENTS a on a.id = q.id
	left join QUESTION_COMMENTS qc on qc.id = q.id

	left join QUESTION_MADE_BY_TOP_5 qtop5 on qtop5.id = q.id
	left join ANSWERS_BY_TOP_5 atop5 on atop5.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_5 qctop5 on qctop5.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_5 actop5 on actop5.id = q.id

	left join QUESTION_MADE_BY_TOP_5_10 qtop510 on qtop510.id = q.id
	left join ANSWERS_BY_TOP_5_10 atop510 on atop510.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_5_10 qctop510 on qctop510.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_5_10 actop510 on actop510.id = q.id

	left join QUESTION_MADE_BY_TOP_10_15 qtop1015 on qtop1015.id = q.id
	left join ANSWERS_BY_TOP_10_15 atop1015 on atop1015.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_10_15 qctop1015 on qctop1015.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_10_15 actop1015 on actop1015.id = q.id

	left join QUESTION_MADE_BY_TOP_15_20 qtop1520 on qtop1520.id = q.id
	left join ANSWERS_BY_TOP_15_20 atop1520 on atop1520.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_15_20 qctop1520 on qctop1520.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_15_20 actop1520 on actop1520.id = q.id

	left join QUESTION_MADE_BY_TOP_20_25 qtop2025 on qtop2025.id = q.id
	left join ANSWERS_BY_TOP_20_25 atop2025 on atop2025.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_20_25 qctop2025 on qctop2025.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_20_25 actop2025 on actop2025.id = q.id

	left join QUESTION_MADE_BY_TOP_25_30 qtop2530 on qtop2530.id = q.id
	left join ANSWERS_BY_TOP_25_30 atop2530 on atop2530.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_25_30 qctop2530 on qctop2530.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_25_30 actop2530 on actop2530.id = q.id

	left join QUESTION_MADE_BY_TOP_30_35 qtop3035 on qtop3035.id = q.id
	left join ANSWERS_BY_TOP_30_35 atop3035 on atop3035.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_30_35 qctop3035 on qctop3035.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_30_35 actop3035 on actop3035.id = q.id

	left join QUESTION_MADE_BY_TOP_35_40 qtop3540 on qtop3540.id = q.id
	left join ANSWERS_BY_TOP_35_40 atop3540 on atop3540.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_35_40 qctop3540 on qctop3540.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_35_40 actop3540 on actop3540.id = q.id

	left join QUESTION_MADE_BY_TOP_40_45 qtop4045 on qtop4045.id = q.id
	left join ANSWERS_BY_TOP_40_45 atop4045 on atop4045.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_40_45 qctop4045 on qctop4045.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_40_45 actop4045 on actop4045.id = q.id

	left join QUESTION_MADE_BY_TOP_45_50 qtop4550 on qtop4550.id = q.id
	left join ANSWERS_BY_TOP_45_50 atop4550 on atop4550.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_45_50 qctop4550 on qctop4550.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_45_50 actop4550 on actop4550.id = q.id

	left join QUESTION_MADE_BY_TOP_50_100 qtop50100 on qtop50100.id = q.id
	left join ANSWERS_BY_TOP_50_100 atop50100 on atop50100.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_TOP_50_100 qctop50100 on qctop50100.id = q.id
	left join ANSWER_COMMENTS_BY_TOP_50_100 actop50100 on actop50100.id = q.id

	left join QUESTION_MADE_BY_NULL_USER qnullUser on qnullUser.id = q.id
	left join ANSWERS_BY_NULL_USER anullUser on anullUser.parent_post_comm_id = q.id_post_comm
	left join QUESTION_COMMENTS_BY_NULL_USER qcnullUser on qcnullUser.id = q.id
	left join ANSWER_COMMENTS_BY_NULL_USER acnullUser on acnullUser.id = q.id


	-- order by (COALESCE(qtop5.question_made_by_top_5,0) + COALESCE(atop5.answers_by_top_5,0) + COALESCE(qctop5.question_comment_by_top_5,0) + COALESCE(actop5.answer_comment_by_top_5,0))

	$function$


