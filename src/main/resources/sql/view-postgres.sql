
select
	A.*,

	case
		when A.ranking <= (A.total * 5/100) then 1
		when A.ranking > (A.total * 5/100) then 0
	end as top_5,

	case
		when A.ranking <= (A.total * 10/100) then 1
		when A.ranking > (A.total * 10/100) then 0
	end as top_10,

	case
		when A.ranking <= (A.total * 15/100) then 1
		when A.ranking > (A.total * 15/100) then 0
	end as top_15,

	case
		when A.ranking <= (A.total * 20/100) then 1
		when A.ranking > (A.total * 20/100) then 0
	end as top_20,

	case
		when A.ranking <= (A.total * 25/100) then 1
		when A.ranking > (A.total * 25/100) then 0
	end as top_25,

	case
		when A.ranking <= (A.total * 30/100) then 1
		when A.ranking > (A.total * 30/100) then 0
	end as top_30,

	case
		when A.ranking <= (A.total * 35/100) then 1
		when A.ranking > (A.total * 35/100) then 0
	end as top_35,

	case
		when A.ranking <= (A.total * 40/100) then 1
		when A.ranking > (A.total * 40/100) then 0
	end as top_40,

	case
		when A.ranking <= (A.total * 45/100) then 1
		when A.ranking > (A.total * 45/100) then 0
	end as top_45,

	case
		when A.ranking <= (A.total * 50/100) then 1
		when A.ranking > (A.total * 50/100) then 0
	end as top_50

from


(
	SELECT u.id,
		u.reputation,
	    row_number() OVER (
	            order by u.reputation desc
	    ) as ranking,
	    (select count(*) from comm_user
	    where id_community
	    in (select co.id from community co where co.name = 'ai.stackexchange.com')) as total
	  FROM comm_user u
	  where u.id_community in (select co.id from community co where co.name = 'ai.stackexchange.com')
	  order by u.reputation desc
)A




-----------


with USER_TABLE as (
		select * from comm_user u where
		u.id_community in (select co.id from community co where co.name = 'biology.stackexchange.com')
),

 USER_QUESTIONS as (

	select u.id, count(*) as questions from comm_user u
		left join post p on u.id = p.id_user
	where  p.post_type = 1
	and u.id_community in (select co.id from community co where co.name = 'biology.stackexchange.com')
	group by u.id

),
  USER_ANSWERS as (
	select u.id, count(*) as answers from comm_user u
		left join post p on u.id = p.id_user
	where  p.post_type = 2
	and u.id_community in (select co.id from community co where co.name = 'biology.stackexchange.com')
	group by u.id
),
  USER_COMMENTS as (
	select u.id, count(*) as comments from comm_user u
		left join "comment" p on u.id = p.id_user
	where
	u.id_community in (select co.id from community co where co.name = 'biology.stackexchange.com')
	group by u.id
)


select ut.id as user_id, ut.reputation,
		COALESCE(ua.answers, 0) as answers,
		COALESCE(uq.questions, 0) as questions,
		COALESCE(uc.comments, 0) as comments from USER_TABLE ut
	left join USER_ANSWERS ua on ut.id = ua.id
	left join USER_QUESTIONS uq on ut.id = uq.id
	left join USER_COMMENTS uc on ut.id = uc.id
	--order by ut.reputation asc


