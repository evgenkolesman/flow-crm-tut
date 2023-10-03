create or replace function flowcrmtut.count_con(pattern character varying) returns integer
    language plpgsql
as
-- Function needed for test usr of function type sql in MyBatis framework
$$
DECLARE
    res int;
BEGIN
    SELECT count(id) FROM contact c  WHERE c.first_name ~  pattern INTO res;
    RETURN  res;
END;
$$;

alter function flowcrmtut.count_con(varchar) owner to postgres;