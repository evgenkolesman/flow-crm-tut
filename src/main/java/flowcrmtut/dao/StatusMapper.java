package flowcrmtut.dao;

import flowcrmtut.model.Status;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StatusMapper {

    @Select("""
            SELECT * FROM flowcrmtut.status
            """)
    List<Status> getStatusesList();

    @Select("""
            SELECT * FROM flowcrmtut.status WHERE name = #{name}
            """)
    List<Status> getStatusByName(@Param("name") String name);

 @Select("""
            SELECT * FROM flowcrmtut.status WHERE id = #{id}
            """)
    Status getStatusById(@Param("id") String id);


    @Insert("""
            INSERT INTO flowcrmtut.status(name)
            VALUES(#{name})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id",
            keyColumn = "id")
    void insertStatus(Status status);

    @Delete("""
            DELETE FROM flowcrmtut.status
            WHERE name = #{name}
            """)
    void deleteStatusByName(@Param("name") String name);




}
