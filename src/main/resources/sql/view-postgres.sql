
select
	A.*,
	case
		when A.ranking <= (A.total * 10/100) then 1
		when A.ranking > (A.total * 10/100) then 0
	end as top_10,

	case
		when A.ranking <= (A.total * 20/100) then 1
		when A.ranking > (A.total * 20/100) then 0
	end as top_20

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


