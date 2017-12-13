CREATE OR REPLACE FUNCTION public.time_to_first_activity(community_name text)
 RETURNS TABLE(id_user bigint,
 				diff_secs double precision,
 				diff_min double precision,
 				diff interval,
 				category text)
 LANGUAGE sql
AS $function$

with FIRST_ACTIVITY as (
	select a.id_user, min(a.post_creation_date) as min_post_creation_date
	from find_user_activities($1) a
	group by a.id_user
)

select u.id as id_user,
		(select extract(epoch from(p.min_post_creation_date - u.creation_date ))) as diff_secs,
		(select extract(epoch from(p.min_post_creation_date - u.creation_date ))) / 60 as diff_min,
	   (p.min_post_creation_date - u.creation_date ) as diff,
	   case
	   	when u.top_5 = 1 then 'top_5'
	   	when u.top_5_10 = 1 then 'top_5_10'
	   	when u.top_10_15 = 1 then 'top_10_15'
	   	when u.top_15_20 = 1 then 'top_15_20'
	   	when u.top_20_25 = 1 then 'top_20_25'
	   	when u.top_25_30 = 1 then 'top_25_30'
	   	when u.top_30_35 = 1 then 'top_30_35'
	   	when u.top_35_40 = 1 then 'top_35_40'
	   	when u.top_40_45 = 1 then 'top_40_45'
	   	when u.top_45_50 = 1 then 'top_45_50'
	   	when u.top_50_100 = 1 then 'top_50_100'
	   	when u.no_participation = 1 then 'no_participation'
	   end as category


from comm_user_ranking_profile($1) u
inner join FIRST_ACTIVITY p on u.id = p.id_user

$function$


