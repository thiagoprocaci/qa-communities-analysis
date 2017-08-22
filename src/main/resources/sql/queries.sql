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


