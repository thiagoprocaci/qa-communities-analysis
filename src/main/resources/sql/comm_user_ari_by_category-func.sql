CREATE OR REPLACE FUNCTION public.comm_user_ari_by_category(community_name text)
 RETURNS TABLE(
 			  category text,
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
	A.category,
	A.avg_ari,
	A.avg_chars,
	A.avg_syllabes,
	A.avg_words,
	A.avg_complex_words,
	A.avg_sentences

	from (
	select
		u.category,
		avg(u.avg_ari) as avg_ari,
		avg(u.avg_chars) as avg_chars,
		avg(u.avg_syllabes) as avg_syllabes,
		avg(u.avg_words) as avg_words,
		avg(u.avg_complex_words) as avg_complex_words,
		avg(u.avg_sentences) as avg_sentences,
		case
		   	when u.category = 'top_5' then 1
		   	when u.category = 'top_5_10' then 2
		   	when u.category = 'top_10_15' then 3
		   	when u.category = 'top_15_20' then 4
		   	when u.category = 'top_20_25' then 5
		   	when u.category = 'top_25_30' then 6
		   	when u.category = 'top_30_35' then 7
		   	when u.category = 'top_35_40' then 8
		   	when u.category = 'top_40_45' then 9
		   	when u.category = 'top_45_50' then 10
		   	when u.category = 'top_50_100' then 11
		   	when u.category = 'no_participation' then 12
		   end as category_id
	from comm_user_ari($1) u
	group by u.category
)A order by A.category_id

$function$