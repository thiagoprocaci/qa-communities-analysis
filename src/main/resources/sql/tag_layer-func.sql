CREATE OR REPLACE FUNCTION public.tag_layer(community_name text)
 RETURNS TABLE(tag text,
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
	from communities.post_tags($1)
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
	select tag, rank() over (order by "count" desc) as pos  from IMPORTANCE
)



select *,
	case
		when pos <= ((select count(*) from RANKING)/3) then '1'
		when pos > ((select count(*) from RANKING)/3) and pos <= ((select count(*) from RANKING) * 2/3)  then '2'
		else '3'
	end as layer

from RANKING

$function$


