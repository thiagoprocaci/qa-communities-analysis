CREATE OR REPLACE FUNCTION public.user_entropy(community_name text)
 RETURNS TABLE(id_user bigint,
 			entropy numeric)
 LANGUAGE sql
AS $function$

with QUESTION_TAGS as (
	select
			id as id_question,
			tag1 ,
			tag2 ,
			tag3
		from post_tags($1)
), USER_BY_DISCUSSION as (
	select  id_question, id_user from comm_user_by_discussion($1)
),
LAYERS as (
	-- recupera as camadas de cada tag
	select * from tag_layer($1)
),
USER_TAGS as (
	-- liga tags aos usuarios
	select q.*, u.id_user from QUESTION_TAGS q
		inner join USER_BY_DISCUSSION u on q.id_question = u.id_question
	order by u.id_user
),
USER_TAGS_2 as  (
	-- reorganiza a conexao das tags por usuario e adiciona a camada de cada tag
	select A.tag, A.id_user, sum("count") as total_post_tag,
		(select l.layer from LAYERS l where l.tag = A.tag) as 	layer
	from (

		select u.tag1 as tag, u.id_user, count(*) from USER_TAGS u group by u.tag1, u.id_user
		union all
		select u.tag2 as tag, u.id_user, count(*) from USER_TAGS u group by u.tag2, u.id_user
		union all
		select u.tag3 as tag, u.id_user, count(*) from USER_TAGS u group by u.tag3, u.id_user

	)A where length(A.tag) > 0 group by A.tag, A.id_user
),
TOTAL_POST_LAYER as (
	select u.layer, u.id_user, sum(total_post_tag) as total_post_layer from USER_TAGS_2 u group by u.layer, u.id_user
)
select D.id_user, sum(D.partial_entropy) as entropy from (
	select C.id_user, C.layer, (-1* sum(C.partial_entropy)) as partial_entropy from (
		select B.*,
					(B.prob * B.prob_log) as partial_entropy
					from (

			select u.*, t.total_post_layer,
				   (u.total_post_tag/t.total_post_layer) as prob,
				  (ln((u.total_post_tag/t.total_post_layer))) as prob_log

			from USER_TAGS_2 u
			inner join TOTAL_POST_LAYER t on u.id_user = t.id_user and u.layer = t.layer
			order by u.id_user, u.layer
		)B
	)C  group by C.id_user, C.layer
)D group by D.id_user

$function$