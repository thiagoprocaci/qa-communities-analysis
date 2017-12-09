CREATE OR REPLACE FUNCTION public.percent_score_types_per_post_and_user_profile(community_name text)
 RETURNS TABLE(
 	posts_neg_score bigint,
	posts_zero_score bigint,
	posts_pos_score bigint,
	category text,
	perc_posts_neg double precision,
	perc_posts_zero double precision,
	perc_posts_pos double precision
	)
 LANGUAGE sql
AS $function$

with USER_PROFILE as (
	select * from comm_user_ranking_profile($1)
),

POST_USER as (
	select p.score,
		   u.id,
		   u.top_5,
		   u.top_5_10,
		   u.top_10_15,
		   u.top_15_20,
		   u.top_20_25,
		   u.top_25_30,
	 	   u.top_30_35,
		   u.top_35_40,
		   u.top_40_45,
		   u.top_45_50,
		   u.top_50_100
	from post p
	inner join USER_PROFILE u on p.id_user = u.id
	where  p.id_community in (select id from community where name = $1)

	union all

	select p.score,
		   u.id,
		   u.top_5,
		   u.top_5_10,
		   u.top_10_15,
		   u.top_15_20,
		   u.top_20_25,
		   u.top_25_30,
	 	   u.top_30_35,
		   u.top_35_40,
		   u.top_40_45,
		   u.top_45_50,
		   u.top_50_100
	from "comment" p
	inner join USER_PROFILE u on p.id_user = u.id
	where  p.id_community in (select id from community where name = $1)
)

select A.*,
	  ((100 * A.posts_neg_score)/(cast(A.posts_neg_score + A.posts_zero_score + A.posts_pos_score as double precision))) as perc_posts_neg,
	  ((100 * A.posts_zero_score)/(cast(A.posts_neg_score + A.posts_zero_score + A.posts_pos_score as double precision))) as perc_posts_zero,
	  ((100 * A.posts_pos_score)/(cast(A.posts_neg_score + A.posts_zero_score + A.posts_pos_score as double precision))) as perc_posts_pos
	from (
  select
	(select count(*) from POST_USER u where u.top_5 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_5 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_5 = 1 and u.score > 0) as posts_pos_score,
	'top_5' as category

	union all

  select
	(select count(*) from POST_USER u where u.top_5_10 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_5_10 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_5_10 = 1 and u.score > 0) as posts_pos_score,
	'top_5_10' as category

	union all

  select
	(select count(*) from POST_USER u where u.top_10_15 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_10_15 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_10_15 = 1 and u.score > 0) as posts_pos_score,
	'top_10_15' as category

	union all

  select
	(select count(*) from POST_USER u where u.top_15_20 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_15_20 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_15_20 = 1 and u.score > 0) as posts_pos_score,
	'top_15_20' as category

	union all

  select
	(select count(*) from POST_USER u where u.top_20_25 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_20_25 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_20_25 = 1 and u.score > 0) as posts_pos_score,
	'top_20_25' as category

	union all

  select
	(select count(*) from POST_USER u where u.top_25_30 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_25_30 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_25_30 = 1 and u.score > 0) as posts_pos_score,
	'top_25_30' as category

	union all

  select
	(select count(*) from POST_USER u where u.top_30_35 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_30_35 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_30_35 = 1 and u.score > 0) as posts_pos_score,
	'top_30_35' as category

	union all

  select
	(select count(*) from POST_USER u where u.top_35_40 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_35_40 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_35_40 = 1 and u.score > 0) as posts_pos_score,
	'top_35_40' as category

 	union all

  select
	(select count(*) from POST_USER u where u.top_40_45 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_40_45 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_40_45 = 1 and u.score > 0) as posts_pos_score,
	'top_40_45' as category

 	union all

  select
	(select count(*) from POST_USER u where u.top_45_50 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_45_50 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_45_50 = 1 and u.score > 0) as posts_pos_score,
	'top_45_50' as category

	 	union all

  select
	(select count(*) from POST_USER u where u.top_50_100 = 1 and u.score < 0) as posts_neg_score,
	(select count(*) from POST_USER u where u.top_50_100 = 1 and u.score = 0) as posts_zero_score,
	(select count(*) from POST_USER u where u.top_50_100 = 1 and u.score > 0) as posts_pos_score,
	'top_50_100' as category

)A
$function$