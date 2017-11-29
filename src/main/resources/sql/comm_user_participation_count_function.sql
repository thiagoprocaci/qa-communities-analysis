CREATE OR REPLACE FUNCTION public.comm_user_participation_count(community_name text)
 RETURNS TABLE(id bigint, about_me text, age integer, creation_date timestamp without time zone, display_name character varying, down_votes integer, id_user_comm bigint, last_access_date timestamp without time zone, location character varying, period integer, reputation integer, up_votes integer, views integer, website_url character varying, id_community integer, ranking bigint, total bigint, top_5 integer, top_10 integer, top_15 integer, top_20 integer, top_25 integer, top_30 integer, top_35 integer, top_40 integer, top_45 integer, top_50 integer, top_5_10 integer, top_10_15 integer, top_15_20 integer, top_20_25 integer, top_25_30 integer, top_30_35 integer, top_35_40 integer, top_40_45 integer, top_45_50 integer, top_50_100 integer, answers bigint, questions bigint, comments bigint)
 LANGUAGE sql
AS $function$
with USER_TABLE as (
	 select * from comm_user_ranking($1)
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
)


select ut.*,
		COALESCE(ua.answers, 0) as answers,
		COALESCE(uq.questions, 0) as questions,
		COALESCE(uc.comments, 0) as comments from USER_TABLE ut
	left join USER_ANSWERS ua on ut.id = ua.id
	left join USER_QUESTIONS uq on ut.id = uq.id
	left join USER_COMMENTS uc on ut.id = uc.id
	order by ut.ranking
$function$
