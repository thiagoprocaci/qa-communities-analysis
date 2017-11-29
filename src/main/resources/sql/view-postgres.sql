
select B.*  ,
	case when B.top_5 = 0 and B.top_10 = 1  then 1
		 else 0
		 end as top_5_10,

	case when B.top_10 = 0 and B.top_15 = 1  then 1
		 else 0
		 end as top_10_15,

	case when B.top_15 = 0 and B.top_20 = 1  then 1
		 else 0
		 end as top_15_20,

	case when B.top_20 = 0 and B.top_25 = 1  then 1
		 else 0
		 end as top_20_25,

	case when B.top_25 = 0 and B.top_30 = 1  then 1
		 else 0
		 end as top_25_30,

	case when B.top_30 = 0 and B.top_35 = 1  then 1
		 else 0
		 end as top_30_35,

	case when B.top_35 = 0 and B.top_40 = 1  then 1
		 else 0
		 end as top_35_40,

	case when B.top_40 = 0 and B.top_45 = 1  then 1
		 else 0
		 end as top_40_45,

	case when B.top_45 = 0 and B.top_50 = 1  then 1
		 else 0
		 end as top_45_50,

	case when B.top_50 = 0  then 1
		 else 0
		 end as top_50_100
from (

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
		SELECT
			u.*,
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
)B




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



-- select column_name, data_type, character_maximum_length from INFORMATION_SCHEMA.COLUMNS where table_name = 'comm_user';
