CREATE OR REPLACE FUNCTION public.tags_per_user_profile(community_name text)
 RETURNS TABLE(
    tag text,
	top_5 numeric,
	top_5_10 numeric,
	top_15_20 numeric,
	top_20_25 numeric,
	top_25_30 numeric,
	top_30_35 numeric,
	top_35_40 numeric,
	top_40_45 numeric,
	top_45_50 numeric,
	top_50_100 numeric
 )
 LANGUAGE sql
AS $function$
with TAGS as (
	select
		id as id_question,
		tag1,
		tag2,
		tag3
	from post_tags($1) d
),
TAG_PER_QUESTION as (

	select
		d.id_question,
		d.total_top_5_participation as top_5,
		d.total_top_5_10_participation as top_5_10,
		d.total_top_10_15_participation as top_10_15,
		d.total_top_15_20_participation as top_15_20,
		d.total_top_20_25_participation as top_20_25,
		d.total_top_25_30_participation as top_25_30,
		d.total_top_30_35_participation as top_30_35,
		d.total_top_35_40_participation as top_35_40,
		d.total_top_40_45_participation as top_40_45,
		d.total_top_45_50_participation as top_45_50,
		d.total_top_50_100_participation as top_50_100,
		t.*
	from discussion_analysis($1) d
	inner join TAGS t on t.id_question = d.id_question

)

select
	A.tag as tag,
	sum(A.top_5) as top_5,
	sum(A.top_5_10) as top_5_10,
	sum(A.top_15_20) as top_15_20,
	sum(A.top_20_25) as top_20_25,
	sum(A.top_25_30) as top_25_30,
	sum(A.top_30_35) as top_30_35,
	sum(A.top_35_40) as top_35_40,
	sum(A.top_40_45) as top_40_45,
	sum(A.top_45_50) as top_45_50,
	sum(A.top_50_100) as top_50_100


  from (
	select
		q.tag1 as tag,
		sum(q.top_5) as top_5,
		sum(q.top_5_10) as top_5_10,
		sum(q.top_15_20) as top_15_20,
		sum(q.top_20_25) as top_20_25,
		sum(q.top_25_30) as top_25_30,
		sum(q.top_30_35) as top_30_35,
		sum(q.top_35_40) as top_35_40,
		sum(q.top_40_45) as top_40_45,
		sum(q.top_45_50) as top_45_50,
		sum(q.top_50_100) as top_50_100
	from TAG_PER_QUESTION q where q.tag1 is not null and length(q.tag1) > 1
		group by q.tag1

	union all

	select
		q.tag2 as tag,
		sum(q.top_5) as top_5,
		sum(q.top_5_10) as top_5_10,
		sum(q.top_15_20) as top_15_20,
		sum(q.top_20_25) as top_20_25,
		sum(q.top_25_30) as top_25_30,
		sum(q.top_30_35) as top_30_35,
		sum(q.top_35_40) as top_35_40,
		sum(q.top_40_45) as top_40_45,
		sum(q.top_45_50) as top_45_50,
		sum(q.top_50_100) as top_50_100
	from TAG_PER_QUESTION q where q.tag2 is not null and length(q.tag2) > 1
		group by q.tag2

	union all

	select
		q.tag3 as tag,
		sum(q.top_5) as top_5,
		sum(q.top_5_10) as top_5_10,
		sum(q.top_15_20) as top_15_20,
		sum(q.top_20_25) as top_20_25,
		sum(q.top_25_30) as top_25_30,
		sum(q.top_30_35) as top_30_35,
		sum(q.top_35_40) as top_35_40,
		sum(q.top_40_45) as top_40_45,
		sum(q.top_45_50) as top_45_50,
		sum(q.top_50_100) as top_50_100
	from TAG_PER_QUESTION q where q.tag3 is not null and length(q.tag3) > 1
		group by q.tag3
)A group by A.tag


$function$