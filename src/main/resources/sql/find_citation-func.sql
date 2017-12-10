CREATE OR REPLACE FUNCTION public.find_citation(community_name text)
 RETURNS TABLE(citation text,
 				id_user bigint,
 				id_post bigint,
 				type_ text)
 LANGUAGE sql
AS $function$

select
 *
from (
	select
		regexp_split_to_table(p.body, E'\\s+') as citation,
		p.id_user  as id_user,
		p.id as id_post,
		case
			when p.post_type = 1 then 'question'
			when p.post_type = 2 then 'answer'
		end as type_
	 from post p
	where p.id_community in (select id from community where name = $1)
	and p.body is not null

	union all

	select
		regexp_split_to_table(p."text", E'\\s+') as citation,
		p.id_user  as id_user,
		p.id as id_post,
		'comm' as type_
	  from "comment" p
	where p.id_community in (select id from community where name = $1)
	and p."text" is not null

)A where A.citation like '@%'

$function$