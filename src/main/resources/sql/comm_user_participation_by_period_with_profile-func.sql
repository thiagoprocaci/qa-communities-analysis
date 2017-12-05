CREATE OR REPLACE FUNCTION public.comm_user_participation_by_period_with_profile(community_name text)
 RETURNS TABLE(id bigint ,
period_participation integer ,
total_questions numeric ,
total_answers numeric ,
total_comments numeric ,
accepted_answers numeric ,
reviews numeric ,
votes_favorite_given numeric ,
votes_bounty_start_given numeric ,
top_5 integer ,
top_5_10 integer ,
top_10_15 integer ,
top_15_20 integer ,
top_20_25 integer ,
top_25_30 integer ,
top_30_35 integer ,
top_35_40 integer ,
top_40_45 integer ,
top_45_50 integer ,
top_50_100 integer ,
no_participation integer )
 LANGUAGE sql
AS $function$

with USER_PROFILE as (
	select * from comm_user_ranking_profile($1)
)
select  up.* ,
u.top_5,
u.top_5_10,
u.top_10_15,
u.top_15_20,
u.top_20_25,
u.top_25_30,
u.top_30_35,
u.top_35_40,
u.top_40_45,
u.top_45_50,
u.top_50_100,
u.no_participation

from comm_user_participation_by_period($1) up
inner join USER_PROFILE u on u.id = up.id

$function$