CREATE OR REPLACE FUNCTION public.comm_user_ranking_profile(community_name text)
 RETURNS TABLE(id bigint, about_me text, age integer, creation_date timestamp without time zone, display_name character varying, down_votes integer, id_user_comm bigint, last_access_date timestamp without time zone, location character varying, period integer, reputation integer, up_votes integer, views integer, website_url character varying, id_community integer, answers bigint, questions bigint, comments bigint, reviews bigint, accepted_answers bigint, votes_favorite_given bigint, votes_bounty_start_given bigint, total_participation bigint, total_participation_with_votes bigint, top_5 integer, top_10 integer, top_15 integer, top_20 integer, top_25 integer, top_30 integer, top_35 integer, top_40 integer, top_45 integer, top_50 integer, top_5_10 integer, top_10_15 integer, top_15_20 integer, top_20_25 integer, top_25_30 integer, top_30_35 integer, top_35_40 integer, top_40_45 integer, top_45_50 integer, top_50_100 integer, no_participation integer)
 LANGUAGE sql
AS $function$
select B.id,
B.about_me,
B.age,
B.creation_date,
B.display_name,
B.down_votes,
B.id_user_comm,
B.last_access_date,
B.location,
B.period,
B.reputation,
B.up_votes,
B.views,
B.website_url,
B.id_community,
B.answers,
B.questions,
B.comments,
B.reviews,
B.accepted_answers,
B.votes_favorite_given,
B.votes_bounty_start_given,
B.total_participation,
B.total_participation_with_votes,
B.top_5,
B.top_10,
B.top_15,
B.top_20,
B.top_25,
B.top_30,
B.top_35,
B.top_40,
B.top_45,
B.top_50,
	case when B.top_5 = 0 and B.top_10 = 1  then 1
		 else 0
		 end as top_5_10,

	case when B.top_10 = 0 and B.top_15 = 1  then 1
		 else 0
		 end as top_10_15,

	case when B.top_15 = 0 and B.top_20 = 1  then 1
		 else 0
		 end as top_15_20,

	case when B.top_20 = 0 and B.top_25 = 1  then 1
		 else 0
		 end as top_20_25,

	case when B.top_25 = 0 and B.top_30 = 1  then 1
		 else 0
		 end as top_25_30,

	case when B.top_30 = 0 and B.top_35 = 1  then 1
		 else 0
		 end as top_30_35,

	case when B.top_35 = 0 and B.top_40 = 1  then 1
		 else 0
		 end as top_35_40,

	case when B.top_40 = 0 and B.top_45 = 1  then 1
		 else 0
		 end as top_40_45,

	case when B.top_45 = 0 and B.top_50 = 1  then 1
		 else 0
		 end as top_45_50,

	case when B.top_50 = 0  then 1
		 else 0
		 end as top_50_100,

		 0 as no_participation
from (

	select
		A.*,

		case
			when A.ranking <= (A.total * 5/100) then 1
			when A.ranking > (A.total * 5/100) then 0
		end as top_5,

		case
			when A.ranking <= (A.total * 10/100) then 1
			when A.ranking > (A.total * 10/100) then 0
		end as top_10,

		case
			when A.ranking <= (A.total * 15/100) then 1
			when A.ranking > (A.total * 15/100) then 0
		end as top_15,

		case
			when A.ranking <= (A.total * 20/100) then 1
			when A.ranking > (A.total * 20/100) then 0
		end as top_20,

		case
			when A.ranking <= (A.total * 25/100) then 1
			when A.ranking > (A.total * 25/100) then 0
		end as top_25,

		case
			when A.ranking <= (A.total * 30/100) then 1
			when A.ranking > (A.total * 30/100) then 0
		end as top_30,

		case
			when A.ranking <= (A.total * 35/100) then 1
			when A.ranking > (A.total * 35/100) then 0
		end as top_35,

		case
			when A.ranking <= (A.total * 40/100) then 1
			when A.ranking > (A.total * 40/100) then 0
		end as top_40,

		case
			when A.ranking <= (A.total * 45/100) then 1
			when A.ranking > (A.total * 45/100) then 0
		end as top_45,

		case
			when A.ranking <= (A.total * 50/100) then 1
			when A.ranking > (A.total * 50/100) then 0
		end as top_50

	from


	(
		SELECT
			u.*,
		    row_number() OVER (
		            order by u.reputation desc
		    ) as ranking,
		    (select count(*) from comm_user_participation($1)
		    where total_participation > 0) as total
		  FROM comm_user_participation($1) u
		  where u.total_participation > 0
		  order by u.reputation desc
	)A
)B

union all

select B.id,
B.about_me,
B.age,
B.creation_date,
B.display_name,
B.down_votes,
B.id_user_comm,
B.last_access_date,
B.location,
B.period,
B.reputation,
B.up_votes,
B.views,
B.website_url,
B.id_community,
B.answers,
B.questions,
B.comments,
B.reviews,
B.accepted_answers,
B.votes_favorite_given,
B.votes_bounty_start_given,
B.total_participation,
B.total_participation_with_votes ,
		0 as top_5,
		0 as top_10,
		0 as top_15,
		0 as top_20,
		0 as top_25,
		0 as top_30,
		0 as top_35,
		0 as top_40,
		0 as top_45,
		0 as top_50,
		0 as top_5_10,
		0 as top_10_15,
		0 as top_15_20,
		0 as top_20_25,
		0 as top_25_30,
	 	0 as top_30_35,
		0 as top_35_40,
		0 as top_40_45,
		0 as top_45_50,
		0  as top_50_100,
		1 as no_participation


from (
		SELECT
			u.*
		  FROM comm_user_participation($1) u
		  where u.total_participation = 0
		  order by u.reputation desc

)B

$function$
