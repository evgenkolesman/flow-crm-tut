package flowcrmtut.dao;

import flowcrmtut.model.Company;
import flowcrmtut.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Results({
        @Result(column = "id", property = "id", typeHandler = UUIDTypeHandler.class),
        @Result(column = "name", property = "name")})
    @Select("""
            SELECT id, name  FROM flowcrmtut.company
            """)
    List<Company> getCompaniesList();

    @Select("""
            SELECT id, name FROM flowcrmtut.company WHERE name = #{name}
            """)
    List<Company> getCompanyByName(@Param("name") String name);

 @Select("""
            SELECT id, name FROM flowcrmtut.company WHERE id = #{id}::UUID
            """)
    Company getCompanyById(@Param("id") String id);


    @Select("""
            INSERT INTO flowcrmtut.company(name)
            VALUES(#{name}) RETURNING id
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id",
            keyColumn = "id")
    @Result(id = true, column = "id")
    String insertCompany(Company company);

    @Delete("""
            DELETE FROM flowcrmtut.company
            WHERE name = #{name}
            """)
    void deleteCompanyByName(@Param("name") String name);




}
