

create materialized  view count_i_usage_with_profile_biology_view as (

select * from count_i_usage_with_profile('biology.stackexchange.com')

)