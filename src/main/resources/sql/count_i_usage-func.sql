CREATE OR REPLACE FUNCTION public.count_i_usage(community_name text)
 RETURNS TABLE(id_user bigint, count_i_usage bigint)
 LANGUAGE sql
AS $function$

select
  A.id_user,
  count(*) as count_I_usage

from (
select * from find_tokens($1)

)A

where lower(A.token)
in ('i', 'id', 'i''d','i''ll','im','i''m', 'ive', 'i''ve', 'me',
'mine', 'my', 'myself')

group by A.id_user order by count(*) desc
$function$
