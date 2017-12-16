CREATE OR REPLACE FUNCTION public.comm_user_ari_over_time(community_name text)
 RETURNS TABLE(id bigint,
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

with  user_ranking_profile as (
	select * from comm_user_ranking_profile($1)
)


select
	u.id,
	u.category,
	u.period_post,
	avg(u.ari) as avg_ari,
	avg(u.characters_) as avg_chars,
	avg(u.syllables) as avg_syllabes,
	avg(u.words) as avg_words,
	avg(u.complexwords) as avg_complex_words,
	avg(u.sentences) as avg_sentences


from (

	select A.*,
	    case
		   	when A.top_5 = 1 then 'top_5'
		   	when A.top_5_10 = 1 then 'top_5_10'
		   	when A.top_10_15 = 1 then 'top_10_15'
		   	when A.top_15_20 = 1 then 'top_15_20'
		   	when A.top_20_25 = 1 then 'top_20_25'
		   	when A.top_25_30 = 1 then 'top_25_30'
		   	when A.top_30_35 = 1 then 'top_30_35'
		   	when A.top_35_40 = 1 then 'top_35_40'
		   	when A.top_40_45 = 1 then 'top_40_45'
		   	when A.top_45_50 = 1 then 'top_45_50'
		   	when A.top_50_100 = 1 then 'top_50_100'
		   	when A.no_participation = 1 then 'no_participation'
		   end as category

	from (
			select u.*,
				   p.ari_text as ari,
				   p.characters_text as characters_,
				   p.syllables_text as syllables,
				   p.words_text as words,
				   p.complexwords_text as complexwords,
				   p.sentences_text as sentences,
				   p.period as period_post
			from post p
			inner join user_ranking_profile u on p.id_user = u.id
			where p.id_community in (select co.id from community co where co.name = $1)

			union all

			select u.*,
				   p.ari as ari,
				   p."characters" as characters_,
				   p.syllables as syllables,
				   p.words as words,
				   p.complexwords as complexwords,
				   p.sentences as sentences,
				   p.period as period_post
			from "comment" p
			inner join user_ranking_profile u on p.id_user = u.id
			where p.id_community in (select co.id from community co where co.name = $1)
	)A
)u
group by u.id, u.category, u.period_post order by u.period_post

$function$