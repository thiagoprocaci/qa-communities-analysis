CREATE OR REPLACE FUNCTION public.user_entropy(community_name text)
 RETURNS TABLE(id_user bigint,
 			entropy numeric)
 LANGUAGE sql
AS $function$

with QUESTION_TAGS as (
	select
			id as id_question,
			tag1 as L1,
			tag2 as L2,
			tag3 as L3
		from post_tags($1)
), USER_BY_DISCUSSION as (
	select  id_question, id_user from comm_user_by_discussion($1)
),
USER_LAYERS as (
select q.*, u.id_user from QUESTION_TAGS q
inner join USER_BY_DISCUSSION u on q.id_question = u.id_question
order by u.id_user
),
USER_LAYERS_2 as (
	select  u.l1 as tag, u.id_user, count(*) as posts, '1' as layer from USER_LAYERS u
	group by u.l1, u.id_user

	union all

	select  u.l2 as tag, u.id_user, count(*) as posts, '2' as layer from USER_LAYERS u
	group by u.l2, u.id_user

	union all

	select  u.l3 as tag, u.id_user, count(*) as posts, '3' as layer from USER_LAYERS u
	group by u.l3, u.id_user

),
TOTAL_POSTS_USER_BY_LAYER as (
select u.layer, u.id_user, sum(u.posts) as total_posts_layer from USER_LAYERS_2 u group by u.layer, u.id_user
)
select D.id_user, sum(D.partial_entropy) as entropy from (
	select C.id_user, C.layer, (-1* sum(C.partial_entropy)) as partial_entropy from (
		select B.*,
			(B.prob * B.prob_log) as partial_entropy
			from (

			select u.*, t.total_posts_layer,
					(u.posts/t.total_posts_layer) as prob,
					(ln((u.posts/t.total_posts_layer))) as prob_log
			from USER_LAYERS_2 u
			inner join TOTAL_POSTS_USER_BY_LAYER t on t.id_user = u.id_user and t.layer = u.layer

		)B
	)C group by C.id_user, C.layer
)D group by D.id_user
$function$