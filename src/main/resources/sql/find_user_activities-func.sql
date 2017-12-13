CREATE OR REPLACE FUNCTION public.find_user_activities(community_name text)
 RETURNS TABLE(id_user bigint, post_creation_date timestamp without time zone, type_ text)
 LANGUAGE sql
AS $function$
select A.id_user, A.creation_date as post_creation_date, A.type_  from (
	select p.id, p.id_user, p.creation_date,
		case 
			when p.post_type = 1 then 'question'
			when p.post_type = 2 then 'answer'
		end as type_
		from post p where p.id_community in (select id from community where name = $1)
	union all
	
	select p.id, p.id_user, p.creation_date,
		'comment 'as type_
		from "comment" p where p.id_community in (select id from community where name = $1)
	
	union all 

		select p.id, p.id_user, p.creation_date,
		'post_history 'as type_
		from post_history p where 
		p.id_community in (select id from community where name = $1)
		and p.post_history_type not  in (1,2,3)
	
)A where A.id_user is not null   
order by  A.creation_date asc

$function$
