CREATE OR REPLACE FUNCTION public.comm_user_participation_by_category(community_name text)
 RETURNS TABLE(category text, questions numeric, answers numeric, comments numeric, ques_ans_comm numeric, reviews numeric, ques_ans_comm_rev numeric, accepted_answers numeric, total_users_in_category bigint, total_questions numeric, total_answers numeric, total_comments numeric, total_ques_ans_comm numeric, total_reviews numeric, total_ques_ans_comm_rev numeric, total_accepted_answers numeric, total_users_in_community numeric, perc_questions numeric, perc_answers numeric, perc_comments numeric, perc_ques_ans_comm numeric, perc_reviews numeric, perc_ques_ans_comm_rev numeric, perc_accepted_answers numeric, questions_per_user numeric, answers_per_user numeric, comments_per_user numeric, ques_ans_comm_per_user numeric, reviews_per_user numeric, ques_ans_comm_rev_per_user numeric, accepted_answers_per_user numeric)
 LANGUAGE sql
AS $function$

with participation_by_category as (

select
	A.category,
	sum(A.questions) as questions,
	sum(A.answers) as answers,
	sum(A.comments) as comments,
	(sum(A.questions) + sum(A.answers) + sum(A.comments)) as ques_ans_comm,
	sum(A.reviews) as reviews,
	(sum(A.questions) + sum(A.answers) + sum(A.comments) + sum(A.reviews)) as ques_ans_comm_rev,
	sum(A.accepted_answers) as accepted_answers,
	count(*) as total_users_in_category
	from (
	select u.*,
		case
		   	when u.top_5 = 1 then 'top_5'
		   	when u.top_5_10 = 1 then 'top_5_10'
		   	when u.top_10_15 = 1 then 'top_10_15'
		   	when u.top_15_20 = 1 then 'top_15_20'
		   	when u.top_20_25 = 1 then 'top_20_25'
		   	when u.top_25_30 = 1 then 'top_25_30'
		   	when u.top_30_35 = 1 then 'top_30_35'
		   	when u.top_35_40 = 1 then 'top_35_40'
		   	when u.top_40_45 = 1 then 'top_40_45'
		   	when u.top_45_50 = 1 then 'top_45_50'
		   	when u.top_50_100 = 1 then 'top_50_100'
		   	when u.no_participation = 1 then 'no_participation'
		   end as category,
		 case
		   	when u.top_5 = 1 then 1
		   	when u.top_5_10 = 1 then 2
		   	when u.top_10_15 = 1 then 3
		   	when u.top_15_20 = 1 then 4
		   	when u.top_20_25 = 1 then 5
		   	when u.top_25_30 = 1 then 6
		   	when u.top_30_35 = 1 then 7
		   	when u.top_35_40 = 1 then 8
		   	when u.top_40_45 = 1 then 9
		   	when u.top_45_50 = 1 then 10
		   	when u.top_50_100 = 1 then 11
		   	when u.no_participation = 1 then 12
		   end as category_id
	from comm_user_ranking_profile($1) u
	)A group by A.category, A.category_id order by A.category_id
) ,
GENERAL_PARTICIPATION as (
	select
		sum(A.questions) as total_questions,
		sum(A.answers) as total_answers,
		sum(A.comments) as total_comments,
		sum(A.ques_ans_comm) as total_ques_ans_comm,
		sum(A.reviews) as total_reviews,
		sum(A.ques_ans_comm_rev) as total_ques_ans_comm_rev,
		sum(A.accepted_answers) as total_accepted_answers,
		sum(total_users_in_category) as total_users_in_community
	from participation_by_category A
),
all_data as (
	select * from participation_by_category, GENERAL_PARTICIPATION
)
select a.*,
	  (a.questions * 100/total_questions) as perc_questions,
	  (a.answers * 100/total_answers) as perc_answers,
	  (a.comments * 100/total_comments) as perc_comments,
	  (a.ques_ans_comm * 100/total_ques_ans_comm) as perc_ques_ans_comm,
	  (a.reviews * 100/total_reviews) as perc_reviews,
	  (a.ques_ans_comm_rev * 100/total_ques_ans_comm_rev) as perc_ques_ans_comm_rev,
	  (a.accepted_answers * 100/total_accepted_answers) as perc_accepted_answers,

	  (a.questions/total_users_in_category) as questions_per_user,
	  (a.answers/total_users_in_category) as answers_per_user,
	  (a.comments/total_users_in_category) as comments_per_user,
	  (a.ques_ans_comm/total_users_in_category) as ques_ans_comm_per_user,
	  (a.reviews/total_users_in_category) as reviews_per_user,
	  (a.ques_ans_comm_rev/total_users_in_category) as ques_ans_comm_rev_per_user,
	  (a.accepted_answers/total_users_in_category) as accepted_answers_per_user

from all_data a

$function$
