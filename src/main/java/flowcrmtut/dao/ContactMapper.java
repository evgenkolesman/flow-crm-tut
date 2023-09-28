package flowcrmtut.dao;

import flowcrmtut.model.Company;
import flowcrmtut.model.Contact;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ContactMapper {

    @Select("""
            SELECT * FROM flowcrmtut.contact
            """)
    List<Company> getContactsList();

    @Select("""
            SELECT id, first_name, last_name, email, status_id , company_id
            FROM flowcrmtut.contact WHERE id = #{id}
            """)
    Contact getContactByName(@Param("id") UUID id);


    @Insert("""
            INSERT INTO flowcrmtut.contact(first_name, last_name, email, status_id, company_id)
            VALUES(#{firstName},#{lastName}, #{email}, #{status, javaType = Status, jdbcType=JAVA_OBJECT, typeHandler=ObjectTypeHandler}, #{company, javaType = Company, jdbcType=JAVA_OBJECT})
            """)
    @Options(useGeneratedKeys = true,
            keyProperty = "id",
            keyColumn = "id")
    UUID insertContact(Contact contact);
//JdbcType
    @Delete("""
            DELETE FROM flowcrmtut.contact
            WHERE id = #{id}
            """)
    void deleteContactById(@Param("id") UUID id);

}
