CREATE OR REPLACE FUNCTION public.last_graph_analysis_context(community_name text)
 RETURNS TABLE(
 id	bigint,
 avg_clustering_coef	double precision,
avg_degree	double precision,
avg_dist	double precision,
density	double precision,
diameter	double precision,
edges	integer,
id_community	integer,
modularity	double precision,
modularity_with_resolution	double precision,
nodes	integer,
number_communities	integer,
period	integer,
radius	double precision,
strongly_component_count	integer,
weakly_component_count	integer

 )
 LANGUAGE sql
AS $function$


select
ctx.id,
ctx.avg_clustering_coef,
ctx.avg_degree,
ctx.avg_dist,
ctx.density,
ctx.diameter,
ctx.edges,
ctx.id_community,
ctx.modularity,
ctx.modularity_with_resolution,
ctx.nodes,
ctx.number_communities,
ctx.period,
ctx.radius,
ctx.strongly_component_count,
ctx.weakly_component_count


from graph_analysis_context ctx
where ctx.id_community in (select id from community where name = $1)
and ctx.period in (
	select max(ctx.period) from graph_analysis_context ctx
	where ctx.id_community in (select id from community where name = $1)
)


$function$
-- select column_name, data_type
-- from INFORMATION_SCHEMA.COLUMNS where table_name = 'graph_analysis_context';