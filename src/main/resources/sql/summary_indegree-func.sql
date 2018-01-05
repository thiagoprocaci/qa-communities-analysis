CREATE OR REPLACE FUNCTION public.summary_indegree(community_name text)
 RETURNS TABLE(value double precision, desc_ text)
 LANGUAGE sql
AS $function$

with quartiles as (
select indegree, ntile(4) over (order by indegree) as quartile from graph_node
	where id_graph_analysis_context in (
		select id from last_graph_analysis_context($1)
	)
)
select min(indegree) as "value", 'min' as desc_ from quartiles
union all
select max(indegree) as "value",  'first_quartile' as desc_ from quartiles  where quartile = 1
union all
SELECT PERCENTILE_CONT(0.5) WITHIN GROUP(ORDER by indegree) as "value", 'median' as desc_ from graph_node
	where id_graph_analysis_context in (
		select id from last_graph_analysis_context($1)
	)

union all
select avg(indegree) as "value", 'mean' as desc_ from quartiles
union all
select max(indegree) as "value",  'third_quartile' as desc_ from quartiles  where quartile = 3
union all
select max(indegree) as "value", 'max' as desc_ from quartiles

$function$
