create view  biology_max_graph_analysis_context as (
select g.* from graph_analysis_context g
inner join community c on g.id_community = c.id
where c.name = 'biology.stackexchange.com'
order by g.period desc limit 1

);



CREATE VIEW giant_component_biology_max_period as (
select n.strongly_component
			from graph_node n
inner join biology_max_graph_analysis_context g
on n.id_graph_analysis_context = g.id
 group by n.strongly_component  order by count(*) desc limit 1

);

create view  chemistry_max_graph_analysis_context as (
select g.* from graph_analysis_context g
inner join community c on g.id_community = c.id
where c.name = 'chemistry.stackexchange.com'
order by g.period desc limit 1

);

CREATE VIEW giant_component_chemistry_max_period as (
select n.strongly_component
			from graph_node n
inner join chemistry_max_graph_analysis_context g
on n.id_graph_analysis_context = g.id
 group by n.strongly_component  order by count(*) desc limit 1

);
