CREATE OR REPLACE FUNCTION public.count_they_usage(community_name text)
 RETURNS TABLE(id_user bigint, count_they_usage bigint)
 LANGUAGE sql
AS $function$

select
  A.id_user,
  count(*) as count_they_usage

from (
select * from find_tokens($1)

)A

where lower(A.token)
in (
'their',
'them',
'themselves',
'they',
'theyd',
'they''d',
'theyll',
'they''ll',
'theyve',
'they''ve'

)

group by A.id_user order by count(*) desc
$function$