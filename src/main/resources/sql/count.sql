select count(*) from comment
union all
select count(*) from community
union all
select count(*) from post
union all
select count(*) from post_link
union all
select count(*) from user
union all
select count(*) from vote