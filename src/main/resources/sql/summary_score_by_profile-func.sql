CREATE OR REPLACE FUNCTION public.summary_score_by_profile(community_name text)
 RETURNS TABLE(
 	min_score integer,
	max_score integer,
	avg_score numeric,
	variance_score numeric,
	stddev_score numeric,
	category text)
 LANGUAGE sql
AS $function$
with USER_PROFILE as (
	select * from comm_user_ranking_profile($1)
),

POST_USER as (
	select p.score,
		   u.id,
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
		   u.top_50_100
	from post p
	inner join USER_PROFILE u on p.id_user = u.id
	where  p.id_community in (select id from community where name = $1)

	union all

	select p.score,
		   u.id,
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
		   u.top_50_100
	from "comment" p
	inner join USER_PROFILE u on p.id_user = u.id
	where  p.id_community in (select id from community where name = $1)
)
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score ,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_5' as category from POST_USER u where u.top_5 = 1
union all
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score ,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_5_10' as category from POST_USER u where u.top_5_10 = 1
union all
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score ,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_10_15' as category from POST_USER u where u.top_10_15 = 1
union all
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score ,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_15_20' as category from POST_USER u where u.top_15_20 = 1
union all
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score ,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_20_25' as category from POST_USER u where u.top_20_25 = 1
union all
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score ,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_25_30' as category from POST_USER u where u.top_25_30 = 1
union all
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score ,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_30_35' as category from POST_USER u where u.top_30_35 = 1
union all
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score ,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_35_40' as category from POST_USER u where u.top_35_40 = 1
union all
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_40_45' as category from POST_USER u where u.top_40_45 = 1
	union all
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score ,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_45_50' as category from POST_USER u where u.top_45_50 = 1
	union all
select
	min(u.score) as min_score,
	max(u.score) as max_score,
	avg(u.score) as avg_score ,
	variance(u.score) as variance_score,
	stddev(u.score) as stddev_score,
	'top_50_100' as category from POST_USER u where u.top_50_100 = 1
$function$
