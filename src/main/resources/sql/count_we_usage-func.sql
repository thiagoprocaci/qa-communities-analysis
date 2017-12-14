CREATE OR REPLACE FUNCTION public.count_we_usage(community_name text)
 RETURNS TABLE(id_user bigint, count_we_usage bigint)
 LANGUAGE sql
AS $function$

select
  A.id_user,
  count(*) as count_we_usage

from (
select * from find_tokens($1)

)A

where lower(A.token)
in (
'lets',
'let''s',
'our',
'ours',
'ourselves',
'us',
'we',
'we''d',
'we''ll',
'we''re',
'weve',
'we''ve'
)

group by A.id_user order by count(*) desc
$function$
