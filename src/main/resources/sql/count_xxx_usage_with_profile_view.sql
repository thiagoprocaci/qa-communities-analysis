create materialized  view count_you_usage_with_profile_biology_view as (

select * from count_you_usage_with_profile('biology.stackexchange.com')

);

create materialized  view count_you_usage_with_profile_chemistry_view as (

select * from count_you_usage_with_profile('chemistry.stackexchange.com')

);

create materialized  view count_we_usage_with_profile_biology_view as (

select * from count_we_usage_with_profile('biology.stackexchange.com')

);

create materialized  view count_we_usage_with_profile_chemistry_view as (

select * from count_we_usage_with_profile('chemistry.stackexchange.com')

);


create materialized  view count_they_usage_with_profile_biology_view as (

select * from count_they_usage_with_profile('biology.stackexchange.com')

);

create materialized  view count_they_usage_with_profile_chemistry_view as (

select * from count_they_usage_with_profile('chemistry.stackexchange.com')

);

create materialized  view count_she_he_usage_with_profile_biology_view as (

select * from count_she_he_usage_with_profile('biology.stackexchange.com')

);

create materialized  view count_she_he_usage_with_profile_chemistry_view as (

select * from count_she_he_usage_with_profile('chemistry.stackexchange.com')

);



create materialized  view count_i_usage_with_profile_biology_view as (

select * from count_i_usage_with_profile('biology.stackexchange.com')

);



create materialized  view count_i_usage_with_profile_biology_view as (

select * from count_i_usage_with_profile('biology.stackexchange.com')

)