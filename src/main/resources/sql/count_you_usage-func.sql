CREATE OR REPLACE FUNCTION public.count_you_usage(community_name text)
 RETURNS TABLE(id_user bigint, count_you_usage bigint)
 LANGUAGE sql
AS $function$

select
  A.id_user,
  count(*) as count_you_usage

from (
select * from find_tokens($1)

)A

where lower(A.token)
in (
'thee',
'thine',
'thou',
'thoust',
'thy',
'ya',
'yall',
'y''all',
'ye',
'you',
'youd',
'you''d',
'youll',
'you''ll',
'your' ,
'youre',
'you''re',
'yours',
'youve',
'you''ve'
)

group by A.id_user order by count(*) desc
$function$