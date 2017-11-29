create or replace VIEW ai_user AS select * from comm_user_ranking('ai.stackexchange.com');
create or replace VIEW biology_user AS select * from comm_user_ranking('biology.stackexchange.com');
create or replace VIEW chemistry_user AS select * from comm_user_ranking('chemistry.stackexchange.com');