CREATE OR REPLACE FUNCTION public.comm_user_participation(community_name text)
 RETURNS TABLE(id bigint, about_me text, age integer, creation_date timestamp without time zone, display_name character varying, down_votes integer, id_user_comm bigint, last_access_date timestamp without time zone, location character varying, period integer, reputation integer, up_votes integer, views integer, website_url character varying, id_community integer, answers bigint, questions bigint, comments bigint, reviews bigint, accepted_answers bigint, votes_favorite_given bigint, votes_bounty_start_given bigint, total_participation bigint, total_participation_with_votes bigint)
 LANGUAGE sql
AS $function$


select A.*,
	(A.answers + A.questions + A.comments + A.reviews) as total_participation,
	(A.answers + A.questions + A.comments + A.reviews + A.votes_favorite_given
	+ A.votes_bounty_start_given) as total_participation_with_votes
	from (

with USER_TABLE as (
	 select * from comm_user u where u.id_community in (select co.id from community co where co.name = $1)
),

 USER_QUESTIONS as (

	select u.id, count(*) as questions from comm_user u
		inner join post p on u.id = p.id_user
	where  p.post_type = 1
	and u.id_community in (select co.id from community co where co.name = $1)
	group by u.id

),
  USER_ANSWERS as (
	select u.id, count(*) as answers from comm_user u
		inner join post p on u.id = p.id_user
	where  p.post_type = 2
	and u.id_community in (select co.id from community co where co.name = $1)
	group by u.id
),
  USER_COMMENTS as (
	select u.id, count(*) as comments from comm_user u
		inner join "comment" p on u.id = p.id_user
	where
	u.id_community in (select co.id from community co where co.name = $1)
	group by u.id
), USER_REVIEW as (
	select u.id, count(*) as reviews from comm_user u
		inner join post_history p on u.id = p.id_user
	where
	u.id_community in (select co.id from community co where co.name = $1)
	group by u.id
), USER_ACCEPTED_ANSWERS as (
		with QUESTIONS_SOLVED as (
		select * from post p
			where p.post_type = 1 and p.accepted_answer_comm_id is not null
			and p.id_community in (select co.id from community co where co.name = $1)
		) select u.id, count(*) as accepted_answers from comm_user u
				inner join post p on u.id = p.id_user
				inner join QUESTIONS_SOLVED qs on qs.accepted_answer_comm_id = p.id_post_comm
			where  p.post_type = 2
			and u.id_community in (select co.id from community co where co.name = $1)
			group by u.id

), USER_VOTE_FAVORITE as (
	select u.id, count(*) as votes_favorite_given from comm_user u
		inner join vote p on u.id = p.id_user
	where
	u.id_community in (select co.id from community co where co.name = $1)
	and p.vote_type = 5
	group by u.id

), USER_VOTE_BOUNTY_START as (
	select u.id, count(*) as votes_bounty_start_given from comm_user u
		inner join vote p on u.id = p.id_user
	where
	u.id_community in (select co.id from community co where co.name = $1)
	and p.vote_type = 8
	group by u.id
)

select ut.*,
		COALESCE(ua.answers, 0) as answers,
		COALESCE(uq.questions, 0) as questions,
		COALESCE(uc.comments, 0) as comments,
		COALESCE(ur.reviews, 0) as reviews,
		COALESCE(uaa.accepted_answers, 0) as accepted_answers,
		COALESCE(uvf.votes_favorite_given, 0) as votes_favorite_given,
		COALESCE(uvbt.votes_bounty_start_given, 0) as votes_bounty_start_given
		from USER_TABLE ut
	left join USER_ANSWERS ua on ut.id = ua.id
	left join USER_QUESTIONS uq on ut.id = uq.id
	left join USER_COMMENTS uc on ut.id = uc.id
	left join USER_REVIEW ur on ut.id = ur.id
	left join USER_ACCEPTED_ANSWERS uaa on ut.id = uaa.id
	left join USER_VOTE_FAVORITE uvf on ut.id = uvf.id
	left join USER_VOTE_BOUNTY_START uvbt on ut.id = uvbt.id
	order by ut.reputation desc
)A

$function$
