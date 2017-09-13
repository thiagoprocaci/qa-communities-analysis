-- getting the tags
select
	p.*,
	replace(substring_index(p.tags,'>',1), '<', '') as tag1,
	replace(replace(	replace(substring_index(p.tags,'>',2), substring_index(p.tags,'>',1), ''), '>', '' ), '<', '')
	as tag2,
	replace(replace(	replace(substring_index(p.tags,'>',3), substring_index(p.tags,'>',2), ''), '>', '' ), '<', '')
	as tag3,
	replace(replace(	replace(substring_index(p.tags,'>',4), substring_index(p.tags,'>',3), ''), '>', '' ), '<', '')
	as tag4,
	replace(replace(	replace(substring_index(p.tags,'>',5), substring_index(p.tags,'>',4), ''), '>', '' ), '<', '')
	as tag5,
	replace(replace(	replace(substring_index(p.tags,'>',6), substring_index(p.tags,'>',5), ''), '>', '' ), '<', '')
	as tag6
from post p where p.tags is not null;

-- getting the questions
select * from  post p where p.post_type = 1

-- getting the answers
select * from  post p where p.post_type = 2

-- getting the experts
 SELECT
	  A.rank,
	  A.id,
	  CASE WHEN A.rank <= (select (count(*) * 10)/100 from user)  THEN 1
	       WHEN A.rank > (select (count(*) * 10)/100 from user) THEN 0
	  END AS 'expert'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from user p ,
			(SELECT @rownum:=0) r order by reputation desc

	)A ;

	-- checking the graph nodes

	select distinct id from (
    	select  distinct u.id from user u where u.id_community = 6
    	and u.id in (
    		select p.id_user from post p where p.id_community = 6 and (p.id not in (
    			select p.id from post p where p.id_community = 6 and p.answer_count = 0
    		) or p.id in (select c.id_post from comment c where c.id_post = p.id))
    	)
    	union all
    	select  u.id from user u where u.id_community = 6
    	and u.id in (
    		select p.id_user from comment p where p.id_community = 6 and p.id_post is not null
    	) and 1 = 1
    )A order by A.id


-------------------------

quartiles


select min(u.reputation) as reputation, (select 'min' from dual) as description from chemistry_user u
union all

select * from (
	select max(B.reputation) as reputation, B.description  from (

 SELECT
	  A.rank,
	  A.id,
	  A.reputation,
	  (select 'Q1' from dual) as description,
	  CASE WHEN A.rank <= (select (count(*) * 25)/100 from chemistry_user)  THEN 'Q1_start'
	  END AS 'quartile'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from chemistry_user p ,
			(SELECT @rownum:=0) r order by reputation asc

	)A

	)B where B.quartile = 'Q1_start' order by B.reputation
)C
	union all
select * from (
	SELECT avg(t1.reputation) as reputation, (select 'median' from dual) as description FROM (
SELECT @rownum:=@rownum+1 as `row_number`, d.reputation
  FROM chemistry_user d,  (SELECT @rownum:=0) r
  WHERE 1
  ORDER BY d.reputation
) as t1,
(
  SELECT count(*) as total_rows
  FROM chemistry_user d
  WHERE 1
) as t2
WHERE 1
AND t1.row_number in ( floor((total_rows+1)/2), floor((total_rows+2)/2))
	)C

	union all

select avg(u.reputation) as reputation, (select 'mean' from dual) as description from chemistry_user u
union all

select * from (
select min(B.reputation) as reputation, B.description from (

 SELECT
	  A.rank,
	  A.id,
	  A.reputation,
	  (select 'Q3' from dual) as description,
	  CASE WHEN A.rank >= (select (count(*) * 75)/100 from chemistry_user) THEN 'Q3_start'
	  END AS 'quartile'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from chemistry_user p ,
			(SELECT @rownum:=0) r order by reputation asc

	)A

	)B where B.quartile = 'Q3_start' order by B.reputation
)C

union all

select max(u.reputation) as reputation, (select 'max' from dual) as description from chemistry_user u