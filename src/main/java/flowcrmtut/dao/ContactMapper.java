package flowcrmtut.dao;

import flowcrmtut.model.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ContactMapper {

    @Select("""
            SELECT * FROM flowcrmtut.contact
            """)
    List<Company> getContactsList();

    @Select("""
            SELECT * FROM flowcrmtut.contact WHERE name = #{name}
            """)
    List<Company> getcContactByName(@Param("name") String name);


    @Insert("""
            INSERT INTO flowcrmtut.contact(name)
            VALUES(#{name})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id",
            keyColumn = "id")
    void insertCompany(Company company);

    @Delete("""
            DELETE FROM flowcrmtut.company
            WHERE name = #{name}
            """)
    void deleteCompanyByName(@Param("name") String name);




}
