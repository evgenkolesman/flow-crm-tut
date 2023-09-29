package ru.flowcrmtut.dao;

import ru.flowcrmtut.model.Status;
import ru.flowcrmtut.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StatusMapper {

    @Results({
            @Result(column = "id", property = "id", typeHandler = UUIDTypeHandler.class),
            @Result(column = "name", property = "name")})
    @Select("""
            SELECT * FROM flowcrmtut.status
            """)
    List<Status> getStatusesList();

    @Results({
            @Result(column = "id", property = "id", typeHandler = UUIDTypeHandler.class),
            @Result(column = "name", property = "name")})
    @Select("""
            SELECT id, name  FROM flowcrmtut.status WHERE name = #{name}
            """)
    List<Status> getStatusByName(@Param("name") String name);

    @Results({
            @Result(column = "id", property = "id", typeHandler = UUIDTypeHandler.class),
            @Result(column = "name", property = "name")})
 @Select("""
            SELECT id, name  FROM flowcrmtut.status WHERE id = #{id}::uuid
            """)
    Status getStatusById(@Param("id") String id);


    @Select("""
            INSERT INTO flowcrmtut.status(name)
            VALUES(#{name}) RETURNING id
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id",
            keyColumn = "id")
    @Result(id = true, column = "id")
    String insertStatus(Status status);

    @Delete("""
            DELETE FROM flowcrmtut.status
            WHERE name = #{name}
            """)
    void deleteStatusByName(@Param("name") String name);




}
