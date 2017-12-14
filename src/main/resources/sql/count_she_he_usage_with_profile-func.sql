CREATE OR REPLACE FUNCTION public.count_she_he_usage_with_profile(community_name text)
 RETURNS TABLE(id_user bigint, count_she_he_usage bigint, category text)
 LANGUAGE sql
AS $function$
select c.*,
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

from count_she_he_usage($1) c
inner join comm_user_ranking_profile($1) u
on u.id = c.id_user
$function$
