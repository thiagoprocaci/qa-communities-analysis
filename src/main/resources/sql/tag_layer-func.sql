CREATE OR REPLACE FUNCTION public.tag_layer(community_name text)
 RETURNS TABLE(tag text,
 			"count" bigint,
 			 pos bigint,
 			 layer text
 )
 LANGUAGE sql
AS $function$
with TAGS as  (
	select
	id,
	tag1,
	tag2,
	tag3
	from post_tags($1)
),
IMPORTANCE as (
	select count(*), A.tag from (
		select tag1 as tag
		from TAGS t

		union all

		select tag2 as tag
		from TAGS t

		union all

		select tag3 as tag
		from TAGS t

	)A
	where  length(A.tag) >0
	group by A.tag order by count(*) desc
),
RANKING as (
	select tag, "count", rank() over (order by "count" desc) as pos  from IMPORTANCE
)



select *,
	case
		when "count" >= 1000 then '1'
		when "count" >= 500 and "count" < 1000 then '2'
		when "count" >= 100 and "count" < 500 then '3'
		else '4'
	end as layer

from RANKING order by pos asc

$function$
