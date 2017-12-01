CREATE OR REPLACE FUNCTION public.comm_user_participation_by_period(community_name text)
 RETURNS TABLE(id bigint, period_participation integer,
 	total_questions numeric, total_answers numeric,
 	total_comments numeric, accepted_answers numeric,
 	reviews numeric, votes_favorite_given numeric,
 	votes_bounty_start_given numeric

 )
 LANGUAGE sql
AS $function$
select
	A.id,
	A.period_participation,
	sum(A.total_questions) as total_questions,
	sum(A.total_answers) as total_answers,
	sum(A.total_comments) as total_comments,
	sum(A.accepted_answers) as accepted_answers,
	sum(A.reviews) as reviews,
	sum(A.votes_favorite_given) as votes_favorite_given,
	sum(A.votes_bounty_start_given) as votes_bounty_start_given

from (

	select u.id, p.period as period_participation,
		count(*) as total_questions,
		0 as total_answers,
		0 as total_comments,
		0 as accepted_answers,
		0 as reviews,
		0 as votes_favorite_given,
		0 as votes_bounty_start_given
	from comm_user u
			inner join post p on u.id = p.id_user
		where  p.post_type = 1 and p.period is not null
		and u.id_community in (select co.id from community co where co.name = $1)
		group by u.id, p.period

	union all

	select u.id, p.period as period_participation,
		0 as total_questions,
		count(*) as total_answers,
		0 as total_comments,
		0 as accepted_answers,
		0 as reviews,
		0 as votes_favorite_given,
		0 as votes_bounty_start_given
	from comm_user u
			inner join post p on u.id = p.id_user
		where  p.post_type = 2 and p.period is not null
		and u.id_community in (select co.id from community co where co.name = $1)
		group by u.id, p.period

	union all

	select u.id, p.period as period_participation,
		0 as total_questions,
		0 as total_answers,
		count(*) as total_comments ,
		0 as accepted_answers,
		0 as reviews,
		0 as votes_favorite_given,
		0 as votes_bounty_start_given
	from comm_user u
			inner join "comment" p on u.id = p.id_user
		where  p.period is not null
		and u.id_community in (select co.id from community co where co.name = $1)
		group by u.id, p.period

	union all

	select * from (
	with QUESTIONS_SOLVED as (
		select * from post p
			where p.post_type = 1 and p.accepted_answer_comm_id is not null
			and p.id_community in (select co.id from community co where co.name = $1)
		) select u.id, p.period as period_participation,
			0 as total_questions,
			0 as total_answers,
			0 as total_comments ,
			count(*) as accepted_answers,
			0 as reviews,
			0 as votes_favorite_given,
			0 as votes_bounty_start_given
		from comm_user u
				inner join post p on u.id = p.id_user
				inner join QUESTIONS_SOLVED qs on qs.accepted_answer_comm_id = p.id_post_comm
			where  p.post_type = 2
			and u.id_community in (select co.id from community co where co.name = $1)
			group by u.id, p.period
	)AA

	union all

	select u.id, p.period as period_participation,
		0 as total_questions,
		0 as total_answers,
		0 as total_comments ,
		0 as accepted_answers,
		count(*) as reviews,
		0 as votes_favorite_given,
		0 as votes_bounty_start_given
	from comm_user u
		inner join post_history p on u.id = p.id_user
	where
	u.id_community in (select co.id from community co where co.name = $1)
	group by u.id, p.period

	union all

	select u.id, p.period as period_participation,
		0 as total_questions,
		0 as total_answers,
		0 as total_comments ,
		0 as accepted_answers,
		0 as reviews,
		count(*) as votes_favorite_given ,
		0 as votes_bounty_start_given
	from comm_user u
		inner join vote p on u.id = p.id_user
	where
	u.id_community in (select co.id from community co where co.name = $1)
	and p.vote_type = 5
	group by u.id, p.period

	union all

	select u.id, p.period as period_participation,
		0 as total_questions,
		0 as total_answers,
		0 as total_comments ,
		0 as accepted_answers,
		0 as reviews,
		0 as votes_favorite_given,
		count(*) as votes_bounty_start_given
	from comm_user u
		inner join vote p on u.id = p.id_user
	where
	u.id_community in (select co.id from community co where co.name = $1)
	and p.vote_type = 8
	group by u.id, p.period
)A

group by A.id, A.period_participation order by sum(A.total_answers) desc
$function$