CREATE OR REPLACE FUNCTION public.summary_reputation(community_name text)
 RETURNS TABLE("value" double precision, desc_ text)
 LANGUAGE sql
AS $function$

with quartiles as (
select reputation, ntile(4) over (order by reputation) as quartile from comm_user
where id_community in (select id from community where name = $1)
)
select min(reputation) as "value", 'min' as desc_ from quartiles
union all
select max(reputation) as "value",  'first_quartile' as desc_ from quartiles  where quartile = 1
union all
SELECT PERCENTILE_CONT(0.5) WITHIN GROUP(ORDER by reputation) as "value", 'median' as desc_ FROM comm_user
where id_community in (select id from community where name = $1)
union all
select avg(reputation) as "value", 'mean' as desc_ from quartiles
union all
select max(reputation) as "value",  'third_quartile' as desc_ from quartiles  where quartile = 3
union all
select max(reputation) as "value", 'max' as desc_ from quartiles

$function$