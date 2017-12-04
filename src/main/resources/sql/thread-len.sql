
with QUESTIONS as (
	select *
	from post p
	where
	p.post_type = 1
	and p.id_community in (select id from community where name = 'chemistry.stackexchange.com')
) ,
ANSWER_COMMENTS as (
	select question.id, count(*)  as answer_comment_count from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name = 'chemistry.stackexchange.com')
	and c.id_community = answer.id_community
	and answer.id_community = question.id_community
	group by question.id

),
QUESTION_COMMENTS as (
	select question.id, count(*)  as question_comment_count from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = 'chemistry.stackexchange.com')
	and c.id_community = question.id_community
	group by question.id

)

select q.id as id_question,
	   COALESCE(q.answer_count,0) as answer_count,
	   COALESCE(a.answer_comment_count,0) as answer_comment_count,
	   COALESCE(qc.question_comment_count,0) as question_comment_count
	from  QUESTIONS q
	left join ANSWER_COMMENTS a on a.id = q.id
	left join QUESTION_COMMENTS qc on qc.id = q.id

