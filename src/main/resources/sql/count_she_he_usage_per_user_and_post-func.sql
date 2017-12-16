CREATE OR REPLACE FUNCTION public.count_she_he_usage_per_user_and_post(community_name text)
 RETURNS TABLE(category text, total_category numeric, total numeric, usage_per_user numeric, usage_per_post numeric)
 LANGUAGE sql
AS $function$

with participation_by_category as (
	select * from comm_user_participation_by_category($1)

),
count_usage_group as (
	select c.category, sum(c.count_she_he_usage) as total_category
	from count_she_he_usage_with_profile($1) c
	group by c.category
),
sum_usage as (
	select sum(c.total_category) as total from count_usage_group c
),
all_usage as (
	select * from count_usage_group, sum_usage
)

select a.*,
	(a.total_category / p.total_users_in_category) as usage_per_user,
	(a.total_category / p.ques_ans_comm) as usage_per_post

from all_usage a
inner join participation_by_category p
on a.category = p.category

$function$
