CREATE OR REPLACE FUNCTION public.when_user_was_created(community_name text)
 RETURNS TABLE(
 	period integer,
	count bigint,
	category text,
	category_id integer
	)
 LANGUAGE sql
AS $function$

with USERS as (
	select * from comm_user_ranking_profile($1)
)
select * from (
	select u.period,
		   count(*),
		   'top_5' as category,
		   1 as category_id
	from USERS u where u.top_5 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'top_5_10' as category,
		   2 as category_id
	from USERS u where u.top_5_10 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'top_10_15' as category,
		   3 as category_id
	from USERS u where u.top_10_15 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'top_15_20' as category,
		   4 as category_id
	from USERS u where u.top_15_20 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'top_20_25' as category,
		   5 as category_id
	from USERS u where u.top_20_25 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'top_25_30' as category,
		   6 as category_id
	from USERS u where u.top_25_30 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'top_30_35' as category,
		   7 as category_id
	from USERS u where u.top_30_35 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'top_35_40' as category,
		   8 as category_id
	from USERS u where u.top_35_40 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'top_40_45' as category,
		   9 as category_id
	from USERS u where u.top_40_45 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'top_45_50' as category,
		   10 as category_id
	from USERS u where u.top_45_50 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'top_50_100' as category,
		   11 as category_id
	from USERS u where u.top_50_100 = 1
	group by u.period

union all

	select u.period,
		   count(*),
		   'no_participation' as category,
		   12 as category_id
	from USERS u where u.no_participation = 1
	group by u.period


)A order by A.category_id,  A.period

$function$



