CREATE OR REPLACE FUNCTION public.count_citation_by_unique_display_name(community_name text)
 RETURNS TABLE(display_name text, count bigint, id_user bigint)
 LANGUAGE sql
AS $function$


select A.*,
	u.id as id_user

from (

	with USERNAMES as (
		select u.display_name from comm_user u where id_community in (
				select id from community where name in ( $1 )
			) group by display_name having count(*) = 1
	)
	select u.display_name, count(*) from USERNAMES u, find_citation($1) c

	where c.citation like '@' || u.display_name
	or
	c.citation like '@' || u.display_name || ','
	or
	c.citation like '@' || u.display_name || ':'
	or
	c.citation like '@' || u.display_name || '.'
	or
	c.citation like '@' || u.display_name || '''s'


	group by u.display_name

)A inner join comm_user u on u.display_name = A.display_name

where id_community in (
				select id from community where name in ( $1 )
			)
$function$
