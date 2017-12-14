CREATE OR REPLACE FUNCTION public.count_she_he_usage(community_name text)
 RETURNS TABLE(id_user bigint, count_she_he_usage bigint)
 LANGUAGE sql
AS $function$

select
  A.id_user,
  count(*) as count_she_he_usage

from (
select * from find_tokens($1)

)A

where lower(A.token)
in (
'he',
'hed',
'he''d',
'her',
'hers',
'herself',
'hes',
'he''s',
'him',
'himself',
'his',
'oneself',
'she',
'she''d',
'she''ll',
'shes',
'she''s'
)

group by A.id_user order by count(*) desc
$function$