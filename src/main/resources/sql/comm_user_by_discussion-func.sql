CREATE OR REPLACE FUNCTION public.comm_user_by_discussion(community_name text)
 RETURNS TABLE(id_question bigint, id_user bigint)
 LANGUAGE sql
AS $function$
select * from (

with QUESTION as (
	select * from post p where p.post_type = 1
	and p.id_community in (select id from community where name = $1)
),
ANSWERS as (
	select p.parent_post_comm_id, p.id_user  from post p where p.post_type = 2
	and p.id_community in (select id from community where name = $1)
	and p.id_user is not null

),
QUESTION_COMMENTS as (
	select question.id as question_id, c.id_user from "comment" c
	inner join post question on question.id = c.id_post
	where
	question.post_type = 1
	and c.id_community in (select id from community where name = $1)
	and c.id_community = question.id_community
	and c.id_user is not null

),
ANSWER_COMMENTS as (
	select question.id as question_id, c.id_user from "comment" c
	inner join post answer on answer.id = c.id_post
	inner join post question on answer.parent_post_comm_id = question.id_post_comm
	where
	answer.post_type = 2
	and c.id_community in (select id from community where name =  $1)
	and c.id_community = answer.id_community
	and c.id_user is not null
	and answer.id_community = question.id_community

)

select question.id as id_question,
	   question.id_user
from QUESTION question
where question.id_user is not null

union all

select question.id as id_question,
		answer.id_user
from QUESTION question
inner join  ANSWERS answer on answer.parent_post_comm_id = question.id_post_comm

union all

select question.id as id_question,
		question_comm.id_user

from QUESTION question
inner join QUESTION_COMMENTS question_comm on question_comm.question_id = question.id


union all

select question.id as id_question,
		answer_comm.id_user
from QUESTION question
inner join ANSWER_COMMENTS answer_comm on answer_comm.question_id = question.id


)A order by A.id_question
$function$