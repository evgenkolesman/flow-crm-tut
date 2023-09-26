package flowcrmtut.dao;

import flowcrmtut.model.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Select("""
            SELECT * FROM flowcrmtut.company
            """)
    List<Company> getCompaniesList();

    @Select("""
            SELECT * FROM flowcrmtut.company WHERE name = #{name}
            """)
    List<Company> getCompanyByName(@Param("name") String name);


    @Insert("""
            INSERT INTO flowcrmtut.company(name)
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
