CREATE OR REPLACE FUNCTION public.post_tags(community_name text)
 RETURNS TABLE(
 id bigint,
accepted_answer_comm_id bigint,
answer_count integer,
ari_text double precision,
ari_title double precision,
body text,
characters_text double precision,
characters_title double precision,
closed_date timestamp without time zone,
coleman_liau_text double precision,
coleman_liau_title double precision,
comment_count integer,
community_owned_date timestamp without time zone,
complexwords_text double precision,
complexwords_title double precision,
creation_date timestamp without time zone,
favorite_count integer,
flesch_kincaid_text double precision,
flesch_kincaid_title double precision,
flesch_reading_text double precision,
flesch_reading_title double precision,
gunning_fog_text double precision,
gunning_fog_title double precision,
id_post_comm bigint,
id_user_community bigint,
last_activity_date timestamp without time zone,
last_edit_date timestamp without time zone,
last_editor_display_name character varying,
last_editor_user_community_id bigint,
parent_post_comm_id bigint,
period integer,
post_type integer,
score integer,
sentences_text double precision,
sentences_title double precision,
smog_index_text double precision,
smog_index_title double precision,
smog_text double precision,
smog_title double precision,
syllables_text double precision,
syllables_title double precision,
tags character varying,
title character varying,
view_count integer,
words_text double precision,
words_title double precision,
id_community integer,
id_user bigint,
tag1 text,
tag2 text,
tag3 text,
tag4 text,
tag5 text,
tag6 text)
LANGUAGE sql
AS $function$
select p.* ,
replace(split_part(p.tags,'>',1 ), '<', '') as tag1,
replace(replace(replace(split_part(p.tags,'>',2), split_part(p.tags,'>',1), ''), '>', '' ), '<', '')
as tag2,
replace(replace(	replace(split_part(p.tags,'>',3), split_part(p.tags,'>',2), ''), '>', '' ), '<', '')
as tag3,
replace(replace(	replace(split_part(p.tags,'>',4), split_part(p.tags,'>',3), ''), '>', '' ), '<', '')
as tag4,
replace(replace(	replace(split_part(p.tags,'>',5), split_part(p.tags,'>',4), ''), '>', '' ), '<', '')
as tag5,
replace(replace(	replace(split_part(p.tags,'>',6), split_part(p.tags,'>',5), ''), '>', '' ), '<', '')
as tag6

from post p
where p.post_type = 1
and p.id_community in (select c.id from community c where c.name = $1)


$function$