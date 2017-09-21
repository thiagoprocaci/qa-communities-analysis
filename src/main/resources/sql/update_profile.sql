update user u set u.profile = 1 where u.id in (

	select B.id from  (
 SELECT
	  A.*,
	  CASE WHEN A.rank <= (select (count(*) * 10)/100 from chemistry_user)  THEN 4
	  		 WHEN A.rank <= (select (count(*) * 15)/100 from chemistry_user)
				and A.rank > (select (count(*) * 10)/100 from chemistry_user) THEN 3
				WHEN A.rank <= (select (count(*) * 20)/100 from chemistry_user)
				and A.rank > (select (count(*) * 15)/100 from chemistry_user) THEN 2
	       WHEN A.rank > (select (count(*) * 20)/100 from chemistry_user) THEN 1
	  END AS 'profile_temp'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from chemistry_user p ,
			(SELECT @rownum:=0) r order by reputation desc

	)A

) B where B.profile_temp = 1

);

update user u set u.profile = 2 where u.id in (

	select B.id from  (
 SELECT
	  A.*,
	  CASE WHEN A.rank <= (select (count(*) * 10)/100 from chemistry_user)  THEN 4
	  		 WHEN A.rank <= (select (count(*) * 15)/100 from chemistry_user)
				and A.rank > (select (count(*) * 10)/100 from chemistry_user) THEN 3
				WHEN A.rank <= (select (count(*) * 20)/100 from chemistry_user)
				and A.rank > (select (count(*) * 15)/100 from chemistry_user) THEN 2
	       WHEN A.rank > (select (count(*) * 20)/100 from chemistry_user) THEN 1
	  END AS 'profile_temp'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from chemistry_user p ,
			(SELECT @rownum:=0) r order by reputation desc

	)A

) B where B.profile_temp = 2

);

update user u set u.profile = 3 where u.id in (

	select B.id from  (
 SELECT
	  A.*,
	  CASE WHEN A.rank <= (select (count(*) * 10)/100 from chemistry_user)  THEN 4
	  		 WHEN A.rank <= (select (count(*) * 15)/100 from chemistry_user)
				and A.rank > (select (count(*) * 10)/100 from chemistry_user) THEN 3
				WHEN A.rank <= (select (count(*) * 20)/100 from chemistry_user)
				and A.rank > (select (count(*) * 15)/100 from chemistry_user) THEN 2
	       WHEN A.rank > (select (count(*) * 20)/100 from chemistry_user) THEN 1
	  END AS 'profile_temp'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from chemistry_user p ,
			(SELECT @rownum:=0) r order by reputation desc

	)A

) B where B.profile_temp = 3

);

update user u set u.profile = 4 where u.id in (

	select B.id from  (
 SELECT
	  A.*,
	  CASE WHEN A.rank <= (select (count(*) * 10)/100 from chemistry_user)  THEN 4
	  		 WHEN A.rank <= (select (count(*) * 15)/100 from chemistry_user)
				and A.rank > (select (count(*) * 10)/100 from chemistry_user) THEN 3
				WHEN A.rank <= (select (count(*) * 20)/100 from chemistry_user)
				and A.rank > (select (count(*) * 15)/100 from chemistry_user) THEN 2
	       WHEN A.rank > (select (count(*) * 20)/100 from chemistry_user) THEN 1
	  END AS 'profile_temp'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from chemistry_user p ,
			(SELECT @rownum:=0) r order by reputation desc

	)A

) B where B.profile_temp = 4

);

update user u set u.profile = 1 where u.id in (

	select B.id from  (
 SELECT
	  A.*,
	  CASE WHEN A.rank <= (select (count(*) * 10)/100 from biology_user)  THEN 4
	  		 WHEN A.rank <= (select (count(*) * 15)/100 from biology_user)
				and A.rank > (select (count(*) * 10)/100 from biology_user) THEN 3
				WHEN A.rank <= (select (count(*) * 20)/100 from biology_user)
				and A.rank > (select (count(*) * 15)/100 from biology_user) THEN 2
	       WHEN A.rank > (select (count(*) * 20)/100 from biology_user) THEN 1
	  END AS 'profile_temp'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from biology_user p ,
			(SELECT @rownum:=0) r order by reputation desc

	)A

) B where B.profile_temp = 1

);

update user u set u.profile = 2 where u.id in (

	select B.id from  (
 SELECT
	  A.*,
	  CASE WHEN A.rank <= (select (count(*) * 10)/100 from biology_user)  THEN 4
	  		 WHEN A.rank <= (select (count(*) * 15)/100 from biology_user)
				and A.rank > (select (count(*) * 10)/100 from biology_user) THEN 3
				WHEN A.rank <= (select (count(*) * 20)/100 from biology_user)
				and A.rank > (select (count(*) * 15)/100 from biology_user) THEN 2
	       WHEN A.rank > (select (count(*) * 20)/100 from biology_user) THEN 1
	  END AS 'profile_temp'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from biology_user p ,
			(SELECT @rownum:=0) r order by reputation desc

	)A

) B where B.profile_temp = 2

);

update user u set u.profile = 3 where u.id in (

	select B.id from  (
 SELECT
	  A.*,
	  CASE WHEN A.rank <= (select (count(*) * 10)/100 from biology_user)  THEN 4
	  		 WHEN A.rank <= (select (count(*) * 15)/100 from biology_user)
				and A.rank > (select (count(*) * 10)/100 from biology_user) THEN 3
				WHEN A.rank <= (select (count(*) * 20)/100 from biology_user)
				and A.rank > (select (count(*) * 15)/100 from biology_user) THEN 2
	       WHEN A.rank > (select (count(*) * 20)/100 from biology_user) THEN 1
	  END AS 'profile_temp'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from biology_user p ,
			(SELECT @rownum:=0) r order by reputation desc

	)A

) B where B.profile_temp = 3

);

update user u set u.profile = 4 where u.id in (

	select B.id from  (
 SELECT
	  A.*,
	  CASE WHEN A.rank <= (select (count(*) * 10)/100 from biology_user)  THEN 4
	  		 WHEN A.rank <= (select (count(*) * 15)/100 from biology_user)
				and A.rank > (select (count(*) * 10)/100 from biology_user) THEN 3
				WHEN A.rank <= (select (count(*) * 20)/100 from biology_user)
				and A.rank > (select (count(*) * 15)/100 from biology_user) THEN 2
	       WHEN A.rank > (select (count(*) * 20)/100 from biology_user) THEN 1
	  END AS 'profile_temp'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from biology_user p ,
			(SELECT @rownum:=0) r order by reputation desc

	)A

) B where B.profile_temp = 4

);