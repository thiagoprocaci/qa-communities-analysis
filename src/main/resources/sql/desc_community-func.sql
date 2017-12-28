CREATE OR REPLACE FUNCTION public.desc_community(community_name text)
 RETURNS TABLE("count" bigint, desc_ text)
 LANGUAGE sql
AS $function$

select count(*), 'users' as desc_
from comm_user where id_community in (select id from community where name = $1)
union all

select count(*), 'questions' as desc_
from post where id_community in (select id from community where name = $1)
and post_type = 1

union all

select count(*), 'answers' as desc_
from post where id_community in (select id from community where name = $1)
and post_type = 2

union all

select count(*), 'comments' as desc_
from "comment" where id_community in (select id from community where name = $1)

$function$
