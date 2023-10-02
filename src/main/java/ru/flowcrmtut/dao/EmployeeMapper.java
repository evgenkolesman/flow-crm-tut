package ru.flowcrmtut.dao;

import org.apache.ibatis.annotations.*;
import ru.flowcrmtut.model.Employee;
import ru.flowcrmtut.typehandler.UUIDTypeHandler;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Results({
        @Result(column = "id", property = "id", typeHandler = UUIDTypeHandler.class),
        @Result(column = "name", property = "name")})
    @Select("""
            SELECT id, employee_data  FROM flowcrmtut.employee WHERE company_id = #{company_id}::uuid
            """)
    List<Employee> getEmployeeListByCompanyId(@Param("company_id") String company_id);

    @Select("""
            SELECT count(id)  FROM flowcrmtut.employee WHERE company_id = #{company_id}::uuid
            """)
    int countEmployeeInCompany(@Param("company_id") String company_id);




//
//    @Select("""
//            SELECT id, name FROM flowcrmtut.company WHERE name = #{name}
//            """)
//    @Results({
//            @Result(column = "id", property = "id", typeHandler = UUIDTypeHandler.class),
//            @Result(column = "name", property = "name")})
//    List<Company> getCompanyByName(@Param("name") String name);
//
// @Select("""
//            SELECT id, name FROM flowcrmtut.company WHERE id = #{id}::UUID
//            """)
// @Results({
//         @Result(column = "id", property = "id", typeHandler = UUIDTypeHandler.class),
//         @Result(column = "name", property = "name")})
//    Company getCompanyById(@Param("id") String id);
//
//
//    @Select("""
//            INSERT INTO flowcrmtut.company(name)
//            VALUES(#{name}) RETURNING id
//            """)
//    @Options(useGeneratedKeys = true, keyProperty = "id",
//            keyColumn = "id")
//    @Result(id = true, column = "id")
//    String insertCompany(Company company);
//
//    @Delete("""
//            DELETE FROM flowcrmtut.company
//            WHERE name = #{name}
//            """)
//    void deleteCompanyByName(@Param("name") String name);
//
//


}
