

create materialized  view count_i_usage_with_profile_chemistry_view as (

select * from count_i_usage_with_profile('chemistry.stackexchange.com')

)