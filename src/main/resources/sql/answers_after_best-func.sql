CREATE OR REPLACE FUNCTION public.answers_after_best(community_name text)
 RETURNS TABLE(id_question bigint,
 				id_user_answers_after_best bigint,
 				best_answer_date timestamp,
 				user_answer_date timestamp,
 				diff_secs double precision,
 				diff_min double precision,
 				diff interval
 				)
 LANGUAGE sql
AS $function$


with QUESTION_SOLVED as (
	select *
	from post question
	where question.post_type = 1
	and question.accepted_answer_comm_id is not null
	and question.id_community in (select id from community where name = $1)
),
BEST_ANSWER as (
	select question.id as id_question,
	question.id_post_comm as id_question_comm,
	answer.id as id_answer,
		answer.creation_date as answer_date ,
		answer.id_community
	from post answer
	inner join QUESTION_SOLVED question on question.accepted_answer_comm_id = answer.id_post_comm
	where answer.post_type = 2
	and answer.id_community = question.id_community
) select ba.id_question,
		p.id_user as id_user_answers_after_best,
		ba.answer_date as best_answer_date,
		p.creation_date as user_answer_date,
		(select extract(epoch from(p.creation_date - ba.answer_date ))) as diff_secs,
		(select extract(epoch from(p.creation_date - ba.answer_date ))) / 60 as diff_min,
		(p.creation_date - ba.answer_date ) as diff
	from BEST_ANSWER ba, post p
	where
	p.parent_post_comm_id = ba.id_question_comm
	and p.post_type = 2
	and p.id_community = ba.id_community
	and p.creation_date > ba.answer_date
order by ba.id_question

$function$
