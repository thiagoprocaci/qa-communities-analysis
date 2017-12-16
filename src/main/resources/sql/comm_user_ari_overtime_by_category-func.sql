CREATE OR REPLACE FUNCTION public.comm_user_ari_overtime_by_category(community_name text)
 RETURNS TABLE(
 			  category text,
 			  period_post integer,
				avg_ari double precision,
				avg_chars double precision,
				avg_syllabes double precision,
				avg_words double precision,
				avg_complex_words double precision,
				avg_sentences double precision
 			  )
 LANGUAGE sql
AS $function$

select
	u.category,
	u.period_post,
	avg(u.avg_ari) as avg_ari,
	avg(u.avg_chars) as avg_chars,
	avg(u.avg_syllabes) as avg_syllabes,
	avg(u.avg_words) as avg_words,
	avg(u.avg_complex_words) as avg_complex_words,
	avg(u.avg_sentences) as avg_sentences

from comm_user_ari_over_time($1) u
group by u.category, u.period_post order by  u.period_post
$function$