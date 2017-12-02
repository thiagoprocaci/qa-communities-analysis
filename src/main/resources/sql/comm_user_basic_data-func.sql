CREATE OR REPLACE FUNCTION public.comm_user_basic_data(community_name text)
 RETURNS TABLE(category text,
 				website_null numeric,
				website_not_null numeric,
				location_null numeric,
				location_not_null numeric,
				age_null numeric,
				age_not_null numeric,
				about_me_null numeric,
				about_me_not_null numeric,
				total_user numeric,
				perc_website_null numeric,
				perc_website_not_null numeric,
				perc_location_null numeric,
				perc_location_not_null numeric,
				perc_age_null numeric,
				perc_age_not_null numeric,
				perc_about_me_null numeric,
				perc_about_me_not_null numeric
 	)
 LANGUAGE sql
AS $function$

select * from (

select B.*,
	(B.website_null / B.total_user * 100) as perc_website_null,
	(B.website_not_null / B.total_user * 100) as perc_website_not_null,
	(B.location_null / B.total_user * 100) as perc_location_null,
	(B.location_not_null / B.total_user * 100) as perc_location_not_null,
	(B.age_null / B.total_user * 100) as perc_age_null,
	(B.age_not_null / B.total_user * 100) as perc_age_not_null,
	(B.about_me_null / B.total_user * 100) as perc_about_me_null,
	(B.about_me_not_null / B.total_user * 100) as perc_about_me_not_null

from (

select
	A.category,
	sum(A.website_null) as website_null,
	sum(A.website_not_null) as website_not_null,
	sum(A.location_null) as location_null,
	sum(A.location_not_null) as location_not_null,
	sum(A.age_null) as age_null,
	sum(A.age_not_null) as age_not_null,
	sum(A.about_me_null) as about_me_null,
	sum(A.about_me_not_null) as about_me_not_null,
	(sum(A.about_me_null) + sum(A.about_me_not_null)) as total_user
from (
	with USER_TAB as (
		select * from comm_user_ranking_profile($1)
	)

	select
		'top_5' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5 = 1
	and u.website_url is null

	union all

	select
		'top_5' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5 = 1
	and u.website_url is not null

	union all

	select
		'top_5' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5 = 1
	and u.location is null

	union all

	select
		'top_5' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5 = 1
	and u.location is not null

	union all

	select
		'top_5' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5 = 1
	and u.age is null

	union all

	select
		'top_5' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5 = 1
	and u.age is not null

	union all

	select
		'top_5' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5 = 1
	and u.about_me is null

	union all

	select
		'top_5' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_5 = 1
	and u.about_me is not null

	union all

	select
		'top_5_10' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5_10 = 1
	and u.website_url is null

	union all

	select
		'top_5_10' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5_10 = 1
	and u.website_url is not null

	union all

	select
		'top_5_10' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5_10 = 1
	and u.location is null

	union all

	select
		'top_5_10' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5_10 = 1
	and u.location is not null

	union all

	select
		'top_5_10' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5_10 = 1
	and u.age is null

	union all

	select
		'top_5_10' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5_10 = 1
	and u.age is not null

	union all

	select
		'top_5_10' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_5_10 = 1
	and u.about_me is null

	union all

	select
		'top_5_10' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_5_10 = 1
	and u.about_me is not null

	union all

	select
		'top_10_15' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_10_15 = 1
	and u.website_url is null

	union all

	select
		'top_10_15' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_10_15 = 1
	and u.website_url is not null

	union all

	select
		'top_10_15' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_10_15 = 1
	and u.location is null

	union all

	select
		'top_10_15' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_10_15 = 1
	and u.location is not null

	union all

	select
		'top_10_15' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_10_15 = 1
	and u.age is null

	union all

	select
		'top_10_15' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_10_15 = 1
	and u.age is not null

	union all

	select
		'top_10_15' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_10_15 = 1
	and u.about_me is null

	union all

	select
		'top_10_15' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_10_15 = 1
	and u.about_me is not null

	union all

	select
		'top_15_20' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_15_20 = 1
	and u.website_url is null

	union all

	select
		'top_15_20' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_15_20 = 1
	and u.website_url is not null

	union all

	select
		'top_15_20' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_15_20 = 1
	and u.location is null

	union all

	select
		'top_15_20' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_15_20 = 1
	and u.location is not null

	union all

	select
		'top_15_20' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_15_20 = 1
	and u.age is null

	union all

	select
		'top_15_20' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_15_20 = 1
	and u.age is not null

	union all

	select
		'top_15_20' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_15_20 = 1
	and u.about_me is null

	union all

	select
		'top_15_20' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_15_20 = 1
	and u.about_me is not null

	union all

	select
		'top_20_25' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_20_25 = 1
	and u.website_url is null

	union all

	select
		'top_20_25' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_20_25 = 1
	and u.website_url is not null

	union all

	select
		'top_20_25' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_20_25 = 1
	and u.location is null

	union all

	select
		'top_20_25' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_20_25 = 1
	and u.location is not null

	union all

	select
		'top_20_25' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_20_25 = 1
	and u.age is null

	union all

	select
		'top_20_25' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_20_25 = 1
	and u.age is not null

	union all

	select
		'top_20_25' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_20_25 = 1
	and u.about_me is null

	union all

	select
		'top_20_25' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_20_25 = 1
	and u.about_me is not null

	union all

	select
		'top_25_30' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_25_30 = 1
	and u.website_url is null

	union all

	select
		'top_25_30' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_25_30 = 1
	and u.website_url is not null

	union all

	select
		'top_25_30' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_25_30 = 1
	and u.location is null

	union all

	select
		'top_25_30' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_25_30 = 1
	and u.location is not null

	union all

	select
		'top_25_30' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_25_30 = 1
	and u.age is null

	union all

	select
		'top_25_30' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_25_30 = 1
	and u.age is not null

	union all

	select
		'top_25_30' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_25_30 = 1
	and u.about_me is null

	union all

	select
		'top_25_30' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_25_30 = 1
	and u.about_me is not null

	union all

	select
		'top_30_35' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_30_35 = 1
	and u.website_url is null

	union all

	select
		'top_30_35' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_30_35 = 1
	and u.website_url is not null

	union all

	select
		'top_30_35' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_30_35 = 1
	and u.location is null

	union all

	select
		'top_30_35' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_30_35 = 1
	and u.location is not null

	union all

	select
		'top_30_35' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_30_35 = 1
	and u.age is null

	union all

	select
		'top_30_35' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_30_35 = 1
	and u.age is not null

	union all

	select
		'top_30_35' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_30_35 = 1
	and u.about_me is null

	union all

	select
		'top_30_35' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_30_35 = 1
	and u.about_me is not null

	union all

	select
		'top_35_40' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_35_40 = 1
	and u.website_url is null

	union all

	select
		'top_35_40' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_35_40 = 1
	and u.website_url is not null

	union all

	select
		'top_35_40' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_35_40 = 1
	and u.location is null

	union all

	select
		'top_35_40' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_35_40 = 1
	and u.location is not null

	union all

	select
		'top_35_40' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_35_40 = 1
	and u.age is null

	union all

	select
		'top_35_40' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_35_40 = 1
	and u.age is not null

	union all

	select
		'top_35_40' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_35_40 = 1
	and u.about_me is null

	union all

	select
		'top_35_40' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_35_40 = 1
	and u.about_me is not null

	union all

	select
		'top_40_45' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_40_45 = 1
	and u.website_url is null

	union all

	select
		'top_40_45' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_40_45 = 1
	and u.website_url is not null

	union all

	select
		'top_40_45' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_40_45 = 1
	and u.location is null

	union all

	select
		'top_40_45' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_40_45 = 1
	and u.location is not null

	union all

	select
		'top_40_45' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_40_45 = 1
	and u.age is null

	union all

	select
		'top_40_45' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_40_45 = 1
	and u.age is not null

	union all

	select
		'top_40_45' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_40_45 = 1
	and u.about_me is null

	union all

	select
		'top_40_45' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_40_45 = 1
	and u.about_me is not null

	union all
	select
		'top_45_50' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_45_50 = 1
	and u.website_url is null

	union all

	select
		'top_45_50' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_45_50 = 1
	and u.website_url is not null

	union all

	select
		'top_45_50' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_45_50 = 1
	and u.location is null

	union all

	select
		'top_45_50' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_45_50 = 1
	and u.location is not null

	union all

	select
		'top_45_50' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_45_50 = 1
	and u.age is null

	union all

	select
		'top_45_50' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_45_50 = 1
	and u.age is not null

	union all

	select
		'top_45_50' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_45_50 = 1
	and u.about_me is null

	union all

	select
		'top_45_50' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_45_50 = 1
	and u.about_me is not null

	union all

	select
		'top_50_100' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_50_100 = 1
	and u.website_url is null

	union all

	select
		'top_50_100' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_50_100 = 1
	and u.website_url is not null

	union all

	select
		'top_50_100' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_50_100 = 1
	and u.location is null

	union all

	select
		'top_50_100' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_50_100 = 1
	and u.location is not null

	union all

	select
		'top_50_100' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_50_100 = 1
	and u.age is null

	union all

	select
		'top_50_100' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_50_100 = 1
	and u.age is not null

	union all

	select
		'top_50_100' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.top_50_100 = 1
	and u.about_me is null

	union all

	select
		'top_50_100' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.top_50_100 = 1
	and u.about_me is not null

	union all

	select
		'no_participation' as category,
		count(*) as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.no_participation = 1
	and u.website_url is null

	union all

	select
		'no_participation' as category,
		0  as website_null,
		count(*) as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.no_participation = 1
	and u.website_url is not null

	union all

	select
		'no_participation' as category,
		0 as website_null,
		0 as website_not_null,
		count(*) as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.no_participation = 1
	and u.location is null

	union all

	select
		'no_participation' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		count(*) as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.no_participation = 1
	and u.location is not null

	union all

	select
		'no_participation' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		count(*) as age_null,
		0 as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.no_participation = 1
	and u.age is null

	union all

	select
		'no_participation' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		count(*) as age_not_null,
		0 as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.no_participation = 1
	and u.age is not null

	union all

	select
		'no_participation' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		count(*) as about_me_null,
		0 as about_me_not_null
	from USER_TAB u
	where u.no_participation = 1
	and u.about_me is null

	union all

	select
		'no_participation' as category,
		0 as website_null,
		0 as website_not_null,
		0 as location_null,
		0 as location_not_null,
		0 as age_null,
		0 as age_not_null,
		0 as about_me_null,
		count(*) as about_me_not_null
	from USER_TAB u
	where u.no_participation = 1
	and u.about_me is not null

)A  group by A.category

)B )C

order by C.perc_about_me_not_null desc
$function$